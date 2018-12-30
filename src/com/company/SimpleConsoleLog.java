package com.company;

import java.util.Date;

public class SimpleConsoleLog {
    public static void WriteLine(String message){
        System.out.println(DateFormat.FormatDate(new Date()) + " : " + message);
    }
}
