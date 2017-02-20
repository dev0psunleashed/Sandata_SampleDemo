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

package com.sandata.lab.rest.staff;


import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffAvailabilitySpecific;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffRateExchange;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffTrainingClassEventEnrollmentExt;
import com.sandata.lab.data.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

public class RestfulServices {

    //region StaffWorkingPreference

    @POST
    @Path("/work_preference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffWrkPref_StaffWorkingPreference(StaffWorkingPreference data) {

        return null;
    }

    @PUT
    @Path("/work_preference")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffWrkPref_StaffWorkingPreference(StaffWorkingPreference data) {

        return null;
    }

    @DELETE
    @Path("/work_preference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffWrkPref_StaffWorkingPreference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/work_preference/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffWrkPref_StaffWorkingPreference(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/work_preference")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffWrkPrefForID_StaffWorkingPreference_STAFF_WRK_PREF(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    //region StaffTraining

    @POST
    @Path("/training")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffTrng_StaffTraining(StaffTraining data) {

        return null;
    }

    @PUT
    @Path("/training")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrng_StaffTraining(StaffTraining data) {

        return null;
    }

    @DELETE
    @Path("/training/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrng_StaffTraining(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrng_StaffTraining(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngForID_StaffTraining_STAFF_TRNG(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("stsd") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }
    //endregion

    //region StaffSkill

    @POST
    @Path("/skills")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffSkill_StaffSkill(StaffSkill data) {

        return null;
    }

    @PUT
    @Path("/skills")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffSkill_StaffSkill(StaffSkill data) {

        return null;
    }

    @DELETE
    @Path("/skills/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffSkill_StaffSkill(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/skills/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffSkill_StaffSkill(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/skills")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffSkillForID_StaffSkill_STAFF_SKILL(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }
    //endregion

    // region StaffRate
    @POST
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffRate_StaffRate(StaffRate data) {

        return null;
    }

    @PUT
    @Path("/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffRate_StaffRate(StaffRate data) {

        return null;
    }

    @DELETE
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffRate_StaffRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffRate_StaffRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffRateForID_StaffRate_STAFF_RATE(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffPayrollDetail

    @POST
    @Path("/payroll_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffPrDet_StaffPayrollDetail(StaffPayrollDetail data) {

        return null;
    }

    @PUT
    @Path("/payroll_detail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffPrDet_StaffPayrollDetail(StaffPayrollDetail data) {

        return null;
    }

    @DELETE
    @Path("/payroll_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffPrDet_StaffPayrollDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payroll_detail/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffPrDet_StaffPayrollDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/payroll_detail")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffPrDetlForID_StaffPayrollDetail_STAFF_PR_DET(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffPatientHistory

    @POST
    @Path("/patient_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffPtHist_StaffPatientHistory(StaffPatientHistory data) {

        return null;
    }

    @PUT
    @Path("/patient_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffPtHist_StaffPatientHistory(StaffPatientHistory data) {

        return null;
    }

    @DELETE
    @Path("/patient_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffPtHist_StaffPatientHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/patient_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffPtHist_StaffPatientHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/patient_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffPtHist_StaffPatientHistory(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("patient_id") String patientId) {

        return null;
    }

    // endregion

    // region StaffNotificationMethod

    @POST
    @Path("/notification_method")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffNtfctnMthd_StaffNotificationMethod(StaffNotificationMethod data) {

        return null;
    }

    @PUT
    @Path("/notification_method")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffNtfctnMthd_StaffNotificationMethod(StaffNotificationMethod data) {

        return null;
    }

    @DELETE
    @Path("/notification_method/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffNtfctnMthd_StaffNotificationMethod(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notification_method/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffNtfctnMthd_StaffNotificationMethod(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notification_method")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffNtfctnMthdForID_StaffNotificationMethod_STAFF_NTFCTN_MTHD(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffNote

    @POST
    @Path("/notes")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffNote_StaffNote(StaffNote data) {

        return null;
    }

    @PUT
    @Path("/notes")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffNote_StaffNote(StaffNote data) {

        return null;
    }

    @DELETE
    @Path("/notes/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffNote_StaffNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notes/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffNote_StaffNote(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/notes")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffNoteForID_StaffNote_STAFF_NOTE(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffMedicalHistory

    @POST
    @Path("/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffMedclHist_StaffMedicalHistory(StaffMedicalHistory data) {

        return null;
    }

    @PUT
    @Path("/medical_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffMedclHist_StaffMedicalHistory(StaffMedicalHistory data) {

        return null;
    }

    @DELETE
    @Path("/medical_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffMedclHist_StaffMedicalHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medical_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffMedclHist_StaffMedicalHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/medical_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffMedHistForID_StaffMedicalHistory_STAFF_MEDCL_HIST(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffHiringHistory

    /*@POST
    @Path("/hiring_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffHiringHist_StaffHiringHistory(StaffHiringHistory data) {

        return null;
    }

    @PUT
    @Path("/hiring_history")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffHiringHist_StaffHiringHistory(StaffHiringHistory data) {

        return null;
    }

    @DELETE
    @Path("/hiring_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffHiringHist_StaffHiringHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/hiring_history/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffHiringHist_StaffHiringHistory(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/hiring_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffHiringHistoryWithSort_StaffHiringHistory(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }*/

    @GET
    @Path("/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffHistoryWithSort_Staff(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/hiring_history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffHiringHistoryWithSort_StaffHiringHistory(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction) {

        return null;
    }
    // endregion

    // region StaffEmploymentStatusHistory

    @GET
    @Path("/employment/status/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffEmploymentStatusHistoryWithSort_Staff(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("EmploymentStatusChangeDate") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction) {

        return null;
    }
    
    @GET
    @Path("/profile/changes/history")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffProfileChangesHistory_StaffProfileChangedLog(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @DefaultValue("ChangedOn") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("DESC") @MatrixParam("direction") String direction){

        return null;
    }

    // endregion

    // region StaffDeduction

    @POST
    @Path("/deductions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffDed_StaffDeduction(StaffDeduction data) {

        return null;
    }

    @PUT
    @Path("/deductions")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffDed_StaffDeduction(StaffDeduction data) {

        return null;
    }

    @DELETE
    @Path("/deductions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffDed_StaffDeduction(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/deductions/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffDed_StaffDeduction(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/deductions")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffDed_StaffDeduction(
        @MatrixParam("staff_pr_detl_sk") String staffPrDetlSk) {

        return null;
    }

    // endregion

    // region StaffCredential

    @POST
    @Path("/credentials")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffCredential_StaffCredential(StaffCredential data) {

        return null;
    }

    @PUT
    @Path("/credentials")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffCredential_StaffCredential(StaffCredential data) {

        return null;
    }

    @DELETE
    @Path("/credentials/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffCredential_StaffCredential(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/credentials/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCredential_StaffCredential(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/credentials")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCredentialForID_StaffCredential_STAFF_CREDENTIAL(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffCompliance

    @POST
    @Path("/compliance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffComp_StaffCompliance(StaffCompliance data) {

        return null;
    }

    @PUT
    @Path("/compliance")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffComp_StaffCompliance(StaffCompliance data) {

        return null;
    }

    @DELETE
    @Path("/compliance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffComp_StaffCompliance(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffComp_StaffCompliance(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/compliance")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCompWithSort_StaffCompliance(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("50") @MatrixParam("page_size") int pageSize) {

        return null;
    }

    // endregion

    // region StaffCertification

    @POST
    @Path("/certifications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffCert_StaffCertification(StaffCertification data) {

        return null;
    }

    @PUT
    @Path("/certifications")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffCert_StaffCertification(StaffCertification data) {

        return null;
    }

    @DELETE
    @Path("/certifications/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffCert_StaffCertification(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/certifications/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCert_StaffCertification(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/certifications")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCertForID_StaffCertification_STAFF_CERT(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffAvailability

    @POST
    @Path("/availability")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffAvail_StaffAvailability(StaffAvailability data) {

        return null;
    }

    @PUT
    @Path("/availability")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffAvail_StaffAvailability(StaffAvailability data) {

        return null;
    }
    
    @POST
    @Path("/availability/specific")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffSpecificAvail_StaffAvailabilitySpecific(StaffAvailabilitySpecific data) {

        return null;
    }
    
    @PUT
    @Path("/availability/specific")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffSpecificAvail_StaffAvailabilitySpecific(StaffAvailabilitySpecific data) {

        return null;
    }
    
    /**
     * returns specific available days for specific <code>staffId</code> and <code>bsnEntId</code>
     * 
     * @param staffId
     * @param bsnEntId
     * @return
     */
    @GET
    @Path("/availability/specific")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffSpecificAvail_StaffAvailabilitySpecific(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("from_date_time") String fromDateTime,
            @MatrixParam("to_date_time") String toDateTime,
            @MatrixParam("is_available") Boolean isAvailable,
            @DefaultValue("AVAIL_START_HOUR") @MatrixParam("sort_on") String sortOn,
            @DefaultValue("ASC") @MatrixParam("direction") String direction,
            @DefaultValue("1") @MatrixParam("page") int page,
            @DefaultValue("50") @MatrixParam("page_size") int pageSize) {

        return null;
    }

    @DELETE
    @Path("/availability/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffAvail_StaffAvailability(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/availability/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAvail_StaffAvailability(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    /**
     * returns all the general available days for specific <code>staffId</code> and <code>bsnEntId</code>
     * 
     * @param staffId
     * @param bsnEntId
     * @return
     */
    @GET
    @Path("/availability")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAvailForID_StaffAvailability_STAFF_AVAIL(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffAdmissionType

    @POST
    @Path("/admission_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffAdmTyp_StaffAdmissionType(StaffAdmissionType data) {

        return null;
    }

    @PUT
    @Path("/admission_type")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffAdmTyp_StaffAdmissionType(StaffAdmissionType data) {

        return null;
    }

    @DELETE
    @Path("/admission_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffAdmTyp_StaffAdmissionType(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admission_type/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAdmTyp_StaffAdmissionType(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admission_type")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAdmTypeForID_StaffAdmissionType_STAFF_ADM_TYP(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffAdministrativeAssessment

    @POST
    @Path("/admin_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffAdminAsmt_StaffAdministrativeAssessment(StaffAdministrativeAssessment data) {

        return null;
    }

    @PUT
    @Path("/admin_assessment")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffAdminAsmt_StaffAdministrativeAssessment(StaffAdministrativeAssessment data) {

        return null;
    }

    @DELETE
    @Path("/admin_assessment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffAdminAsmt_StaffAdministrativeAssessment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin_assessment/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAdminAsmt_StaffAdministrativeAssessment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/admin_assessment")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAdminAsmtForID_StaffAdministrativeAssessment_STAFF_ADMIN_ASMT(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffContactAddress

    @POST
    @Path("/contact_address")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffContAddr_StaffContactAddress(StaffContactAddress data) {

        return null;
    }

    @PUT
    @Path("/contact_address")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffContAddr_StaffContactAddress(StaffContactAddress data) {

        return null;
    }

    @DELETE
    @Path("/contact_address/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffContAddr_StaffContactAddress(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_address/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffContAddr_StaffContactAddress(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_address")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffContactAddressForID_StaffContactAddress_STAFF_CONT_ADDR(
        @MatrixParam("staff_id") String staffSk,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffContactPhone

    @POST
    @Path("/contact_phone")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffContPhone_StaffContactPhone(StaffContactPhone data) {

        return null;
    }

    @PUT
    @Path("/contact_phone")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffContPhone_StaffContactPhone(StaffContactPhone data) {

        return null;
    }

    @DELETE
    @Path("/contact_phone/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffContPhone_StaffContactPhone(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_phone/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffContPhone_StaffContactPhone(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/contact_phone")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffContactPhoneForID_StaffContactPhone_STAFF_CONT_PHONE(
        @MatrixParam("staff_id") String staffSk,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region StaffInCaseOfEmergencyContactDetail

    @POST
    @Path("/emergency_contact")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffIceContDet_StaffInCaseOfEmergencyContactDetail(StaffInCaseOfEmergencyContactDetail data) {

        return null;
    }

    @PUT
    @Path("/emergency_contact")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffIceContDet_StaffInCaseOfEmergencyContactDetail(StaffInCaseOfEmergencyContactDetail data) {

        return null;
    }

    @DELETE
    @Path("/emergency_contact/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffIceContDet_StaffInCaseOfEmergencyContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/emergency_contact/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffIceContDet_StaffInCaseOfEmergencyContactDetail(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/emergency_contact")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffIceContDet_StaffInCaseOfEmergencyContactDetail_STAFF_ICE_CONT_DETL(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region Staff

    @POST
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaff_Staff(StaffExt data) {

        return null;
    }

    @PUT
    @Path("/")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_HIST_updateStaff_Staff(StaffExt data) {

        return null;
    }

    @DELETE
    @Path("/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaff_Staff(@PathParam("sequence_key") Long sequenceKey) {

        return null;
    }

    @GET
    @Path("/{staff_sk}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffForSK_Staff(@PathParam("staff_sk") Long sequenceKey) {

        return null;
    }

    @GET
    @Path("/")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffForPK_Staff(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @GET
    @Path("/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_findStaff_Response(
        @MatrixParam("last_name") String lastName,
        @MatrixParam("first_name") String firstName,
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("home_phone") String homePhone,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("ln") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("keyword") String keyword,
        @MatrixParam("sched_from_date_time") String fromDate, //10
        @MatrixParam("sched_to_date_time") String toDate, //11
        @MatrixParam("position") String position, //12
        @MatrixParam("patient_id") String patientId,//13
        @MatrixParam("employment_status_type_name") List<String> employmentStatusList,//14
        @MatrixParam("compliance_status") String complianceStatus, //15
        @MatrixParam("zip_code") String zipCode,
        @MatrixParam("coordinator") String staffCoordinator,
        @MatrixParam("ssn") String ssn,
        @MatrixParam("city") List<String> cities,
        @MatrixParam("address") String address, //20
        @MatrixParam("state") String state, //21
        @MatrixParam("language") String language,
        @MatrixParam("staff_nurse") String staff_nurse ) {
        return null;
    }

    @GET
    @Path("/exists")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_staffExists_Response(
        @MatrixParam("last_name") String lastName,
        @MatrixParam("first_name") String firstName,
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/distinct/first_names")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_distinctFirstNames_DistinctColumn(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("100") @MatrixParam("page_size") int pageSize) {
        return null;
    }

    @GET
    @Path("/distinct/last_names")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_distinctLastNames_DistinctColumn(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("100") @MatrixParam("page_size") int pageSize) {
        return null;
    }

    @GET
    @Path("/validate_patient")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_validatePatientExists_Long(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/is_patient_excluded")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_isPatientExcluded_Long(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/schedule_events")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_staffScheduleEvents_Response(
        @DefaultValue("") @MatrixParam("keyword") String keyword,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize) {
        return null;
    }

    @GET
    @Path("/find/training/category")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_findStaffForTrainingCategory_Response(
          @MatrixParam("bsn_ent_id") String bsnEntId,
          @MatrixParam("staff_trng_loc_name") String staffTrngLocName,
          @MatrixParam("staff_trng_code") String staffTrngCode,
          @MatrixParam("staff_trng_start_dtime") String staffTrngStartDateTime,
          @MatrixParam("employment_status_type_name") String employmentStatusTypeNameString,
          @MatrixParam("compliant") Boolean compliant,
          @MatrixParam("staff_id") String staffId,
          @MatrixParam("first_name") String firstName,
          @MatrixParam("last_name") String lastName,
          @DefaultValue("1") @MatrixParam("page") int page,
          @DefaultValue("10") @MatrixParam("page_size") int pageSize,
          @DefaultValue("StaffLastName") @MatrixParam("sort_on") String sortOn,
          @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    //region StaffLanguage

    @POST
    @Path("/language")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffLang_StaffLanguage(StaffLanguage data) {

        return null;
    }

    @PUT
    @Path("/language")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffLang_StaffLanguage(StaffLanguage data) {

        return null;
    }

    @DELETE
    @Path("/language/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffLang_StaffLanguage(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/language/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffLang_StaffLanguage(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/language")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffLang_StaffLanguage(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    @POST
    @Path("/language/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffLanguageLst_StaffLanguageList(@MatrixParam("staff_id") String staffId,
                                                                             @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                             List<String> languages) {
        return null;
    }

    @PUT
    @Path("/language/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffLanguageLst_StaffLanguageList(@MatrixParam("staff_id") String staffId,
                                                                             @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                             List<String> languages) {
        return null;
    }

    @DELETE
    @Path("/language/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffLanguageLst_StaffLanguageList(@MatrixParam("staff_id") String staffId,
                                                                             @MatrixParam("bsn_ent_id") String bsnEntId,
                                                                             List<String> languages) {
        return null;
    }

    //endregion

    // region StaffRelationship

    @GET
    @Path("/relationship")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffRelationship_Response(
        @MatrixParam("rel_type") String relType,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    // endregion

    // region StaffTrainingLocation

    @POST
    @Path("/training/location")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffTrngLoc_StaffTrainingLocation(StaffTrainingLocation data) {

        return null;
    }

    @PUT
    @Path("/training/location")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrngLoc_StaffTrainingLocation(StaffTrainingLocation data) {

        return null;
    }

    @DELETE
    @Path("/training/location/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrngLoc_StaffTrainingLocation(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training/location/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngLoc_StaffTrainingLocation(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }


    @GET
    @Path("/training/location")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngLoc_StaffTrainingLocation(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                               @MatrixParam("staff_trng_code") String staffTrngCode,
                                                                               @DefaultValue("1") @MatrixParam("page") int page,
                                                                               @DefaultValue("50") @MatrixParam("page_size") int pageSize,
                                                                               @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
                                                                               @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    // region StaffTrainingClassEvent

    @POST
    @Path("/training/class/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffTrngClsEvnt_StaffTrainingClassEvent(StaffTrainingClassEvent staffTrainingClassEvent) {

        return null;
    }

    @PUT
    @Path("/training/class/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrngClsEvnt_StaffTrainingClassEvent(StaffTrainingClassEvent staffTrainingClassEvent) {

        return null;
    }

    @DELETE
    @Path("/training/class/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrngClsEvnt_StaffTrainingClassEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training/class/event/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvnt_StaffTrainingClassEvent(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training/class/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvnt_StaffTrainingClassEvent(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                @MatrixParam("staff_trng_code") String staffTrngCode,
                                                                                @MatrixParam("staff_trng_loc_name") String staffTrngLocName,
                                                                                @DefaultValue("1") @MatrixParam("page") int page,
                                                                                @DefaultValue("50") @MatrixParam("page_size") int pageSize,
                                                                                @DefaultValue("REC_CREATE_TMSTP") @MatrixParam("sort_on") String sortOn,
                                                                                @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/training/location/class/event")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvntByLocation_StaffTrainingClassEvent(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                          @MatrixParam("staff_trng_loc_name") String staffTrngLocName,
                                                                                          @DefaultValue("1") @MatrixParam("page") int page,
                                                                                          @DefaultValue("50") @MatrixParam("page_size") int pageSize,
                                                                                          @DefaultValue("start_time") @MatrixParam("sort_on") String sortOn,
                                                                                          @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    // region StaffTrainingClassEventEnrollment

    @POST
    @Path("/training/class/event/enroll")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffTrngClsEvntEnrol_StaffTrainingClassEventEnrollment(StaffTrainingClassEventEnrollment data) {
        return null;
    }

    @PUT
    @Path("/training/class/event/enroll")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrngClsEvntEnrol_StaffTrainingClassEventEnrollment(StaffTrainingClassEventEnrollment data) {
        return null;
    }

    @DELETE
    @Path("/training/class/event/enroll/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffTrngClsEvntEnrol_StaffTrainingClassEventEnrollment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/training/class/event/enroll/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvntEnrol_StaffTrainingClassEventEnrollment(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @POST
    @Path("/training/class/event/enroll/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvntEnrolList_StaffTrainingClassEventEnrollmentExt(List<StaffTrainingClassEventEnrollmentExt> data) {
        return null;
    }

    @POST
    @Path("/training/class/event/enroll/list/confirm")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffTrngClsEvntEnrolListConfirm_StaffTrainingClassEventEnrollmentExt(List<StaffTrainingClassEventEnrollmentExt> data) {
        return null;
    }

    @PUT
    @Path("/training/class/event/enroll/list")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffTrngClsEvntEnrolList_StaffTrainingClassEventEnrollmentExt(List<StaffTrainingClassEventEnrollmentExt> data) {
        return null;
    }

    @GET
    @Path("/training/class/event/enroll")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffTrngClsEvntEnrol_StaffTrainingClassEventEnrollmentExt(@MatrixParam("bsn_ent_id") String bsnEntId,
                                                                                               @MatrixParam("staff_trng_loc_name") String staffTrngLocName,
                                                                                               @MatrixParam("staff_trng_code") String staffTrngCode,
                                                                                               @MatrixParam("staff_trng_start_dtime") String staffTrngStartDateTime,
                                                                                               @DefaultValue("1") @MatrixParam("page") int page,
                                                                                               @DefaultValue("50") @MatrixParam("page_size") int pageSize,
                                                                                               @DefaultValue("STAFF_LAST_NAME") @MatrixParam("sort_on") String sortOn,
                                                                                               @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    @GET
    @Path("/training/class/find")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_findClassByStaff_FindClassResult(
                                                              @MatrixParam("bsn_ent_id") String bsnEntId,
                                                              @MatrixParam("staff_id") String staffId,
                                                              @MatrixParam("from_date_time") String fromDateTime,
                                                              @MatrixParam("to_date_time") String toDateTime,
                                                              @DefaultValue("1") @MatrixParam("page") int page,
                                                              @DefaultValue("10") @MatrixParam("page_size") int pageSize,
                                                              @DefaultValue("STAFF_TRNG_START_DTIME") @MatrixParam("sort_on") String sortOn,
                                                              @DefaultValue("ASC") @MatrixParam("direction") String direction) {

        return null;
    }

    // endregion

    // region StaffAssociatedRate

    @POST
    @Path("/associated/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_insertStaffAssocRate_StaffAssociatedRate(StaffAssociatedRate data) {

        return null;
    }

    @PUT
    @Path("/associated/rate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_updateStaffAssocRate_StaffAssociatedRate(StaffAssociatedRate data) {

        return null;
    }

    @DELETE
    @Path("/associated/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_deleteStaffAssocRate_StaffAssociatedRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }

    @GET
    @Path("/associated/rate/{sequence_key}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAssocRate_StaffAssociatedRate(@PathParam("sequence_key") long sequenceKey) {

        return null;
    }


    @GET
    @Path("/associated/rate")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffAssocRatePK_StaffAssociatedRate(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {

        return null;
    }

    // endregion

    // region Custom Methods

    @GET
    @Path("/admins")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getAdminStaffRel_Response(
            @MatrixParam("staff_id") String staffId,
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("staff_position") String staffRelType) {
        return null;
    }

    @GET
    @Path("/managers")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffManagers_Response(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/coordinators")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffCoordinators_Response(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/nurses")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getStaffNurses_Response(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/validate/id")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_validateStaffId_Response(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/excluded_patients")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_excludedPatients_Response(
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    @GET
    @Path("/excluded_staff")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_excludedStaff_Response(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @GET
    @Path("/is_staff_excluded")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_isStaffExcluded_Long(
        @MatrixParam("patient_id") String patientId,
        @MatrixParam("staff_id") String staffId,
        @MatrixParam("bsn_ent_id") String bsnEntId) {
        return null;
    }

    @POST
    @Path("/rate/add")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_addStaffRate(
        @DefaultValue("REGULAR") @MatrixParam("type") String type,
        @DefaultValue("PAY") @MatrixParam("code") String code,
        StaffRateExchange staffRateExchange) {
        return null;
    }

    // endregion

    // region Administrative Staff

    @GET
    @Path("/admin")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_ADMIN_getAdminStaffs_AdministrativeStaff(
        @MatrixParam("bsn_ent_id") String bsnEntId,
        @DefaultValue("1") @MatrixParam("page") int page,
        @DefaultValue("10") @MatrixParam("page_size") int pageSize,
        @DefaultValue("AdministrativeStaffFirstName") @MatrixParam("sort_on") String sortOn,
        @DefaultValue("ASC") @MatrixParam("direction") String direction) {
        return null;
    }

    // endregion

    @GET
    @Path("/training/category/hours")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAFF_getTrainingCategoryTotalHours_TrainingCategoryTotalHoursResult(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("staff_id") String staffId) {
        return null;
    }
    
    @POST
    @Path("/validate")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public final Response PKG_STAF_validateExistStaffInformation_StaffExt(
            @MatrixParam("bsn_ent_id") String bsnEntId,
            @MatrixParam("editing_staff_sk") Long editingStaffSK,
            @MatrixParam("ssn") String ssn,
            @MatrixParam("tin") String tin,
            @MatrixParam("email") String email,
            @MatrixParam("last_name") String lastName,
            @MatrixParam("first_name") String firstName,
            @MatrixParam("phone") String phone) {

        return null;
    }
}
