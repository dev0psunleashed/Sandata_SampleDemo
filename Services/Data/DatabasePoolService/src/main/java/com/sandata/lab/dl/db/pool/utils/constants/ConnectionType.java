/*
 * Copyright (c) 2016. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.dl.db.pool.utils.constants;

/**
 * Enumerates the supported connection types/schemas.
 * <p/>
 *
 * @author David Rutgos
 */
public enum ConnectionType {

    COREDATA ("COREDATA"),
    LOBDATA ("LOBDATA"),
    METADATA ("METADATA"),
    COREDATAMART ("COREDATAMART");

    private final String connectionType;

    private ConnectionType(final String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        return this.connectionType;
    }
}
