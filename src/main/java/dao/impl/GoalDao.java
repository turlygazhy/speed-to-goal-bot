package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalDao {
    private final Connection connection;

    public GoalDao(Connection connection) {
        this.connection = connection;
    }

    public String getEmoji(Integer goalId) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT emoji FROM GOAL where id=?");
            ps.setInt(1, goalId);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            return "bad_emoji";
        }
    }
}
