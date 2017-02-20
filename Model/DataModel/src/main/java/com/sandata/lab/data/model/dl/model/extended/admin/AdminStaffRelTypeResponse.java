package com.sandata.lab.data.model.dl.model.extended.admin;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Models the Administrative Staff Relationship Type Response and returns only the required properties.
 * <p/>
 *
 * @author David Rutgos
 */
public class AdminStaffRelTypeResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("AdministrativeStaffSK")
    protected BigInteger administrativeStaffSK;

    @SerializedName("AdministrativeStaffRoleCrossReferenceSK")
    private BigInteger administrativeStaffRoleCrossReferenceSK;

    @SerializedName("AdministrativeStaffID")
    private String administrativeStaffID;

    @SerializedName("AdministrativeStaffFirstName")
    private String administrativeStaffFirstName;

    @SerializedName("AdministrativeStaffLastName")
    private String administrativeStaffLastName;

    @SerializedName("AdministrativeStaffRoleName")
    private String administrativeStaffRoleName;

    public BigInteger getAdministrativeStaffSK() {
        return administrativeStaffSK;
    }

    public void setAdministrativeStaffSK(BigInteger administrativeStaffSK) {
        this.administrativeStaffSK = administrativeStaffSK;
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

    public BigInteger getAdministrativeStaffRoleCrossReferenceSK() {
        return administrativeStaffRoleCrossReferenceSK;
    }

    public void setAdministrativeStaffRoleCrossReferenceSK(BigInteger administrativeStaffRoleCrossReferenceSK) {
        this.administrativeStaffRoleCrossReferenceSK = administrativeStaffRoleCrossReferenceSK;
    }

    public String getAdministrativeStaffRoleName() {
        return administrativeStaffRoleName;
    }

    public void setAdministrativeStaffRoleName(String administrativeStaffRoleName) {
        this.administrativeStaffRoleName = administrativeStaffRoleName;
    }
}
