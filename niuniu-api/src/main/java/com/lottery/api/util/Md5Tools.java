package com.lottery.api.util;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;

public class Md5Tools {
	public static  String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
        	//与口袋支付编码一致
            byte[] btInput = s.getBytes(DataHelper.UTF8Encode);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public static Map checkPostKey(String str,String key){
		Map<String,String> map = new HashMap();
		StringBuffer buf = new StringBuffer();
    	int i = str.indexOf("<ret_code>")+10;
    	int j = str.indexOf("</ret_code>");
    	String ret_code = str.substring(i, j);
    	i = str.indexOf("<ret_msg>")+9;
    	j = str.indexOf("</ret_msg>");
    	String ret_msg = str.substring(i,j);
    	if (!(ret_code.equals("0000"))){
    		map.put("signFlag", "false");
    		map.put("ret_code", ret_code);
    		map.put("ret_msg", ret_msg);
    		return map;
    	}
    	map.put("ret_code", ret_code);

    	map.put("ret_code", ret_code);
    	i = str.indexOf("<agent_id>")+10;
    	j = str.indexOf("</agent_id>");
    	String agent_id = str.substring(i,j);
    	map.put("agent_id", agent_id);
    	i = str.indexOf("<hy_bill_no>")+12;
    	j = str.indexOf("</hy_bill_no>");
    	String hy_bill_no = str.substring(i,j);
    	map.put("hy_bill_no", hy_bill_no);
    	i = str.indexOf("<batch_no>")+10;
    	j = str.indexOf("</batch_no>");
    	String batch_no = str.substring(i,j);
    	map.put("batch_no", batch_no);
    	i = str.indexOf("<batch_amt>")+11;
    	j = str.indexOf("</batch_amt>");
    	String batch_amt = str.substring(i,j);
    	map.put("batch_amt", batch_amt);
    	i = str.indexOf("<batch_num>")+11;
    	j = str.indexOf("</batch_num>");
    	String batch_num = str.substring(i,j);
     	map.put("batch_num", batch_num);
    	i = str.indexOf("<detail_data>")+13;
    	j = str.indexOf("</detail_data>");
    	String detail_data = str.substring(i,j);
    	map.put("detail_data", detail_data);
    	i = str.indexOf("<ext_param1>")+12;
    	j = str.indexOf("</ext_param1>");
    	String ext_param1 = str.substring(i,j);
    	map.put("ext_param1", ext_param1);
    	i = str.indexOf("<sign>")+6;
    	j = str.indexOf("</sign>");
    	String sign = str.substring(i,j);
    	map.put("sign", sign);
    	buf.append("agent_id="+agent_id+"&").append("batch_amt="+batch_amt+"&").append("batch_no="+batch_no+"&")
    	.append("batch_num="+batch_num+"&").append("detail_data="+detail_data+"&")
    	.append("ext_param1="+ext_param1+"&").append("hy_bill_no="+hy_bill_no+"&").append("key="+key+"&")
    	.append("ret_code="+ret_code+"&").append("ret_msg="+ret_msg);
       System.out.println("he-"+buf.toString());
        String signatureStr = Md5Tools.MD5(buf.toString().toLowerCase()).toLowerCase();
        System.out.println("91----"+signatureStr+".."+sign);
    	if (signatureStr.equals(sign))
    		map.put("signFlag", "true");
    	else 
    		map.put("signFlag", "false");
        return map;
    }
}

