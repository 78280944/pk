package com.lottery.orm.dao;

import java.util.List;

import com.lottery.orm.bo.SysPay;

public interface SysPayMapper {
    int deleteByPrimaryKey(Integer spid);

    int insert(SysPay record);

    int insertSelective(SysPay record);

    SysPay selectByPrimaryKey(Integer spid);

    int updateByPrimaryKeySelective(SysPay record);

    int updateByPrimaryKey(SysPay record);
    
    List<SysPay> getSysPayList();
    
}