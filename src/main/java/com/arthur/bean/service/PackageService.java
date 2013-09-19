package com.arthur.bean.service;

import java.util.List;

import com.arthur.bean.details.BeanInfo;

/**
 * 
 * 
 * @author arthur
 * 
 */
public interface PackageService {

	/**
	 * Package names sorted asc that include bean instance.
	 * 
	 * @return
	 */
	List<String> packagesIncludingBeans();

	/**
	 * Get all beans of package and its sub package.
	 * 
	 * @param packageName
	 * @return
	 */
	List<BeanInfo> beansOfPackageIncludingSub(String packageName);

}
