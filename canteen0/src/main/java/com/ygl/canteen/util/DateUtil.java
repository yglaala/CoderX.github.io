package com.ygl.canteen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	* @return
	*/
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
	/**
	* @return
	*/
	public static String getDateStr() { 
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		return sdf.format(date);
	}
	/**
	* @return
	*/
	public static String getTimeStr() { 
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss"); 
		return sdf.format(date);
	}

	public static String getDate() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String dateToWeek(String datetime) {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance(); // 获得一个日历
		Date datet = null;
		try {
			datet = f.parse(datetime.substring(0,10));
			cal.setTime(datet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0)
			w = 0;
		return weekDays[w];

	}

	public static boolean compareToCurrentDate(String date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(new Date());
        System.out.println(date1);
        if(date1.compareTo(date) > 0){
            return true;
        }
        return false;

    }

    public static String[] getDates(int day){

	    String []dates = new String[day];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        for(int i = 0 ; i < day ; i++){
           calendar.add(Calendar.DATE,1);
           Calendar ca = calendar;
           dates[i] = sdf.format(ca.getTime());
        }
        /*System.out.println(dates);*/
	    return dates;
    }
}
