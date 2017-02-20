package com.sandata.lab.rules.dl.app;

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
 * Date: 6/7/2016
 * Time: 12:26 PM
 */
public class AppActivator implements BundleActivator {
    private static Logger logger = LoggerFactory.getLogger(AppActivator.class);
    private String version;
    private String info;

    @Override
    public void start(BundleContext context) throws Exception {
        Bundle bundle = context.getBundle();
        setVersion(bundle.getVersion());
        setInfo(bundle);
        logger.info(String.format("Started: %s" , getInfo()));
        Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
        visitDlLog.info(String.format("Started: %s" , getInfo()));

        //AppContext.ksession = null;
        //AppContext.ksession = getSession();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info(String.format("Stopped: %s" , getInfo()));
        Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
        visitDlLog.info(String.format("Stopped: %s" , getInfo()));

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


}