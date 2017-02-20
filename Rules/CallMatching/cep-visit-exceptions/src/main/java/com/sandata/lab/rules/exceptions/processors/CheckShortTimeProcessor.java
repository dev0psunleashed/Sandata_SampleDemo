package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.rules.exceptions.app.AppContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/2/2016
 * Time: 3:06 PM
 */
public class CheckShortTimeProcessor implements org.apache.camel.Processor {
    private static Date completedFiredTime;
    @Override
    public void process(Exchange exchange) throws Exception {
        /*String timerName = (String) properties.get(Exchange.TIMER_NAME);
        Long timePeriod = (Long)properties.get(Exchange.TIMER_PERIOD);
        Date timerFiredTime = (Date) properties.get(Exchange.TIMER_FIRED_TIME);
        Long timerCounter = (Long) properties.get(Exchange.TIMER_COUNTER);
        */

        Message in = exchange.getIn();
        Date timerFiredTime = (java.util.Date) in.getHeader(Exchange.TIMER_FIRED_TIME);
        if(timerFiredTime == null)
        {
            timerFiredTime =  in.getHeader("firedTime", java.util.Date.class);
        }
        if(timerFiredTime == null) {
            timerFiredTime = completedFiredTime;
        }
        else {
            completedFiredTime = timerFiredTime;
        }
        ArrayList<Date> array = new ArrayList();
        Date from;
        Date to = timerFiredTime;
        String uuid = UUID.randomUUID().toString();

        if(to != null) {
            from = getLastMinutes(to, -15);
            array.add(from);
            array.add(to);
            in.setHeader("guid", uuid);
            in.setBody(array, ArrayList.class);

        }
    }
    private Date getLastMinutes(Date todaysDateNow, int minusMinutes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.MINUTE, minusMinutes);
        return cal.getTime();
    }
}
