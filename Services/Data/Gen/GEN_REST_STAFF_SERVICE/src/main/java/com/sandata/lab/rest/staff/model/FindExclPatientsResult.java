package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Entity that models the excluded patients result.
 * <p/>
 *
 * @author David Rutgos
 */
public class FindExclPatientsResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffExcludedPatientID")
    private String staffExcludedPatientID;
    @SerializedName("ScheduleParticipantExclusionSK")
    private BigInteger scheduleParticipantExclusionSK;
    @SerializedName("StaffID")
    private String staffId;
    @SerializedName("ScheduleParticipantExclusionNote")
    private String scheduleParticipantExclusionNote;
    @SerializedName("PatientSK")
    private BigInteger patientSK;
    @SerializedName("PatientID")
    private String patientId;
    @SerializedName("PatientFirstName")
    private String patientFirstName;
    @SerializedName("PatientLastName")
    private String patientLastName;
    @SerializedName("PatientMiddleName")
    private String patientMiddleName;
    @SerializedName("ScheduleParticipantExclusionQualifier")
    private String scheduleParticipantExclusionQualifier;
    //dmr--GEOR-5000: SCHED_PERM_QLFR
    @SerializedName("SchedulePermissionQualifier")
    private String schedulePermissionQualifier;
    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    public String getStaffExcludedPatientID() {
        return staffExcludedPatientID;
    }

    public void setStaffExcludedPatientID(String staffExcludedPatientID) {
        this.staffExcludedPatientID = staffExcludedPatientID;
    }

    public BigInteger getScheduleParticipantExclusionSK() {
        return scheduleParticipantExclusionSK;
    }

    public void setScheduleParticipantExclusionSK(BigInteger scheduleParticipantExclusionSK) {
        this.scheduleParticipantExclusionSK = scheduleParticipantExclusionSK;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getScheduleParticipantExclusionNote() {
        return scheduleParticipantExclusionNote;
    }

    public void setScheduleParticipantExclusionNote(String scheduleParticipantExclusionNote) {
        this.scheduleParticipantExclusionNote = scheduleParticipantExclusionNote;
    }

    public BigInteger getPatientSK() {
        return patientSK;
    }

    public void setPatientSK(BigInteger patientSK) {
        this.patientSK = patientSK;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getScheduleParticipantExclusionQualifier() {
        return scheduleParticipantExclusionQualifier;
    }

    public void setScheduleParticipantExclusionQualifier(String scheduleParticipantExclusionQualifier) {
        this.scheduleParticipantExclusionQualifier = scheduleParticipantExclusionQualifier;
    }

    public String getSchedulePermissionQualifier() {
        return schedulePermissionQualifier;
    }

    public void setSchedulePermissionQualifier(String schedulePermissionQualifier) {
        this.schedulePermissionQualifier = schedulePermissionQualifier;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }
    
}
