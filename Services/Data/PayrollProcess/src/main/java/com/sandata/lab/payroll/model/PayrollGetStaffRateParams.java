package com.sandata.lab.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.math.BigDecimal;
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
        jpubClass = "com.sandata.lab.payroll.model.jpub.PrGetStaffRateParamsT",
        primaryKeys = {})
public class PayrollGetStaffRateParams extends BaseObject {

    private static final long serialVersionUID = 1L;

    @Mapping(getter = "getBeId", setter = "setBeId", type = "String", index = 0)
    protected String businessEntityID;

    @Mapping(getter = "getBeLocId", setter = "setBeLocId", type = "String", index = 1)
    protected String businessEntityLocationID;

    @Mapping(getter = "getBeLobId", setter = "setBeLobId", type = "String", index = 2)
    protected String businessEntityLineOfBusinessID;

    @Mapping(getter = "getPtId", setter = "setPtId", type = "String", index = 3)
    protected String patientID;

    @Mapping(getter = "getStaffId", setter = "setStaffId", type = "String", index = 4)
    protected String staffID;

    @Mapping(getter = "getSvcName", setter = "setSvcName", type = "String", index = 5)
    protected String serviceName;

    @Mapping(getter = "getRateTypName", setter = "setRateTypName", type = "String", index = 6)
    protected String rateTypeName;

    @Mapping(getter = "getRateSubTypName", setter = "setRateSubTypName", type = "String", index = 7)
    protected String rateSubTypeName;

    @Mapping(getter = "getVisitSk", setter = "setVisitSk", type = "java.math.BigDecimal", index = 8)
    protected BigDecimal visitSK;

    @Mapping(getter = "getVisitDate", setter = "setVisitDate", type = "java.sql.Timestamp", index = 9)
    protected Date visitDate;

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getBusinessEntityLocationID() {
        return businessEntityLocationID;
    }

    public void setBusinessEntityLocationID(String businessEntityLocationID) {
        this.businessEntityLocationID = businessEntityLocationID;
    }

    public String getBusinessEntityLineOfBusinessID() {
        return businessEntityLineOfBusinessID;
    }

    public void setBusinessEntityLineOfBusinessID(String businessEntityLineOfBusinessID) {
        this.businessEntityLineOfBusinessID = businessEntityLineOfBusinessID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRateTypeName() {
        return rateTypeName;
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }

    public String getRateSubTypeName() {
        return rateSubTypeName;
    }

    public void setRateSubTypeName(String rateSubTypeName) {
        this.rateSubTypeName = rateSubTypeName;
    }

    public BigDecimal getVisitSK() {
        return visitSK;
    }

    public void setVisitSK(BigDecimal visitSK) {
        this.visitSK = visitSK;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
