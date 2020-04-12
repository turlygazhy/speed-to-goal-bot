package dao.impl;

import entity.Result;
import org.joda.time.LocalDate;
import service.ResultService;
import tool.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class ResultDao {
    public static final int ID_COLUMN = 1;
    public static final int USER_ID_COLUMN = 2;
    public static final int DATE_COLUMN = 3;
    public static final int HOUR_COLUMN = 4;
    public static final int MINUTE_COLUMN = 5;
    public static final int HOUR_ID_COLUMN_ID = 6;
    private final Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Result result) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO RESULT VALUES(default, ?, ?, ?, ?, ?)");
            ps.setLong(1, result.getUserId());
            ps.setString(2, result.getDateAsString());
            ps.setInt(3, result.getHour());
            ps.setInt(4, result.getMinutes());
            ps.setInt(5, result.getHourId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Result> selectForToday(Long chatId) {
        return select(LocalDate.now(), chatId);
    }

    public List<Result> select(LocalDate date, Long chatId) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESULT where user_id=? and date=?");
            ps.setLong(1, chatId);
            ps.setString(2, date.toString(DateUtil.dd_MM_yy));
            ps.execute();

            ResultSet rs = ps.getResultSet();
            List<Result> results = new ArrayList<>();
            while (rs.next()) {
                Result result = new Result(rs.getInt(ID_COLUMN),
                        rs.getLong(USER_ID_COLUMN),
                        DateUtil.getDate(rs.getString(DATE_COLUMN)),
                        rs.getInt(HOUR_COLUMN),
                        rs.getInt(MINUTE_COLUMN)
                );
                result.setHourId(rs.getInt(HOUR_ID_COLUMN_ID));
                results.add(result);
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Long, Integer> select(LocalDate date) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESULT where date=?");
            ps.setString(1, date.toString(DateUtil.dd_MM_yy));
            ps.execute();

            ResultSet rs = ps.getResultSet();
            List<Result> results = new ArrayList<>();
            while (rs.next()) {
                Result result = new Result(rs.getInt(ID_COLUMN),
                        rs.getLong(USER_ID_COLUMN),
                        DateUtil.getDate(rs.getString(DATE_COLUMN)),
                        rs.getInt(HOUR_COLUMN),
                        rs.getInt(MINUTE_COLUMN)
                );
                result.setHourId(rs.getInt(HOUR_ID_COLUMN_ID));
                results.add(result);
            }
            return sortForSavingScore(results);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Long, Integer> sortForSavingScore(List<Result> results) {
        Set<Long> userIds = results.stream()
                .map(Result::getUserId)
                .collect(Collectors.toSet());

        Map<Long, Integer> scoreMap = new HashMap<>();
        userIds.forEach(userId -> {
            List<Result> resultsForUser = results.stream()
                    .filter(result -> result.getUserId() == userId)
                    .collect(Collectors.toList());
            Integer finalScore = ResultService.getFinalScore(resultsForUser);
            scoreMap.put(userId, finalScore);
        });
        return scoreMap;
    }
}
