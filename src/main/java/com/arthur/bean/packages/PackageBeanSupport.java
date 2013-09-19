package com.arthur.bean.packages;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.arthur.bean.factory.util.SpringBeanUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Pacakge beans basic support class
 * 
 * @author arthur
 * 
 */
public class PackageBeanSupport {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PackageBeanSupport.class);

	/** package name --> map<beanName, bean> */
	private Map<String, Map<String, ?>> packageToBeansMap = new ConcurrentHashMap<String, Map<String, ?>>(
			32);

	private PackageFilter compositePackageFilter;

	private boolean ignoreFrameworkPackage = false;

	@SuppressWarnings("unchecked")
	public List<String> allPackagesIncludingAncestor() {
		checkAndFillPackageToBeansMap();
		Set<String> temp = packageToBeansMap.keySet();
		if (CollectionUtils.isEmpty(temp)) {
			return Collections.EMPTY_LIST;
		}
		// Sort packages.
		List<String> packages = Lists.newArrayList();
		packages.addAll(temp);
		Collections.sort(packages);
		return packages;
	}

	/**
	 * Enable subclass to override this method to custom init packageToBeansMap.
	 */
	protected void initPackageToBeansMap(
			Map<String, Map<String, ?>> packageToBeansMap,
			PackageFilter compositeFilter, boolean ignoreFrameworkPackage) {
		long startTime = System.currentTimeMillis();
		Map<String, ?> allBeans = SpringBeanUtils
				.beanOfTypeIncludingAncestor(Object.class);

		LOGGER.info("find {} beans from application context",
				allBeans == null ? 0 : allBeans.size());
		String className;
		String packageName;
		if (allBeans != null && !CollectionUtils.isEmpty(allBeans)) {
			for (Map.Entry<String, ?> entry : allBeans.entrySet()) {
				// Get class name of bean
				className = entry.getValue().getClass().getName();
				packageName = StringUtils.substringBeforeLast(className, ".");

				if (ignoreFrameworkPackage
						&& compositePackageFilter.ignorePackage(packageName)) {
					continue;
				}

				Map<String, Object> beanMap = Maps
						.newHashMapWithExpectedSize(16);
				beanMap.put(entry.getKey(), entry.getValue());
				packageToBeansMap.put(packageName, beanMap);
			}
		}
		LOGGER.info("init packageToBeansMap completed consuming {}ms",
				System.currentTimeMillis() - startTime);
	}

	private void checkAndFillPackageToBeansMap() {
		if (packageToBeansMap.size() == 0) {
			this.initPackageToBeansMap(packageToBeansMap,
					compositePackageFilter, ignoreFrameworkPackage);
		}
	}

	/**
	 * Get beans of this package and its sub packages.
	 * 
	 * @param packageName
	 * @return
	 */
	public Map<String, Object> beansOfPackage(String packageName) {
		checkAndFillPackageToBeansMap();
		Map<String, Object> beansOfPkg = Maps.newHashMapWithExpectedSize(16);
		for (String pkg : packageToBeansMap.keySet()) {
			// Check same or sub package.
			if (StringUtils.equals(pkg, packageName)
					|| StringUtils.indexOf(pkg, packageName) != -1) {
				beansOfPkg.putAll(packageToBeansMap.get(pkg));
			}
		}
		LOGGER.debug("Find {} bean in package[{}]", beansOfPkg.size(),
				packageName);
		return beansOfPkg;
	}

	public Map<String, Object> allBeans() {
		checkAndFillPackageToBeansMap();

		Map<String, Object> beans = Maps.newHashMapWithExpectedSize(128);
		for (Map<String, ?> map : packageToBeansMap.values()) {
			beans.putAll(map);
		}
		return beans;
	}

	public PackageFilter getCompositePackageFilter() {
		return compositePackageFilter;
	}

	public void setCompositePackageFilter(PackageFilter compositePackageFilter) {
		this.compositePackageFilter = compositePackageFilter;
	}

	public boolean isIgnoreFrameworkPackage() {
		return ignoreFrameworkPackage;
	}

	public void setIgnoreFrameworkPackage(boolean ignoreFrameworkPackage) {
		this.ignoreFrameworkPackage = ignoreFrameworkPackage;
	}

}
