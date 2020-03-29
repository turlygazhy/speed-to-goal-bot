package dao.impl;

import entity.Result;
import tool.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class ResultDao {
    private final Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Result result) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RESULT VALUES(default, ?, ?, ?, ?)");
            ps.setLong(1, result.getUserId());
            ps.setString(2, result.getDateAsString());
            ps.setInt(3, result.getHour());
            ps.setInt(4, result.getMinutes());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
