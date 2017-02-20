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

package com.sandata.lab.rest.task;


import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.task.model.TaskExt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public class RestfulServices {

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_insertTask_TaskExt(TaskExt data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_updateTask_TaskExt(TaskExt data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_deleteTask_Task(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_getTask_Task(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_getTaskPK_Task(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("TaskID") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/service/{service_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_TASKS_getTasksForService_Task(
            @PathParam("service_sk") long serviceSk,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("TaskID") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
}
