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
            sendResults();
            waitingType = WaitingType.TIME_SLOT;
            return false;
        }
        switch (waitingType) {
            case TIME_SLOT:
                int index = Integer.parseInt(updateMessageText) - 1;
                Bot.successIndexes.add(index);
                sendResults();
                return false;
            default:
                throw new RuntimeException();
        }
    }

    private void sendResults() {
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
            if (Bot.successIndexes.contains(i)) {
                str += messageDao.getMessageText(1);//green emoji
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
        sendMessage(s.toString());
    }


}
