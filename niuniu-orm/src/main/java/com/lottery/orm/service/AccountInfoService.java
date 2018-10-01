package com.lottery.orm.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.bo.LotteryGameDetail;
import com.lottery.orm.bo.LotteryGameOrder;
import com.lottery.orm.bo.SysBene;
import com.lottery.orm.bo.SysFee;
import com.lottery.orm.bo.SysLimit;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.dao.LotteryGameDetailMapper;
import com.lottery.orm.dao.LotteryGameOrderMapper;
import com.lottery.orm.dao.SysBeneMapper;
import com.lottery.orm.dao.SysFeeMapper;
import com.lottery.orm.dto.RoomOrderDto;
import com.lottery.orm.result.AccountResult;
import com.lottery.orm.util.MessageTool;

@Service
@Transactional
public class AccountInfoService {
	public static final Logger LOG = Logger.getLogger(AccountInfoService.class);
	@Autowired
	private AccountInfoMapper accountInfoMapper;

	@Autowired
	private AccountDetailMapper accountDetailMapper;
	
	@Autowired
	private LotteryGameOrderMapper lotteryGameOrderMapper;
	
	@Autowired
    private AccountRechargeMapper accountRechargeMapper;
	
	@Autowired
	private SysBeneMapper sysBeneMapper;
	
	@Autowired
	private SysFeeMapper sysFeeMapper;
	
	// 添加账户
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public synchronized void addAccountInfo(AccountInfo paraInfo) {
		accountInfoMapper.insertSelective(paraInfo);
	}

	// 更新账户
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public synchronized void updateAccountInfo(AccountInfo paraInfo) {
		accountInfoMapper.updateByPrimaryKeySelective(paraInfo);
	}
	
	//更新剩余点数
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public synchronized void updateAccountMount(AccountDetail accountDetail) {
	    accountDetailMapper.updateByPrimaryKeySelective(accountDetail);
	}
	
	//客户Id，更新账户余额
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public synchronized void updateResultAccountMount(BigDecimal amount,Integer accountid) {
	    accountInfoMapper.updateResultAccountMount(amount, accountid);
	}
	
	//取现金额检查
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public synchronized String checkCashMoneyInfo(AccountInfo accountInfo,Double transAmt) {
		
		//下注金额最大值
		RoomOrderDto  rd  = new RoomOrderDto();
		rd = lotteryGameOrderMapper.selectAccountIdOrder(accountInfo.getAccountid());
		int count = 0;
		if (rd == null){
			count = 0;
		}else{
		    count = ((null==rd.getOrderamount())?0:rd.getOrderamount().intValue());
			}
			
		//System.out.println("90-----------------"+accountInfo.getUsermoney()+".."+rd.getOrderamount()+"..."+count);
		if (((accountInfo.getUsermoney().subtract(BigDecimal.valueOf(count)).subtract(BigDecimal.valueOf(transAmt)))).doubleValue()<0)
			return "账户金额不足，请重新输入取现金额";
		return "true";
	}
	
	
	//打款金额检查
	public synchronized String checkDoMoneyInfo(AccountInfo accountInfo,Double transAmt) {
		//下注金额checkDoMoneyInfo最大值
		RoomOrderDto  rd  = new RoomOrderDto();
		rd = lotteryGameOrderMapper.selectAccountIdOrder(accountInfo.getAccountid());
		int count = 0;
		if (rd == null){
			count = 0;
		}else{
		    count = ((null==rd.getOrderamount())?0:rd.getOrderamount().intValue());
			}
			
		//System.out.println("90-----------------"+accountInfo.getUsermoney()+".."+rd.getOrderamount()+"..."+count);
		if (((accountInfo.getUsermoney().subtract(BigDecimal.valueOf(count)))).doubleValue()<0)
			return "账户金额不足，请重新输入取现金额";
		return "true";
	}
	
	
	public synchronized String checkResult(String orderNo,String payNo,String transAmt,String orderDate,String respCode,String respDesc){
		LOG.info("返回时间："+new Date()+"，订单编号："+orderNo+",支付订单号："+payNo+",交易金额："+transAmt+",返回消息代码："+respCode+",消息描述："+respDesc);
		AccountRecharge aRecharge = new AccountRecharge();
    	aRecharge.setOrderno(orderNo);
		aRecharge.setPayno(payNo);
		aRecharge.setTransamt((Double.valueOf(transAmt).intValue()));
		aRecharge.setOrderdate(orderDate);
		aRecharge.setRespcode(respCode);
		aRecharge.setRespdesc(respDesc);
		aRecharge.setRelativetype("In");
		AccountRecharge ar = accountRechargeMapper.selectByOrderNoReturn(aRecharge.getOrderno(), "In");
    	if (null == ar){
		      LOG.info("该订单信息有误！");
		      return "false";
    	}
    	if (ar.getOrderstate().equals("01"))
    		return "success";
    	if (null!=payNo&&(aRecharge.getRespcode().equals("00"))){
    		SysBene sb = sysBeneMapper.selectByAmount(BigDecimal.valueOf(aRecharge.getTransamt()));
			Double bene = 0.0;
			Double amount = 0.0;
			if (null == sb||sb.getBenefit() == null||sb.getBenefit() == BigDecimal.valueOf(0.0)){
				bene = 0.0;
			}else{
				bene = sb.getBenefit().doubleValue();
			}
			SysFee sf = sysFeeMapper.selectByPrimaryKey(1000);
			ar.setDonatamt(bene);//赠送金额
			ar.setFee(ar.getTransamt()*sf.getRefee().doubleValue());//充值费用
			ar.setPayamt(ar.getTransamt()-ar.getFee());//实际金额
			AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(ar.getAccountid());
			if (null == aInfo){
			      LOG.info("该用户不存在！,订单号为："+orderNo);
			      return "false";
			}
			amount = ar.getPayamt()+ar.getDonatamt();
			ar.setAccountamount(aInfo.getUsermoney().add(BigDecimal.valueOf(amount)));
	    	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf(amount)));
	    	accountInfoMapper.updateByPrimaryKey(aInfo);
	    	if (ar.getFee()>0){
	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
	    		aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf(ar.getFee())));
		    	accountInfoMapper.updateByPrimaryKey(aInfo);
	    	}
			ar.setPayno(aRecharge.getPayno());
	    	//ar.setOrderstate(aRecharge.getOrderstate());
	    	ar.setUpusertime(new Date());
	    	ar.setRespcode(aRecharge.getRespcode());
	    	ar.setRespdesc(aRecharge.getRespdesc());
	    	ar.setOrderstate("01");//成功
	    	accountRechargeMapper.updateByRechargeMessage(ar);
		    return "success";
	   }else if (aRecharge.getRespcode().equals("P000")||aRecharge.getRespcode().equals("02")||aRecharge.getRespcode().equals("03")){
		   
	   }else {
		   ar.setOrderstate("02");
		   accountRechargeMapper.updateByRechargeMessage(ar);
	   }
    	return "success";
	}
	
}
