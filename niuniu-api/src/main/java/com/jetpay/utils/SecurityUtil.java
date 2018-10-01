package com.jetpay.utils;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.jetpay.servlet.NetpaymentServlet;

public class SecurityUtil{
	public static Logger log=Logger.getLogger(SecurityUtil.class);
	
	//得到签名
	public static String MD5Hex(String paramsStr){
		log.info(new String("sginms:"+paramsStr));
		return SecurityUtil.MD5Encode(paramsStr.trim());
	}
	
	//进行MD5加密
	public static String MD5Encode(String aData) throws SecurityException {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = bytes2HexString(md.digest(aData.getBytes("UTF-8")));
		} catch (Exception e){
			e.printStackTrace();
			throw new SecurityException("MD5运算失败");
		}
		return resultString;
	}
	
	//装换成16进制
	public static String bytes2HexString(byte[] b){
		String ret = "";
		for (int i = 0; i < b.length; i++){
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1){
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

}
