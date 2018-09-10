package com.lottery.api.controller;

import java.math.BigDecimal;
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
import com.lottery.api.dto.SubAccountInfoVo;
import com.lottery.api.dto.UpdateAccountStateVo;
import com.lottery.api.dto.UpdatePlayPassVo;
import com.lottery.api.dto.UpdateSubAccAuserVo;
import com.lottery.api.dto.UpdateSubAccRightVo;
import com.lottery.api.dto.UpdateSubAccStateVo;
import com.lottery.api.util.ToolsUtil;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.OffAccountInfo;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.OffAccountInfoMapper;
import com.lottery.orm.dto.OffsAccountDto;
import com.lottery.orm.dto.SubAccountDto;
import com.lottery.orm.dto.SubsAccountDto;
import com.lottery.orm.result.OffAccountListResult;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.result.SubAccountListResult;
import com.lottery.orm.service.AccountInfoService;
import com.lottery.orm.service.EasemobService;
import com.lottery.orm.service.OffAccountInfoService;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping(value = "/subAccount", produces = {"application/json;charset=UTF-8"})
@Api(value = "/subAccount", description = "代理的子帐号信息接口")
@Controller
public class SubAccountInfoController {
	public static final Logger LOG = Logger.getLogger(SubAccountInfoController.class);
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
    private AccountInfoService accountInfoService;
	
	@Autowired
	private OffAccountInfoMapper offAccountInfoMapper;
	
	@Autowired
    private AccountDetailMapper accountDetailMapper;
	
	@Autowired
    private AccountInfoMapper accountInfoMapper;
	
	@Autowired
	private EasemobService easemobService;
	
	@ApiOperation(value = "新增子帐号", notes = "新增子帐号", httpMethod = "POST")
	@RequestMapping(value = "/addSubAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult addSubAccountInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody SubAccountInfoVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			
			String username = param.getUsername();
			String password = param.getPassword();
			//参数合规性校验，必要参数不能为空
			if (ToolsUtil.isEmptyTrim(username)||ToolsUtil.isEmptyTrim(password)){
			      result.fail("用户名，密码",MessageTool.Code_2002);
			      LOG.info(result.getMessage());
			      return result;
			}
		
			AccountInfo paraInfo = mapper.map(param, AccountInfo.class);
			AccountInfo offAccountInfo = accountInfoMapper.selectByUsername(paraInfo.getUsername());
		    if (offAccountInfo!=null){
			      result.fail(username,MessageTool.Code_2005);
			      LOG.info(result.getMessage());
				    return result;
		    }else{
	
		    	//获取管理员level
		    	AccountInfo offAccountInfo1 = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
		    	if (offAccountInfo1 == null){
		    		result.fail(username,MessageTool.Code_2006);
				    LOG.info(result.getMessage());
				    return result;
		    	}
		    	paraInfo.setPassword(DigestUtils.md5Hex(password));
		    	paraInfo.setLevel(offAccountInfo1.getLevel());
			    paraInfo.setState("1");//默认状态正常
			    paraInfo.setOfftype("2");
			    paraInfo.setInputdate(new Date());
			    paraInfo.setUsermoney(BigDecimal.valueOf(0.0));
			    paraInfo.setSupuserid(param.getAccountid());
			    //注册环信帐号
                Boolean easeRegisterResult = easemobService.registerEaseMobUser(paraInfo.getUsername().toLowerCase(), paraInfo.getUsername().toLowerCase());
                if (easeRegisterResult) {
                    //环信帐号注册成功
                    accountInfoService.addAccountInfo(paraInfo);
                    result.success();
                } else {
                    result.fail("环信注册",MessageTool.Code_3002);
                }
			   
		    }

			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	
	@ApiOperation(value = "获取该代理的子账号列表", notes = "获取该代理的子账号列表", httpMethod = "POST")
	@RequestMapping(value = "/getAllSubAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public synchronized SubAccountListResult getAllSubAccountInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody AccountInfoVo param) throws Exception {
		SubAccountListResult result = new SubAccountListResult();
		try {
			
			AccountInfo offacount = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
			if(offacount==null){
				  result.fail(MessageTool.Code_3001);
			      LOG.info(result.getMessage());
			      return result;
			}
			List<SubsAccountDto> list = offAccountInfoMapper.selectSubSupuserId(offacount.getAccountid(), EnumType.OffType.Sub.ID,param.getBeginRow(), param.getPageSize());
		    result.success(list);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	@ApiOperation(value = "代理用户修改子账户密码", notes = "代理用户修改子账户密码", httpMethod = "POST")
	@RequestMapping(value = "/updateSubAccountPass", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult updateSubAccountPass(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UpdatePlayPassVo param) throws Exception {
		RestResult result = new RestResult();
		try {			
			AccountInfo offAccountInfo = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
			if(offAccountInfo==null){
			      result.fail(MessageTool.Code_3001);
			      LOG.info(result.getMessage());
			      return result;
			}else{
				offAccountInfo.setPassword(DigestUtils.md5Hex(param.getPassword()));
				offAccountInfo.setIp(param.getIp());
				accountInfoMapper.updateByPrimaryKey(offAccountInfo);
			    LOG.info("修改密码记录详情为："+" 管理员："+param.getSupuserid()+" IP："+param.getIp()+" 修改子账户ID"+param.getAccountid()+" 密码修改为"+offAccountInfo.getPassword());
			    result.success();
			}
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	@ApiOperation(value = "代理用户修改子账户状态", notes = "代理用户修改子账户状态", httpMethod = "POST")
	@RequestMapping(value = "/updateSubAccountState", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult updateSubAccountState(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UpdateAccountStateVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			int supaccountId = param.getSupaccountid();
			int accountId = param.getAccountid();
			String state = param.getState();
			String ip = param.getIp();
			
			if (!(state.equals("0")||state.equals("1"))){
			      result.fail("状态",MessageTool.Code_1005);
			      LOG.info(result.getMessage());
			      return result;
			}
			
			AccountInfo offAccountInfo = accountInfoMapper.selectByPrimaryKey(accountId);
			if(offAccountInfo==null){
			      result.fail(MessageTool.Code_3001);
			      return result;
			}else{
				offAccountInfo.setState(state);
				offAccountInfo.setIp(ip);
				accountInfoMapper.updateByPrimaryKey(offAccountInfo);
				
			    LOG.info("修改状态记录详情为："+" 管理员："+supaccountId+" IP："+ip+" 修改下家ID"+accountId+" 状态修改为"+state);
			    result.success();
			}
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
}
