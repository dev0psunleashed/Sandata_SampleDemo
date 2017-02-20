package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Date: 9/11/15
 * Time: 7:10 PM
 */

public class FindPatientResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientID")
    private String patientID;

    @SerializedName("PatientSK")
    private BigInteger patientSk;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientMiddleName")
    private String patientMiddleName;

    @SerializedName("PatientDateofBirth")
    private Date patientDateOfBirth;

    @SerializedName("PatientAddressLine1")
    private String patientAddressLine1;

    @SerializedName("PatientAddressLine2")
    private String patientAddressLine2;

    @SerializedName("PatientAptNumber")
    private String patientAptNumber;

    @SerializedName("PatientCity")
    private String patientCity;

    @SerializedName("PatientState")
    private String patientState;

    @SerializedName("PatientPostalCode")
    private String patientPostalCode;

    @SerializedName("PatientZip4")
    private String patientZip4;

    @SerializedName("PatientPhone")
    private String patientPhone;

    @SerializedName("PatientEmail")
    private String patientEmail;

    @SerializedName("BusinessEntityID")
    private String businessEntityId;

    @SerializedName("BusinessEntityName")
    private String businessEntityName;

    @SerializedName("BusinessEntityType")
    private String businessEntityType;

    @SerializedName("BusinessEntityPrimaryAddressLine1")
    private String businessEntityPrimaryAddressLine1;

    @SerializedName("BusinessEntityPrimaryAddressLine2")
    private String businessEntityPrimaryAddressLine2;

    @SerializedName("BusinessEntityPrimaryCity")
    private String businessEntityPrimaryCity;

    @SerializedName("BusinessEntityPrimaryState")
    private String businessEntityPrimaryState;

    @SerializedName("BusinessEntityPrimaryPostalCode")
    private String businessEntityPrimaryPostalCode;

    @SerializedName("BusinessEntityPrimaryZip4")
    private String businessEntityPrimaryZip4;

    @SerializedName("BusinessEntityContactPhone")
    private String businessEntityContactPhone;

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public BigInteger getPatientSk() {
        return patientSk;
    }

    public void setPatientSk(BigInteger patientSk) {
        this.patientSk = patientSk;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public Date getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(Date patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientAddressLine1() {
        return patientAddressLine1;
    }

    public void setPatientAddressLine1(String patientAddressLine1) {
        this.patientAddressLine1 = patientAddressLine1;
    }

    public String getPatientAddressLine2() {
        return patientAddressLine2;
    }

    public void setPatientAddressLine2(String patientAddressLine2) {
        this.patientAddressLine2 = patientAddressLine2;
    }

    public String getPatientAptNumber() {
        return patientAptNumber;
    }

    public void setPatientAptNumber(String patientAptNumber) {
        this.patientAptNumber = patientAptNumber;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientPostalCode() {
        return patientPostalCode;
    }

    public void setPatientPostalCode(String patientPostalCode) {
        this.patientPostalCode = patientPostalCode;
    }

    public String getPatientZip4() {
        return patientZip4;
    }

    public void setPatientZip4(String patientZip4) {
        this.patientZip4 = patientZip4;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getBusinessEntityType() {
        return businessEntityType;
    }

    public void setBusinessEntityType(String businessEntityType) {
        this.businessEntityType = businessEntityType;
    }

    public String getBusinessEntityPrimaryAddressLine1() {
        return businessEntityPrimaryAddressLine1;
    }

    public void setBusinessEntityPrimaryAddressLine1(String businessEntityPrimaryAddressLine1) {
        this.businessEntityPrimaryAddressLine1 = businessEntityPrimaryAddressLine1;
    }

    public String getBusinessEntityPrimaryAddressLine2() {
        return businessEntityPrimaryAddressLine2;
    }

    public void setBusinessEntityPrimaryAddressLine2(String businessEntityPrimaryAddressLine2) {
        this.businessEntityPrimaryAddressLine2 = businessEntityPrimaryAddressLine2;
    }

    public String getBusinessEntityPrimaryCity() {
        return businessEntityPrimaryCity;
    }

    public void setBusinessEntityPrimaryCity(String businessEntityPrimaryCity) {
        this.businessEntityPrimaryCity = businessEntityPrimaryCity;
    }

    public String getBusinessEntityPrimaryState() {
        return businessEntityPrimaryState;
    }

    public void setBusinessEntityPrimaryState(String businessEntityPrimaryState) {
        this.businessEntityPrimaryState = businessEntityPrimaryState;
    }

    public String getBusinessEntityPrimaryPostalCode() {
        return businessEntityPrimaryPostalCode;
    }

    public void setBusinessEntityPrimaryPostalCode(String businessEntityPrimaryPostalCode) {
        this.businessEntityPrimaryPostalCode = businessEntityPrimaryPostalCode;
    }

    public String getBusinessEntityPrimaryZip4() {
        return businessEntityPrimaryZip4;
    }

    public void setBusinessEntityPrimaryZip4(String businessEntityPrimaryZip4) {
        this.businessEntityPrimaryZip4 = businessEntityPrimaryZip4;
    }

    public String getBusinessEntityContactPhone() {
        return businessEntityContactPhone;
    }

    public void setBusinessEntityContactPhone(String businessEntityContactPhone) {
        this.businessEntityContactPhone = businessEntityContactPhone;
    }
}
