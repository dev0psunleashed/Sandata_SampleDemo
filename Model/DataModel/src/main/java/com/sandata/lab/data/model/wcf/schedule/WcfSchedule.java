/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.data.model.wcf.schedule;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;
import java.util.List;

/**
 * Models the WCF Schedule class.
 * <p/>
 *
 * @author David Rutgos
 */
public class WcfSchedule extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("ScheduleId")
    private int scheduleId;

    @SerializedName("StaffId")
    private int staffId;

    @SerializedName("PatientId")
    private int patientId;

    @SerializedName("StartDate")
    private Date startDate;

    @SerializedName("EndDate")
    private Date endDate;

    @SerializedName("FromTime")
    private String fromTime;

    @SerializedName("EndTime")
    private String endTime;

    @SerializedName("Restrictions")
    private String restrictions;

    @SerializedName("FrequencyId")
    private Integer frequencyId;

    @SerializedName("NumberOfOccurrences")
    private Integer numberOfOccurrences;

    @SerializedName("DayOfMonth")
    private Integer dayOfMonth;

    @SerializedName("ScheduleWeekDays")
    private List<Integer> scheduleWeekDays;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public Integer getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Integer frequencyId) {
        this.frequencyId = frequencyId;
    }

    public Integer getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public void setNumberOfOccurrences(Integer numberOfOccurrences) {
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public List<Integer> getScheduleWeekDays() {
        return scheduleWeekDays;
    }

    public void setScheduleWeekDays(List<Integer> scheduleWeekDays) {
        this.scheduleWeekDays = scheduleWeekDays;
    }
}