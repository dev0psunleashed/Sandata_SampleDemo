package com.sandata.lab.rules.exceptions.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 9/30/2016
 * Time: 6:45 AM
 */
public class FromToValuesProcessor implements org.apache.camel.Processor {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();

        if(in.getBody() != null) {
            int minutesBack = getHeaderInt("createFromToMinutesBack", in);
            int daysBack = getHeaderInt("createFromToDaysBack", in);
            int incremental = getHeaderInt("createFromToIncremental", in);
            String useDaysBack = getHeaderString("createFromToUseDays", in);
            long todayLong = getHeaderLong(InitFromToValuesProcesor.visitExceptionCheckerStarted, in);
            if (in.getBody() instanceof ArrayList) {
                ArrayList<Date> array = new ArrayList();
                Date from;
                Date to;

                Date started = new Date(todayLong);
                if (useDaysBack.equals("true")) {
                    from = getLastHours(started, daysBack * 24);
                    to = getLastHours(from, Math.abs(incremental * 24));
                    daysBack = daysBack - incremental;
                } else {
                    from = getLastMinutes(started, minutesBack);
                    to = started;
                    minutesBack = Math.abs(10 * minutesBack);//to stop further iterations.
                }
                if (from.before(to) && to.before(started)) {

                    array.add(from);
                    array.add(to);
                    //Message out = exchange.getOut();
                    in.setHeader("createFromToMinutesBack", minutesBack);
                    in.setHeader("createFromToDaysBack", daysBack);
                    in.setHeader("createFromToUseDays", useDaysBack);
                    in.setHeader("createFromToIncremental", incremental);
                    in.setHeader(InitFromToValuesProcesor.visitExceptionCheckerStarted, todayLong);
                    /*out.setHeader("createFromToMinutesBack", minutesBack);
                    out.setHeader("createFromToDaysBack", daysBack);
                    out.setHeader("createFromToUseDays", useDaysBack);
                    out.setHeader("createFromToIncremental", incremental);
                    out.setHeader(InitFromToValuesProcesor.visitExceptionCheckerStarted, todayLong);
                    */
                    in.setBody(array);
                    //out.setBody(array);
                } else {
                    in.setBody(null);
                    //exchange.getOut().setBody(array);
                }
            }
        }
    }
    private long getHeaderLong(String key, Message in) throws Exception{
        return Long.valueOf(getHeaderString(key, in));
    }
    private int getHeaderInt(String key, Message in) throws Exception{
            return Integer.valueOf(getHeaderString(key, in));
    }
    private String getHeaderString(String key, Message in) throws Exception{
        if(in != null && in.getHeader(key) != null ) {
            return in.getHeader(key).toString();
        }
        else
        {
            throw new NoSuchFieldException(String.format("exchange was missing header %s", key));
        }
    }
    Date getLastHours(Date todaysDateNow, int minusHours) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.HOUR, minusHours);
        return cal.getTime();
    }
    Date getLastMinutes(Date todaysDateNow, int minusMinutes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.MINUTE, minusMinutes);
        return cal.getTime();
    }
    Date getSpecifTimeToday(Date todaysDateNow, int hourOfDay) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        return cal.getTime();
    }
    public Logger getVisitExcpLog() {
        if(this.visitExcpLog != null) {
            return this.visitExcpLog;
        }
        else {
            this.visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
            return this.visitExcpLog;
        }
    }

    public void setVisitExcpLog(Logger visitExcpLog) {
        this.visitExcpLog = visitExcpLog;
    }
}
