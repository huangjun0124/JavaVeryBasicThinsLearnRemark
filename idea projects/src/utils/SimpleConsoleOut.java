package utils;

import java.util.Date;

public class SimpleConsoleOut {
    public static void WriteLine(String message){
        System.out.println(DateFormat.FormatDate(new Date()) + " : " + message);
    }
}
