package com.sandata.lab.data.model.dl.model.custom;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

@OracleMetadata(
        packageName = "PKG_AUDIT",
        insertMethod = "N/A",
        updateMethod = "N/A",
        deleteMethod = "N/A",
        getMethod = "N/A",
        findMethod = "N/A",
        jpubClass = "com.sandata.lab.data.model.jpub.model.AuditVisitHistT",
        primaryKeys = {})
public class AuditVisitHistory extends BaseObject {

    private static final long serialVersionUID = 6721823963602508718L;

    @Mapping(getter = "getAuditHost", setter = "setAuditHost", type = "String", index = 0)
    protected String auditHost;

    @Mapping(getter = "getUserGuid", setter = "setUserGuid", type = "String", index = 1)
    protected String userGuid;

    @Mapping(getter = "getReasonCode", setter = "setReasonCode", type = "String", index = 2)
    protected String reasonCode;

    @Mapping(getter = "getNotes", setter = "setNotes", type = "String", index = 3)
    protected String notes;

    @Mapping(getter = "getPtIdOld", setter = "setPtIdOld", type = "String", index = 4)
    protected String patientIdOld;

    @Mapping(getter = "getPtIdNew", setter = "setPtIdNew", type = "String", index = 5)
    protected String patientIdNew;

    @Mapping(getter = "getPtNameOld", setter = "setPtNameOld", type = "String", index = 6)
    protected String patientNameOld;

    @Mapping(getter = "getPtNameNew", setter = "setPtNameNew", type = "String", index = 7)
    protected String patientNameNew;

    @Mapping(getter = "getStaffIdOld", setter = "setStaffIdOld", type = "String", index = 8)
    protected String staffIdOld;

    @Mapping(getter = "getStaffIdNew", setter = "setStaffIdNew", type = "String", index = 9)
    protected String staffIdNew;

    @Mapping(getter = "getStaffNameOld", setter = "setStaffNameOld", type = "String", index = 10)
    protected String staffNameOld;

    @Mapping(getter = "getStaffNameNew", setter = "setStaffNameNew", type = "String", index = 11)
    protected String staffNameNew;

    @Mapping(getter = "getServiceOld", setter = "setServiceOld", type = "String", index = 12)
    protected String serviceOld;

    @Mapping(getter = "getServiceNew", setter = "setServiceNew", type = "String", index = 13)
    protected String serviceNew;

    @Mapping(getter = "getSchdInOld", setter = "setSchdInOld", type = "String", index = 14)
    protected String scheduleInOld;

    @Mapping(getter = "getSchdInNew", setter = "setSchdInNew", type = "String", index = 15)
    protected String ScheduleInNew;

    @Mapping(getter = "getSchdOutOld", setter = "setSchdOutOld", type = "String", index = 16)
    protected String scheduleOutOld;

    @Mapping(getter = "getSchdOutNew", setter = "setSchdOutNew", type = "String", index = 17)
    protected String ScheduleOutNew;

    @Mapping(getter = "getSchdHrsOld", setter = "setSchdHrsOld", type = "String", index = 18)
    protected String scheduleHoursOld;

    @Mapping(getter = "getSchdHrsNew", setter = "setSchdHrsNew", type = "String", index = 19)
    protected String ScheduleHoursNew;

    @Mapping(getter = "getCallInOld", setter = "setCallInOld", type = "String", index = 20)
    protected String callInOld;

    @Mapping(getter = "getCallInNew", setter = "setCallInNew", type = "String", index = 21)
    protected String callInNew;

    @Mapping(getter = "getCallOutOld", setter = "setCallOutOld", type = "String", index = 22)
    protected String callOutOld;

    @Mapping(getter = "getCallOutNew", setter = "setCallOutNew", type = "String", index = 23)
    protected String callOutNew;

    @Mapping(getter = "getCallHrsOld", setter = "setCallHrsOld", type = "String", index = 24)
    protected String callHoursOld;

    @Mapping(getter = "getCallHrsNew", setter = "setCallHrsNew", type = "String", index = 25)
    protected String callHoursNew;

    @Mapping(getter = "getAdjInOld", setter = "setAdjInOld", type = "String", index = 26)
    protected String adjustInOld;

    @Mapping(getter = "getAdjInNew", setter = "setAdjInNew", type = "String", index = 27)
    protected String adjustInNew;

    @Mapping(getter = "getAdjOutOld", setter = "setAdjOutOld", type = "String", index = 28)
    protected String adjustOutOld;

    @Mapping(getter = "getAdjOutNew", setter = "setAdjOutNew", type = "String", index = 29)
    protected String adjustOutNew;

