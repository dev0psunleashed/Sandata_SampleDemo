package com.sandata.lab.data.model.dl.model.extended;

import com.sandata.lab.data.model.dl.model.ScheduleEvent;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;

/**
 * Date: 11/13/15
 * Time: 12:55 PM
 */

public class ScheduleEventExt extends ScheduleEvent {

    private static final long serialVersionUID = 1L;
    private String vv_id;
    private String ani;

    public ScheduleEventExt() {
    }

    public ScheduleEventExt(final ScheduleEvent scheduleEvent) {

        BeanUtils.copyProperties(scheduleEvent, this);
    }

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getVv_id() {
        return vv_id;
    }

    public void setVv_id(String vv_id) {
        this.vv_id = vv_id;
    }
}
