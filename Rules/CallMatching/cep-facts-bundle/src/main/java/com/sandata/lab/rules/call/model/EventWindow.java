package com.sandata.lab.rules.call.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        this.center = new Date(center.getTime());
        this.offSetPast = offSetPast;
        this.offSetFuture = offSetFuture;
    }

    public Date getFromDate() {
        Date d = Schedule.CreateDateWithOffset(center, offSetPast);
        if ( d == null)
        {
            d = new Date(center.getTime() - ((60 * 60 * 1000) * offSetPast));
        }
        return d;
    }

    public Date getCenter() {
        return center;
    }

    public void setCenter(Date center) {
        this.center = center;
    }

    public Date getToDate() {

        Date d = Schedule.CreateDateWithOffset(center, offSetFuture);
        if ( d == null)
        {
            d = new Date(center.getTime() + ((60 * 60 * 1000) * offSetFuture));
        }
        return d;
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

    public static final EventWindow estimateWindow(Date from, Date to) {
        TimeUnit timeUnit = TimeUnit.HOURS;
        long diffInMillis = to.getTime() - from.getTime();
        long centerInMillis = from.getTime() + (diffInMillis/2);
        Date c = new Date(centerInMillis);
        long diffInHours = timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
        int dH = (int)(diffInHours/2);
        EventWindow w = new EventWindow(c, -dH, dH);
        return w;
    }
}
