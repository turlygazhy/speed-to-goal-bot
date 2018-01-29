package reminder.timer_task;

import main.Bot;
import reminder.Reminder;

import java.util.TimerTask;

public class EveryHalfHourTask extends TimerTask {
    private final Bot bot;
    private final Reminder reminder;

    public EveryHalfHourTask(Bot bot, Reminder reminder) {
        this.bot=bot;
        this.reminder=reminder;
    }

    @Override
    public void run() {
        reminder.setNextHalfHourTask();
        bot.sendMessage("new half hours");
    }
}
