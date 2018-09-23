package com.lottery.orm.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.AccountAmount;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.LotteryGameOrder;
import com.lottery.orm.bo.OffAccountInfo;
import com.lottery.orm.bo.TradeInfo;
import com.lottery.orm.dao.AccountAmountMapper;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.OffAccountInfoMapper;
import com.lottery.orm.dao.TradeInfoMapper;
import com.lottery.orm.result.TradeListResult;
import com.lottery.orm.util.EnumType;

@Service
@Transactional
public class TradeInfoService {

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    @Autowired
    private AccountDetailMapper accountDetailMapper;
    
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    
    @Autowired
	private AccountAmountMapper accountAmountMapper;
    
  /*
    // 添加出入金款项并更新账户
    public String addInoutTradeInfo(TradeInfo tradeInfo) {
	    AccountDetail supAccountDetail = accountDetailMapper.selectByPrimaryKey(tradeInfo.getRelativeid());
		AccountDetail accountDetail = accountDetailMapper.selectByPrimaryKey(tradeInfo.getAccountid());
		Double supAccountAmount = supAccountDetail.getMoney()==null?0.0:supAccountDetail.getMoney().doubleValue();
		Double accountAmount = accountDetail.getMoney()==null?0.0:accountDetail.getMoney().doubleValue();
		Double accountBudget = accountDetail.getBudget()==null?0.0:accountDetail.getBudget();
		
		Double tradeAmount = 0.0;//交易点数
		Double budgetAmount = 0.0;//预算款
		if(tradeInfo.getRelativetype().equals(EnumType.RalativeType.In.ID)){
			if(supAccountAmount>=tradeInfo.getTradeamount()||supAccountDetail.getOfftype().equals(EnumType.OffType.Admin.ID)){
				if(accountDetail.getOfftype().equals(EnumType.OffType.Agency.ID)){
					budgetAmount = tradeInfo.getTradeamount()*accountDetail.getPercentage();
					accountBudget = accountBudget + budgetAmount;
				}
				supAccountAmount = supAccountAmount - tradeInfo.getTradeamount();
			    accountAmount = accountAmount + tradeInfo.getTradeamount();
			    tradeAmount = tradeInfo.getTradeamount();
			}else{
				return "您帐户的点数小于上分的点数,无法给下级进行上分!";
			}
		}else if(tradeInfo.getRelativetype().equals(EnumType.RalativeType.Out.ID)){
			if(accountAmount>=tradeInfo.getTradeamount()){
				if(accountDetail.getOfftype().equals(EnumType.OffType.Agency.ID)){
					budgetAmount = 0.0 - tradeInfo.getTradeamount()*accountDetail.getPercentage();
					accountBudget = accountBudget + budgetAmount;
				}
				supAccountAmount = supAccountAmount + tradeInfo.getTradeamount();
			    accountAmount = accountAmount - tradeInfo.getTradeamount();
			    tradeAmount = 0.0 - tradeInfo.getTradeamount();
			}else{
				return "下级帐户的点数小于退分的点数,无法给下级进行退分!";
			}
		}else{
			return "不支持该交易类型";
		}
		tradeInfo.setTradeamount(tradeAmount);//转换为负数
		tradeInfo.setBudgetamount(budgetAmount);
		tradeInfo.setAccountamount(new BigDecimal(accountAmount));
		tradeInfo.setTradetype(EnumType.TradeType.Inout.ID);
		tradeInfo.setInputtime(new Date());
		
		if (tradeInfoMapper.insertSelective(tradeInfo) > 0) {
			accountDetail.setBudget(accountBudget);
			accountDetail.setMoney(new BigDecimal(accountAmount));
		    accountDetailMapper.updateByPrimaryKeySelective(accountDetail);
		    supAccountDetail.setMoney(new BigDecimal(supAccountAmount));
		    accountDetailMapper.updateByPrimaryKeySelective(supAccountDetail);
		    return "";
		}else{
			return "新增点数出入记录失败!";
		}
		
    }
    */
    // 添加出入金款项并更新账户
    
