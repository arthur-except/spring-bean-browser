package com.arthur.bean.packages.filter;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

public class CompositePackageFilter implements PackageFilter, InitializingBean {

	private List<PackageFilter> packageFilter;

	public void afterPropertiesSet() throws Exception {
		if(CollectionUtils.isEmpty(packageFilter)){
			// Default enable excludePackageFilter
		}
	}
	

	public boolean ignorePackage(String packageName) {
		return false;
	}
	
	public List<PackageFilter> getPackageFilter() {
		return packageFilter;
	}

	public void setPackageFilter(List<PackageFilter> packageFilter) {
		this.packageFilter = packageFilter;
	}
	
}
