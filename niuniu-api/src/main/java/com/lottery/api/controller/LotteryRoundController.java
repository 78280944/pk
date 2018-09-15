package com.lottery.api.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.api.dto.GameCurVo;
import com.lottery.api.dto.GameLobbyVo;
import com.lottery.api.dto.GameVo;
import com.lottery.api.dto.HisRoundParamVo;
import com.lottery.api.dto.ResultParamVo;
import com.lottery.api.dto.RoundParamVo;
import com.lottery.api.dto.UpdateRoundVo;
import com.lottery.orm.bo.LotteryGame;
import com.lottery.orm.bo.LotteryGameRound;
import com.lottery.orm.bo.LotteryRoom;
import com.lottery.orm.bo.LotteryRound;
import com.lottery.orm.dao.CustomLotteryMapper;
import com.lottery.orm.dao.LotteryGameMapper;
import com.lottery.orm.dao.LotteryGameResultsMapper;
import com.lottery.orm.dao.LotteryGameRoundMapper;
import com.lottery.orm.dao.LotteryRoomMapper;
import com.lottery.orm.dao.LotteryRoundMapper;
import com.lottery.orm.dto.LotteryGameCurDto;
import com.lottery.orm.dto.LotteryGameDto;
import com.lottery.orm.dto.LotteryResultDto;
import com.lottery.orm.dto.LotteryRoomPlayerDto;
import com.lottery.orm.dto.ResultDataDto;
import com.lottery.orm.result.BaseRestResult;
import com.lottery.orm.result.GameCurResult;
import com.lottery.orm.result.GameItemResult;
import com.lottery.orm.result.GameResult;
import com.lottery.orm.result.GameRoomResult;
import com.lottery.orm.result.HisRoundResult;
import com.lottery.orm.result.LotteryGameResult;
import com.lottery.orm.result.LotterySidResult;
import com.lottery.orm.result.ResultListResult;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.HttpclientTool;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/*
@RequestMapping(value = "/lottery", produces = { "application/json;charset=UTF-8" })
@Api(value = "/lottery", description = "游戏信息接口")
@Controller
*/
public class LotteryRoundController {
	public static final Logger LOG = Logger.getLogger(LotteryRoundController.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	LotteryRoundMapper lotteryRoundMapper;
	
	@Autowired
	CustomLotteryMapper customLotteryMapper;
	
	@Autowired
	LotteryGameRoundMapper lotteryGameRoundMapper;
	
	@Autowired
	LotteryGameMapper lotteryGameMapper;
	
	@Autowired
	LotteryGameResultsMapper lotteryGameResultsMapper;
	
	@Autowired
	LotteryRoomMapper lotteryRoomMapper;
	
	@ApiOperation(value = "获取游戏类型", notes = "获取游戏类型", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGame", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameResult getLotteryGame() throws Exception {
		GameResult result = new GameResult();
		try {
			List<LotteryGameDto> list = lotteryGameMapper.selectLotteryGame();
            result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@ApiOperation(value = "获取游戏类型大厅", notes = "获取游戏类型大厅", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGameItem", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameItemResult getLotteryGameItem(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody GameVo param) throws Exception {
		GameItemResult result = new GameItemResult();
		try {
			List<LotteryGame> list = lotteryGameMapper.selectLotteryGameItem(param.getGametype());
            result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	@ApiOperation(value = "获取期次游戏结果", notes = "获取期次游戏结果", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryTermResult", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameCurResult getLotteryTermResult(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody GameCurVo param) throws Exception {
		GameCurResult result = new GameCurResult();
		try {
			LotteryGameRound lgr = lotteryGameRoundMapper.selectLotteryGameResult(param.getSid(),param.getLotteryterm());
            result.success(lgr);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@ApiOperation(value = "获取游戏房间", notes = "获取游戏房间", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGameRoom", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameRoomResult getLotteryGameRoom(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody GameLobbyVo param) throws Exception {
		GameRoomResult result = new GameRoomResult();
		try {
			int len = 0;
			if (param.getType()==1)
				len = 7;
			else if (param.getType()==2)
				len = 8;
			List<LotteryRoomPlayerDto> list = lotteryRoomMapper.selectLotteryGameRoom(param.getSid(),len);
            result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	@ApiOperation(value = "获取游戏结果", notes = "获取游戏结果", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryResult", method = RequestMethod.POST)
	@ResponseBody
	public synchronized ResultListResult getLotteryResult(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody ResultParamVo param) throws Exception {
		ResultListResult result = new ResultListResult();
		
		try {
			//List<ResultDataDto> list = lotteryRoundService.getLotteryResult(param.getStartDate(), param.getEndDate(), param.getSid(), param.getBeginRow(), param.getPageSize());
			List<ResultDataDto> roundList = new ArrayList<ResultDataDto>();
			Date[] sTime = CommonUtils.getDateTime(param.getStartDate(), param.getEndDate());
			roundList = lotteryGameRoundMapper.selectGameResult(sTime[0], sTime[1], param.getSid(), param.getBeginRow(), param.getPageSize());
			result.success(roundList);
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@ApiOperation(value = "获取游戏当期接口结果", notes = "获取游戏当期接口结果", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryCurResult", method = RequestMethod.POST)
	@ResponseBody
	public synchronized LotterySidResult getLotteryCurResult(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody GameLobbyVo param) throws Exception {
		LotterySidResult result = new LotterySidResult();	
		try {
			//LotteryGameCurDto lgc = lotteryRoundService.getLotteyCurResult(param.getSid());
			List<LotteryGameRound> list = lotteryGameRoundMapper.selectLotteryOrderResult(param.getSid());
			LotteryGameCurDto lgc = new LotteryGameCurDto();
			for (int i = 0;i<list.size();i++){
				LotteryGameRound lgr = new LotteryGameRound();
				lgr = list.get(i);
				if (i==0){
					lgc.setSid(lgr.getSid());
					lgc.setStarttime(lgr.getStarttime());
					lgc.setOvertime(lgr.getOvertime());
					lgc.setOpentime(lgr.getOpentime());
					lgc.setLotteryterm(lgr.getLotteryterm());
				}
				if (i==1){
					lgc.setLastlotteryterm(lgr.getLotteryterm());
					lgc.setLastlotteryresult(lgr.getLotteryresult());
				}
			}
			result.success(lgc);
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@ApiOperation(value = "获取游戏主界面结果", notes = "获取游戏主界面结果", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGameResults", method = RequestMethod.POST)
	@ResponseBody
	public  synchronized LotteryGameResult getLotteryGameResults(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody GameCurVo param) throws Exception {
		LotteryGameResult result = new LotteryGameResult();	
		try {
			List<LotteryResultDto> list = lotteryGameResultsMapper.selectSidGameResult(param.getSid(), param.getLotteryterm());
			result.success(list);
		} catch (Exception e) {
			result.fail(MessageTool.ErrorCode);
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
}
