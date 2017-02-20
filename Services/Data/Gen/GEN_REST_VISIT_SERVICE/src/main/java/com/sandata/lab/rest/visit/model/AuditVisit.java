package com.sandata.lab.rest.visit.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sandata.lab.data.model.base.BaseObject;

public class AuditVisit extends BaseObject {

    private static final long serialVersionUID = -2482207912961817278L;

    private String businessEntityID;

    private long visitSk;

    private String changeReasonCode;

    private String changeReasonMemo;

    private String patientId;

    private String patientName;

    private String staffId;

    private String staffName;

    private String service;

    private Date scheduleIn;

    private Date scheduleOut;

    private String scheduledHours;

    private Date callIn;

    private Date callOut;

    private String callHours;

    private Date adjustIn;

    private Date adjustOut;

    private String adjustHours;

    private BigDecimal payHours;

    private BigDecimal billHours;

    private String status;

    private Boolean verifiedByScheduleIndicator;

    private Boolean payByScheduleIndicator;

    private BigDecimal overtimeAbsenceHours;

    private Boolean approvedIndicator;

    private List<AuditVisitException> visitExceptions;

    private List<AuditVisitNote> visitNotes;

    private List<AuditVisitTask> visitTasks;

    /**
     * @return the businessEntityID
     */
    public String getBusinessEntityID() {
        return businessEntityID;
    }

    /**
     * @param businessEntityID the businessEntityID to set
     */
    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    /**
     * @return the visitSk
     */
    public long getVisitSk() {
        return visitSk;
    }

    /**
     * @param visitSk the visitSk to set
     */
    public void setVisitSk(long visitSk) {
        this.visitSk = visitSk;
    }

    /**
     * @return the changeReasonCode
     */
    public String getChangeReasonCode() {
        return changeReasonCode;
    }

    /**
     * @param changeReasonCode the changeReasonCode to set
     */
    public void setChangeReasonCode(String changeReasonCode) {
        this.changeReasonCode = changeReasonCode;
    }

    /**
     * @return the changeReasonMemo
     */
    public String getChangeReasonMemo() {
        return changeReasonMemo;
    }

    /**
     * @param changeReasonMemo the changeReasonMemo to set
     */
    public void setChangeReasonMemo(String changeReasonMemo) {
        this.changeReasonMemo = changeReasonMemo;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the staffName
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param staffName the staffName to set
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the scheduleIn
     */
    public Date getScheduleIn() {
        return scheduleIn;
    }

    /**
     * @param scheduleIn the scheduleIn to set
     */
    public void setScheduleIn(Date scheduleIn) {
        this.scheduleIn = scheduleIn;
    }

    /**
     * @return the scheduleOut
     */
    public Date getScheduleOut() {
        return scheduleOut;
    }

    /**
     * @param scheduleOut the scheduleOut to set
     */
    public void setScheduleOut(Date scheduleOut) {
        this.scheduleOut = scheduleOut;
    }

    /**
     * @return the scheduledHours
     */
    public String getScheduledHours() {
        return scheduledHours;
    }

    /**
     * @param scheduledHours the scheduledHours to set
     */
    public void setScheduledHours(String scheduledHours) {
        this.scheduledHours = scheduledHours;
    }

    /**
     * @return the callIn
     */
    public Date getCallIn() {
        return callIn;
    }

    /**
     * @param callIn the callIn to set
     */
    public void setCallIn(Date callIn) {
        this.callIn = callIn;
    }

    /**
     * @return the callOut
     */
    public Date getCallOut() {
        return callOut;
    }

    /**
     * @param callOut the callOut to set
     */
    public void setCallOut(Date callOut) {
        this.callOut = callOut;
    }

    /**
     * @return the callHours
     */
    public String getCallHours() {
        return callHours;
    }

    /**
     * @param callHours the callHours to set
     */
    public void setCallHours(String callHours) {
        this.callHours = callHours;
    }

    /**
     * @return the adjustIn
     */
    public Date getAdjustIn() {
        return adjustIn;
    }

    /**
     * @param adjustIn the adjustIn to set
     */
    public void setAdjustIn(Date adjustIn) {
        this.adjustIn = adjustIn;
    }

    /**
     * @return the adjustOut
     */
    public Date getAdjustOut() {
        return adjustOut;
    }

    /**
     * @param adjustOut the adjustOut to set
     */
    public void setAdjustOut(Date adjustOut) {
        this.adjustOut = adjustOut;
    }

    /**
     * @return the adjustHours
     */
    public String getAdjustHours() {
        return adjustHours;
    }

    /**
     * @param adjustHours the adjustHours to set
     */
    public void setAdjustHours(String adjustHours) {
        this.adjustHours = adjustHours;
    }

    /**
     * @return the payHours
     */
    public BigDecimal getPayHours() {
        return payHours;
    }

    /**
     * @param payHours the payHours to set
     */
    public void setPayHours(BigDecimal payHours) {
        this.payHours = payHours;
    }

    /**
     * @return the billHours
     */
    public BigDecimal getBillHours() {
        return billHours;
    }

    /**
     * @param billHours the billHours to set
     */
    public void setBillHours(BigDecimal billHours) {
        this.billHours = billHours;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the verifiedByScheduleIndicator
     */
    public Boolean getVerifiedByScheduleIndicator() {
        return verifiedByScheduleIndicator;
    }

    /**
     * @param verifiedByScheduleIndicator the verifiedByScheduleIndicator to set
     */
    public void setVerifiedByScheduleIndicator(Boolean verifiedByScheduleIndicator) {
        this.verifiedByScheduleIndicator = verifiedByScheduleIndicator;
    }

    /**
     * @return the payByScheduleIndicator
     */
    public Boolean getPayByScheduleIndicator() {
        return payByScheduleIndicator;
    }

    /**
     * @param payByScheduleIndicator the payByScheduleIndicator to set
     */
    public void setPayByScheduleIndicator(Boolean payByScheduleIndicator) {
        this.payByScheduleIndicator = payByScheduleIndicator;
    }

    /**
     * @return the overtimeAbsenceHours
     */
    public BigDecimal getOvertimeAbsenceHours() {
        return overtimeAbsenceHours;
    }

    /**
     * @param overtimeAbsenceHours the overtimeAbsenceHours to set
     */
    public void setOvertimeAbsenceHours(BigDecimal overtimeAbsenceHours) {
        this.overtimeAbsenceHours = overtimeAbsenceHours;
    }

    /**
     * @return the approvedIndicator
     */
    public Boolean getApprovedIndicator() {
        return approvedIndicator;
    }

    /**
     * @param approvedIndicator the approvedIndicator to set
     */
    public void setApprovedIndicator(Boolean approvedIndicator) {
        this.approvedIndicator = approvedIndicator;
    }

    /**
     * @return the visitExceptions
     */
    public List<AuditVisitException> getVisitExceptions() {
        if (visitExceptions == null) {
            visitExceptions = new ArrayList<AuditVisitException>();
        }

        return visitExceptions;
    }

    /**
     * @return the visitNotes
     */
    public List<AuditVisitNote> getVisitNotes() {
        if (visitNotes == null) {
            visitNotes = new ArrayList<AuditVisitNote>();
        }

        return visitNotes;
    }

    /**
     * @return the visitTasks
     */
    public List<AuditVisitTask> getVisitTasks() {
        if (visitTasks == null) {
            visitTasks = new ArrayList<AuditVisitTask>();
        }

        return visitTasks;
    }

}
