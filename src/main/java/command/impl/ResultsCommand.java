package command.impl;

import command.Command;
import entity.WaitingType;
import org.joda.time.LocalDate;

public class ResultsCommand extends Command {
    private String week = buttonDao.getButtonText(3);

    @Override
    public boolean execute() {
        if (waitingType == null) {
            sendMessage(2);//choose period
            waitingType = WaitingType.PERIOD;
            return false;
        }
        switch (waitingType) {
            case PERIOD:
                LocalDate startDate = getStartDate();
                showChart(startDate);
                return false;
            default:
                throw new RuntimeException();
        }
    }

    private LocalDate getStartDate() {
        if (updateMessageText.equals(week)) {
            return LocalDate.now().minusDays(7);
        }
        throw new RuntimeException("There is no start date for period " + updateMessageText);
    }
}
