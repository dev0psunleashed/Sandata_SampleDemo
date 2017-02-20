package com.sandata.lab.data.model.payroll;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 12/4/15
 * Time: 1:14 AM
 */

public class FindPayrollDetail extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PayrollSK")
    private long payrollSk;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("Services")
    private String services;

    @SerializedName("PayHours")
    private Double payHours;

    @SerializedName("Check")
    private String checkNumber;

    @SerializedName("CheckAmount")
    private Double checkAmount;

    @SerializedName("PayEndDate")
    private String payEndDate;

    @SerializedName("PayrollStatus")
    private String payrollStatus;

    public long getPayrollSk() {
        return payrollSk;
    }

    public void setPayrollSk(long payrollSk) {
        this.payrollSk = payrollSk;
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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Double getPayHours() {
        return payHours;
    }

    public void setPayHours(Double payHours) {
        this.payHours = payHours;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getPayrollStatus() {
        return payrollStatus;
    }

    public void setPayrollStatus(String payrollStatus) {
        this.payrollStatus = payrollStatus;
    }
}
