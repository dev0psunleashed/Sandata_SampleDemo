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
public class StaffWorkedHoursResult {
    private static final long serialVersionUID = 1L;
    
    
    @SerializedName("BusinessEntityID")
    private String bsnEntId;
    @SerializedName("StaffID")
    private String staffId;
    @SerializedName("DateFrom")
    private Date dateFrom;
    @SerializedName("DateTo")
    private Date dateTo;
    @SerializedName("WorkedHours")
    private BigDecimal workedHours;
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
     * @return the workedHours
     */
    public BigDecimal getWorkedHours() {
        return workedHours;
    }
    /**
     * @param workedHours the workedHours to set
     */
    public void setWorkedHours(BigDecimal workedHours) {
        this.workedHours = workedHours;
    }
    /**
     * @return the bsnEntId
     */
    public String getBsnEntId() {
        return bsnEntId;
    }
    /**
     * @param bsnEntId the bsnEntId to set
     */
    public void setBsnEntId(String bsnEntId) {
        this.bsnEntId = bsnEntId;
    }
    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
