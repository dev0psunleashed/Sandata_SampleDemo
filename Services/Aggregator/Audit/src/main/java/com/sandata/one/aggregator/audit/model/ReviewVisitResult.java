package com.sandata.one.aggregator.audit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Visit;

import java.math.BigDecimal;
import java.util.Date;

public class ReviewVisitResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityName")
    private String businessEntityName;

    @SerializedName("PayerID")
    private String payerId;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("ContractID")
    private String contractId;

    @SerializedName("ContractDescription")
    private String contractDesc;

    @SerializedName("ServiceName")
    private String serviceName;

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

    @SerializedName("VisitLocation")
    private String visitLocation;

    @SerializedName("VisitTimeZone")
    private String visitTimeZone;

    @SerializedName("StaffSSN")
    private String staffSsn;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffMiddleName")
    private String staffMiddleName;

    @SerializedName("StaffContactPhoneNumber")
    private String staffContactPhoneNumber;

    @SerializedName("VisitStatus")
    private String visitStatus;

    @SerializedName("CallInType")
    private String callInType;

    @SerializedName("CallOutType")
    private String callOutType;

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

    @SerializedName("ScheduledStartDate")
    private Date scheduledStartDate;

    @SerializedName("ScheduledEndDate")
    private Date scheduledEndDate;

    @SerializedName("ScheduledHours")
    private BigDecimal scheduledHours;

    @SerializedName("TaskCount")
    private int taskCount = 0;

    @SerializedName("VisitExceptionCount")
    private int visitExceptionCount = 0;

    @SerializedName("VisitSource")
    private String visitSource = "Sandata";

    @SerializedName("MemberVerified")
    private Boolean memberVerified = false;

    @SerializedName("ClaimsVerificationStatus")
    private Boolean claimsVerificationStatus = false;

    @SerializedName("Visit")
    private Visit visit;

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractDesc() {
        return contractDesc;
    }

    public void setContractDesc(String contractDesc) {
        this.contractDesc = contractDesc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

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

    public String getPrimaryPatientPhoneNumber() {
        return primaryPatientPhoneNumber;
    }

    public void setPrimaryPatientPhoneNumber(String primaryPatientPhoneNumber) {
        this.primaryPatientPhoneNumber = primaryPatientPhoneNumber;
    }

    public String getVisitLocation() {
        return visitLocation;
    }

    public void setVisitLocation(String visitLocation) {
        this.visitLocation = visitLocation;
    }

    public String getStaffSsn() {
        return staffSsn;
    }

    public void setStaffSsn(String staffSsn) {
        this.staffSsn = staffSsn;
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

    public String getStaffContactPhoneNumber() {
        return staffContactPhoneNumber;
    }

    public void setStaffContactPhoneNumber(String staffContactPhoneNumber) {
        this.staffContactPhoneNumber = staffContactPhoneNumber;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public String getCallInType() {
        return callInType;
    }

    public void setCallInType(String callInType) {
        this.callInType = callInType;
    }

    public String getCallOutType() {
        return callOutType;
    }

    public void setCallOutType(String callOutType) {
        this.callOutType = callOutType;
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

    public Date getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(Date scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public Date getScheduledEndDate() {
        return scheduledEndDate;
    }

    public void setScheduledEndDate(Date scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    public BigDecimal getScheduledHours() {
        return scheduledHours;
    }

    public void setScheduledHours(BigDecimal scheduledHours) {
        this.scheduledHours = scheduledHours;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getVisitExceptionCount() {
        return visitExceptionCount;
    }

    public void setVisitExceptionCount(int visitExceptionCount) {
        this.visitExceptionCount = visitExceptionCount;
    }

    public String getVisitSource() {
        return visitSource;
    }

    public void setVisitSource(String visitSource) {
        this.visitSource = visitSource;
    }

    public Boolean getMemberVerified() {
        return memberVerified;
    }

    public void setMemberVerified(Boolean memberVerified) {
        this.memberVerified = memberVerified;
    }

    public Boolean getClaimsVerificationStatus() {
        return claimsVerificationStatus;
    }

    public void setClaimsVerificationStatus(Boolean claimsVerificationStatus) {
        this.claimsVerificationStatus = claimsVerificationStatus;
    }

    public String getVisitTimeZone() {
        return visitTimeZone;
    }

    public void setVisitTimeZone(String visitTimeZone) {
        this.visitTimeZone = visitTimeZone;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getStaffMiddleName() {
        return staffMiddleName;
    }

    public void setStaffMiddleName(String staffMiddleName) {
        this.staffMiddleName = staffMiddleName;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
