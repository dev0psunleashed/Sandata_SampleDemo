package com.sandata.lab.data.model.dl.model.extended.ar;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.AccountsReceivable;

import java.math.BigDecimal;
import java.util.Date;

public class AccountsReceivableExt extends AccountsReceivable {


    @SerializedName("ServiceSubmissionStatusName")
    private String serviceSubmissionStatusName;

    @SerializedName("InvoiceStatusName")
    private String invoiceStatusName;

    @SerializedName("ServiceStatusName")
    private String serviceStatusName;

    @SerializedName("DenialCode")
    private String denialCode;

    @SerializedName("DateOfService")
    private Date dateOfService;

    @SerializedName("Balance")
    private BigDecimal balance;

    @SerializedName("Credits")
    private BigDecimal credits;

    @SerializedName("Debits")
    private BigDecimal debits;

    public String getServiceSubmissionStatusName() {
        return serviceSubmissionStatusName;
    }

    public void setServiceSubmissionStatusName(String serviceSubmissionStatusName) {
        this.serviceSubmissionStatusName = serviceSubmissionStatusName;
    }

    public String getServiceStatusName() {
        return serviceStatusName;
    }

    public void setServiceStatusName(String serviceStatusName) {
        this.serviceStatusName = serviceStatusName;
    }

    public String getDenialCode() {
        return denialCode;
    }

    public void setDenialCode(String denialCode) {
        this.denialCode = denialCode;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getInvoiceStatusName() {
        return invoiceStatusName;
    }

    public void setInvoiceStatusName(String invoiceStatusName) {
        this.invoiceStatusName = invoiceStatusName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    public BigDecimal getDebits() {
        return debits;
    }

    public void setDebits(BigDecimal debits) {
        this.debits = debits;
    }
}
