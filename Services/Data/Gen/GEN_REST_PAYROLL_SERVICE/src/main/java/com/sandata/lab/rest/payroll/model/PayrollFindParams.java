package com.sandata.lab.rest.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Date: 8/18/16
 * Time: 5:20 PM
 */
@OracleMetadata(
        packageName = "PKG_PR_UTIL",
        insertMethod = "N/A",
        updateMethod = "N/A",
        deleteMethod = "N/A",
        getMethod = "N/A",
        findMethod = "N/A",
        jpubClass = "com.sandata.lab.rest.payroll.model.jpub.PrFindParamsT",
        primaryKeys = {})
public class PayrollFindParams extends BaseObject {

    private static final long serialVersionUID = 1L;

    @Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 0)
    protected String businessEntityID;

    @Mapping(getter = "getPtId", setter = "setPtId", type = "String", index = 1)
    protected String patientID;

    @Mapping(getter = "getPtFirstName", setter = "setPtFirstName", type = "String", index = 2)
    protected String patientFirstName;

    @Mapping(getter = "getPtLastName", setter = "setPtLastName", type = "String", index = 3)
    protected String patientLastName;

    @Mapping(getter = "getStaffId", setter = "setStaffId", type = "String", index = 4)
    protected String staffID;

    @Mapping(getter = "getStaffFirstName", setter = "setStaffFirstName", type = "String", index = 5)
    protected String staffFirstName;

    @Mapping(getter = "getStaffLastName", setter = "setStaffLastName", type = "String", index = 6)
    protected String staffLastName;

    @Mapping(getter = "getPrFromWeekEndDate", setter = "setPrFromWeekEndDate", type = "java.sql.Timestamp", index = 7)
    protected Date payrollFromDate;

    @Mapping(getter = "getPrToWeekEndDate", setter = "setPrToWeekEndDate", type = "java.sql.Timestamp", index = 8)
    protected Date payrollToDate;

    @Mapping(getter = "getSvcName", setter = "setSvcName", type = "String", index = 9)
    protected String serviceName;

    @Mapping(getter = "getPayHrs", setter = "setPayHrs", type = "java.math.BigDecimal", index = 10)
    protected BigInteger payHours;

    @Mapping(getter = "getPayHrsFilter", setter = "setPayHrsFilter", type = "String", index = 11)
    protected String payHoursFilter;

    @Mapping(getter = "getRateAmt", setter = "setRateAmt", type = "java.math.BigDecimal", index = 12)
    protected BigDecimal rateAmount;

    @Mapping(getter = "getStatus", setter = "setStatus", type = "String", index = 13)
    protected PayrollFindStatus status;

    @Mapping(getter = "getCheckNum", setter = "setCheckNum", type = "String", index = 14)
    protected String checkNumber;

    @Mapping(getter = "getCheckDate", setter = "setCheckDate", type = "java.sql.Timestamp", index = 15)
    protected Date checkDate;

    @Mapping(getter = "getCheckAmount", setter = "setCheckAmount", type = "java.math.BigDecimal", index = 16)
    protected BigDecimal checkAmount;

    @Mapping(getter = "getFromRow", setter = "setFromRow", type = "java.math.BigDecimal", index = 17)
    protected BigInteger fromRow;

    @Mapping(getter = "getToRow", setter = "setToRow", type = "java.math.BigDecimal", index = 18)
    protected BigInteger toRow;

    @Mapping(getter = "getOrderByCol", setter = "setOrderByCol", type = "String", index = 19)
    protected String orderByColumn;

    @Mapping(getter = "getOrderByDir", setter = "setOrderByDir", type = "String", index = 20)
    protected String orderByDirection;

    @Mapping(getter = "getRateTypName", setter = "setRateTypName", type = "String", index = 21)
    protected String rateTypName;

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
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

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

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

    public Date getPayrollFromDate() {
        return payrollFromDate;
    }

    public void setPayrollFromDate(Date payrollFromDate) {
        this.payrollFromDate = payrollFromDate;
    }

    public Date getPayrollToDate() {
        return payrollToDate;
    }

    public void setPayrollToDate(Date payrollToDate) {
        this.payrollToDate = payrollToDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigInteger getPayHours() {
        return payHours;
    }

    public void setPayHours(BigInteger payHours) {
        this.payHours = payHours;
    }

    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(BigDecimal rateAmount) {
        this.rateAmount = rateAmount;
    }

    public PayrollFindStatus getStatus() {
        return status;
    }

    public void setStatus(PayrollFindStatus status) {
        this.status = status;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public BigInteger getFromRow() {
        return fromRow;
    }

    public void setFromRow(BigInteger fromRow) {
        this.fromRow = fromRow;
    }

    public BigInteger getToRow() {
        return toRow;
    }

    public void setToRow(BigInteger toRow) {
        this.toRow = toRow;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getOrderByDirection() {
        return orderByDirection;
    }

    public void setOrderByDirection(String orderByDirection) {
        this.orderByDirection = orderByDirection;
    }

    public String getPayHoursFilter() {
        return payHoursFilter;
    }

    public void setPayHoursFilter(String payHoursFilter) {
        this.payHoursFilter = payHoursFilter;
    }

    public String getRateTypName() {
        return rateTypName;
    }

    public void setRateTypName(String rateTypName) {
        this.rateTypName = rateTypName;
    }
}
