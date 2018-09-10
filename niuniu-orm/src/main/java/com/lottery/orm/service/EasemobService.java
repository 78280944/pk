package com.lottery.orm.service;

import com.lottery.orm.util.ConfigUtils;
import com.lottery.orm.util.HttpclientTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EasemobService {

    public final Logger log = Logger.getLogger(this.getClass());

    @Value("${lottery.easeMob.OrgName}")
    private String easeMobOrgName;

    @Value("${lottery.easeMob.AppName}")
    private String easeMobAppName;

    @Value("${lottery.easeMob.ClientId}")
    private String client_id;

    @Value("${lottery.easeMob.ClientSecret}")
    private String client_secret;

    @Value("${lottery.easeMob.BaseHttpUrl}")
    private String easeMobBaseHttpUrl;

    private static String easeToken;


    @Transactional
    public Boolean registerEaseMobUser(String username, String password) {
    	/*
    	String easeMobBaseHttpUrl = "https://a1.easemob.com/1145171016115852/niuniu/";
    	Strin/g easeToken = "YWMtGnYQqvj_EeestdkGppOTEQAAAAAAAAAAAAAAAAAAAAFRRUpA9g4R570sKa-efwfDAgMAAAFg86LKDgBPGgABAB7-zwBHjLqlAMi1nnq5nn3g7POpLRJZxrOVo86Jvg";
       */
        String userHttpUrl = easeMobBaseHttpUrl + "users";
        if (StringUtils.isBlank(easeToken)) {
            easeToken = getEaseMobToken();
        }
       
        String headerValue = "Bearer " + easeToken;
        try {
        	DefaultHttpClient httpClient = new DefaultHttpClient();
        	HttpPost post = new HttpPost(userHttpUrl);
	        // 接收参数json列表
	        JSONObject jsonParam = new JSONObject();
	        jsonParam.put("username", username);
	        jsonParam.put("password", password);
	        StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");// 解决中文乱码问题
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");
	        post.setHeader("Authorization",headerValue);
	        post.setEntity(entity);
	  
            HttpResponse ret = httpClient.execute(post);
            String tokenRet = EntityUtils.toString(ret.getEntity(), "UTF-8");
            System.out.println(tokenRet);
            log.info(ret);
             if (ret != null) {
                if (ret.getStatusLine().getStatusCode() == 200) {
                	System.out.println("sucessful");
                    log.info("环信注册用户成功 200 ");
                    return true;
                } else if (ret.getStatusLine().getStatusCode() == 401) {
                    log.error("环信注册用户token错误 " + ret.getStatusLine().getStatusCode() + " 重新获取token执行");
                    easeToken = getEaseMobToken();
                    log.error("尝试重新注册 1次");
                    //更新header
                    headerValue = "Bearer " + easeToken;

                   // HttpResponse retCheck = HttpclientTool.getPostResponseWithHeader(userHttpUrl, postObject, headerValue);
                    HttpResponse retCheck = httpClient.execute(post);
                    if (retCheck != null) {
                        if (retCheck.getStatusLine().getStatusCode() == 200) {
                            log.error("环信重新注册用户成功 200 ");
                            return true;
                        } else {
                            log.error("环信重新注册用户失败 " + retCheck.getStatusLine().getStatusCode());
                        }
                    } else {
                        log.error("环信重新注册用户请求失败,retcheck null");
                    }

                } else {
                    log.error("环信注册用户错误 " + ret.getStatusLine().getStatusCode());
                }

            } else {
                log.error("环信注册用户请求失败,ret null");
            }

        } catch (Exception e) {
            log.error("环信注册用户exception " + e.getMessage());
        }

        return false;

    }

    private String getEaseMobToken() {
    	
        String tokenHttpUrl = easeMobBaseHttpUrl+"token";
        String token = "";
        try {
        	DefaultHttpClient httpClient = new DefaultHttpClient();
        	HttpPost post = new HttpPost(tokenHttpUrl);
	        // 接收参数json列表
	        JSONObject jsonParam = new JSONObject();
	        jsonParam.put("grant_type", "client_credentials");
	        jsonParam.put("client_id", client_id);
	        jsonParam.put("client_secret", client_secret);
	        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");
	        post.setEntity(entity);
	  
            HttpResponse resp = httpClient.execute(post);
            String tokenRet = EntityUtils.toString(resp.getEntity(), "UTF-8");
            System.out.println("返回结果："+tokenRet);
            log.info("返回结果： " + tokenRet);
            if (StringUtils.isNotBlank(tokenRet)) {
                JSONObject ro = new JSONObject(tokenRet);
                token = ro.getString("access_token");
                log.info("环信token获取成功 : " + token);
            } else {
                log.error("环信token请求失败");
            }
        } catch (Exception e) {
            log.error("环信获取token exception " + e.getMessage());
        }
        return token;
    }
    public static void main(String args[]) throws Exception{
    	Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    ArrayList arrayList  = new ArrayList();
	    int i=0,j = 0;
	        Class.forName("com.mysql.jdbc.Driver");// 加载Oracle驱动程序
	        System.out.println("开始尝试连接数据库！");
	        String url = "jdbc:mysql://localhost:3306/world";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = "root";// 用户名,系统默认的账户名
	        String password = "amarsoft";// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接成功！");
	        pre = con.prepareStatement("select * from account_info where accountid>=1100 and offtype<>'99'");
	        //preStatement.setString(1, "Citibank");
	 
	        result = pre.executeQuery();
	        String sql = "select * from account_info";// 预编译语句，“？”代表参数
	      
            System.out.println("学号\t姓名");
         //   while (result.next()) {
               // System.out.println("12--"+result.getString(2));
          //  String username = result.getString(2);
          //  String password1 = result.getString(2);
    	EasemobService es = new EasemobService();
    	
    	String username1 = "sys888_qwe_125";
    	String password1 = "sys888_qwe_125";
    	
    	Thread.sleep(2000);
    	System.out.println(es.registerEaseMobUser(username1, password1));
        //    }
    }
}
