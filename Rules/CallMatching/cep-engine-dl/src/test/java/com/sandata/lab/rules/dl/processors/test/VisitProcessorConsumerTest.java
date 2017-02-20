/**
 * 
 */
package com.sandata.lab.rules.dl.processors.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sandata.lab.rules.dl.processors.VisitProcessorConsumerVisitEventStaffId;
import org.junit.Assert;
import org.junit.Test;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 27, 2016
 * 
 * 
 */
public class VisitProcessorConsumerTest extends BaseTestSupport {

    VisitProcessorConsumerVisitEventStaffId visitProcessorConsumerVisitEventStaffId;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        visitProcessorConsumerVisitEventStaffId = new VisitProcessorConsumerVisitEventStaffId();
    }
    
    
    @Test
    public void test_process_body_is_Visit_instance_ScheduleEventSK_not_null() throws Exception{
        
        //data input
        Visit visit = new Visit();
        visit.setVisitSK(new BigInteger("100"));
        visit.setScheduleEventSK(new BigInteger("200"));
        
        exchange.getIn().setBody(visit);
        
        //executing method
        visitProcessorConsumerVisitEventStaffId.process(exchange);
        
        
        //expectations
        Assert.assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        VisitFact visitFact = (VisitFact) exchange.getIn().getBody();
        
        Assert.assertEquals(State.SCHEDULED, visitFact.getState());
        
        Assert.assertEquals(1, visitFact.getVisit().getVisitEvent().size());
        
    }
    
    
    @Test
    public void test_process_body_is_Visit_instance_ScheduleEventSK_is_null() throws Exception{
        
        //VisitChild
        
        //data input
        VisitChild visit = new VisitChild();
        visit.setVisitSK(new BigInteger("100"));
        List<VisitEvent> events = new ArrayList<>();
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setAutomaticNumberIdentification("AutoNumID");
        visitEvent.setDialedNumberIdentificationService("DialNumberID");;
        events.add(visitEvent);
        
        visit.setVisitEvent(events);
        
        exchange.getIn().setBody(visit);
        
        //executing method
        visitProcessorConsumerVisitEventStaffId.process(exchange);
        
        
        //expectations
        Assert.assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        VisitFact visitFact = (VisitFact) exchange.getIn().getBody();
        
        Assert.assertEquals(State.UNPLANNED, visitFact.getState());
        
        Assert.assertEquals(1, visitFact.getVisit().getVisitEvent().size());
        
        Assert.assertEquals("DialNumberID", exchange.getIn().getHeader("DNIS"));
        
    }
    
    @Test
    public void test_process_body_is_Visit_instance_ScheduleEventSK_is_not_null() throws Exception{
        
        //VisitChild
        
        //data input
        VisitChild visit = new VisitChild();
        visit.setScheduleEventSK(new BigInteger("200"));
        visit.setVisitSK(new BigInteger("100"));
        visit.setVisitActualStartTimestamp(Calendar.getInstance().getTime());
        
        List<VisitEvent> events = new ArrayList<>();
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setAutomaticNumberIdentification("AutoNumID");
        visitEvent.setDialedNumberIdentificationService("DialNumberID");;
        events.add(visitEvent);
        
        visit.setVisitEvent(events);
        
        exchange.getIn().setBody(visit);
        
        //executing method
        visitProcessorConsumerVisitEventStaffId.process(exchange);
        
        
        //expectations
        Assert.assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        VisitFact visitFact = (VisitFact) exchange.getIn().getBody();
        
        Assert.assertEquals(State.NOT_MATCHED, visitFact.getState());
        
        Assert.assertEquals(1, visitFact.getVisit().getVisitEvent().size());
        
        Assert.assertEquals("DialNumberID", exchange.getIn().getHeader("DNIS"));
        
    }
    
    
    
    
    class VisitChild extends Visit{
        
        public void setVisitEvent(List<VisitEvent> visitEvents){
            this.visitEvent = visitEvents;
        }
        
    }

}
