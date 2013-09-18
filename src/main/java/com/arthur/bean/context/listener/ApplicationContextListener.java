package com.arthur.bean.context.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arthur.bean.factory.util.SpringBeanUtils;

public class ApplicationContextListener implements ServletContextListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("initing webApplicationContext...");
		SpringBeanUtils.init(sce.getServletContext());
	}

	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("destory webApplicationContext...");
		SpringBeanUtils.destory();
	}

}
