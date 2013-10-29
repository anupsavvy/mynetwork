package com.sonic.mynetwork.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.oauth.OAuthService;

public class Init implements ServletContextListener{
	private Logger logger = Logger.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent sce){
		//TODO : need to destroy context properly
	}

	public void contextInitialized(ServletContextEvent sce){
		ServletContext servletContext = sce.getServletContext();
		logger.debug("Initialization succeeded.");
	}

}

