package com.lottery.api.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lottery.api.util.Des3Util;
import com.lottery.orm.bo.AccountDetail;
import com.lottery.orm.dao.AccountDetailMapper;
import com.lottery.orm.util.MessageTool;

public class RestAuthFilter implements Filter {

	@Autowired
    private AccountDetailMapper accountDetailMapper;
	
	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Value("${jwt.splitter}")
    private String tokenSplitter;
	
	@Value("${jwt.secret}")
    private String tokenSecret;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	ServletContext context = filterConfig.getServletContext();
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	//apiUserService = (ApiUserService) ctx.getBean("apiUserService");
    }

    /**
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
    	String clientToken = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		//boolean result = false;
		Integer errorCode = null;
		if (requestURI.contains("/account/getAccountInfo")) {
			//result = true;
			errorCode = MessageTool.SuccessCode;
		}else{
		    try {
		        clientToken = httpRequest.getHeader(tokenHeader);
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
					throw new InvalidClientException();
		        }
				if (secret.equals(tokenSecret)&&account!=null) {
					if(account.getState().equals("1")){
						//result = true;
					}else{
						throw new LockedClientException();
					}
				} else {
					throw new InvalidClientException();
				}
			}else{
				throw new InvalidClientException();
			}
		}
	
		if (errorCode==MessageTool.SuccessCode) {
		    chain.doFilter(httpRequest, response);
		    return;
		} else {
			httpRequest.getRequestDispatcher("/lottery-api/error/" + errorCode).forward(httpRequest, response);
		}
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {

    }

}
