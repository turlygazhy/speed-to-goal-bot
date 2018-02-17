package main;

import connection_pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
//import reminder.Reminder;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);


    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        Bot bot = new Bot();
//        new Reminder(bot);
        new TelegramBotsApi().registerBot(bot);
        logger.info("Bot was registered");
    }
}
