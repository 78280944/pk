package com.lottery.orm.dao;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.SysCom;

public interface SysComMapper {
    int deleteByPrimaryKey(Integer scid);

    int insert(SysCom record);

    int insertSelective(SysCom record);

    SysCom selectByPrimaryKey(Integer scid);

    int updateByPrimaryKeySelective(SysCom record);

    int updateByPrimaryKey(SysCom record);
    
    SysCom selectByGameType(@Param("gametype")String gametype);

}