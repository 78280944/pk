package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryOrderRecord;

public interface LotteryOrderRecordMapper {
    int deleteByPrimaryKey(Integer lorid);

    int insert(LotteryOrderRecord record);

    int insertSelective(LotteryOrderRecord record);

    LotteryOrderRecord selectByPrimaryKey(Integer lorid);

    int updateByPrimaryKeySelective(LotteryOrderRecord record);

    int updateByPrimaryKey(LotteryOrderRecord record);
    
    LotteryOrderRecord selectByKeyValue(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
    List<LotteryOrderRecord> selectValueList();
    
    LotteryOrderRecord selectValueByLottery(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
}