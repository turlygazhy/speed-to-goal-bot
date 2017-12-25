package main;

import command.Command;
import command.CommandFactory;
import dao.DaoFactory;
import dao.impl.ResultDao;
import entity.Result;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tool.Chart;
import tool.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class Bot extends TelegramLongPollingBot {
    public final static Long ADMIN_CHAT_ID = 293188753L;
    public static final int TWO_WEEKS = 14;
    private Command command = null;
    public static Set<Integer> successIndexes = new HashSet<>();
    private static ResultDao resultDao = DaoFactory.getFactory().getResultDao();
    private static Chart chart = new Chart();

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();

        boolean access = checkAccess(chatId);
        if (!access) return;

        try {
            command = CommandFactory.getCommand(update);
        } catch (Exception ignored) {
        }
        if (command != null) {
            try {
                command.init(update, this);
                boolean execute = command.execute();
                if (execute) {
                    command = null;
                }
            } catch (Exception e) {
                command = null;
                sayCannotHandle(chatId);
            }
        } else {
            sayCannotHandle(chatId);
        }
    }

    private void sayCannotHandle(Long chatId) {
        try {
            sendMessage(new SendMessage()
                    .setChatId(chatId)
                    .setText("Cannot handle command or incorrect data was inputted")
                    .setReplyMarkup(DaoFactory.getFactory().getKeyboardMarkUpDao().select(1))
            );
        } catch (TelegramApiException | SQLException ignored) {
        }
    }

    private boolean checkAccess(Long chatId) {
        if (!Objects.equals(chatId, ADMIN_CHAT_ID)) {
            try {
                sendMessage(new SendMessage()
                        .setChatId(chatId)
                        .setText("This is private bot, sorry you cannot use it.")
                );
            } catch (TelegramApiException ignored) {
            }
            return false;
        }
        return true;
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "347871545:AAHm1ZsUSGknq18dbTyJgk_ETji25dasvGQ";//https://web.telegram.org/#/im?p=@q_personal_assistant_bot
    }

    public static void saveAndClearResult() {
        double hours = successIndexes.size() / 2;
        resultDao.insert(DateUtil.getPastDay(), hours);
        successIndexes = new HashSet<>();
    }

    public static void sendChart() {
        List<Result> results = resultDao.select(TWO_WEEKS);
        File file;
        FileInputStream fileInputStream;
        String filePath = chart.getChart(results);
        file = new File(filePath);

        try {
            fileInputStream = new FileInputStream(file + ".jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            new Bot().sendPhoto(new SendPhoto()
                    .setChatId(ADMIN_CHAT_ID)
                    .setNewPhoto("photo", fileInputStream)
            );
        } catch (TelegramApiException ignored) {
        }
    }
}
