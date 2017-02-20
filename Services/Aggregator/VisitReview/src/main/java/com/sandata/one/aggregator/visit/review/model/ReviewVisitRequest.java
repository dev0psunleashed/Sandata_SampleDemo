package com.sandata.one.aggregator.visit.review.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReviewVisitRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String payerId;
    private String contractId;
    private String serviceName;
    private String businessEntityId;
    private Date fromDate;
    private Date toDate;
    private String patientLastName;
    private String patientFirstName;
    private String patientName;
    private String patientId;
    private String medicaidId;
    private String staffLastName;
    private String staffFirstName;
    private String staffName;
    private String staffId;
    private String staffSsn;
    private String visitStatus;
    private List<BigInteger> exceptionsList;
    private String callType;
    private String coordinatorName;
    private String coordinatorId;
    private String managerName;
    private int page;
    private int pageSize;
    private String sortOn;
    private String direction;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMedicaidId() {
        return medicaidId;
    }

    public void setMedicaidId(String medicaidId) {
        this.medicaidId = medicaidId;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffSsn() {
        return staffSsn;
    }

    public void setStaffSsn(String staffSsn) {
        this.staffSsn = staffSsn;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }

    public List<BigInteger> getExceptionsList() {
        return exceptionsList;
    }

    public void setExceptionsList(List<BigInteger> exceptionsList) {
        this.exceptionsList = exceptionsList;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
