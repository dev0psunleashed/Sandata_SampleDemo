package com.sandata.lab.rules.call.model;

import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;

import java.io.Serializable;

/**
 * Created by tom.dornseif on 11/18/2015.
 */
public class ScheduleEventResponseExt extends ScheduleEventResponse implements Serializable{

    static final long serialVersionUID = 1L;

    private EventWindow window;


    public EventWindow getWindow() {
        this.window = EventWindow
                .estimateWindow( super.getScheduleEventRequest()
                        .getFromDate(),
                        super.getScheduleEventRequest().getToDate());
        return window;
    }
    public ScheduleEventResponseExt() {}


    public void setWindow(EventWindow window) {
        this.window = window;
    }
}
