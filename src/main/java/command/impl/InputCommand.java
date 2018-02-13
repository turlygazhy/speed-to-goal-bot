package command.impl;

import command.Command;
import dao.DaoFactory;
import entity.Button;
import entity.Goal;
import entity.WaitingType;
import main.Bot;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class InputCommand extends Command {
    private int goalId;

    @Override
    public boolean execute() {
        if (waitingType == null) {
            sendMessage("Choose goal", getGoalsKeyboard());
            waitingType = WaitingType.GOAL_ID;
            return false;
        }
        switch (waitingType) {
            case GOAL_ID:
                goalId = Integer.parseInt(updateMessageText);
                try {
                    bot.editMessageText(
                            new EditMessageText()
                                    .setText("Please wait...")
                                    .setChatId(chatId)
                    );
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                sendMessage("Send how much time you spend in minutes");
                waitingType = WaitingType.MINUTES;
                return false;
            case MINUTES:

            default:
                throw new RuntimeException();
        }
    }

    private ReplyKeyboard getGoalsKeyboard() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<Goal> goals = DaoFactory.getFactory().getGoalDao().selectAll();

        for (Goal goal : goals) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(goal.getName());
            button.setCallbackData(String.valueOf(goal.getId()));
            row.add(button);
            rows.add(row);
        }

        keyboard.setKeyboard(rows);
        return keyboard;
    }

}