    public synchronized String addInoutTradeInfo(TradeInfo tradeInfo,Integer sid,String lotteryterm) {
    	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(tradeInfo.getAccountid());
    	tradeInfo.setAccountamount(aInfo.getUsermoney().add(BigDecimal.valueOf(tradeInfo.getTradeamount())));
    	tradeInfoMapper.insertSelective(tradeInfo);
    	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf(tradeInfo.getTradeamount())));
    	accountInfoMapper.updateByPrimaryKeySelective(aInfo);
    	AccountAmount aa = accountAmountMapper.selectByAgency(aInfo.getAccountid(), sid, lotteryterm);
    	if (null == aa){
    		AccountAmount aas = new AccountAmount();
    		aas.setAccountid(aInfo.getAccountid());
    		aas.setSid(sid);
    		aas.setLotteryterm(lotteryterm);
    		aas.setLoss(BigDecimal.valueOf(tradeInfo.getTradeamount()));
    		aas.setStarttime(new Date());
    		aas.setOvertime(new Date());
    		accountAmountMapper.insert(aas);
    	}else{
    		accountAmountMapper.updateWinAmount(aInfo.getAccountid(), sid, lotteryterm, BigDecimal.valueOf(tradeInfo.getTradeamount()));
    	}
    	//accountAmountMapper.updateWinAmount(tradeInfo.getAccountid(),sid, lotteryterm,BigDecimal.valueOf(tradeInfo.getTradeamount()));
    	accountInfoMapper.updateResultAccountMount(BigDecimal.valueOf(0.0).subtract(BigDecimal.valueOf(tradeInfo.getTradeamount())), 1000);
    	return "sucess";
    }
    
    //代理
    public synchronized String addAgencyTradeInfo(TradeInfo tradeInfo,Integer sid,String lotteryterm) {
    	//System.out.println("78-----------"+tradeInfo.getAccountid());
    	List<AccountInfo> aInfo = accountInfoMapper.selectAgencyInfo(tradeInfo.getAccountid());
    	//System.out.println("78-----------"+aInfo.size());
    	TradeInfo tradeInfos = tradeInfo;
    	Double amount = tradeInfo.getTradeamount();
    	for (int i = 0;i<aInfo.size();i++){
    		AccountInfo ac = new AccountInfo();
    		ac = aInfo.get(i);
    		accountInfoMapper.updateAgencyAccountMount(BigDecimal.valueOf(amount*Double.valueOf(ac.getOfftype())), ac.getAccountid());
    		tradeInfos.setRelativeid(EnumType.RalativeType.AgencyWin.NOID);
    		tradeInfos.setRelativetype(EnumType.RalativeType.AgencyWin.ID);
			tradeInfos.setAccountid(ac.getAccountid());
			tradeInfos.setAccountamount(ac.getUsermoney());
			
			tradeInfos.setTradeamount(amount*Double.valueOf(ac.getOfftype()));
			//System.out.println("78----23--rrr-----"+tradeInfos.getAccountid()+".."+tradeInfos.getTradeamount()+".."+tradeInfos.getAccountamount());
			tradeInfos.setRemark("代理返现，accountid:"+tradeInfo.getAccountid()+",游戏号:"+sid+",游戏期数:"+lotteryterm);
	    	tradeInfoMapper.insertSelective(tradeInfos);
	        AccountAmount aa = new AccountAmount();
	        aa.setAccountid(ac.getAccountid());
	        aa.setSid(sid);
	        aa.setLotteryterm(lotteryterm);
	        aa.setEarns(BigDecimal.valueOf(amount*Double.valueOf(ac.getOfftype())));
	        aa.setStarttime(new Date());
	        aa.setOvertime(new Date());
	    	//System.out.println("78------rrr-----"+ac.getAccountid()+".."+aa.getAccountid()+".."+aa.getEarns());
	        AccountAmount aAmount = accountAmountMapper.selectByAgency(ac.getAccountid(), sid, lotteryterm);
	        if (null == aAmount)
	            accountAmountMapper.insert(aa);
	        else{
	        	aAmount.setEarns(((null==aAmount.getEarns())?BigDecimal.valueOf(0.0):aAmount.getEarns()).add(aa.getEarns()));
	        	accountAmountMapper.updateByPrimaryKey(aAmount);
	        }
	        accountInfoMapper.updateAgencyAccountMount(BigDecimal.valueOf(0.0).subtract(BigDecimal.valueOf(amount*Double.valueOf(ac.getOfftype()))), 1000);
    	}
    	return "sucess";
    }
    
    
    //代理金款项并更新账户
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public synchronized String addAgencyTradeInfo(TradeInfo tradeInfo,Double fee,int sid,String lotteryterm,Double comission,AccountAmount accAmount) {
    	List<AccountInfo> aInfo = accountInfoMapper.selectAgencyInfo(tradeInfo.getAccountid());
    	Double cfee = 0.0;
    	Double profits = 0.0;
    	Double percentage = 0.0;
    	Double tradeamount = tradeInfo.getTradeamount().doubleValue();
    	//System.out.println("12--------------"+aInfo.size()+"..."+tradeamount+".."+fee);
    	for (int i = 0;i<aInfo.size();i++){
    		AccountInfo ac = new AccountInfo();
    		ac = aInfo.get(i);
    		cfee =  (Math.abs(tradeamount)+fee)/2*comission;
    		profits = cfee * (ac.getPercentage() - percentage);
    		percentage = ac.getPercentage();
    		if (i+1 != aInfo.size()){
    			accountInfoMapper.updateResultAccountMount(BigDecimal.valueOf(profits), ac.getAccountid());
    			tradeInfo.setAccountid(ac.getAccountid());
    			tradeInfo.setAccountamount(ac.getUsermoney().add(BigDecimal.valueOf(profits)));
    			tradeInfo.setTradeamount(profits);
    			tradeInfo.setRemark("代理返现，accountid:"+tradeInfo.getAccountid()+",游戏号:"+sid+",游戏期数:"+lotteryterm);
    	    	tradeInfoMapper.insertSelective(tradeInfo);
		        AccountAmount aa = new AccountAmount();
		        aa.setAccountid(ac.getAccountid());
		        aa.setSid(sid);
		        aa.setLotteryterm(lotteryterm);
		        aa.setLoss(accAmount.getLoss());
		        aa.setEarns(accAmount.getEarns());
		        aa.setGains(accAmount.getGains());
		        aa.setCfee(BigDecimal.valueOf(cfee));
		        aa.setProfits(BigDecimal.valueOf(profits));
		        aa.setStarttime(new Date());
		        aa.setOvertime(new Date());
		        accountAmountMapper.insert(aa);
    	    	
    		}else{
    			accountInfoMapper.updateResultAccountMount(BigDecimal.valueOf(profits), 1000);
    			tradeInfo.setAccountid(1000);
    			tradeInfo.setAccountamount(ac.getUsermoney().add(BigDecimal.valueOf(profits)));
    			tradeInfo.setTradeamount(profits);
    			tradeInfo.setRemark("总代理返现，accountid:"+tradeInfo.getAccountid()+",游戏号:"+sid+",游戏期数:"+lotteryterm);
    	    	tradeInfoMapper.insertSelective(tradeInfo);
		        AccountAmount aa = new AccountAmount();
		        aa.setAccountid(1000);
		        aa.setSid(sid);
		        aa.setLotteryterm(lotteryterm);
		        aa.setLoss(accAmount.getLoss());
		        aa.setEarns(accAmount.getEarns());
		        aa.setGains(accAmount.getGains());
		        aa.setCfee(BigDecimal.valueOf(cfee));
		        aa.setProfits(BigDecimal.valueOf(profits));
		        aa.setStarttime(new Date());
		        aa.setOvertime(new Date());
		        accountAmountMapper.insert(aa);
    	    	
    		}
    	}
    	return "sucess";
    }
    
    //查询
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public List<TradeInfo> selectByTrade(String relativeType,String startTime,String overTime,int beginRow,int pageSize) {
    	List<TradeInfo> list = tradeInfoMapper.selectByTrade(relativeType, startTime, overTime, beginRow, pageSize);
        return list;
    }
	  
    public Date getStrtoDate(String dateString){ 
    	Date date = null;
    	try  
    	{  
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	    date = sdf.parse(dateString);  
    	    return date;
    	}  
    	catch (ParseException e)  
    	{  
    	    System.out.println(e.getMessage());  
    	} 
    	return date;
    }
    
    public static void main(String args[]){
    	TradeInfoService ts = new TradeInfoService();
    	//System.out.println(ts.getStrtoDate("2017-10-6"));
    	//Unparseable date: "2017-03-10"
    	//Unparseable date: "2017-09-20"
    	
    }
    
}
