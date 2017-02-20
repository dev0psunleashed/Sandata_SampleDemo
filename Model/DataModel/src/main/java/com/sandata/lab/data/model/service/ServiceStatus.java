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

package com.sandata.lab.data.model.service;

/**
 * Enum that defines the different service codes that can be returned by a REST endpoint.
 * <p/>
 *
 * @author David Rutgos
 */
public enum ServiceStatus {

    /**
     * Request was successful.
     */
    SUCCESS(200),

    /**
     * Request was successful.
     */
    BAD_REQUEST(400),

    /**
     * Request does not have sufficient permissions
     */
    FORBIDDEN(403),

    /**
     * The specified resource does not exist.
     */
    NOT_FOUND(404),

    /**
     * The resource doesn't support the specified HTTP verb
     */
    METHOD_NOT_ALLOWED(405),

    /**
     * The specified account already exists.
     */
    CONFLICT(405),

    /**
     * The server encountered an internal error.
     */
    INTERNAL_SERVER_ERROR (500),

    /**
     * The server is currently unable to receive requests
     */
    SERVICE_UNAVAILABLE (503),

    /**
     * Request failed.
     */
    FAILED(500),

    /**
     * Request failed.
     */
    RULE_FAILURE(500);

    /**
     * Response code.
     */
    private final int status;

    /**
     * Set the response code in the constructor.
     *
     * @param status
     */
    ServiceStatus(final int status) {
        this.status = status;
    }

    /**
     * Return the response code.
     *
     * @return status
     */
    public int getCode() {
        return status;
    }
}
