package com.sandata.lab.data.model.dl.model.extended.payroll;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ServiceUnitName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Entity to expose the current Staff Rate based on payroll hierarchy rules.
 * <p/>
 *
 * @author David Rutgos
 */
public class CurrentStaffRate extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("StaffRateSource")
    protected StaffRateSource staffRateSource;

    // Check the StaffRateSource since the SK value can be either StaffRate or BusinessEntityRate
    @SerializedName("StaffRateSK")
    protected BigInteger staffRateSK;

    @SerializedName("StaffRateEffectiveDate")
    protected Date staffRateEffectiveDate;

    @SerializedName("StaffRateTerminationDate")
    protected Date staffRateTerminationDate;

    @SerializedName("StaffRateAmount")
    protected BigDecimal staffRateAmount;

    @SerializedName("ServiceUnitName")
    protected ServiceUnitName serviceUnitName;

    public StaffRateSource getStaffRateSource() {
        return staffRateSource;
    }

    public void setStaffRateSource(StaffRateSource staffRateSource) {
        this.staffRateSource = staffRateSource;
    }

    public BigInteger getStaffRateSK() {
        return staffRateSK;
    }

    public void setStaffRateSK(BigInteger staffRateSK) {
        this.staffRateSK = staffRateSK;
    }

    public Date getStaffRateEffectiveDate() {
        return staffRateEffectiveDate;
    }

    public void setStaffRateEffectiveDate(Date staffRateEffectiveDate) {
        this.staffRateEffectiveDate = staffRateEffectiveDate;
    }

    public Date getStaffRateTerminationDate() {
        return staffRateTerminationDate;
    }

    public void setStaffRateTerminationDate(Date staffRateTerminationDate) {
        this.staffRateTerminationDate = staffRateTerminationDate;
    }

    public BigDecimal getStaffRateAmount() {
        return staffRateAmount;
    }

    public void setStaffRateAmount(BigDecimal staffRateAmount) {
        this.staffRateAmount = staffRateAmount;
    }

    public ServiceUnitName getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(ServiceUnitName serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }
}
