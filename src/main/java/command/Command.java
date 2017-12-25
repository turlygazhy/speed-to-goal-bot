package command;

import dao.DaoFactory;
import dao.impl.ButtonDao;
import dao.impl.KeyboardMarkUpDao;
import dao.impl.MessageDao;
import entity.WaitingType;
import main.Bot;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

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

    protected DaoFactory factory = DaoFactory.getFactory();
    protected ButtonDao buttonDao = factory.getButtonDao();
    protected KeyboardMarkUpDao keyboardMarkUpDao = factory.getKeyboardMarkUpDao();
    protected MessageDao messageDao = factory.getMessageDao();

    public abstract boolean execute();

    public void init(Update update, Bot bot) {
        Message updateMessage = update.getMessage();
        this.bot = bot;
        updateMessageText = updateMessage.getText();
        chatId = updateMessage.getChatId();
    }

    protected void sendMessage(String text) {
        sendMessage(text, chatId, null);
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
}