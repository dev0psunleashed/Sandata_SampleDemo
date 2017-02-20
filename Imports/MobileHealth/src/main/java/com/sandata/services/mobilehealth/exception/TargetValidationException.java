package com.sandata.services.mobilehealth.exception;

/**
 * Copied from EvvBatch
 * 
 * @author khangle
 */
public class TargetValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public TargetValidationException(String message) {
        super(message);
    }
    public TargetValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
