package com.lottery.api.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lottery.api.dto.AccountBankVo;
import com.lottery.api.dto.AccountInfoVo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lottery.api.dto.AccountInfoVo;
import com.lottery.api.dto.AccountRecordVo;
import com.lottery.api.dto.AccountRemarkVo;
import com.lottery.api.dto.CashOrderVo;
import com.lottery.api.dto.DemoInfoVo;
import com.lottery.api.dto.LoginParamVo;
import com.lottery.api.dto.NoticeHisTypeVo;
import com.lottery.api.dto.NoticeTypeVo;
import com.lottery.api.dto.PasswordInfoVo;
import com.lottery.api.dto.PlayAccountInfoVo;
import com.lottery.api.dto.UpdateAccountVo;
import com.lottery.api.dto.UserCashVo;
import com.lottery.api.dto.UserOrderNoticeVo;
import com.lottery.api.dto.UserOrderVo;
import com.lottery.api.dto.UserRechargeResVo;
import com.lottery.api.dto.UserRechargeVo;
import com.lottery.api.filter.LockedClientException;
import com.lottery.api.util.Des3Util;
import com.lottery.api.util.ToolsUtil;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.bo.AccountRecord;
import com.lottery.orm.bo.AccountRemark;
import com.lottery.orm.bo.BankCash;
import com.lottery.orm.bo.NoticeInfo;
import com.lottery.orm.bo.OffAccountInfo;
import com.lottery.orm.bo.SysBene;
import com.lottery.orm.bo.SysFee;
import com.lottery.orm.dao.AccountAmountMapper;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.dao.AccountRecordMapper;
import com.lottery.orm.dao.AccountRemarkMapper;
import com.lottery.orm.dao.BankCashMapper;
import com.lottery.orm.dao.LotteryGameOrderMapper;
import com.lottery.orm.dao.LotteryOrderMapper;
import com.lottery.orm.dao.NoticeInfoMapper;
import com.lottery.orm.dao.OffAccountInfoMapper;
import com.lottery.orm.dao.SysBeneMapper;
import com.lottery.orm.dao.SysFeeMapper;
import com.lottery.orm.dao.TradeInfoMapper;
import com.lottery.orm.dto.AccountInfoDto;
import com.lottery.orm.dto.AccountSimInfoDto;
import com.lottery.orm.dto.RemarkDto;
import com.lottery.orm.dto.UserRechargeDto;
import com.lottery.orm.dto.UserRechargeResDto;
import com.lottery.orm.result.AccountListResult;
import com.lottery.orm.result.AccountResult;
import com.lottery.orm.result.AccountSimResult;
import com.lottery.orm.result.BankCashResult;
import com.lottery.orm.result.NoticeResult;
import com.lottery.orm.result.RemarkResult;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.result.UserRechargeResult;
import com.lottery.orm.service.AccountInfoService;
import com.lottery.orm.service.EasemobService;
import com.lottery.orm.util.CommonUtils;
import com.lottery.orm.util.EnumType;
import com.lottery.orm.util.MessageTool;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping(value = "/account", produces = {"application/json;charset=UTF-8"})
@Api(value = "/account", description = "玩家帐号信息接口")
@Controller
public class AccountInfoController {
	public static final Logger LOG = Logger.getLogger(AccountInfoController.class);
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
    private AccountInfoService accountInfoService;
	
	@Autowired
    private AccountInfoMapper accountInfoMapper;
	
	@Autowired
    private AccountRemarkMapper accountRemarkMapper;
	
	@Autowired
    private AccountDetailMapper accountDetailMapper;
	
	@Autowired
    private OffAccountInfoMapper offAccountInfoMapper;
	
	@Autowired
    private NoticeInfoMapper noticeInfoMapper;
	
	@Autowired
    private AccountRecordMapper accountRecordMapper;
	
	@Autowired
    private AccountRechargeMapper accountRechargeMapper;
	
	@Autowired
	private LotteryGameOrderMapper lotteryGameOrderMapper;
	
	@Autowired
	private AccountAmountMapper accountAmountMapper;
	
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	
	@Autowired
	private SysFeeMapper sysFeeMapper;
	
	@Autowired
	private SysBeneMapper sysBeneMapper;
	
	@Autowired
	private TransScanCodePayTest transScanCodePayTest;
	
	@Autowired
	private TransProxyPayTest transProxyPayTest;
	
	@Autowired
	private QueryTransStatusTest queryTransStatusTest;
	
	@Autowired
	private TransCashTest transCashTest;
	
	@Autowired
	private TransPayTest transPayTest;
	
	@Autowired
	private BankCashMapper bankCashMapper;
	
	@Autowired
	private EasemobService easemobService;
	
