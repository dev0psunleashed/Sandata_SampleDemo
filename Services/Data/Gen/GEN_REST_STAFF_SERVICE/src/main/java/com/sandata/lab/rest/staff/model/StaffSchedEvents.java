package com.sandata.lab.rest.staff.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Staff;

import java.util.List;

/**
 * Models the relationship between staff and schedule events.
 * <p/>
 *
 * @author David Rutgos
 */
public class StaffSchedEvents extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Staff")
    private Staff staff;

    @SerializedName("ScheduleEvents")
    private List<ScheduleEvent> scheduleEvents;


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<ScheduleEvent> getScheduleEvents() {
        return scheduleEvents;
    }

    public void setScheduleEvents(List<ScheduleEvent> scheduleEvents) {
        this.scheduleEvents = scheduleEvents;
    }
}
