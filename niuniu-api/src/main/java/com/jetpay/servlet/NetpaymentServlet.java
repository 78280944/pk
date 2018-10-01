package com.jetpay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jetpay.config.JetPayConfig;
import com.jetpay.model.netpayment.RequestNetPayMent;
import com.jetpay.model.netpayment.ResponseNetPayMent;
import com.jetpay.service.netpayment.NetPayMentService;
import com.jetpay.utils.DateUtil;
import com.jetpay.utils.RandomUtil;
import com.jetpay.utils.SecurityUtil;
import com.jetpay.utils.XmlObjMcUtil;
import com.jfinal.core.Controller;

public class NetpaymentServlet extends Controller{
	
	Logger log=Logger.getLogger(NetpaymentServlet.class);
	
	private final  String PAY_RETURN_SUCCESS="00";
	private final  String PAY_RETURN_FAIL="01";
	private final  String PAY_RETURN_UNCERTAIN="02";
	private final  String PAY_RETURN_RECEIVED="03";
	private final  String PAY_PARAM_KEY="PayReturnOther";
	
	public void index(){
		render("/main/netpayment.html");
	}
	
	//网银支付和充值请求
	public void doNetpayment(){
		
		RequestNetPayMent pay=new RequestNetPayMent();
		//交易名称，固定填netpayment
		log.info(new String("需要支付的金额为："+getPara("amt")).trim());
		String amtl=getPara("amt");
		pay.setAmount(String.valueOf((int)(Float.parseFloat(amtl)*100)));
		pay.setCurrency("CNY");
		pay.setMerCode("JAG009");
		pay.setNotifyURL("http://xmywork.gicp.net:8090/netpayment/dorespNetpayment");
		//产品描述
		pay.setOrderDesc("支付");
		//订单号，本Demo随机生成字符串来填充,本demo 的orderno只做参考
		String orderno=DateUtil.format(DateUtil.getCurrDate(), DateUtil.formatStr_yyyyMMddHHmmss1)
		+RandomUtil.nextChar()+RandomUtil.nextChar()+RandomUtil.nextIntAsStringByLength(5);
		pay.setOrderNo(orderno);
		//本Demo这里获取系统的时间,进行格式化转换
		pay.setOrderTime(DateUtil.format(DateUtil.getCurrDate(), DateUtil.formatStr_yyyyMMddHHmmss1));
		//交易类型
	    //pay.setPayType(getPara("paytype").trim());
		pay.setPayType("17");
		
		//商品名称自定
		pay.setProductName("网银支付");
		//pay.setReservedField1("6225210110459824");
		//pay.setReservedField2("b");
		//pay.setReturnURL(JetPayConfig.props.get("returnURL").toString());
		pay.setReturnURL("http://xmywork.gicp.net:8090/netpayment");
		//pay.setPayCode("");
		//pay.setTranName(JetPayConfig.props.get("tranName").toString().trim());
		pay.setTranName("payment");
				
		//pay.setTranName("h5pay");
		//版本号固定，最低版本为1.00
		//pay.setVersion(JetPayConfig.props.get("version").toString().trim());
		pay.setVersion("1.00");
		//商户号，自定
		//pay.setMerCode(JetPayConfig.props.get("merCode").toString().trim());
		
	
		//pay.setPayType("21");										
	
		//币种，当前只能填CNY
		//pay.setCurrency(JetPayConfig.props.get("currency").toString());
		
		
		
		//商户后台回地址
		//pay.setReturnURL(JetPayConfig.props.get("returnURL").toString());
		//pay.setReturnURL("http://xmywork.gicp.net:8090/netpayment");
		//商户后台通知地址，自定 http://xmywork.gicp.net:8090/netpayment/dorespNetpayment
		//pay.setNotifyURL(JetPayConfig.props.get("notifyURL").toString());
		String res=NetPayMentService.netpayservice.getRequestParams(pay);
		log.info(new String("请求的参数已经生成："+res));
		//String payurl = "http://xmywork.gicp.net:8090/netpayment";
		//String payurl = "http://119.23.249.175:8080/pp_server/pay";
		String payurl = "http://120.79.25.230:8080/pp_server/pay";
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<html><head><title>pay</title></head><body>");
		sbHtml.append("<form id=\"form\" target=\"_blank\" name=\"form1\" action=\"" +payurl + "\" method=\"post\">");
		sbHtml.append("<input type=\"hidden\" name=\"tranType\" value=\"payment\"/>");
		sbHtml.append("<input type=\"hidden\" name=\"param\" value='" +res + "'/>");
		
		sbHtml.append("<input type=\"submit\" value=\"submit\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['form'].submit();</script>");
		sbHtml.append("</body></html>");
		
		log.info("请求表单已生成："+sbHtml.toString());
		renderHtml(sbHtml.toString());
		
	}
	
	//解析pp_server响应的数据
	public void dorespNetpayment(){
		
		int contentLength = this.getRequest().getContentLength();
		byte buffer[] = new byte[contentLength]; 
		try {
			 for (int i = 0; i < contentLength;){  
		            int readlen;
					readlen = this.getRequest().getInputStream().read(buffer, i,contentLength - i);
		            if (readlen == -1){  
		                break;  
		            }  
		            i += readlen;  
		    }  
		    try{
		        	//xml数据
					String content= new String(buffer, "UTF-8");
					log.info(new String("pp_server 返回的xml数据："+content));
					//将xml转换为javabean
					ResponseNetPayMent respay=(ResponseNetPayMent) XmlObjMcUtil.converyToJavaBean(content, ResponseNetPayMent.class);
					//进行返回数据验签
					if(NetPayMentService.netpayservice.isCheckRespSign(respay)){
						//支付详情页
						/*PaymentState: 00 支付成功	01 支付失败	02:不确定(对于不确定状态交易，需要商户稍后发起订单查询) 03：已接收 （注意：根据不同的状态可做相应的处理）*/
						if(this.PAY_RETURN_SUCCESS.equals(respay.getPaymentState())){
							setAttr("NetPayMentInfo", respay);
							render("/main/pay_success.html");
						}else if(this.PAY_RETURN_FAIL.equals(respay.getPaymentState())){
							//支付失败
							setAttr(this.PAY_PARAM_KEY, "支付失败");
							render("/main/other.html");
						}else if(this.PAY_RETURN_UNCERTAIN.equals(respay.getPaymentState())){
							//不确定
							setAttr(this.PAY_PARAM_KEY, "不确定");
							render("/main/other.html");
						}else if(this.PAY_RETURN_RECEIVED.equals(respay.getPaymentState())){
							//已接收
							setAttr(this.PAY_PARAM_KEY, "已接收");
							render("/main/other.html");
						}
					}
				} catch (UnsupportedEncodingException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (Exception e){
			// TODO: handle exception
			log.error(new String("解析pp_server 返回的xml数据错误"),e);
		} 
	}
}
