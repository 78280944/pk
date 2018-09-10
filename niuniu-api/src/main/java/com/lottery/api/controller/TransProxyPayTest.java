package com.lottery.api.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.colotnet.util.CodingUtil;
import com.colotnet.util.ConfigUtils;
import com.colotnet.util.FileUtil;
import com.colotnet.util.RSAUtil;
import com.colotnet.util.SSLClient;
import com.colotnet.util.SignUtils;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.service.AccountInfoService;
/**
 * 类TransProxyPayTest.java的实现描述：单笔代付
 * @author pay 2016年4月27日 下午3:15:55
 */
@Service
@Transactional
public class TransProxyPayTest {
	public static final Logger LOG = Logger.getLogger(TransProxyPayTest.class);
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
    private AccountRechargeMapper accountRechargeMapper;
	
	@Autowired
    private AccountInfoMapper accountInfoMapper;
	
	
	public synchronized  String getPayTrans(AccountRecharge aRecharge) throws Exception{
    	
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost(ConfigUtils.getProperty("trans_url"));
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V1.0"));
        nvps.add(new BasicNameValuePair("productId", "1043"));// T1到账，固定值：1043 T0到账, 固定值：1041
        nvps.add(new BasicNameValuePair("transId", "67"));
        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty("merchant_no")));
        nvps.add(new BasicNameValuePair("orderDate", aRecharge.getOrderdate()));
        nvps.add(new BasicNameValuePair("orderNo", aRecharge.getOrderno()));
        nvps.add(new BasicNameValuePair("transAmt", String.valueOf((int)(aRecharge.getPayamt()*100))));
        nvps.add(new BasicNameValuePair("isCompany", "0"));// 0-对私,1-对公
        nvps.add(new BasicNameValuePair("phoneNo", aRecharge.getPhoneno()));
        nvps.add(new BasicNameValuePair("customerName", aRecharge.getBankid()));
        nvps.add(new BasicNameValuePair("bankNo", ""));
        nvps.add(new BasicNameValuePair("bankName", aRecharge.getBankname()));
        nvps.add(new BasicNameValuePair("bankLocalName", aRecharge.getBankaddress()));
        nvps.add(new BasicNameValuePair("acctNo", aRecharge.getBankaccount()));
        nvps.add(new BasicNameValuePair("bankLocalProvinceName", aRecharge.getBankloproname()));
        nvps.add(new BasicNameValuePair("bankLocalCityName", aRecharge.getBanklocityname()));
        nvps.add(new BasicNameValuePair("remark", "取现金额:"+aRecharge.getTransamt()+",取现时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        nvps.add(new BasicNameValuePair("extendField", ""));
        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
      
        try {
            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse resp = httpClient.execute(postMethod);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");		
            System.out.println("返回结果："+str+",arid="+aRecharge.getArid());
            LOG.info("取现结果:"+str+",arid = "+aRecharge.getArid());
            int statusCode = resp.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                boolean signFlag = SignUtils.verferSignData(str);
                if (!signFlag) {
                    System.out.println("验签失败");
                    return "false";
                }
                Map mapTypes = JSON.parseObject(str);
                Map<String, Object> data = new HashMap<String, Object>();
                for (Object obj : mapTypes.keySet()){  
                    if((!obj.toString().equals("signature"))){
                    	//System.out.println("通知字段-"+obj+":"+mapTypes.get(obj).toString());
                    	aRecharge.setMerno(mapTypes.get("merNo").toString());
                    	aRecharge.setOrderdate(mapTypes.get("orderDate").toString());
                    	aRecharge.setOrderno(mapTypes.get("orderNo").toString());
                    	if (null == mapTypes.get("payNo"))
                    		aRecharge.setPayno("");
                    	else
                    	    aRecharge.setPayno(mapTypes.get("payNo").toString());
                    	aRecharge.setProductid(mapTypes.get("productId").toString());
                    	aRecharge.setRequestno(mapTypes.get("requestNo").toString());
                    	aRecharge.setRespcode(mapTypes.get("respCode").toString());
                    	aRecharge.setRespdesc(mapTypes.get("respDesc").toString());
                    	aRecharge.setSignature(mapTypes.get("signature").toString());
                    	aRecharge.setTransid(mapTypes.get("transId").toString());
                    	aRecharge.setVersion(mapTypes.get("version").toString());
                    }
                }  
                if (aRecharge.getRespcode().equals("0000")){
                	aRecharge.setOrderstate("01");
                }else if (aRecharge.getRespcode().equals("P000")){
         		   
         	    }else {
 	                	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
 	                	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf((double)(aRecharge.getTransamt()/100))));
 	            		aRecharge.setAccountamount(aInfo.getUsermoney());
 	            		accountInfoMapper.updateByPrimaryKey(aInfo);
 	        	    	if (aRecharge.getFee()>0){
 	        	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
 	        	    		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getFee())));
 	        		    	accountInfoMapper.updateByPrimaryKey(aInfo);
 	        	    	}
 	        	    	aRecharge.setOrderstate("02");
         	    }
                accountRechargeMapper.updateByRechargeCashReady(aRecharge);
                LOG.info("验签成功结果:arid = "+aRecharge.getArid());
                System.out.println("验签成功");
                return "success";
            }
            System.out.println("返回错误码:" + statusCode);
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fasle";
    }
	
}
