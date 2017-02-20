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

package com.sandata.lab.export.schedule;

import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServices {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response EXPORT_SCHEDULE(@MatrixParam("staffId") Integer staffId,
                                          @MatrixParam("patientId") Integer patientId,
                                          @MatrixParam("startDate") String startDate,
                                          @MatrixParam("endDate") String endDates) {
        return null;
    }
}
