package com.bmsoft.canteensystem.util;

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

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
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

	public static boolean compareToNewDate(String date){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sdf.format(new Date());
		System.out.println(newDate);
		if(newDate.compareTo(date) > 0){
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

    public static String getLastDayOfMonth() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime())+" 23:59:59";

    }

    public static String getFirstDayOfMonth() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return format.format(cal_1.getTime())+" 00:00:00";
    }

    public static String getCurrentMonth() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        return format.format(cal_1.getTime());
    }

    public static String getPerTime(int time , String targetTime) {

	    time *= 60 * 60 * 1000;
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date date = null;
        try {
            date = format.parse(targetTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return format.format(new Date(date.getTime() -(long)time));

    }

    public static String getPerDate(int time , String targetTime) {

        time *= 60 * 60 * 1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(targetTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return format1.format(new Date(date.getTime() -(long)time));

    }
   public static void main(String[] args) {
       //System.out.println(getPerDate(2,"2018-10-28 1:10:25"));
       System.out.println(getPerTime(2,"13:10:25"));
   }
}
