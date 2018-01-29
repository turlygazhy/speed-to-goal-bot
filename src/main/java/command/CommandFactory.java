package command;

import command.impl.ClearResultsCommand;
import command.impl.InputCommand;
import command.impl.MyGoalsCommand;
import command.impl.SendChartCommand;
import dao.DaoFactory;
import dao.impl.ButtonDao;
import exception.CommandNotFoundException;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class CommandFactory {

    public static Command getCommand(String updateMessageText) {

        ButtonDao buttonDao = DaoFactory.getFactory().getButtonDao();

        int commandId = buttonDao.getCommandId(updateMessageText);

        switch (commandId) {
            case 1:
                return new MyGoalsCommand();
            case 2:
                return new InputCommand();
            case 3:
                return new ClearResultsCommand();
            case 4:
                return new SendChartCommand();
            default:
                throw new CommandNotFoundException();
        }
    }
}
