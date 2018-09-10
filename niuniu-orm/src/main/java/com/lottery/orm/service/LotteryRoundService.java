package com.lottery.orm.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.LotteryGameRound;
import com.lottery.orm.bo.LotteryItem;
import com.lottery.orm.bo.LotteryOrder;
import com.lottery.orm.bo.LotteryRound;
import com.lottery.orm.bo.LotteryRoundItem;
import com.lottery.orm.bo.TSTimeTask;
import com.lottery.orm.dao.CustomLotteryMapper;
import com.lottery.orm.dao.LotteryGameRoundMapper;
import com.lottery.orm.dao.LotteryRoundItemMapper;
import com.lottery.orm.dao.LotteryRoundMapper;
import com.lottery.orm.dao.ScheduleJobMapper;
import com.lottery.orm.dao.TSTimeTaskMapper;
import com.lottery.orm.dto.LotteryGameCurDto;
import com.lottery.orm.dto.ResultDataDto;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.TaskUtils;

@Service
@Transactional
public class LotteryRoundService {
	public final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private LotteryRoundMapper lotteryRoundMapper;

	@Autowired
	private LotteryRoundItemMapper roundItemMapper;

	@Autowired
	private CustomLotteryMapper customLotteryMapper;
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	@Autowired
	private LotteryOrderService lotteryOrderService;
	
	@Autowired
	private LotteryGameRoundMapper lotteryGameRoundMapper;
	
	@Autowired
	private DynamicTask dynamicTask;
	
	@Autowired
	private TSTimeTaskMapper TSTimeTaskMapper;
	
	// 添加投注单
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean addLotteryRound(LotteryRound round) throws Exception {
		
		DateTime openTime = new DateTime(round.getOpentime());
		DateTime nowTime = new DateTime(new Date());
		DateTime closeTime = openTime.minusMinutes(2);//开奖前2分钟封盘
		if(Minutes.minutesBetween(nowTime, closeTime).getMinutes()>10){//如果封盘离现在的时间超过10分钟,则从开始时间往后推8分钟来确定封盘时间
			closeTime = nowTime.plusMinutes(8);
		}
		round.setRoundstatus(EnumType.RoundStatus.Open.ID);
		round.setStarttime(nowTime.toDate());
		round.setClosetime(closeTime.toDate());//开奖前2分钟封盘
		LotteryRound existRound = customLotteryMapper.selectRoundByTypeAndTerm(round.getLotterytype(), round.getLotteryterm());
		if(existRound==null){
			List<LotteryItem> itemList = customLotteryMapper.selectItemByLottery(EnumType.Lottery.YMZ.ID);
			List<LotteryRoundItem> roundItemList = new ArrayList<LotteryRoundItem>();
			lotteryRoundMapper.insertSelective(round);
			if (round.getRoundid() > 0) {
				for (LotteryItem item : itemList) {
					LotteryRoundItem roundItem = new LotteryRoundItem();
					roundItem.setRoundid(round.getRoundid());
					roundItem.setItemno(item.getItemno());
					roundItem.setItemscale(item.getItemscale());
					roundItemMapper.insertSelective(roundItem);
					roundItemList.add(roundItem);
				}
				round.setRoundItemList(roundItemList);
				addRoundTask(round);
				return true;
			}
		}
		return false;
	}
	
