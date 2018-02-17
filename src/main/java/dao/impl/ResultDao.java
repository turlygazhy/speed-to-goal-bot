package dao.impl;

import entity.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class ResultDao {
    private final Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Result result) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO RESULT VALUES(default, ?, ?)");
        ps.setInt(1, result.getGoalId());
        ps.setInt(2, result.getMinutes());
        ps.execute();
    }
}
