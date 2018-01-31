package reminder.timer_task;

import main.Bot;
import reminder.Reminder;

import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

public class EveryHalfHourTask extends TimerTask {
    private final Bot bot;
    private final Reminder reminder;

    public EveryHalfHourTask(Bot bot, Reminder reminder) {
        this.bot = bot;
        this.reminder = reminder;
    }

    @Override
    public void run() {
        reminder.setNextHalfHourTask();
        bot.sendMessage("What will you do next half hour?");
        Bot.waitingAnswer = true;
        Date date = new Date();
        if (date.getHours() == 0 && date.getMinutes() == 0) {
            Bot.sendAnswers();
        }
        Bot.answers = new HashMap<>();
    }
}
