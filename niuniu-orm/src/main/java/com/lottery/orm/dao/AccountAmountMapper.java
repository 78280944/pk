package com.lottery.orm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountAmount;
import com.lottery.orm.dto.AccAmountDto;
import com.lottery.orm.dto.ProAccAmountDto;
import com.lottery.orm.bo.AccountAmountKey;

public interface AccountAmountMapper {
   

	int deleteByPrimaryKey(Integer aaid);
	
	int deleteByPlayer(Integer accountid);

    int insert(AccountAmount record);

    int insertSelective(AccountAmount record);

    AccountAmount selectByPrimaryKey(Integer aaid);

    int updateByPrimaryKeySelective(AccountAmount record);

    int updateByPrimaryKey(AccountAmount record);
    
    List<ProAccAmountDto> selectProWinReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountid")Integer accountid,@Param("level")String level,@Param("offtype")String offtype,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<ProAccAmountDto> selectProWinReportBytime(@Param("accountid")Integer accountid,@Param("level")String level,@Param("time")String time,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
   
    List<AccAmountDto> selectAccWinReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountid")Integer accountid,@Param("level")String level,@Param("offtype")String offtype,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
   
    List<AccAmountDto> selectAccWinReportBytime(@Param("accountid")Integer accountid,@Param("time")String time,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
	
   
}