package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Date: 9/13/15
 * Time: 6:50 PM
 */

public class FindStaffResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffID")
    protected String staffID;

    @SerializedName("StaffSK")
    protected BigInteger staffSK;

    @SerializedName("StaffFirstName")
    protected String staffFirstName;

    @SerializedName("StaffLastName")
    protected String staffLastName;

    @SerializedName("StaffAddressLine1")
    protected String staffAddressLine1;

    @SerializedName("StaffAddressLine2")
    protected String staffAddressLine2;

    @SerializedName("StaffCity")
    protected String staffCity;

    @SerializedName("StaffState")
    protected String staffState;

    @SerializedName("StaffPostalCode")
    protected String staffPostalCode;

    @SerializedName("StaffPreferredPhone")
    protected String staffPreferredPhone;

    @SerializedName("StaffDateOfBirth")
    protected Date staffDateOfBirth;

    @SerializedName("HireDate")
    protected Date staffHireDate;

    @SerializedName("StaffRehireDate")
    protected Date staffLastHireDate;

    @SerializedName("LastDateWorked")
    protected  Date lastDateWorked;

    @SerializedName("BusinessEntitySK")
    private BigInteger businessEntitySK;

    @SerializedName("BusinessEntityName")
    private String businessEntityName;

    @SerializedName("BusinessEntityType")
    private String businessEntityType;

    @SerializedName("BusinessEntityPrimaryAddressLine1")
    private String businessEntityPrimaryAddressLine1;

    @SerializedName("BusinessEntityPrimaryAddressLine2")
    private String businessEntityPrimaryAddressLine2;

    @SerializedName("BusinessEntityPrimaryCity")
    private String businessEntityPrimaryCity;

    @SerializedName("BusinessEntityPrimaryState")
    private String businessEntityPrimaryState;

    @SerializedName("BusinessEntityPrimaryPostalCode")
    private String businessEntityPrimaryPostalCode;

    @SerializedName("BusinessEntityPrimaryZip4")
    private String businessEntityPrimaryZip4;

    @SerializedName("BusinessEntityContactPhone")
    private String businessEntityContactPhone;

    @SerializedName("StaffPrimaryLanguage")
    private String staffPrimaryLanguage;
    
    @SerializedName("StaffServiceName")
    private String staffServiceName;
    
    @SerializedName("StaffEmploymentStatusTypeName")
    private EmploymentStatusTypeName employmentStatusTypeName;

    @SerializedName("ScheduleEvents")
    private List<ScheduleEvent> scheduleEvents;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public BigInteger getStaffSK() {
        return staffSK;
    }

    public void setStaffSK(BigInteger staffSK) {
        this.staffSK = staffSK;
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

    public String getStaffAddressLine1() {
        return staffAddressLine1;
    }

    public void setStaffAddressLine1(String staffAddressLine1) {
        this.staffAddressLine1 = staffAddressLine1;
    }

    public String getStaffAddressLine2() {
        return staffAddressLine2;
    }

    public void setStaffAddressLine2(String staffAddressLine2) {
        this.staffAddressLine2 = staffAddressLine2;
    }

    public String getStaffCity() {
        return staffCity;
    }

    public void setStaffCity(String staffCity) {
        this.staffCity = staffCity;
    }

    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    public String getStaffPostalCode() {
        return staffPostalCode;
    }

    public void setStaffPostalCode(String staffPostalCode) {
        this.staffPostalCode = staffPostalCode;
    }

    public String getStaffPreferredPhone() {
        return staffPreferredPhone;
    }

    public void setStaffPreferredPhone(String staffPreferredPhone) {
        this.staffPreferredPhone = staffPreferredPhone;
    }

    public Date getStaffDateOfBirth() {
        return staffDateOfBirth;
    }

    public void setStaffDateOfBirth(Date staffDateOfBirth) {
        this.staffDateOfBirth = staffDateOfBirth;
    }

    public BigInteger getBusinessEntitySK() {
        return businessEntitySK;
    }

    public void setBusinessEntitySK(BigInteger businessEntitySK) {
        this.businessEntitySK = businessEntitySK;
    }

    public String getBusinessEntityName() {
        return businessEntityName;
    }

    public void setBusinessEntityName(String businessEntityName) {
        this.businessEntityName = businessEntityName;
    }

    public String getBusinessEntityType() {
        return businessEntityType;
    }

    public void setBusinessEntityType(String businessEntityType) {
        this.businessEntityType = businessEntityType;
    }

    public String getBusinessEntityPrimaryAddressLine1() {
        return businessEntityPrimaryAddressLine1;
    }

    public void setBusinessEntityPrimaryAddressLine1(String businessEntityPrimaryAddressLine1) {
        this.businessEntityPrimaryAddressLine1 = businessEntityPrimaryAddressLine1;
    }

    public String getBusinessEntityPrimaryAddressLine2() {
        return businessEntityPrimaryAddressLine2;
    }

    public void setBusinessEntityPrimaryAddressLine2(String businessEntityPrimaryAddressLine2) {
        this.businessEntityPrimaryAddressLine2 = businessEntityPrimaryAddressLine2;
    }

    public String getBusinessEntityPrimaryCity() {
        return businessEntityPrimaryCity;
    }

    public void setBusinessEntityPrimaryCity(String businessEntityPrimaryCity) {
        this.businessEntityPrimaryCity = businessEntityPrimaryCity;
    }

    public String getBusinessEntityPrimaryState() {
        return businessEntityPrimaryState;
    }

    public void setBusinessEntityPrimaryState(String businessEntityPrimaryState) {
        this.businessEntityPrimaryState = businessEntityPrimaryState;
    }

    public String getBusinessEntityPrimaryPostalCode() {
        return businessEntityPrimaryPostalCode;
    }

    public void setBusinessEntityPrimaryPostalCode(String businessEntityPrimaryPostalCode) {
        this.businessEntityPrimaryPostalCode = businessEntityPrimaryPostalCode;
    }

    public String getBusinessEntityPrimaryZip4() {
        return businessEntityPrimaryZip4;
    }

    public void setBusinessEntityPrimaryZip4(String businessEntityPrimaryZip4) {
        this.businessEntityPrimaryZip4 = businessEntityPrimaryZip4;
    }

    public String getBusinessEntityContactPhone() {
        return businessEntityContactPhone;
    }

    public void setBusinessEntityContactPhone(String businessEntityContactPhone) {
        this.businessEntityContactPhone = businessEntityContactPhone;
    }

    public Date getStaffLastHireDate() {
        return staffLastHireDate;
    }

    public void setStaffLastHireDate(Date staffLastHireDate) {
        this.staffLastHireDate = staffLastHireDate;
    }

    public String getStaffPrimaryLanguage() {
        return staffPrimaryLanguage;
    }

    public void setStaffPrimaryLanguage(String staffPrimaryLanguage) {
        this.staffPrimaryLanguage = staffPrimaryLanguage;
    }

    public String getStaffServiceName() {
        return staffServiceName;
    }

    public void setStaffServiceName(String staffServiceName) {
        this.staffServiceName = staffServiceName;
    }

    public Date getLastDateWorked() {
        return lastDateWorked;
    }

    public void setLastDateWorked(Date lastDateWorked) {
        this.lastDateWorked = lastDateWorked;
    }

    public List<ScheduleEvent> getScheduleEvents() {
        return scheduleEvents;
    }

    public void setScheduleEvents(List<ScheduleEvent> scheduleEvents) {
        this.scheduleEvents = scheduleEvents;
    }

    public Date getStaffHireDate() {
        return staffHireDate;
    }

    public void setStaffHireDate(Date staffHireDate) {
        this.staffHireDate = staffHireDate;
    }

    public EmploymentStatusTypeName getEmploymentStatusTypeName() {
        return employmentStatusTypeName;
    }

    public void setEmploymentStatusTypeName(EmploymentStatusTypeName employmentStatusTypeName) {
        this.employmentStatusTypeName = employmentStatusTypeName;
    }
}
