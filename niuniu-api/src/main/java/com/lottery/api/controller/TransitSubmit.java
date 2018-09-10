package com.lottery.api.controller;

import java.io.IOException;

import com.kdpay.util.Utils;
import com.lottery.api.dto.PayTransitModel;
import com.lottery.api.dto.SubmitReturn;
import com.lottery.api.util.DataHelper;

public class TransitSubmit {
	
	public static SubmitReturn SubmitUrl(PayTransitModel model,String key) throws Exception
	{
		SubmitReturn subReturn = new SubmitReturn();
		//String url = "https://agentpay.duqee.com/Payment/BatchTransfer.aspx";//此为测试地址，商户应使用文档中的正式地址
		//String url = "https://api.aunpay.com/Payment/BatchTransfer.aspx";
		String url=Utils.readProp("CashStr");
		//组织MD5加密
		String sign = DataHelper.GetSgin(model,key);
		
		//获取url
		String postParemise = DataHelper.GetPostURL_sign(model,sign);
        System.out.println("98---"+postParemise);
        
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
