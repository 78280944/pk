package com.lottery.orm.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryGameOrder;
import com.lottery.orm.dto.AddLotteryAmountDto;
import com.lottery.orm.dto.LotteryAmountDto;
import com.lottery.orm.dto.LotteryNoidDto;
import com.lottery.orm.dto.ResultAmountDto;
import com.lottery.orm.dto.RoomAmountDto;
import com.lottery.orm.dto.RoomHisOrderDto;
import com.lottery.orm.dto.RoomOrderDetailDto;
import com.lottery.orm.dto.RoomOrderItemDto;
import com.lottery.orm.dto.RoomOrderDto;

public interface LotteryGameOrderMapper {

	int deleteByPrimaryKey(Integer lgmid);
	
	int deleteByPlayerBatch(@Param("sid")Integer sid,@Param("opentime")Date opentime);
	
	int deleteByPlayerOrder(Integer accountid);

    int insert(LotteryGameOrder record);

    int insertSelective(LotteryGameOrder record);
    
    int updateWinAmount(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("noid")Integer noid);
    
    List<LotteryGameOrder> selectWinAmount(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("noid")Integer noid);

    LotteryGameOrder selectByPrimaryKey(Integer lgmid);

    int updateByPrimaryKeySelective(LotteryGameOrder record);

    int updateByPrimaryKey(LotteryGameOrder record);
    
    List<RoomOrderDto> selectGameOrder(@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
   
    List<RoomHisOrderDto> selectGameHisOrder(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    List<RoomHisOrderDto> selectGameHisAllOrder(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<RoomHisOrderDto> selectGameHisOrderBytime(@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("time")String time,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<RoomOrderItemDto> selectGameOrderItem(@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("rmid")Integer rmid,@Param("lotteryTerm")String lotteryTerm); 

    List<RoomAmountDto> selectGameAmount(@Param("rmid")Integer rmid,@Param("lotteryterm")String lotteryterm);
    
    ResultAmountDto selectGameHisAmount(@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
    
    ResultAmountDto selectGameAllHisAmount(@Param("accountid")Integer accountid,@Param("sid")Integer sid,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
     
    RoomOrderDto selectAccountIdOrder(@Param("accountid")Integer accountid);

    RoomOrderDto selectNoIdOrder(@Param("accountid")Integer accountid,@Param("rmid")Integer rmid,@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("noid")Integer noid);
    
    List<LotteryNoidDto> selectGameNoid(@Param("sid")Integer sid,@Param("rmid")Integer rmid,@Param("lotteryterm")String lotteryterm);
	
    int updatePlayOridle(@Param("sid")Integer sid,
			@Param("rmid")Integer rmid,@Param("noid")Integer noid,@Param("lotteryterm")String lotteryterm);
    
    List<LotteryAmountDto> selectGameAmountResult(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
    List<LotteryAmountDto> selectGameAmountResult1(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
    
    List<LotteryAmountDto> selectGameAmountMoreResult(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
    List<LotteryAmountDto> selectGameAmountLessResult(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
    List<LotteryAmountDto> selectGameAmountEqualResult(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
    List<LotteryAmountDto> selectGameAmountNoidResult(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid,@Param("noid")Integer noid);
    
    List<LotteryAmountDto> selectGameIsOrNotBank(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid,@Param("rmid")Integer rmid);
   
    List<LotteryGameOrder> selectGamePlayoridle(@Param("lotteryterm")String lotteryterm,@Param("sid")Integer sid);
    
    int updateOrderResult(@Param("lgmid")Integer lgmid,@Param("opentime")Date opentime,@Param("result")String result,@Param("lastamount")BigDecimal lastamount);
    
    List<LotteryGameOrder> selectGameRmid(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
    List<AddLotteryAmountDto> selectByResultValue(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("rmid")Integer rmid);
    
    List<LotteryGameOrder> selectByNoResultValue(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
    List<LotteryGameOrder> checkPlayOridle(@Param("sid")Integer sid,@Param("rmid")Integer rmid,@Param("lotteryterm")String lotteryterm);
    
    List<RoomOrderDetailDto> selectGameAmountDetail(@Param("rmid")Integer rmid,@Param("lotteryterm")String lotteryterm);
    
    List<LotteryGameOrder> selectAddAccountIds(@Param("sid")Integer sid,@Param("rmid")Integer rmid,@Param("noid")Integer noid,@Param("lotteryterm")String lotteryterm,@Param("accountids")List<Integer> accountids);
    
    List<LotteryGameOrder> selectSumOrder(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("noid1")Integer noid1,@Param("noid2")Integer noid2,@Param("asc1")String asc1);
    
    List<LotteryGameOrder> selectNewRatio();
    
}