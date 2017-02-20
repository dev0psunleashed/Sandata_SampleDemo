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

package com.sandata.lab.rest.patient;

import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.rest.patient.model.extended.PatientExt;
import com.sandata.lab.rest.patient.model.extended.PatientPayerExt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class RestfulServices {

    //region Patient

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPt_PatientExt(PatientExt data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})

    public final Response PKG_HIST_updatePatient_PatientExt(PatientExt data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePt_Patient(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPt_Patient(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientForID_Patient(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientAllergy

    @POST
    @Path("/allergies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtAllergy_PatientAllergy(PatientAllergy data) {

        return null;
    }

    @PUT
    @Path("/allergies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtAllergy_PatientAllergy(PatientAllergy data) {

        return null;
    }

    @DELETE
    @Path("/allergies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtAllergy_PatientAllergy(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/allergies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtAllergy_PatientAllergy(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/allergies")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientAllergyForID_PatientAllergy(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @POST
    @Path("/allergies/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPatientAllergyList_PatientAllergy(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> allergyNameList) {

        return null;
    }

    @DELETE
    @Path("/allergies/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientAllergyList_PatientAllergy(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> allergyNameList) {

        return null;
    }

    @PUT
    @Path("/allergies/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePatientAllergyList_PatientAllergy(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> allergyNameList) {

        return null;
    }
    //endregion

    //region PatientClinicalAssessment

    @POST
    @Path("/clinical_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtClinicalAsmt_PatientClinicalAssessment(PatientClinicalAssessment data) {

        return null;
    }

    @PUT
    @Path("/clinical_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtClinicalAsmt_PatientClinicalAssessment(PatientClinicalAssessment data) {

        return null;
    }

    @DELETE
    @Path("/clinical_assessment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtClinicalAsmt_PatientClinicalAssessment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/clinical_assessment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtClinicalAsmt_PatientClinicalAssessment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/clinical_assessment")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtClinicalAsmt_PatientClinicalAssessment(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientContactAddress

    @POST
    @Path("/address")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtContAddr_PatientContactAddress(PatientContactAddress data) {

        return null;
    }

    @PUT
    @Path("/address")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtContAddr_PatientContactAddress(PatientContactAddress data) {

        return null;
    }

    @DELETE
    @Path("/address/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtContAddr_PatientContactAddress(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/address/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtContAddr_PatientContactAddress(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/address")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientCntctAddrForID_PatientContactAddress(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region PatientContactEmail

    @POST
    @Path("/email")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtContEmail_PatientContactEmail(PatientContactEmail data) {

        return null;
    }

    @PUT
    @Path("/email")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtContEmail_PatientContactEmail(PatientContactEmail data) {

        return null;
    }

    @DELETE
    @Path("/email/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtContEmail_PatientContactEmail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/email/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtContEmail_PatientContactEmail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/email")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtContEmail_PatientContactEmail(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientContactPhone

    @POST
    @Path("/phone")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtContPhone_PatientContactPhone(PatientContactPhone data) {

        return null;
    }

    @PUT
    @Path("/phone")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtContPhone_PatientContactPhone(PatientContactPhone data) {

        return null;
    }

    @DELETE
    @Path("/phone/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtContPhone_PatientContactPhone(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/phone/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtContPhone_PatientContactPhone(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/phone")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientContactPhoneID_PatientContactPhone(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region PatientDiagnosis

    @POST
    @Path("/diagnosis")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtDx_PatientDiagnosis(PatientDiagnosis data) {

        return null;
    }

    @PUT
    @Path("/diagnosis")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtDx_PatientDiagnosis(PatientDiagnosis data) {

        return null;
    }

    @DELETE
    @Path("/diagnosis/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtDx_PatientDiagnosis(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/diagnosis/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtDx_PatientDiagnosis(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/diagnosis")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientDiagnosis_PatientDiagnosis(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("service_name") String serviceName,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //region PatientNutrRqmt

    @PUT
    @Path("/nutritional_requirements/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_UpdatePatientNutrRqmt_PatientNutrRqmt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> nutrRqmtNames) {

        return null;
    }


    @POST
    @Path("/nutritional_requirements/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_AddNewPatientNutrRqmt_PatientNutrRqmt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> nutrRqmtNames) {

        return null;
    }


    @DELETE
    @Path("/nutritional_requirements/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_DeletePatientNutrRqmt_PatientNutrRqmt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> nutrRqmtNames) {

        return null;
    }

    //endregion

    //region PatientDurableMedicalEquipmentAndSupply

    @POST
    @Path("/dme_and_supplies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtDmeAndSupply_PatientDurableMedicalEquipmentAndSupply(PatientDurableMedicalEquipmentAndSupply data) {

        return null;
    }

    @PUT
    @Path("/dme_and_supplies")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtDmeAndSupply_PatientDurableMedicalEquipmentAndSupply(PatientDurableMedicalEquipmentAndSupply data) {

        return null;
    }

    @DELETE
    @Path("/dme_and_supplies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtDmeAndSupply_PatientDurableMedicalEquipmentAndSupply(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/dme_and_supplies/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtDmeAndSupply_PatientDurableMedicalEquipmentAndSupply(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/dme_and_supplies")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtDmeAndSupply_PatientDurableMedicalEquipmentAndSupply(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @DELETE
    @Path("/dme_and_supplies/list")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePatientDmeAndSupplyList_PatientDurableMedicalEquipmentAndSupply(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> dmeSupplyIdList) {

        return null;
    }

    @POST
    @Path("/dme_and_supplies/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPatientDmeAndSupplyList_PatientDurableMedicalEquipmentAndSupply(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> dmeSupplyIdList) {

        return null;
    }

    @PUT
    @Path("/dme_and_supplies/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePatientDmeAndSupplyList_PatientDurableMedicalEquipmentAndSupply(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("patient_id") String patientId,
            List<String> dmeSupplyIdList) {

        return null;
    }
    //endregion

    //region PatientEnvironment

    @POST
    @Path("/environment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtEnv_PatientEnvironment(PatientEnvironment data) {

        return null;
    }

    @PUT
    @Path("/environment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtEnv_PatientEnvironment(PatientEnvironment data) {

        return null;
    }

    @DELETE
    @Path("/environment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtEnv_PatientEnvironment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/environment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtEnv_PatientEnvironment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/environment")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtEnv_PatientEnvironment(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientIDCrosswalk

    @POST
    @Path("/id_crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtIdXwalk_PatientIDCrosswalk(PatientIDCrosswalk data) {

        return null;
    }

    @PUT
    @Path("/id_crosswalk")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtIdXwalk_PatientIDCrosswalk(PatientIDCrosswalk data) {

        return null;
    }

    @DELETE
    @Path("/id_crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtIdXwalk_PatientIDCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/id_crosswalk/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtIdXwalk_PatientIDCrosswalk(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/id_crosswalk")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtIdXwalk_PatientIDCrosswalk(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientMedication

    @POST
    @Path("/medications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtMed_PatientMedication(PatientMedication data) {

        return null;
    }

    @PUT
    @Path("/medications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtMed_PatientMedication(PatientMedication data) {

        return null;
    }

    @DELETE
    @Path("/medications/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtMed_PatientMedication(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medications/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtMed_PatientMedication(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medications")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtMed_PatientMedication(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientMedicalHistory

    @POST
    @Path("/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtMedclHist_PatientMedicalHistory(PatientMedicalHistory data) {

        return null;
    }

    @PUT
    @Path("/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtMedclHist_PatientMedicalHistory(PatientMedicalHistory data) {

        return null;
    }

    @DELETE
    @Path("/medical_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtMedclHist_PatientMedicalHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medical_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtMedclHist_PatientMedicalHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medical_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtMedclHist_PatientMedicalHistory(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientNote

    @POST
    @Path("/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtNote_PatientNote(PatientNote data) {

        return null;
    }

    @PUT
    @Path("/note")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtNote_PatientNote(PatientNote data) {

        return null;
    }

    @DELETE
    @Path("/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtNote_PatientNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/note/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtNote_PatientNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/note")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientNoteForID_PatientNote(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientHealthcareProfessional

    @POST
    @Path("/healthcare/professional")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtHcp_PatientHealthcareProfessional(PatientHealthcareProfessional data) {

        return null;
    }

    @PUT
    @Path("/healthcare/professional")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtHcp_PatientHealthcareProfessional(PatientHealthcareProfessional data) {

        return null;
    }

    @DELETE
    @Path("/healthcare/professional/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtHcp_PatientHealthcareProfessional(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/healthcare/professional/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtHcp_PatientHealthcareProfessional(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/healthcare/professional")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtHcp_PatientHealthcareProfessional(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientNutritionalRequirement

    @POST
    @Path("/nutritional_requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtNutrRqmt_PatientNutritionalRequirement(PatientNutritionalRequirement data) {

        return null;
    }

    @PUT
    @Path("/nutritional_requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtNutrRqmt_PatientNutritionalRequirement(PatientNutritionalRequirement data) {

        return null;
    }

    @DELETE
    @Path("/nutritional_requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtNutrRqmt_PatientNutritionalRequirement(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/nutritional_requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtNutrRqmt_PatientNutritionalRequirement(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/nutritional_requirements")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtNutrRqmt_PatientNutritionalRequirement(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientPayer

    @POST
    @Path("/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtPayer_PatientPayer(PatientPayer data) {

        return null;
    }

    @PUT
    @Path("/payer")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtPayer_PatientPayer(PatientPayer data) {

        return null;
    }

    @DELETE
    @Path("/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtPayer_PatientPayer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayer_PatientPayer(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayer_PatientPayer(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId) {

        return null;
    }
    
    @GET
    @Path("/payer/full")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientPayerFull_PatientPayer(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    //endregion

    //region PatientPayerEligibility
    @PUT
    @Path("/payer/eligibility")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePatientPayerEligibility_PatientPayerExt(PatientPayerExt data) {

        return null;
    }

    @GET
    @Path("/payer/eligibility/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayer_PatientPayerExt(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/eligibility")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientPayerEligibility_PatientPayerExt(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }
    //endregion

    //region PatientPayerInsurance

    @POST
    @Path("/payer/insurance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtPayerIns_PatientPayerInsurance(PatientPayerInsurance data) {

        return null;
    }

    @PUT
    @Path("/payer/insurance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtPayerIns_PatientPayerInsurance(PatientPayerInsurance data) {

        return null;
    }

    @DELETE
    @Path("/payer/insurance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtPayerIns_PatientPayerInsurance(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/insurance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayerIns_PatientPayerInsurance(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/insurance")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayerIns_PatientPayerInsurance(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId) {

        return null;
    }

    //endregion

    //region PatientPayerLimit

    @POST
    @Path("/payer/limit")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtPayerLmt_PatientPayerLimit(PatientPayerLimit data) {

        return null;
    }

    @PUT
    @Path("/payer/limit")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtPayerLmt_PatientPayerLimit(PatientPayerLimit data) {

        return null;
    }

    @DELETE
    @Path("/payer/limit/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtPayerLmt_PatientPayerLimit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/limit/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayerLmt_PatientPayerLimit(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payer/limit")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPayerLmt_PatientPayerLimit(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("payer_id") String payerId) {

        return null;
    }

    //endregion

    //region PatientPet

    @POST
    @Path("/pet")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtPet_PatientPet(PatientPet data) {

        return null;
    }

    @PUT
    @Path("/pet")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtPet_PatientPet(PatientPet data) {

        return null;
    }

    @DELETE
    @Path("/pet/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtPet_PatientPet(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/pet/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPet_PatientPet(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/pet")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtPet_PatientPet(
            @MatrixParam("patient_env_sk") String patientEnvSk) {

        return null;
    }
    //endregion

    //region PatientRate

    @POST
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtRate_PatientRate(PatientRate data) {

        return null;
    }

    @PUT
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtRate_PatientRate(PatientRate data) {

        return null;
    }

    @DELETE
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtRate_PatientRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtRate_PatientRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtRate_PatientRate(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientRequirement

    @POST
    @Path("/requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtRqmt_PatientRequirement(PatientRequirement data) {

        return null;
    }

    @PUT
    @Path("/requirements")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtRqmt_PatientRequirement(PatientRequirement data) {

        return null;
    }

    @DELETE
    @Path("/requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtRqmt_PatientRequirement(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/requirements/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtRqmt_PatientRequirement(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/requirements")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtRqmt_PatientRequirement(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientSafetyMeasure

    @POST
    @Path("/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtSftyMeasure_PatientSafetyMeasure(PatientSafetyMeasure data) {

        return null;
    }

    @PUT
    @Path("/safety_measures")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtSftyMeasure_PatientSafetyMeasure(PatientSafetyMeasure data) {

        return null;
    }

    @DELETE
    @Path("/safety_measures/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtSftyMeasure_PatientSafetyMeasure(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/safety_measures/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtSftyMeasure_PatientSafetyMeasure(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/safety_measures")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtSftyMeasure_PatientSafetyMeasure(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region PatientStructuralBarrierDetail

    @POST
    @Path("/structural_barriers_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_insertPtStructBrrDet_PatientStructuralBarrierDetail(PatientStructuralBarrierDetail data) {

        return null;
    }

    @PUT
    @Path("/structural_barriers_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_updatePtStructBrrDet_PatientStructuralBarrierDetail(PatientStructuralBarrierDetail data) {

        return null;
    }

    @DELETE
    @Path("/structural_barriers_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_deletePtStructBrrDet_PatientStructuralBarrierDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/structural_barriers_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtStructBrrDet_PatientStructuralBarrierDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/structural_barriers_detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPtStructBrrDet_PatientStructuralBarrierDetail(
            @MatrixParam("patient_env_sk") String patientEnvSk) {

        return null;
    }
    //endregion

    //region Custom Methods

    //region Patient Designee
    @POST
    @Path("/designee/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PD_insertPdContDet_PatientDesigneeContactDetail(PatientDesigneeContactDetail data) {

        return null;
    }

    @PUT
    @Path("/designee/detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PD_updatePdContDet_PatientDesigneeContactDetail(PatientDesigneeContactDetail data) {

        return null;
    }

    @DELETE
    @Path("/designee/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PD_deletePdContDet_PatientDesigneeContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/designee/detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PD_getPdContDet_PatientDesigneeContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/designee/detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PD_getPdContactDetlForID_PatientDesigneeContactDetail(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion

    @GET
    @Path("/contact_detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientContactDetails_PatientContactDetail(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_findPatients_Response(
            @MatrixParam("keyword") String keyword,
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("home_phone") String homePhone,
            @MatrixParam("email") String email,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("last_name") String lastName,
            @MatrixParam("service") String service,
            @MatrixParam("sched_from_date_time") String schedFromDate, //12
            @MatrixParam("sched_to_date_time") String schedToDate,
            @MatrixParam("staff_id") String staffId, //14
            @MatrixParam("status") List<String> statusList,
            @MatrixParam("city") List<String> cityList,
            @MatrixParam("state") List<String> stateList,
            @MatrixParam("address") List<String> addressList,
            @MatrixParam("priority_levels") List<String> priorityLevelsList,
            @MatrixParam("coordinator_id") String coordinatorId, //20
            @MatrixParam("payer_id") String payerId,
            @MatrixParam("contract_id") String contractId,
            @MatrixParam("zip_code") String zipCode,
            @MatrixParam("ssn") String ssn, //24
            @MatrixParam("nurse_id") String nurseId,
            @MatrixParam("medicaid_id") String medicaidId)

    {
        return null;
    }

    @GET
    @Path("/distinct/first_names")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_distinctFirstNames_DistinctColumn(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize) {
        return null;
    }

    @GET
    @Path("/distinct/last_names")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_distinctLastNames_DistinctColumn(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("100") @MatrixParam("page_size") int pageSize) {
        return null;
    }

    @GET
    @Path("/validate/id")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_validatePatientId_Response(
            @MatrixParam("patient_id") String patientId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/find/staff/{staff_id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_findPatientsForStaffAgency_Response(
            @PathParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("PT_LAST_NAME") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/exists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_patientExists_Response(
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("patient_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/coordinators")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getPatientCoordinators_Response(
            @MatrixParam("patient_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/find/address")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getAddressByCityAndState_PatientContactAddress(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("city") List<String> cityList,
            @MatrixParam("state") List<String> stateList,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("addr1") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/find/city")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_PATIENT_getCityByState_PatientContactAddress(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("state") List<String> stateList,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("10") @MatrixParam("page_size") int pageSize,
            @DefaultValue("city") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    //endregion
}
