package command.impl;

import command.Command;
import entity.Result;
import entity.WaitingType;

public class InsertMinutesCommand extends Command {
    private Result result;

    @Override
    public boolean execute() {
        if (waitingType == null) {
            result = new Result(chatId);
            sendMessage(1);//send minutes
            waitingType = WaitingType.MINUTES;
            return false;
        }
        switch (waitingType) {
            case MINUTES:
                result.setMinutes(Integer.parseInt(updateMessageText));
                resultDao.insert(result);
//                showTodaysChart() todo
                return true;
        }
        throw new RuntimeException();
    }
}
