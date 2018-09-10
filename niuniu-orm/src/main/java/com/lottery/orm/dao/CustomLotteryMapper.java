package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.LotteryItem;
import com.lottery.orm.bo.LotteryOrder;
import com.lottery.orm.bo.LotteryRound;

public interface CustomLotteryMapper {
    
    List<Map<String, String>> selectOrderForCheck(@Param("roundId")Integer roundId, @Param("accountId")Integer accountId);
    
    List<AccountDetail> selectAccountBySupUserName(@Param("supUserName")String supUserName);
    
    List<LotteryItem> selectItemByLottery(@Param("lotteryType")String lotteryType);
    
    List<LotteryOrder> selectOrderByRoundId(@Param("roundId")Integer roundId);
    
    LotteryOrder selectOrderByOrderId(@Param("orderId")Integer orderId);
    
    Integer selectCurrentRoundId(@Param("lotteryType")String lotteryType);
    
    LotteryRound selectRoundByRoundId(@Param("roundId")Integer roundId);
    
    LotteryRound selectRoundByTypeAndTerm(@Param("lotteryType")String lotteryType, @Param("lotteryTerm")String lotteryTerm);
    
    List<LotteryRound> selectByHistoryRound(@Param("lotteryType")String lotteryType, @Param("roundStatus")String roundStatus ,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
    
    List<LotteryRound> selectRoundByTime(@Param("lotteryType")String lotteryType, @Param("closeTime")Date closeTime );
    
    
}