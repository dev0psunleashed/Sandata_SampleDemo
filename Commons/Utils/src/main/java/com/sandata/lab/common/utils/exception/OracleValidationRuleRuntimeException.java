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

/**
 * User: JNordstrom
 * Date: 10/21/13
 * Time: 3:31 PM
 */
public class OracleValidationRuleRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OracleValidationRuleRuntimeException(String message) {
        super(message);
    }
}