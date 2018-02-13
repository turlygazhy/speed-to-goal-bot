package command.impl;

import command.Command;
import dao.DaoFactory;
import entity.Const;
import entity.Goal;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class MyGoalsCommand extends Command {

    @Override
    public boolean execute() {
        List<Goal> goals = DaoFactory.getFactory().getGoalDao().selectAll();
        String result = "Goals:";
        for (Goal goal : goals) {
            result += "\n" + goal.getId() + " - " + goal.getName();
        }
        sendMessage(result);
        return true;
    }

}
