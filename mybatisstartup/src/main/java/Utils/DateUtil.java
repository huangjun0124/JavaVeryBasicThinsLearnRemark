package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    private static SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Date 转换为 yyyy-MM-dd HH:mm:ss 格式
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        return _formatter.format(date);
    }

    /**
     * 返回当前时间戳： yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String  getTimeStamp(){
         return dateToStr(new Date());
    }
}
