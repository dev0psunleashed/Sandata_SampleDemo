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

package com.sandata.lab.dl.db.pool.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;

import java.sql.Connection;

/**
 * Contract for retrieving @java.sql.Connection instances.
 * <p/>
 *
 * @author David Rutgos
 */
public interface ConnectionPoolDataService {

    Connection getConnection() throws SandataRuntimeException;
    Connection getConnection(ConnectionType connectionType) throws SandataRuntimeException;
    void close(Connection conn) throws SandataRuntimeException;
}
