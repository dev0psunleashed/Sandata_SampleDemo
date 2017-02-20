package com.sandata.lab.rest.sched.model;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.joda.time.LocalTime;

/**
 * Model for a day of week in {@link com.sandata.lab.data.model.dl.model.extended.schedule.SelectedDaysOfWeek}
 */
public class DayInWeek {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isOvernight;

    public DayInWeek(LocalTime startTime, LocalTime endTime, boolean isOvernight) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isOvernight = isOvernight;

        if(this.isOvernight && this.endTime.isAfter(this.startTime)){
            throw new SandataRuntimeException(String.format("There is an invalid Overnight schedule event: Starttime: %s - Endtime: %s", this.startTime, this.endTime));
        }

        if(!this.isOvernight && this.endTime.isBefore(this.startTime)){
            throw new SandataRuntimeException(String.format("There is not an Overnight schedule event, but Starttime: %s - Endtime: %s", this.startTime, this.endTime));
        }
    }

    /**
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return the isOvernight
     */
    public boolean isOvernight() {
        return isOvernight;
    }
}
