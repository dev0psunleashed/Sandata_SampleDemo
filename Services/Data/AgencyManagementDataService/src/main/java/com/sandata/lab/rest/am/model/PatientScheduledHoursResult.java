/**
 * 
 */
package com.sandata.lab.rest.am.model;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * @author huyvo
 *
 */
public class PatientScheduledHoursResult {
    private static final long serialVersionUID = 1L;
    
    @SerializedName("BusinessEntityID")
    private String bsnEntId;
    @SerializedName("PatientID")
    private String patientId;
    @SerializedName("DateFrom")
    private Date dateFrom;
    @SerializedName("DateTo")
    private Date dateTo;
    @SerializedName("ScheduledHours")
    private BigDecimal scheduledHours;
    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }
    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
    /**
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }
    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    /**
     * @return the bsnEntId
     */
    public String getBsnEntId() {
        return bsnEntId;
    }
    /**
     * @return the scheduledHours
     */
    public BigDecimal getScheduledHours() {
        return scheduledHours;
    }
    /**
     * @param scheduledHours the scheduledHours to set
     */
    public void setScheduledHours(BigDecimal scheduledHours) {
        this.scheduledHours = scheduledHours;
    }
    /**
     * @param bsnEntId the bsnEntId to set
     */
    public void setBsnEntId(String bsnEntId) {
        this.bsnEntId = bsnEntId;
    }
    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }
    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
