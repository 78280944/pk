package com.lottery.api.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lottery.api.util.Des3Util;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.dao.AccountDetailMapper;

public class ResponseInterceptor extends HandlerInterceptorAdapter {
	public static final Logger LOG = Logger.getLogger(ResponseInterceptor.class);
	
	@Autowired
    private AccountDetailMapper accountDetailMapper;
	
	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Value("${jwt.splitter}")
    private String tokenSplitter;
	
	@Value("${jwt.secret}")
    private String tokenSecret;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientToken = null;
        try {
            clientToken = request.getHeader(tokenHeader);
            if(clientToken==null)	clientToken = request.getHeader(tokenHeader.replace("_", ""));//nginx可能过滤掉下划线
        } catch (Exception e) {
            clientToken = null;
        }
        
		if (clientToken != null && !"".equals(clientToken)) {
			AccountDetail account = null;
			String secret = "";
			try {
				Des3Util des3Util = new Des3Util();
				String decodedToken = des3Util.decode(clientToken);
				int accountid = Integer.parseInt(decodedToken.split(tokenSplitter)[0]);
				secret = decodedToken.split(tokenSplitter)[1];
				account = accountDetailMapper.selectByPrimaryKey(accountid);
			
			} catch (Exception e) {
				LOG.error("Decode clientToken error!");
				throw new InvalidClientException();
	        }
			if (secret.equals(tokenSecret)&&account!=null) {
				if(account.getState().equals("1")){
					return true;
				}else{
					throw new LockedClientException();
				}
			} else {
				throw new InvalidClientException();
			}
		}else{
			LOG.error("ClientToken is null!");
			throw new InvalidClientException();
		}

    }


}