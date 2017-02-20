package com.sandata.lab.common.utils.exception;

import org.apache.camel.Exchange;

/**
 * Created by vuto on 11/15/2016.
 */
public class SandataValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SandataValidationException(Exchange exchange, String message) {
        super(message);

        exchange.getIn().setHeader("SANDATA_ERR_MSG", message);
    }

    public SandataValidationException(String message) {
        super(message);
    }

    public SandataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}