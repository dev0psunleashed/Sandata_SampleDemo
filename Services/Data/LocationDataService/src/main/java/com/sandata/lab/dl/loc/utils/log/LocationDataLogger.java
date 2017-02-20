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

package com.sandata.lab.dl.loc.utils.log;
import com.sandata.lab.common.utils.log.SandataLogger;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example implementation of a SandataLogger with built in metrics. Delete/Rename before use!
 * <p/>
 *
 * @author Ralph Sylvain
 */


public class LocationDataLogger extends SandataLogger {

    private static Logger logger = LoggerFactory.getLogger("LocationDataLogger");

    public static SandataLogger CreateLogger() {

        return new LocationDataLogger();
    }

    public static SandataLogger CreateLogger(final Exchange exchange) {

        return new LocationDataLogger(exchange);
    }

    private LocationDataLogger() {
        super();
    }

    private LocationDataLogger(final Exchange exchange) {
        super(exchange);
    }

    @Override
    public String processName() {
        return "LocationData::SandataLogger";
    }

    @Override
    public Logger logger() {
        return logger;
    }
}
