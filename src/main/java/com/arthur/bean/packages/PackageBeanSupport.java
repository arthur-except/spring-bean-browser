package com.arthur.bean.packages;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.arthur.bean.factory.util.SpringBeanUtils;
import com.google.common.collect.Sets;

public abstract class PackageBeanSupport {

	private Map<String, Set<?>> packageToBeansMap = new ConcurrentHashMap<String, Set<?>>(
			64);

	private PackageFilter compositePackageFilter;

	private boolean ignoreFrameworkPackage = false;

	public Set<String> allPackagesIncludingAncestor() {
		if (packageToBeansMap.size() == 0) {
			Map<String, ?> beanMap = SpringBeanUtils
					.beanOgTypeIncludingAncestor(Object.class);
			if (beanMap != null && !CollectionUtils.isEmpty(beanMap)) {
				for (Map.Entry<String, ?> entry : beanMap.entrySet()) {
					// TODO:是否需要过滤掉某些bean?
					String className = entry.getValue().getClass().getName();
					String packageName = StringUtils.substringBefore(className,
							".");
					Set<Object> beanSet = Sets.newHashSetWithExpectedSize(16);
					beanSet.add(entry.getValue());
					packageToBeansMap.put(packageName, beanSet);
				}
			}
		}
		return packageToBeansMap.keySet();
	}

	protected abstract void filterPackage(PackageFilter compositePackageFilter,
			String packageName);

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
