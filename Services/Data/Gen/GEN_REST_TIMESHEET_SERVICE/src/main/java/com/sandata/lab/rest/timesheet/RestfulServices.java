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

package com.sandata.lab.rest.timesheet;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region TimesheetDetail

    @POST
    @Path("/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_insertTimesheetDet_TimesheetDetail(TimesheetDetail data) {

        return null;
    }

    @PUT
    @Path("/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_updateTimesheetDet_TimesheetDetail(TimesheetDetail data) {

        return null;
    }

    @DELETE
    @Path("/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_deleteTimesheetDet_TimesheetDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_getTimesheetDet_TimesheetDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_getTimesheetDet_TimesheetDetail(
            @MatrixParam("visit_sk") String visitSk,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region TimesheetSummary

    @POST
    @Path("/summary")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_insertTimesheetSummary_TimesheetSummary(TimesheetSummary data) {

        return null;
    }

    @PUT
    @Path("/summary")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_updateTimesheetSummary_TimesheetSummary(TimesheetSummary data) {

        return null;
    }

    @DELETE
    @Path("/summary/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_deleteTimesheetSummary_TimesheetSummary(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/summary/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_getTimesheetSummary_TimesheetSummary(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/summary")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TIMESHEET_getTimesheetSummary_TimesheetSummary(
            @MatrixParam("staff_id") String staffID,
            @MatrixParam("bsn_ent_id") String bsnEntID) {

        return null;
    }
    //endregion
}
