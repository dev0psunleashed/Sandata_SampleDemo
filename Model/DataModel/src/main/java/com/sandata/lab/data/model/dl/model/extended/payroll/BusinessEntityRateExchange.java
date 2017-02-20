package com.sandata.lab.data.model.dl.model.extended.payroll;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.ServiceUnitName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Entity that combines the Regular, Weekend, and Holiday rate together into a single object.
 * <p/>
 *
 * @author David Rutgos
 */
public class BusinessEntityRateExchange extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("RegularPayrollRateSK")
    protected BigInteger regularPayrollRateSK;
    @SerializedName("WeekendPayrollRateSK")
    protected BigInteger weekendPayrollRateSK;
    @SerializedName("HolidayPayrollRateSK")
    protected BigInteger holidayPayrollRateSK;
    @SerializedName("RegularChangeVersionID")
    protected BigInteger regularChangeVersionID;
    @SerializedName("WeekendChangeVersionID")
    protected BigInteger weekendChangeVersionID;
    @SerializedName("HolidayChangeVersionID")
    protected BigInteger holidayChangeVersionID;
    @SerializedName("BusinessEntityID")
    protected String businessEntityID;
    @SerializedName("BusinessEntityLineofBusinessID")
    protected String businessEntityLineofBusinessID;
    @SerializedName("BusinessEntityLocationID")
    protected String businessEntityLocationID;
    @SerializedName("EffectiveDate")
    protected Date effectiveDate;
    @SerializedName("ServiceName")
    protected ServiceName serviceName;
    @SerializedName("RateTypeName")
    protected String rateTypeName;
    @SerializedName("ServiceUnitName")
    protected ServiceUnitName serviceUnitName;
    // Regular
    @SerializedName("RegularRateAmount")
    protected BigDecimal regularRateAmount;
    // Weekend
    @SerializedName("WeekendRateAmount")
    protected BigDecimal weekendRateAmount;
    // Holiday
    @SerializedName("HolidayRateAmount")
    protected BigDecimal holidayRateAmount;

    public BigInteger getRegularPayrollRateSK() {
        return regularPayrollRateSK;
    }

    public void setRegularPayrollRateSK(BigInteger regularPayrollRateSK) {
        this.regularPayrollRateSK = regularPayrollRateSK;
    }

    public BigInteger getWeekendPayrollRateSK() {
        return weekendPayrollRateSK;
    }

    public void setWeekendPayrollRateSK(BigInteger weekendPayrollRateSK) {
        this.weekendPayrollRateSK = weekendPayrollRateSK;
    }

    public BigInteger getHolidayPayrollRateSK() {
        return holidayPayrollRateSK;
    }

    public void setHolidayPayrollRateSK(BigInteger holidayPayrollRateSK) {
        this.holidayPayrollRateSK = holidayPayrollRateSK;
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

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getBusinessEntityLineofBusinessID() {
        return businessEntityLineofBusinessID;
    }

    public void setBusinessEntityLineofBusinessID(String businessEntityLineofBusinessID) {
        this.businessEntityLineofBusinessID = businessEntityLineofBusinessID;
    }

    public String getBusinessEntityLocationID() {
        return businessEntityLocationID;
    }

    public void setBusinessEntityLocationID(String businessEntityLocationID) {
        this.businessEntityLocationID = businessEntityLocationID;
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

    public BigDecimal getRegularRateAmount() {
        return regularRateAmount;
    }

    public void setRegularRateAmount(BigDecimal regularRateAmount) {
        this.regularRateAmount = regularRateAmount;
    }

    public BigDecimal getWeekendRateAmount() {
        return weekendRateAmount;
    }

    public void setWeekendRateAmount(BigDecimal weekendRateAmount) {
        this.weekendRateAmount = weekendRateAmount;
    }

    public BigDecimal getHolidayRateAmount() {
        return holidayRateAmount;
    }

    public void setHolidayRateAmount(BigDecimal holidayRateAmount) {
        this.holidayRateAmount = holidayRateAmount;
    }

    public ServiceUnitName getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(ServiceUnitName serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }
}
