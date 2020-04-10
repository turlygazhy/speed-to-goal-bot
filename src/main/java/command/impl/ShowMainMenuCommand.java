package command.impl;

import command.Command;

public class ShowMainMenuCommand extends Command {
    @Override
    public boolean execute() {
        sendMessage(4);//main menu
        return true;
    }
}
