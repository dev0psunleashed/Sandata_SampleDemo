package com.sandata.lab.data.model.dl.model.find;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * For mapping search result set of ar/find endpoint
 * @author thanhxle
 *
 */
public class AccountsReceivableFindResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("AccountsReceivableSK")
    private BigDecimal accountsReceivableSK;

    @SerializedName("InvoiceSK")
    private BigDecimal invoiceSK;

    @SerializedName("LineOfBusiness")
    private String lineOfBusiness;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("PayerID")
    private String payerId;

    @SerializedName("ContractID")
    private String contractId;

    @SerializedName("ContractName")
    private String contractName;

    @SerializedName("DateOfServiceStart")
    private Date invoiceStartDate;

    @SerializedName("DateOfServiceEnd")
    private Date invoiceEndDate;

    @SerializedName("InvoiceDate")
    private Date invoiceDate;

    @SerializedName("InvoiceNumber")
    private String invoiceNumber;

    @SerializedName("PatientLastName")
    private String patienttLastName;

    @SerializedName("PatientFirstName")
    private String patienttFirstName;

    @SerializedName("BilledAmount")
    private BigDecimal billedAmount;

    @SerializedName("PaidAmount")
    private BigDecimal paidAmount;

    @SerializedName("TransactionCode")
    private String transactionCode;

    @SerializedName("DenialCode")
    private String denialCode;

    @SerializedName("Balance")
    private BigDecimal balanceAmount;

    @SerializedName("SubmissionStatusDescription")
    private String invoiceSubmissionStatusDescription;

    @SerializedName("SubmissionStatusCode")
    private String invoiceSubmissionStatusCode;

    @SerializedName("InvoiceStatusCode")
    private String invoiceStatusCode;

    @SerializedName("InvoiceStatusDescription")
    private String invoiceStatusDescription;

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }



    public Date getInvoiceStartDate() {
        return invoiceStartDate;
    }

    public void setInvoiceStartDate(Date invoiceStartDate) {
        this.invoiceStartDate = invoiceStartDate;
    }

    public Date getInvoiceEndDate() {
        return invoiceEndDate;
    }

    public void setInvoiceEndDate(Date invoiceEndDate) {
        this.invoiceEndDate = invoiceEndDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPatienttLastName() {
        return patienttLastName;
    }

    public void setPatienttLastName(String patienttLastName) {
        this.patienttLastName = patienttLastName;
    }

    public String getPatienttFirstName() {
        return patienttFirstName;
    }

    public void setPatienttFirstName(String patienttFirstName) {
        this.patienttFirstName = patienttFirstName;
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

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getInvoiceSubmissionStatusDescription() {
        return invoiceSubmissionStatusDescription;
    }

    public void setInvoiceSubmissionStatusDescription(String invoiceSubmissionStatusDescription) {
        this.invoiceSubmissionStatusDescription = invoiceSubmissionStatusDescription;
    }

    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }


    public String getInvoiceSubmissionStatusCode() {
        return invoiceSubmissionStatusCode;
    }

    public void setInvoiceSubmissionStatusCode(String invoiceSubmissionStatusCode) {
        this.invoiceSubmissionStatusCode = invoiceSubmissionStatusCode;
    }

    public String getInvoiceStatusDescription() {
        return invoiceStatusDescription;
    }

    public void setInvoiceStatusDescription(String invoiceStatusDescription) {
        this.invoiceStatusDescription = invoiceStatusDescription;
    }

    public String getDenialCode() {
        return denialCode;
    }

    public void setDenialCode(String denialCode) {
        this.denialCode = denialCode;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getAccountsReceivableSK() {
        return accountsReceivableSK;
    }

    public void setAccountsReceivableSK(BigDecimal accountsReceivableSK) {
        this.accountsReceivableSK = accountsReceivableSK;
    }

    public BigDecimal getInvoiceSK() {
        return invoiceSK;
    }

    public void setInvoiceSK(BigDecimal invoiceSK) {
        this.invoiceSK = invoiceSK;
    }

}