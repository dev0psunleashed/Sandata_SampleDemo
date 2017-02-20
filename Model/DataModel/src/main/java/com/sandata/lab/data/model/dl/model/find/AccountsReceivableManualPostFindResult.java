package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.InvoiceStatusName;

import java.util.Date;

/**
 * <p>AccountsReceivableManualPostFindResult</p>
 * <p>Date: 8/1/2016</p>
 * <p>Time: 4:00 PM</p>
 *
 * @author jasonscott
 */
    public class AccountsReceivableManualPostFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("InvoiceNumber")
    private String invoiceNumber;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientMiddleName")
    private String patientMiddleName;

    @SerializedName("PatientInsuranceId")
    private String patientInsuranceId;

    @SerializedName("InternalControlNumber")
    private String internalControlNumber;

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
    private Double payment;

    @SerializedName("Balance")
    private Double balance;

    @SerializedName("InvoiceStatusName")
    private InvoiceStatusName invoiceStatusName;

    @SerializedName("NoteType")
    private String noteType;

    @SerializedName("Note")
    private String note;

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

    public String getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(String patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public String getInternalControlNumber() {
        return internalControlNumber;
    }

    public void setInternalControlNumber(String internalControlNumber) {
        this.internalControlNumber = internalControlNumber;
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

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public InvoiceStatusName getInvoiceStatusName() {
        return invoiceStatusName;
    }

    public void setInvoiceStatusName(InvoiceStatusName invoiceStatusName) {
        this.invoiceStatusName = invoiceStatusName;
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
}
