package com.arthur.bean.packages;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.collect.Sets;

/**
 * 
 * @author Arthur
 *
 */
public class DefaultPackageFilter implements PackageFilter, InitializingBean {

	private Set<String> ignorePackages;

	public boolean ignorePackage(String packageName) {
		for(String ignore : ignorePackages){
			if(StringUtils.contains(packageName, ignore) || StringUtils.contains(ignore, packageName))
				return true;
		}
		return false;
	}

	public void afterPropertiesSet() throws Exception {
		if (ignorePackages == null) {
			// No config ignorePackagse, user defualt.
			this.initDefaultIgorePackage();
		}
	}

	public void initDefaultIgorePackage() {
		ignorePackages = Sets.newHashSetWithExpectedSize(16);
		ignorePackages.add(PackageConstant.SPRING_PACKAGE);
		ignorePackages.add(PackageConstant.APACHE_PACKAGE);
		ignorePackages.add(PackageConstant.MYBATIS_SPRING_PACKAGE);
		ignorePackages.add(PackageConstant.HIBERNATE_PACKAGE);
		ignorePackages.add(PackageConstant.JERSEY_PACKAGE);
	}

	public Set<String> getIgnorePackages() {
		return ignorePackages;
	}

	public void setIgnorePackages(Set<String> ignorePackages) {
		this.ignorePackages = ignorePackages;
	}
	
}
