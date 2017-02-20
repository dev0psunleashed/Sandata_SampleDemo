package com.sandata.lab.data.model.dl.model.extended.schedule;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.dl.model.Schedule;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;

/**
 * Created by khangle on 09/12/2016.
 */
public class ScheduleExt extends Schedule {
    private static final long serialVersionUID = 1L;

    @SerializedName("ServiceSK")
    protected BigInteger serviceSK;

    @SerializedName("AuthorizationID")
    protected String authorizationID;

    @SerializedName("AuthorizationSK")
    protected BigInteger authorizationSK;

    @SerializedName("SelectedDaysOfWeek")
    protected SelectedDaysOfWeek selectedDaysOfWeek;

    @SerializedName("WeekOfMonthNumber")
    protected int weekOfMonthNumber;
    
    @SerializedName("PlanOfCareSK")
    protected BigInteger planOfCareSK;
    
    @SerializedName("PayerID")
    protected String payerId;
    
    @SerializedName("EventStatus")
    protected String eventStatus;

    /**
     * Default constructor
     */
    public ScheduleExt() {

    }

    /**
     * Constructor
     * @param schedule
     */
    public ScheduleExt(Schedule schedule) {
        BeanUtils.copyProperties(schedule, this);
    }


    public SelectedDaysOfWeek getSelectedDaysOfWeek() {
        return selectedDaysOfWeek;
    }

    public void setSelectedDaysOfWeek(SelectedDaysOfWeek selectedDaysOfWeek) {
        this.selectedDaysOfWeek = selectedDaysOfWeek;
    }

    public int getWeekOfMonthNumber() {
        return weekOfMonthNumber;
    }

    public void setWeekOfMonthNumber(int weekOfMonthNumber) {
        this.weekOfMonthNumber = weekOfMonthNumber;
    }

    public BigInteger getServiceSK() {
        return serviceSK;
    }

    public void setServiceSK(BigInteger serviceSK) {
        this.serviceSK = serviceSK;
    }

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(String authorizationID) {
        this.authorizationID = authorizationID;
    }

    public BigInteger getAuthorizationSK() {
        return authorizationSK;
    }

    public void setAuthorizationSK(BigInteger authorizationSK) {
        this.authorizationSK = authorizationSK;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public BigInteger getPlanOfCareSK() {
        return planOfCareSK;
    }

    public void setPlanOfCareSK(BigInteger planOfCareSK) {
        this.planOfCareSK = planOfCareSK;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
}
