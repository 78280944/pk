package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.SysLimit;

public interface SysLimitMapper {
  
	int deleteByPrimaryKey(Integer slid);

    int insert(SysLimit record);

    int insertSelective(SysLimit record);

    SysLimit selectByPrimaryKey(Integer slid);

    int updateByPrimaryKeySelective(SysLimit record);

    int updateByPrimaryKey(SysLimit record);
    
    SysLimit selectByOrder(@Param("gametype")String gametype,@Param("offtype")String offtype);
    
    SysLimit selectByOrderGs(@Param("gametype")String gametype,@Param("offtype")String offtype);
    
    List<SysLimit> selectByLimit();
}