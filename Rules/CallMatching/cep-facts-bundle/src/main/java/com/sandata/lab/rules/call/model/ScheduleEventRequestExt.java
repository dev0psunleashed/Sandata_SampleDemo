package com.sandata.lab.rules.call.model;

import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tom.dornseif on 11/18/2015.
 */
public class ScheduleEventRequestExt extends ScheduleEventRequest implements Serializable {
    private EventWindow window;

    static final long serialVersionUID = 1L;

    public ScheduleEventRequestExt(String dnis) {
        super();
        this.window = new EventWindow(new Date(), -24, 24);
        super.setDnis(dnis);
        super.setFromDate(this.window.getFromDate());
        super.setToDate(this.window.getToDate());
    }

    public ScheduleEventRequestExt(String dnis, Date center, int offsetPast, int offsetFuture, String staffId) {
        super();
        this.window = new EventWindow(center, offsetPast, offsetFuture);
        super.setDnis(dnis);
        super.setStaffId(staffId);
        super.setFromDate(this.window.getFromDate());
        super.setToDate(this.window.getToDate());
    }
    public final ScheduleEventRequest getScheduleRequest() {
        ScheduleEventRequest s = new ScheduleEventRequest();
        s.setAni(super.getAni());
        s.setPatientId(super.getPatientId());
        s.setDnis(super.getDnis());
        s.setStaffId(super.getStaffId());
        s.setToDate(super.getToDate());
        s.setFromDate(super.getFromDate());
        return s;
    }

}
