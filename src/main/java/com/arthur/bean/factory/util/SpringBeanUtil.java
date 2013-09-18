package com.arthur.bean.factory.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringBeanUtil {

	private static ConfigurableListableBeanFactory beanFactory;

	private static WebApplicationContext wac;

	public static void init(ServletContext sc) {
		SpringBeanUtil.wac = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		// For web app, this can't throw ClassCastException
		beanFactory = ((XmlWebApplicationContext) wac).getBeanFactory();
	}

	public static void destory() {
		wac = null;
		beanFactory = null;
	}

	public static WebApplicationContext getContext() {
		return wac;
	}

	public static ConfigurableListableBeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static <T> Collection<T> getByType(Class<T> type) {
		return beanFactory.getBeansOfType(type).values();
	}

	/**
	 * Get all bean name from factory
	 * 
	 * @return
	 */
	public static List<String> getAllBeanNames() {
		BeanFactoryUtils.beanNamesIncludingAncestors(beanFactory);
		return Arrays.asList(beanFactory.getBeanDefinitionNames());
	}

	/**
	 * Return the names of all beans that this specificed bean dependens on, if
	 * any.
	 * 
	 * @param beanName
	 * @return
	 */
	public static List<String> getDependenciesForBean(String beanName) {
		return Arrays.asList(beanFactory.getDependenciesForBean(beanName));
	}

	/**
	 * Return the names of all beans which depend on the specified bean, if any.
	 * 
	 * @param beanName
	 * @return
	 */
	public static String[] getDependentBean(String beanName) {
		return beanFactory.getDependentBeans(beanName);
	}

	public static Map<String, ?> getBeanOfType(Class<?> beanType) {
		// BeanFactoryUtils
		return beanFactory.getBeansOfType(beanType);
	}

}
