package com.lottery.orm.dao;

import com.lottery.orm.bo.ApiUser;

public interface ApiUserMapper {
    int deleteByPrimaryKey(String scratchUser);

    int insert(ApiUser record);

    int insertSelective(ApiUser record);

    ApiUser selectByPrimaryKey(String scratchUser);

    int updateByPrimaryKeySelective(ApiUser record);

    int updateByPrimaryKey(ApiUser record);
}