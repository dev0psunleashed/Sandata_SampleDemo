package com.sandata.lab.rules.call.matching.app;

import java.util.Dictionary;

/*
import java.util.Collection;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;*/
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
import com.sandata.lab.rules.calls.matching.listeners.CEPWML;
import com.sandata.lab.rules.calls.matching.processors.CallProcessor;
import com.sandata.lab.rules.calls.model.CallPreferences;
*/

public class AppActivator implements BundleActivator {

	private Logger rulesLog = LoggerFactory.getLogger("rulesLog");
	private String version;
	private String info;
	
	@Override
	public void start(BundleContext context) throws Exception {
		Bundle bundle = context.getBundle();
		setVersion(bundle.getVersion());
		setInfo(bundle);
		getRulesLog().info(String.format("Started: %s" , getInfo()));
		//AppContext.ksession = null;
		//AppContext.ksession = getSession();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		getRulesLog().info(String.format("Stopped: %s" , getInfo()));


	}

	/*protected KieSession getSession() throws Exception {
		KieServices ks = null;
        KieBaseConfiguration config = null;
        KieContainer kcont = null;
        
        KieBase kbase = null;
        CEPWML cEPWML = null;
        try {
        	ks = KieServices.Factory.get();
        	config = ks.newKieBaseConfiguration();
        	config.setOption(EventProcessingOption.STREAM);
        	config.setOption(EqualityBehaviorOption.EQUALITY);
        	kcont = ks.getKieClasspathContainer();//getClass().getClassLoader());
        	//if(kcont == null) {
        	//	logger.info("KieContainer was null, trying raw");
        	//	kcont = ks.getKieClasspathContainer();
        	//}
        	//The way it is done in straight non osgi world
        	//KieContainer kcont = ks.getKieClasspathContainer();//rts.getClass().getClassLoader());
        	kbase = kcont.getKieBase("ceprules");
        	logger.info(kbase.toString());
        	if(AppContext.ksession == null) {
        		AppContext.ksession = kbase.newKieSession();
        		CallPreferences callPreferences = new CallPreferences("8001231234");
        		cEPWML = new CEPWML();
        		AppContext.ksession.addEventListener(cEPWML);
        		AppContext.ksession.setGlobal("callPreferences", callPreferences);
        		logger.info("===============================================================================================");
        		logger.info("===========================================       KieSession created.     =====================");
        		logger.info("===============================================================================================");
        		
            }
        	
        	return AppContext.ksession ;
        }
        catch(Exception ex) {
        	logger.error("Exception====================================================================================");
        	logger.error(ex.getLocalizedMessage());*/
	/*
        	logger.error("Exception====================================================================================");
        	
        	logger.error("ks:" + (ks == null?"null":ks.toString()) +
        			"config:" + (config==null?"null":config.toString()) + "kcont:" + (kcont==null?"null":kcont.toString()) +
        			"kbase:" + (kbase==null?"null":kbase.toString()));*//*
        	throw(new Exception(ex.getCause()));
        }
        	
	}*/

	public String getVersion() {
		return version;
	}

	private void setVersion(Version version) {
		try {
            String qualifier = version.getQualifier();
            if(qualifier != null && qualifier.length() > 0) {
                this.version = String.format("%d.%d.%d-%s",

                        version.getMajor(),
                        version.getMinor(),
                        version.getMicro(),
                        qualifier
                );
            }
            else {
                this.version = String.format("%d.%d.%d",
                        version.getMajor(),
                        version.getMinor(),
                        version.getMicro()
                );
            }
        }
        catch (Exception e) {
            getRulesLog().error("AppVersion: Constructor: EXCEPTION: " + e.getMessage());
        }

	}

	public String getInfo() {
		return info;
	}

	private void setInfo(Bundle bundle) {
		Dictionary dictionary = bundle.getHeaders();
		String bundleDescritpion = (String)dictionary.get("Bundle-Description");
		this.info = String.format("Bundle : [%s], Version : [%s], Description : [%s]", bundle.getSymbolicName(), this.version, bundleDescritpion); 
	}


	public Logger getRulesLog() {
		if(this.rulesLog == null) {
			this.rulesLog = LoggerFactory.getLogger("rulesLog");
		}
		return this.rulesLog;
	}

	public void setRulesLog(Logger rulesLog) {
		this.rulesLog = rulesLog;
	}
}
