package command.impl;

import command.Command;
import entity.Event;
import entity.WaitingType;

import java.time.format.DateTimeFormatter;

public class NewScheduleCommand extends Command {

    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("HH:mm");//todo нужно использовать jodatime
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH mm");
    DateTimeFormatter format3 = DateTimeFormatter.ofPattern("HHmm"); //todo сможет ли распарсить 700?

    private Event event;

    @Override
    public boolean execute() {
        if (waitingType == null) {
            sendMessage(4);//input event name
            waitingType = WaitingType.EVENT_NAME;
            return false;
        }
        switch (waitingType) {
            case EVENT_NAME:
                event = new Event(updateMessageText);
                sendMessage(6);//input start time
                waitingType = WaitingType.START_TIME;
                return false;

            case START_TIME:
                parseTime(updateMessageText);
        }
        return false;
    }

    private void parseTime(String time) {
        if (time.contains(":")) {
            return format1.
        }
    }
}
