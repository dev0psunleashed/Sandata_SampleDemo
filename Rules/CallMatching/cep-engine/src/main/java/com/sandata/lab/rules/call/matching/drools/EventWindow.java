package com.sandata.lab.rules.call.matching.drools;

import java.io.Serializable;
import java.util.Date;
import com.sandata.lab.rules.call.model.Schedule;
/**
 * Created by tom.dornseif on 11/15/2015.
 */
public class EventWindow implements Serializable {

    static final long serialVersionUID = 1L;
    private int offSetPast;
    private Date center;
    private int offSetFuture;

    /***
     * Create a default 48 hour EventWindow with a center of now and a 24 past and future offset
     */
    public EventWindow(){
        this(new Date(), -24, 24);
    }

    /***
     *
     * @param center
     * @param offSetPast should be negative hour Example -24
     * @param offSetFuture postive hour Ex: 24
     */
    public EventWindow(Date center, int offSetPast, int offSetFuture) {
        center = center;
        offSetPast = offSetPast;
        offSetFuture = offSetFuture;
    }

    public Date getFromDate() {
        return Schedule.CreateDateWithOffset(center, offSetPast);
    }

    public Date getCenter() {
        return center;
    }

    public void setCenter(Date center) {
        this.center = center;
    }

    public Date getToDate() {
        return Schedule.CreateDateWithOffset(center, offSetFuture);
    }


    public int getOffSetPast() {
        return offSetPast;
    }

    public void setOffSetPast(int offSetPast) {
        this.offSetPast = offSetPast;
    }

    public int getOffSetFuture() {
        return offSetFuture;
    }

    public void setOffSetFuture(int offSetFuture) {
        this.offSetFuture = offSetFuture;
    }
}
