/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.ArrayList;

import org.junit.Test;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.processors.ProcessInvalidStaffAndUnplannedVisit;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 19, 2016
 * 
 * 
 */
public class ProcessInvalidStaffAndUnplannedVisitTest  extends BaseTestSupport{

    ProcessInvalidStaffAndUnplannedVisit processInvalidStaffAndUnplannedVisit;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        processInvalidStaffAndUnplannedVisit = new ProcessInvalidStaffAndUnplannedVisit();
    }
    
    @Test(expected=Exception.class)
    public void test_process_state_INVALID_STAFF_add_visitEventFact_null() throws Exception{
        
        ArrayList lsit = new ArrayList<>();
        VisitFact visitFact = new VisitFact();
        
        lsit.add(visitFact);
        
        exchange.getIn().setBody(lsit);
        exchange.getIn().setHeader("STATE", State.INVALID_STAFF);
        processInvalidStaffAndUnplannedVisit.process(exchange);
        
        //expectation throw exception
        
    }
    
    
    @Test
    public void test_process_state_INVALID_STAFF_with_2_visitFact() throws Exception{
        
        ArrayList list = new ArrayList<>();
        //first empty visit fact
        list.add(new VisitFact());
        
        //the second visit fact with data
        VisitFact visitFact = new VisitFact();
        visitFact.setAni("the_second_visitfact_ani");
        visitFact.setDnis("the_second_visitfact_Dnis");
        VisitEventFact callCallIn = new VisitEventFact();
        callCallIn.setVisit(new VisitEvent());
        visitFact.setCallCallIn(callCallIn);
        
        //add to list
        list.add(visitFact);
        
        
        //executing the method
        exchange.getIn().setBody(list);
        exchange.getIn().setHeader("STATE", State.INVALID_STAFF);
        processInvalidStaffAndUnplannedVisit.process(exchange);
        
        //assertions
        assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        VisitFact resultVisitFact = exchange.getIn().getBody(VisitFact.class);
        assertEquals("the_second_visitfact_ani", resultVisitFact.getAni());
        assertEquals("the_second_visitfact_Dnis", resultVisitFact.getDnis());
       
        
    }
    
    
    
    
    
    @Test
    public void test_process_state_INVALID_STAFF_with_visitEventFact_timze_zone_not_set() throws Exception{
        
        ArrayList list = new ArrayList<>();
        VisitFact visitFact = new VisitFact();
        
        VisitEventFact callCallIn = new VisitEventFact();
        
        callCallIn.setVisit(new VisitEvent());
        
        visitFact.setCallCallIn(callCallIn);
        
        
        list.add(visitFact);
        
        exchange.getIn().setBody(list);
        exchange.getIn().setHeader("STATE", State.INVALID_STAFF);
        processInvalidStaffAndUnplannedVisit.process(exchange);
        
        //assertion
        assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        //checking timezone
        VisitFact resultVisitFact = exchange.getIn().getBody(VisitFact.class);
        VisitEventFact visitEventFact = resultVisitFact.getCallCallIn();
        assertEquals("US/Eastern", visitEventFact.getVisit().getTimezoneName());
        assertTrue(visitEventFact.getVisit().isCallInIndicator());
        
    }
    
    
    
    @Test
    public void test_process_state_NO_SCHEDULES_with_2_visitFact() throws Exception{
        
        ArrayList list = new ArrayList<>();
        //first empty visit fact
        VisitFact visitFact1 = new VisitFact();
        visitFact1.setAni("the_first_visitfact_ani");
        visitFact1.setDnis("the_first_visitfact_Dnis");
        list.add(visitFact1);
        
        //the second visit fact with data
        VisitFact visitFact = new VisitFact();
        visitFact.setAni("the_second_visitfact_ani");
        visitFact.setDnis("the_second_visitfact_Dnis");
        VisitEventFact callCallIn = new VisitEventFact();
        callCallIn.setVisit(new VisitEvent());
        visitFact.setCallCallIn(callCallIn);
        
        //add to list
        list.add(visitFact);
        
        
        //executing the method
        exchange.getIn().setBody(list);
        exchange.getIn().setHeader("STATE",  State.NO_SCHEDULES);
        processInvalidStaffAndUnplannedVisit.process(exchange);
        
        //assertions
        assertTrue(exchange.getIn().getBody() instanceof VisitFact);
        VisitFact resultVisitFact = exchange.getIn().getBody(VisitFact.class);
        assertEquals("the_first_visitfact_ani", resultVisitFact.getAni());
        assertEquals("the_first_visitfact_Dnis", resultVisitFact.getDnis());
       
        
    }
    

}
