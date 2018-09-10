package com.lottery.orm.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.LotteryOrder;
import com.lottery.orm.bo.LotteryOrderDetail;
import com.lottery.orm.bo.LotteryRound;
import com.lottery.orm.bo.LotteryRoundItem;
import com.lottery.orm.dao.AccountAmountMapper;
import com.lottery.orm.dao.CustomLotteryMapper;
import com.lottery.orm.dao.LotteryReportMapper;
import com.lottery.orm.dto.AccAmountDto;
import com.lottery.orm.dto.InoutAccReportDto;
import com.lottery.orm.dto.PlayerWinReportDto;
import com.lottery.orm.dto.ProAccAmountDto;
import com.lottery.orm.dto.RoomHisOrderDto;
import com.lottery.orm.util.CommonUtils;

@Service
@Transactional
public class LotteryReportService {
	
	@Autowired
	private AccountAmountMapper accountAmountMapper;
	
	@Autowired
	private LotteryReportMapper lotteryReportMapper;

	//代理输赢报表
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public List<ProAccAmountDto> getProAccWinReport(Date startTime, Date endTime, Integer accountid,String level,String offtype,Integer beginRow, Integer pageSize) throws ParseException {
		List<ProAccAmountDto> roundList = new ArrayList<ProAccAmountDto>();
		//Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
		roundList = accountAmountMapper.selectProWinReport(startTime, endTime, accountid, level, offtype,beginRow, pageSize);		
		return roundList;
	}
	
	//会员报表
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public List<AccAmountDto> getAccWinReport(Date startTime, Date endTime, Integer accountid,String level, String offtype,Integer beginRow, Integer pageSize) throws ParseException {
		List<AccAmountDto> roundList = new ArrayList<AccAmountDto>();
		//Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
		roundList = accountAmountMapper.selectAccWinReport(startTime, endTime, accountid,level,offtype,beginRow, pageSize);
		return roundList;
	}
	
	//交易报表
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public List<AccAmountDto> getPlayTradeReport(Date startTime, Date endTime, Integer accountid,String level, Integer beginRow, Integer pageSize) throws ParseException {
		List<AccAmountDto> roundList = new ArrayList<AccAmountDto>();
		//Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
		//roundList = accountAmountMapper.selectAccWinReport(startTime, endTime, accountid, beginRow, pageSize);
		return roundList;
		
		
		
		
	}
	
	//玩家点数出入报表
	public List<InoutAccReportDto> selectAccInoutReport(Date startTime, Date endTime, Integer accountId,String level, Integer beginRow, Integer pageSize) throws ParseException {
		List<InoutAccReportDto> roundList = new ArrayList<InoutAccReportDto>();
		//Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
	    roundList = lotteryReportMapper.selectAccInoutReport(startTime, endTime, accountId, level,beginRow, pageSize);	
		return roundList;
	}
	
	
	//代理点数出入报表
	public List<InoutAccReportDto> selectProInoutReport(Date startTime, Date endTime, Integer accountId,Integer beginRow, Integer pageSize) throws ParseException {
		List<InoutAccReportDto> roundList = new ArrayList<InoutAccReportDto>();
		//Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
	    roundList = lotteryReportMapper.selectProInoutReport(startTime, endTime, accountId,beginRow, pageSize);	
		return roundList;
	}
	
	
	
}
