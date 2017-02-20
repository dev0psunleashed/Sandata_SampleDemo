package com.sandata.lab.rules.exceptions.app;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.kie.api.runtime.KieSession;
public class AppContext {

	public static final int OFFSET_PAST = -24;
	public static final int OFFSET_FUTURE = 24;
	private static CamelContext camelContext;
	public static KieSession ksession;
	private AppContext() {}
	
	public static CamelContext getContext() { return camelContext;}
		
	public static CamelContext InitCamelContext(CamelContext context) {
		//PropertiesComponent pc = context.getComponent("properties", PropertiesComponent.class);
		//pc.setLocation("blueprint:blueprint:cep.engine.properties");
		camelContext = context;

		return camelContext;
	}
	
}
