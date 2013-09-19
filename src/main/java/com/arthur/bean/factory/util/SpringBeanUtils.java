package com.arthur.bean.factory.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringBeanUtils {

	private static ConfigurableListableBeanFactory beanFactory;

	private static WebApplicationContext wac;

	public static void init(ServletContext sc) {
		SpringBeanUtils.wac = WebApplicationContextUtils
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
	public static String[] getAllBeanNames() {
		return beanFactory.getBeanDefinitionNames();
	}

	public static String[] beanNamesIncludingAncestors() {
		return BeanFactoryUtils.beanNamesIncludingAncestors(beanFactory);
	}

	/**
	 * Return the names of all beans that this specificed bean dependens on, if
	 * any.
	 * 
	 * @param beanName
	 * @return
	 */
	public static String[] getDependenciesForBean(String beanName) {
		return beanFactory.getDependenciesForBean(beanName);
	}

	/**
	 * Return the names of all beans which depend on the specified bean, if any.
	 * 
	 * @param beanName
	 * @return
	 */
	public static String[] getDependentBeans(String beanName) {
		return beanFactory.getDependentBeans(beanName);
	}

	public static <T> Map<String, T> getBeanOfType(Class<T> beanType) {
		// BeanFactoryUtils
		return beanFactory.getBeansOfType(beanType);
	}

	public static <T> Map<String, T> beanOfTypeIncludingAncestor(Class<T> beanType) {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory,
				beanType);
	}

	public static <T> T beanOfTypeIncludingAncestors(Class<T> beanType) {
		return BeanFactoryUtils.beanOfTypeIncludingAncestors(beanFactory,
				beanType);
	}

	public static <T> T beanOfType(Class<T> beanType,
			boolean includeNonSingletons, boolean allowEagerInit) {
		return BeanFactoryUtils.beanOfType(beanFactory, beanType,
				includeNonSingletons, allowEagerInit);
	}

	public static int getBeanCount() {
		return beanFactory.getBeanDefinitionCount();
	}

	public static int getBeanCountIncludingAncestor() {
		return BeanFactoryUtils.countBeansIncludingAncestors(beanFactory);
	}

	public static Object getBeanByName(String beanName) {
		return beanFactory.getBean(beanName);
	}

}
