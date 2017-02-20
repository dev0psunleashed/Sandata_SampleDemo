package com.sandata.lab.rest.payroll.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Defines the find payroll details result entity.
 * <p/>
 *
 * @author David Rutgos
 */
public class FindPayrollDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("VisitSK")
    private BigInteger visitSk;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("ServiceName")
    private String serviceName;

    @SerializedName("ServiceDate")
    private Date serviceDate;

    @SerializedName("Rate")
    private BigDecimal rate;

    @SerializedName("Hours")
    private BigDecimal hours;

    @SerializedName("TotalPay")
    private BigDecimal totalPay;

    @SerializedName("PayrollAmount")
    private BigDecimal payrollAmount;

    @SerializedName("RateTypeName")
    private String rateTypeName;

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

    public BigInteger getVisitSk() {
        return visitSk;
    }

    public void setVisitSk(BigInteger visitSk) {
        this.visitSk = visitSk;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
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

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
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
}
