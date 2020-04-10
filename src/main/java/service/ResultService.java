package service;

import dao.DaoFactory;
import dao.impl.ResultDao;
import entity.Result;
import org.joda.time.LocalDate;
import tool.Chart;

import java.util.ArrayList;
import java.util.List;

public class ResultService {
    private DaoFactory factory = DaoFactory.getFactory();
    private ResultDao resultDao = factory.getResultDao();
    private Long chatId;

    public ResultService(Long chatId) {
        this.chatId = chatId;
    }

    public String getChartForToday() {
        List<Result> results = resultDao.selectForToday(chatId);

        List<Integer> yData = new ArrayList<>();
        List<Integer> xData = new ArrayList<>();

        int score1 = getScore(results, 1);
        yData.add(score1);
        xData.add(1);

        int total2 = 0;
        if (contains(results, 2)) {
            int score2 = getScore(results, 2);
            total2 = score1 + score2;
            yData.add(total2);
            xData.add(2);
        }

        int total3 = 0;
        if (contains(results, 3)) {
            int score3 = getScore(results, 3);
            total3 = total2 + score3;
            yData.add(total3);
            xData.add(3);
        }

        int total4 = 0;
        if (contains(results, 4)) {
            int score4 = getScore(results, 4);
            total4 = total3 + score4;
            yData.add(total4);
            xData.add(4);
        }

        int total5 = 0;
        if (contains(results, 5)) {
            int score5 = getScore(results, 5);
            total5 = total4 + score5;
            yData.add(total5);
            xData.add(5);
        }

        if (contains(results, 6)) {// TODO: 08.04.20 если проверка на предыдущий день то показывать
            int score6 = getScore(results, 6);
            int total6 = total5 + score6;
            yData.add(total6);
            xData.add(6);
        }

        return Chart.getChart(yData, xData);
    }

    private boolean contains(List<Result> results, int hourId) {
        return results.stream()
                .anyMatch(result -> result.getHourId() == hourId);
    }

    private String getXData(List<Result> results, int hourId) {// TODO: 08.04.20 make x data String i.e. 1 (11)
        Result resultForHourId = results.stream()
                .filter(result -> result.getHourId() == hourId)
                .findFirst()
                .orElse(null);
        if (resultForHourId != null) {
            return resultForHourId.getHourId() + " (" + resultForHourId.getHour() + ")";
        } else {
            return String.valueOf(hourId);
        }
    }

    private int getScore(List<Result> results, int hourId) {
        int minutes = results.stream()
                .filter(result -> result.getHourId() == hourId)
                .mapToInt(Result::getMinutes)
                .reduce(0, Integer::sum);
        if (minutes < 10) {
            if (hourId == 0) {
                return 0;
            }
            return -10;
        }
        if (minutes < 25) {
            return 0;
        }
        if (minutes < 50) {
            return 10;
        }
        if (hourId == 6) {
            return minutes / 25 * 10;
        }
        return 30;
    }

    public int getHourId(int hour) {
        List<Result> results = resultDao.selectForToday(chatId);
        //if for today were no results
        if (results.isEmpty()) {
            return 1;
        }

        //if for this hour we have result
        Result resultForThisHour = results.stream()
                .filter(result -> result.getHour() == hour)
                .findFirst()
                .orElse(null);
        if (resultForThisHour != null) {
            return resultForThisHour.getHourId();
        }

        int maxHourId = results.stream()
                .mapToInt(Result::getHourId)
                .max()
                .orElseThrow(() -> new RuntimeException("Could not find max hour id from results"));
        if (maxHourId >= 5) {
            return 6;
        } else {
            return maxHourId + 1;
        }
    }

    public String getChartIncludingToday(LocalDate startDate) {
        return null;// TODO: 10.04.20
    }
}
