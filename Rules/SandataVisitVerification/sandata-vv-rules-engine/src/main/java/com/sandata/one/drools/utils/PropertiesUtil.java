package com.sandata.one.drools.utils;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Load the pre-defined properties for application. 
 * @author richardwu
 * @version 1.0
 */

public abstract class PropertiesUtil
{
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadPropertiesFromFile (String fileName) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			properties.load(inputStream);
			for (String property : properties.stringPropertyNames()) {
				String value = properties.getProperty(property);
				logger.info("Loading properties from file : " + fileName + " [" + property + "=" + value.length() + "]");
			}
		} catch (IOException e) {
			logger.error("cannot load property file: " + fileName + " Error message: " + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

} 