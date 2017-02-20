package com.sandata.lab.rest.visit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.extended.visit.VisitExt;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Models the response of the find visit endpoint.
 * <p/>
 *
 * @author David Rutgos
 */
public class FindVisitResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("CallIn")
    private Date callIn;
    @SerializedName("CallOut")
    private Date callOut;
    @SerializedName("TaskCount")
    private int taskCount = 0;
    @SerializedName("VisitExceptionCount")
    private int visitExceptionCount = 0;
    @SerializedName("PatientFirstName")
    private String patientFirstName;
    @SerializedName("PatientLastName")
    private String patientLastName;
    @SerializedName("PatientPhone")
    private String patientPhone;
    @SerializedName("PatientID")
    private String patientId;


    @SerializedName("PatientMedicaidID")
    private String medicaidId;

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
    @SerializedName("CallHours")                // This is "actual" hours
    private String callHours;
    @SerializedName("AdjustedHours")
    private String adjustedHours;
    @SerializedName("Visit")
    private VisitExt visitExt;
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
    @SerializedName("PatientCoordinatorId")
    private String patientCoordinatorId;    // is email address
    @SerializedName("PatientCoordinatorFirstName")
    private String patientCoordinatorFirstName;
    @SerializedName("PatientCoordinatorLastName")
    private String patientCoordinatorLastName;
    @SerializedName("PatientCoordinatorPhone")
    private String patientCoordinatorPhone;
    @SerializedName("StaffCoordinatorId")
    private String staffCoordinatorId;    // is email address
    @SerializedName("StaffCoordinatorFirstName")
    private String staffCoordinatorFirstName;
    @SerializedName("StaffCoordinatorLastName")
    private String staffCoordinatorLastName;
    @SerializedName("StaffCoordinatorPhone")
    private String staffCoordinatorPhone;

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

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
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

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
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

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
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

    public String getCallHours() {
        return callHours;
    }

    public void setCallHours(String callHours) {
        this.callHours = callHours;
    }

    public String getAdjustedHours() {
        return adjustedHours;
    }

    public void setAdjustedHours(String adjustedHours) {
        this.adjustedHours = adjustedHours;
    }

    public VisitExt getVisitExt() {
        return visitExt;
    }

    public void setVisitExt(VisitExt visitExt) {
        this.visitExt = visitExt;
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
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
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

    public int getVisitExceptionCount() {
        return visitExceptionCount;
    }

    public void setVisitExceptionCount(int visitExceptionCount) {
        this.visitExceptionCount = visitExceptionCount;
    }

    public String getPatientCoordinatorId() {
        return patientCoordinatorId;
    }

    public void setPatientCoordinatorId(String patientCoordinatorId) {
        this.patientCoordinatorId = patientCoordinatorId;
    }

    public String getPatientCoordinatorFirstName() {
        return patientCoordinatorFirstName;
    }

    public void setPatientCoordinatorFirstName(String patientCoordinatorFirstName) {
        this.patientCoordinatorFirstName = patientCoordinatorFirstName;
    }

    public String getPatientCoordinatorLastName() {
        return patientCoordinatorLastName;
    }

    public void setPatientCoordinatorLastName(String patientCoordinatorLastName) {
        this.patientCoordinatorLastName = patientCoordinatorLastName;
    }

    public String getPatientCoordinatorPhone() {
        return patientCoordinatorPhone;
    }

    public void setPatientCoordinatorPhone(String patientCoordinatorPhone) {
        this.patientCoordinatorPhone = patientCoordinatorPhone;
    }

    public String getStaffCoordinatorId() {
        return staffCoordinatorId;
    }

    public void setStaffCoordinatorId(String staffCoordinatorId) {
        this.staffCoordinatorId = staffCoordinatorId;
    }

    public String getStaffCoordinatorFirstName() {
        return staffCoordinatorFirstName;
    }

    public void setStaffCoordinatorFirstName(String staffCoordinatorFirstName) {
        this.staffCoordinatorFirstName = staffCoordinatorFirstName;
    }

    public String getStaffCoordinatorLastName() {
        return staffCoordinatorLastName;
    }

    public void setStaffCoordinatorLastName(String staffCoordinatorLastName) {
        this.staffCoordinatorLastName = staffCoordinatorLastName;
    }

    public String getStaffCoordinatorPhone() {
        return staffCoordinatorPhone;
    }


	public void setStaffCoordinatorPhone(String staffCoordinatorPhone) {
		this.staffCoordinatorPhone = staffCoordinatorPhone;
	}

    public String getMedicaidId() {
        return medicaidId;
    }

    public void setMedicaidId(String medicaidId) {
        this.medicaidId = medicaidId;
    }


}
