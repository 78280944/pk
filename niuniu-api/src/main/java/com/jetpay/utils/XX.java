package com.jetpay.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class XX {
	
	public static boolean isEmpty(Object o)
	{
	    if (o == null) {
	      return true;
	    }
	    if ((o instanceof String)) {
	      if (o.toString().trim().equals(""))
	        return true;
	    }
	    else if ((o instanceof List)) {
	      if (((List)o).size() == 0)
	        return true;
	    }
	    else if ((o instanceof Map)) {
	      if (((Map)o).size() == 0)
	        return true;
	    }
	    else if ((o instanceof Set)) {
	      if (((Set)o).size() == 0)
	        return true;
	    }
	    else if ((o instanceof Object[])) {
	      if (((Object[])o).length == 0)
	        return true;
	    }
	    else if ((o instanceof int[])) {
	      if (((int[])o).length == 0)
	        return true;
	    }
	    else if (((o instanceof long[])) && 
	      (((long[])o).length == 0)) {
	      return true;
	    }

	    return false;
	 }

	public static Boolean toBoolean(Object obj, Boolean defaultValue)
	{
	    if (isEmpty(obj)) {
	      return defaultValue;
	    }
	    return toBoolean(obj);
	  }
	
	public static Boolean toBoolean(Object obj)
	{
	    return Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
	 }
	
	 public static boolean isNum(Object obj)
	 {
	     try
	     {
	       Integer.parseInt(obj.toString());
	     } catch (Exception e) {
	       return false;
	     }
	     return true;
	  }
	 
	 public static int toInt(Object obj)
	 {
	     return Integer.parseInt(obj.toString());
	 }

	 public static String format(Object str)
	 {
	     return "'" + str.toString() + "'";
	 }
}
