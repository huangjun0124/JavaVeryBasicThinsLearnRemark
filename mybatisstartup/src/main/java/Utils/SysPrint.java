package Utils;

public class SysPrint {
    public static void PrintLine(String msg){
        System.out.println(DateUtil.getTimeStamp() + " : " + msg);
    }
}
