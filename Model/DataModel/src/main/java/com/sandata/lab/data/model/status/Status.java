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

package com.sandata.lab.data.model.status;

/**
 * Enum that defines the different status states.
 * <p/>
 *
 * @author David Rutgos
 */
public enum Status {

    SUCCESS ("SUCCESS"),
    FAILED ("FAILED"),
    PENDING ("PENDING"),
    PROCESSING ("PROCESSING"),
    PROCESSED ("PROCESSED");

    private final String status;

    Status(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
