package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Entity that models the excluded staff result.
 * <p/>
 *
 * @author David Rutgos
 */
public class FindExclStaffResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffExcludedPatientID")
    private String staffExcludedPatientID;
    @SerializedName("ScheduleParticipantExclusionSK")
    private BigInteger scheduleParticipantExclusionSK;
    @SerializedName("PatientExcludedSK")
    private BigInteger patientExcludedSk;
    @SerializedName("StaffID")
    private String staffId;
    @SerializedName("ScheduleParticipantExclusionNote")
    private String scheduleParticipantExclusionNote;
    @SerializedName("PatientID")
    private String patientId;
    @SerializedName("StaffFirstName")
    private String staffFirstName;
    @SerializedName("StaffLastName")
    private String staffLastName;
    @SerializedName("StaffMiddleName")
    private String staffMiddleName;
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

	public BigInteger getPatientExcludedSk() {
        return patientExcludedSk;
    }

    public void setPatientExcludedSk(BigInteger patientExcludedSk) {
        this.patientExcludedSk = patientExcludedSk;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffMiddleName() {
        return staffMiddleName;
    }

    public void setStaffMiddleName(String staffMiddleName) {
        this.staffMiddleName = staffMiddleName;
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
