package com.jetpay.service.netpayment;

import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.jetpay.config.JetPayConfig;
import com.jetpay.model.netpayment.RequestNetPayMent;
import com.jetpay.model.netpayment.ResponseNetPayMent;
import com.jetpay.utils.MapJavaObjectConverrter;
import com.jetpay.utils.SecurityUtil;
import com.jetpay.utils.XmlObjMcUtil;
/**
 * @ClassName:     NetPayMentService
 * @Description:   TODO(网银支付接口service类)
 * @author:        zdg
 * @date:          2017-7-11 下午04:48:33
 */
public class NetPayMentService{
	
	public	static NetPayMentService netpayservice=new NetPayMentService();
	Logger log=Logger.getLogger(NetPayMentService.class);
	
	/** 
	 * @Title: getRequestRes
	 * @Description: TODO(获取请求xml参数结果)
	 * @param netpayment
	 * @return
	*/
	public String getRequestParams(RequestNetPayMent pay)throws RuntimeException{
		
		//把pay装换成Map<String,String>,用于下面首字母排序
		Map<String,String> params=MapJavaObjectConverrter.objectToMapString(pay, true);
		//转换成请求的签名原串
		String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
		String merKey = "eSnTRj5nR3p5jgGeE8tb";
		paramsStr = paramsStr+"&key="+merKey;
		log.info(new String("签名原串+key："+paramsStr));
		//log.info(new String("签名原串+key："+paramsStr+"&key="+JetPayConfig.props.get("merKey").toString()));
		//写进入签名
		//String paramsStr1 = "amount=10000&currency=CNY&merCode=JAG009&notifyURL=http://xmywork.gicp.net:8090/netpayment/dorespNetpayment&orderDesc=支付&orderNo=201809301521446277231&orderTime=20180930152144&payType=21&productName=网银支付&returnURL=http://xmywork.gicp.net:8090/netpayment&tranName=h5pay&version=1.00&key=eSnTRj5nR3p5jgGeE8tb";
		pay.setSign(SecurityUtil.MD5Hex(paramsStr));
		System.out.println("签名原串+keynew1："+SecurityUtil.MD5Hex(paramsStr));
		//进行javabean====>xml数据格式转换，生成请求参数
		try {
			String res=XmlObjMcUtil.ObjectToXml(pay, "UTF-8");
			log.error(new String("请求的数据 NetPayMentService res"+ res));
			return res;
		} catch (JAXBException e){
			// TODO Auto-generated catch block
			log.error(new String("网银支付请求参数转换失败"));
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * @Title: isCheckRespSign
	 * @Description: TODO(验证返回参数)
	 * @return boolean
	 */
	public boolean isCheckRespSign(ResponseNetPayMent resppay){
		
		String sgin=resppay.getSign();
		//返回签名不需要参与排序和生成签名原串
		resppay.setSign("");

		//把resppay装换成Map<String,String>,用于下面首字母排序
		Map<String,String> params=MapJavaObjectConverrter.objectToMapString(resppay, true);
		
		//转换成签名原串
		String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
		log.info("pp_server响应的签名原串："+paramsStr);
		//生成签名
		String md5hexSign=SecurityUtil.MD5Hex(paramsStr+ "&key="+JetPayConfig.props.get("merKey").toString());
		log.info("pp_server响应的签名原串生成的的签名："+md5hexSign);
		//验证签名
		if(sgin.equals("md5hexSign"))
			return true;
		else
			return false;
		
	}

}
