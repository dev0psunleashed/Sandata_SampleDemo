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

package com.sandata.lab.wcf.export.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.response.Response;

import java.util.Date;

/**
 * Export services that exposes WCF export endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public interface ExportService {

    public Response exportSchedule(int staffId, int patientId, Date startDate, Date endDate)
            throws SandataRuntimeException;
}