	//添加游戏定时任务
	private void addRoundTask(LotteryRound nextRound) throws Exception{
		/*String cronStr = "";
		try {
			DateTime runGetOpenTime = new DateTime(nextRound.getOpentime()).plusSeconds(3);//执行任务时间最好比开奖晚1秒
			cronStr = TaskUtils.getOpenCron(runGetOpenTime.toDate());
			ScheduleJob job = lotteryTaskService.getTaskByNameAndGroup("nextRound", nextRound.getLotterytype());
			if (job != null) {
				job.setCronExpression(cronStr);
				if(taskService.updateJobCron(job)){
					scheduleJobMapper.updateByPrimaryKeySelective(job);
				}
			}
			
			cronStr = TaskUtils.getCloseCron(nextRound.getClosetime());
			job = taskService.getTaskByNameAndGroup("closeRound", nextRound.getLotterytype());
			
			if (job != null) {
				job.setCronExpression(cronStr);
				if(taskService.updateJobCron(job)){
					scheduleJobMapper.updateByPrimaryKeySelective(job);
				}
			}
			
		} catch (Exception e) {
			log.error("更新获取开奖结果任务失败:"+nextRound.getLotteryterm()+":"+cronStr);
			throw new Exception(e);
		}*/
		
		String openCronStr = "";
		String endCronStr = "";
		//DateTime runGetOpenTime = new DateTime(nextRound.getOpentime()).plusSeconds(3);//执行任务时间最好比开奖晚1秒
		DateTime runGetOpenTime = new DateTime(nextRound.getOpentime());
		openCronStr = TaskUtils.getOpenCron(runGetOpenTime.toDate());
		endCronStr = TaskUtils.getCloseCron(nextRound.getClosetime());
		TSTimeTask openTask = null;
		TSTimeTask endTask = null;
		boolean openResult = false;
		
		boolean endResult = false;
		if(nextRound.getLotterytype().equals(EnumType.LotteryType.CQ.ID)){
			openTask = TSTimeTaskMapper.selectByTaskId("openCQCronTrigger");
			openResult = dynamicTask.updateCronExpression(openTask.getTaskId(), openCronStr, runGetOpenTime.toDate());
			
			endTask = TSTimeTaskMapper.selectByTaskId("closeCQCronTrigger");
			endResult = dynamicTask.updateCronExpression(endTask.getTaskId(), endCronStr);
		}else if(nextRound.getLotterytype().equals(EnumType.LotteryType.TJ.ID)){
			openTask = TSTimeTaskMapper.selectByTaskId("openTJCronTrigger");
			openResult = dynamicTask.updateCronExpression(openTask.getTaskId(), openCronStr, runGetOpenTime.toDate());
			
			endTask = TSTimeTaskMapper.selectByTaskId("closeTJCronTrigger");
			endResult = dynamicTask.updateCronExpression(endTask.getTaskId(), endCronStr);
		}else if(nextRound.getLotterytype().equals(EnumType.LotteryType.GD.ID)){
			openTask = TSTimeTaskMapper.selectByTaskId("openGDCronTrigger");
			openResult = dynamicTask.updateCronExpression(openTask.getTaskId(), openCronStr, runGetOpenTime.toDate());
			
			endTask = TSTimeTaskMapper.selectByTaskId("closeGDCronTrigger");
			endResult = dynamicTask.updateCronExpression(endTask.getTaskId(), endCronStr);
		}
		
		if(openResult){
			openTask.setCronExpression(openCronStr);
			openTask.setStartTime(runGetOpenTime.toDate());
			TSTimeTaskMapper.updateByPrimaryKeySelective(openTask);
		}
		
		if(endResult){
			endTask.setCronExpression(endCronStr);
			TSTimeTaskMapper.updateByPrimaryKeySelective(endTask);
		}else{
			log.error("定时封盘任务失败:"+nextRound.getLotteryterm()+":"+endCronStr);
			throw new Exception("定时封盘任务失败:"+nextRound.getLotteryterm()+":"+openCronStr+":"+endCronStr);
		}
	}

	// 游戏结束
	public int endLotteryRound(LotteryRound round, String originResult, Date openTime){
		round.setResultstr(getCronLotteryResult(originResult));
		round.setActualopentime(openTime);//更新开奖时间
		round.setRoundstatus(EnumType.RoundStatus.End.ID);
		round.setEndtime(new Date());
		round.setOriginresult(originResult);
		return lotteryRoundMapper.updateByPrimaryKeySelective(round);
	}
	
