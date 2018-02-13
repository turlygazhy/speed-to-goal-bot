package main;

import command.Command;
import command.CommandFactory;
import dao.DaoFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class Bot extends TelegramLongPollingBot {
    public final static Long ADMIN_CHAT_ID = 293188753L;
    private Command command;

    @Override
    public void onUpdateReceived(Update update) {
        Message updateMessage = update.getMessage();
        String updateMessageText;
        if (updateMessage == null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            updateMessage = callbackQuery.getMessage();
            updateMessageText = callbackQuery.getData();
        } else {
            updateMessageText = updateMessage.getText();
        }
        Long chatId = updateMessage.getChatId();
        if (!checkAccess(chatId)) {
            return;
        }
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
        }
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
}
