/**
 * 
 */
package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.aggregate.StaffAndVisitEventAggregationStrategy;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.StaffFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 25, 2016
 * 
 * 
 */
public class StaffAndVisitEventAggregationStrategyTest extends BaseTestSupport{

    StaffAndVisitEventAggregationStrategy staffAndVisitEventAggregationStrategy;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        staffAndVisitEventAggregationStrategy = new StaffAndVisitEventAggregationStrategy();
    }
    
    
    @Test
    public void test_aggreate_first_time_run_StaffFact_instance(){
        
        //input data
        Exchange oldExchange = null;
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("StaffId");
        exchange.getIn().setBody(staffFact);
        Exchange newExchange = exchange;
        
        
        //executing the method
        
        Exchange result = staffAndVisitEventAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //expectation
        assertTrue(result.getIn().getBody() instanceof List);
        
        List list = result.getIn().getBody(List.class);
        assertEquals(staffFact.getStaffId(), ((StaffFact) list.get(0)).getStaffId());
        assertEquals(1, list.size());
        
    }
    
    
    @Test
    public void test_aggreate_first_time_run_VisitEvent_instance(){
        
        //input data
        Exchange oldExchange = null;
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setStaffID("StaffId");
        visitEvent.setVisitEventDatetime(Calendar.getInstance().getTime());
        exchange.getIn().setBody(visitEvent);
        Exchange newExchange = exchange;
        
        
        //executing the method
        
        Exchange result = staffAndVisitEventAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //expectation
        assertTrue(result.getIn().getBody() instanceof List);
        
        List list = result.getIn().getBody(List.class);
        assertTrue(list.get(0) instanceof VisitEvent);
        assertEquals(visitEvent.getStaffID(), ((VisitEvent) list.get(0)).getStaffID());
        assertEquals(1, list.size());
        
    }
    
    
    @Test
    public void test_aggreate_old_exchange_is_VisitEvent_new_exchange_is_StaffFact(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        List aggregatedSofar = new ArrayList();
        
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setStaffID("StaffId");
        visitEvent.setVisitEventDatetime(Calendar.getInstance().getTime());
        aggregatedSofar.add(visitEvent);
        oldExchange.getIn().setBody(aggregatedSofar);
        
        
        
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("NewStaffId");
        exchange.getIn().setBody(staffFact);
        Exchange newExchange = exchange;
        
        
        //executing the method
        
        Exchange result =staffAndVisitEventAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //expectation
        assertTrue(result.getIn().getBody() instanceof List);
        List list = result.getIn().getBody(List.class);
        assertEquals(2, list.size());
        
        
        assertTrue(list.get(0) instanceof VisitEvent);
        assertTrue(list.get(1) instanceof StaffFact);
        
        assertEquals("NewStaffId", ((VisitEvent)list.get(0)).getStaffID());
        
    }
    
    
    @Test
    public void test_aggreate_new_exchange_is_VisitEvent(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        List aggregatedSofar = new ArrayList();
        
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setStaffID("StaffId_before_udpated");
        visitEvent.setVisitEventDatetime(Calendar.getInstance().getTime());
        aggregatedSofar.add(visitEvent);
        oldExchange.getIn().setBody(aggregatedSofar);
        
        //new exchange
        VisitEvent visitEvent1 = new VisitEvent();
        visitEvent1.setStaffID("StaffId_after_udpated"); 
        visitEvent1.setVisitEventDatetime(Calendar.getInstance().getTime());
        exchange.getIn().setBody(visitEvent1);
        Exchange newExchange = exchange;
        
        
        //executing the method
        
        Exchange result = staffAndVisitEventAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //expectation
        assertTrue(result.getIn().getBody() instanceof List);
        List list = result.getIn().getBody(List.class);
        assertEquals(2, list.size());
        
        
        assertTrue(list.get(0) instanceof VisitEvent);
        assertTrue(list.get(1) instanceof VisitEvent);
        
        assertEquals("StaffId_after_udpated", ((VisitEvent)list.get(0)).getStaffID());
        
    }


}
