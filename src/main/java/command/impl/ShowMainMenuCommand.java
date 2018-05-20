package command.impl;

import command.Command;

public class ShowMainMenuCommand extends Command {

    @Override
    public boolean execute() {
        sendMessage(5);//main menu
        return true;
    }
}
