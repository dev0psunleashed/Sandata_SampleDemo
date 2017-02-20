package com.sandata.lab.rest.ar.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.InvoiceDetail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ArInvoiceFindResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("InvoiceSK")
    protected BigInteger invoiceSk;

    @SerializedName("InvoiceNumber")
    protected String invoiceNumber;

    @SerializedName("BusinessEntityID")
    protected String businessEntityId;

    @SerializedName("PatientMedicareID")
    private String patientMedicareID;

    @SerializedName("PatientMedicaidID")
    private String patientMedicaidID;

    @SerializedName("PatientDateOfBirth")
    private Date patientDateOfBirth;

    @SerializedName("PatientGender")
    private String patientGender;

    @SerializedName("PatientLastName")
    protected String patientLastName;

    @SerializedName("PatientFirstName")
    protected String patientFirstName;

    @SerializedName("PatientMiddleName")
    private String patientMiddleName;

    @SerializedName("PatientInsuranceIdNumber")
    protected String patientInsuranceIdNumber;

    @SerializedName("PatientID")
    protected String patientId;

    @SerializedName("InvoiceStatus")
    protected String invoiceStatus;

    @SerializedName("CRN")
    protected String crn;

    @SerializedName("Balance")
    private BigDecimal balance;

    @SerializedName("InvoiceDetail")
    private List<InvoiceDetail> invoiceDetailList;

    public BigInteger getInvoiceSk() {
        return invoiceSk;
    }

    public void setInvoiceSk(BigInteger invoiceSk) {
        this.invoiceSk = invoiceSk;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getPatientMedicareID() {
        return patientMedicareID;
    }

    public void setPatientMedicareID(String patientMedicareID) {
        this.patientMedicareID = patientMedicareID;
    }

    public String getPatientMedicaidID() {
        return patientMedicaidID;
    }

    public void setPatientMedicaidID(String patientMedicaidID) {
        this.patientMedicaidID = patientMedicaidID;
    }

    public Date getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(Date patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
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

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientInsuranceIdNumber() {
        return patientInsuranceIdNumber;
    }

    public void setPatientInsuranceIdNumber(String patientInsuranceIdNumber) {
        this.patientInsuranceIdNumber = patientInsuranceIdNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<InvoiceDetail> getInvoiceDetailList() {
        return invoiceDetailList;
    }

    public void setInvoiceDetailList(List<InvoiceDetail> invoiceDetailList) {
        this.invoiceDetailList = invoiceDetailList;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
