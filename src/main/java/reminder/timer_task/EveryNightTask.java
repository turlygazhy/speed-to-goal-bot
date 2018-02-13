//package reminder.timer_task;
//
//import dao.DaoFactory;
//import entity.Goal;
//import main.Bot;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.telegram.telegrambots.api.methods.send.SendMessage;
//import org.telegram.telegrambots.exceptions.TelegramApiException;
//import reminder.Reminder;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Yerassyl_Turlygazhy on 02-Mar-17.
// */
//public class EveryNightTask extends AbstractTask {
//    private static final Logger logger = LoggerFactory.getLogger(EveryNightTask.class);
//
//    public EveryNightTask(Bot bot, Reminder reminder) {
//        super(bot, reminder);
//    }
//
//    @Override
//    public void run() {
//        logger.info("Start run");
//        reminder.setNextNightTask();
////        List<Goal> goals = DaoFactory.getFactory().getGoalDao().selectAll();
////        for (Goal goal : goals) {
//            int counter = 0;
////            for (Map.Entry<Integer, Integer> entry : Bot.successIndexAndGoalId.entrySet()) {
////                if (entry.getValue() == goal.getId()) {
////                    counter++;
////                }
////            }
//            try {
//                bot.sendMessage(
//                        new SendMessage()
//                                .setChatId(Bot.ADMIN_CHAT_ID)
//                                .setText("For goal '" + goal.getName() + " " + goal.getEmoji() + "': " + counter)
//                );
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
////        Bot.saveAndClearResult();
////        Bot.sendChart();
//    }
//}
