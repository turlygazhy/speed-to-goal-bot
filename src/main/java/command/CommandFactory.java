package command;

import command.impl.InputCommand;
import command.impl.MyGoalsCommand;
import dao.DaoFactory;
import dao.impl.ButtonDao;
import exception.CommandNotFoundException;
import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class CommandFactory {

    public static Command getCommand(Update update) {
        String updateMessageText = update.getMessage().getText();

        ButtonDao buttonDao = DaoFactory.getFactory().getButtonDao();

        int commandId = buttonDao.getCommandId(updateMessageText);

        switch (commandId) {
            case 1:
                return new MyGoalsCommand();
            case 2:
                return new InputCommand();
            default:
                throw new CommandNotFoundException();
        }
    }
}
