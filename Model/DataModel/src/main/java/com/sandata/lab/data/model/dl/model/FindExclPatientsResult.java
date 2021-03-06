//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2015.09.10 at 06:16:03 PM EDT
//


package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Date: 9/17/15
 * Time: 6:21 PM
 */

public class FindExclPatientsResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffExcludedPatientID")
    private String staffExcludedPatientID;

    @SerializedName("PatientExcludedSK")
    private BigInteger patientExcludedSK;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("ReasonForExclusion")
    private String reasonForExclusion;

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

    public String getStaffExcludedPatientID() {
        return staffExcludedPatientID;
    }

    public void setStaffExcludedPatientID(String staffExcludedPatientID) {
        this.staffExcludedPatientID = staffExcludedPatientID;
    }

    public BigInteger getPatientExcludedSK() {
        return patientExcludedSK;
    }

    public void setPatientExcludedSK(BigInteger patientExcludedSK) {
        this.patientExcludedSK = patientExcludedSK;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public BigInteger getPatientSK() {
        return patientSK;
    }

    public void setPatientSK(BigInteger patientSK) {
        this.patientSK = patientSK;
    }
}
