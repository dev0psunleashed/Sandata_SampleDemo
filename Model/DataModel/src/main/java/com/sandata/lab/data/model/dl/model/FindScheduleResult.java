package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 10/8/15
 * Time: 11:21 AM
 */

public class FindScheduleResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientAddr1")
    private String patientAddr1;

    @SerializedName("PatientAddr2")
    private String patientAddr2;

    @SerializedName("PatientApartmentNumber")
    private String patientApartmentNumber;

    @SerializedName("PatientCity")
    private String patientCity;

    @SerializedName("PatientState")
    private String patientState;

    @SerializedName("PatientPostalCode")
    private String patientPostalCode;

    @SerializedName("PatientZip")
    private String patientZip;

    @SerializedName("PatientPhoneQualifier")
    private PhoneQualifier patientPhoneQualifier;

    @SerializedName("PatientPhone")
    private String patientPhone;

    @SerializedName("PatientPhoneExtension")
    private String patientPhoneExtension;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffAddr1")
    private String staffAddr1;

    @SerializedName("StaffAddr2")
    private String staffAddr2;

    @SerializedName("StaffApartmentNumber")
    private String staffApartmentNumber;

    @SerializedName("StaffCity")
    private String staffCity;

    @SerializedName("StaffState")
    private String staffState;

    @SerializedName("StaffPostalCode")
    private String staffPostalCode;

    @SerializedName("StaffZip")
    private String staffZip;

    @SerializedName("ServiceName")
    private String serviceName;

    @SerializedName("Schedule")
    private Schedule schedule;

    @SerializedName("ScheduleEventStatus")
    private String scheduleEventStatus;



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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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

    public String getScheduleEventStatus() {
        return scheduleEventStatus;
    }

    public void setScheduleEventStatus(String scheduleEventStatus) {
        this.scheduleEventStatus = scheduleEventStatus;
    }

    public String getPatientAddr1() {
        return patientAddr1;
    }

    public void setPatientAddr1(String patientAddr1) {
        this.patientAddr1 = patientAddr1;
    }

    public String getPatientAddr2() {
        return patientAddr2;
    }

    public void setPatientAddr2(String patientAddr2) {
        this.patientAddr2 = patientAddr2;
    }

    public String getPatientApartmentNumber() {
        return patientApartmentNumber;
    }

    public void setPatientApartmentNumber(String patientApartmentNumber) {
        this.patientApartmentNumber = patientApartmentNumber;
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

    public String getPatientZip() {
        return patientZip;
    }

    public void setPatientZip(String patientZip) {
        this.patientZip = patientZip;
    }

    public String getStaffAddr1() {
        return staffAddr1;
    }

    public void setStaffAddr1(String staffAddr1) {
        this.staffAddr1 = staffAddr1;
    }

    public String getStaffAddr2() {
        return staffAddr2;
    }

    public void setStaffAddr2(String staffAddr2) {
        this.staffAddr2 = staffAddr2;
    }

    public String getStaffApartmentNumber() {
        return staffApartmentNumber;
    }

    public void setStaffApartmentNumber(String staffApartmentNumber) {
        this.staffApartmentNumber = staffApartmentNumber;
    }

    public String getStaffCity() {
        return staffCity;
    }

    public void setStaffCity(String staffCity) {
        this.staffCity = staffCity;
    }

    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    public String getStaffPostalCode() {
        return staffPostalCode;
    }

    public void setStaffPostalCode(String staffPostalCode) {
        this.staffPostalCode = staffPostalCode;
    }

    public String getStaffZip() {
        return staffZip;
    }

    public void setStaffZip(String staffZip) {
        this.staffZip = staffZip;
    }

    public PhoneQualifier getPatientPhoneQualifier() {
        return patientPhoneQualifier;
    }

    public void setPatientPhoneQualifier(PhoneQualifier patientPhoneQualifier) {
        this.patientPhoneQualifier = patientPhoneQualifier;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhoneExtension() {
        return patientPhoneExtension;
    }

    public void setPatientPhoneExtension(String patientPhoneExtension) {
        this.patientPhoneExtension = patientPhoneExtension;
    }
}
