package com.lottery.api.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.lottery.orm.bo.AccountInfo;

public class ToolsUtil {
	public static Map getFiledName(Object o)  
	{    
		Map sMap = new HashMap();
	    try   
	    {  
		    java.lang.reflect.Field[] fields = o.getClass().getDeclaredFields();  
		    //String[] fieldNames = new String[fields.length];    
			for (int i=0; i < fields.length; i++)  
			{    
			    //fieldNames[i] = fields[i].getName();
			    sMap.put(fields[i].getName(), fields[i].getType().toString());
			}    
		    return sMap;  
	    } catch (SecurityException e)   
	    {  
			e.printStackTrace();  
			System.out.println(e.toString());  
	    }  
	    return null;  
	}    
	
	public static Date getCurrentTime(){
		  Date currentTime = new Date();
		  System.out.println("0--"+currentTime);
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateString = formatter.format(currentTime);
		  System.out.println("01--"+dateString);
		  ParsePosition pos = new ParsePosition(0);
		  Date currentTime_2 = formatter.parse(dateString, pos);
		  System.out.println("01dd--"+currentTime_2);
		  return currentTime_2;
	} 
	
	public static String getCurrentSerial(){
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	}
	
	
	//判断空或者trim()
	public static boolean isEmptyTrim(String parameter){
		if (null == parameter||"".equals(parameter)||"".equals(parameter.trim()))
	        return true;			
		else 
			return false;
	}
	
	//14个英文或者数字组合
	public static boolean validatName(String parameter){
		String strRerex = "^[A-Za-z0-9\\-]{0,14}$";
		Pattern pattern = Pattern.compile(strRerex);
		Matcher matcher = pattern.matcher(parameter);
		return !matcher.find();
	}
	
	//6-14位数字、字母、符号组合
	public static boolean validateSignName(String parameter){
		String strRerex = "^[\\x21-\\x7E]{6,14}$";
		Pattern pattern = Pattern.compile(strRerex);
		Matcher matcher = pattern.matcher(parameter);
		return !matcher.find();
	}
	
	//点数限额只能为数字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*.[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	//洗码比只能为数字且小于1
	public static boolean isNumericRatio(String str) {
		Pattern pattern = Pattern.compile("^(1(.0*)?)|((2*\\d)(.\\d0*)?)$");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	//在范围之类
	public static boolean betweenRange(String parameter)
	{
	    if (parameter.equals("0")){
	    	return false;
	    }else if (parameter.equals("1")){
	    	return false;
	    }else
		    return true;
	}
	
	//层级判断
	public static String decideLevel(String parameter){
		String sLevel = "";
		if (parameter.equals("0"))
			sLevel = "1";
		else if (parameter.equals("1"))
			sLevel = "2";
		else if (parameter.equals("2"))
			sLevel = "3";
		return sLevel;
	}
	
	//查询范围判断
	public static boolean checkQuery(String parameter){
		String objectString = "M1,M2";
		String[] temp = objectString.split(",");
		String[] para = parameter.split(",");
		int count = 0;
		for (int i=0;i<para.length;i++){
			for (int j=0;j<temp.length;j++){
				if (para[i].equals(temp[j])){
					count++;
					break;
				}
			}
		}
		if (count<para.length){
			return true;
		}
		return false;
	}
	
	public static boolean checkUpdatePeriod() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		//if (dayOfWeek == 2 && hourOfDay >= 3 && hourOfDay <= 9) {
		if (hourOfDay >= 3 && hourOfDay <= 9) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkTime() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		if (hourOfDay >= 6 && hourOfDay <= 22) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void main(String args[]){
		System.out.println(checkTime());
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		int minOfDay = c.get(Calendar.MINUTE);
		System.out.println(hourOfDay+".."+minOfDay);
		if (hourOfDay == 9 && (minOfDay >=12 && minOfDay<50)) 
			System.out.println("2,"+checkTime());
		/* System.out.println(DigestUtils.md5Hex("123"));
		  
		AccountInfo ai = new AccountInfo();
		ToolsUtil a = new ToolsUtil();
		System.out.println("7--4-------"+a.checkQuery("Y1,Y2,9"));
		System.out.println("7---------"+a.validatName("111111%^"));
		Map map = a.getFiledName(ai);
		Iterator entries = map.entrySet().iterator();  */
		/*  
		while (entries.hasNext()) {  
		  
		    Map.Entry entry = (Map.Entry) entries.next();  
		  
		    String key = (String)entry.getKey();  
		  
		    String value = (String)entry.getValue();  
		  
		    System.out.println("Key = " + key + ", Value = " + value);  
		  
		}  
		*/
	}
}
