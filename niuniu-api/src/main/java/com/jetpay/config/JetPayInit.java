package com.jetpay.config;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.jetpay.utils.FileUtil;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;


/**
 * @描述：加载默认配置
 * @作者： zdg
 * @功能: 资源文件下的所有的配置文件
 */
public class JetPayInit {
	
  /**
   * @描述：初始化配置文件
   */
   public static void initConfig(){
	   
	    String resPath = PathKit.getRootClassPath() + File.separator;
	    System.out.println("项目初始化的项目路径:):"+resPath);
	    boolean flag = loadConfig(resPath + "default");
	    if(flag){
	      System.out.println("默认配置加载成功:(resources/default)\n");
	    }
	    flag = loadConfig(resPath + "dev");
	    if (flag)
	      System.out.println("本地配置覆盖成功:(resources/dev)\n");
   }
   
   /**
    * @描述：加载所有.config后缀的文件
    * @param path 加载默认配置文件路径
    * @return boolean 是否加载成功
   */
   public static boolean loadConfig(String path){
	    if(!FileUtil.isDir(path)){
	      return false;
	    }
	    File[] files = FileUtil.getFiles(path);
	    for (File file : files)
	      if (file.getName().endsWith(".config"))
	      {
	        Properties properties = FileUtil.getProp(file);
	        Set keySet = properties.keySet();
	        for(Iterator localIterator = keySet.iterator(); localIterator.hasNext(); ){ Object ks = localIterator.next();
	          String key = ks.toString();
	          JetPayConfig.props.put(key, properties.getProperty(key));
	        }
	        System.out.println(file.getName());
	      }
	      return true;
   }
  
}