    @Mapping(getter = "getAdjHrsOld", setter = "setAdjHrsOld", type = "String", index = 30)
    protected String adjustHoursOld;

    @Mapping(getter = "getAdjHrsNew", setter = "setAdjHrsNew", type = "String", index = 31)
    protected String adjustHoursNew;

    @Mapping(getter = "getPayHrsOld", setter = "setPayHrsOld", type = "String", index = 32)
    protected String payHoursOld;

    @Mapping(getter = "getPayHrsNew", setter = "setPayHrsNew", type = "String", index = 33)
    protected String payHoursNew;

    @Mapping(getter = "getBillHrsOld", setter = "setBillHrsOld", type = "String", index = 35)
    protected String billHoursOld;

    @Mapping(getter = "getBillHrsNew", setter = "setBillHrsNew", type = "String", index = 36)
    protected String billHoursNew;

    @Mapping(getter = "getStatusOld", setter = "setStatusOld", type = "String", index = 37)
    protected String statusOld;

    @Mapping(getter = "getStatusNew", setter = "setStatusNew", type = "String", index = 38)
    protected String statusNew;

    @Mapping(getter = "getVerifyBySchdOld", setter = "setVerifyBySchdOld", type = "String", index = 39)
    protected String verifyByScheduleOld;

    @Mapping(getter = "getVerifyBySchdNew", setter = "setVerifyBySchdNew", type = "String", index = 40)
    protected String verifyByScheduleNew;

    @Mapping(getter = "getPayBySchdOld", setter = "setPayBySchdOld", type = "String", index = 41)
    protected String payByScheduleOld;

    @Mapping(getter = "getPayBySchdNew", setter = "setPayBySchdNew", type = "String", index = 42)
    protected String payByScheduleNew;

    @Mapping(getter = "getOtAbsHoursOld", setter = "setOtAbsHoursOld", type = "String", index = 43)
    protected String otAbsHoursOld;

    @Mapping(getter = "getOtAbsHoursNew", setter = "setOtAbsHoursNew", type = "String", index = 44)
    protected String otAbsHoursNew;

    @Mapping(getter = "getApprvOld", setter = "setApprvOld", type = "String", index = 45)
    protected String approvedOld;

    @Mapping(getter = "getApprvNew", setter = "setApprvNew", type = "String", index = 46)
    protected String approvedNew;

    @Mapping(getter = "getVisitExceptionsOld", setter = "setVisitExceptionsOld", type = "String", index = 47)
    protected String visitExceptionsOld;

    @Mapping(getter = "getVisitExceptionsNew", setter = "setVisitExceptionsNew", type = "String", index = 48)
    protected String visitExceptionsNew;

    @Mapping(getter = "getVisitNotesOld", setter = "setVisitNotesOld", type = "String", index = 49)
    protected String visitNotesOld;

    @Mapping(getter = "getVisitNotesNew", setter = "setVisitNotesNew", type = "String", index = 50)
    protected String visitNotesNew;

    @Mapping(getter = "getVisitTasksOld", setter = "setVisitTasksOld", type = "String", index = 51)
    protected String visitTasksOld;

    @Mapping(getter = "getVisitTasksNew", setter = "setVisitTasksNew", type = "String", index = 52)
    protected String visitTasksNew;

    /**
     * @return the auditHost
     */
    public String getAuditHost() {
        return auditHost;
    }

    /**
     * @param auditHost the auditHost to set
     */
    public void setAuditHost(String auditHost) {
        this.auditHost = auditHost;
    }

    /**
     * @return the userGuid
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * @param userGuid the userGuid to set
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    /**
     * @return the reasonCode
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * @param reasonCode the reasonCode to set
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the patientIdOld
     */
    public String getPatientIdOld() {
        return patientIdOld;
    }

    /**
     * @param patientIdOld the patientIdOld to set
     */
    public void setPatientIdOld(String patientIdOld) {
        this.patientIdOld = patientIdOld;
    }

    /**
     * @return the patientIdNew
     */
    public String getPatientIdNew() {
        return patientIdNew;
    }

    /**
     * @param patientIdNew the patientIdNew to set
     */
    public void setPatientIdNew(String patientIdNew) {
        this.patientIdNew = patientIdNew;
    }

    /**
     * @return the patientNameOld
     */
    public String getPatientNameOld() {
        return patientNameOld;
    }

    /**
     * @param patientNameOld the patientNameOld to set
     */
    public void setPatientNameOld(String patientNameOld) {
        this.patientNameOld = patientNameOld;
    }

