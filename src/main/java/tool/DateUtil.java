package tool;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class DateUtil {
    public static final String dd_MM_yy = "dd.MM.yy";

    public static LocalDate getDate(String dateAsString) {
        return LocalDate.parse(dateAsString, DateTimeFormat.forPattern(dd_MM_yy));
    }
}
