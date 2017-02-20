/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.rules.call.matching.processors.VisitToStaffRequestAndAggregation;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.VisitFact;
import com.sandata.lab.rules.call.model.State;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 25, 2016
 * 
 * 
 */
public class VisitToStaffRequestAndAggregationTest  extends BaseTestSupport{

    
    VisitToStaffRequestAndAggregation visitToStaffRequestAndAggregation;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        visitToStaffRequestAndAggregation = new VisitToStaffRequestAndAggregation();
    }
    
    
    @Test(expected=Exception.class)
    public void test_process_empty_list() throws Exception{
        
        //data test
        List list = new ArrayList();
        exchange.getIn().setBody(list);
        //executing the method
        visitToStaffRequestAndAggregation.process(exchange);
        
        //expectation , throw exception
        
    }
    
    
    @Test
    public void test_process_list_instance() throws Exception{
        
        //data test
        List list = new ArrayList();
        VisitFact visitFact = new VisitFact();
        visitFact.setStaffId("StaffID");
        visitFact.setDnis("Dnis");
        list.add(visitFact);
        
        exchange.getIn().setBody(list);
        //executing the method
        visitToStaffRequestAndAggregation.process(exchange);
        
        //expectation
        assertTrue(exchange.getIn().getBody() instanceof StaffFact);
        StaffFact staffFact = (com.sandata.lab.rules.call.model.StaffFact) exchange.getIn().getBody();
        assertEquals("StaffID", staffFact.getStaffId());
        assertEquals("Dnis", staffFact.getDnis());
        assertEquals(State.LOADED, staffFact.getState());
        
    }
    
    
    @Test
    public void test_process_VisitFact_instance() throws Exception{
        
        //data test
        VisitFact visitFact = new VisitFact();
        visitFact.setStaffId("StaffID");
        visitFact.setDnis("Dnis");
        
        exchange.getIn().setBody(visitFact);
        //executing the method
        visitToStaffRequestAndAggregation.process(exchange);
        
        //expectation
        assertTrue(exchange.getIn().getBody() instanceof StaffFact);
        StaffFact staffFact = (com.sandata.lab.rules.call.model.StaffFact) exchange.getIn().getBody();
        assertEquals("StaffID", staffFact.getStaffId());
        assertEquals("Dnis", staffFact.getDnis());
        assertEquals(State.LOADED, staffFact.getState());
        
    }
    
    
    
    
    

}
