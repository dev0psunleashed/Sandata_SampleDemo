package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.rules.exceptions.app.AppContext;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.properties.PropertiesComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 9/24/2016
 * Time: 7:56 AM
 */
public class InitFromToValuesProcesor implements Processor {
    private Logger visitExcpLog = LoggerFactory.getLogger("visitExcpLog");
    public static final String visitExceptionCheckerStarted = "visitExceptionCheckerStarted";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void process(Exchange exchange) throws Exception {
        /*<cm:property name="createFromToStartingHour" value="7" />
            <cm:property name="createFromToQuittingHour" value="19" />
            <cm:property name="createFromToMinutesBack" value="-6" />
            <cm:property name="createFromToDaysBack" value="-10" />
            <cm:property name="createFromToUseDays" value="true" />*/
        CamelContext context = AppContext.getContext();
        int startHour = Integer.valueOf(context.resolvePropertyPlaceholders("{{createFromToStartingHour}}"));
        int endHour = Integer.valueOf(context.resolvePropertyPlaceholders("{{createFromToQuittingHour}}"));
        int minutesBack = Integer.valueOf(context.resolvePropertyPlaceholders("{{createFromToMinutesBack}}"));
        int daysBack = Integer.valueOf(context.resolvePropertyPlaceholders("{{createFromToDaysBack}}"));
        int incremental = Integer.valueOf(context.resolvePropertyPlaceholders("{{createFromToIncremental}}"));
        String useDaysBack = context.resolvePropertyPlaceholders("{{createFromToUseDays}}");

        Message in = exchange.getIn();
        ArrayList array = new ArrayList();
        Date todaysDateNow = new Date();
        Date start = getSpecifTimeToday(todaysDateNow, startHour);
        Date end = getSpecifTimeToday(todaysDateNow,  endHour);
        Date from = getLastMinutes(todaysDateNow, minutesBack);
        if(useDaysBack.equals("true")) {
            from = getLastHours(todaysDateNow, daysBack * 24);
        }
        array.add(from);
        array.add(todaysDateNow);
        in.setHeader("createFromToMinutesBack", minutesBack);
        in.setHeader("createFromToDaysBack", daysBack);
        in.setHeader("createFromToUseDays", useDaysBack);
        in.setHeader("createFromToIncremental", incremental);
        in.setHeader("visitExceptionCheckerStarted", todaysDateNow.getTime());
        in.setBody(array);
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
