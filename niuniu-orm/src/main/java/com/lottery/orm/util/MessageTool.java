package com.lottery.orm.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MessageTool {
	public static final Logger LOG = Logger.getLogger(MessageTool.class);
	public final static String ConfigPropertiesFile = "rest-messages.properties";
	public final static int SuccessCode = 2000;
	public final static int FailCode = 3000;
	public final static int ErrorCode = 4000;
	public final static int InvlidClientCode = 5000;
	public final static int Code_1001 = 1001;
	public final static int Code_1004 = 1004;
	public final static int Code_1005 = 1005;
	public final static int Code_1006 = 1006;
	public final static int Code_1007 = 1007;
	public final static int Code_1008 = 1008;
	public final static int Code_1009 = 1009;
	public final static int Code_1010 = 1010;
	
	public final static int Code_2002 = 2002;
	public final static int Code_2004 = 2004;
	public final static int Code_2005 = 2005;
	public final static int Code_2006 = 2006;
	public final static int Code_2007 = 2007;
	public final static int Code_2008 = 2008;
	
	public final static int Code_3001 = 3001;
	public final static int Code_3002 = 3002;
	public final static int Code_3003 = 3003;
	public final static int Code_3004 = 3004;
	public final static int Code_3005 = 3005;
	public final static int Code_3006 = 3006;
	public final static int Code_3007 = 3007;
	public final static int Code_4000 = 4000;
	public final static int Code_4001 = 4001;

	public final static int Code_5000 = 5000;
	public final static int Code_5001 = 5001;
	public final static int Online = 9001;
	public final static int Share = 9002;
	public final static int Rule = 9003;

	private static Properties prop = new Properties();
	static{
		try {
			InputStream in = MessageTool.class.getClassLoader().getResourceAsStream(ConfigPropertiesFile);
			prop.load(in);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String getMsg(int code){
		String propertyValue = null;
		if(prop.containsKey(String.valueOf(code))){
			propertyValue = prop.getProperty(String.valueOf(code));
			try {
				propertyValue = new String(propertyValue.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//new String(name.getBytes("ISO-8859-1"),"GBK"); 
		}else{
			LOG.error("Message '"+code+"' not exists, please set message property!");
		}
		return propertyValue;
	}
}
