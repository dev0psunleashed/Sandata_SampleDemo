package com.sandata.lab.rules.call.matching.service.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Constant class for the properties. 
 * @author richardwu
 *
 */
public final class SandataConstants {
	private static Logger logger = LoggerFactory.getLogger(SandataConstants.class);

	private static String FILE_NAME = "./sandata.properties";
	static Properties properties = PropertiesUtil.loadPropertiesFromFile(FILE_NAME);

	public static String AMQ_USER_NAME_VALUE = properties.getProperty("AMQ_USER_NAME");;
	public static String AMQ_PASSWORD_VALUE = properties.getProperty("AMQ_PASSWORD");
	public static String AMQ_URI_VALUE = properties.getProperty("AMQ_URI");

	

}
