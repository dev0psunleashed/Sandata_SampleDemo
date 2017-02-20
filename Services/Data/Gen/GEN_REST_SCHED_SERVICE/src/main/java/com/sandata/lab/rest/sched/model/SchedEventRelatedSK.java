package com.sandata.lab.rest.sched.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SchedEventRelatedSK {

    private final String bsnEntID;
    private final Long schedEvntSK;

    public SchedEventRelatedSK(String bsnEntID, Long schedEvntSK) {
        this.bsnEntID = bsnEntID;
        this.schedEvntSK = schedEvntSK;
    }

    private List<Long> schedEvntActivities;
    private List<Long> schedEvntAuths;
    private List<Long> schedEvntServices;

    public Object[] getParams() {

        List objects = new ArrayList();

        objects.add(String.format("%s|%d", bsnEntID, schedEvntSK));

        if (schedEvntActivities != null) {
            for (Long skValue : schedEvntActivities) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (schedEvntAuths != null) {
            for (Long skValue : schedEvntAuths) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (schedEvntServices != null) {
            for (Long skValue : schedEvntServices) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        return objects.toArray();
    }

    public void addScheduleEventActivity(BigDecimal value) {
        if (value != null) {

            if (schedEvntActivities == null) {
                schedEvntActivities = new ArrayList<>();
            }

            schedEvntActivities.add(value.longValue());
        }
    }

    public void addScheduleEventAuth(BigDecimal value) {
        if (value != null) {

            if (schedEvntAuths == null) {
                schedEvntAuths = new ArrayList<>();
            }

            schedEvntAuths.add(value.longValue());
        }
    }

    public void addScheduleEventService(BigDecimal value) {
        if (value != null) {

            if (schedEvntServices == null) {
                schedEvntServices = new ArrayList<>();
            }

            schedEvntServices.add(value.longValue());
        }
    }
}
