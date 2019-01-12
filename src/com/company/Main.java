package com.company;

import DataStructureSamples.DataStructTest;
import DataStructureSamples.EnumWeekDay;

public class Main {

    public static void main(String[] args) {
        UtlTest();
        Integer ii = 1111; // Integer 类有个缓存池
        Integer i2 = 1111;
        Integer i3 = 111;
        Integer i4 = 111;
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
