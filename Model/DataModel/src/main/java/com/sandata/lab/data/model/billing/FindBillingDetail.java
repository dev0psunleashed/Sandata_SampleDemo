package com.sandata.lab.data.model.billing;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Date: 12/2/15
 * Time: 7:55 PM
 */

public class FindBillingDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("InvoiceSK")
    private long invoiceSk;

    @SerializedName("InvoiceDate")
    private Date invoiceDate;

    @SerializedName("InvoiceAmount")
    private Double invoiceAmount;

    @SerializedName("PayerContractID")
    private String payerContractId;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("PatientFirstName")
    private String patientFirstName;

    @SerializedName("PatientLastName")
    private String patientLastName;

    @SerializedName("PatientID")
    private String patientId;

    @SerializedName("InvoiceHrs")
    private Integer invoiceHours;

    @SerializedName("PayUnit")
    private String payUnit;

    public long getInvoiceSk() {
        return invoiceSk;
    }

    public void setInvoiceSk(long invoiceSk) {
        this.invoiceSk = invoiceSk;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
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

    public Integer getInvoiceHours() {
        return invoiceHours;
    }

    public void setInvoiceHours(Integer invoiceHours) {
        this.invoiceHours = invoiceHours;
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
}
