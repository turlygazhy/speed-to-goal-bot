package command.impl;

import command.Command;
import entity.Const;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class MyGoalsCommand extends Command {
    private int workedHours = 0;
    private int learnedMinutes = 0;

    @Override
    public boolean execute() {
        if (waitingType == null) {
            sendResults();
        }
        return false;
    }

    private void sendResults() {
        String result = "Worked hours = " + workedHours +
                "\nLearned minutes = " + learnedMinutes;
        sendMessage(result, chatId, getKeyboard(1));
    }



    private String[] row(String... s) {
        return s;
    }


}
