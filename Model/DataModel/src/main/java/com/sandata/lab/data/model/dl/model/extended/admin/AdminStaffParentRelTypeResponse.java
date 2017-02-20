package com.sandata.lab.data.model.dl.model.extended.admin;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Models the Administrative Staff Parent Relationship Type Response and returns only the required properties.
 * <p/>
 *
 * @author David Rutgos
 */
public class AdminStaffParentRelTypeResponse extends BaseObject {

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

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
}
