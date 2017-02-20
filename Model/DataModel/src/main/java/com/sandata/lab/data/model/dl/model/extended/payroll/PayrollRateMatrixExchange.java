package com.sandata.lab.data.model.dl.model.extended.payroll;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Entity that combines the Regular, Weekend, and Holiday rate matrix together into a single object.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollRateMatrixExchange extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("RegularPayrollRateMatrixSK")
    protected BigInteger regularPayrollRateMatrixSK;
    @SerializedName("WeekendPayrollRateMatrixSK")
    protected BigInteger weekendPayrollRateMatrixSK;
    @SerializedName("HolidayPayrollRateMatrixSK")
    protected BigInteger holidayPayrollRateMatrixSK;
    @SerializedName("RegularChangeVersionID")
    protected BigInteger regularChangeVersionID;
    @SerializedName("WeekendChangeVersionID")
    protected BigInteger weekendChangeVersionID;
    @SerializedName("HolidayChangeVersionID")
    protected BigInteger holidayChangeVersionID;
    @SerializedName("BusinessEntityID")
    protected String businessEntityID;
    @SerializedName("BusinessEntityLocationID")
    protected String businessEntityLocationID;
    @SerializedName("PayerID")
    protected String payerID;
    @SerializedName("ContractID")
    protected String contractID;
    @SerializedName("EffectiveDate")
    protected Date effectiveDate;
    @SerializedName("ServiceName")
    protected ServiceName serviceName;
    @SerializedName("RateTypeName")
    protected String rateTypeName;
    @SerializedName("PayrollRateMatrixNote")
    protected String payrollRateMatrixNote;
    @SerializedName("PayrollCode")
    protected String payrollCode;
    // Regular
    @SerializedName("RegularRateAmount")
    protected BigDecimal regularRateAmount;
    @SerializedName("RegularServiceUnitName")
    protected ServiceUnitName regularServiceUnitName;
    // Weekend
    @SerializedName("WeekendRateAmount")
    protected BigDecimal weekendRateAmount;
    @SerializedName("WeekendServiceUnitName")
    protected ServiceUnitName weekendServiceUnitName;
    // Holiday
    @SerializedName("HolidayRateAmount")
    protected BigDecimal holidayRateAmount;
    @SerializedName("HolidayServiceUnitName")
    protected ServiceUnitName holidayServiceUnitName;

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

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public String getRateTypeName() {
        return rateTypeName;
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }

    public String getPayrollRateMatrixNote() {
        return payrollRateMatrixNote;
    }

    public void setPayrollRateMatrixNote(String payrollRateMatrixNote) {
        this.payrollRateMatrixNote = payrollRateMatrixNote;
    }

    public BigDecimal getRegularRateAmount() {
        return regularRateAmount;
    }

    public void setRegularRateAmount(BigDecimal regularRateAmount) {
        this.regularRateAmount = regularRateAmount;
    }

    public ServiceUnitName getRegularServiceUnitName() {
        return regularServiceUnitName;
    }

    public void setRegularServiceUnitName(ServiceUnitName regularServiceUnitName) {
        this.regularServiceUnitName = regularServiceUnitName;
    }

    public BigDecimal getWeekendRateAmount() {
        return weekendRateAmount;
    }

    public void setWeekendRateAmount(BigDecimal weekendRateAmount) {
        this.weekendRateAmount = weekendRateAmount;
    }

    public ServiceUnitName getWeekendServiceUnitName() {
        return weekendServiceUnitName;
    }

    public void setWeekendServiceUnitName(ServiceUnitName weekendServiceUnitName) {
        this.weekendServiceUnitName = weekendServiceUnitName;
    }

    public BigDecimal getHolidayRateAmount() {
        return holidayRateAmount;
    }

    public void setHolidayRateAmount(BigDecimal holidayRateAmount) {
        this.holidayRateAmount = holidayRateAmount;
    }

    public ServiceUnitName getHolidayServiceUnitName() {
        return holidayServiceUnitName;
    }

    public void setHolidayServiceUnitName(ServiceUnitName holidayServiceUnitName) {
        this.holidayServiceUnitName = holidayServiceUnitName;
    }

    public BigInteger getRegularPayrollRateMatrixSK() {
        return regularPayrollRateMatrixSK;
    }

    public void setRegularPayrollRateMatrixSK(BigInteger regularPayrollRateMatrixSK) {
        this.regularPayrollRateMatrixSK = regularPayrollRateMatrixSK;
    }

    public BigInteger getWeekendPayrollRateMatrixSK() {
        return weekendPayrollRateMatrixSK;
    }

    public void setWeekendPayrollRateMatrixSK(BigInteger weekendPayrollRateMatrixSK) {
        this.weekendPayrollRateMatrixSK = weekendPayrollRateMatrixSK;
    }

    public BigInteger getHolidayPayrollRateMatrixSK() {
        return holidayPayrollRateMatrixSK;
    }

    public void setHolidayPayrollRateMatrixSK(BigInteger holidayPayrollRateMatrixSK) {
        this.holidayPayrollRateMatrixSK = holidayPayrollRateMatrixSK;
    }

    public BigInteger getRegularChangeVersionID() {
        return regularChangeVersionID;
    }

    public void setRegularChangeVersionID(BigInteger regularChangeVersionID) {
        this.regularChangeVersionID = regularChangeVersionID;
    }

    public BigInteger getWeekendChangeVersionID() {
        return weekendChangeVersionID;
    }

    public void setWeekendChangeVersionID(BigInteger weekendChangeVersionID) {
        this.weekendChangeVersionID = weekendChangeVersionID;
    }

    public BigInteger getHolidayChangeVersionID() {
        return holidayChangeVersionID;
    }

    public void setHolidayChangeVersionID(BigInteger holidayChangeVersionID) {
        this.holidayChangeVersionID = holidayChangeVersionID;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }
}
