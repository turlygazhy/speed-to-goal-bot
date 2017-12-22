package main;

import command.Command;
import command.CommandFactory;
import dao.DaoFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class Bot extends TelegramLongPollingBot {
    public final static Long ADMIN_CHAT_ID = 293188753L;
    private Command command = null;
    public static Set<Integer> successIndexes = new HashSet<>();// TODO: 20-Dec-17 work around, need to keep in db


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
}
