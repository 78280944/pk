package com.lottery.orm.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.LotteryGame;
import com.lottery.orm.bo.LotteryGameOrder;
import com.lottery.orm.bo.LotteryGameRound;
import com.lottery.orm.bo.SysLimit;
import com.lottery.orm.dao.AccountAmountMapper;
import com.lottery.orm.dao.LotteryGameDetailMapper;
import com.lottery.orm.dao.LotteryGameMapper;
import com.lottery.orm.dao.LotteryGameOrderMapper;
import com.lottery.orm.dao.LotteryGameRoundMapper;
import com.lottery.orm.dao.LotteryServiceMapper;
import com.lottery.orm.dao.SysLimitMapper;
import com.lottery.orm.dao.TradeInfoMapper;
import com.lottery.orm.dto.LotteryAmountDto;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.HttpclientTool;
import com.lottery.orm.util.MessageTool;
import com.lottery.orm.bo.LotteryService;

@Service
@Transactional
public class JobsTaskService {
	public static final Logger LOG = Logger.getLogger(JobsTaskService.class);
	
	@Autowired
	private LotteryGameRoundMapper lotteryGameRoundMapper;
	
	@Autowired
	private LotteryGameOrderMapper lotteryGameOrderMapper;
	
	@Autowired
	private LotteryGameMapper lotteryGameMapper;
	
	@Autowired
	private AccountAmountMapper accountAmountMapper;
	
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	
	@Autowired
	private SysLimitMapper sysLimitMapper;
	
	@Autowired
	private LotteryServiceMapper lotteryServiceMapper;
	
	@Autowired
	private LotteryGameDetailMapper lotteryGameDetailMapper;
	
	/**
	 *试玩账户删除
	 * @throws Exception 
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void LotteryPlayerDelete() throws Exception{
		Date lastDay = CommonUtils.getNextDay(new Date());
		Date[] param1 = CommonUtils.getDateTime(lastDay, lastDay);
		System.out.println("Time do delete --"+param1[0]+".."+param1[1]);
		List<LotteryGameRound> list =  lotteryGameRoundMapper.selectLotteryOrderPlayer(param1[0],param1[1]);
		Date openTime = null;
		int[] accoundis = {888,987,988,989,990,991,992,993,994,995,996,997,998,999};
		for (int i = 0;i<list.size();i++){
			LotteryGameRound lr = new LotteryGameRound();
			lr = list.get(i);	
			lotteryGameOrderMapper.deleteByPlayerBatch(lr.getSid(),openTime);
			for (int j = 0;j<accoundis.length;j++){
			    	tradeInfoMapper.deleteByPlayer(accoundis[j]);
			    	accountAmountMapper.deleteByPlayer(accoundis[j]);
			    	lotteryGameDetailMapper.deletePlayer(accoundis[j]);
			    }
		}
	}
		
	/**
	 *是否读取开奖
	 * @throws Exception 
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public  synchronized String[] LotterApi(int sid,String apiUrl) throws Exception{
		LotteryGame lg =  lotteryGameMapper.selectLotteryBySid(sid);
		Date date = new Date();
		String[] result = new String[2];
		//boolean flag = false;
		//if ((sid==1001&&CommonUtils.dateRange())||sid==2001||sid==2002)
			//date = CommonUtils.getChangeDate(20);
		/*
		String result = HttpclientTool.get(apiUrl);
		System.out.println("result.."+result+"..");
		if(StringUtils.isNotBlank(result)&&result.trim().startsWith("{")){
		    JSONObject jObj = new JSONObject(result);
		    JSONArray openArray = jObj.getJSONArray("open");
		    System.out.println("result.e."+openArray.getJSONObject(0).getString("expect")+".."+lg.getGameterm());
			if (!(openArray.getJSONObject(0).getString("expect").equals(String.valueOf(BigInteger.valueOf(Long.valueOf(lg.getGameterm())-1))))){
				flag = true;
				System.out.println("读取sdsd,open:"+flag+".."+openArray.getJSONObject(0).getString("expect"));
			}
		}
		*/
		System.out.println("是否运行状态:"+date.after(lg.getGameovertime())+".."+sid+".."+lg.getGamename()+".."+".."+lg.getGamename().equals("0"));
		if (date.after(lg.getGameovertime())){
			System.out.println("是否最终状态：true");
			result[0] = "true";
			result[1] = lg.getGamename();
			return result;
		}else{
			System.out.println("是否最终状态：false");
			result[0] = "false";
			result[1] = lg.getGamename();
			return result;
		}

	}
	
	/**
	 *公司上庄
	 * @throws Exception 
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public synchronized void taskplayoridle(LotteryGameRound lgr) throws Exception{
		//LOG.info("公司上庄开始："+new Date());
		java.util.Random random=new java.util.Random();// 定义随机类
		//List<LotteryGameRound> list =  lotteryGameRoundMapper.selectLotteryPlayoridle();
		String offtype = "";
		Date currentDate = new Date();
		Date overTime = null;
	    int  noid  = 0;
		overTime = lgr.getOvertime();
		String gametype = "";
		//LOG.info("公司上庄状态："+ls.getAremarksercie());
			if ((currentDate.getTime() - overTime.getTime())/1000>=-40&&((currentDate.getTime() - overTime.getTime())<0)){	
				List<LotteryGameOrder> lists =  lotteryGameOrderMapper.selectGamePlayoridle(lgr.getLotteryterm(), lgr.getSid());
				for (int j = 0;j<lists.size();j++){
	                if (lgr.getSid()==2001||lgr.getSid()==2002){
						offtype = "2";
						noid = 5;
						gametype = "04";
					}else{
						offtype = "1";
						noid = 10;
						gametype = "03";
					}
					SysLimit sys = sysLimitMapper.selectByOrderGs(gametype,offtype);
					LotteryGameOrder la = new LotteryGameOrder();
					la = lists.get(j);
					int result = random.nextInt(noid);
			        LotteryGameOrder order = new LotteryGameOrder();
			        order.setAccountid(1003);
			        order.setSid(lgr.getSid());
			    	order.setRmid(la.getRmid());
			    	order.setLtdid(result+1);
			    	order.setNoid(result+1);
			    	order.setPlayoridle("1");
			    	order.setLotteryterm(lgr.getLotteryterm());
			    	order.setOrderamount(sys.getLimited());
			    	order.setOrdertime(new Date());
			    	order.setOpentime(lgr.getOpentime());
			        lotteryGameOrderMapper.insertSelective(order);
			        lotteryGameOrderMapper.updatePlayOridle(order.getSid(), order.getRmid(), order.getNoid(),order.getLotteryterm());
					}
				}
		//LOG.info("公司上庄结束："+new Date());
	}
	
}
