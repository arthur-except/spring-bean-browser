package com.arthur.bean.details;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Bean information.<br/>
 * 
 * @author arthur
 * 
 */
public class BeanInfo implements Serializable {

	private static final long serialVersionUID = -4599886362409932204L;

	private String beanName;

	private String packageName;

	private String className;

	private Object instance;

	private List<BeanInfo> dependencies;

	public BeanInfo() {
	}

	public BeanInfo(String beanName, Object instance) {
		this.beanName = beanName;
		this.instance = instance;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public List<BeanInfo> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<BeanInfo> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("[packageName:")
				.append(packageName).append(", className:").append(className)
				.append(", beanName:").append(beanName).append(", instance:")
				.append(instance).append(", denpendencies:")
				.append(dependencies).append("]").toString();
	}
}
