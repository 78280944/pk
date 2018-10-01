package com.jetpay.Interceptor;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class IndexInterceptor implements Interceptor{

	   private Logger logger=Logger.getLogger(IndexInterceptor.class);
	   public static ArrayList<String> excludes = new ArrayList();
	   static{ 
		 //不需要全局拦截器拦截，在这里配置
		 excludes.add("/queryorder");
	   }
	 
	   public void intercept(Invocation inv)
	   {
		 logger.info("已经进入到全局拦截器");  
	     if (excludes.contains(inv.getActionKey())) {
	       inv.invoke();
	       return;
	     }
	     inv.getController().redirect("/");
	     inv.invoke();
	   }

}
