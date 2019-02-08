package com.ansell.ask.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ansell.ask.service.interfaces.ITokenAuthenticationService;

@Component
@Order(1)
public class TokenAuthenticationFilter implements Filter{

	private static Logger gtLogger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
	
	@Autowired
	private ITokenAuthenticationService tokenAuthService = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		gtLogger.debug("Hi Filter Init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		gtLogger.debug("Hi Filter doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String requestedURI = req.getRequestURI();
		if((requestedURI.contains("/pingServer") == Boolean.FALSE) &&
				(requestedURI.contains("/index") == Boolean.FALSE) &&
				(requestedURI.contains("/actuator") == Boolean.FALSE) && 
				(requestedURI.contains("/favicon.ico") == Boolean.FALSE)) {
			String token = req.getHeader("xssf-token");
			String userId= req.getParameter("userId");
			gtLogger.info("Requested URL is ::"+requestedURI+" Having User Id::"+userId+" Token::"+token);
			String userIdByTokenId = tokenAuthService.getUserIdByToken(token);
			
			gtLogger.info("User Id By Token Id::"+token+" is ::"+userIdByTokenId);
			if(userId != null) {
				if(userIdByTokenId != null) {
					if(userId.equalsIgnoreCase(userIdByTokenId) == Boolean.FALSE) {
						gtLogger.info("User id not same");
						resp.reset();
						resp.setStatus(IConstant.TOKEN_NOT_VALID_STATUS);
						return;
					}else {
						gtLogger.info("User id is same. For Checking user have access to ASk or NOT userId::"+userIdByTokenId );
						//TODO: Code for checking user have permission or not if don't have permission then return access Denied status
					}
				}else {
					gtLogger.info("User token id is null");
					resp.reset();
					resp.setStatus(IConstant.TOKEN_NOT_VALID_STATUS);
					return;
				}
			}else {
				gtLogger.info("User id is null");
				resp.reset();
				resp.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
		}
		chain.doFilter(request,response);
		gtLogger.info("Starting a transaction for req : {}", req.getRequestURI());
		
	}

	@Override
	public void destroy() {
		gtLogger.debug("Hi Filter destroy");
	}

}
