package com.sandata.lab.rest.sched.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.math.BigInteger;

/**
 * Entity to update the StaffID for a given schedule and schedule event.
 * <p/>
 *
 * @author David Rutgos
 */
public class SchedUpdateStaff extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ScheduleSK")
    private BigInteger scheduleSK;

    @SerializedName("ScheduleEventSK")
    private BigInteger scheduleEventSK;

    @SerializedName("StaffID")
    private String staffID;

    @SerializedName("BusinessEntityID")
    private String businessEntityID;

    public BigInteger getScheduleSK() {
        return scheduleSK;
    }

    public void setScheduleSK(BigInteger scheduleSK) {
        this.scheduleSK = scheduleSK;
    }

    public BigInteger getScheduleEventSK() {
        return scheduleEventSK;
    }

    public void setScheduleEventSK(BigInteger scheduleEventSK) {
        this.scheduleEventSK = scheduleEventSK;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getBusinessEntityID() {
        return businessEntityID;
    }

    public void setBusinessEntityID(String businessEntityID) {
        this.businessEntityID = businessEntityID;
    }
}
