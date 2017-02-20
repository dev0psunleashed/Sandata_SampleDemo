package com.sandata.lab.data.model.payroll;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.staff.StaffVisit;

import java.util.Date;
import java.util.List;

/**
 * Date: 12/4/15
 * Time: 1:16 AM
 */

public class PayrollDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PayrollSK")
    private long payrollSk;

    @SerializedName("PayrollID")
    private String payrollId;

    @SerializedName("PayEndDate")
    private Date payEndDate;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("PayHours")
    private Double payHours;

    @SerializedName("Check")
    private String check;

    @SerializedName("CheckAmount")
    private Double checkAmount;

    @SerializedName("CheckDate")
    private Date checkDate;

    @SerializedName("PayRate")
    private Double payRate;

    @SerializedName("Services")
    private String services;

    @SerializedName("Status")
    private String status = "PENDING"; // DEFAULT

    @SerializedName("StaffVisits")
    private List<StaffVisit> staffVisits;

    public long getPayrollSk() {
        return payrollSk;
    }

    public void setPayrollSk(long payrollSk) {
        this.payrollSk = payrollSk;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Double getPayHours() {
        return payHours;
    }

    public void setPayHours(Double payHours) {
        this.payHours = payHours;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public List<StaffVisit> getStaffVisits() {
        return staffVisits;
    }

    public void setStaffVisits(List<StaffVisit> staffVisits) {
        this.staffVisits = staffVisits;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
