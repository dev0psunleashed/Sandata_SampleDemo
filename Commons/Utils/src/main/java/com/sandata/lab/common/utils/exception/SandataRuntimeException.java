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

package com.sandata.lab.common.utils.exception;

import org.apache.camel.Exchange;

/**
 * Date: 8/13/13
 * Time: 11:17 AM
 */
public class SandataRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SandataRuntimeException(Exchange exchange, String message) {
        super(message);

        setMessageToExchangeHeader(exchange, message);
    }

    public SandataRuntimeException(Exchange exchange, String message, Throwable cause) {
        super(message, cause);

        setMessageToExchangeHeader(exchange, message);
    }
    
    public SandataRuntimeException(String message) {
        super(message);
    }

    public SandataRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    private void setMessageToExchangeHeader(Exchange exchange, String message) {
        exchange.getIn().setHeader("SANDATA_ERR_MSG", message);
    }
}
