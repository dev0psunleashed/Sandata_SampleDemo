package com.sandata.lab.rules.callcreator.app;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/5/2016
 * Time: 12:15 PM
 */
public class AppActivator implements BundleActivator {

    private static Logger logger = LoggerFactory.getLogger(AppActivator.class);
    private Logger callCreatorLog = LoggerFactory.getLogger("callCreatorLog");
    private String version;
    private String info;

    @Override
    public void start(BundleContext context) throws Exception {
        Bundle bundle = context.getBundle();

        setVersion(bundle.getVersion());
        setInfo(bundle);
        logger.info(String.format("Started: %s" , getInfo()));
        getCallCreatorLog().info(String.format("Started: %s" , getInfo()));
        //AppContext.ksession = null;
        //AppContext.ksession = getSession();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info(String.format("Stopped: %s" , getInfo()));
        getCallCreatorLog().info(String.format("Stopped: %s" , getInfo()));

    }
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
            logger.error("AppVersion: Constructor: EXCEPTION: " + e.getMessage());
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

    public Logger getCallCreatorLog() {
        if(this.callCreatorLog != null ) {
            return this.callCreatorLog;
        }
        else {
            this.callCreatorLog = LoggerFactory.getLogger("callCreatorLog");
            return this.callCreatorLog;
        }
    }

    public void setCallCreatorLog(Logger callCreatorLog) {
        this.callCreatorLog = callCreatorLog;
    }
}

