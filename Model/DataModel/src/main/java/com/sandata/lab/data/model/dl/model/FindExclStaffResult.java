package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Model to return excluded staff for a given patient.
 * <p/>
 *
 * @author David Rutgos
 */
public class FindExclStaffResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffExcludedPatientID")
    private String staffExcludedPatientID;

    @SerializedName("PatientExcludedSK")
    private BigInteger patientExcludedSk;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("ReasonForExclusion")
    private String reasonForExclusion;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffMiddleName")
    private String staffMiddleName;

    public String getStaffExcludedPatientID() {
        return staffExcludedPatientID;
    }

    public void setStaffExcludedPatientID(String staffExcludedPatientID) {
        this.staffExcludedPatientID = staffExcludedPatientID;
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

    public String getReasonForExclusion() {
        return reasonForExclusion;
    }

    public void setReasonForExclusion(String reasonForExclusion) {
        this.reasonForExclusion = reasonForExclusion;
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
}
