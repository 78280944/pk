package com.lottery.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.api.dto.TradeInfoVo;
import com.lottery.api.dto.TradeParamVo;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.TradeInfo;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.dto.AccountInfoDto;
import com.lottery.orm.dto.TradeInfoDto;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.result.TradeListResult;
import com.lottery.orm.service.TradeInfoService;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping(value = "/trade", produces = { "application/json;charset=UTF-8" })
@Api(value = "/trade", description = "鍑哄叆閲戞椤规帴鍙�")
@Controller
public class TradeInfoController {
	public static final Logger LOG = Logger.getLogger(TradeInfoController.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	TradeInfoService tradeInfoService;
	
	@Autowired
	AccountRechargeMapper accountRechargeMapper;
	
	/*
	@ApiOperation(value = "鏌ヨ浜ゆ槗璁板綍", notes = "鏌ヨ浜ゆ槗璁板綍", httpMethod = "POST")
	@RequestMapping(value = "/getTradeInfo", method = RequestMethod.POST)
	@ResponseBody
	*/
	public TradeListResult getTradeInfo(@ApiParam(value = "Json鍙傛暟", required = true) @Validated @RequestBody TradeInfoVo param) throws Exception {
		TradeListResult result = new TradeListResult();
		try {
			Date[] param1 = CommonUtils.getDateTime(param.getStarttime(), param.getOvertime());
			List<TradeInfoDto> list  = accountRechargeMapper.selectByTrade(param.getAccountid(),param.getRelativetype(),param1[0],param1[1],Integer.valueOf(param.getBeginRow()),Integer.valueOf(param.getPageSize()));
			if (list.size() == 0){
				result.fail("璇ユ煡璇㈡潯浠朵笅", MessageTool.Code_1010);
				LOG.info(result.getMessage());
				return result;
			}
			result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

}
