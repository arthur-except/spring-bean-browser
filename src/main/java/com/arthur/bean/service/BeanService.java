package com.arthur.bean.service;

import java.util.List;

import com.arthur.bean.details.BeanInfo;

/**
 * Bean search service.
 * 
 * @author arthur
 * 
 */
public interface BeanService {

	/**
	 * Search beans by bean name.
	 * 
	 * @param beanName
	 * @return
	 */
	List<BeanInfo> searchByName(String beanName);

	/**
	 * Search beans by bean type.
	 * 
	 * @param className
	 * @return
	 */
	List<BeanInfo> searchByType(String className) throws ClassNotFoundException;

}
