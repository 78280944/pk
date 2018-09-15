package com.lottery.orm.dao;

import java.util.List;

import com.lottery.orm.bo.SysRatio;

public interface SysRatioMapper {
    int deleteByPrimaryKey(Integer srid);

    int insert(SysRatio record);

    int insertSelective(SysRatio record);

    SysRatio selectByPrimaryKey(Integer srid);

    int updateByPrimaryKeySelective(SysRatio record);

    int updateByPrimaryKey(SysRatio record);
    
    List<SysRatio> selectSysRatio();
}