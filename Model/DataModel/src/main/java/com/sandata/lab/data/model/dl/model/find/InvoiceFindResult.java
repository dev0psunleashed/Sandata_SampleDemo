package com.sandata.lab.data.model.dl.model.find;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * <p>InvoiceFindResult</p>
 * <p>Date: 7/27/2016</p>
 * <p>Time: 3:00 PM</p>
 *
 * @author jasonscott
 */
public class InvoiceFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BillingSK")
    protected BigInteger billingSk;

    @SerializedName("LineOfBusiness")
    protected String lineOfBusiness;

    @SerializedName("PayerName")
    protected String payerName;

    @SerializedName("PayerID")
    protected String payerId;

    @SerializedName("PayerInvoiceType")
    protected String payerInvoiceType;

    @SerializedName("ContractDescription")
    protected String contractDescription;

    @SerializedName("ContractID")
    protected String contractId;

    @SerializedName("InvoiceFromDate")
    protected Date invoiceFromDate;

    @SerializedName("InvoiceToDate")
    protected Date invoiceToDate;

    @SerializedName("InvoiceNumber")
    protected String invoiceNumber;

    @SerializedName("InvoiceDate")
    protected Date invoiceDate;

    @SerializedName("PatientLastName")
    protected String patientLastName;

    @SerializedName("PatientFirstName")
    protected String patientFirstName;

    @SerializedName("PatientInsuranceIdNumber")
    protected String patientInsuranceIdNumber;

    @SerializedName("PatientID")
    protected String patientId;

    @SerializedName("Location")
    protected String location;

    @SerializedName("BusinessEntityID")
    protected String businessEntityId;

    @SerializedName("BilledAmount")
    protected BigDecimal billedAmount;

    @SerializedName("SubmissionStatus")
    protected String submissionStatus;

    @SerializedName("Status")
    protected String status;

    @SerializedName("EditErrorReasons")
    protected List<String> editErrorReasons;

    @SerializedName("InvoiceDeletedBy")
    protected String invoiceDeletedBy;

    @SerializedName("InvoiceDeletedReasonCode")
    protected String invoiceDeletedReasonCode;

    @SerializedName("InvoiceDeletedNotes")
    protected String invoiceDeletedNotes;

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

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    public Date getInvoiceFromDate() {
        return invoiceFromDate;
    }

    public void setInvoiceFromDate(Date invoiceFromDate) {
        this.invoiceFromDate = invoiceFromDate;
    }

    public Date getInvoiceToDate() {
        return invoiceToDate;
    }

    public void setInvoiceToDate(Date invoiceToDate) {
        this.invoiceToDate = invoiceToDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientInsuranceIdNumber() {
        return patientInsuranceIdNumber;
    }

    public void setPatientInsuranceIdNumber(String patientInsuranceIdNumber) {
        this.patientInsuranceIdNumber = patientInsuranceIdNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(BigDecimal billedAmount) {
        this.billedAmount = billedAmount;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public List<String> getEditErrorReasons() {
        return editErrorReasons;
    }

    public void setEditErrorReasons(List<String> editErrorReasons) {
        this.editErrorReasons = editErrorReasons;
    }

    public String getInvoiceDeletedBy() {
        return invoiceDeletedBy;
    }

    public void setInvoiceDeletedBy(String invoiceDeletedBy) {
        this.invoiceDeletedBy = invoiceDeletedBy;
    }

    public String getInvoiceDeletedReasonCode() {
        return invoiceDeletedReasonCode;
    }

    public void setInvoiceDeletedReasonCode(String invoiceDeletedReasonCode) {
        this.invoiceDeletedReasonCode = invoiceDeletedReasonCode;
    }

    public String getInvoiceDeletedNotes() {
        return invoiceDeletedNotes;
    }

    public void setInvoiceDeletedNotes(String invoiceDeletedNotes) {
        this.invoiceDeletedNotes = invoiceDeletedNotes;
    }

    public String getPayerInvoiceType() {
        return payerInvoiceType;
    }

    public void setPayerInvoiceType(String payerInvoiceType) {
        this.payerInvoiceType = payerInvoiceType;
    }

    public BigInteger getBillingSk() {
        return billingSk;
    }

    public void setBillingSk(BigInteger billingSk) {
        this.billingSk = billingSk;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
