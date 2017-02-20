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

package com.sandata.lab.rest.poc;

import com.sandata.lab.data.model.dl.model.PlanOfCare;
import com.sandata.lab.data.model.dl.model.PlanOfCareActivity;
import com.sandata.lab.data.model.dl.model.PlanOfCareService;
import com.sandata.lab.data.model.dl.model.PlanOfCareTaskList;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    //region PlanOfCare

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_insertPoc_PlanOfCare(PlanOfCare data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_updatePoc_PlanOfCare(PlanOfCare data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_deletePoc_PlanOfCare(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPoc_PlanOfCare(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPoc_PlanOfCare(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PlanOfCareActivity

    @POST
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_insertPocActivity_PlanOfCareActivity(PlanOfCareActivity data) {

        return null;
    }

    @PUT
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_updatePocActivity_PlanOfCareActivity(PlanOfCareActivity data) {

        return null;
    }

    @DELETE
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_deletePocActivity_PlanOfCareActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocActivity_PlanOfCareActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocActivity_PlanOfCareActivity(
            @MatrixParam("poc_sk") String pocSk) {

        return null;
    }
    //endregion

    //region PlanOfCareService

    @POST
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_insertPocSvc_PlanOfCareService(PlanOfCareService data) {

        return null;
    }

    @PUT
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_updatePocSvc_PlanOfCareService(PlanOfCareService data) {

        return null;
    }

    @DELETE
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_deletePocSvc_PlanOfCareService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocSvc_PlanOfCareService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocSvc_PlanOfCareService(
            @MatrixParam("poc_sk") String pocSk) {

        return null;
    }
    //endregion

    //region PlanOfCareTaskList

    @POST
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_insertPocTaskLst_PlanOfCareTaskList(PlanOfCareTaskList data) {

        return null;
    }

    @PUT
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_updatePocTaskLst_PlanOfCareTaskList(PlanOfCareTaskList data) {

        return null;
    }

    @DELETE
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_deletePocTaskLst_PlanOfCareTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocTaskLst_PlanOfCareTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task_list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocTaskLst_PlanOfCareTaskList(
            @MatrixParam("poc_sk") String pocSk,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/task_list/task")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocTaskLstForTaskId_PlanOfCareTaskList(
            @MatrixParam("task_id") String taskId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/task_list/poc")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_POC_getPocTaskLstForPocSk_PlanOfCareTaskList(
        @MatrixParam("poc_sk") String pocSk) {

        return null;
    }
    //endregion
}
