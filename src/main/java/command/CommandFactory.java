package command;

import command.impl.*;
import dao.DaoFactory;
import dao.impl.ButtonDao;
import exception.CommandNotFoundException;

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
            case 5:
                return new ShowTimeWindowsCommand();
            case 6:
                return new NewScheduleCommand();
            case 7:
                return new ShowMainMenuCommand();
            default:
                throw new CommandNotFoundException();
        }
    }
}
