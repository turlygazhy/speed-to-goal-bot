package command.impl;

import command.Command;
import entity.WaitingType;
import main.Bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class InputCommand extends Command {

    @Override
    public boolean execute() {
        if (waitingType == null) {
            Bot.sendResults();
            waitingType = WaitingType.TIME_SLOT;
            return false;
        }
        switch (waitingType) {
            case TIME_SLOT:
                int index = Integer.parseInt(updateMessageText) - 1;
                Bot.successIndexes.add(index);
                Bot.sendResults();
                return false;
            default:
                throw new RuntimeException();
        }
    }

}
