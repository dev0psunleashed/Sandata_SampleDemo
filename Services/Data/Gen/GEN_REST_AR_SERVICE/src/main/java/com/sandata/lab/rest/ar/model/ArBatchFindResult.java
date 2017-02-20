package com.sandata.lab.rest.ar.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ArBatchFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("AccountsReceivableSK")
    private BigInteger accountsReceivableSK;

    @SerializedName("InvoiceNumber")
    private String invoiceNumber;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientMiddleName")
    private String patientMiddleName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("PatientInsuranceID")
    private String patientInsuranceId;

    @SerializedName("CRN")
    private String crn;

    @SerializedName("DateOfService")
    private Date dateOfService;

    @SerializedName("BillCode")
    private String billCode;

    @SerializedName("RemittanceCode")
    private String remittanceCode;

    @SerializedName("TransactionCode")
    private String transactionCode;

    @SerializedName("TransactionType")
    private String transactionType;

    @SerializedName("Modifier1")
    private String modifier1;

    @SerializedName("Modifier2")
    private String modifier2;

    @SerializedName("Modifier3")
    private String modifier3;

    @SerializedName("Modifier4")
    private String modifier4;

    @SerializedName("TransactionDescription")
    private String transactionDescription;

    @SerializedName("Payment")
    private BigDecimal payment;

    @SerializedName("Balance")
    private BigDecimal balance;

    @SerializedName("InvoiceStatus")
    private String invoiceStatus;

    @SerializedName("NoteType")
    private String noteType;

    @SerializedName("Note")
    private String note;

    @SerializedName("PayerID")
    private String payerId;

    @SerializedName("UnappiedBalanceIndicator")
    private boolean unappliedBalanceIND;

    @SerializedName("UserName")
    private String userName;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @SerializedName("TransactionDate")
    private Date transactionDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUnappliedBalanceIND() {
        return unappliedBalanceIND;
    }

    public void setUnappliedBalanceIND(boolean unappliedBalanceIND) {
        this.unappliedBalanceIND = unappliedBalanceIND;
    }

    public BigInteger getAccountsReceivableSK() {
        return accountsReceivableSK;
    }

    public void setAccountsReceivableSK(BigInteger accountsReceivableSK) {
        this.accountsReceivableSK = accountsReceivableSK;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(String patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getRemittanceCode() {
        return remittanceCode;
    }

    public void setRemittanceCode(String remittanceCode) {
        this.remittanceCode = remittanceCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getModifier1() {
        return modifier1;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return modifier2;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return modifier3;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return modifier4;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
}
