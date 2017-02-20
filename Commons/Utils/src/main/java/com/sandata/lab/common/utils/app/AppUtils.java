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

package com.sandata.lab.common.utils.app;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;

/**
 * Implements common applications utils for OSGi bundles.
 * <p/>
 * (Optional) Additional detail about the class that we may want to document
 *
 * @author David Rutgos
 */
public class AppUtils {

    public AppUtils() {
    }

    public static String startInfo(BundleContext context, String version) {
        Bundle bundle = context.getBundle();
        Dictionary dictionary = bundle.getHeaders();
        String bundleDescription = (String)dictionary.get("Bundle-Description");
        if(bundleDescription == null || bundleDescription.length() == 0) {
            bundleDescription = "[Description Not Set]";
        }

        String info = String.format("Started: [%s] [%s]: %s", new Object[]{bundle.getSymbolicName(), version, bundleDescription});
        System.out.println(info);
        return info;
    }

    public static String stopInfo(BundleContext context, String version) {
        Bundle bundle = context.getBundle();
        Dictionary dictionary = bundle.getHeaders();
        String bundleName = (String)dictionary.get("Bundle-Name");
        String info = String.format("Stopped: [%s] [%s]: %s", new Object[]{bundle.getSymbolicName(), version, bundleName});
        System.out.println(info);
        return info;
    }
}
