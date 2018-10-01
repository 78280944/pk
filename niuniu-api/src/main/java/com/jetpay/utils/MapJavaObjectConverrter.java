package com.jetpay.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.cglib.beans.BeanMap;

/** 
 * @ClassName:     MapJavaObjectConverrter
 * @Description:   TODO(javabean map Converrter)
 * @author:        zdg
 * @date:          2017-7-11 下午08:20:08
 */
public class MapJavaObjectConverrter{
	
	/**
	 * @Title: objectToMapString
	 * @Description: TODO(javaBean2MapString)
	 * @param obj
	 * @param is
	 * @return Map<String,String>
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static Map<String,String> objectToMapString(Object obj,boolean isIgnorBlankOrNull){
		
		if(obj == null){  
            return null;  
        } 
		
		Map<String, String> map = new HashMap<String, String>();  
		try {
		        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
		        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
		        for (PropertyDescriptor property : propertyDescriptors) {    
		            String key = property.getName();    
		            if(key.compareToIgnoreCase("class") == 0) {   
		                continue;  
		            }  
		            Method getter = property.getReadMethod();  
		            Object value = getter!=null ? getter.invoke(obj): null;  
		            
		            if(value!=null){
		            	if(!StringUtils.isBlank(value.toString()))
		            		map.put(key, value.toString());  
		            } 
		        }      
		        System.out.println("map size"+map.size());
		} catch (Exception e){
			// TODO: handle exception
			e.printStackTrace();
		}
        return map;  
	}
	
	/**
	 * @Title: mapStringKeySortToLinkString
	 * @Description: TODO(排序非空参数生成签名原串)
	 * @param params
	 * @param isIgnorBlankOrNull
	 * @return String
	 */
	public static String mapStringKeySortToLinkString(Map<String,String> params,boolean isIgnorBlankOrNull){
		List<String> keys=new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder sb=new StringBuilder();
		int size=keys.size();
		for(int i=0;i<size;i++){
			String key=keys.get(i);
			String obj=params.get(key);
			if(!isIgnorBlankOrNull || !StringUtils.isBlank(obj)){
				sb.append(key).append("=").append(obj==null?"":obj);
				//最后一组参数，结尾不包括'&'
				if(i<size-1){
					sb.append("&");
				}
			}
		}
		System.out.println("mapStringKeySortToLinkString 签名原串     "+sb.toString());
		return sb.toString();
	}

}
