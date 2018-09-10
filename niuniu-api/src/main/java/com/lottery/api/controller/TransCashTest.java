package com.lottery.api.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdpay.util.Utils;
import com.lottery.api.dto.PayTransitModel;
import com.lottery.api.dto.SubmitReturn;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.service.AccountInfoService;

@Service
@Transactional
public class TransCashTest {
	
	public static final Logger LOG = Logger.getLogger(TransCashTest.class);
	
	@Autowired
    private AccountInfoMapper accountInfoMapper;
	
	@Autowired
    private AccountRechargeMapper accountRechargeMapper;
	
	
	public synchronized  String getCashSubmit(AccountRecharge aRecharge) throws Exception{
	  
		PayTransitModel model = new PayTransitModel();
		model.setVersion("2");//必填	当前接口版本号 2
		model.setAgent_id(Utils.readProp("P_UserId"));//必填	商户编号如1001
		model.setBatch_no(aRecharge.getOrderno());//必填	批量付款定单号（要保证唯一）。长度最长50字符	11<批量付款定单号<50
		model.setBatch_amt(String.valueOf(aRecharge.getTransamt())+".00");
		model.setBatch_num("1");//必填	该次付款总笔数，付给多少人的数目，“单笔数据集”里面的数据总笔数
		model.setDetail_data(model.getBatch_no()+"^"+aRecharge.getBankno()+"^0^"+aRecharge.getBankaccount()+"^"+aRecharge.getBankid()+"^"+model.getBatch_amt()+"^cash^"
		+aRecharge.getBankloproname()+"^"+aRecharge.getBanklocityname()+"^"+aRecharge.getBankaddress());
		//必填	批付到银行帐户格式:“商户流水号^银行编号^对公对私^收款人帐号^收款人姓名^付款金额^付款理由^省份^城市^收款支行名称”来组织数据，每条整数据间用“|”符号分隔商户流水号长度最长20字符银行编号：参照枚举类型1,对公对私：参照枚举类型2
		model.setNotify_url(Utils.readProp("C_Notify_URL"));//必填	支付后返回的商户处理页面，URL参数是以http://或https://开头的完整URL地址(后台处理)
		model.setExt_param1("cash");//必填	商户自定义原样返回,长度最长50字符
		String key = Utils.readProp("key");
		/*
		String version = "2";	    
		String agent_id = "1004044";	
		String key = Utils.readProp("key");//密钥需要商户替换为自己的密钥
		String batch_no ="20180315121117";	//必填	批量付款定单号（要保证唯一）。长度最长50字符	11<批量付款定单号<50
		String batch_amt = "50.00";	//必填	付款总金额不可为空，单位：元，小数点后保留两位。12.37
		String batch_num = "1";	//必填	该次付款总笔数，付给多少人的数目，“单笔数据集”里面的数据总笔数
		String detail_data = batch_no+"^5^0^6217582600006480203^吴柳平^50.00^测试^广西壮族自治区^柳州市^中国银行柳州市屏山大道支行";	
		//必填	批付到银行帐户格式:“商户流水号^银行编号^对公对私^收款人帐号^收款人姓名^付款金额^付款理由^省份^城市^收款支行名称”来组织数据，每条整数据间用“|”符号分隔商户流水号长度最长20字符银行编号：参照枚举类型1,对公对私：参照枚举类型2
		String notify_url = "http://admin.niuniu668.com";	//必填	支付后返回的商户处理页面，URL参数是以http://或https://开头的完整URL地址(后台处理)
		String ext_param1 = "cash";	//必填	商户自定义原样返回,长度最长50字符
		PayTransitModel model = new PayTransitModel();
		
		model.setVersion(version);
		model.setAgent_id(agent_id);
		model.setBatch_no(batch_no);
		model.setBatch_amt(batch_amt);
		model.setBatch_num(batch_num);
		model.setDetail_data(detail_data);
		model.setNotify_url(notify_url);
		model.setExt_param1(ext_param1);
		
		//*/
    	aRecharge.setMerno(model.getAgent_id());
    	aRecharge.setNotifyurl(Utils.readProp("C_Notify_URL"));
    	aRecharge.setRemark("取现金额:"+aRecharge.getTransamt()+",取现时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	SubmitReturn subReturn = TransitSubmit.SubmitUrl(model,key);//处理请求
		if(subReturn.is_success())
	    {
	    	System.out.println(subReturn.get_error_message());//出现错误打印出错误信息
	    	LOG.info(new Date()+"error:"+subReturn.get_error_message());
	    	if (aRecharge.getRelativetype().equals("Out")){
            	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
            	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
        		aRecharge.setAccountamount(aInfo.getUsermoney());
        		accountInfoMapper.updateByPrimaryKey(aInfo);
    	    	if (aRecharge.getFee()>0){
    	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
    	    		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getFee())));
    		    	accountInfoMapper.updateByPrimaryKey(aInfo);
    	    	}

            	aRecharge.setOrderstate("02");
            	accountRechargeMapper.updateByPrimaryKey(aRecharge);
        	}
	    	return "1111~该用户打款出现异常！";
	    }
	    else
	    {
	    	//提交成功后，返回的支付信息，商户需要验证签名和金额，来处理自己的业务逻辑
	    	//此处返回值为xml 此处未完善。需要商户自己处理解析xml
	    	String str = subReturn.get_error_message();
	    	int i = str.indexOf("<ret_code>")+10;
	    	int j = str.indexOf("</ret_code>");
	    	String code = str.substring(i, j);
	    	i = str.indexOf("<ret_msg>")+9;
	    	j = str.indexOf("</ret_msg>");
	    	String msg = str.substring(i,j);
	    	String result = code+"~"+msg;
	    	System.out.println(subReturn.get_error_message());
	    	LOG.info(new Date()+",sucess:"+subReturn.get_error_message());
	    	if (aRecharge.getRelativetype().equals("Out")&&!(code.equals("0000"))){
            	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
            	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
        		aRecharge.setAccountamount(aInfo.getUsermoney());
        		accountInfoMapper.updateByPrimaryKey(aInfo);
    	    	if (aRecharge.getFee()>0){
    	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
    	    		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getFee())));
    		    	accountInfoMapper.updateByPrimaryKey(aInfo);
    	    	}
            	aRecharge.setOrderstate("02");
            	accountRechargeMapper.updateByPrimaryKey(aRecharge);
        	}else{
    	    	accountRechargeMapper.updateByPrimaryKey(aRecharge);	
        	}

	    	return result;
	    }
		
	}
	public static void main(String args[]) throws Exception{
		TransCashTest a = new TransCashTest();
		AccountRecharge aRecharge = new AccountRecharge();
		a.getCashSubmit(aRecharge);
    	//System.out.println("12-"+code+".."+msg);
	}
}
