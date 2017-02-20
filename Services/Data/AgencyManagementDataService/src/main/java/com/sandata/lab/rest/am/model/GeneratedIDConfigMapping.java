package com.sandata.lab.rest.am.model;

import java.util.Map;

/**
 * Models the model that include configuration mapping to generated staff/patient id endpoints;
 * <p/>
 *
 * @author Thanh.le
 */
public class GeneratedIDConfigMapping {

	private Map<String,String> configMap;

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

   
}
