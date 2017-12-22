package entity;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class Message {
    private long id;
    private SendMessage sendMessage;
    private long keyboardMarkUpId;
    private SendPhoto sendPhoto;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setKeyboardMarkUpId(long keyboardMarkUpId) {
        this.keyboardMarkUpId = keyboardMarkUpId;
    }

    public long getKeyboardMarkUpId() {
        return keyboardMarkUpId;
    }

    public void setSendPhoto(SendPhoto sendPhoto) {
        this.sendPhoto = sendPhoto;
    }

    public SendPhoto getSendPhoto() {
        return sendPhoto;
    }
}
