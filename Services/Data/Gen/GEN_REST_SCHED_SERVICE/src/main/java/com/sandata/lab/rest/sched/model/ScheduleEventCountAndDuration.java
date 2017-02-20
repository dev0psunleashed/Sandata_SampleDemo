package com.sandata.lab.rest.sched.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;

/**
 * An entity that stores number of ScheduleEvents and total duration of those events for a date range.
 *
 * Created by khangle on 10/20/2016.
 */
public class ScheduleEventCountAndDuration extends BaseObject {
    private Date scheduleEventStartDate;

    private Date scheduleEventEndDate;

    private int scheduleEventCount;

    private long totalDuration;

    /**
     * Default constructor
     */
    public ScheduleEventCountAndDuration() {
        this.scheduleEventStartDate = null;
        this.scheduleEventEndDate = null;
        this.scheduleEventCount = 0;
        this.totalDuration = 0;
    }

    public Date getScheduleEventStartDate() {
        return scheduleEventStartDate;
    }

    public void setScheduleEventStartDate(Date scheduleEventStartDate) {
        this.scheduleEventStartDate = scheduleEventStartDate;
    }

    public Date getScheduleEventEndDate() {
        return scheduleEventEndDate;
    }

    public void setScheduleEventEndDate(Date scheduleEventEndDate) {
        this.scheduleEventEndDate = scheduleEventEndDate;
    }

    public int getScheduleEventCount() {
        return scheduleEventCount;
    }

    public void setScheduleEventCount(int scheduleEventCount) {
        this.scheduleEventCount = scheduleEventCount;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }
}
