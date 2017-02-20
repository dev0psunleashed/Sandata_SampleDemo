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

package com.sandata.lab.rest.visit;

import com.sandata.lab.data.model.dl.model.*;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.visit.model.FindVisitDetailsResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.List;

public class RestfulServices {

    //region Visit

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisit_Visit(Visit data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisit_Visit(Visit data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisit_Visit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisit_Visit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisit_Visit(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("sched_evnt_sk") String schedEventSk,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region VisitActivity

    @POST
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitActivity_VisitActivity(VisitActivity data) {

        return null;
    }

    @PUT
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitActivity_VisitActivity(VisitActivity data) {

        return null;
    }

    @DELETE
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitActivity_VisitActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitActivity_VisitActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitActivity_VisitActivity(
            @MatrixParam("visit_sk") String visitSk) {

        return null;
    }
    //endregion

    //region VisitAuthorization

    @POST
    @Path("/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitAuth_VisitAuthorization(VisitAuthorization data) {

        return null;
    }

    @PUT
    @Path("/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitAuth_VisitAuthorization(VisitAuthorization data) {

        return null;
    }

    @DELETE
    @Path("/authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitAuth_VisitAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitAuth_VisitAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/authorization")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitAuth_VisitAuthorization(
            @MatrixParam("visit_sk") String visitSk) {

        return null;
    }
    //endregion

    //region VisitEvent

    @POST
    @Path("/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitEvnt_VisitEvent(VisitEvent data) {

        return null;
    }

    @PUT
    @Path("/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitEvnt_VisitEvent(VisitEvent data) {

        return null;
    }

    @DELETE
    @Path("/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitEvnt_VisitEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitEvnt_VisitEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitEvnt_VisitEvent(
            @MatrixParam("visit_sk") String visitSk) {

        return null;
    }
    //endregion

    //region VisitException

    @POST
    @Path("/exception")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitExcp_VisitException(VisitException data) {

        return null;
    }

    @PUT
    @Path("/exception")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitExcp_VisitException(VisitException data) {

        return null;
    }

    @DELETE
    @Path("/exception/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitExcp_VisitException(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/exception/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitExcp_VisitException(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/exception")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitExcp_VisitException(
            @MatrixParam("visit_sk") String visitSk) {

        return null;
    }
    //endregion

    //region VisitHistory

    @POST
    @Path("/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitHist_VisitHistory(VisitHistory data) {

        return null;
    }

    @PUT
    @Path("/history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitHist_VisitHistory(VisitHistory data) {

        return null;
    }

    @DELETE
    @Path("/history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitHist_VisitHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitHist_VisitHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitHist_VisitHistory_List(
            @MatrixParam("visit_sk") String visitSk,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/history/detail/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitHistDetail_Response(
            @PathParam("visit_sk") Long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("changed_on") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    //region VisitNote

    @POST
    @Path("/notes")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitNote_VisitNote(VisitNote data) {

        return null;
    }

    @PUT
    @Path("/notes")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitNote_VisitNote(VisitNote data) {

        return null;
    }

    @DELETE
    @Path("/notes/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitNote_VisitNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notes/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitNote_VisitNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notes")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitNoteList_VisitNote_List(
            @MatrixParam("visit_sk") String visitSk,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region Visit Service

    @POST
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitSvc_VisitService(VisitService data) {

        return null;
    }

    @PUT
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitSvc_VisitService(VisitService data) {

        return null;
    }

    @DELETE
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitSvc_VisitService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitSvc_VisitService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitSvc_VisitService(
            @MatrixParam("visit_sk") String visitSk,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region VisitTaskList

    @POST
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitTaskLst_VisitTaskList(VisitTaskList data) {

        return null;
    }

    @PUT
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitTaskLst_VisitTaskList(VisitTaskList data) {

        return null;
    }

    @DELETE
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitTaskLst_VisitTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitTaskLst_VisitTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @POST
    @Path("/task_lists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response insertVisitTaskListDetails(List<VisitTaskList> visitTaskLists) {

        return null;
    }

    @PUT
    @Path("/task_lists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response updateVisitTaskListDetails(List<VisitTaskList> visitTaskLists) {

        return null;
    }

    @GET
    @Path("/task_list/visit/{visit_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response getVisitTaskListDetails(
            @PathParam("visit_sk") BigInteger visitSk) {

        return null;
    }

    @DELETE
    @Path("/tasks")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response deleteVisitTasks(@MatrixParam("visit_task_lst_sk") List<Long> data) {
        return null;
    }
    //endregion

    //region VisitVerification

    @POST
    @Path("/verification")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_insertVisitVerif_VisitVerification(VisitVerification data) {

        return null;
    }

    @PUT
    @Path("/verification")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitVerif_VisitVerification(VisitVerification data) {

        return null;
    }

    @DELETE
    @Path("/verification/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_deleteVisitVerif_VisitVerification(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/verification/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitVerif_VisitVerification(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/verification")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitVerif_VisitVerification(
            @MatrixParam("visit_sk") String visitSk) {

        return null;
    }

    @PUT
    @Path("/verification/roundingrulesforbillablehrs")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVvRoundingRules_Response(
            @MatrixParam("visit_sk") String visitSk) {
        return null;
    }


    //endregion

    //region Custom Methods

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final javax.ws.rs.core.Response PKG_VISIT_findVisit_Response(
            @MatrixParam("patient_first_name") String patientFirstName,
            @MatrixParam("patient_last_name") String patientLastName,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("staff_first_name") String staffFirstName,
            @MatrixParam("staff_last_name") String staffLastName,
            @MatrixParam("staff_id") String staffId,//5
            @MatrixParam("from_date_time") String fromDate,//6
            @MatrixParam("to_date_time") String toDate,//7
            @DefaultValue("1") @MatrixParam("page") int page,//8
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,//9
            @DefaultValue("pln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("all") @MatrixParam("status") String status,
            @MatrixParam("schd_hrs") Double scheduledHours, //14
            @MatrixParam("visit_sk") Long visitSK, //15
            @MatrixParam("patient_coordinator") String patientCoordinator, //16
            @MatrixParam("staff_coordinator") String staffCoordinator,//17
            @MatrixParam("excp_id") List<BigInteger> exceptionIdList,//18 
            @MatrixParam("excp_only_flag") Boolean exceptionOnlyFlag) {
        return null;
    }


    @GET
    @Path("/details")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_findVisitDetails_Response(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("visit_sk") Long visitSk,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("vsk") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @PUT
    @Path("/details")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_updateVisitDetails_Response(FindVisitDetailsResult visitDetails) {
        return null;
    }

    @GET
    @Path("/patient/{patient_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_getVisitsForPatient_Response(
            @PathParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @MatrixParam("from_date_time") String fromDateTime,
            @MatrixParam("to_date_time") String toDateTime) {

        return null;
    }

    @PUT
    @Path("/event/accept_call")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_VISIT_acceptCalls(List<VisitEvent> data) {
        return null;
    }



    //endregion

    //Search Staff, Patient of VV project
    @GET
    @Path("/findStaffsForSearch")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response findStaffsForSearch(
            @MatrixParam("bsn_ent_id") String beId,//bsn_ent_id
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("staff_position_name") List<String> staffPositionNameList,
            @MatrixParam("visit_date_time") String visitDateTimeString,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("sln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction
    ) {
        return null;
    }

    @GET
    @Path("/findPatientsForSearch")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response findPatientsForSearch(
            @MatrixParam("bsn_ent_id") String beId,
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("svc_name") List<String> serviceNameList,
            @MatrixParam("visit_date_time") String visitDateTimeString,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("fln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction
    ) {
        return null;
    }
}
