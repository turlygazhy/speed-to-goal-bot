package command;

import dao.DaoFactory;
import dao.impl.*;
import entity.Result;
import entity.WaitingType;
import main.Bot;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import service.ResultService;
import tool.Chart;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public abstract class Command {
    protected String updateMessageText;
    protected WaitingType waitingType;
    protected Bot bot;
    protected Long chatId;
    protected ResultService resultService;

    protected DaoFactory factory = DaoFactory.getFactory();
    protected ButtonDao buttonDao = factory.getButtonDao();
    protected KeyboardMarkUpDao keyboardMarkUpDao = factory.getKeyboardMarkUpDao();
    protected MessageDao messageDao = factory.getMessageDao();
    protected ResultDao resultDao = factory.getResultDao();

    protected Message updateMessage;

    public abstract boolean execute();

    public void init(Update update, Bot bot) {
        updateMessage = update.getMessage();
        if (updateMessage == null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            updateMessage = callbackQuery.getMessage();
            updateMessageText = callbackQuery.getData();
        } else {
            updateMessageText = updateMessage.getText();
        }
        this.bot = bot;
        chatId = updateMessage.getChatId();
        resultService = new ResultService(chatId);
    }

    protected void sendMessage(String text) {
        sendMessage(text, chatId, null);
    }

    protected void sendMessage(int messageId) {
        try {
            entity.Message message = messageDao.getMessage(messageId);
            sendMessage(message);
        } catch (SQLException | TelegramApiException ignored) {
        }
    }

    private void sendMessage(entity.Message message) throws TelegramApiException, SQLException {
        SendPhoto photo = message.getSendPhoto();
        if (photo != null) {
            bot.sendPhoto(photo.setChatId(chatId));
        }
        sendMessage(message.getSendMessage().getText(), keyboardMarkUpDao.select(message.getKeyboardMarkUpId()));
    }

    public void sendMessage(String text, ReplyKeyboard keyboard) {
        sendMessage(text, chatId, keyboard);
    }

    protected void sendMessage(String text, Long chatId, ReplyKeyboard keyboard) {
        try {
            bot.sendMessage(new SendMessage()
                    .setText(text)
                    .setChatId(chatId)
                    .setReplyMarkup(keyboard)
                    .setParseMode(ParseMode.HTML)
            );
        } catch (TelegramApiException ignored) {
        }
    }

    protected ReplyKeyboard getKeyboard(int keyboardId) {
        try {
            return keyboardMarkUpDao.select(keyboardId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ReplyKeyboard getKeyboard(String[]... buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rowsList = new ArrayList<>();

        for (String[] row : buttons) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String buttonText : row) {
                KeyboardButton button = new KeyboardButton();
                button.setText(buttonText);
                keyboardRow.add(button);
            }
            rowsList.add(keyboardRow);
        }

        replyKeyboardMarkup.setKeyboard(rowsList);
        return replyKeyboardMarkup;
    }

    public void showTodaysChart() {
        List<Result> results = resultDao.selectForToday(chatId);
        String chart = Chart.getChart(results);
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setNewPhoto(new File(chart));
            bot.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void getChart() {
        // TODO: 03.04.20 тут мы должны взять минуты и по ним посчитать сколько очком у нас есть
    }
}
