package dao.impl;

import org.joda.time.LocalDate;
import tool.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreDao {
    public static final int SCORE_COLUMN_INDEX = 4;
    private final Connection connection;

    public ScoreDao(Connection connection) {
        this.connection = connection;
    }

    public void sumAndInsert(Long userId, Integer score, LocalDate finishedDay) {
        int scoreForDayBeforeYesterday = select(userId, finishedDay.minusDays(1));
        insert(userId, score + scoreForDayBeforeYesterday, finishedDay);
    }

    public void insert(Long userId, int score, LocalDate date) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO score VALUES(default, ?, ?, ?)");
            ps.setLong(1, userId);
            ps.setString(2, date.toString(DateUtil.dd_MM_yy));
            ps.setInt(3, score);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int select(Long userId, LocalDate date) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM score where date=? and user_id=?");
            ps.setString(1, date.toString(DateUtil.dd_MM_yy));
            ps.setLong(2, userId);
            ps.execute();

            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return rs.getInt(SCORE_COLUMN_INDEX);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
