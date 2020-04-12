package reminder.timer_task;

import main.Bot;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reminder.Reminder;

import java.util.Map;

/**
 * Created by Yerassyl_Turlygazhy on 02-Mar-17.
 */
public class EveryNightTask extends AbstractTask {
    private static final Logger logger = LoggerFactory.getLogger(EveryNightTask.class);

    public EveryNightTask(Bot bot, Reminder reminder) {
        super(bot, reminder);
    }

    @Override
    public void run() {
        logger.info("Start run");
        reminder.setNextNightTask();
        LocalDate finishedDay = LocalDate.now().minusDays(1);
        Map<Long, Integer> finishedDayScores = resultDao.select(finishedDay);
        for (Map.Entry<Long, Integer> entry : finishedDayScores.entrySet()) {
            scoreDao.sumAndInsert(entry.getKey(), entry.getValue(), finishedDay);
        }
        // TODO: 13.04.20 need to send chart here?
    }
}
