package com.lottery.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.api.dto.CurResultParamVo;
import com.lottery.api.dto.EndOrderVo;
import com.lottery.api.dto.HisOrderVo;
import com.lottery.api.dto.LotteryGameDetailVO;
import com.lottery.api.dto.OrderDetailVo;
import com.lottery.api.dto.OrderParamVo;
import com.lottery.api.dto.ReportParamVo;
import com.lottery.api.dto.ResultAmountVo;
import com.lottery.api.dto.ResultParamVo;
import com.lottery.api.dto.RoomAmountVo;
import com.lottery.api.dto.RoomOrderVo;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.LotteryGameOrder;
import com.lottery.orm.bo.LotteryGameRound;
import com.lottery.orm.bo.LotteryItem;
import com.lottery.orm.bo.LotteryOrder;
import com.lottery.orm.bo.LotteryOrderDetail;
import com.lottery.orm.bo.LotteryRound;
import com.lottery.orm.bo.SysLimit;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.CustomLotteryMapper;
import com.lottery.orm.dao.LotteryGameDetailMapper;
import com.lottery.orm.dao.LotteryGameOrderMapper;
import com.lottery.orm.dao.LotteryGameRoundMapper;
import com.lottery.orm.dao.LotteryOrderMapper;
import com.lottery.orm.dao.LotteryReportMapper;
import com.lottery.orm.dao.LotteryRoomMapper;
import com.lottery.orm.dao.LotteryRoundMapper;
import com.lottery.orm.dao.SysLimitMapper;
import com.lottery.orm.dto.HistoryOrderDto;
import com.lottery.orm.dto.LotteryGameDetailDto;
import com.lottery.orm.dto.LotteryNoidDto;
import com.lottery.orm.dto.LotteryOrderDto;
import com.lottery.orm.dto.LotteryRoomPlayerDto;
import com.lottery.orm.dto.ResultAmountDto;
import com.lottery.orm.dto.RoomAmountDto;
import com.lottery.orm.dto.RoomHisOrderDto;
import com.lottery.orm.dto.RoomOrderDetailDto;
import com.lottery.orm.dto.RoomOrderDto;
import com.lottery.orm.dto.RoomOrderItemDto;
import com.lottery.orm.result.BaseRestResult;
import com.lottery.orm.result.GameOrderGrListResult;
import com.lottery.orm.result.GameOrderList;
import com.lottery.orm.result.GameOrderListResult;
import com.lottery.orm.result.HistoryOrderResult;
import com.lottery.orm.result.LotteryGameDetailResult;
import com.lottery.orm.result.LotteryNoidResult;
import com.lottery.orm.result.OrderAmountResult;
import com.lottery.orm.result.OrderListResult;
import com.lottery.orm.result.OrderResult;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.result.RoomAmountResult;
import com.lottery.orm.result.RoomOrderDetaiResult;
import com.lottery.orm.service.LotteryOrderService;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping(value = "/order", produces = { "application/json;charset=UTF-8" })
@Api(value = "/order", description = "投注信息接口")
@Controller
public class LotteryOrderController {
	public static final Logger LOG = Logger.getLogger(LotteryOrderController.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	private LotteryOrderService  lotteryOrderService;
	
	@Autowired
	private LotteryOrderMapper lotteryOrderMapper;

	@Autowired
	private LotteryRoundMapper lotteryRoundMapper;
	
	@Autowired
	private CustomLotteryMapper customLotteryMapper;
	
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	
	@Autowired
	private LotteryReportMapper reportLotteryMapper;
	
	@Autowired
	private LotteryGameOrderMapper lotteryGameOrderMapper;

	@Autowired
	private AccountInfoMapper accountInfoMapper;
	
	@Autowired
	private SysLimitMapper sysLimitMapper;
	
	@Autowired
	private LotteryGameDetailMapper lotteryGameDetailMapper;
	
	@Autowired
	private LotteryGameRoundMapper lotteryGameRoundMapper;
	
	@Autowired
	private LotteryRoomMapper lotteryRoomMapper;
	
	@ApiOperation(value = "新增投注记录", notes = "新增投注记录", httpMethod = "POST")
	@RequestMapping(value = "/addLotteryOrder", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult addLotteryOrder(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody OrderParamVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			
			LotteryGameOrder order = mapper.map(param, LotteryGameOrder.class);
			//System.out.println("投注开始时间------------------"+new Date());
			LOG.info("投注开始时间------------------"+new Date()+".."+param.getAccountId());
			LotteryGameRound lgr = lotteryGameRoundMapper.selectLotteryGameResult(order.getSid(), order.getLotteryterm());
			if (lgr.getOvertime().getTime()<(new java.util.Date()).getTime()){
				result.fail("该游戏已封盘");
				return result;
			}
			AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey(order.getAccountid());
			if (accountInfo == null){
				result.fail(MessageTool.Code_3001);
				return result;
			}

			for (OrderDetailVo orderDetailVo : param.getOrderDetails()) {
				order.setOrdertime(new Date());
				order.setNoid(orderDetailVo.getNoId());
				order.setOrderamount(orderDetailVo.getOrderAmount());
				
				//投注检查
				if((order.getOrderamount()).compareTo(accountInfo.getUsermoney())>0){
					result.fail("下注金额不能超过账户金额");
					return result;
				}

				//账户变动
				lotteryOrderService.changeAccountAmount(accountInfo, order);
				
				order.setResult(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()));
				//投注
				lotteryOrderService.insertLotteryGameOrder(order);
				
			}
			//System.out.println("投注结束时间------------------"+new Date());
			LOG.info("投注结束时间------------------"+new Date()+".."+param.getAccountId());
			result.success();
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
    /*
	@ApiOperation(value = "获取投注明细", notes = "获取投注明细", httpMethod = "POST")
	@RequestMapping(value = "/getOrderItem", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameOrderGrListResult getOrderItem(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody RoomOrderVo param) throws Exception {
		GameOrderGrListResult  result =  new GameOrderGrListResult();
		try { 	
			List<RoomOrderItemDto> list = lotteryOrderService.selectGameOrderItem(param.getLotteryterm(), param.getSid(), param.getRmid(), param.getAccountid());
			result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	
	
	@ApiOperation(value = "获取本期注单", notes = "获取本期注单", httpMethod = "POST")
	@RequestMapping(value = "/getCurTermOrder", method = RequestMethod.POST)
	@ResponseBody
	public synchronized GameOrderListResult getCurTermOrder(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody CurResultParamVo param) throws Exception {
		GameOrderListResult result = new GameOrderListResult();
		try {

			List<RoomOrderDto> orderList = lotteryGameOrderMapper.selectGameOrder(param.getAccountid(), param.getSid(), param.getBeginRow(), param.getPageSize());
			result.success(orderList);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	
	@ApiOperation(value = "获取游戏桌台人数及庄台", notes = "获取游戏桌台人数及庄台", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGameNoid", method = RequestMethod.POST)
	@ResponseBody
	public synchronized LotteryNoidResult getLotteryGameNoid(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody RoomOrderVo param) throws Exception {
		LotteryNoidResult result = new LotteryNoidResult();
		try {
			List<LotteryNoidDto> orderList = lotteryGameOrderMapper.selectGameNoid(param.getSid(), param.getRmid(),param.getLotteryterm());
			//System.out.println("12-------------"+param.getSid()+".."+param.getRmid()+".."+param.getLotteryterm()+".."+orderList.size());
			
			result.success(orderList);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	*/
	@ApiOperation(value = "获取历史注单", notes = "获取历史注单", httpMethod = "POST")
	@RequestMapping(value = "/getHisOrder", method = RequestMethod.POST)
	@ResponseBody
	public synchronized OrderListResult getHisOrder(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody ResultParamVo param) throws Exception {
		OrderListResult result = new OrderListResult();
		try {
			Date[] param1 = CommonUtils.getDateTime(param.getStartDate(), param.getEndDate());
			System.out.println("902----------------"+param1[0]+".."+param1[1]);
			List<RoomHisOrderDto> orderList =null;
			if (param.getSid()==9999){
			   orderList = lotteryOrderService.getLotteryHisAllOrder(param1[0], param1[1],param.getAccountid(), param.getSid(), param.getBeginRow(), param.getPageSize());
			}else{
			   orderList = lotteryOrderService.getLotteryHisOrder(param1[0], param1[1],param.getAccountid(), param.getSid(), param.getBeginRow(), param.getPageSize());
			}
				result.success(orderList);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	
	/*
	@ApiOperation(value = "获取注单汇总金额", notes = "获取注单汇总金额", httpMethod = "POST")
	@RequestMapping(value = "/getHisOrderAmount", method = RequestMethod.POST)
	@ResponseBody
	public synchronized OrderAmountResult getHisOrderAmount(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody ResultAmountVo param) throws Exception {
		OrderAmountResult result = new OrderAmountResult();
		try {
			Date[] param1 = CommonUtils.getDateTime(param.getStartDate(), param.getEndDate());
			ResultAmountDto ra = null;
			if (param.getSid()==9999){
				ra = lotteryGameOrderMapper.selectGameAllHisAmount(param.getAccountid(),param.getSid(),param1[0], param1[1]);
			}else{
			    ra = lotteryGameOrderMapper.selectGameHisAmount(param.getAccountid(),param.getSid(),param1[0], param1[1]);
				
			}
			result.success(ra);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@ApiOperation(value = "获取房间收益", notes = "获取房间收益", httpMethod = "POST")
	@RequestMapping(value = "/getOrderAmount", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RoomAmountResult getOrderAmount(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody RoomAmountVo param) throws Exception {
		RoomAmountResult result = new RoomAmountResult();
		try {
			List<RoomAmountDto> list = lotteryGameOrderMapper.selectGameAmount(param.getRmid(), param.getLotteryterm());
			result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	
	@ApiOperation(value = "获取房间结算/本房/明细", notes = "获取房间结算/本房/明细", httpMethod = "POST")
	@RequestMapping(value = "/getOrderAmountDetail", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RoomOrderDetaiResult getOrderAmountDetail(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody RoomAmountVo param) throws Exception {
		RoomOrderDetaiResult result = new RoomOrderDetaiResult();
		try {
			List<RoomOrderDetailDto> list = lotteryGameOrderMapper.selectGameAmountDetail(param.getRmid(), param.getLotteryterm());
			result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	
	
	@ApiOperation(value = "获取输赢明细记录", notes = "获取输赢明细记录", httpMethod = "POST")
	@RequestMapping(value = "/getLotteryGameDetail", method = RequestMethod.POST)
	@ResponseBody
	public synchronized LotteryGameDetailResult getLotteryGameDetail(
			@ApiParam(value = "Json参数", required = true) @Validated @RequestBody LotteryGameDetailVO param) throws Exception {
		LotteryGameDetailResult result = new LotteryGameDetailResult();
		try {
			List<LotteryGameDetailDto> list = lotteryGameDetailMapper.selectByGameDetail(param.getLgmid());
			result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(), e);
		}
		return result;

	}
	*/
}