	@Value("${jwt.splitter}")
    private String tokenSplitter;
	
	@Value("${jwt.secret}")
    private String tokenSecret;
	
	@Value("${lottery.shareUrl}")
    private String shareUrl;
	
	@Value("${lottery.shareCodeImg}")
    private String shareCodeImg;
	
	@Value("${lottery.ruleUrl}")
    private String ruleUrl;
	
	@Value("${lottery.serviceTel}")
    private String serviceTel;
	
	@Value("${lottery.androidAppVersion}")
    private String androidAppVersion;
	
	@Value("${lottery.iosAppVersion}")
    private String iosAppVersion;

	@Value("${lottery.playoridle}")
    private String playoridle;
	
	@Value("${lottery.noplayoridle}")
    private String noplayoridle;
	
	/*
	@ApiOperation(value = "获取玩家或者代理商、子代理商信息", notes = "获取玩家或者代理商、子代理商信息", httpMethod = "POST")
	@RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public synchronized AccountResult getAccountInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody LoginParamVo param) throws Exception {
		AccountResult result = new AccountResult();
		try {
			String username = param.getUsername();
			String password = param.getPassword();
			
			//参数合规性校验，必要参数不能为空;
			if (ToolsUtil.isEmptyTrim(username)||ToolsUtil.isEmptyTrim(password)){
			      result.fail(MessageTool.Code_2002);
			      LOG.info(result.getMessage());
			      return result;
			}
			param.setPassword(DigestUtils.md5Hex(password));
		    AccountInfo paraInfo = mapper.map(param, AccountInfo.class);
		    AccountInfo accountInfo = accountInfoMapper.selectByLogin(paraInfo);
		    if(accountInfo!=null){	
		    	
				if(accountInfo.getState().equals("0")){
					throw new LockedClientException();
				}
				if (accountInfo.getOfftype().equals("2")){
					accountInfo =  accountInfoMapper.selectByPrimaryKey(accountInfo.getSupuserid());
				}
				
				AccountRecord aRecord = new AccountRecord();
				String sRecordid = CommonUtils.getCurrentMills();
				
				aRecord.setRecordid(sRecordid);
				System.out.println(sRecordid+"..."+aRecord.getRecordid());
				aRecord.setAccountid(accountInfo.getAccountid());
				aRecord.setIp(param.getIp());
				aRecord.setLevel(accountInfo.getLevel());
				aRecord.setOfftype(accountInfo.getOfftype());
				aRecord.setInputtime(new Date());
				accountRecordMapper.insert(aRecord);
				AccountInfoDto rAcDto = new AccountInfoDto();
				rAcDto = mapper.map(accountInfo, AccountInfoDto.class);
			    rAcDto.setToken((new Des3Util()).encode(accountInfo.getAccountid()+tokenSplitter+tokenSecret));
			    rAcDto.setRecordid(sRecordid);
				if (accountInfo.getOfftype().equals("99"))
					rAcDto.setPassword("123456XYV");
			    result.success(rAcDto);
		    }else {
			      result.fail(MessageTool.Code_3001);
			      LOG.info(result.getMessage());
			      return result;
		    }
			LOG.info(username+","+result.getMessage()+","+new Date());
			//登录
			
		} catch (LockedClientException e) {
			throw new LockedClientException();
		}catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;

	}
	*/
	@ApiOperation(value = "登录获取账户信息", notes = "登录获取账户信息", httpMethod = "POST")
	@RequestMapping(value = "/getAccountSimInfo", method = RequestMethod.POST)
	@ResponseBody
	public AccountSimResult getAccountSimpleInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody LoginParamVo param) throws Exception {
		AccountSimResult result = new AccountSimResult();
		try {
			String username = param.getUsername();
			String password = param.getPassword();
			
			//参数合规性校验，必要参数不能为空;
			if (ToolsUtil.isEmptyTrim(username)||ToolsUtil.isEmptyTrim(password)){
			      result.fail(MessageTool.Code_2002);
			      LOG.info(result.getMessage());
			      return result;
			}
			param.setPassword(DigestUtils.md5Hex(password));
		    AccountInfo paraInfo = mapper.map(param, AccountInfo.class);
		    AccountInfo accountInfo = accountInfoMapper.selectByLogin(paraInfo);
		    if(accountInfo!=null){	
				if(accountInfo.getState().equals("0")){
					throw new LockedClientException();
				}
				AccountRecord aRecord = new AccountRecord();
				String sRecordid = CommonUtils.getCurrentMills();
				aRecord.setRecordid(sRecordid);
				aRecord.setAccountid(accountInfo.getAccountid());
				aRecord.setIp(param.getIp());
				aRecord.setInputtime(new Date());
				accountRecordMapper.insert(aRecord);    	
				AccountSimInfoDto rAcDto = new AccountSimInfoDto();
				rAcDto = mapper.map(accountInfo, AccountSimInfoDto.class);
				rAcDto.setRecordid(sRecordid);
				result.success(rAcDto);
		    }else {
			      result.fail(MessageTool.Code_3001);
			      LOG.info(result.getMessage());
			      return result;
		    }
			//LOG.info(username+","+result.getMessage()+","+new Date());
		} catch (LockedClientException e) {
			throw new LockedClientException();
		}catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;

	}
	
	/*
	@ApiOperation(value = "试玩获取", notes = "试玩获取", httpMethod = "POST")
	@RequestMapping(value = "/getAccountDemoInfo", method = RequestMethod.POST)
	@ResponseBody
	public AccountResult getAccountDemoInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody DemoInfoVo param) throws Exception {
		AccountResult result = new AccountResult();
		try {
			//99：试玩用户
			AccountInfo info = new AccountInfo();
			info.setUsername("Test"+CommonUtils.produceString(4)+CommonUtils.RamdomNum());
			info.setAusername(info.getUsername());
			info.setPassword(DigestUtils.md5Hex("123456XYV"));
			info.setOfftype("99");
			info.setLevel("99");
			info.setInputdate(new Date());
			info.setUsermoney(BigDecimal.valueOf(20000));
			info.setSupuserid(1000);
			info.setState("1");
			accountInfoService.addAccountInfo(info);
			
			AccountInfo paraInfo = mapper.map(info, AccountInfo.class);
			AccountInfo accountInfo = accountInfoMapper.selectByLogin(paraInfo);
			
			AccountRecord aRecord = new AccountRecord();
			String sRecordid = CommonUtils.getCurrentMills();
			
			aRecord.setRecordid(sRecordid);
			System.out.println(sRecordid+"..."+aRecord.getRecordid());
			aRecord.setAccountid(accountInfo.getAccountid());
			aRecord.setIp(param.getIp());
			aRecord.setLevel(accountInfo.getLevel());
			aRecord.setOfftype(accountInfo.getOfftype());
			aRecord.setInputtime(new Date());
			accountRecordMapper.insert(aRecord);
			AccountInfoDto rAcDto = new AccountInfoDto();
			rAcDto = mapper.map(accountInfo, AccountInfoDto.class);
		    rAcDto.setToken((new Des3Util()).encode(accountInfo.getAccountid()+tokenSplitter+tokenSecret));
		    rAcDto.setRecordid(sRecordid);
		    rAcDto.setPassword("123456XYV");
		    result.success(rAcDto);
		}catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;

	}
	*/
	
	
	@ApiOperation(value = "玩家注册", notes = "玩家注册", httpMethod = "POST")
	@RequestMapping(value = "/addAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestResult addAccountInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody PlayAccountInfoVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			AccountInfo accountInfo = mapper.map(param,AccountInfo.class);			
			//参数合规性校验，必要参数不能为空
			if (ToolsUtil.isEmptyTrim(accountInfo.getUsername())||ToolsUtil.isEmptyTrim(accountInfo.getPassword())){
			      result.fail("用户名，密码",MessageTool.Code_2002);
			      LOG.info(result.getMessage());
			      return result;
			}
			
			//最长14个英文或者数字组合
			if (ToolsUtil.validatName(accountInfo.getUsername())){
			      result.fail("用户名",MessageTool.Code_1006);
			      LOG.info(result.getMessage());
			      return result;
			}
			
			if ("".equals(accountInfo.getSupuserid())){
			      result.fail("代理ID",MessageTool.Code_1009);
			      LOG.info(result.getMessage());
			      return result;
			}
				
			//玩家是否存在，用户名不能一致
		    //AccountInfo paraInfo = mapper.map(param, AccountInfo.class);
		    AccountInfo accountInfo1 = accountInfoMapper.selectByUsername(accountInfo.getUsername());
		    if (accountInfo1!=null){
			      result.fail(accountInfo.getUsername(),MessageTool.Code_2005);
			      return result;
		    }else{
		    	
		    	//根据邀请码判断上级
		    
			    accountInfo.setPassword(DigestUtils.md5Hex(accountInfo.getPassword())); 
			    accountInfo.setState("1");//默认状态正常
			    accountInfo.setInputdate(new Date());
			    accountInfo.setUsermoney(BigDecimal.valueOf(0.0));
			    accountInfo.setPercentage((0.0));
			    accountInfoMapper.insertSelective(accountInfo);
			    result.success();	
			    //注册环信帐号
			    /*
	            Boolean easeRegisterResult = easemobService.registerEaseMobUser(accountInfo.getUsername().toLowerCase(), accountInfo.getUsername().toLowerCase());
			    if (easeRegisterResult) {
	                    //环信帐号注册成功
	                    accountInfoService.addAccountInfo(accountInfo);
	                    result.success();
	                } else {
	                    result.fail("环信注册",MessageTool.Code_3002);
	                }
	               
		    	}else {
				     result.fail("邀请码",MessageTool.Code_3003);
				     return result;
		    	}
		    	*/

		    }
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	/*
	@ApiOperation(value = "修改玩家", notes = "修改玩家", httpMethod = "POST")
	@RequestMapping(value = "/updateAccountInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestResult updateAccountInfo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UpdateAccountVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
			if(accountInfo==null){
			      result.fail(MessageTool.Code_3001);
			}else{
				if (null==param.getPassword()||param.getPassword().equals("")){}
				else
				    param.setPassword(DigestUtils.md5Hex(param.getPassword()));
			    AccountInfo paraInfo = mapper.map(param, AccountInfo.class);
			    accountInfoService.updateAccountInfo(paraInfo);
			    result.success();
			}
			  LOG.info(result.getMessage());
	        } catch (Exception e) {
				result.error();
				LOG.error(e.getMessage(),e);
	        }
	     return result;
    }
	
	
	@ApiOperation(value = "获取在线客服、分享链接、规则说明", notes = "获取在线客服、分享链接、规则说明", httpMethod = "POST")
	@RequestMapping(value = "/getRemarkInfo", method = RequestMethod.POST)
	@ResponseBody
	public RemarkResult getAllAccountInfo() throws Exception {
		RemarkResult result = new RemarkResult();
		RemarkDto remark = new RemarkDto();
			
		remark.setOnline(serviceTel);
		remark.setShare(shareUrl);
		remark.setRule(ruleUrl);
		remark.setShareCode(shareCodeImg);
		remark.setAndroidAppVersion(androidAppVersion);
		remark.setIosAppVersion(iosAppVersion);
		remark.setPlayoridle(playoridle);
		remark.setNoplayoridle(noplayoridle);
		result.success(remark);

		return result;
	}
	*/
	/*
	@ApiOperation(value = "获取公告", notes = "获取公告", httpMethod = "POST")
	@RequestMapping(value = "/lotteryMessage", method = RequestMethod.POST)
	@ResponseBody
	public RestResult getLotteryMessage(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody NoticeTypeVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			String stype = param.getOfftype();
			if (stype.equals("")||!(stype.equals("3"))){
			      result.fail("公告类型",MessageTool.Code_1005);
			      LOG.info(result.getMessage());
			      return result;
			}
			String offtype = "";
			if (stype.equals("3"))
				offtype = "1";

            NoticeInfo noticeInfo = noticeInfoMapper.selectByNotice(offtype);
			if(noticeInfo==null){
			      result.fail(MessageTool.Code_4001);
			}else{
				result.success(noticeInfo);
			}
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	*/
	@ApiOperation(value = "获取消息公告", notes = "获取消息公告", httpMethod = "POST")
	@RequestMapping(value = "/lotteryHisMessage", method = RequestMethod.POST)
	@ResponseBody
	public NoticeResult getLotteryHisMessage(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody NoticeHisTypeVo param) throws Exception {
		NoticeResult result = new NoticeResult();
		try {
			String stype = param.getOfftype();
			if (stype.equals("")||!(stype.equals("3"))){
			      result.fail("公告类型",MessageTool.Code_1005);
			      LOG.info(result.getMessage());
			      return result;
			}
			String offtype = "";
			if (stype.equals("3"))
				offtype = "1";

            List<NoticeInfo> list = noticeInfoMapper.selectByHisNotice(offtype,param.getBeginRow(), param.getPageSize());
			if(list==null){
			      result.fail(MessageTool.Code_4001);
			}else{
				result.success(list);
			}
			LOG.info(result.getMessage());
		} catch (Exception e) {
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
	    		result.fail("新密码和确认密码输入不一致，请重新输入");
			    LOG.info(result.getMessage());
			    return result;
			}
			param.setPassword(DigestUtils.md5Hex(param.getSfcode()));
			AccountInfo accountInfo = mapper.map(param, AccountInfo.class);
			AccountInfo accountInfo1  = accountInfoMapper.selectByLogin(accountInfo);
			//AccountInfo accountInfo1 = accountInfoMapper.selectByUserAndSfcode(accountInfo.getUsername(), accountInfo.getSfcode());
			if (accountInfo1!=null){
				if ((accountInfo1.getPassword().equals(param.getPassword()))){
					accountInfo1.setPassword(DigestUtils.md5Hex(param.getSpassword()));
					accountInfoMapper.updateByPrimaryKey(accountInfo1);
				    result.success();
				}
			}else {
	    		result.fail("旧密码输入有误,请重新输入");
			    LOG.info(result.getMessage());
			    return result;
			}
         }catch(Exception e){
				result.error();
				LOG.error(e.getMessage(),e);
        	 }
		   return result;
         }
	
	@ApiOperation(value = "意见反馈", notes = "意见反馈", httpMethod = "POST")
	@RequestMapping(value = "/lotteryRemark", method = RequestMethod.POST)
	@ResponseBody
	public RestResult getLotteryMessage(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody AccountRemarkVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			//参数合规性校验，必要参数不能为空;
			if (ToolsUtil.isEmptyTrim(String.valueOf(param.getAccountid()))||ToolsUtil.isEmptyTrim(param.getRemark())){
			      result.fail("用户ID或者意见为空");
			      LOG.info(result.getMessage());
			      return result;
			}
			AccountRemark ar = mapper.map(param, AccountRemark.class);
			ar.setInputdate(new Date());
			accountRemarkMapper.insertSelective(ar);
			result.success();
		}catch(Exception e){
			result.error();
			LOG.error(e.getMessage(),e);
    	 }
		return result;
	}
	
	@ApiOperation(value = "绑定银行卡", notes = "绑定银行卡", httpMethod = "POST")
	@RequestMapping(value = "/userBank", method = RequestMethod.POST)
	@ResponseBody
	public RestResult getUserBank(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody AccountBankVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			//参数合规性校验，必要参数不能为空;
			if (ToolsUtil.isEmptyTrim(String.valueOf(param.getAccountid()))||ToolsUtil.isEmptyTrim(param.getBankid())){
			      result.fail("用户ID或者持卡人为空");
			      LOG.info(result.getMessage());
			      return result;
			}
			AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
			aInfo = mapper.map(param, AccountInfo.class);
			accountInfoMapper.updateByPrimaryKeySelective(aInfo);
			result.success();
		}catch(Exception e){
			result.error();
			LOG.error(e.getMessage(),e);
    	 }
		return result;
	}
	
	
	@ApiOperation(value = "退出", notes = "退出", httpMethod = "POST")
	@RequestMapping(value = "/userExit", method = RequestMethod.POST)
	@ResponseBody
	public RestResult userExit(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody AccountRecordVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			AccountRecord aRecord = new AccountRecord();
			aRecord.setRecordid(param.getRecordid());
			aRecord.setAccountid(param.getAccountid());
			aRecord.setOuttime(new Date());
			accountRecordMapper.updateByPrimaryKeySelective(aRecord);
			AccountInfo aInfo = new AccountInfo();
			aInfo.setAccountid(param.getAccountid());
			//aInfo.setPassword(DigestUtils.md5Hex("123456"));
			System.out.println("123------------"+aInfo.getAccountid());
			AccountInfo aInfo1 = accountInfoMapper.selectByPrimaryKey(param.getAccountid());
			if (aInfo1.getLevel().equals("99")){
				tradeInfoMapper.deleteByPlayer(aInfo1.getAccountid());
				accountInfoMapper.deleteByPrimaryKey(aInfo1.getAccountid());
				lotteryGameOrderMapper.deleteByPlayerOrder(aInfo1.getAccountid());
				accountAmountMapper.deleteByPlayer(aInfo1.getAccountid());
				
			}
			
			result.success();
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	/*
	@ApiOperation(value = "用户充值", notes = "用户充值", httpMethod = "POST")
	@RequestMapping(value = "/userRecharge", method = RequestMethod.POST)
	@ResponseBody
	public  UserRechargeResult userRecharge(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UserRechargeVo param) throws Exception {
		UserRechargeResult result = new UserRechargeResult();
		try {
			AccountRecharge aRecharge = new AccountRecharge();
			aRecharge = mapper.map(param, AccountRecharge.class);	
	    	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
	    	if (null == aInfo){
			      result.fail("该用户不存在！");
			      LOG.info(result.getMessage());
			      return result;
	    	}
	    	if (!(param.getUsername().equals(aInfo.getUsername()))){
	    	      result.fail("该用户名与ID不匹配！");
			      LOG.info(result.getMessage());
			      return result;
	    	}
   	
	    	if (param.getAccountId().toString().length()==3){
			      result.fail("该用户不允许充值！");
			      LOG.info(result.getMessage());
			      return result;
	    	}
	    	aRecharge.setTransamt(param.getTransAmt().intValue());
	    	aRecharge = transPayTest.getPayUrl(aRecharge);
	    	
	    	/*
	    	if (param.getProductId().equals("1053"))
	    		aRecharge = transScanCodePayTest.getPayWayTrans(aRecharge);
	    	else
	    	    aRecharge = transScanCodePayTest.getPayTrans(aRecharge);
	    	if (null == aRecharge){
	    	      result.fail("该用户充值出现异常！");
			      LOG.info(result.getMessage());
			      return result;
	    	}*/
	    	/*
			aRecharge.setRelativetype(EnumType.RalativeType.In.ID);
			aRecharge.setOpuserid(String.valueOf(param.getAccountId()));
			aRecharge.setOpusertime(new Date());
			aRecharge.setOrderstate("03");//处理中
			aRecharge.setInputtime(new Date());
	    	accountRechargeMapper.insert(aRecharge);
	    	UserRechargeResDto urDto = mapper.map(aRecharge, UserRechargeResDto.class);	 
	    	urDto.setUsername(param.getUsername());
	    	urDto.setTransAmt(param.getTransAmt());
	    	urDto.setKeyType("file");
	    	urDto.setBankCode("CMB");
	    	urDto.setCardType("0");
			result.success(urDto);
			LOG.info(result.getMessage());
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	*/
	/*
	@ApiOperation(value = "用户充值结果", notes = "用户充值结果", httpMethod = "POST")
	@RequestMapping(value = "/userRechargeResult", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userRechargeResult(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UserRechargeResVo param) throws Exception {
		RestResult result = new RestResult();
		try {
			String message = accountInfoService.checkResult(param.getOrderNo(), param.getPayNo(), param.getTransAmt(), param.getOrderDate(), param.getRespCode(), param.getRespDesc());
			result.success(message);
			return result;
	    
		} catch (Exception e) {
			result.error();
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	@ApiOperation(value = "用户取现申请", notes = "用户取现申请", httpMethod = "POST")
	@RequestMapping(value = "/userCashApply", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userCashApply(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody UserCashVo param) throws Exception {
		RestResult result = new RestResult();
    	if (param.getAccountId().toString().length()==3){
		      result.fail("该用户不允许取现！");
		      LOG.info(result.getMessage());
		      return result;
      	}
		AccountRecharge aRecharge = new AccountRecharge();
		aRecharge = mapper.map(param, AccountRecharge.class);	
		aRecharge.setRelativetype(EnumType.RalativeType.Out.ID);
		SysFee sf = sysFeeMapper.selectByPrimaryKey(1001);
		aRecharge.setFee(aRecharge.getTransamt()*sf.getCafee().doubleValue());
		aRecharge.setPayamt(aRecharge.getTransamt()-aRecharge.getFee());
		aRecharge.setOrderstate("03");//未处理,审核中
		aRecharge.setUpstate("03");//未处理，审核中
		aRecharge.setInputtime(new Date());
		AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
		if (null == aInfo){
		      result.fail("该用户不存在！");
		      LOG.info(result.getMessage());
		      return result;
		}
		
		if(aInfo.getUsermoney().subtract(BigDecimal.valueOf((double)(aRecharge.getTransamt()))).doubleValue()<0){
			result.fail("取现金额不能大于账户金额");
			return result;
		}
		String checkInfo = accountInfoService.checkCashMoneyInfo(aInfo, Double.valueOf(aRecharge.getTransamt()));
		if ((!"true".equals(checkInfo))){
			result.fail(checkInfo);
			return result;
		}
		LOG.info(aInfo.getUsername()+",账户金额:"+aInfo.getUsermoney()+",取现金额:"+(aRecharge.getTransamt())+",账户取现后余额："+aInfo.getUsermoney().subtract(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
		aRecharge.setAccountamount(aInfo.getUsermoney());
		accountInfoMapper.updateByPrimaryKey(aInfo);
		if((aRecharge.getTransamt()) % (sf.getRatio().doubleValue()) != 0){
			result.fail("取现必须为"+sf.getRatio().doubleValue()+"的倍数");
			return result;
	    }
		List<AccountRecharge> list =  accountRechargeMapper.selectByTime(aRecharge.getOrderdate(),EnumType.RalativeType.Out.ID,param.getAccountId());
		if (null != list&&list.size()>sf.getTime()){
			result.fail("当天取现次数不允许超过"+sf.getTime()+"次");
			return result;
		}
		if (aRecharge.getFee()>0){
    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
    		aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf(aRecharge.getFee())));
	    	accountInfoMapper.updateByPrimaryKey(aInfo);
    	}
		aRecharge.setTransamt(aRecharge.getTransamt());
		aRecharge.setRemark("取现金额:"+aRecharge.getTransamt()+",取现时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		accountRechargeMapper.insert(aRecharge);
		result.success();
		LOG.info(result.getMessage());
		return result;
      }
	
	@ApiOperation(value = "后台审核", notes = "后台审核", httpMethod = "POST")
	@RequestMapping(value = "/userCashAudit", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userCashAudit(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody CashOrderVo param) throws Exception {
		RestResult result = new RestResult();
		AccountRecharge aRecharge = new AccountRecharge();
		aRecharge = mapper.map(param, AccountRecharge.class);	
		SysFee sf = sysFeeMapper.selectByPrimaryKey(1001);
		aRecharge = accountRechargeMapper.selectByPrimaryKey(aRecharge.getArid());
		aRecharge.setTransamt(aRecharge.getTransamt()*100);
		AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
		if (null == aInfo){
		      result.fail("该用户不存在！");
		      LOG.info(result.getMessage());
		      return result;
		}
		
		String checkInfo = accountInfoService.checkCashMoneyInfo(aInfo, Double.valueOf(aRecharge.getTransamt()/100));
		if ((!"true".equals(checkInfo))){
			result.fail(checkInfo);
			return result;
		}
		
		if((aRecharge.getTransamt()/100) % (sf.getRatio().doubleValue()) != 0){
			result.fail("取现必须为"+sf.getRatio().doubleValue()+"的倍数");
			return result;
	    }
		List<AccountRecharge> list =  accountRechargeMapper.selectByTime(aRecharge.getOrderdate(),EnumType.RalativeType.Out.ID,aRecharge.getAccountid());
		if (null != list&&list.size()>sf.getTime()){
			result.fail("当天取现次数不允许超过"+sf.getTime()+"次");
			return result;
		}
		
		result.success("success");
		LOG.info(result.getMessage());
		return result;
      }
	
	@ApiOperation(value = "后台打款", notes = "后台打款", httpMethod = "POST")
	@RequestMapping(value = "/userCashdo", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userCashdo(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody CashOrderVo param) throws Exception {
		RestResult result = new RestResult();
		AccountRecharge aRecharge = new AccountRecharge();
		aRecharge = mapper.map(param, AccountRecharge.class);	
		aRecharge = accountRechargeMapper.selectByPrimaryKey(aRecharge.getArid());
		if (null == aRecharge){
		      result.fail("取现订单异常！");
		      LOG.info(result.getMessage());
		      return result;
		}
		if (null == aRecharge.getRespcode()){
			if (aRecharge.getOrderstate().equals("04")){
			      result.fail("该订单已取现或人工已处理！");
			      LOG.info(result.getMessage());
			      return result;
			}
			if (aRecharge.getOrderstate().equals("01")||aRecharge.getOrderstate().equals("02")){
			      result.fail("该订单已处理，无法打款！");
			      LOG.info(result.getMessage());
			      return result;
			}
		}else if ((aRecharge.getRespcode().equals("P000"))||aRecharge.getOrderdate().equals("04")){
		      result.fail("该订单已取现或者人工已处理！");
		      LOG.info(result.getMessage());
		      return result;
		}else if (aRecharge.getOrderstate().equals("01")||aRecharge.getOrderstate().equals("02")){
		      result.fail("该订单已处理，无法打款！");
		      LOG.info(result.getMessage());
		      return result;
		}
		aRecharge.setRequestno(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		aRecharge.setOrderno(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		aRecharge.setOrderdate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		aRecharge.setOpusertime(new Date());
		aRecharge.setOpuserid(param.getOpuserid());
		SysFee sf = sysFeeMapper.selectByPrimaryKey(1001);
		aRecharge.setTransamt(aRecharge.getTransamt());
		AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
		if (null == aInfo){
		      result.fail("该用户不存在！");
		      LOG.info(result.getMessage());
		      return result;
		}
		
		String checkInfo = accountInfoService.checkDoMoneyInfo(aInfo, Double.valueOf(aRecharge.getTransamt()));
		if ((!"true".equals(checkInfo))){
			result.fail(checkInfo);
			return result;
		}
		
		if((aRecharge.getTransamt()) % (sf.getRatio().doubleValue()) != 0){
			result.fail("取现必须为"+sf.getRatio().doubleValue()+"的倍数");
			return result;
	    }
		List<AccountRecharge> list =  accountRechargeMapper.selectByTime(aRecharge.getOrderdate(),EnumType.RalativeType.Out.ID,aRecharge.getAccountid());
		if (null != list&&list.size()>sf.getTime()){
			result.fail("当天取现次数不允许超过"+sf.getTime()+"次");
			return result;
		}
		//String results = transProxyPayTest.getPayTrans(aRecharge);
		String results = transCashTest.getCashSubmit(aRecharge);
		String[] res = results.split("~");
    	if (null == res[0]||res[0].equals("")||res[0].equals("1111")){
    	    result.fail("该用户打款出现异常！");
		    LOG.info(result.getMessage());
		    return result;
    	}else if (res[0].equals("0000")){
    		result.success("success");
    	}else{
  	        result.fail(res[1]);
		    LOG.info(result.getMessage());
		    return result;
    	}
		result.success("success");
		LOG.info(result.getMessage());
		return result;
      }
	
	@ApiOperation(value = "后台取消", notes = "后台取消", httpMethod = "POST")
	@RequestMapping(value = "/userCashCancel", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userCashCancel(@ApiParam(value = "Json参数", required = true) @Validated @RequestBody CashOrderVo param) throws Exception {
		RestResult result = new RestResult();
		AccountRecharge aRecharge = new AccountRecharge();
		aRecharge = mapper.map(param, AccountRecharge.class);	
		aRecharge = accountRechargeMapper.selectByPrimaryKey(aRecharge.getArid());
		if (null == aRecharge){
		      result.fail("取现订单异常！");
		      LOG.info(result.getMessage());
		      return result;
		}
		AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
		if (null == aInfo){
		      result.fail("该用户不存在！");
		      LOG.info(result.getMessage());
		      return result;
		}

		if (null == aRecharge.getRespcode()){
			if (aRecharge.getOrderstate().equals("04")){
			      result.fail("该订单已取现或已人工处理，无法取消！");
			      LOG.info(result.getMessage());
			      return result;
			}
			if (aRecharge.getOrderstate().equals("01")||aRecharge.getOrderstate().equals("02")){
			      result.fail("该订单已处理，无法取消！");
			      LOG.info(result.getMessage());
			      return result;
			}
		}
		else if ((aRecharge.getRespcode().equals("P000"))||aRecharge.getOrderdate().equals("04")){
		      result.fail("该订单已取现或已人工处理，无法取消！");
		      LOG.info(result.getMessage());
		      return result;
		}else if (aRecharge.getOrderstate().equals("01")||aRecharge.getOrderstate().equals("02")){
		      result.fail("该订单已处理，无法取消！");
		      LOG.info(result.getMessage());
		      return result;
		}
		
		aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf(aRecharge.getTransamt().doubleValue())));
		aRecharge.setAccountamount(aInfo.getUsermoney());
		accountInfoMapper.updateByPrimaryKey(aInfo);
		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getTransamt().doubleValue())));
		accountInfoMapper.updateByPrimaryKey(aInfo);
		aRecharge.setOrderstate("04");
		aRecharge.setOpuserid(param.getOpuserid());
		aRecharge.setOpusertime(new Date());
		accountRechargeMapper.updateByPrimaryKey(aRecharge);
		result.success("success");
		LOG.info(result.getMessage());
		return result;
      }
	
	@ApiOperation(value = "用户取现支持的银行列表", notes = "用户取现支持的银行列表", httpMethod = "POST")
	@RequestMapping(value = "/bankCashList", method = RequestMethod.POST)
	@ResponseBody
	public BankCashResult bankCashList() throws Exception {
		BankCashResult result = new BankCashResult();
		List<BankCash> list = bankCashMapper.selectBankCash();
		result.success(list);
		LOG.info(result.getMessage());
		return result;
      }
	
	
	@ApiOperation(value = "后台取现结果查询", notes = "后台取现结果查询", httpMethod = "POST")
	@RequestMapping(value = "/userOutResult", method = RequestMethod.POST)
	@ResponseBody
	public synchronized RestResult userOutResult() throws Exception {
		System.out.println("ddo..start."+new Date());
		RestResult result = new RestResult();
		List<AccountRecharge> list = accountRechargeMapper.selectByOutResult();
		if (null != list){
			  for (int i = 0;i<list.size();i++){
				  AccountRecharge aRecharge = new AccountRecharge();
				  aRecharge = list.get(i);
				  String results = "";
				  //String results = queryTransStatusTest.getPayResults(aRecharge);
				  if (aRecharge.getRelativetype().equals("In"))
				      results = transPayTest.getPayResults(aRecharge);
				  else if (aRecharge.getRelativetype().equals("Out"))
					  results = transPayTest.getCashResults(aRecharge);
				  System.out.println(results +",accountid = "+aRecharge.getAccountid()+",transAmt = "+aRecharge.getTransamt());
				  LOG.info(results +",accountid = "+aRecharge.getAccountid()+",transAmt = "+aRecharge.getTransamt());
			  }
		}
		result.success("success");
		LOG.info(result.getMessage());
		System.out.println("ddo..over."+new Date());
		return result;
      }
*/
}
