package com.sandata.lab.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.*;

import java.math.BigDecimal;

/**
 * Models the staff rate entities associated with a particular staff member, patient, contract.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffPayrollRates extends BaseObject {

    private static final long serialVersionUID = 1L;

    public StaffPayrollRates(String businessEntityID, String businessEntityLocationID,
                             String businessEntityLineOfBusinessID, String staffID) {

        this.businessEntityID = businessEntityID;
        this.businessEntityLocationID = businessEntityLocationID;
        this.businessEntityLineOfBusinessID = businessEntityLineOfBusinessID;
        this.staffID = staffID;
    }

    private String businessEntityID;
    private String businessEntityLocationID;
    private String businessEntityLineOfBusinessID;
    private String staffID;

    private BusinessEntityRate businessEntityRate;
    private StaffSupplementalRate staffSupplementalRate;
    private StaffAssociatedRate staffAssociatedRate;
    private PayrollRateMatrix payrollRateMatrix;
    private StaffRate staffRate;

    protected BigDecimal staffRateAmount;

    public BusinessEntityRate getBusinessEntityRate() {
        return businessEntityRate;
    }

    public void setBusinessEntityRate(BusinessEntityRate businessEntityRate) {
        this.businessEntityRate = businessEntityRate;
    }

    public StaffSupplementalRate getStaffSupplementalRate() {
        return staffSupplementalRate;
    }

    public void setStaffSupplementalRate(StaffSupplementalRate staffSupplementalRate) {
        this.staffSupplementalRate = staffSupplementalRate;
    }

    public StaffAssociatedRate getStaffAssociatedRate() {
        return staffAssociatedRate;
    }

    public void setStaffAssociatedRate(StaffAssociatedRate staffAssociatedRate) {
        this.staffAssociatedRate = staffAssociatedRate;
    }

    public PayrollRateMatrix getPayrollRateMatrix() {
        return payrollRateMatrix;
    }

    public void setPayrollRateMatrix(PayrollRateMatrix payrollRateMatrix) {
        this.payrollRateMatrix = payrollRateMatrix;
    }

    public StaffRate getStaffRate() {
        return staffRate;
    }

    public void setStaffRate(StaffRate staffRate) {
        this.staffRate = staffRate;
    }

    public BigDecimal getStaffRateAmount() {
        return staffRateAmount;
    }

    public void setStaffRateAmount(BigDecimal staffRateAmount) {
        this.staffRateAmount = staffRateAmount;
    }

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

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
}
