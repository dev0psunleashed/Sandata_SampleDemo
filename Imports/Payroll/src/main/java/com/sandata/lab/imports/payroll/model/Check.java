package com.sandata.lab.imports.payroll.model;

import com.sandata.lab.data.model.base.BaseObject;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Check")
public class Check extends BaseObject {

    @XmlElement(name = "CheckNumber")
    private String checkNumber;

    @XmlElement(name = "CheckDate")
    private Date checkDate;

    @XmlElement(name = "VoucherNumber")
    private String voucherNumber;

    @XmlElement(name = "PeriodBegin")
    private Date periodBegin;

    @XmlElement(name = "PeriodEnd")
    private Date periodEnd;

    @XmlElement(name = "GrossAmount")
    private BigDecimal grossAmount;

    @XmlElement(name = "NetAmount")
    private BigDecimal netAmount;

    @XmlElementWrapper(name = "Deductions")
    @XmlElement(name = "Deduction")
    private List<Deduction> deductions;

    @XmlElementWrapper(name = "Earnings")
    @XmlElement(name = "Earning")
    private List<Earning> earnings;

    @XmlElementWrapper(name = "Taxes")
    @XmlElement(name = "Tax")
    private List<Tax> taxes;

    @XmlElement(name = "StaffID")
    private String staffID;

    @XmlElement(name = "StaffSSN")
    private String staffSSN;

    public String getStaffSSN() {
        return staffSSN;
    }

    public void setStaffSSN(String staffSSN) {
        this.staffSSN = staffSSN;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public List<Deduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<Deduction> deductions) {
        this.deductions = deductions;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Date getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Date periodBegin) {
        this.periodBegin = periodBegin;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }


}
