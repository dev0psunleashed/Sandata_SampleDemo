package com.sandata.lab.data.model.billing;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 12/2/15
 * Time: 7:38 PM
 */

public class BillingSearchFilter extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String payerName;
    private String payerContractId;
    private String location;
    private String coordinator;
    private String patientFirstName;
    private String patientLastName;
    private String patientId;
    private String service;
    private String billingFrequency;
    private String reason;
    private String fromDate;
    private String toDate;
    private String visitDate;
    private String billingModifier;
    private String businessEntityId;

    // unbillable / billable
    private String status = "unbillable";
    private int page = 1;
    private int pageSize = 10;

    // ln = last name / fn = first name
    private String sortOn = "ln";
    private String direction = "ASC";

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBillingFrequency() {
        return billingFrequency;
    }

    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getBillingModifier() {
        return billingModifier;
    }

    public void setBillingModifier(String billingModifier) {
        this.billingModifier = billingModifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortOn() {
        return sortOn;
    }

    public void setSortOn(String sortOn) {
        this.sortOn = sortOn;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public String getPayerContractId() {
        return payerContractId;
    }

    public void setPayerContractId(String payerContractId) {
        this.payerContractId = payerContractId;
    }
}
