package com.lottery.orm.dao;

import java.math.BigDecimal;

import com.lottery.orm.bo.SysBene;
import org.apache.ibatis.annotations.Param;

public interface SysBeneMapper {
    int deleteByPrimaryKey(Integer sbid);

    int insert(SysBene record);

    int insertSelective(SysBene record);

    SysBene selectByPrimaryKey(Integer sbid);

    int updateByPrimaryKeySelective(SysBene record);

    int updateByPrimaryKey(SysBene record);
    
    SysBene selectByAmount(@Param("amount")BigDecimal amount);
}