package tool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Yerassyl_Turlygazhy on 06-Mar-17.
 */
public class DateUtil {
    private static SimpleDateFormat ddMMyy = new SimpleDateFormat("dd.MM.yy");
    private static SimpleDateFormat ddMM = new SimpleDateFormat("dd.MM");

    //получение ближайшего прошедшего понедельника
    public static Date getThisMonday() {
        Date date = new Date();
        while (!date.toString().contains("Mon")) {
            date.setDate(date.getDate() - 1);
        }
        return date;
    }

    //получение ближайшего будущего воскресенья
    public static Date getThisSunday() {
        Date date = new Date();
        while (!date.toString().contains("Sun")) {
            date.setDate(date.getDate() + 1);
        }
        return date;
    }

    //получение ближайшего следующего месяца
    public static Date getNextMonth() {
        Date date = new Date();
        date.setDate(date.getDate() + 1);
        while (date.getDate() != 1) {
            date.setDate(date.getDate() + 1);
        }
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }

    public static Date getNextWeek() {
        Date date = new Date();
        date.setDate(date.getDate() + 1);
        while (!date.toString().contains("Mon")) {
            date.setDate(date.getDate() + 1);
        }
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static List<String> getLastWeek() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(1);
        List<String> d = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            d.add(localDate.format(formatter));
            localDate = localDate.minusDays(1);
        }
        return d;
    }

    public static Date getNextNight() {
        Date date = new Date();
        date.setDate(date.getDate() + 1);//
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }

    public static boolean checkHour(int hour) {
        Date date = new Date();
        return date.getHours() == hour;
    }

    public static Date getHour(int hour) {
        Date date = new Date();
        if (date.getHours() >= hour) {
            date.setDate(date.getDate() + 1);//
        }
        date.setHours(hour);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }

    public static boolean isNewWeek() {
        Date date = new Date();
        return date.toString().contains("Mon");
    }

    public static boolean isNewMonth() {
        return new Date().getDate() == 1;
    }

    public static String getPastDay() {
        Date date = new Date();
        date.setDate(date.getDate() - 1);
        return ddMM.format(date);
    }

    public static List<String> getLastMonthSundaysListAsString() {
        List<String> result = new ArrayList<>();
        Date date = new Date();
        date.setDate(date.getDate() - 1);
        int month = date.getMonth();
        while (true) {
            if (month > date.getMonth()) {
                break;
            }

            String dateAsString = date.toString();
            if (dateAsString.contains("Sun")) {
                result.add(ddMM.format(date));
            }
            date.setDate(date.getDate() - 1);
        }
        return result;
    }

    public static Date getThisMonthStartDay() {
        Date date = new Date();
        date.setDate(1);
        return date;
    }

    public static Date getLastMonthFirstDay() {
        Date date = new Date();
        date.setMonth(date.getMonth() - 1);
        date.setDate(1);
        return date;
    }

    public static Date getLastMonthLastDay() {
        Date date = new Date();
        int month = date.getMonth();
        while (true) {
            date.setDate(date.getDate() - 1);
            if (month > date.getMonth()) {
                break;
            }
        }
        return date;
    }

    public static Date getLastWeekMonday() {
        Date date = new Date();
        date.setDate(date.getDate() - 1);
        while (!date.toString().contains("Mon")) {
            date.setDate(date.getDate() - 1);
        }
        return date;
    }

    public static Date getLastWeekSunday() {
        Date date = new Date();
        while (!date.toString().contains("Sun")) {
            date.setDate(date.getDate() - 1);
        }
        return date;
    }

    public static String getFormatted(Date date) {
        return ddMM.format(date);
    }

    public static int getWeekDay() {
        Date date = new Date();
        date.setDate(date.getDate() - 1);

        if (date.toString().contains("Mon")) return 1;
        if (date.toString().contains("Tue")) return 2;
        if (date.toString().contains("Wed")) return 3;
        if (date.toString().contains("Thu")) return 4;
        if (date.toString().contains("Fri")) return 5;
        if (date.toString().contains("Sat")) return 6;
        if (date.toString().contains("Sun")) return 7;
        return 0;
    }

    public static Date getThisMonthEndDay() {
//        Date date = new Date();
//        int month = date.getMonth();
//        while (true) {
//            date.setDate(date.getDate() + 1);
//            if (month < date.getMonth()) {
//                break;
//            }
//        }
//        date.setDate(date.getDate() - 1);
//        return date;
        LocalDate localDate = LocalDate.now();
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());
        return Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void getWeekOfMonth() {
        Date date = new Date();
    }

    public static int getWeeksCountInThisMonthBeforeThisWeek() {
        int result = 0;
        Date date = new Date();
        date.setDate(date.getDate() - 1);
        int month = date.getMonth();
        while (true) {
            if (date.toString().contains("Sun")) {
                result++;
            }
            date.setDate(date.getDate() - 1);
            if (month > date.getMonth()) {
                break;
            }
        }
        return result;
    }

    public static int getWeeksCountInThisMonth() {
//        int result = 1;
//        Date date = new Date();
//        date.setDate(2);
//        int month = date.getMonth();
//        while (true) {
//            if (date.toString().contains("Mon")) {
//                result++;
//            }
//            date.setDate(date.getDate() + 1);
//            if (month < date.getMonth()) {
//                break;
//            }
//        }
//        return result;
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    public static Date getDate(String date) {
        try {
            return ddMM.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date nextHaltHour() {
        Date date = new Date();
        int minutes = date.getMinutes();
        if (minutes < 30) {
            date.setMinutes(30);
            System.out.println(date);
        } else {
            date.setMinutes(0);
            date.setHours(date.getHours() + 1);
        }
        date.setSeconds(1);
        return date;
    }
}
