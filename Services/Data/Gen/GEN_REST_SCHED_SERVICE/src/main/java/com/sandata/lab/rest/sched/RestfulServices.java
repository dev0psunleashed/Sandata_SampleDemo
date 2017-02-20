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

package com.sandata.lab.rest.sched;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleEvntExt;
import com.sandata.lab.data.model.dl.model.extended.schedule.ScheduleExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.sched.model.SchedUpdateStaff;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Date;
import java.util.List;


public class RestfulServices {

    //region Schedule

    /**
     * @deprecated Replaced by {@link #PKG_SCHEDULE_insertSchedFullProcess_ScheduleExt}
     */
    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Deprecated public final Response PKG_SCHEDULE_insertSched_Schedule(Schedule data) {

        return null;
    }

    /**
     * @deprecated Replaced by {@link #PKG_SCHEDULE_updateSchedFullProcess_ScheduleExt}
     */
    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Deprecated public final Response PKG_SCHEDULE_updateSched_Schedule(Schedule data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSched_Schedule(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSched_Schedule(@PathParam("sequence_key") long scheduleSk) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSched_Schedule(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    /**
     * @deprecated Replaced by {@link #PKG_SCHEDULE_updateSchedFullProcess_ScheduleExt}
     */
    @PUT
    @Path("/daterange")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Deprecated public final Response PKG_SCHEDULE_updateSchedWithDateRange_Schedule(
            @MatrixParam("date_from") String dateFrom,
            @MatrixParam("date_to") String dateTo,
            Schedule data) {

        return null;
    }
    //endregion

    //region ScheduleActivity

    @POST
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedActivity_ScheduleActivity(ScheduleActivity data) {

        return null;
    }

    @PUT
    @Path("/activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedActivity_ScheduleActivity(ScheduleActivity data) {

        return null;
    }

    @DELETE
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedActivity_ScheduleActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedActivity_ScheduleActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/activity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedActivity_ScheduleActivity(
            @MatrixParam("sched_sk") String schedSk,
            @MatrixParam("activity_sk") String activitySk) {

        return null;
    }
    //endregion

    //region ScheduleAuthorization

    @POST
    @Path("/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedAuth_ScheduleAuthorization(ScheduleAuthorization data) {

        return null;
    }

    @PUT
    @Path("/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedAuth_ScheduleAuthorization(ScheduleAuthorization data) {

        return null;
    }

    @DELETE
    @Path("/authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedAuth_ScheduleAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedAuth_ScheduleAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/authorization")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedAuth_ScheduleAuthorization(
            @MatrixParam("sched_sk") String schedSk,
            @MatrixParam("auth_sk") String authSk) {

        return null;
    }
    //endregion

    //region ScheduleEvent

    @POST
    @Path("/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedEvnt_ScheduleEvent(ScheduleEvent data) {

        return null;
    }

    @PUT
    @Path("/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedEvnt_ScheduleEvent(ScheduleEvent data) {

        return null;
    }

    @DELETE
    @Path("/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedEvnt_ScheduleEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvnt_ScheduleEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvnt_ScheduleEvent(
            @MatrixParam("sched_sk") Long schedSk,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("staff_id") String staffId) {

        return null;
    }

    @GET
    @Path("/event/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntHistory_ScheduleEvent(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("sched_from_date_time") String schedFromDate,
            @MatrixParam("sched_to_date_time") String schedToDate,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/event/history/detail/{schedule_event_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntHistoryDetail_Response(
            @PathParam("schedule_event_sk") Long scheduleEvenSK,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("changed_on") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region ScheduleEventActivity

    @POST
    @Path("/event_activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedEvntActivity_ScheduleEventActivity(ScheduleEventActivity data) {

        return null;
    }

    @PUT
    @Path("/event_activity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedEvntActivity_ScheduleEventActivity(ScheduleEventActivity data) {

        return null;
    }

    @DELETE
    @Path("/event_activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedEvntActivity_ScheduleEventActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_activity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntActivity_ScheduleEventActivity(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_activity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntActivity_ScheduleEventActivity(
            @MatrixParam("sched_event_sk") String schedEventSk,
            @MatrixParam("activity_sk") String activitySk) {

        return null;
    }
    //endregion

    //region ScheduleEventAuthorization

    @POST
    @Path("/event_authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedEvntAuth_ScheduleEventAuthorization(ScheduleEventAuthorization data) {

        return null;
    }

    @PUT
    @Path("/event_authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedEvntAuth_ScheduleEventAuthorization(ScheduleEventAuthorization data) {

        return null;
    }

    @DELETE
    @Path("/event_authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedEvntAuth_ScheduleEventAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntAuth_ScheduleEventAuthorization(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_authorization")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntAuth_ScheduleEventAuthorization(
            @MatrixParam("sched_event_sk") String schedEventSk,
            @MatrixParam("auth_sk") String authSk) {

        return null;
    }
    //endregion

    //region ScheduleEventService

    @POST
    @Path("/event_service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedEvntSvc_ScheduleEventService(ScheduleEventService data) {

        return null;
    }

    @PUT
    @Path("/event_service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedEvntSvc_ScheduleEventService(ScheduleEventService data) {

        return null;
    }

    @DELETE
    @Path("/event_service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedEvntSvc_ScheduleEventService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntSvc_ScheduleEventService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/event_service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedEvntSvc_ScheduleEventService(
            @MatrixParam("sched_event_sk") String schedEventSk,
            @MatrixParam("service_sk") String serviceSk) {

        return null;
    }
    //endregion

    //region ScheduleParticipantExclusion

    @POST
    @Path("/participant/exclusion")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedPtcExcl_ScheduleParticipantExclusion(ScheduleParticipantExclusion data) {

        return null;
    }

    @PUT
    @Path("/participant/exclusion")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedPtcExcl_ScheduleParticipantExclusion(ScheduleParticipantExclusion data) {

        return null;
    }

    @DELETE
    @Path("/participant/exclusion/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedPtcExcl_ScheduleParticipantExclusion(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/participant/exclusion/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedPtcExcl_ScheduleParticipantExclusion(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/participant/exclusion")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedPtcExcl_ScheduleParticipantExclusion(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region ScheduleRepeatDayOfMonth

    @POST
    @Path("/repeat_day_of_month")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedRptDayOfMonth_ScheduleRepeatDayOfMonth(ScheduleRepeatDayOfMonth data) {

        return null;
    }

    @PUT
    @Path("/repeat_day_of_month")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedRptDayOfMonth_ScheduleRepeatDayOfMonth(ScheduleRepeatDayOfMonth data) {

        return null;
    }

    @DELETE
    @Path("/repeat_day_of_month/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedRptDayOfMonth_ScheduleRepeatDayOfMonth(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_day_of_month/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptDayOfMonth_ScheduleRepeatDayOfMonth(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_day_of_month")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptDayOfMonth_ScheduleRepeatDayOfMonth(
            @MatrixParam("sched_sk") String schedSk) {

        return null;
    }
    //endregion

    //region ScheduleRepeatDayOfWeek

    @POST
    @Path("/repeat_day_of_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedRptDayOfWeek_ScheduleRepeatDayOfWeek(ScheduleRepeatDayOfWeek data) {

        return null;
    }

    @PUT
    @Path("/repeat_day_of_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedRptDayOfWeek_ScheduleRepeatDayOfWeek(ScheduleRepeatDayOfWeek data) {

        return null;
    }

    @DELETE
    @Path("/repeat_day_of_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedRptDayOfWeek_ScheduleRepeatDayOfWeek(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_day_of_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptDayOfWeek_ScheduleRepeatDayOfWeek(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_day_of_week")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptDayOfWeek_ScheduleRepeatDayOfWeek(
            @MatrixParam("sched_sk") String schedSk) {

        return null;
    }
    //endregion

    //region ScheduleRepeatMonth

    @POST
    @Path("/repeat_month")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedRptMonth_ScheduleRepeatMonth(ScheduleRepeatMonth data) {

        return null;
    }

    @PUT
    @Path("/repeat_month")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedRptMonth_ScheduleRepeatMonth(ScheduleRepeatMonth data) {

        return null;
    }

    @DELETE
    @Path("/repeat_month/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedRptMonth_ScheduleRepeatMonth(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_month/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptMonth_ScheduleRepeatMonth(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    // TODO: Won't work because SCHED_SK is missing form the table. GEORG-747
    @GET
    @Path("/repeat_month")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptMonth_ScheduleRepeatMonth(
            @MatrixParam("sched_sk") String schedSk) {

        return null;
    }
    //endregion

    //region ScheduleRepeatWeek

    @POST
    @Path("/repeat_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedRptWeek_ScheduleRepeatWeek(ScheduleRepeatWeek data) {

        return null;
    }

    @PUT
    @Path("/repeat_week")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedRptWeek_ScheduleRepeatWeek(ScheduleRepeatWeek data) {

        return null;
    }

    @DELETE
    @Path("/repeat_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedRptWeek_ScheduleRepeatWeek(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_week/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptWeek_ScheduleRepeatWeek(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/repeat_week")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedRptWeek_ScheduleRepeatWeek(
            @MatrixParam("sched_sk") String schedSk) {

        return null;
    }
    //endregion

    //region ScheduleService

    @POST
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedSvc_ScheduleService(ScheduleService data) {

        return null;
    }

    @PUT
    @Path("/service")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedSvc_ScheduleService(ScheduleService data) {

        return null;
    }

    @DELETE
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedSvc_ScheduleService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedSvc_ScheduleService(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/service")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedSvc_ScheduleService(
            @MatrixParam("sched_sk") String schedEventSk,
            @MatrixParam("service_sk") String serviceSk) {

        return null;
    }
    //endregion

    //region ScheduleTaskList

    @POST
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedTaskLst_ScheduleTaskList(ScheduleTaskList data) {

        return null;
    }

    @PUT
    @Path("/task_list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedTaskLst_ScheduleTaskList(ScheduleTaskList data) {

        return null;
    }

    @DELETE
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedTaskLst_ScheduleTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task_list/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedTaskLst_ScheduleTaskList(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/task_list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getSchedTaskLst_ScheduleTaskList(

            @MatrixParam("sched_sk") String schedEventSk) {

        return null;
    }
    //endregion

    //region Custom Methods

    @GET
    @Path("/find/staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_findStaff_Response(
            @DefaultValue("English") @MatrixParam("language") String language,
            @DefaultValue("") @MatrixParam("service") String service,
            @DefaultValue("") @MatrixParam("keyword") String keyword,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("Active") @MatrixParam("employment_status_type_name") String employmentStatusTypeName,
            @DefaultValue("0") @MatrixParam("no_conflict_with_schedule_sk") long noConflictedWithScheduleSK, // 7
            @DefaultValue("0") @MatrixParam("no_conflict_with_event_sk") long noConflictedWithEventSK, // 8
            @MatrixParam("event_date_from") String eventDateFrom, // 9
            @MatrixParam("event_date_to") String eventDateTo) { // 10
        return null;
    }

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_findSchedule_Response(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("auth_sk") Long authSk,
            @MatrixParam("start_date_time") String startDate,
            @MatrixParam("end_date_time") String endDate,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction, // 9
            @MatrixParam("status") List<String> status) {
        return null;
    }

    @GET
    @Path("/delete")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteSchedule_Response(
            @DefaultValue("") @MatrixParam("sched_sk") String schedSk,
            @DefaultValue("") @MatrixParam("week_days") String weekDays
    ) {
        return null;
    }

    @POST
    @Path("/event/save")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_saveSchedEvents_List(List<ScheduleEvent> scheduleEvents) {

        return null;
    }

    @PUT
    @Path("/event/staff/update")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateStaffIdForSchedule_SchedUpdateStaff(SchedUpdateStaff schedUpdateStaff) {

        return null;
    }

    @POST
    @Path("/validate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_validateSchedule_ScheduleExt(ScheduleExt scheduleExt,
                                                                    @MatrixParam("date_from") String dateFrom,
                                                                    @MatrixParam("date_to") String dateTo) {

        return null;
    }

    @POST
    @Path("/event/validate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_validateScheduleEvent_ScheduleEvntExt(ScheduleEvntExt scheduleEvntExt) {

        return null;
    }

    //endregion
    @POST
    @Path("/full")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_insertSchedFullProcess_ScheduleExt(ScheduleExt data) {

        return null;
    }
    
    @PUT
    @Path("/full")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_updateSchedFullProcess_ScheduleExt(
            @MatrixParam("date_from") String dateFrom,
            @MatrixParam("date_to") String dateTo,
            @MatrixParam("manually_override_indicator") Boolean manuallyOverrideIndicator,
            ScheduleExt data) {

        return null;
    }

    // region schedule refactoring
}
