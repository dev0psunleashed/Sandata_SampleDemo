package com.sandata.lab.rules.visit.exception.service.app;

import com.sandata.lab.common.utils.app.AppUtils;
import com.sandata.lab.common.utils.app.AppVersion;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Application activator that is the entry point for the bundle.</p>
 *
 * @author jasonscott
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
