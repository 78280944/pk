package com.lottery.orm.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.OffAccountInfo;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.OffAccountInfoMapper;

@Service
@Transactional
public class OffAccountInfoService {

	@Autowired
	private OffAccountInfoMapper offAccountInfoMapper;

	@Autowired
	private AccountDetailMapper accountDetailMapper;

	// 添加账户
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addOffAccountInfo(OffAccountInfo paraInfo) {
		offAccountInfoMapper.insertSelective(paraInfo);
		
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserid(paraInfo.getUserid());
		accountDetail.setUsername(paraInfo.getUsername());
		accountDetail.setLimited(paraInfo.getLimited());
		accountDetail.setRatio(paraInfo.getRatio());
		accountDetail.setPercentage(paraInfo.getPercentage());
		accountDetail.setState(paraInfo.getState());
		//accountDetail.setSupusername(paraInfo.getUsername());
		accountDetail.setLevel(paraInfo.getLevel());
		accountDetail.setOfftype(paraInfo.getOfftype());
		accountDetail.setMoney(BigDecimal.valueOf(0.0));
		accountDetail.setBudget(0.0);
		accountDetailMapper.insertSelective(accountDetail);
	}

	// 添加子帐户
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addOffAccountInfo(OffAccountInfo paraInfo,String offtype) {
		if (offtype.equals("2")){
		    offAccountInfoMapper.insertSelective(paraInfo);
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setUserid(paraInfo.getUserid());
			accountDetail.setUsername(paraInfo.getUsername());
			accountDetail.setRatio(paraInfo.getRatio());
			accountDetail.setPercentage(paraInfo.getPercentage());
			accountDetail.setState(paraInfo.getState());
			//accountDetail.setSupusername(paraInfo.getUsername());
			accountDetail.setLevel(paraInfo.getLevel());
			accountDetail.setOfftype("2");
			accountDetailMapper.insertSelective(accountDetail);  
		    
		}
	}
	
	// 更新帐户
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOffAccountInfo(OffAccountInfo paraInfo) {
		offAccountInfoMapper.updateByPrimaryKeySelective(paraInfo);
		
		//AccountDetail accountDetail = new AccountDetail();
		AccountDetail accountDetail = accountDetailMapper.selectByUserId(paraInfo.getUserid(), paraInfo.getOfftype());
		accountDetail.setUserid(paraInfo.getUserid());
		accountDetail.setUsername(paraInfo.getUsername());
		accountDetail.setRatio(paraInfo.getRatio());
		accountDetail.setPercentage(paraInfo.getPercentage());
		accountDetail.setState(paraInfo.getState());
		//accountDetail.setSupusername(paraInfo.getSupusername());
		accountDetail.setLevel(paraInfo.getLevel());
		accountDetail.setOfftype(paraInfo.getOfftype());
		accountDetailMapper.updateByPrimaryKeySelective(accountDetail);
	}
	
	// 更新子帐户
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void updateOffAccountInfo(OffAccountInfo paraInfo,String offtype) {
		if (offtype.equals("2"))
		    offAccountInfoMapper.updateByPrimaryKeySelective(paraInfo);
	}

}
