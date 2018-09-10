package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.dto.AgencyWinReportDto;
import com.lottery.orm.dto.HistoryOrderDto;
import com.lottery.orm.dto.InoutAccReportDto;
import com.lottery.orm.dto.InoutReportDto;
import com.lottery.orm.dto.PlayerWinReportDto;
import com.lottery.orm.dto.TradeReportDto;

public interface LotteryReportMapper {
    
    List<PlayerWinReportDto> selectWinReportByPlayer(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("supUsername")String supUsername,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    List<AgencyWinReportDto> selectWinReportByAgency(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("supUsername")String supUsername,@Param("level")String level,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    List<TradeReportDto> selectByTradeReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("supUsername")String supUsername,@Param("userName")String userName, @Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<InoutReportDto> selectByInoutReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("supUsername")String supUsername,@Param("offType")String offType, @Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
 
    List<HistoryOrderDto> selectByCurRoundOrder(@Param("roundId")Integer roundId, @Param("accountId")Integer accountId);
    
    List<HistoryOrderDto> selectByHistoryOrder(@Param("startTime")Date startTime,@Param("endTime")Date endTime, @Param("accountId")Integer accountId,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<InoutAccReportDto> selectAccInoutReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountId")Integer accountId,@Param("level")String level, @Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<InoutAccReportDto> selectAccInoutReportBytime(@Param("accountId")Integer accountId,@Param("level")String level, @Param("time")String time,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<InoutAccReportDto> selectProInoutReport(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountId")Integer accountId, @Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
}