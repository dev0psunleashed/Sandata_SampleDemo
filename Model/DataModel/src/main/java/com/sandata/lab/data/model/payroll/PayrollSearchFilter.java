package com.sandata.lab.data.model.payroll;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 12/4/15
 * Time: 1:07 AM
 */

public class PayrollSearchFilter extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String staffFirstName;
    private String staffLastName;
    private String staffId;
    private Integer payHours;
    private String service;
    private String fromDate;
    private String toDate;
    private String check;
    private String checkAmount;
    private String checkDate;
    private String payRate;
    private int status = -1;        //default

    private int page = 1;
    private int pageSize = 10;

    // ln = last name / fn = first name
    private String sortOn = "ln";
    private String direction = "ASC";

    private String businessEntityId;

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getPayHours() {
        return payHours;
    }

    public void setPayHours(Integer payHours) {
        this.payHours = payHours;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(String checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
}
