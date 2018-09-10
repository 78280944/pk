package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.NoticeInfo;

public interface NoticeInfoMapper {
    int deleteByPrimaryKey(Integer noticeid);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    NoticeInfo selectByPrimaryKey(Integer noticeid);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);
    
    NoticeInfo selectByNotice(String offtype);
    
    List<NoticeInfo> selectByHisNotice(@Param("offtype")String offtype,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
} 