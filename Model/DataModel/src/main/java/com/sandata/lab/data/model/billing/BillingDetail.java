package com.sandata.lab.data.model.billing;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.Visit;

import java.util.Date;
import java.util.List;

/**
 * Date: 12/2/15
 * Time: 7:53 PM
 */

public class BillingDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffID")
    private String staffID;

    @SerializedName("InvoiceSK")
    private long invoiceSk;

    @SerializedName("InvoiceID")
    private String invoiceID;

    @SerializedName("InvoiceDate")
    private Date invoiceDate;

    @SerializedName("InvoiceAmount")
    private long invoiceAmount;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("Contract")
    private String payerContractId;

    @SerializedName("StaffCoordinatorID")
    private String staffCoordinatorId;

    @SerializedName("ProcModifier")
    private String procModifier;

    @SerializedName("BusinessEntityAddress1")
    private String businessEntityAddr1;

    @SerializedName("BusinessEntityAddress2")
    private String businessEntityAddr2;

    @SerializedName("BusinessEntityCity")
    private String businessEntityCity;

    @SerializedName("BusinessEntityState")
    private String businessEntityState;

    @SerializedName("BusinessEntityZip")
    private String businessEntityZip;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("PatientDOB")
    private Date patientDob;

    @SerializedName("ServiceName")
    private String serviceName;

    @SerializedName("BillingFrequency")
    private String billingFrequency;

    @SerializedName("InvoiceHrs")
    private int invoiceHours;

    @SerializedName("IsBillable")
    private boolean isBillable;

    @SerializedName("Visits")
    private List<Visit> visits;

    @SerializedName("PayUnit")
    private String payUnit;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public long getInvoiceSk() {
        return invoiceSk;
    }

    public void setInvoiceSk(long invoiceSk) {
        this.invoiceSk = invoiceSk;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public long getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
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

    public int getInvoiceHours() {
        return invoiceHours;
    }

    public void setInvoiceHours(int invoiceHours) {
        this.invoiceHours = invoiceHours;
    }

    public boolean isBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public String getPayUnit() {
        return payUnit;
    }

    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit;
    }

    public String getPayerContractId() {
        return payerContractId;
    }

    public void setPayerContractId(String payerContractId) {
        this.payerContractId = payerContractId;
    }

    public String getStaffCoordinatorId() {
        return staffCoordinatorId;
    }

    public void setStaffCoordinatorId(String staffCoordinatorId) {
        this.staffCoordinatorId = staffCoordinatorId;
    }

    public String getProcModifier() {
        return procModifier;
    }

    public void setProcModifier(String procModifier) {
        this.procModifier = procModifier;
    }

    public String getBusinessEntityAddr1() {
        return businessEntityAddr1;
    }

    public void setBusinessEntityAddr1(String businessEntityAddr1) {
        this.businessEntityAddr1 = businessEntityAddr1;
    }

    public String getBusinessEntityAddr2() {
        return businessEntityAddr2;
    }

    public void setBusinessEntityAddr2(String businessEntityAddr2) {
        this.businessEntityAddr2 = businessEntityAddr2;
    }

    public String getBusinessEntityCity() {
        return businessEntityCity;
    }

    public void setBusinessEntityCity(String businessEntityCity) {
        this.businessEntityCity = businessEntityCity;
    }

    public String getBusinessEntityState() {
        return businessEntityState;
    }

    public void setBusinessEntityState(String businessEntityState) {
        this.businessEntityState = businessEntityState;
    }

    public String getBusinessEntityZip() {
        return businessEntityZip;
    }

    public void setBusinessEntityZip(String businessEntityZip) {
        this.businessEntityZip = businessEntityZip;
    }

    public Date getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(Date patientDob) {
        this.patientDob = patientDob;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getBillingFrequency() {
        return billingFrequency;
    }

    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }
}
