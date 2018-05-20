package command.impl;

import command.Command;
import entity.Schedule;

public class ShowTimeWindowsCommand extends Command {

    @Override
    public boolean execute() {
        sendResults();
        return true;
    }

    private void sendResults() {

        Schedule schedule = scheduleDao.getTodayScheduleForUser(chatId);
        if (schedule == null) {
            sendMessage(3);
        } else {
            /*todo здесь буду брать актуальное состояние окошек на данный день
             * что уже сделано, что осталось и тд и показывать их*/

            sendMessage("Ваш день:");
        }

    }
}
