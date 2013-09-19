package com.arthur.bean.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.arthur.bean.details.BeanInfo;
import com.arthur.bean.factory.util.SpringBeanUtils;
import com.arthur.bean.packages.PackageBeanSupport;
import com.arthur.bean.service.BeanService;
import com.google.common.collect.Lists;

/**
 * 
 * @author arthur
 * 
 */
public class BeanServiceImpl implements BeanService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeanServiceImpl.class);

	private PackageBeanSupport packageBeanSupport;

	public List<BeanInfo> searchByName(String beanName) {
		Map<String, ?> allBeans = packageBeanSupport.allBeans();
		if (!CollectionUtils.isEmpty(allBeans)) {
			List<BeanInfo> searchedBeans = Lists.newArrayList();
			for (String bName : allBeans.keySet()) {
				if (StringUtils.contains(bName, beanName)) {
					searchedBeans.add(this.buildBeanInfo(bName,
							allBeans.get(bName)));
				}
			}
			LOGGER.debug("find {} beans by bean name[{}]",
					searchedBeans.size(), beanName);
		}

		return null;
	}

	public List<BeanInfo> searchByType(String className)
			throws ClassNotFoundException {
		Class<?> beanType = Class.forName(className);
		Map<String, ?> beanMap = SpringBeanUtils
				.beanOfTypeIncludingAncestor(beanType);

		if (!CollectionUtils.isEmpty(beanMap)) {
			Map<String, ?> allBeans = packageBeanSupport.allBeans();
			if (CollectionUtils.isEmpty(allBeans)) {
				List<BeanInfo> searchedBeans = Lists.newArrayList();
				for (String name : beanMap.keySet()) {
					searchedBeans.add(this.buildBeanInfo(name,
							beanMap.get(name)));
				}

				LOGGER.debug("find {} beans by beanType[{}]",
						searchedBeans.size(), className);
				return searchedBeans;
			}
		}

		return null;
	}

	/**
	 * Build BeanInfo with bean name and bean instance.
	 * 
	 * @param beanName
	 * @param instance
	 * @return
	 */
	private BeanInfo buildBeanInfo(String beanName, Object instance) {
		BeanInfo beanInfo = new BeanInfo(beanName, instance);

		String[] dependencies = SpringBeanUtils
				.getDependenciesForBean(beanName);
		Object dependencyBean;
		if (!ArrayUtils.isEmpty(dependencies)) {
			for (String dependencyName : dependencies) {
				dependencyBean = SpringBeanUtils.getBeanByName(dependencyName);
				BeanInfo dependencyInfo = new BeanInfo(dependencyName,
						dependencyBean);

				dependencyInfo
						.setClassName(dependencyBean.getClass().getName());
				dependencyInfo.setPackageName(StringUtils.substringBeforeLast(
						dependencyName, "."));

				List<BeanInfo> dependencyBeans = beanInfo.getDependencies();
				if (dependencyBeans == null) {
					dependencyBeans = Lists.newArrayListWithCapacity(8);
					beanInfo.setDependencies(dependencyBeans);
				}
				dependencyBeans.add(dependencyInfo);
			}
		}

		return beanInfo;
	}
}
