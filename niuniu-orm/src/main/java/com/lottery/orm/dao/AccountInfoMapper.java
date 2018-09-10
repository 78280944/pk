package com.lottery.orm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountInfo;

public interface AccountInfoMapper {
    

	int deleteByPrimaryKey(Integer serialno);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer serialno);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
    
    int updateAccountState(@Param("state")String state,@Param("supusername")String supusername);
    
    int updateAccountSupuserState(@Param("state")String state,@Param("supusername")String supusername);
  
    //get account info when login
    AccountInfo selectByLogin(AccountInfo record);
    
    AccountInfo selectByLoginPlayer(AccountInfo record);
    
    List<AccountInfo> selectBySupusername(@Param("supusername")String supusername, @Param("beginrow")Integer beginrow, @Param("pageSize")Integer pageSize);
    
    List<AccountInfo> selectAgencyInfo(@Param("accountid")Integer accountid);
    
    AccountInfo selectByUsername(String username);
    
    AccountInfo selectByCodeCount(@Param("accountid")Integer accountid);
    
    List<AccountInfo> selectByCodeNo(@Param("code")String code);
    
    AccountInfo selectByUserAndId(AccountInfo record);
    
    AccountInfo selectByCode(@Param("code")String code, @Param("level")String level, @Param("offtype")String offtype);
    
    AccountInfo selectByUserAndSfcode(@Param("username")String username, @Param("sfcode")String sfcode);
 
    int updateResultAccountMount(@Param("usermoney")BigDecimal usermoney,@Param("accountid")Integer accountid);
    
    int updateOffPercentage(@Param("percentage")Double percentage,@Param("accountid")Integer accountid);
    
    int updateOffState(@Param("state")String state,@Param("accountid")Integer accountid);
}