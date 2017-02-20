package com.sandata.lab.rules.call.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/16/2016
 * Time: 5:51 PM
 */
public class VisitAndSchedule implements Serializable{
    static final long serialVersionUID = 1L;
    private Schedule schedule;
    private VisitFact visit;
    private String scheduleEventSk;
    private VisitAndScheduleType type;
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        this.type = VisitAndScheduleType.SCHEDULE;
    }

    public VisitFact getVisit() {
        return visit;
    }

    public void setVisit(VisitFact visit) {
        this.visit = visit;
        this.type = VisitAndScheduleType.VISIT;
    }

    public String getScheduleEventSk() {
        return scheduleEventSk;
    }

    public void setScheduleEventSk(String scheduleEventSk) {
        this.scheduleEventSk = scheduleEventSk;
    }

    public VisitAndScheduleType getType() {
        return type;
    }

    private void setType(VisitAndScheduleType type) {
        this.type = type;
    }
}
