package dao.impl;

import java.sql.Connection;

public class GoalDao {
    private final Connection connection;

    public GoalDao(Connection connection) {
        this.connection = connection;
    }
}
