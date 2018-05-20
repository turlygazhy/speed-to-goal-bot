package dao.impl;

import entity.Schedule;

import java.sql.Connection;

public class ScheduleDao {

    private final Connection connection;

    public ScheduleDao(Connection connection) {
        this.connection = connection;
    }

    public Schedule getTodayScheduleForUser(Long chatId) {
        return null; //todo
    }
}
