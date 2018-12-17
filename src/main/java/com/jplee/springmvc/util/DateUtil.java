package com.jplee.springmvc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static final String TIME_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT_FULL_SIMP = "yyyyMMddHHmmss";
	public static final String TIME_FORMAT_YMD = "yyyy-MM-dd";
	public static final String TIME_FORMAT_YMD_SIMP = "yyyyMMdd";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static int TIME_DAY_MILLISECOND = 86400000;
	
	/**
	* @Title: dateFormat
	* @Description: 将Date类型转String类型
	* @param date
	* @param formatStr
	* @return    参数
	* @return String    返回类型
	* @throws
	 */
	public static String dateFormat(Date date, String formatStr) {
		if(null==date)
			return StringUtils.EMPTY;
		DateFormat myformat = null;
		if (StringUtils.isBlank(formatStr))
			myformat = new SimpleDateFormat(TIME_FORMAT_YMD_SIMP);
		else {
			myformat = new SimpleDateFormat(formatStr);
		}
		return myformat.format(date);
	}
	 /***
     * 将string字符串转化为Date类型的字符串
     * @param dateTimeStr 需要转化的string类型的字符串
     * @param formatStr 转化规则
     * @return 返回转化后的Date类型的时间
     */
    public static Date strToDate(String dateTimeStr, String formatStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /***
     * 将date类型的时间转化为string类型
     * @param date 需要转化的date类型的时间
     * @param formatStr 转化规则
     * @return 返回转化后的string类型的时间
     */
    public static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }
    
    /***
     * 将string字符串转化为Date类型的字符串
     * @param dateTimeStr 需要转化的string类型的时间
     * @return 返回转化后的Date类型的时间
     */
    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(TIME_FORMAT_FULL);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    
    /***
     * 将date类型的时间转化为string类型
     * @param date 需要转化的date类型的时间
     * @return 返回转化后的string类型的时间
     */
    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(TIME_FORMAT_FULL);
    }

    /*
     * 当前时间转换成指定格式的String类型
     */
    public static String newDateString(String format) {
		return new DateTime().toString(format);
	}
 
	/*
	 * 将日期转成指定格式
	 */
	public static Date newDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_YMD_SIMP);
		Date returnDate = null;

		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			returnDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (null == returnDate)
			returnDate = new Date();
		return returnDate;
	}
	
	/**
	 *将String类型的日期转成对应格式的Date类型日期 
	 * 			如newDate("2018-09-12","yyyy-MM-dd")
	 */
	public static Date newDate(String date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date returnDate = null;

		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			returnDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	
	
	/**
     * 获得两个Date型日期之间相差的天数
     * 大日期在前为正
     * @param Date
     *                first, Date second
     * @return int 相差的天数
     */
    public static int getDaysBetweenDates(Date first, Date second) {
    	Long mils = 0L;
    	if(first.getTime() > second.getTime()) {
    		mils = (long)Math.ceil((first.getTime() - second.getTime()) / ((double)TIME_DAY_MILLISECOND));
    	} else if (first.getTime() < second.getTime()) {
    		mils = (long)Math.floor((first.getTime() - second.getTime()) / ((double)TIME_DAY_MILLISECOND));
    	} else {
    		return 0;
    	}
        return mils.intValue();
    }
    /**
     * 获得两个Date型日期之间相差的天数
     *
     * 小日期在前为正
     * @return int 相差的天数
     */
    public static int getDayBetweenDates(Date first, Date second) {
    	DateTime firstDate = new DateTime(first);          
        DateTime secondDate = new DateTime(second);
//    	int days = Days.daysBetween(firstDate, secondDate).getDays();
        Period p = new Period(firstDate,secondDate,PeriodType.days());

        int days = p.getDays();
		return days; 
    }
	
	/**
	 * 获取某年的开始时间</br></br>
	 * 如2015年的开始时间为</br>
	 * 2015-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某年的开始时间
	 */
	public static Date getYearBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某月的开始时间</br></br>
	 * 如2015-04月的开始时间为</br>
	 * 2015-04-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某月的开始时间
	 */
	public static Date getMonthBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某天的开始时间</br></br>
	 * 如2015-04-24 12:00:00的开始时间为</br>
	 * 2015-04-24 00:00:00.000
	 * 
	 * @param date
	 * @return 某天的开始时间
	 */
	public static Date getDayBegin(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某年的结束时间</br></br>
	 * 如2015年的结束时间为</br>
	 * 2016-01-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某年的结束时间
	 */
	public static Date getYearEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getYearBegin(date));
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取某月的结束时间</br></br>
	 * 如2015-04月的结束时间为</br>
	 * 2015-05-01 00:00:00.000
	 * 
	 * @param date
	 * @return 某月的结束时间
	 */
	public static Date getMonthEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getMonthBegin(date));
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取某天的结束时间</br></br>
	 * 如2015-04-24 12:00:00的结束时间为</br>
	 * 2015-04-25 00:00:00.000
	 * 
	 * @param date
	 * @return 某天的结束时间
	 */
	public static Date getDayEnd(Date date) {
		if(date==null)return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDayBegin(date));
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getDayBetweenDates(strToDate("2018-09-12","yyyy-MM-dd"),new Date()));
		System.out.println(getDaysBetweenDates(new Date(),strToDate("2018-09-12","yyyy-MM-dd")));
	}

}

