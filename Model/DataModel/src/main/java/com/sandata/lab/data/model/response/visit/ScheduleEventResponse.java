package com.sandata.lab.data.model.response.visit;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;

import java.util.List;

/**
 * Date: 11/12/15
 * Time: 11:42 AM
 */

public class ScheduleEventResponse extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String errorMessage;

    private ScheduleEventRequest scheduleEventRequest;
    private List<ScheduleEventExt> scheduleEvents;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ScheduleEventExt> getScheduleEventsExt() {
        return scheduleEvents;
    }

    public void setScheduleEventsExt(List<ScheduleEventExt> scheduleEvents) {
        this.scheduleEvents = scheduleEvents;
    }

    public ScheduleEventRequest getScheduleEventRequest() {
        return scheduleEventRequest;
    }

    public void setScheduleEventRequest(ScheduleEventRequest scheduleEventRequest) {
        this.scheduleEventRequest = scheduleEventRequest;
    }
}
