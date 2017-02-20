package com.sandata.lab.quartzds;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;

/**
 * Date: 1/16/14
 * Time: 3:43 AM
 */

public class AppUtils {

    public static String startInfo(BundleContext context, String version) {

        Bundle bundle = context.getBundle();

        Dictionary dictionary = bundle.getHeaders();

        String bundleDescription = (String)dictionary.get("Bundle-Description");

        String info = String.format("Started: [%s] [%s]: %s", bundle.getSymbolicName(), version, bundleDescription);

        System.out.println(info);

        return info;
    }

    public static String stopInfo(BundleContext context, String version) {

        Bundle bundle = context.getBundle();

        Dictionary dictionary = bundle.getHeaders();

        String bundleDescription = (String)dictionary.get("Bundle-Description");

        String info = String.format("Stopped: [%s] [%s]: %s", bundle.getSymbolicName(), version, bundleDescription);

        System.out.println(info);

        return info;
    }
}
