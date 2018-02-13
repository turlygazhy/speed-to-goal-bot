package dao.impl;

import entity.Goal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Goal> selectAll() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM GOAL");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            List<Goal> goals = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                goals.add(new Goal(id, name));
            }
            return goals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
