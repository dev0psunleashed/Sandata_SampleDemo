package com.sandata.lab.rest.payroll.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Visit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Date: 12/4/15
 * Time: 1:26 AM
 */

public class StaffVisit extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("Rate")
    private BigDecimal rate;

    @SerializedName("Hours")
    private BigDecimal hours;

    @SerializedName("TotalPay")
    private BigDecimal totalPay;

    @SerializedName("PayrollAmount")
    private BigDecimal payrollAmount;

    @SerializedName("PayrollCode")
    private String payrollCode;

    @SerializedName("TimesheetDate")
    private Date timesheetDate;

    @SerializedName("Service")
    private String service;

    @SerializedName("RateTypeName")
    private String rateTypeName;

    @SerializedName("Visit")
    private Visit visit;

    public void initTotalPay() {

        if (hours != null && rate != null) {
            totalPay = hours.multiply(rate).setScale(2, RoundingMode.CEILING);
        }
        else if (payrollAmount != null) {
            totalPay = payrollAmount;
        }
        else {
            totalPay = BigDecimal.ZERO;
        }
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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public String getPayrollCode() {

        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }

    public Date getTimesheetDate() {
        return timesheetDate;
    }

    public void setTimesheetDate(Date timesheetDate) {
        this.timesheetDate = timesheetDate;
    }

    public BigDecimal getPayrollAmount() {
        return payrollAmount;
    }

    public void setPayrollAmount(BigDecimal payrollAmount) {
        this.payrollAmount = payrollAmount;
    }

    public String getRateTypeName() {
        return rateTypeName;
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
