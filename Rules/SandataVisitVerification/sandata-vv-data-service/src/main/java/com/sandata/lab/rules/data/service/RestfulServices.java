package com.sandata.lab.rules.data.service;

import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rules.vv.fact.VisitEventFact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>CXF Service Class</p>
 *
 * @author jasonscott
 */
public class RestfulServices {

    @GET
    @Path("/bsn/ent/dnis/mapping")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_BUSINESS_ENTITY_DNIS_MAPPING_ROUTE() {
        return null;
    }

    @GET
    @Path("/call/preferences")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_BUSINESS_ENTITY_CALL_PREFERENCES(@MatrixParam("bsnEntId") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/visit/verification/exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_VISIT_VERIFICATION_EXCEPTIONS_ROUTE() {
        return null;
    }

    @GET
    @Path("/visit/verification/settings")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_BUSINESS_ENTITY_VISIT_VERIFICATION_SETTINGS_ROUTE(@MatrixParam("bsnEntId") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/scheduled/tasks")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_SCHEDULE_TASK_LIST_ROUTE(@MatrixParam("bsnEntId") String bsnEntId,@MatrixParam("getTaskBySchedSk") String schedSk) {
        return null;
    }

    @GET
    @Path("/bsn/ent/id")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_BUSINESS_ENTITY_ID_LIST_ROUTE() {
        return null;
    }

    @GET
    @Path("/sched/evnt")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_SCHEDULE_EVENTS_ROUTE(@MatrixParam("bsnEntId") String bsnEntId,
                                                    @MatrixParam("lowerLimitDateString") String lowerLimitDateString,
                                                    @MatrixParam("upperLimitDateString") String upperLimitDateString) {
        return null;
    }

    @GET
    @Path("/visits/for/exceptions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_BUSINESS_ENTITY_VISITS_FOR_EXCEPTIONS_ROUTE(@MatrixParam("bsnEntId") String bsnEntId,
                                                    @MatrixParam("scheduledStartDateLowerLimitString") String scheduledStartDateLowerLimitString,
                                                    @MatrixParam("scheduledStartDateUpperLimitString") String scheduledStartDateUpperLimitString) {
        return null;
    }

    /**
     *
     * @param bsnEntId
     * @param staffIds comma seperated like : '000001','000002'
     * @return
     */
    @GET
    @Path("/call/staffs")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_STAFFS_ROUTE(@MatrixParam("bsnEntId") String bsnEntId ,@MatrixParam("staffIds") String staffIds ) {
        return null;
    }

    /**
     *
     * @param bsnEntId
     * @param patientIds comma seperated like : '000001','000002'
     * @return
     */
    @GET
    @Path("/call/patients")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_PATIENTS_ROUTE(@MatrixParam("bsnEntId") String bsnEntId ,@MatrixParam("patientIds") String patientIds,@MatrixParam("aniList") String aniList ) {
        return null;
    }


    /**
     * Get the PT_CONT_ADDR by pt_id
     * @param bsnEntId
     * @param staffIds
     * @return
     */
    @GET
    @Path("/patient/coord")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response GET_PATIENT_COORDINATOR_ROUTE(@MatrixParam("bsnEntId") String bsnEntId ,@MatrixParam("patientIdForGPS") String staffIds ) {
        return null;
    }


    @POST
    @Path("/planned/visit/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response POST_PLANNED_VISIT_EVENT_ROUTE(VisitEventFact visitEventFact) {
        return null;
    }

    @POST
    @Path("/unplanned/visit/event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response POST_UNPLANNED_VISIT_EVENT_ROUTE(VisitEventFact visitEventFact) {
        return null;
    }

    @POST
    @Path("/visit/create/auth")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response CREATE_VISIT_AUTH_ROUTE(VisitEventFact visitEventFact) {
        return null;
    }
}
