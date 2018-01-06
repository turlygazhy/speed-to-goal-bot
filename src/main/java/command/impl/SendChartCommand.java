package command.impl;

import command.Command;
import main.Bot;

public class SendChartCommand extends Command {
    @Override
    public boolean execute() {
        Bot.sendChart();
        return true;
    }
}
