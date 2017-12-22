package reminder.timer_task;

import main.Bot;
import reminder.Reminder;

import java.util.TimerTask;

/**
 * Created by Yerassyl_Turlygazhy on 02-Mar-17.
 */
public abstract class AbstractTask extends TimerTask {
    protected Bot bot;
    protected Reminder reminder;

    public AbstractTask(Bot bot, Reminder reminder) {
        this.bot = bot;
        this.reminder = reminder;
    }
}
