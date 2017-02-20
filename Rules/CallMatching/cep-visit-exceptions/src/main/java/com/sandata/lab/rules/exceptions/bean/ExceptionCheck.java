package com.sandata.lab.rules.exceptions.bean;

import com.sandata.lab.rules.exceptions.app.AppContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Properties;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/2/2016
 * Time: 10:50 AM
 */
public class ExceptionCheck {
    private Endpoint shortTimeEndpoint;
    public void checkShortTime(Exchange exchange, @Properties Map<String, Object> properties) {
        String timerName = (String) properties.get(Exchange.TIMER_NAME);
        Long timePeriod = (Long)properties.get(Exchange.TIMER_PERIOD);
        Date timerFiredTime = (Date) properties.get(Exchange.TIMER_FIRED_TIME);
        Long timerCounter = (Long) properties.get(Exchange.TIMER_COUNTER);
        Message in = exchange.getIn();
        ArrayList<Date> array = new ArrayList();
        Date from;
        Date to = timerFiredTime;
        String uuid = UUID.randomUUID().toString();
        if(to != null) {
            from = getLastMinutes(to, -5);
            array.add(from);
            array.add(to);
            in.setHeader("guid", uuid);
            in.setBody(array, ArrayList.class);
            AppContext.getContext().createProducerTemplate().sendBodyAndHeader("activemq:queue:shortTimeVisitExceptionsCheckRequest", array, "guid", uuid);
            Exchange exchange1 = AppContext.getContext().createConsumerTemplate().receive("activemq:queue:callNextShortTimeProcess", 180000);
            String uuidRecvd = (String)exchange.getIn().getHeader("guid");
            if(uuidRecvd.equals(uuid)) {
                exchange.getContext().createProducerTemplate().send("activemq:queue:shortTimeScheduledVisitExceptionsCheckRequest", exchange);
            }


        }
    }

    private Date getLastHours(Date todaysDateNow, int minusHours) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.HOUR, minusHours);
        return cal.getTime();
    }
    private Date getLastMinutes(Date todaysDateNow, int minusMinutes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.MINUTE, minusMinutes);
        return cal.getTime();
    }

    public Endpoint getShortTimeEndpoint() {
        if(this.shortTimeEndpoint == null) {
            this.shortTimeEndpoint = AppContext.getContext().getEndpoint("activemq:queue:shortTimeCheckResponse");
        }
        return this.shortTimeEndpoint;
    }

    public void setShortTimeEndpoint(Endpoint endpoint) {
        this.shortTimeEndpoint = endpoint;
    }
}
