import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yerassyl_Turlygazhy on 20-Dec-17.
 */
public class Poligon {
    public static void main(String[] args) {
        // get System properties :
        java.util.Properties properties = System.getProperties();

        // to print all the keys in the properties map <for testing>
        properties.list(System.out);

        // get Operating System home directory
        String home = properties.get("user.home").toString();

        // get Operating System separator
        String separator = properties.get("file.separator").toString();

        // your directory name
        String directoryName = "karthik";

        // your file name
        String fileName = "newfile.txt";


        // create your directory Object (wont harm if it is already there ...
        // just an additional object on the heap that will cost you some bytes
        File dir = new File(home+separator+directoryName);

        //  create a new directory, will do nothing if directory exists
        dir.mkdir();

        // create your file Object
        File file = new File(dir,fileName);

        // the rest of your code
        try {
            if (file.createNewFile()) {
                System.out.println("created new fle");
            } else {
                System.out.println("could not create a new file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
