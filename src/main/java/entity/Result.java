package entity;

/**
 * Created by Yerassyl_Turlygazhy on 25-Dec-17.
 */
public class Result {
    private int id;
    private String date;
    private double result;

    public Result(int id, String date, double result) {
        this.id = id;
        this.date = date;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
