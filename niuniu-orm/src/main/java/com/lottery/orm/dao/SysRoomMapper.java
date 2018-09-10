package com.lottery.orm.dao;

import java.util.List;

import com.lottery.orm.bo.SysRoom;

public interface SysRoomMapper {
    int deleteByPrimaryKey(Integer srid);

    int insert(SysRoom record);

    int insertSelective(SysRoom record);

    SysRoom selectByPrimaryKey(Integer srid);
    
    List<SysRoom> selectByRoom();

    int updateByPrimaryKeySelective(SysRoom record);

    int updateByPrimaryKey(SysRoom record);
}