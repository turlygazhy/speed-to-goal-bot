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
    private int index = 0;

    @Override
    public boolean execute() {
        if (waitingType == null) {
            Bot.sendResults();
            waitingType = WaitingType.TIME_SLOT;
            return false;
        }
        switch (waitingType) {
            case TIME_SLOT:
                index = Integer.parseInt(updateMessageText) - 1;
                waitingType = WaitingType.GOAL_ID;
                sendMessage("1) Work\n2) Learning\n3) Another");
                return false;
            case GOAL_ID:
                int goalId = Integer.parseInt(updateMessageText);
                if (goalId > 2) {
                    goalId = 0;
                }
                Bot.successIndexAndGoalId.put(index, goalId);
                Bot.sendResults();
                waitingType = WaitingType.TIME_SLOT;
                return false;
            default:
                throw new RuntimeException();
        }
    }

}
