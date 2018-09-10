package com.lottery.orm.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;



public class HttpclientTool {
	public static String post(String urlStr, JSONObject bodyObj) {
		CloseableHttpClient closeableHttpClient = null;
		String result = null;
		try {
			// create HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			closeableHttpClient = httpClientBuilder.build();
			HttpPost httpPost = new HttpPost(urlStr);

			String content = bodyObj.toString();
			StringEntity stringEntity = new StringEntity(content, ContentType.create("application/json", Consts.UTF_8));
			httpPost.setEntity(stringEntity);
			// perform post request
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			// to get response entity
			HttpEntity entity = httpResponse.getEntity();
			// response status
			// to judge the response entity is null or not
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close stream and release the resource
				if (closeableHttpClient != null)
					closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static HttpResponse getPostResponse(String urlStr, JSONObject bodyObj) {
		CloseableHttpClient closeableHttpClient = null;
		HttpResponse httpResponse = null;
		try {
			// create HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			closeableHttpClient = httpClientBuilder.build();
			HttpPost httpPost = new HttpPost(urlStr);

			String content = bodyObj.toString();
			StringEntity stringEntity = new StringEntity(content, ContentType.create("application/json", Consts.UTF_8));
			httpPost.setEntity(stringEntity);
			// perform post request
			httpResponse = closeableHttpClient.execute(httpPost);
			return httpResponse;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// close stream and release the resource
				if (closeableHttpClient != null)
					closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return httpResponse;
	}

	public static String get(String urlStr) {
		CloseableHttpClient closeableHttpClient = null;
		String result = null;
		try {
			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			closeableHttpClient = httpClientBuilder.build();
			HttpGet httpGet = new HttpGet(urlStr);

			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			// 判断响应实体是否为空
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				if (closeableHttpClient != null)
					closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	 public static String getToken() throws Exception {
	        String resultStr = null;
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        String url = "https://a1.easemob.com/1145171016115852/niuniu/token";
            String client_id = "YXA6UUVKQPYOEee9LCmvnn8Hww";
            String client_secret = "YXA6lCZECJ7FU3UGC-kYoRv9gaNe40U";
	        HttpPost post = new HttpPost(url);
	        // 接收参数json列表
	        JSONObject jsonParam = new JSONObject();
	        jsonParam.put("grant_type", "client_credentials");
	        jsonParam.put("client_id", client_id);
	        jsonParam.put("client_secret", client_secret);
	        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");
	        post.setEntity(entity);

	        // 请求结束，返回结果
	        try {
	            HttpResponse res = httpClient.execute(post);
	            // 如果服务器成功地返回响应
	            String responseContent = null; // 响应内容
	            HttpEntity httpEntity = res.getEntity();
	            responseContent = EntityUtils.toString(httpEntity, "UTF-8");
	            System.out.println(responseContent);
	            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // 关闭连接 ,释放资源
	            httpClient.getConnectionManager().shutdown();
	            return resultStr;
	        }
	    }
	public static void main(String args[]) throws Exception{
		String urls = "https://a1.easemob.com/1145171016115852/niuniu/token";
	  	String easeMobClientId = "YXA6UUVKQPYOEee9LCmvnn8Hww";
    	String easeMobClientSecret = "YXA61CZECJ7FU3UGC-kYoRv9gaNe40U";
	    JSONObject tokenObject = new JSONObject();
         tokenObject.put("grant_type", "client_credentials");
         tokenObject.put("client_id", easeMobClientId);
         tokenObject.put("client_secret", easeMobClientSecret);
         
		HttpclientTool.getToken();
	}
}
