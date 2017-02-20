package com.sandata.lab.quartzds;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 12/24/13
 * Time: 2:34 PM
 */

public class AppActivator implements BundleActivator {

    private static Logger logger = LoggerFactory.getLogger(AppActivator.class);

    @Override
    public void start(BundleContext context) throws Exception {

        logger.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {

        logger.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
    }
}
