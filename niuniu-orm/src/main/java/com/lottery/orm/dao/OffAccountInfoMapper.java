package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.OffAccountInfo;
import com.lottery.orm.dto.OffsAccountDto;
import com.lottery.orm.dto.SubsAccountDto;

public interface OffAccountInfoMapper {
    
	int deleteByPrimaryKey(Integer serialno);

    int insert(OffAccountInfo record);

    int insertSelective(OffAccountInfo record);

    OffAccountInfo selectByPrimaryKey(Integer serialno);

    int updateByPrimaryKeySelective(OffAccountInfo record);

    int updateByPrimaryKey(OffAccountInfo record);
    
    int updateOffAccountState(OffAccountInfo record);
    
    //get account info when login
    OffAccountInfo selectByLogin(OffAccountInfo record);
    
    OffAccountInfo selectByUsername(String username);
    
    OffAccountInfo selectByUserAndId(OffAccountInfo record);
    
    List<OffAccountInfo> selectBySupusername(@Param("supusername")String supusername, @Param("offtype")String offtype,@Param("beginrow")Integer beginrow, @Param("pageSize")Integer pageSize);
   
    List<OffsAccountDto> selectOffSupuserId(@Param("supuserid")Integer supuserid, @Param("offtype")String offtype,@Param("beginrow")Integer beginrow, @Param("pageSize")Integer pageSize);
    
    List<SubsAccountDto> selectSubSupuserId(@Param("supuserid")Integer supuserid, @Param("offtype")String offtype,@Param("beginrow")Integer beginrow, @Param("pageSize")Integer pageSize);
    
    List<OffAccountInfo> selectBySupuserAndRatio(@Param("supusername")String supusername, @Param("offtype")String offtype);
    
    List<OffAccountInfo> selectBySupuserAndPer(@Param("supusername")String supusername, @Param("offtype")String offtype);
    
    OffAccountInfo selectByUseridAndType(@Param("userid")Integer userid, @Param("offtype")String offtype);
    
    List<OffsAccountDto> selectAccSupuserId(@Param("supuserid")Integer supuserid, @Param("offtype")String offtype,@Param("beginrow")Integer beginrow, @Param("pageSize")Integer pageSize);
}