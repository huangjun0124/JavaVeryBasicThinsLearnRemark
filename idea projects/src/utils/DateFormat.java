package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    private static SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String FormatDate(Date date){
        return _formatter.format(date);
    }
}
