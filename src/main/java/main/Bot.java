package main;

import command.Command;
import command.CommandFactory;
import dao.DaoFactory;
import dao.impl.GoalDao;
import dao.impl.MessageDao;
import dao.impl.ResultDao;
import entity.Result;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tool.Chart;
import tool.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class Bot extends TelegramLongPollingBot {
    public final static Long ADMIN_CHAT_ID = 293188753L;
    public static final int TWO_WEEKS = 14;
    private Command command = null;
    public static Map<Integer, Integer> successIndexAndGoalId = new HashMap<>();
    private static DaoFactory factory = DaoFactory.getFactory();
    private static ResultDao resultDao = factory.getResultDao();
    private static MessageDao messageDao = factory.getMessageDao();
    private static GoalDao goalDao = factory.getGoalDao();
    private static Chart chart = new Chart();
    public static boolean waitingAnswer = false;
    public static Map<String, String> answers = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        boolean access = checkAccess(chatId);
        if (!access) return;
        String updateMessageText = update.getMessage().getText();
        if (waitingAnswer) {
            answers.put(DateUtil.getTime(), updateMessageText);
            sendMessage("Answer was added");
            waitingAnswer = false;
        } else {
            if (updateMessageText.equalsIgnoreCase("showall")) {
                sendAnswers();
            } else {
                sendMessage("For this moment nothing is work");
            }
        }
        /*
        try {
            command = CommandFactory.getCommand(updateMessageText);
        } catch (Exception e1) {
            System.out.println("No command for '" + updateMessageText + "'");// TODO: 06.01.2018 should be log
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
        }*/
    }

    private void sayCannotHandle(Long chatId) {
        try {
            sendMessage(new SendMessage()
                    .setChatId(chatId)
                    .setText("Cannot handle command or incorrect data was inputted")
                    .setReplyMarkup(DaoFactory.getFactory().getKeyboardMarkUpDao().select(1))
            );
        } catch (TelegramApiException | SQLException e) {
            e.printStackTrace();
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
        double hours = successIndexAndGoalId.size() / 2;
        resultDao.insert(DateUtil.getPastDay(), hours);
        clearResults();
    }

    public static void clearResults() {
        successIndexAndGoalId = new HashMap<>();
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

    public static void sendResults() {
        List<String> dates = new ArrayList<>();
        int hours = 6;
        int minutes = 0;

        while (hours < 23) {
            String e = hours + ":" + minutes;
            if (minutes == 0) {
                e += "0";
            }
            dates.add(e);
            if (minutes == 0) {
                minutes = 30;
            } else {
                minutes = 0;
                hours++;
            }
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < dates.size(); i++) {
            String str = (i + 1) + ") " + dates.get(i) + " - ";
            if (Bot.successIndexAndGoalId.containsKey(i)) {
                Integer goalId = successIndexAndGoalId.get(i);
                if (goalId == 0) {
                    str += messageDao.getMessageText(1);//green emoji
                } else {
                    str += goalDao.getEmoji(goalId);
                }
            } else {
                str += messageDao.getMessageText(2);//red emoji
            }
            str = str.trim();
            if ((i + 1) % 2 == 0) {
                str += "\n";
            } else {
                str += "      ";
            }
            s.append(str);
        }
        try {
            new Bot().sendMessage(
                    new SendMessage()
                            .setText(s.toString())
                            .setChatId(ADMIN_CHAT_ID)
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String text) {
        sendMessage(text, ADMIN_CHAT_ID, null);
    }

    public void sendMessage(String text, Long chatId, ReplyKeyboard keyboard) {
        try {
            sendMessage(new SendMessage()
                    .setText(text)
                    .setChatId(chatId)
                    .setReplyMarkup(keyboard)
                    .setParseMode(ParseMode.HTML)
            );
        } catch (TelegramApiException ignored) {
        }
    }

    public static void sendAnswers() {
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            new Bot().sendMessage(entry.getKey() + " - " + entry.getValue());
        }
    }
}
