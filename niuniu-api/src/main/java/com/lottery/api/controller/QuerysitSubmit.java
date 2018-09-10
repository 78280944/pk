package com.lottery.api.controller;

import com.kdpay.util.Utils;
import com.lottery.api.dto.QueryModel;
import com.lottery.api.dto.SubmitReturn;
import com.lottery.api.util.DataHelper;


public class QuerysitSubmit {
	
	public static SubmitReturn SubmitUrl(QueryModel model,String key)
	{
		SubmitReturn subReturn = new SubmitReturn();
		//String url = "https://agentpay.duqee.com/Payment/BatchQuery.aspx";//此为测试地址，商户应使用文档中的正式地址
		String url = Utils.readProp("CashQueryStr");
		//组织MD5加密
		String sign = DataHelper.QuerySgin(model,key);
		
		//获取url
		String postParemise = DataHelper.QueryPostURL(model,sign);

		//提交请求
		String resParamit = DataHelper.RequestPostUrl(url,postParemise);
		if(resParamit != null)
		{
			subReturn.set_success(false);
			subReturn.set_error_message(resParamit);
			return subReturn;
		}
		else
		{
			subReturn.set_success(true);
			subReturn.set_error_message("请重试");
			return subReturn;
		}
		
	}
}