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

package com.sandata.lab.dl.vv.utils.log;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.logger.api.LoggerService;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VisitEventRepositoryLogger extends SandataLogger {

    private static Logger logger = LoggerFactory.getLogger("VisitEventRepositoryLogger");

    public static SandataLogger CreateLogger() {

        return new VisitEventRepositoryLogger();
    }

    public static SandataLogger CreateLogger(final Exchange exchange) {

        return new VisitEventRepositoryLogger(exchange);
    }
    public static SandataLogger CreateLogger(final Exchange exchange, final LoggerService loggerService) {

        return new VisitEventRepositoryLogger(exchange, loggerService);
    }

    private VisitEventRepositoryLogger() {
        super();
    }

    private VisitEventRepositoryLogger(final Exchange exchange) {
        super(exchange);
    }

    private VisitEventRepositoryLogger(final Exchange exchange, final LoggerService service) {
        super(exchange, service);
    }

    @Override
    public String processName() {
        return "VisitEventRepositoryLogger:sandata-visit-event-data-service";
    }

    @Override
    public Logger logger() {
        return logger;
    }
}
