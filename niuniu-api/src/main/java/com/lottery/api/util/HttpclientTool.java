package com.lottery.api.util;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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
}
