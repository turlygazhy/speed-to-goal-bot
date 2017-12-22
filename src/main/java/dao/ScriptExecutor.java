package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by user on 8/12/17.
 */
public class ScriptExecutor {
    private final Connection connection;

    public ScriptExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execute(String script) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(script);
        ps.execute();
    }
}
