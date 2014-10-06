package net.martenscs.osgi.mybatis.blueprint.example.objfactory;

import java.util.Map;

public class ReflectiveObjectProperties {

	private String className;
	private Map<String, Object> propertiesMap;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, Object> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, Object> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

}
