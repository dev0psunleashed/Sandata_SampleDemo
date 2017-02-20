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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements standard OSGi bundle version qualifier.
 * <p/>
 *
 * @author David Rutgos
 */
public class AppVersion {

    private static Logger logger = LoggerFactory.getLogger(AppVersion.class);
    private String build;

    public AppVersion(Bundle bundle) {
        try {
            String e = bundle.getVersion().getQualifier();
            if(e != null && e.length() > 0) {
                this.build = String.format("%d.%d.%d-%s", new Object[]{Integer.valueOf(bundle.getVersion().getMajor()), Integer.valueOf(bundle.getVersion().getMinor()), Integer.valueOf(bundle.getVersion().getMicro()), e});
            } else {
                this.build = String.format("%d.%d.%d", new Object[]{Integer.valueOf(bundle.getVersion().getMajor()), Integer.valueOf(bundle.getVersion().getMinor()), Integer.valueOf(bundle.getVersion().getMicro())});
            }
        } catch (Exception var3) {
            logger.error("AppVersion: Constructor: EXCEPTION: " + var3.getMessage());
        }

    }

    public String getBuild() {
        return this.build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String toString() {
        return this.getBuild();
    }
}
