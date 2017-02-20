package com.sandata.lab.data.model.dl.model.custom;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

public class AccountsReceivableAutoPostDetail extends BaseObject{

    private static final long serialVersionUID = 1L;

    @SerializedName("PatientFirstName")
    String patientFirstName;

    @SerializedName("PatientLastName")
    String patientLastName;

    @SerializedName("InvoiceNumber")
    String invoiceNumber;

    @SerializedName("BilledAmount")
    BigDecimal billedAmount;

    @SerializedName("PaidAmount")
    BigDecimal paidAmount;

    @SerializedName("AdjustedAmount")
    BigDecimal adjustedAmount;

    @SerializedName("RemaingAmount")
    BigDecimal remainingAmount;

    @SerializedName("FirstDateOfService")
    Date firstDateOfService;

    @SerializedName("LastDateOfService")
    Date lastDateOfService;

    @SerializedName("IsSandataInvoice")
    Boolean isSandataInvoice;

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(BigDecimal billedAmount) {
        this.billedAmount = billedAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getAdjustedAmount() {
        return adjustedAmount;
    }

    public void setAdjustedAmount(BigDecimal adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Date getFirstDateOfService() {
        return firstDateOfService;
    }

    public void setFirstDateOfService(Date firstDateOfService) {
        this.firstDateOfService = firstDateOfService;
    }

    public Date getLastDateOfService() {
        return lastDateOfService;
    }

    public void setLastDateOfService(Date lastDateOfService) {
        this.lastDateOfService = lastDateOfService;
    }

    public Boolean getSandataInvoice() {
        return isSandataInvoice;
    }

    public void setSandataInvoice(Boolean sandataInvoice) {
        isSandataInvoice = sandataInvoice;
    }


}
