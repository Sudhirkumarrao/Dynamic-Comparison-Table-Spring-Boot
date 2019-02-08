package com.ansell.ask.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ansell.ask.dao.interfaces.ITokenAuthenticationDAO;
import com.ansell.ask.service.impl.TokenAuthenticationServiceImpl;

@Repository
public class TokenAuthenticationDAOImpl implements ITokenAuthenticationDAO{
	
	private static Logger gtLogger = LoggerFactory.getLogger(TokenAuthenticationServiceImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedJDBCTemplate = null;

	@Override
	public String getUserIdByToken(String token) {
		gtLogger.info("Inside getUserIdByToken() token:: "+token);
		String userId = null;
		try {
			StringBuilder selectQuery = new StringBuilder();
			selectQuery.append("SELECT user_id FROM int_user_token_assoc WHERE token =:token");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("token", token);
			
			userId = namedJDBCTemplate.queryForObject(selectQuery.toString(), paramMap, String.class);
		}catch (EmptyResultDataAccessException e) {
			userId = null;
			gtLogger.error("Error Ocuured while fetching UserId of token::"+token+" "+e.getMessage());
		}
		return userId;
	}

}
