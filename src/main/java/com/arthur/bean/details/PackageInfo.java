package com.arthur.bean.details;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Package info to facility unfolding package by level.
 * 
 * @author arthur
 * 
 */
public class PackageInfo implements Serializable {

	private static final long serialVersionUID = 2284484014655737013L;

	private String currentPackageName;

	private Set<String> subPackageName;

	public String getCurrentPackageName() {
		return currentPackageName;
	}

	public void setCurrentPackageName(String currentPackageName) {
		this.currentPackageName = currentPackageName;
	}

	public Set<String> getSubPackageName() {
		return subPackageName;
	}

	public void setSubPackageName(Set<String> subPackageName) {
		this.subPackageName = subPackageName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("[currentPackageName:")
				.append(currentPackageName).append(", subPacakageName:")
				.append(subPackageName).toString();
	}

}
