package com.lottery.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.api.dto.AccountInfoVo;
import com.lottery.api.dto.PasswordInfoVo;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.LotteryService;
import com.lottery.orm.bo.SysFee;
import com.lottery.orm.bo.SysLimit;
import com.lottery.orm.bo.SysRatio;
import com.lottery.orm.bo.SysRoom;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.LotteryServiceMapper;
import com.lottery.orm.dao.SysFeeMapper;
import com.lottery.orm.dao.SysLimitMapper;
import com.lottery.orm.dao.SysRatioMapper;
import com.lottery.orm.dao.SysRoomMapper;
import com.lottery.orm.dto.LotteryServiceDto;
import com.lottery.orm.result.InitInfoResult;
import com.lottery.orm.result.OffAccountListResult;
import com.lottery.orm.result.RemarkResult;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.result.SysRatioListResult;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

	@RequestMapping(value = "/initService", produces = {"application/json;charset=UTF-8"})
	@Api(value = "/initService", description = "初始化信息接口")
	@Controller
	public class LotteryServiceControl {
		public static final Logger LOG = Logger.getLogger(LotteryServiceControl.class);
		
		@Autowired
		private Mapper mapper;
		
		@Autowired
	    private LotteryServiceMapper lotteryServiceMapper;
		
		@Autowired
	    private AccountInfoMapper accountInfoMapper;
		
		@Autowired
	    private SysRoomMapper sysRoomMapper;
		
		@Autowired
	    private SysLimitMapper sysLimitMapper;
		
		@Autowired
		private SysFeeMapper sysFeeMapper;
		
		@Autowired
		private SysRatioMapper sysRatioMapper;
		
		@ApiOperation(value = "获取最新赔率及剩余金额", notes = "获取最新赔率及剩余金额", httpMethod = "POST")
		@RequestMapping(value = "/getRatioAmount", method = RequestMethod.POST)
		@ResponseBody
		public SysRatioListResult getRatioAmount() throws Exception {
			SysRatioListResult result = new SysRatioListResult();
			try {
				List<SysRatio> sys = sysRatioMapper.selectSysRatio();
				result.success(sys);  
             }catch (Exception e) {
 				result.error();
 				LOG.error(e.getMessage(),e);
 			}
			return result;
		}
		
		/*
		@ApiOperation(value = "初始化信息", notes = "初始化信息", httpMethod = "POST")
		@RequestMapping(value = "/initDataAmount", method = RequestMethod.POST)
		@ResponseBody
		public SysRatioListResult getInitInfoAmount() throws Exception {
			SysRatioListResult result = new SysRatioListResult();
			try {
				List<SysRatio> sys = sysRatioMapper.selectSysRatio();
				result.success(sys);  
             }catch (Exception e) {
 				result.error();
 				LOG.error(e.getMessage(),e);
 			}
			return result;
		}
		
		@ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST")
		@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
		@ResponseBody
		public RestResult updatePassword(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody PasswordInfoVo param) throws Exception {
			RestResult result = new RestResult();
			try {
				if (!(param.getPassword().equals(param.getSpassword()))){
		    		result.fail("密码输入不一致，请重新输入");
				    LOG.info(result.getMessage());
				    return result;
				}
				AccountInfo accountInfo = mapper.map(param, AccountInfo.class);
				AccountInfo accountInfo1 = accountInfoMapper.selectByUserAndSfcode(accountInfo.getUsername(), accountInfo.getSfcode());
				if (accountInfo1!=null){
					accountInfo1.setPassword(DigestUtils.md5Hex(accountInfo.getPassword()));
					accountInfoMapper.updateByPrimaryKey(accountInfo1);
					result.success();
				}else {
		    		result.fail("用户名或安全码",MessageTool.Code_3003);
				    LOG.info(result.getMessage());
				    return result;
				}
				
             }catch(Exception e){
 				result.error();
 				LOG.error(e.getMessage(),e);
            	 }
			   return result;
             }
             */
		}
