package com.sandata.lab.quartzds;

import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 1/16/14
 * Time: 3:45 AM
 */

public class AppVersion {

    private static Logger logger = LoggerFactory.getLogger(AppVersion.class);

    private String version;

    public AppVersion(Bundle bundle) {
        try {
            String qualifier = bundle.getVersion().getQualifier();
            if(qualifier != null && qualifier.length() > 0) {
                version = String.format("%d.%d.%d-%s",

                        bundle.getVersion().getMajor(),
                        bundle.getVersion().getMinor(),
                        bundle.getVersion().getMicro(),
                        qualifier
                );
            }
            else {
                version = String.format("%d.%d.%d",

                        bundle.getVersion().getMajor(),
                        bundle.getVersion().getMinor(),
                        bundle.getVersion().getMicro()
                );
            }
        }
        catch (Exception e) {
            logger.error("AppVersion: Constructor: EXCEPTION: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return version;
    }
}
