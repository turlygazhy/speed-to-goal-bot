package entity;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import tool.DateUtil;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class Result {
    private int id;
    private long userId;
    private LocalDate date;
    private int hour;
    private int minutes;

    public Result(long userId) {
        date = LocalDate.now();
        hour = DateTime.now().getHourOfDay();
        this.userId = userId;
    }

    public Result(int id, long userId, LocalDate date, int hour, int minutes) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDateAsString() {
        return date.toString(DateUtil.dd_mm_yy);
    }
}
