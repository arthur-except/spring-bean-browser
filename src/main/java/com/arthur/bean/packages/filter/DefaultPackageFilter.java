package com.arthur.bean.packages.filter;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.arthur.bean.packages.PackageConstant;
import com.google.common.collect.Sets;

/**
 * Default package implement. <br/>
 * This implement take excludedPackages high priority.
 * 
 * @author Arthur
 * 
 */
public class DefaultPackageFilter implements PackageFilter, InitializingBean {

	private Set<String> excludedPackages;

	private Set<String> includedPackages;

	public void afterPropertiesSet() throws Exception {
		if (excludedPackages == null) {
			// No config ignorePackagse, user defualt.
			this.initDefaultIgorePackage();
		}
	}

	public boolean ignorePackage(String packageName) {
		if (isExcludedPackages(packageName, excludedPackages))
			return true;
		return this.isIncludedPackages(packageName, includedPackages);
	}

	protected boolean isExcludedPackages(String packageName,
			Set<String> excludedPackages) {
		for (String ignore : excludedPackages) {
			if (StringUtils.contains(packageName, ignore)
					|| StringUtils.contains(ignore, packageName))
				return true;
		}
		return false;
	}

	protected boolean isIncludedPackages(String packageName,
			Set<String> includedPackages) {
		if (!CollectionUtils.isEmpty(includedPackages)) {
			for (String pkgName : includedPackages) {
				if (StringUtils.startsWith(packageName, pkgName)) {
					return true;
				}
			}
		}
		return false;
	}

	public void initDefaultIgorePackage() {
		excludedPackages = Sets.newHashSetWithExpectedSize(16);
		excludedPackages.add(PackageConstant.SPRING_PACKAGE);
		excludedPackages.add(PackageConstant.APACHE_PACKAGE);
		excludedPackages.add(PackageConstant.MYBATIS_SPRING_PACKAGE);
		excludedPackages.add(PackageConstant.HIBERNATE_PACKAGE);
		excludedPackages.add(PackageConstant.JERSEY_PACKAGE);
	}

	public Set<String> getExcludedPackages() {
		return excludedPackages;
	}

	public void setExcludedPackages(Set<String> excludedPackages) {
		this.excludedPackages = excludedPackages;
	}

	public Set<String> getIncludedPackages() {
		return includedPackages;
	}

	public void setIncludedPackages(Set<String> includedPackages) {
		this.includedPackages = includedPackages;
	}

}
