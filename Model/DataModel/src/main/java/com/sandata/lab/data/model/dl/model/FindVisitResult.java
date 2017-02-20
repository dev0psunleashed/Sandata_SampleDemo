package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 10/9/15
 * Time: 5:38 AM
 */

public class FindVisitResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("CallIn")
    private Date callIn;

    @SerializedName("CallOut")
    private Date callOut;

    @SerializedName("TaskCount")
    private int taskCount = 0;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientPhone")
    private String patientPhone;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffPhone")
    private String staffPhone;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("StaffManager")
    private String staffManager;

    @SerializedName("PlanOfCareID")
    private String pocId;

    @SerializedName("ScheduleEventStartDatetime")
    private Date scheduleEventStartDatetime;

    @SerializedName("ScheduleEventEndDatetime")
    private Date scheduleEventEndDatetime;

    @SerializedName("Service")
    private String service;

    @SerializedName("ScheduledHours")
    private String scheduledHours;

    @SerializedName("Visit")
    private Visit visit;

    @SerializedName("PrimaryPayerName")
    private String primaryPayerName;

    @SerializedName("PrimaryPayerSK")
    private BigInteger primaryPayerSk;

    @SerializedName("SecondaryPayerName")
    private String secondaryPayerName;

    @SerializedName("SecondaryPayerSK")
    private BigInteger secondaryPayerSk;

    @SerializedName("Company")
    private String company;

    @SerializedName("Location")
    private List<String> location;

    @SerializedName("TraveledMiles")
    private String traveledMiles;

    @SerializedName("EventCode")
    private String eventCode;

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffManager() {
        return staffManager;
    }

    public void setStaffManager(String staffManager) {
        this.staffManager = staffManager;
    }

    public String getPocId() {
        return pocId;
    }

    public void setPocId(String pocId) {
        this.pocId = pocId;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public Date getScheduleEventStartDatetime() {
        return scheduleEventStartDatetime;
    }

    public void setScheduleEventStartDatetime(Date scheduleEventStartDatetime) {
        this.scheduleEventStartDatetime = scheduleEventStartDatetime;
    }

    public Date getScheduleEventEndDatetime() {
        return scheduleEventEndDatetime;
    }

    public void setScheduleEventEndDatetime(Date scheduleEventEndDatetime) {
        this.scheduleEventEndDatetime = scheduleEventEndDatetime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getScheduledHours() {
        return scheduledHours;
    }

    public void setScheduledHours(String scheduledHours) {
        this.scheduledHours = scheduledHours;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Date getCallIn() {
        return callIn;
    }

    public void setCallIn(Date callIn) {
        this.callIn = callIn;
    }

    public Date getCallOut() {
        return callOut;
    }

    public void setCallOut(Date callOut) {
        this.callOut = callOut;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getPrimaryPayerName() {
        return primaryPayerName;
    }

    public void setPrimaryPayerName(String primaryPayerName) {
        this.primaryPayerName = primaryPayerName;
    }

    public BigInteger getPrimaryPayerSk() {
        return primaryPayerSk;
    }

    public void setPrimaryPayerSk(BigInteger primaryPayerSk) {
        this.primaryPayerSk = primaryPayerSk;
    }

    public String getSecondaryPayerName() {
        return secondaryPayerName;
    }

    public void setSecondaryPayerName(String secondaryPayerName) {
        this.secondaryPayerName = secondaryPayerName;
    }

    public BigInteger getSecondaryPayerSk() {
        return secondaryPayerSk;
    }

    public void setSecondaryPayerSk(BigInteger secondaryPayerSk) {
        this.secondaryPayerSk = secondaryPayerSk;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getLocation() {
        if(this.location ==  null) {
            this.location = new ArrayList<>();
        }
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = new ArrayList<>();
        this.location.addAll(location);
    }

    public String getTraveledMiles() {
        return traveledMiles;
    }

    public void setTraveledMiles(String traveledMiles) {
        this.traveledMiles = traveledMiles;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
}
