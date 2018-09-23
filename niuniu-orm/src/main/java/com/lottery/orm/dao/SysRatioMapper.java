package com.lottery.orm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.SysRatio;

public interface SysRatioMapper {
    int deleteByPrimaryKey(Integer srid);

    int insert(SysRatio record);

    int insertSelective(SysRatio record);

    SysRatio selectByPrimaryKey(Integer srid);

    int updateByPrimaryKeySelective(SysRatio record);

    int updateByPrimaryKey(SysRatio record);
    
    List<SysRatio> selectSysRatio();
    
    SysRatio selectSingRatio(@Param("itemno")String itemno);
    
    int updateLotteryterm(@Param("lotteryterm")String lotteryterm);
    
    int updateLotteryRatio(@Param("ratio")BigDecimal ratio,@Param("itemno")String itemno);
}