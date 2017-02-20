package com.sandata.lab.rules.call.matching.rules.exceptions;

/**
 * Created by thanhxle on 12/22/2016.
 */
public class SandataRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SandataRuntimeException (String message) {
		super(message);
	}
	
	
	public SandataRuntimeException (String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public SandataRuntimeException (Throwable throwable) {
		super(throwable);
	}
	
}
