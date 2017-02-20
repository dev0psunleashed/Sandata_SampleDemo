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

package com.sandata.lab.dl.vv;


import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    @GET
    @Path("/events")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_getScheduleEvents_Response(
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate,
            @MatrixParam("dnis") String dnis,
            @MatrixParam("staff_id") String StaffId) {
        return null;
    }
}