	// 游戏兑奖
	public void prizeLotteryRound(LotteryRound round){
		List<LotteryItem> itemList = customLotteryMapper.selectItemByLottery(EnumType.Lottery.YMZ.ID);
		List<LotteryOrder> orderList = customLotteryMapper.selectOrderByRoundId(round.getRoundid());
		for (LotteryOrder order : orderList) {
			try{
				String updateResult = lotteryOrderService.updateOrderByRound(round, order, itemList);
				if(!updateResult.equals("")){
					log.error(updateResult);
				}
			}catch(Exception e){
				log.error(e);
			}
		}
	}
	
	
	//根据广西快乐十分的开奖结果计算玉米籽的开奖结果
	private String getCronLotteryResult(String originResult){
		if(originResult.contains(",")){
			String[] prizeNumbers = originResult.split(",");
			int prizeNumber = Integer.parseInt(prizeNumbers[prizeNumbers.length-1]);
			int cronNumber = prizeNumber % 4 == 0 ? 4 : prizeNumber % 4;
			return String.valueOf(cronNumber);
		}else{
			return null;
		}
	}
	
	/*// 游戏结束并兑奖
	public boolean endLotteryRoundByTerm(LotteryRound round) {
		LotteryRound existRound = customLotteryMapper.selectRoundByTypeAndTerm(round.getLotterytype(), round.getLotteryterm());
		if(existRound!=null&&!existRound.getRoundstatus().equals(EnumType.RoundStatus.End.ID)){
			round.setRoundid(existRound.getRoundid());
			return endLotteryRound(round);
		}
		return false;
	}*/
	
	// 游戏封盘
	public boolean closeLotteryRound(String lotteryType) {
		List<LotteryRound> roundList =  customLotteryMapper.selectRoundByTime(lotteryType, new Date());
		if(roundList.size()>0){
			for (LotteryRound round : roundList) {
				round.setActualclosetime(new Date());
				round.setRoundstatus(EnumType.RoundStatus.Close.ID);
				lotteryRoundMapper.updateByPrimaryKeySelective(round);
			}
			return true;
		}
		return false;
	}
	
	// 游戏结果
	public List<ResultDataDto> getLotteryResult(Date startTime, Date endTime, Integer sid, Integer beginRow, Integer pageSize) throws ParseException {
		List<ResultDataDto> roundList = new ArrayList<ResultDataDto>();
		System.out.println("9---"+(null != startTime)+"..."+(!("".equals(startTime))));
		Date[] sTime = CommonUtils.getDateTime(startTime, endTime);
		roundList = lotteryGameRoundMapper.selectGameResult(sTime[0], sTime[1], sid,  beginRow, pageSize);
		/*
		if ((null != startTime)&&(null != endTime)){
		    roundList = lotteryGameRoundMapper.selectGameResult(startTime, endTime, sid,  beginRow, pageSize);
		}else if (time.equals("01")||(time.equals("02")||(time.equals("03")))){
			Date[] sTime = CommonUtils.getDateBetween(startTime, endTime, time);
			roundList = lotteryGameRoundMapper.selectGameResult(sTime[0], sTime[1], sid,  beginRow, pageSize);
		}else if (time.equals("04")||time.equals("05")){	
			roundList = lotteryGameRoundMapper.selectGameResultBytime(sid, time, beginRow, pageSize);
		}
		*/	
		return roundList;
	}
	
	//获取是否有游戏结果
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public synchronized LotteryGameRound getLotteryTermResult(Integer sid,String lotteryterm) throws Exception{
		LotteryGameRound lotteryGameRound = lotteryGameRoundMapper.selectLotteryGameResult(sid, lotteryterm);
		return lotteryGameRound;
	} 
	
	//获取当前游戏接口信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public LotteryGameCurDto getLotteyCurResult(Integer sid) throws Exception{
		List<LotteryGameRound> list = lotteryGameRoundMapper.selectLotteryOrderResult(sid);
		LotteryGameCurDto lgc = new LotteryGameCurDto();
		for (int i = 0;i<list.size();i++){
			LotteryGameRound lgr = new LotteryGameRound();
			lgr = list.get(i);
			if (i==0){
				lgc.setSid(lgr.getSid());
				lgc.setStarttime(lgr.getStarttime());
				lgc.setOvertime(lgr.getOvertime());
				lgc.setOpentime(lgr.getOpentime());
				lgc.setLotteryterm(lgr.getLotteryterm());
			}
			if (i==1){
				lgc.setLastlotteryterm(lgr.getLotteryterm());
				lgc.setLastlotteryresult(lgr.getLotteryresult());
			}
		}
		return lgc;
	}
}
