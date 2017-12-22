package exception;

import java.sql.SQLException;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException(Exception e) {
        super(e);
    }

    public CommandNotFoundException() {
    }
}
