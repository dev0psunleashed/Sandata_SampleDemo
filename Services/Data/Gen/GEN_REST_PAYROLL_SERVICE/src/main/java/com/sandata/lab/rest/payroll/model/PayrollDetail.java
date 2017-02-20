//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sandata.lab.rest.payroll.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private BigDecimal payHours;
    @SerializedName("Check")
    private String check;
    @SerializedName("CheckAmount")
    private BigDecimal checkAmount;
    @SerializedName("CheckDate")
    private Date checkDate;
    @SerializedName("Status")
    private String status = "PENDING";
    @SerializedName("VoucherID")
    private String voucherId;
    @SerializedName("StaffVisits")
    private List<StaffVisit> staffVisits;

    public PayrollDetail() {
    }

    public long getPayrollSk() {
        return this.payrollSk;
    }

    public void setPayrollSk(long payrollSk) {
        this.payrollSk = payrollSk;
    }

    public String getPayrollId() {
        return this.payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getStaffFirstName() {
        return this.staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return this.staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public BigDecimal getPayHours() {
        return this.payHours;
    }

    public void setPayHours(BigDecimal payHours) {
        this.payHours = payHours;
    }

    public String getCheck() {
        return this.check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public BigDecimal getCheckAmount() {
        return this.checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public List<StaffVisit> getStaffVisits() {
        return this.staffVisits;
    }

    public void setStaffVisits(List<StaffVisit> staffVisits) {
        this.staffVisits = staffVisits;
    }

    public Date getPayEndDate() {
        return this.payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
}
