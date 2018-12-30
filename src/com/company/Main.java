package com.company;

import DataStructureSamples.DataStructTest;
import DataStructureSamples.EnumWeekDay;

public class Main {

    public static void main(String[] args) {
        UtlTest();
    }

    private static void UtlTest()
    {
        DataStructTest ds = new DataStructTest();
        ds.UrlConnectTest();
    }
    private static void EnumTest(){
        EnumWeekDay day = EnumWeekDay.SATURDAY;
        System.out.println(day);
        System.out.println(day.toString());
        int dayIndex = day.getIndex();
    }

}
