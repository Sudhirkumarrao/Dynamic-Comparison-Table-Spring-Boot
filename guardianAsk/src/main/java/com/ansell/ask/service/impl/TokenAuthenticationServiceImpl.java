package com.ansell.ask.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ansell.ask.dao.interfaces.ITokenAuthenticationDAO;
import com.ansell.ask.service.interfaces.ITokenAuthenticationService;

@Service
public class TokenAuthenticationServiceImpl implements ITokenAuthenticationService{
	
	private static Logger gtLogger = LoggerFactory.getLogger(TokenAuthenticationServiceImpl.class);

	@Autowired
	private ITokenAuthenticationDAO tokenAuthDAO = null;
	
	@Override
	public String getUserIdByToken(String token) {
		gtLogger.info("Inside getUserIdByToken() ---- {token} "+token);
		return tokenAuthDAO.getUserIdByToken(token);
	}

}
