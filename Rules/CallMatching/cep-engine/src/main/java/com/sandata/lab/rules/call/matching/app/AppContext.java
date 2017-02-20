package com.sandata.lab.rules.call.matching.app;

import org.apache.camel.CamelContext;
import org.kie.api.runtime.KieSession;
public class AppContext {

	public static final int OFFSET_PAST = -24;
	public static final int OFFSET_FUTURE = 24;
	private static CamelContext camelContext;
	public static KieSession ksession;
	private AppContext() {}
	
	public static CamelContext getContext() { return camelContext;}
		
	public static CamelContext InitCamelContext(CamelContext context) {
		AppContext.camelContext = context;
		return AppContext.camelContext;
	}
	
}
