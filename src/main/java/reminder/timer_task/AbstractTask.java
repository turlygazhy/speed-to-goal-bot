package reminder.timer_task;

import dao.DaoFactory;
import dao.impl.ResultDao;
import dao.impl.ScoreDao;
import main.Bot;
import reminder.Reminder;

import java.util.TimerTask;

/**
 * Created by Yerassyl_Turlygazhy on 02-Mar-17.
 */
public abstract class AbstractTask extends TimerTask {
    protected Bot bot;
    protected Reminder reminder;
    protected DaoFactory factory = DaoFactory.getFactory();
    protected ResultDao resultDao = factory.getResultDao();
    protected ScoreDao scoreDao = factory.getScoreDao();

    public AbstractTask(Bot bot, Reminder reminder) {
        this.bot = bot;
        this.reminder = reminder;
    }
}
