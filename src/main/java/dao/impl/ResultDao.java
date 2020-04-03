package dao.impl;

import entity.Result;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import tool.DateUtil;

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
    public static final int ID_COLUMN = 1;
    public static final int USER_ID_COLUMN = 2;
    public static final int DATE_COLUMN = 3;
    public static final int HOUR_COLUMN = 4;
    public static final int MINUTE_COLUMN = 5;
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

    public List<Result> selectForToday(Long chatId) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESULT where user_id=? and date=?");
            ps.setLong(1, chatId);
            ps.setString(2, DateTime.now().toString(DateUtil.dd_MM_yy));
            ps.execute();

            ResultSet rs = ps.getResultSet();
            List<Result> results = new ArrayList<>();
            while (rs.next()){
                results.add(new Result(rs.getInt(ID_COLUMN),
                        rs.getLong(USER_ID_COLUMN),
                        DateUtil.getDate(rs.getString(DATE_COLUMN)),
                        rs.getInt(HOUR_COLUMN),
                        rs.getInt(MINUTE_COLUMN)
                ));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