    /**
     * @return the patientNameNew
     */
    public String getPatientNameNew() {
        return patientNameNew;
    }

    /**
     * @param patientNameNew the patientNameNew to set
     */
    public void setPatientNameNew(String patientNameNew) {
        this.patientNameNew = patientNameNew;
    }

    /**
     * @return the staffIdOld
     */
    public String getStaffIdOld() {
        return staffIdOld;
    }

    /**
     * @param staffIdOld the staffIdOld to set
     */
    public void setStaffIdOld(String staffIdOld) {
        this.staffIdOld = staffIdOld;
    }

    /**
     * @return the staffIdNew
     */
    public String getStaffIdNew() {
        return staffIdNew;
    }

    /**
     * @param staffIdNew the staffIdNew to set
     */
    public void setStaffIdNew(String staffIdNew) {
        this.staffIdNew = staffIdNew;
    }

    /**
     * @return the staffNameOld
     */
    public String getStaffNameOld() {
        return staffNameOld;
    }

    /**
     * @param staffNameOld the staffNameOld to set
     */
    public void setStaffNameOld(String staffNameOld) {
        this.staffNameOld = staffNameOld;
    }

    /**
     * @return the staffNameNew
     */
    public String getStaffNameNew() {
        return staffNameNew;
    }

    /**
     * @param staffNameNew the staffNameNew to set
     */
    public void setStaffNameNew(String staffNameNew) {
        this.staffNameNew = staffNameNew;
    }

    /**
     * @return the serviceOld
     */
    public String getServiceOld() {
        return serviceOld;
    }

    /**
     * @param serviceOld the serviceOld to set
     */
    public void setServiceOld(String serviceOld) {
        this.serviceOld = serviceOld;
    }

    /**
     * @return the serviceNew
     */
    public String getServiceNew() {
        return serviceNew;
    }

    /**
     * @param serviceNew the serviceNew to set
     */
    public void setServiceNew(String serviceNew) {
        this.serviceNew = serviceNew;
    }

    /**
     * @return the scheduleInOld
     */
    public String getScheduleInOld() {
        return scheduleInOld;
    }

    /**
     * @param scheduleInOld the scheduleInOld to set
     */
    public void setScheduleInOld(String scheduleInOld) {
        this.scheduleInOld = scheduleInOld;
    }

    /**
     * @return the scheduleInNew
     */
    public String getScheduleInNew() {
        return ScheduleInNew;
    }

    /**
     * @param scheduleInNew the scheduleInNew to set
     */
    public void setScheduleInNew(String scheduleInNew) {
        ScheduleInNew = scheduleInNew;
    }

    /**
     * @return the scheduleOutOld
     */
    public String getScheduleOutOld() {
        return scheduleOutOld;
    }

    /**
     * @param scheduleOutOld the scheduleOutOld to set
     */
    public void setScheduleOutOld(String scheduleOutOld) {
        this.scheduleOutOld = scheduleOutOld;
    }

    /**
     * @return the scheduleOutNew
     */
    public String getScheduleOutNew() {
        return ScheduleOutNew;
    }

    /**
     * @param scheduleOutNew the scheduleOutNew to set
     */
    public void setScheduleOutNew(String scheduleOutNew) {
        ScheduleOutNew = scheduleOutNew;
    }

    /**
     * @return the scheduleHoursOld
     */
    public String getScheduleHoursOld() {
        return scheduleHoursOld;
    }

    /**
     * @param scheduleHoursOld the scheduleHoursOld to set
     */
    public void setScheduleHoursOld(String scheduleHoursOld) {
        this.scheduleHoursOld = scheduleHoursOld;
    }

    /**
     * @return the scheduleHoursNew
     */
    public String getScheduleHoursNew() {
        return ScheduleHoursNew;
    }

    /**
     * @param scheduleHoursNew the scheduleHoursNew to set
     */
    public void setScheduleHoursNew(String scheduleHoursNew) {
        ScheduleHoursNew = scheduleHoursNew;
    }

    /**
     * @return the callInOld
     */
    public String getCallInOld() {
        return callInOld;
    }

    /**
     * @param callInOld the callInOld to set
     */
    public void setCallInOld(String callInOld) {
        this.callInOld = callInOld;
    }

    /**
     * @return the callInNew
     */
    public String getCallInNew() {
        return callInNew;
    }

    /**
     * @param callInNew the callInNew to set
     */
    public void setCallInNew(String callInNew) {
        this.callInNew = callInNew;
    }

    /**
     * @return the callOutOld
     */
    public String getCallOutOld() {
        return callOutOld;
    }

