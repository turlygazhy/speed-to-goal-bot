//package com.turlygazhy.dao;
//
//import com.turlygazhy.Bot;
//import com.turlygazhy.entity.WaitingType;
//import org.telegram.telegrambots.api.objects.Update;
//import org.telegram.telegrambots.exceptions.TelegramApiException;
//
//import java.sql.SQLException;
//
///**
// * Created by user on 8/12/17.
// */
//public class ExecuteScriptCommand extends com.turlygazhy.command.Command {
//    private WaitingType wt;
//    private ScriptExecutor scriptExecutor = factory.getScriptExecutor();
//
//
//    @Override
//    public boolean execute(Update update, Bot bot) throws SQLException, TelegramApiException {
//        if (wt == null) {
//            sendMessage("send script", update.getMessage().getChatId(), bot);
//            wt = WaitingType.SCRIPT;
//            return false;
//        }
//        switch (wt) {
//            case SCRIPT:
//                scriptExecutor.execute(update.getMessage().getText());
//                sendMessage("done", update.getMessage().getChatId(), bot);
//                return true;
//        }
//        return false;
//    }
//}
