package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import com.sandata.lab.data.model.dl.model.Visit;

/**
 * <p>Extension of Visit entity for exception logic.</p>
 *
 * @author jasonscott
 */
public class VisitForExceptionsFact extends Visit {

    private static final long serialVersionUID = 1L;

    public ScheduleEvent scheduleEvent;

    public ScheduleEvent getScheduleEvent() {
        return scheduleEvent;
    }

    public void setScheduleEvent(ScheduleEvent scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VisitForExceptionsFact that = (VisitForExceptionsFact) o;

        return scheduleEvent != null ? scheduleEvent.equals(that.scheduleEvent) : that.scheduleEvent == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (scheduleEvent != null ? scheduleEvent.hashCode() : 0);
        return result;
    }
}
