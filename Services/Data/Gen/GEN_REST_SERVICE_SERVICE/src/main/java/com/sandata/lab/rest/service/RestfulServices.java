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

package com.sandata.lab.rest.service;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region Service

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_insertSvc_Service(Service data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_updateSvc_Service(Service data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_deleteSvc_Service(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvc_Service(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcByBsnEntId_Service(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("svn") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region ServiceActivityBillingCode

    @POST
    @Path("/activity_bill_code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_insertSvcActivityBillingCode_ServiceActivityBillingCode(ServiceActivityBillingCode data) {

        return null;
    }

    @PUT
    @Path("/activity_bill_code")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_updateSvcActivityBillingCode_ServiceActivityBillingCode(ServiceActivityBillingCode data) {

        return null;
    }

    @DELETE
    @Path("/activity_bill_code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_deleteSvcActivityBillingCode_ServiceActivityBillingCode(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity_bill_code/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcActivityBillingCode_ServiceActivityBillingCode(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity_bill_code")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcActivityBillingCode_ServiceActivityBillingCode(
            @MatrixParam("activity_sk") String activity,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region ServiceTask

    /*
    @POST
    @Path("/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_insertSvcTask_ServiceTask(ServiceTask data) {

        return null;
    }

    @PUT
    @Path("/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_updateSvcTask_ServiceTask(ServiceTask data) {

        return null;
    }
    */

    @DELETE
    @Path("/task/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_deleteSvcTask_ServiceTask(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcTask_ServiceTask(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcTask_ServiceTask(
            @MatrixParam("service_sk") String serviceSk,
            @MatrixParam("task_id") String taskId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region ServiceVisit

    @POST
    @Path("/visit")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_insertSvcVisit_ServiceVisit(ServiceVisit data) {

        return null;
    }

    @PUT
    @Path("/visit")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_updateSvcVisit_ServiceVisit(ServiceVisit data) {

        return null;
    }

    @DELETE
    @Path("/visit/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_deleteSvcVisit_ServiceVisit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/visit/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcVisit_ServiceVisit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/visit")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SERVICE_getSvcVisit_ServiceVisit(
            @MatrixParam("service_sk") String serviceSk,
            @MatrixParam("visit_sk") String visitSk,
            @MatrixParam("bsn_ent_id") String bnsEntId
            ) {

        return null;
    }
    //endregion
}
