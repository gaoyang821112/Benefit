package com.gaoyang.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties applicationProperties;

	public static Properties getApplicationProperties() {
		return applicationProperties;
	}

	static {
		getPropertiesContext();
	}
	
	/**
	 * 获得Spring框架应用上下文对�?
	 * 
	 * @return ApplicationContext
	 */
	public static void getPropertiesContext() {
		String propFile = "/application.properties";
		if (applicationProperties == null) {
			try {
				applicationProperties = new Properties();
				InputStream stream = PropertiesUtils.class.getResourceAsStream(propFile);
				applicationProperties.load(stream);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
