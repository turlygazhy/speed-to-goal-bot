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
    public static final int ID_COLUMN_INDEX = 1;
    public static final int DATE_COLUMN_INDEX = 2;
    public static final int RESULT_COLUMN_INDEX = 3;
    private final Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(String day, double hours) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RESULT VALUES(default, ?, ?)");
            ps.setString(1, day);
            ps.setDouble(2, hours);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Result> select(int daysCount) {
        List<Result> results = new ArrayList<>();
        List<Result> clearedResults = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESULT");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                results.add(new Result(rs.getInt(ID_COLUMN_INDEX), rs.getString(DATE_COLUMN_INDEX), rs.getDouble(RESULT_COLUMN_INDEX)));
            }
            int count = results.size();
            if (count < daysCount) {
                return results;
            }
            int requiredIndexesStart = count - daysCount;
            for (int i = requiredIndexesStart; i < count; i++) {
                clearedResults.add(results.get(i));
            }
            return clearedResults;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
