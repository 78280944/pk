package com.lottery.orm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.lottery.orm.bo.ScheduleJob;

public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringUtils.getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		AutowireCapableBeanFactory factory = StaticApplicationContext.applicationContext.getAutowireCapableBeanFactory();
		factory.autowireBean( object );
		
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.debug("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}
	
	public static String getCron(java.util.Date  date){  
        String dateFormat="ss mm HH dd MM ? yyyy";  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        String formatTimeStr = null;  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
    }
	
	public static String getCloseCron(Date  runTime){
		Calendar c = Calendar.getInstance(); ;
		c.setTime(runTime);
		return c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" * * ?";
    } 
	
	/*public static String getOpenCron(Date  runTime){
		Calendar c = Calendar.getInstance();
		c.setTime(runTime);
		return c.get(Calendar.SECOND)+" "+c.get(Calendar.MINUTE)+" "+c.get(Calendar.HOUR_OF_DAY)+" * * ?";
    } */
	public static String getOpenCron(Date  runTime){
		Calendar c = Calendar.getInstance();
		c.setTime(runTime);
		return "0/5 * * * * ?";
    } 
}
