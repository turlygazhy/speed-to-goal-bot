package command.impl;

import command.Command;
import entity.WaitingType;

public class InsertMinutesCommand extends Command {
    @Override
    public boolean execute() {
        if(waitingType==null){
            sendMessage(1);//send minutes
            waitingType = WaitingType.MINUTES;
            return false;
        }
        switch (waitingType){
            case MINUTES:
                // TODO: 29.03.20  
        }
        return false;
    }
}
