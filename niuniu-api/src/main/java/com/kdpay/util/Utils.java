package com.kdpay.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	public static String readProp(String key) {
		InputStream in = Utils.class.getClassLoader().getResourceAsStream("param.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
