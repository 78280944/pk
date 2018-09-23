package com.lottery.orm.dao;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.SysAgency;

public interface SysAgencyMapper {
    int deleteByPrimaryKey(Integer said);

    int insert(SysAgency record);

    int insertSelective(SysAgency record);

    SysAgency selectByPrimaryKey(Integer said);

    int updateByPrimaryKeySelective(SysAgency record);

    int updateByPrimaryKey(SysAgency record);
    
    SysAgency selectByAgency(@Param("accountid")Integer accountid);
}