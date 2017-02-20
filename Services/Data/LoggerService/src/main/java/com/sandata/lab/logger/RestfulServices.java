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

package com.sandata.lab.logger;

public class RestfulServices {

    // Removed REST Endpoints from Logger since logger is used in export/import services as well and will have a port 9090 conflict

    //region ApplicationLog

    /*
    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppLog_ApplicationLog(ApplicationLog data) {

        return null;
    }

    /* DO NOT ALLOW
    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppLog_ApplicationLog(ApplicationLog data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppLog_ApplicationLog(@PathParam("sequence_key") int sequenceKey) {

        return null;
    }
    */

    /*
    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppLog_ApplicationLog(@PathParam("sequence_key") int sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppLogPK_ApplicationLog(
            @MatrixParam("log_thread") Long logThread,
            @MatrixParam("log_level") String logLevel,
            @MatrixParam("log_process") String logProcess,
            @MatrixParam("from_date") String fromDate,
            @DefaultValue("00:00")@MatrixParam("from_time") String fromTime,
            @MatrixParam("to_date") String toDate,
            @DefaultValue("23:59")@MatrixParam("to_time") String toTime) {

        return null;
    }

    @GET
    @Path("/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppLogHistory_ApplicationLog(
            @MatrixParam("log_thread") Long logThread,
            @MatrixParam("log_level") String logLevel,
            @MatrixParam("log_process") String logProcess,
            @MatrixParam("from_date") String fromDate,
            @DefaultValue("00:00")@MatrixParam("from_time") String fromTime,
            @MatrixParam("to_date") String toDate,
            @DefaultValue("23:59")@MatrixParam("to_time") String toTime,
            @MatrixParam("user_guid") String userGuid,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("update") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("asc") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region ApplicationSession

    @POST
    @Path("/session")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_insertAppSess_ApplicationSession(ApplicationSession data) {

        return null;
    }

    /* DO NOT ALLOW
    @PUT
    @Path("/session")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_updateAppSess_ApplicationSession(ApplicationSession data) {

        return null;
    }

    @DELETE
    @Path("/session/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_deleteAppSess_ApplicationSession(@PathParam("sequence_key") int sequenceKey) {

        return null;
    }
    */

    /*
    @GET
    @Path("/session/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppSess_ApplicationSession(@PathParam("sequence_key") int sequenceKey) {

        return null;
    }

    @GET
    @Path("/session")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_getAppSessPK_ApplicationSession(
            @MatrixParam("user_guid") String userGuid,
            @MatrixParam("from_date") String fromDate,
            @DefaultValue("00:00")@MatrixParam("from_time") String fromTime,
            @MatrixParam("to_date") String toDate,
            @DefaultValue("23:59")@MatrixParam("to_time") String toTime) {

        return null;
    }
    //endregion

    @POST
    @Path("/add/{user_guid}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_addLogForUserGUID_Response(
            @PathParam("user_guid") String userGuid,
            ApplicationLog applicationLog
    ) {
        return null;
    }

    @POST
    @Path("/add")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_APP_addLog_Response(ApplicationLog applicationLog) {
        return null;
    }

    @GET
    @Path("/status")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response status() {
        return null;
    }
    */
}
