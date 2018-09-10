package com.lottery.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colotnet.util.CodingUtil;
import com.colotnet.util.ConfigUtils;
import com.colotnet.util.FileUtil;
import com.colotnet.util.RSAUtil;
import com.colotnet.util.SSLClient;
import com.colotnet.util.SignUtils;
import com.lottery.orm.bo.AccountRecharge;

/**
 *	@author pay
 *	@Time	2017年5月25日上午9:36:41
 * 	TransScanCodePayTest.java描述：扫码支付
 */

@Service
@Transactional
public class TransScanCodePayTest {
	
public synchronized  AccountRecharge getPayTrans(AccountRecharge aRecharge) throws Exception{
	DefaultHttpClient httpClient = new SSLClient();
    HttpPost postMethod = new HttpPost(ConfigUtils.getProperty("trans_url"));
    List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
    nvps.add(new BasicNameValuePair("version", "V1.0"));
    nvps.add(new BasicNameValuePair("transId", "75"));
    /*<productId>产品
      1201:微信公众号
      1202：微信正扫
      1203：微信反扫
      1204：微信APP支付
      1205：微信WAP支付
      1206：支付宝正扫
      1207：支付宝反扫*/
    nvps.add(new BasicNameValuePair("productId", aRecharge.getProductid()));//1202
    nvps.add(new BasicNameValuePair("transAmt", String.valueOf(aRecharge.getTransamt())));//transAmt
    nvps.add(new BasicNameValuePair("commodityName",aRecharge.getAccountid()+",充值金额："+ (aRecharge.getTransamt()/100)+",IP:"+aRecharge.getOrderip()));
    nvps.add(new BasicNameValuePair("notifyUrl", ConfigUtils.getProperty("notifyUrl")));
    nvps.add(new BasicNameValuePair("returnUrl", ConfigUtils.getProperty("returnUrl")));
    nvps.add(new BasicNameValuePair("mchId", ""));
    nvps.add(new BasicNameValuePair("opName", ""));
    nvps.add(new BasicNameValuePair("remark", "充值金额:"+ (aRecharge.getTransamt()/100)+",充值时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    nvps.add(new BasicNameValuePair("extendField", ""));
    //<autoCode>当产品为1203时，可输
    //nvps.add(new BasicNameValuePair("autoCode", ""));
    //<storeId>当产品为1206,1207时，可输
    //nvps.add(new BasicNameValuePair("storeId", ""));
    //<terminaId>当产品为1206,1207时，可输
    //nvps.add(new BasicNameValuePair("terminaId", ""));
    //<openId>当产品为1201时，可输
    //nvps.add(new BasicNameValuePair("openId", ""));
    nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty("merchant_no")));
    nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
    nvps.add(new BasicNameValuePair("orderNo", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
    nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
    nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
    try {
        postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        HttpResponse resp = httpClient.execute(postMethod);
        String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
        System.out.println("返回结果："+str);
        int statusCode = resp.getStatusLine().getStatusCode();
        if (200 == statusCode) {
            boolean signFlag = SignUtils.verferSignData(str);
            if (!signFlag) {
                System.out.println("验签失败");
                return null;
            }
            System.out.println("验签成功");
            JSONObject respJSONObject = JSON.parseObject(str);
            AccountRecharge aRecharge1 = new AccountRecharge();
            aRecharge1.setCommodityname(respJSONObject.getString("commodityName"));
            aRecharge1.setExtendfield(respJSONObject.getString("extendField"));
            aRecharge1.setMerno(respJSONObject.getString("merNo"));
            aRecharge1.setMweburl(respJSONObject.getString("mwebUrl"));
            aRecharge1.setNotifyurl(respJSONObject.getString("notifyUrl"));
            aRecharge1.setOrderdate(respJSONObject.getString("orderDate"));
            aRecharge1.setOrderno(respJSONObject.getString("orderNo"));
            aRecharge1.setProductid(respJSONObject.getString("productId"));
            aRecharge1.setRemark(respJSONObject.getString("remark"));
            aRecharge1.setRequestno(respJSONObject.getString("requestNo"));
            aRecharge1.setRespcode(respJSONObject.getString("respCode"));
            aRecharge1.setRespdesc(respJSONObject.getString("respDesc"));
            aRecharge1.setReturnurl(respJSONObject.getString("returnUrl"));
            aRecharge1.setSignature(respJSONObject.getString("signature"));
            aRecharge1.setTransamt(Integer.valueOf(respJSONObject.getString("transAmt")));
            aRecharge1.setTransid(respJSONObject.getString("transId"));
            aRecharge1.setVersion(respJSONObject.getString("version"));
            aRecharge1.setAccountid(aRecharge.getAccountid());
            aRecharge1.setOrderip(aRecharge.getOrderip());
            aRecharge1.setInputtime(new Date());
            //payno
            return aRecharge1;
        }
        System.out.println("返回错误码:" + statusCode);
    } catch (Exception e) {
        e.printStackTrace();
    }
	return aRecharge;
	
}
	
public synchronized  AccountRecharge getPayWayTrans(AccountRecharge aRecharge) throws Exception{
	
	String trans_url = ConfigUtils.getProperty("trans_url");
	byte[] readFileByte = FileUtil.readFileByte(ConfigUtils.getProperty("private_key_pfx_path"));
	String secretKey = CodingUtil.base64Encode(readFileByte);
	String privateKey = RSAUtil.readFile(ConfigUtils.getProperty("private_key_path"), "UTF-8");
	String privateKeyPwd = ConfigUtils.getProperty("private_key_pwd");
	String merNo = ConfigUtils.getProperty("merchant_no");
	String requestNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	String version = "V1.0";
	String transId = "70";
	String orderDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
    String orderNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String returnUrl = ConfigUtils.getProperty("returnUrl");
    String notifyUrl = ConfigUtils.getProperty("notifyUrl");
    String commodityName = aRecharge.getAccountid()+",充值金额："+ (aRecharge.getTransamt()/100)+",IP:"+aRecharge.getOrderip();
    String remark  = "充值金额:"+ (aRecharge.getTransamt()/100)+",充值时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    String extendField = "";
    String cardType = "0";
    String bankCode = "CMB";
    String signStr = "";
	String signatureStr = "";
	String PRIVATE_KEY_PW_KEY = "1234567890qwertyuiopasdfghjklzxcvbnm";
	String keyType = "file";
	aRecharge.setExtendfield(extendField);
	aRecharge.setMerno(merNo);
	aRecharge.setRequestno(requestNo);
	aRecharge.setVersion(version);
	aRecharge.setTransid(transId);
	aRecharge.setOrderdate(orderDate);
	aRecharge.setOrderno(orderNo);
	aRecharge.setReturnurl(returnUrl);
	aRecharge.setNotifyurl(notifyUrl);
	aRecharge.setCommodityname(commodityName);
	aRecharge.setRemark(remark);
	aRecharge.setMweburl(trans_url);
	    try {
	        StringBuffer signature = new StringBuffer();
	         signature.append("bankCode="+bankCode+"&")
	        .append("cardType="+cardType+"&")
	        .append("commodityName="+commodityName+"&")
	        .append("keyType="+keyType+"&")
	        .append("merNo="+merNo+"&")
	        .append("notifyUrl="+notifyUrl+"&")
	        .append("orderDate="+orderDate+"&")
	        .append("orderNo="+orderNo+"&")
	        .append("productId="+aRecharge.getProductid()+"&")
	        .append("remark="+remark+"&")
	        .append("requestNo="+requestNo+"&")
	        .append("returnUrl="+returnUrl+"&") 
	        .append("transAmt="+aRecharge.getTransamt()+"&")
	        .append("transId="+transId+"&")
	        .append("version="+version+"&");
       		signatureStr = signature.toString().substring(0, signature.length() - 1);
       		signStr = RSAUtil.signByPrivate(signatureStr, privateKey, "UTF-8");   
	      System.out.println("12----"+signatureStr);
	      System.out.println("12-we---"+signStr);
    	} catch (Exception e) {
        e.printStackTrace();
   	 }
     aRecharge.setSignature(signStr);
    return aRecharge;
}

public static void main(String[] args) throws Exception {
	TransScanCodePayTest a = new TransScanCodePayTest();
	AccountRecharge aRecharge = new AccountRecharge();
	aRecharge.setAccountid(1000);
	aRecharge.setTransamt(1000);
	aRecharge.setProductid("1205");
	AccountRecharge c = a.getPayTrans(aRecharge);
	System.out.println("90---"+c.getMweburl());
}
}
