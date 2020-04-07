package tool;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.List;
import java.util.Random;

/**
 * Created by user on 3/2/17.
 */
public class Chart {
    //    private static final String PATH = "C:\\bots-data\\charts";
    private static final String PATH = "/home/user/charts";// TODO: 04.04.20 from property
//    private Map<Date, Integer> data = new LinkedHashMap<>();

    public static String getChart(List<Integer> yData, List<Integer> xData) {
        // Create Chart
        XYChart chart = new XYChart(500, 400);
        XYSeries series = chart.addSeries("y(x)", xData, yData);//добавляем данные в x и y оси, указана
        //зависимость y от x
        series.setMarker(SeriesMarkers.CIRCLE);//устанавливается маркер (??)

        String fullPath = PATH + "/chart" + new Random().nextInt(1000) + ".jpg";
        try {
            BitmapEncoder.saveBitmap(chart, fullPath, BitmapEncoder.BitmapFormat.JPG);//создание jpg-картинки диаграммы
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fullPath;//возвращается полный путь к файлу(картинке) диаграммы
    }

//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }

//    public void addPair(Date date, int result) {
//        data.put(date, result);
//    }

//    public String getChart(String goalName, Map<String, List<SavedResult>> userNameAndResults) {
//        String fileName = "chart";
//
//        XYChart chart = new XYChart(500, 400);
//        chart.setTitle(goalName);
//
//        for (Map.Entry<String, List<SavedResult>> entry : userNameAndResults.entrySet()) {
//            List<SavedResult> results = entry.getValue();
//            chart.addSeries(entry.getKey(), getXData(results), getYData(results));
//        }
//
//        String fullPath = PATH + "/" + fileName;
//        try {
//            BitmapEncoder.saveBitmap(chart, fullPath, BitmapEncoder.BitmapFormat.JPG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fullPath;
//    }

//    private List<Double> getYData(List<SavedResult> results) {
//        List<Double> yData = new ArrayList<>();
//        double prev = 0;
//        for (SavedResult result : results) {
//            prev = prev + (double) result.getResult();
//            yData.add(prev);
//        }
//        return yData;
//    }

//    private List<Date> getXData(List<SavedResult> results) {
//        List<Date> xData = new ArrayList<>();
//        for (SavedResult result : results) {
//            xData.add(result.getDate());
//        }
//        return xData;
//    }
//
//    public String getPieChart(String titleText, Map<String, List<SavedResult>> stringListMap) throws NoResultForChartException {
//        PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();
//
//        int resultCount = 0;
//        for (Map.Entry<String, List<SavedResult>> entry : stringListMap.entrySet()) {
//            List<SavedResult> value = entry.getValue();
//            int onePersonRead = 0;
//            for (SavedResult savedResult : value) {
//                onePersonRead = onePersonRead + savedResult.getResult();
//            }
//            chart.addSeries(entry.getKey() + "(" + onePersonRead + ")", onePersonRead);
//            resultCount = resultCount + onePersonRead;
//        }
//
//        if (resultCount == 0) {
//            throw new NoResultForChartException();
//        }
//
//        chart.setTitle(titleText + " " + resultCount);
//
//        chart.getStyler().setLegendVisible(false);
//        chart.getStyler().setAnnotationType(PieStyler.AnnotationType.Label);
//
//        String fullPath = PATH + "/week";
//        try {
//            BitmapEncoder.saveBitmap(chart, fullPath, BitmapEncoder.BitmapFormat.JPG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fullPath;
//    }
}