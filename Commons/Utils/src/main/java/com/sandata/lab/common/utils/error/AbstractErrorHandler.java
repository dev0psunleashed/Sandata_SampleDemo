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

package com.sandata.lab.common.utils.error;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.DefaultLogger;
import com.sandata.lab.common.utils.log.SandataLogger;

/**
 * Implements an abstract error handler class that will process error/exceptions in a consistent way.
 * <p/>
 *
 * @author David Rutgos
 */
public abstract class AbstractErrorHandler {

    protected SandataLogger logger;

    protected AbstractErrorHandler() {
    }

    protected AbstractErrorHandler(final SandataLogger logger) {
        this.logger = logger;
    }

    public void handleFatalError(final Throwable throwable, final String methodName) throws SandataRuntimeException {

        throwable.printStackTrace();

        String errMsg = String.format("%s: %s: Fatal: EXCEPTION: %s: %s",
                getClass().getName(),
                methodName,
                throwable.getClass().getName(),
                throwable.getMessage()
        );

        getLogger().error(errMsg);

        throw new SandataRuntimeException(errMsg);
    }

    public void logException(final Throwable throwable, final String methodName) {

        String errMsg = String.format("%s: %s: EXCEPTION: %s: %s",
                getClass().getName(),
                methodName,
                throwable.getClass().getName(),
                throwable.getMessage()
        );

        getLogger().error(errMsg);
    }

    public void setLogger(final SandataLogger logger) {
        this.logger = logger;
    }

    public SandataLogger getLogger() {

        // If the logger was not injected, then create a default logger instance
        if (this.logger == null) {
            this.logger = DefaultLogger.CreateLogger();
        }

        return this.logger;
    }
}
