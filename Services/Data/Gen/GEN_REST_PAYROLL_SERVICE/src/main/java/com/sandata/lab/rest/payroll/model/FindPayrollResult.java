package com.sandata.lab.rest.payroll.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Entity that defines the values needed for Payroll Find result.
 *
 * EPIC: http://jira.sandata.com/browse/GEOR-6
 *
 * PENDING: https://projects.invisionapp.com/share/JT89OI1EY#/screens/180896661
 * PAID: https://projects.invisionapp.com/share/JT89OI1EY#/screens/180896666
 *
 * <p/>
 *
 * @author David Rutgos
 */
public class FindPayrollResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("PayrollSK")
    private BigInteger payrollSk;

    @SerializedName("StaffFirstName")
    private String staffFirstName;

    @SerializedName("StaffLastName")
    private String staffLastName;

    @SerializedName("StaffID")
    private String staffId;

    @SerializedName("PayHours")
    private BigDecimal payHours;

    @SerializedName("TotalPay")
    private BigDecimal totalPay;

    @SerializedName("ElectronicFinancialTransactionID")
    private String electronicFinancialTransactionID;

    @SerializedName("Check")
    private String checkNumber;

    @SerializedName("CheckAmount")
    private BigDecimal checkAmount;

    @SerializedName("CheckDate")
    private Date checkDate;

    @SerializedName("PayEndDate")
    private Date payEndDate;

    @SerializedName("PayrollStatus")
    private String payrollStatus;

    @SerializedName("Services")
    List<FindPayrollDetail> services;

    public void init() {

        boolean initPayHours = false;
        if (payHours == null) {
            payHours = BigDecimal.ZERO;
            initPayHours = true;
        }

        boolean initTotalPay = false;
        if (totalPay == null) {
            totalPay = BigDecimal.ZERO;
            initTotalPay = true;
        }

        if (!initPayHours && !initTotalPay) {
            return;
        }

        if (services != null) {

            for (FindPayrollDetail findPayrollDetail : services) {

                if (findPayrollDetail.getHours() != null && initPayHours) {
                    payHours = payHours.add(findPayrollDetail.getHours());
                }

                if (findPayrollDetail.getTotalPay() != null && initTotalPay) {
                    totalPay = totalPay.add(findPayrollDetail.getTotalPay());
                }
            }
        }
    }

    public BigInteger getPayrollSk() {
        return payrollSk;
    }

    public void setPayrollSk(BigInteger payrollSk) {
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

    public BigDecimal getPayHours() {

        return payHours;
    }

    public void setPayHours(BigDecimal payHours) {
        this.payHours = payHours;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public BigDecimal getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(BigDecimal checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getPayrollStatus() {
        return payrollStatus;
    }

    public void setPayrollStatus(String payrollStatus) {
        this.payrollStatus = payrollStatus;
    }

    public List<FindPayrollDetail> getServices() {
        return services;
    }

    public void setServices(List<FindPayrollDetail> services) {
        this.services = services;
    }

    public String getElectronicFinancialTransactionID() {
        return electronicFinancialTransactionID;
    }

    public void setElectronicFinancialTransactionID(String electronicFinancialTransactionID) {
        this.electronicFinancialTransactionID = electronicFinancialTransactionID;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }
}
