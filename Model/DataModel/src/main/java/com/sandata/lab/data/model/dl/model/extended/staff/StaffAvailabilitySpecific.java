/**
 * 
 */
package com.sandata.lab.data.model.dl.model.extended.staff;

import java.math.BigInteger;
import java.util.Date;

import org.joda.time.LocalTime;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

/**
 * @author huyvo
 *
 */
public class StaffAvailabilitySpecific extends BaseObject {
    private static final long serialVersionUID = 1L;

    @SerializedName("StaffAvailabilitySK")
    protected BigInteger staffAvailabilitySK;

    @SerializedName("StaffAvailabilityID")
    protected String staffAvailabilityID;

    @SerializedName("RecordCreateTimestamp")
    protected Date recordCreateTimestamp;

    @SerializedName("RecordUpdateTimestamp")
    protected Date recordUpdateTimestamp;

    @SerializedName("ChangeVersionID")
    protected BigInteger changeVersionID;

    @SerializedName("BusinessEntityID")
    protected String businessEntityID;

    @SerializedName("StaffID")
    protected String staffID;

    @SerializedName("StaffIsAvailableIndicator")
    protected Boolean staffIsAvailableIndicator;

    @SerializedName("AvailabilityStartDate")
    protected Date availabilityStartDate;
    @SerializedName("AvailabilityEndDate")
    protected Date availabilityEndDate;

    @SerializedName("AvailabilityStartTime")
    protected LocalTime availabilityStartTime;
    @SerializedName("AvailabilityEndTime")
    protected LocalTime availabilityEndTime;
    
    @SerializedName("TimezoneName")
    protected String timezoneName;
    
    public BigInteger getStaffAvailabilitySK() {
        return staffAvailabilitySK;
    }
    public void setStaffAvailabilitySK(BigInteger staffAvailabilitySK) {
        this.staffAvailabilitySK = staffAvailabilitySK;
    }
    public String getStaffAvailabilityID() {
        return staffAvailabilityID;
    }
    public void setStaffAvailabilityID(String staffAvailabilityID) {
        this.staffAvailabilityID = staffAvailabilityID;
    }
    public Date getRecordCreateTimestamp() {
        return recordCreateTimestamp;
    }
    public void setRecordCreateTimestamp(Date recordCreateTimestamp) {
        this.recordCreateTimestamp = recordCreateTimestamp;
    }
    public Date getRecordUpdateTimestamp() {
        return recordUpdateTimestamp;
    }
    public void setRecordUpdateTimestamp(Date recordUpdateTimestamp) {
        this.recordUpdateTimestamp = recordUpdateTimestamp;
    }
    public BigInteger getChangeVersionID() {
        return changeVersionID;
    }
    public void setChangeVersionID(BigInteger changeVersionID) {
        this.changeVersionID = changeVersionID;
    }
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
    public Boolean getStaffIsAvailableIndicator() {
        return staffIsAvailableIndicator;
    }
    public void setStaffIsAvailableIndicator(Boolean staffIsAvailableIndicator) {
        this.staffIsAvailableIndicator = staffIsAvailableIndicator;
    }
    public Date getAvailabilityStartDate() {
        return availabilityStartDate;
    }
    public void setAvailabilityStartDate(Date availabilityStartDate) {
        this.availabilityStartDate = availabilityStartDate;
    }
    public Date getAvailabilityEndDate() {
        return availabilityEndDate;
    }
    public void setAvailabilityEndDate(Date availabilityEndDate) {
        this.availabilityEndDate = availabilityEndDate;
    }
    public LocalTime getAvailabilityStartTime() {
        return availabilityStartTime;
    }
    public void setAvailabilityStartTime(LocalTime availabilityStartTime) {
        this.availabilityStartTime = availabilityStartTime;
    }
    public LocalTime getAvailabilityEndTime() {
        return availabilityEndTime;
    }
    public void setAvailabilityEndTime(LocalTime availabilityEndTime) {
        this.availabilityEndTime = availabilityEndTime;
    }
    public String getTimezoneName() {
        return timezoneName;
    }
    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }
}
