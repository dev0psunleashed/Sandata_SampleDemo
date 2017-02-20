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

package com.sandata.lab.rest.oracle;


import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.oracle.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

public class RestfulServices {

    @POST
    @Path("/patient")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_INSERT(Patient data) {
        return null;
    }

    @PUT
    @Path("/patient")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_UPDATE(Patient data) {
        return null;
    }


    @DELETE
    @Path("/patient/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatient(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatient_Patient(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatient_Patient(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @GET
    @Path("/patient/find/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_FIND_getPatients_PatientFind(@DefaultValue("") @MatrixParam("lastName") String lastName,
                                                           @DefaultValue("") @MatrixParam("firstName") String firstName,
                                                           @DefaultValue("") @MatrixParam("patientId") String patientId,
                                                           @DefaultValue("") @MatrixParam("entityINS") String entityINS,
                                                           @DefaultValue("1") @MatrixParam("page") BigDecimal page,
                                                           @DefaultValue("10") @MatrixParam("pageSize") BigDecimal pageSize,
                                                           @DefaultValue("") @MatrixParam("sortOn") String sortOn,
                                                           @DefaultValue("0") @MatrixParam("isAsc") BigDecimal isAsc) {

        return null;
    }

    @POST
    @Path("/patient/patientservice")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SERVICE_INSERT(PatientService data) {
        return null;
    }

    @PUT
    @Path("/patient/patientservice")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SERVICE_UPDATE(PatientService data) {
        return null;
    }

    @DELETE
    @Path("/patient/patientservice/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientService(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/patientservice/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientService_PatientService(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/patientservice")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientService_PatientService(@MatrixParam("patient_id") String patientId,
                                                                 @MatrixParam("entity_id") String entityId) {
        return null;
    }


    @POST
    @Path("/patient/business_entity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_BSN_ENT_INSERT(PatientBusinessEntity data) {
        return null;
    }

    @PUT
    @Path("/patient/business_entity")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_BSN_ENT_UPDATE(PatientBusinessEntity data) {
        return null;
    }

    @DELETE
    @Path("/patient/business_entity/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientBsnEnt_PatientBsnEnt(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/business_entity/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientBsnEnt_PatientBsnEnt(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/business_entity")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientBsnEnt_PatientBsnEnt(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ID_XWALK_INSERT(PatientIDCrosswalk data) {
        return null;
    }


    @PUT
    @Path("/patient/crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ID_XWALK_UPDATE(PatientIDCrosswalk data) {
        return null;
    }

    @DELETE
    @Path("/patient/crosswalk/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientIdXwalk(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientIdXwalk_PatientIdXwalk(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/crosswalk")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientIdXwalk_PatientIdXwalk(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SAFETY_MSRS_INSERT(PatientSafetyMeasures data) {
        return null;
    }

    @PUT
    @Path("/patient/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SAFETY_MSRS_UPDATE(PatientSafetyMeasures data) {
        return null;
    }

    @DELETE
    @Path("/patient/safety_measures/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientSafetyMsrs(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/safety_measures/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientSafetyMsrs_PatientSafetyMsrs(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/safety_measures")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientSafetyMsrs_PatientSafetyMsrs(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/environment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ENV_INSERT(PatientEnvironment data) {
        return null;
    }

    @PUT
    @Path("/patient/environment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ENV_UPDATE(PatientEnvironment data) {
        return null;
    }

    @DELETE
    @Path("/patient/environment/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientEnv(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/environment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientEnv_PatientEnv(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/environment")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientEnv_PatientEnv(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/schedule_event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SCHEDULED_EVENT_INSERT(ScheduleEvent data) {
        return null;
    }

    @PUT
    @Path("/patient/schedule_event")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_SCHEDULED_EVENT_UPDATE(ScheduleEvent data) {
        return null;
    }

    @DELETE
    @Path("/patient/schedule_event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_deleteScheduleEvnt(
            @PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/schedule_event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getScheduleEvnt_ScheduleEvent(
            @PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/schedule_event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_SCHEDULE_getScheduleEvnt_ScheduleEvent(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/patient_payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_PAYER_INSERT(PatientPayer data) {
        return null;
    }

    @PUT
    @Path("/patient/patient_payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_PAYER_UPDATE(PatientPayer data) {
        return null;
    }

    @DELETE
    @Path("/patient/patient_payer/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientPayer(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/patient_payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientPayer_PatientPayer(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/patient_payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientPayer_PatientPayer(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_AUTHORIZATION_INSERT(Authorization data) {
        return null;
    }

    @PUT
    @Path("/patient/authorization")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_AUTH_UPDATE(Authorization data) {
        return null;
    }

    @DELETE
    @Path("/patient/authorization/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_deleteAuthorization(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/authorization/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getAuthorization_Authorization(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/authorization")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_AUTH_getAuthorization_Authorization(@MatrixParam("patient_id") String patientId,
                                                                   @MatrixParam("entity_id") String entityId) {
        return null;
    }


    @POST
    @Path("/patient/excluded")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_EXCLUDED_INSERT(ExcludedPatients data) {
        return null;
    }

    @POST
    @Path("/patient/staff_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_STAFF_HISTORY_INSERT(StaffPatientHistory data) {
        return null;
    }

    @POST
    @Path("/patient/eligibility")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ELIGIBILITY_INSERT(Eligibility data) {
        return null;
    }

    @PUT
    @Path("/patient/eligibility")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ELIGIBILITY_UPDATE(Eligibility data) {
        return null;
    }

    @DELETE
    @Path("/patient/eligibility/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ELIGIBILITY_deleteElig(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/eligibility/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ELIGIBILITY_getElig_Eligibility(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/eligibility")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ELIGIBILITY_getElig_Eligibility(@MatrixParam("patient_id") String patientId,
                                                              @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/nutritional_reqs")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_NUTRL_REQS_INSERT(PatientNutritionalReqs data) {
        return null;
    }


    @PUT
    @Path("/patient/nutritional_reqs")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_NUTRL_REQS_UPDATE(PatientNutritionalReqs data) {
        return null;
    }

    @DELETE
    @Path("/patient/nutritional_reqs/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientNutrlReqs(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/nutritional_reqs/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientNutrlReqs_PatientNutrlReqs(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/nutritional_reqs")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientNutrlReqs_PatientNutrlReqs(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/clinical_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_CLINICAL_ASMT_INSERT(PatientClinicalAssessment data) {
        return null;
    }


    @PUT
    @Path("/patient/clinical_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_CLINICAL_ASMT_UPDATE(PatientClinicalAssessment data) {
        return null;
    }

    @DELETE
    @Path("/patient/clinical_assessment/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientClinicalAsmt(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/clinical_assessment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientClinicalAsmt_PatientClinicalAsmt(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/clinical_assessment")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientClinicalAsmt_PatientClinicalAsmt(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/allergies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ALLERGIES_INSERT(PatientAllergies data) {
        return null;
    }


    @PUT
    @Path("/patient/allergies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_ALLERGIES_UPDATE(PatientAllergies data) {
        return null;
    }

    @DELETE
    @Path("/patient/allergies/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientAllergies(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/allergies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientAllergies_PatientAllergies(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/allergies")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientAllergies_PatientAllergies(@MatrixParam("patient_id") String patientId,
                                                                     @MatrixParam("entity_id") String entityId) {
        return null;
    }


    @POST
    @Path("/patient/intake")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_INTAKE_INSERT(PatientIntake data) {
        return null;
    }

    @PUT
    @Path("/patient/intake")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_INTAKE_UPDATE(PatientIntake data) {
        return null;
    }

    @DELETE
    @Path("/patient/intake/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientIntake(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/intake/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientIntake_PatientIntake(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/intake")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientIntake_PatientIntake(@MatrixParam("patient_id") String patientId,
                                                                 @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/contact_details")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_CONTACT_DETAILS_INSERT(PatientContactDetail data) {
        return null;
    }

    @PUT
    @Path("/patient/contact_details")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_CONTACT_DETAILS_UPDATE(PatientContactDetail data) {
        return null;
    }

    @DELETE
    @Path("/patient/contact_details/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientContactDetl(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/contact_details/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientContactDetl_PatientContactDetail(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/contact_details")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientContactDetl_PatientContactDetail(@MatrixParam("patient_id") String patientId,
                                                            @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_TASK_INSERT(PatientTask data) {
        return null;
    }

    @PUT
    @Path("/patient/task")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_TASK_UPDATE(PatientTask data) {
        return null;
    }

    @DELETE
    @Path("/patient/task/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientTask(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/task/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientTask_PatientTask(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/task")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientTask_PatientTask(@MatrixParam("patient_id") String patientId,
                                                                                  @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/reference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_REFERENCE_INSERT(Reference data) {
        return null;
    }

    @POST
    @Path("/patient/dme_supplies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_DME_SUPPLIES_INSERT(PatientDMEAndSupplies data) {
        return null;
    }

    @PUT
    @Path("/patient/dme_supplies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_DME_SUPPLIES_UPDATE(PatientDMEAndSupplies data) {
        return null;
    }

    @DELETE
    @Path("/patient/dme_supplies/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientDmeSuppl(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/dme_supplies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientDmeSuppl_PatientDMEAndSupplies(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/dme_supplies")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientDmeSuppl_PatientDMEAndSupplies(@MatrixParam("patient_id") String patientId,
                                                                               @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/visit")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_VISIT_INSERT(Visit data) {
        return null;
    }

    @POST
    @Path("/patient/plan_of_care")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_PLAN_OF_CARE_INSERT(PlanOfCare data) {
        return null;
    }

    @POST
    @Path("/patient/requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_REQUIREMENTS_INSERT(PatientRequirements data) {
        return null;
    }

    @PUT
    @Path("/patient/requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_REQUIREMENTS_UPDATE(PatientRequirements data) {
        return null;
    }

    @DELETE
    @Path("/patient/requirements/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientRqmts(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientRqmts_PatientRequirements(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/requirements")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientRqmts_PatientRequirements(@MatrixParam("patient_id") String patientId,
                                                                               @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/medications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_MEDICATIONS_INSERT(PatientMedications data) {
        return null;
    }

    @PUT
    @Path("/patient/medications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_MEDICATIONS_UPDATE(PatientMedications data) {
        return null;
    }

    @DELETE
    @Path("/patient/medications/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientMedications(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/medications/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientMedications_PatientMedications(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/medications")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientMedications_PatientMedications(@MatrixParam("patient_id") String patientId,
                                                                                  @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/billing_rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_BILLING_RATE_INSERT(BillingRate data) {
        return null;
    }

    @PUT
    @Path("/patient/billing_rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_BILLING_RATE_UPDATE(BillingRate data) {
        return null;
    }

    @DELETE
    @Path("/patient/billing_rate/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_deleteBillingRate(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/billing_rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRate_BillingRate(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/billing_rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_BILLING_getBillingRate_BillingRate(@MatrixParam("patient_id") String patientId,
                                                                 @MatrixParam("entity_id") String entityId)  {
        return null;
    }

    @POST
    @Path("/patient/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_MEDICAL_HIST_INSERT(PatientMedicalHistory data) {
        return null;
    }

    @PUT
    @Path("/patient/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PATIENT_MEDICAL_HIST_UPDATE(PatientMedicalHistory data) {
        return null;
    }

    @DELETE
    @Path("/patient/medical_history/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientMedicalHist(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/medical_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientMedicalHist_PatientMedicalHistory(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/medical_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientMedicalHist_PatientMedicalHistory(@MatrixParam("patient_id") String patientId,
                                                                                 @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/referral")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_REFERRAL_INSERT(Reference data) {
        return null;
    }

    @PUT
    @Path("/patient/referral")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_REFERRAL_UPDATE(Reference data) {
        return null;
    }

    @GET
    @Path("/patient/referral/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REFERRAL_getRfrl_Referral(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }


    @GET
    @Path("/patient/referral")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REFERRAL_getRfrl_Referral(@MatrixParam("referral_id") String referralId) {
        return null;
    }

    @DELETE
    @Path("/patient/referral/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deleteRfrl_Referral(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @POST
    @Path("/patient/reference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_REFERENCE_INSERT(Reference data) {
        return null;
    }

    @PUT
    @Path("/patient/reference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_REFERENCE_UPDATE(Reference data) {
        return null;
    }

    @DELETE
    @Path("/patient/reference/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REFERENCE_deleteReference(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/reference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REFERENCE_getReference_Reference(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/reference")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_REFERENCE_getReference_Reference(@MatrixParam("patient_id") String patientId,
                                                                               @MatrixParam("entity_id") String entityId) {
        return null;
    }

    @POST
    @Path("/patient/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PAYER_INSERT(Payer data) {
        return null;
    }

    @PUT
    @Path("/patient/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response REST_PAYER_UPDATE(Payer data) {
        return null;
    }

    @DELETE
    @Path("/patient/payer/{sequence_key}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_deletePayer(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }
    @GET
    @Path("/patient/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayer_Payer(@PathParam("sequence_key") int sequenceKey) {
        return null;
    }

    @GET
    @Path("/patient/payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PAYER_getPayer_Payer(@MatrixParam("payer_id") String id) {
        return null;
    }

}
