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

package com.sandata.lab.intake.staff;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.staff.Staff;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Defines the cxf REST endpoints.
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServices {

    @POST
    @Path("/staffmember")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response INSERT_STAFF_MEMBER(Staff staffMember) {
        return null;
    }

    @POST
    @Path("/staffmembers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response INSERT_STAFF_MEMBERS(List<Staff> staffMembers) {
        return null;
    }

    @PUT
    @Path("/staffmember")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response UPDATE_STAFF_MEMBER(Staff staffMember) {
        return null;
    }

    @PUT
    @Path("/staffmembers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response UPDATE_STAFF_MEMBERS(List<Staff> staffMembers) {
        return null;
    }

    @DELETE
    @Path("/staffmember")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response DELETE_STAFF_MEMBER(Staff staffMember) {
        return null;
    }

    @DELETE
    @Path("/staffmembers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final Response DELETE_STAFF_MEMBERS(List<Staff> staffMembers) {
        return null;
    }
}
