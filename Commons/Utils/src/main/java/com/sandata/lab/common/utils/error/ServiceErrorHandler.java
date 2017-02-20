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

import com.sandata.lab.common.utils.data.response.Response;
import com.sandata.lab.common.utils.data.response.ServiceStatus;
import com.sandata.lab.common.utils.exception.SandataValidationException;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 8/19/13
 * Time: 11:56 AM
 */

public class ServiceErrorHandler {

    /**
     * Logger.
     */
    private static final Logger logger =
            LoggerFactory.getLogger(ServiceErrorHandler.class);

    /**
     * Wrap error in response object.
     * Errors only, validation should be handled elsewhere
     *
     * @param exchange
     */
    public final void handleError(final Exchange exchange) {
        Throwable cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);

        Message message = exchange.getIn();
        Response response = new Response();
        response.setStatus(ServiceStatus.FAILED);
        response.setId((String)message.getHeader("SANDATA_LOGGER_ID"));
        response.setMessageSummary("ERROR");

        String errorMessage = cause.getMessage();
        String messageDetail = ExceptionUtils.getFullStackTrace(cause);

        response.setErrorMessage(errorMessage);
        response.setMessageDetail(messageDetail);

        message.setBody(javax.ws.rs.core.Response.serverError().entity(response).build());

        if(cause instanceof SandataValidationException){
            response.setStatus(ServiceStatus.BAD_REQUEST);
            message.setBody(javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity(response).build());
        }

        logger.error(String.format("SANDATA_MDW_ERROR: %s", messageDetail));
    }
}
