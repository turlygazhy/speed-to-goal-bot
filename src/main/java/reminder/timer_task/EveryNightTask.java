package reminder.timer_task;


import command.impl.ClearCommand;
import command.impl.InputCommand;
import main.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reminder.Reminder;

/**
 * Created by Yerassyl_Turlygazhy on 02-Mar-17.
 */
public class EveryNightTask extends AbstractTask {
    private static final Logger logger = LoggerFactory.getLogger(EveryNightTask.class);
    private long groupChatId;

    public EveryNightTask(Bot bot, Reminder reminder) {
        super(bot, reminder);
    }


    @Override
    public void run() {
        logger.info("Start run");
        reminder.setNextNightTask();
        new InputCommand().execute();
        new ClearCommand().execute();
    }
}
