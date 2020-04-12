package reminder;

import main.Bot;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reminder.timer_task.EveryNightTask;
import tool.DateUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Yerassyl_Turlygazhy on 21-Dec-17.
 */
public class Reminder {
    private static final Logger logger = LoggerFactory.getLogger(Reminder.class);

    private Bot bot;
    private Timer timer = new Timer(true);

    public Reminder(Bot bot) {
        this.bot = bot;
        setNextNightTask();
    }

    public void setNextNightTask() {
        DateTime nextNight = DateTime.now().plusDays(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(1);
        logger.info("new reminder time: " + nextNight);
        EveryNightTask everyNightTask = new EveryNightTask(bot, this);
//        timer.schedule(everyNightTask, nextNight.toDate());//todo return
        timer.schedule(everyNightTask, DateTime.now().plusSeconds(1).toDate());//todo delete only for test
    }

}
