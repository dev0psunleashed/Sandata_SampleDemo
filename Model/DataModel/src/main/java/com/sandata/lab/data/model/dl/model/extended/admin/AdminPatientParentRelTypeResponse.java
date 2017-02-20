package com.sandata.lab.data.model.dl.model.extended.admin;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Models the Administrative Patient Parent Relationship Type Response and returns only the required properties.
 * <p/>
 *
 * @author David Rutgos
 */
public class AdminPatientParentRelTypeResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("AdministrativeStaffSK")
    protected BigInteger administrativeStaffSK;

    @SerializedName("AdministrativeStaffRelationshipSK")
    private BigInteger administrativeStaffRelationshipSK;

    @SerializedName("AdministrativeStaffID")
    private String administrativeStaffID;

    @SerializedName("AdministrativeStaffFirstName")
    private String administrativeStaffFirstName;

    @SerializedName("AdministrativeStaffLastName")
    private String administrativeStaffLastName;

    @SerializedName("AdministrativeStaffRelationshipTypeName")
    private String administrativeStaffRelationshipTypeName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    public BigInteger getAdministrativeStaffSK() {
        return administrativeStaffSK;
    }

    public void setAdministrativeStaffSK(BigInteger administrativeStaffSK) {
        this.administrativeStaffSK = administrativeStaffSK;
    }

    public BigInteger getAdministrativeStaffRelationshipSK() {
        return administrativeStaffRelationshipSK;
    }

    public void setAdministrativeStaffRelationshipSK(BigInteger administrativeStaffRelationshipSK) {
        this.administrativeStaffRelationshipSK = administrativeStaffRelationshipSK;
    }

    public String getAdministrativeStaffID() {
        return administrativeStaffID;
    }

    public void setAdministrativeStaffID(String administrativeStaffID) {
        this.administrativeStaffID = administrativeStaffID;
    }

    public String getAdministrativeStaffFirstName() {
        return administrativeStaffFirstName;
    }

    public void setAdministrativeStaffFirstName(String administrativeStaffFirstName) {
        this.administrativeStaffFirstName = administrativeStaffFirstName;
    }

    public String getAdministrativeStaffLastName() {
        return administrativeStaffLastName;
    }

    public void setAdministrativeStaffLastName(String administrativeStaffLastName) {
        this.administrativeStaffLastName = administrativeStaffLastName;
    }

    public String getAdministrativeStaffRelationshipTypeName() {
        return administrativeStaffRelationshipTypeName;
    }

    public void setAdministrativeStaffRelationshipTypeName(String administrativeStaffRelationshipTypeName) {
        this.administrativeStaffRelationshipTypeName = administrativeStaffRelationshipTypeName;
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
}
