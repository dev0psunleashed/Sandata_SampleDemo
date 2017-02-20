/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.data.model.constants.filter.Filter;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.rules.call.matching.processors.CreateStaffRequestFromVisitFact;
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
public class CreateStaffRequestFromVisitFactTest  extends BaseTestSupport{

    CreateStaffRequestFromVisitFact createStaffRequestFromVisitFact;
    
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        // TODO Auto-generated method stub
        createStaffRequestFromVisitFact = new CreateStaffRequestFromVisitFact();
    }
    
    
    @Test
    public void test_process_exchange_is_ArrayList_instance() throws Exception{
        
        VisitFact visitFact = new VisitFact();
        visitFact.setDnis("DNIS1");
        visitFact.setStaffId("StaffID");
        visitFact.setPatientId("PatientID");
        visitFact.setClientEntered(true);
        visitFact.setAni("Ani");
        
        //Input an empty ArrayList
        List list = new ArrayList();
        list.add(visitFact);
        
        exchange.getIn().setBody(list);
        createStaffRequestFromVisitFact.process(exchange);
        
        //expectation 
        assertTrue(exchange.getIn().getBody() instanceof PatientStaffRequest);
        
        PatientStaffRequest request = exchange.getIn().getBody(PatientStaffRequest.class);
        assertEquals(visitFact.getDnis(), request.getDnis());
        assertEquals(visitFact.getStaffId(), request.getStaffId());
        assertEquals(visitFact.getPatientId(), request.getPatientId());
        assertEquals(visitFact.getAni(), request.getAni());
        assertEquals(visitFact.isClientEntered(), request.isClientEntered());
        assertEquals(Filter.OPTIONS.CONTAINS, request.getFilter());
        
    }
    
    
    @Test
    public void test_process_exchange_is_ArrayList_instance_list_empty() throws Exception{
        
        //Input an empty ArrayList
        exchange.getIn().setBody(new ArrayList<>());
        createStaffRequestFromVisitFact.process(exchange);
        
        //expectation 
        assertTrue(exchange.getIn().getBody() instanceof PatientStaffRequest);
        
        //failed , index of out bound exception because of empy list
    }
    
    
    
    @Test
    public void test_process_exchange_is_VisitFact_instance() throws Exception{
        
        VisitFact visitFact = new VisitFact();
        visitFact.setDnis("DNIS1");
        visitFact.setStaffId("StaffID");
        visitFact.setPatientId("PatientID");
        visitFact.setClientEntered(true);
        visitFact.setAni("Ani");
        
        
        exchange.getIn().setBody(visitFact);
        createStaffRequestFromVisitFact.process(exchange);
        
        //expectation 
        assertTrue(exchange.getIn().getBody() instanceof PatientStaffRequest);
        
        PatientStaffRequest request = exchange.getIn().getBody(PatientStaffRequest.class);
        assertEquals(visitFact.getDnis(), request.getDnis());
        assertEquals(visitFact.getStaffId(), request.getStaffId());
        assertEquals(visitFact.getPatientId(), request.getPatientId());
        assertEquals(visitFact.getAni(), request.getAni());
        assertEquals(visitFact.isClientEntered(), request.isClientEntered());
        assertEquals(Filter.OPTIONS.CONTAINS, request.getFilter());
        
    }
    

}
