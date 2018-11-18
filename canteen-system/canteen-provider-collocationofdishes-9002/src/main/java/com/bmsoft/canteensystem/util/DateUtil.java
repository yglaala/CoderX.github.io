package com.bmsoft.canteensystem.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author liugaoyang
 * @Date 2018/10/19 16:53
 */
public class DateUtil {
    public static Date getDate(Date date,String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }
}
