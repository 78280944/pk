package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.dto.TradeInfoDto;

public interface AccountRechargeMapper {

	int deleteByPrimaryKey(Integer arid);

    int insert(AccountRecharge record);

    int insertSelective(AccountRecharge record);

    AccountRecharge selectByPrimaryKey(Integer arid);

    int updateByPrimaryKeySelective(AccountRecharge record);

    int updateByPrimaryKey(AccountRecharge record);
   
    int updateByRechargeMessage(AccountRecharge record);
    
    int updateByRechargeCashReady(AccountRecharge record);
    
    int updateByRechargeCashResult(AccountRecharge record);
    
    List<AccountRecharge> selectByTime(@Param("orderdate")String orderdate,@Param("relativetype")String relativetype,@Param("accountid")Integer accountid);
    
    List<AccountRecharge> selectByOutResult();
    
    AccountRecharge selectByOrderNo(@Param("orderno")String orderno,@Param("relativetype")String relativetype,@Param("accountid")Integer accountid);
    
    AccountRecharge selectByOrderNoReturn(@Param("orderno")String orderno,@Param("relativetype")String relativetype);
    
    List<TradeInfoDto> selectByTrade(@Param("accountid")Integer accountid,@Param("relativetype")String relativetype,@Param("starttime")Date starttime,@Param("overtime")Date overtime,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
}