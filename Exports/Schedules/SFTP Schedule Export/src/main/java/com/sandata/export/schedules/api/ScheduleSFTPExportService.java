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

package com.sandata.export.schedules.api;

import org.apache.camel.Exchange;

/**
 * Staff intake service that defines the CRUD interface.
 * <p/>
 *
 * @author David Rutgos
 */
public interface ScheduleSFTPExportService {

    public void exportSchedules(Exchange exchange);

}
