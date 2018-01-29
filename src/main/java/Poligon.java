import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class Poligon {
    public static void main(String[] args) {
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
        System.out.println(date);
    }
}
