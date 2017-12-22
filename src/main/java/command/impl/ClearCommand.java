package command.impl;

import command.Command;
import main.Bot;

import java.util.HashSet;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class ClearCommand extends Command {
    @Override
    public boolean execute() {
        Bot.successIndexes = new HashSet<>();
        return true;
    }
}
