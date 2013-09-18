package com.arthur.bean.packages;

import java.util.List;

public class CompositePackageFilter implements PackageFilter {

	private List<PackageFilter> packageFilter;

	public boolean ignorePackage(String packageName) {
		for (PackageFilter filter : packageFilter) {
			if (filter.ignorePackage(packageName))
				return true;
		}
		return false;
	}

	public List<PackageFilter> getPackageFilter() {
		return packageFilter;
	}

	public void setPackageFilter(List<PackageFilter> packageFilter) {
		this.packageFilter = packageFilter;
	}
}
