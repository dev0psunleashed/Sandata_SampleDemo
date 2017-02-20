package com.sandata.lab.data.model.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Visit;

/**
 * Date: 12/4/15
 * Time: 1:26 AM
 */

public class StaffVisit extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("TotalPay")
    private long totalPay;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("Rate")
    private String rate;

    @SerializedName("Hours")
    private long hours;

    @SerializedName("TotalAdditionalPay")
    private long totalAdditionalPay;            // TODO: BA's are researching

    @SerializedName("TotalVisitPay")
    private long totalVisitPay;                 // TODO: Issue: PR_OUTPUT.PR_RATE is a String

    @SerializedName("StaffAdditionalPay")
    private long staffAdditionalPay;            // TODO: BA's are researching

    @SerializedName("Visit")
    private Visit visit;

    public long getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(long totalPay) {
        this.totalPay = totalPay;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public long getTotalAdditionalPay() {
        return totalAdditionalPay;
    }

    public void setTotalAdditionalPay(long totalAdditionalPay) {
        this.totalAdditionalPay = totalAdditionalPay;
    }

    public long getTotalVisitPay() {
        return totalVisitPay;
    }

    public void setTotalVisitPay(long totalVisitPay) {
        this.totalVisitPay = totalVisitPay;
    }

    public long getStaffAdditionalPay() {
        return staffAdditionalPay;
    }

    public void setStaffAdditionalPay(long staffAdditionalPay) {
        this.staffAdditionalPay = staffAdditionalPay;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
