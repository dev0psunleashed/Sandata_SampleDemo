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

package com.sandata.lab.billing.edi.app;

import com.sandata.lab.common.utils.app.AppUtils;
import com.sandata.lab.common.utils.app.AppVersion;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Activator class to log out information about the application when starting or stopping
 */
public class AppActivator implements BundleActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppActivator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {

        LOGGER.info(AppUtils.startInfo(context, new AppVersion(context.getBundle()).toString()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(BundleContext context) throws Exception {

        LOGGER.info(AppUtils.stopInfo(context, new AppVersion(context.getBundle()).toString()));
    }
}
