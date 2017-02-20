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

package com.sandata.one.aggregator.visit.review;

import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.List;

public class RestfulServices {

    //region Visit

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_reviewVisit_Response(
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("bsn_ent_id") String bsnEnt,
            @MatrixParam("from_date") String fromDate,
            @MatrixParam("to_date") String toDate,
            @MatrixParam("from_time") String fromTime,
            @MatrixParam("to_time") String toTime,
            @MatrixParam("patient_last_name") String patientLastName,
            @MatrixParam("patient_first_name") String patientFirstName,
            @MatrixParam("patient_name") String patientName,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("medicaid_id") String medicaidId,
            @MatrixParam("staff_last_name") String staffLastName,
            @MatrixParam("staff_first_name") String staffFirstName,
            @MatrixParam("staff_name") String staffName,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("staff_snn") String staffSsn,
            @DefaultValue("all") @MatrixParam("visit_status") String visitStatus,
            @MatrixParam("exceptions_list") List<BigInteger> exceptionsList,
            @MatrixParam("call_type") String callType,
            @MatrixParam("coordinator_name") String coordinatorName,
            @MatrixParam("coordinator_id") String coordinatorId,
            @MatrixParam("manager_name") String managerName,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("PatientLastName") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Visit History

    @GET
    @Path("/history/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_AUDIT_getVisitHistory_Response(
            @PathParam("visit_sk") long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ChangedBy") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Visit Patient Details

    @GET
    @Path("/patient/details/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_getVisitPatientDetails_Response(@PathParam("visit_sk") long visitSk) {
        return null;
    }

    //endregion


    //region Visit exception Details

    @GET
    @Path("/exception/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitExcprReview_VisitException(
            @PathParam("sequence_key") long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("excplknm") @MatrixParam("sort_on") String sortOn,//default sort on ExceptionLookupName
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion


    //region Visit Notes

    @GET
    @Path("/visit/notes/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_getVisitNotes_Response(@PathParam("visit_sk") long visitSk) {
        return null;
    }

    //endregion

    //region Visit Tasks

    @GET
    @Path("/visit/tasks/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_getVisitTasks_Response(
            @PathParam("visit_sk") long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("TaskID") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //endregion

    //region Visit staff
    @GET
    @Path("/staff/details/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getStaffDetail_VisitReview(@PathParam("sequence_key") long visitSk) {

        return null;
    }

    //endregion


    @GET
    @Path("/call/logs/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_getCallLogs_Response(
            @PathParam("visit_sk") long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("visitdtime") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    //region Visit Details

    @GET
    @Path("/visit/details/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_getVisitDetails_Response(@PathParam("visit_sk") long visitSk) {
        return null;
    }

    //endregion
}