    /**
     * @param callOutOld the callOutOld to set
     */
    public void setCallOutOld(String callOutOld) {
        this.callOutOld = callOutOld;
    }

    /**
     * @return the callOutNew
     */
    public String getCallOutNew() {
        return callOutNew;
    }

    /**
     * @param callOutNew the callOutNew to set
     */
    public void setCallOutNew(String callOutNew) {
        this.callOutNew = callOutNew;
    }

    /**
     * @return the callHoursOld
     */
    public String getCallHoursOld() {
        return callHoursOld;
    }

    /**
     * @param callHoursOld the callHoursOld to set
     */
    public void setCallHoursOld(String callHoursOld) {
        this.callHoursOld = callHoursOld;
    }

    /**
     * @return the callHoursNew
     */
    public String getCallHoursNew() {
        return callHoursNew;
    }

    /**
     * @param callHoursNew the callHoursNew to set
     */
    public void setCallHoursNew(String callHoursNew) {
        this.callHoursNew = callHoursNew;
    }

    /**
     * @return the adjustInOld
     */
    public String getAdjustInOld() {
        return adjustInOld;
    }

    /**
     * @param adjustInOld the adjustInOld to set
     */
    public void setAdjustInOld(String adjustInOld) {
        this.adjustInOld = adjustInOld;
    }

    /**
     * @return the adjustInNew
     */
    public String getAdjustInNew() {
        return adjustInNew;
    }

    /**
     * @param adjustInNew the adjustInNew to set
     */
    public void setAdjustInNew(String adjustInNew) {
        this.adjustInNew = adjustInNew;
    }

    /**
     * @return the adjustOutOld
     */
    public String getAdjustOutOld() {
        return adjustOutOld;
    }

    /**
     * @param adjustOutOld the adjustOutOld to set
     */
    public void setAdjustOutOld(String adjustOutOld) {
        this.adjustOutOld = adjustOutOld;
    }

    /**
     * @return the adjustOutNew
     */
    public String getAdjustOutNew() {
        return adjustOutNew;
    }

    /**
     * @param adjustOutNew the adjustOutNew to set
     */
    public void setAdjustOutNew(String adjustOutNew) {
        this.adjustOutNew = adjustOutNew;
    }

    /**
     * @return the adjustHoursOld
     */
    public String getAdjustHoursOld() {
        return adjustHoursOld;
    }

    /**
     * @param adjustHoursOld the adjustHoursOld to set
     */
    public void setAdjustHoursOld(String adjustHoursOld) {
        this.adjustHoursOld = adjustHoursOld;
    }

    /**
     * @return the adjustHoursNew
     */
    public String getAdjustHoursNew() {
        return adjustHoursNew;
    }

    /**
     * @param adjustHoursNew the adjustHoursNew to set
     */
    public void setAdjustHoursNew(String adjustHoursNew) {
        this.adjustHoursNew = adjustHoursNew;
    }

    /**
     * @return the payHoursOld
     */
    public String getPayHoursOld() {
        return payHoursOld;
    }

    /**
     * @param payHoursOld the payHoursOld to set
     */
    public void setPayHoursOld(String payHoursOld) {
        this.payHoursOld = payHoursOld;
    }

    /**
     * @return the payHoursNew
     */
    public String getPayHoursNew() {
        return payHoursNew;
    }

    /**
     * @param payHoursNew the payHoursNew to set
     */
    public void setPayHoursNew(String payHoursNew) {
        this.payHoursNew = payHoursNew;
    }

    /**
     * @return the billHoursOld
     */
    public String getBillHoursOld() {
        return billHoursOld;
    }

    /**
     * @param billHoursOld the billHoursOld to set
     */
    public void setBillHoursOld(String billHoursOld) {
        this.billHoursOld = billHoursOld;
    }

    /**
     * @return the billHoursNew
     */
    public String getBillHoursNew() {
        return billHoursNew;
    }

    /**
     * @param billHoursNew the billHoursNew to set
     */
    public void setBillHoursNew(String billHoursNew) {
        this.billHoursNew = billHoursNew;
    }

    /**
     * @return the statusOld
     */
    public String getStatusOld() {
        return statusOld;
    }

    /**
     * @param statusOld the statusOld to set
     */
    public void setStatusOld(String statusOld) {
        this.statusOld = statusOld;
    }

    /**
     * @return the statusNew
     */
    public String getStatusNew() {
        return statusNew;
    }

    /**
     * @param statusNew the statusNew to set
     */
    public void setStatusNew(String statusNew) {
        this.statusNew = statusNew;
    }

    /**
     * @return the verifyByScheduleOld
     */
    public String getVerifyByScheduleOld() {
        return verifyByScheduleOld;
    }

