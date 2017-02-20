/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.dl.vv.app;

import com.sandata.lab.common.utils.app.AppUtils;
import com.sandata.lab.common.utils.app.AppVersion;
import com.sandata.lab.dl.vv.api.VisitEventDataService;
import com.sandata.lab.dl.vv.impl.VisitEventRepository;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppActivator implements BundleActivator {

    private static Logger logger = LoggerFactory.getLogger(AppActivator.class);

    @Override
    public void start(BundleContext context) throws Exception {

        logger.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
        Logger visitEventLogger = LoggerFactory.getLogger("visitEventLogger");
        visitEventLogger.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {

        logger.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
        Logger visitEventLogger = LoggerFactory.getLogger("visitEventLogger");
        visitEventLogger.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
    }
}
