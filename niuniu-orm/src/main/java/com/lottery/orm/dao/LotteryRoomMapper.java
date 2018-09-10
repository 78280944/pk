package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryRoom;
import com.lottery.orm.dto.LotteryRoomPlayerDto;

public interface LotteryRoomMapper {
    int deleteByPrimaryKey(Integer rmid);

    int insert(LotteryRoom record);

    int insertSelective(LotteryRoom record);

    LotteryRoom selectByPrimaryKey(Integer rmid);

    int updateByPrimaryKeySelective(LotteryRoom record);

    int updateByPrimaryKey(LotteryRoom record);
    
    List<LotteryRoomPlayerDto> selectLotteryGameRoom(@Param("sid")Integer sid,@Param("len")Integer len);
    
    List<LotteryRoom> selectDistinctSid(@Param("sid")Integer sid);
    
    LotteryRoomPlayerDto selectLotteryRoomCount(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm,@Param("rmid")Integer rmid);
    
    List<LotteryRoom> selectDistinctSysSid(@Param("sid")Integer sid,@Param("first")Integer first,@Param("second")Integer second);
    
}