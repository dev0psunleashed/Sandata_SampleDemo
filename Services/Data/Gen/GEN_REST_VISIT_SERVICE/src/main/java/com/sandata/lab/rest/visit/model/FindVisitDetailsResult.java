package com.sandata.lab.rest.visit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by khangle on 02/08/2017.
 */
public class FindVisitDetailsResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("VisitSK")
    private Long visitSk;

    @SerializedName("VisitStartDate")
    private Date visitStartDate;

    @SerializedName("VisitEndDate")
    private Date visitEndDate;

    @SerializedName("VisitTimeZone")
    private String visitTz;

    @SerializedName("VisitStatus")
    private String visitStatus;

    @SerializedName("Company")
    private String company;

    @SerializedName("PayerId")
    private String payerId;

    @SerializedName("PayerName")
    private String payerName;

    @SerializedName("ContractId")
    private String contractId;

    @SerializedName("ContractName")
    private String contractName;

    @SerializedName("ServiceName")
    private String serviceName;

    @SerializedName("ServiceRateType")
    private String serviceRateType;

    @SerializedName("ServiceRateQualifierCode")
    private String serviceRateQualifierCode;

    @SerializedName("BillCode")
    private String billCode;

    @SerializedName("CallIn")
    private Date callIn;

    @SerializedName("CallOut")
    private Date callOut;

    @SerializedName("CallHours")
    private BigDecimal callHours;

    @SerializedName("AdjustedIn")
    private Date adjustedIn;

    @SerializedName("AdjustedOut")
    private Date adjustedOut;

    @SerializedName("BillHours")
    private BigDecimal billHours;

    @SerializedName("PatientVerifiedTime")
    private String patientVerifiedTime;

    @SerializedName("PatientVerifiedService")
    private String patientVerifiedService;

    @SerializedName("PatientDigitalSignatureDocID")
    private String patientDigitalSignatureDocId;

    @SerializedName("PatientAudioSignatureDocID")
    private String patientAudioSignatureDocId;

    @SerializedName("VisitDoNotBillIndicator")
    private boolean visitDoNotBillIndicator;

    public Long getVisitSk() {
        return visitSk;
    }

    public void setVisitSk(Long visitSk) {
        this.visitSk = visitSk;
    }

    public Date getVisitStartDate() {
        return visitStartDate;
    }

    public void setVisitStartDate(Date visitStartDate) {
        this.visitStartDate = visitStartDate;
    }

    public Date getVisitEndDate() {
        return visitEndDate;
    }

    public void setVisitEndDate(Date visitEndDate) {
        this.visitEndDate = visitEndDate;
    }

    public String getVisitTz() {
        return visitTz;
    }

    public void setVisitTz(String visitTz) {
        this.visitTz = visitTz;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceRateType() {
        return serviceRateType;
    }

    public void setServiceRateType(String serviceRateType) {
        this.serviceRateType = serviceRateType;
    }

    public String getServiceRateQualifierCode() {
        return serviceRateQualifierCode;
    }

    public void setServiceRateQualifierCode(String serviceRateQualifierCode) {
        this.serviceRateQualifierCode = serviceRateQualifierCode;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Date getCallIn() {
        return callIn;
    }

    public void setCallIn(Date callIn) {
        this.callIn = callIn;
    }

    public Date getCallOut() {
        return callOut;
    }

    public void setCallOut(Date callOut) {
        this.callOut = callOut;
    }

    public BigDecimal getCallHours() {
        return callHours;
    }

    public void setCallHours(BigDecimal callHours) {
        this.callHours = callHours;
    }

    public Date getAdjustedIn() {
        return adjustedIn;
    }

    public void setAdjustedIn(Date adjustedIn) {
        this.adjustedIn = adjustedIn;
    }

    public Date getAdjustedOut() {
        return adjustedOut;
    }

    public void setAdjustedOut(Date adjustedOut) {
        this.adjustedOut = adjustedOut;
    }

    public BigDecimal getBillHours() {
        return billHours;
    }

    public void setBillHours(BigDecimal billHours) {
        this.billHours = billHours;
    }

    public String getPatientVerifiedTime() {
        return patientVerifiedTime;
    }

    public void setPatientVerifiedTime(String patientVerifiedTime) {
        this.patientVerifiedTime = patientVerifiedTime;
    }

    public String getPatientVerifiedService() {
        return patientVerifiedService;
    }

    public void setPatientVerifiedService(String patientVerifiedService) {
        this.patientVerifiedService = patientVerifiedService;
    }

    public String getPatientDigitalSignatureDocId() {
        return patientDigitalSignatureDocId;
    }

    public void setPatientDigitalSignatureDocId(String patientDigitalSignatureDocId) {
        this.patientDigitalSignatureDocId = patientDigitalSignatureDocId;
    }

    public String getPatientAudioSignatureDocId() {
        return patientAudioSignatureDocId;
    }

    public void setPatientAudioSignatureDocId(String patientAudioSignatureDocId) {
        this.patientAudioSignatureDocId = patientAudioSignatureDocId;
    }

    public boolean isVisitDoNotBillIndicator() {
        return visitDoNotBillIndicator;
    }

    public void setVisitDoNotBillIndicator(boolean visitDoNotBillIndicator) {
        this.visitDoNotBillIndicator = visitDoNotBillIndicator;
    }
}
