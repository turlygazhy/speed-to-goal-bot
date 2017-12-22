package connection_pool;

/**
 * Created by Yerassyl_Turlygazhy on 12-Dec-17.
 */
public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String s) {
        super(s);
    }
}