    /**
     * @param verifyByScheduleOld the verifyByScheduleOld to set
     */
    public void setVerifyByScheduleOld(String verifyByScheduleOld) {
        this.verifyByScheduleOld = verifyByScheduleOld;
    }

    /**
     * @return the verifyByScheduleNew
     */
    public String getVerifyByScheduleNew() {
        return verifyByScheduleNew;
    }

    /**
     * @param verifyByScheduleNew the verifyByScheduleNew to set
     */
    public void setVerifyByScheduleNew(String verifyByScheduleNew) {
        this.verifyByScheduleNew = verifyByScheduleNew;
    }

    /**
     * @return the payByScheduleOld
     */
    public String getPayByScheduleOld() {
        return payByScheduleOld;
    }

    /**
     * @param payByScheduleOld the payByScheduleOld to set
     */
    public void setPayByScheduleOld(String payByScheduleOld) {
        this.payByScheduleOld = payByScheduleOld;
    }

    /**
     * @return the payByScheduleNew
     */
    public String getPayByScheduleNew() {
        return payByScheduleNew;
    }

    /**
     * @param payByScheduleNew the payByScheduleNew to set
     */
    public void setPayByScheduleNew(String payByScheduleNew) {
        this.payByScheduleNew = payByScheduleNew;
    }

    /**
     * @return the otAbsHoursOld
     */
    public String getOtAbsHoursOld() {
        return otAbsHoursOld;
    }

    /**
     * @param otAbsHoursOld the otAbsHoursOld to set
     */
    public void setOtAbsHoursOld(String otAbsHoursOld) {
        this.otAbsHoursOld = otAbsHoursOld;
    }

    /**
     * @return the otAbsHoursNew
     */
    public String getOtAbsHoursNew() {
        return otAbsHoursNew;
    }

    /**
     * @param otAbsHoursNew the otAbsHoursNew to set
     */
    public void setOtAbsHoursNew(String otAbsHoursNew) {
        this.otAbsHoursNew = otAbsHoursNew;
    }

    /**
     * @return the approvedOld
     */
    public String getApprovedOld() {
        return approvedOld;
    }

    /**
     * @param approvedOld the approvedOld to set
     */
    public void setApprovedOld(String approvedOld) {
        this.approvedOld = approvedOld;
    }

    /**
     * @return the approvedNew
     */
    public String getApprovedNew() {
        return approvedNew;
    }

    /**
     * @param approvedNew the approvedNew to set
     */
    public void setApprovedNew(String approvedNew) {
        this.approvedNew = approvedNew;
    }

    /**
     * @return the visitExceptionsOld
     */
    public String getVisitExceptionsOld() {
        return visitExceptionsOld;
    }

    /**
     * @param visitExceptionsOld the visitExceptionsOld to set
     */
    public void setVisitExceptionsOld(String visitExceptionsOld) {
        this.visitExceptionsOld = visitExceptionsOld;
    }

    /**
     * @return the visitExceptionsNew
     */
    public String getVisitExceptionsNew() {
        return visitExceptionsNew;
    }

    /**
     * @param visitExceptionsNew the visitExceptionsNew to set
     */
    public void setVisitExceptionsNew(String visitExceptionsNew) {
        this.visitExceptionsNew = visitExceptionsNew;
    }

    /**
     * @return the visitNotesOld
     */
    public String getVisitNotesOld() {
        return visitNotesOld;
    }

    /**
     * @param visitNotesOld the visitNotesOld to set
     */
    public void setVisitNotesOld(String visitNotesOld) {
        this.visitNotesOld = visitNotesOld;
    }

    /**
     * @return the visitNotesNew
     */
    public String getVisitNotesNew() {
        return visitNotesNew;
    }

    /**
     * @param visitNotesNew the visitNotesNew to set
     */
    public void setVisitNotesNew(String visitNotesNew) {
        this.visitNotesNew = visitNotesNew;
    }

    /**
     * @return the visitTasksOld
     */
    public String getVisitTasksOld() {
        return visitTasksOld;
    }

    /**
     * @param visitTasksOld the visitTasksOld to set
     */
    public void setVisitTasksOld(String visitTasksOld) {
        this.visitTasksOld = visitTasksOld;
    }

    /**
     * @return the visitTasksNew
     */
    public String getVisitTasksNew() {
        return visitTasksNew;
    }

    /**
     * @param visitTasksNew the visitTasksNew to set
     */
    public void setVisitTasksNew(String visitTasksNew) {
        this.visitTasksNew = visitTasksNew;
    }

}
