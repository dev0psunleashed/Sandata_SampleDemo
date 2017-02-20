package com.sandata.one.aggregator.audit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

public class ReviewVisitPatientResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("MedicaidID")
    private String medicaidID;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientMiddleName")
    private String patientMiddleName;

    @SerializedName("PrimaryPatientPhoneNumber")
    private String primaryPatientPhoneNumber;

    @SerializedName("PatientDOB")
    private Date patientDob;

    @SerializedName("CoordinatorID")
    private String coordinatorId;

    @SerializedName("CoordinatorFirstName")
    private String coordinatorFirstName;

    @SerializedName("CoordinatorLastName")
    private String coordinatorLastName;

    @SerializedName("ManagerID")
    private String managerId;

    @SerializedName("ManagerFirstName")
    private String managerFirstName;

    @SerializedName("ManagerLastName")
    private String managerLastName;

    @SerializedName("ThirdPartyPatientID")
    private String thirdPartyPatientID = "N/A";

    @SerializedName("DischargeDate")
    private Date dischargeDate;

    @SerializedName("PatientAddress1")
    private String patientAddress1;

    @SerializedName("PatientAddress2")
    private String patientAddress2;

    @SerializedName("PatientApartment")
    private String patientApartment;

    @SerializedName("PatientCity")
    private String patientCity;

    @SerializedName("PatientState")
    private String patientState;

    @SerializedName("PatientZip4")
    private String patientZip4;

    @SerializedName("PatientPostalCode")
    private String patientPostalCode;

    @SerializedName("PatientCounty")
    private String patientCounty;

    @SerializedName("PatientPriorityLevelCode")
    private String patientPriorityLevelCode;

    public String getMedicaidID() {
        return medicaidID;
    }

    public void setMedicaidID(String medicaidID) {
        this.medicaidID = medicaidID;
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

    public String getPrimaryPatientPhoneNumber() {
        return primaryPatientPhoneNumber;
    }

    public void setPrimaryPatientPhoneNumber(String primaryPatientPhoneNumber) {
        this.primaryPatientPhoneNumber = primaryPatientPhoneNumber;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getCoordinatorFirstName() {
        return coordinatorFirstName;
    }

    public void setCoordinatorFirstName(String coordinatorFirstName) {
        this.coordinatorFirstName = coordinatorFirstName;
    }

    public String getCoordinatorLastName() {
        return coordinatorLastName;
    }

    public void setCoordinatorLastName(String coordinatorLastName) {
        this.coordinatorLastName = coordinatorLastName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getThirdPartyPatientID() {
        return thirdPartyPatientID;
    }

    public void setThirdPartyPatientID(String thirdPartyPatientID) {
        this.thirdPartyPatientID = thirdPartyPatientID;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getPatientAddress1() {
        return patientAddress1;
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress1 = patientAddress1;
    }

    public String getPatientAddress2() {
        return patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    public String getPatientApartment() {
        return patientApartment;
    }

    public void setPatientApartment(String patientApartment) {
        this.patientApartment = patientApartment;
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

    public String getPatientCounty() {
        return patientCounty;
    }

    public void setPatientCounty(String patientCounty) {
        this.patientCounty = patientCounty;
    }

    public Date getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(Date patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientZip4() {
        return patientZip4;
    }

    public void setPatientZip4(String patientZip4) {
        this.patientZip4 = patientZip4;
    }

    public String getPatientPostalCode() {
        return patientPostalCode;
    }

    public void setPatientPostalCode(String patientPostalCode) {
        this.patientPostalCode = patientPostalCode;
    }

    public String getPatientPriorityLevelCode() {
        return patientPriorityLevelCode;
    }

    public void setPatientPriorityLevelCode(String patientPriorityLevelCode) {
        this.patientPriorityLevelCode = patientPriorityLevelCode;
    }
}
