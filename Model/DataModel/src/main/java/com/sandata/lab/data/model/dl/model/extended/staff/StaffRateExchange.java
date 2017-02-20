package com.sandata.lab.data.model.dl.model.extended.staff;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ServiceName;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity defines a simple exchange when creating a StaffRate or StaffAssociatedRate objects.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffRateExchange extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("BusinessEntityID")
    protected String businessEntityID;
    @SerializedName("StaffID")
    protected String staffID;
    @SerializedName("PatientID")
    protected String patientID;
    @SerializedName("EffectiveDate")
    protected Date effectiveDate;
    @SerializedName("TerminationDate")
    protected Date TerminationDate;
    @SerializedName("ServiceName")
    protected ServiceName serviceName;
    @SerializedName("RateTypeName")
    protected String rateTypeName;
    @SerializedName("StaffRateAmount")
    protected BigDecimal staffRateAmount;

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getTerminationDate() {
        return TerminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        TerminationDate = terminationDate;
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

    public BigDecimal getStaffRateAmount() {
        return staffRateAmount;
    }

    public void setStaffRateAmount(BigDecimal staffRateAmount) {
        this.staffRateAmount = staffRateAmount;
    }
}
