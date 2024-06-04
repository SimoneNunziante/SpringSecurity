package com.securityEmpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionTimeoutListner implements HttpSessionListener{

	
	private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutListner.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("Sessione creata, ID: {}", event.getSession().getId());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		logger.info("Sessione distrutta, ID: {}", event.getSession().getId());
	}

}
