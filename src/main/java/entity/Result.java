package entity;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class Result {
    private int id;
    private int minutes;
    private int goalId;

    public Result(int id, int minutes, int goalId) {
        this.id = id;
        this.minutes = minutes;
        this.goalId = goalId;
    }

    public Result(int minutes, int goalId) {
        this.minutes = minutes;
        this.goalId = goalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }
}
