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

package com.sandata.lab.status.service;

import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServices {

    @GET
    @Path("/{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Response REST_GET_STATUS(@PathParam("guid") String guid, @MatrixParam("request") boolean request) {
        return null;
    }
}
