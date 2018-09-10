package com.kdpay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.colotnet.util.ConfigUtils;
import com.colotnet.util.RSAUtil;

public class MD5 {
	    public static String encryption(String plainText) {
	        String re_md5 = new String();
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();
	 
	            int i;
	 
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	 
	            re_md5 = buf.toString();
	 
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return re_md5;
	    }
	  
	    public static boolean checkPostKey(String args){
	        TreeMap<String, String> tempMap = new TreeMap<String, String>();
	        Map mapTypes = JSON.parseObject(args);  
	        String P_PostKey = "";
	        StringBuffer buf = new StringBuffer();
	        for (Object obj : mapTypes.keySet()){  
	            if((obj.toString().equals("P_UserId"))||(obj.toString().equals("P_OrderId"))||(obj.toString().equals("P_CardId"))||(obj.toString().equals("P_CardPass"))
	            		||(obj.toString().equals("P_FaceValue"))||(obj.toString().equals("P_ChannelId"))||(obj.toString().equals("SalfStr"))){
	            	tempMap.put(obj.toString(), mapTypes.get(obj).toString());
	            	//buf.append(obj.toString()).append("|");
	            }
	          
	            if (obj.toString().equals("P_PostKey")){
	            	P_PostKey =mapTypes.get(obj).toString(); 
	            }
	        }  
	        
	        String[] str = {"P_UserId","P_OrderId","P_CardId","P_CardPass","P_FaceValue","P_ChannelId","SalfStr"};
	        for (int i=0;i<str.length;i++){
	        	buf.append(tempMap.get(str[i])).append("|");
	        }
	        System.out.println("90----"+buf.substring(0, buf.length() - 1));
	        String signatureStr = buf.substring(0, buf.length() - 1).toString();
	        
	        signatureStr = encryption(signatureStr);
	        System.out.println("91----"+signatureStr);
	        System.out.println("92----"+P_PostKey);
	        return signatureStr.equals(P_PostKey);
	    }
	
}
