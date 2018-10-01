 package com.jetpay.utils;
 
 import java.io.PrintStream;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 
 public class DateUtil
 {
   public static final String YYYY = "yyyy";
   public static final String MM = "MM";
   public static final String DD = "dd";
   public static final String YYYY_MM_DD = "yyyy-MM-dd";
   public static final String YYYY_MM = "yyyy-MM";
   public static final String HH_MM_SS = "HH:mm:ss";
   public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
   public static String formatStr_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
   public static String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
   public static String formatStr_yyyyMMddHHmmss1 = "yyyyMMddHHmmss";
   public static String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
   public static String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
   public static String formatStr_yyyyMMdd = "yyyy-MM-dd";
   public static String[] formatStr = { formatStr_yyyyMMddHHmmss, formatStr_yyyyMMddHHmm, formatStr_yyyyMMddHH, formatStr_yyyyMMdd };
 
   public static String format(Date date, String pattern)
   {
     if (date == null) {
       return "";
     }
     return getFormatter(pattern).format(date);
   }
 
   public static String format(Date date)
   {
     if (date == null) {
       return "";
     }
     return getFormatter("yyyy-MM-dd").format(date);
   }
 
   public static Date format(String strDate)
   {
     Date d = null;
     if (strDate == "")
       return null;
     try
     {
       d = getFormatter("yyyy-MM-dd").parse(strDate);
     } catch (ParseException pex) {
       return null;
     }
     return d;
   }
 
   public static Date format(String strDate, String f)
   {
     Date d = null;
     if (strDate == "")
       return null;
     try
     {
       d = getFormatter(f).parse(strDate);
     } catch (ParseException pex) {
       return null;
     }
     return d;
   }
 
   public static Date parse(String strDate, String pattern)
     throws ParseException
   {
     try
     {
       return getFormatter(pattern).parse(strDate);
     } catch (ParseException pe) {
       throw new ParseException("Method parse in Class DateUtil err: parse strDate fail.", pe.getErrorOffset());
     }
   }
 
   public static synchronized Date getCurrDate()
   {
     Calendar calendar = Calendar.getInstance();
     return calendar.getTime();
   }
 
   public static String getCurrDateStr()
   {
     return format(getCurrDate(), "yyyy-MM-dd");
   }
 
   public static String getCurrTimeStr()
   {
     return format(getCurrDate(), "HH:mm:ss");
   }
 
   public static String getCurrDateTimeStr()
   {
    return format(getCurrDate(), "yyyy-MM-dd HH:mm:ss");
   }
 
   public static String getYear()
   {
     return format(getCurrDate(), "yyyy");
   }
 
   public static String getMonth()
   {
     return format(getCurrDate(), "MM");
   }
 
   public static String getDay()
   {
     return format(getCurrDate(), "dd");
   }
 
   public static boolean isDate(String strDate, String pattern)
   {
     try
     {
       parse(strDate, pattern);
      return true; } catch (ParseException pe) {
     }
     return false;
   }
 
   public static boolean isYYYY(String strDate)
   {
     try
     {
      parse(strDate, "yyyy");
      return true; } catch (ParseException pe) {
     }
     return false;
   }
 
   public static boolean isYYYY_MM(String strDate)
   {
     try {
      parse(strDate, "yyyy-MM");
       return true; } catch (ParseException pe) {
     }
     return false;
   }
 
   public static boolean isYYYY_MM_DD(String strDate)
   {
     try
     {
      parse(strDate, "yyyy-MM-dd");
      return true; } catch (ParseException pe) {
     }
    return false;
   }
 
   public static boolean isYYYY_MM_DD_HH_MM_SS(String strDate)
   {
     try
     {
       parse(strDate, "yyyy-MM-dd HH:mm:ss");
       return true; } catch (ParseException pe) {
     }
     return false;
   }
 
   public static boolean isHH_MM_SS(String strDate)
   {
     try
     {
       parse(strDate, "HH:mm:ss");
      return true; } catch (ParseException pe) {
     }
     return false;
   }
 
   private static SimpleDateFormat getFormatter(String parttern)
   {
     return new SimpleDateFormat(parttern);
   }
 
   public static String getNextDate(String refenceDate, int intevalDays)
   {
     try
     {
      return getNextDate(parse(refenceDate, "yyyy-MM-dd"), intevalDays); } catch (Exception ee) {
     }
     return "";
   }
 
   public static String getNextDate(Date refenceDate, int intevalDays)
   {
     try
     {
       Calendar calendar = Calendar.getInstance();
      calendar.setTime(refenceDate);
      calendar.set(5, calendar.get(5) + intevalDays);
      return format(calendar.getTime(), "yyyy-MM-dd"); } catch (Exception ee) {
     }
     return "";
   }
 
   public static long getIntevalDays(String startDate, String endDate)
   {
     try {
       return getIntevalDays(parse(startDate, "yyyy-MM-dd"), parse(endDate, "yyyy-MM-dd")); } catch (Exception ee) {
     }
     return 0L;
   }
 
   public static long getIntevalDays(Date startDate, Date endDate)
   {
     try {
      Calendar startCalendar = Calendar.getInstance();
       Calendar endCalendar = Calendar.getInstance();
 
       startCalendar.setTime(startDate);
       endCalendar.setTime(endDate);
       long diff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
 
       return diff / 86400000L; } catch (Exception ee) {
     }
     return 0L;
   }
 
   public static long getTodayIntevalDays(String startDate)
   {
     try
     {
      Date currentDate = new Date();
 
      SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
       Date theDate = myFormatter.parse(startDate);
 
       return (currentDate.getTime() - theDate.getTime()) / 86400000L;
     }
     catch (Exception ee) {
     }
     return 0L;
   }
 
   public static Date parseToDate(String dateTimeStr)
   {
    if (dateTimeStr == null)
     return null;
     Date d = null;
     int formatStrLength = formatStr.length;
     for (int i = 0; i < formatStrLength; i++) {
      d = parseToDate2(dateTimeStr, formatStr[i]);
       if (d != null) {
         break;
       }
     }
     return d;
   }
 
   private static Date parseToDate2(String dateTimeStr, String formatString) {
     Date d = null;
     SimpleDateFormat sdf = new SimpleDateFormat(formatString);
     try {
       d = sdf.parse(dateTimeStr);
     }
     catch (ParseException localParseException) {
     }
     return d;
   }
 
   public static String dateTimeToString(Date datetime)
   {
     GregorianCalendar calendar = new GregorianCalendar();
     calendar.setTime(datetime);
     String dateTime = calendar.get(1) + (calendar.get(2) + 1 > 9 ? "" : "0") + (calendar.get(2) + 1) + (
       calendar.get(5) > 9 ? "" : "0") + calendar.get(5) + (calendar.get(11) > 9 ? "" : "0") + 
       calendar.get(11) + (calendar.get(12) > 9 ? "" : "0") + calendar.get(12) + (
      calendar.get(13) > 9 ? "" : "0") + calendar.get(13);
     return dateTime;
   }
 
   public static String getLastDayOfMonth(String year, String month)
     throws ParseException
   {
     String LastDay = "";
     Calendar cal = Calendar.getInstance();
 
     Date date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-14");
     cal.setTime(date);
    int value = cal.getActualMaximum(5);
    cal.set(5, value);
     Date date_ = cal.getTime();
     LastDay = new SimpleDateFormat("yyyy-MM-dd").format(date_);
     return LastDay;
   }
 
   public static void main(String[] args)
   {
     try
     {
       System.out.println("当前日期：" + getCurrDateStr());
       System.out.println("日期格式化：" + format(new Date(), formatStr_yyyyMMddHHmmss1));
       System.out.println("短日期：" + format(new Date()));
      System.out.println("长日期：" + getCurrDateTimeStr());
       System.out.println("日：" + getDay());
       System.out.println("月：" + getMonth());
       System.out.println("年：" + getYear());
       System.out.println("月未最后一天：" + getLastDayOfMonth("2010", "08"));
       System.out.println("相差几天：" + getIntevalDays("2010-08-01", "2010-08-21"));
 
       System.out.println("当前日期后的几天：" + getNextDate("2010-08-01", -3));
       System.out.println("与今天相差几天：" + getTodayIntevalDays("2010-08-01"));
     } catch (Exception e) {
      e.printStackTrace();
     }
   }
 }