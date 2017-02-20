package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.exceptions.CepException;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;


/**
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 21, 2016
 * 
 *
 */
public class PrepForAggregationTest extends BaseTestSupport{


    com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation prepForAggregation;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        prepForAggregation = new com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation();
    }
    
    @Test
    public void test_process_unexpected_onject() throws Exception{
        
        //test data
        exchange.getIn().setBody(new Integer(22));
        
        //running test
        prepForAggregation.process(exchange);
        
        //assertions
        assertNull(exchange.getIn().getBody());
        assertEquals(State.INVALID.name(), exchange.getIn().getHeader("STATE"));
        
    }
   
   
    @Test
    public void test_process_body_is_VisitEventFact_instance() throws Exception{
        
        //test data
        VisitEventFact visitEventFact = buildVisitEventFact(); 
        exchange.getIn().setBody(visitEventFact);
        
        //running test
        prepForAggregation.process(exchange);
        
        //assertions
       
        VisitFact visitFact = (VisitFact) exchange.getIn().getBody(List.class).get(0);
        assertEquals(State.AGG_WAITING_FOR_RESPONSE, visitFact.getState());
        assertEquals(visitEventFact.getDnis()+visitEventFact.getStaffID()+visitEventFact.getPatientID(), exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.AGG_WAITING_FOR_RESPONSE.name(), exchange.getIn().getHeader("STATE"));
        
        //setClientEntered = true
        assertTrue(visitFact.isClientEntered());
        
        
    }
    
    
    @Test
    public void test_process_body_is_VisitEventFact_instance_patient_is_not_set() throws Exception{
        
        //test data
        VisitEventFact visitEventFact = buildVisitEventFact(); 
        visitEventFact.setPatientID(null);
        exchange.getIn().setBody(visitEventFact);
        
        //running test
        prepForAggregation.process(exchange);
        
        //assertions
       
        VisitFact visitFact = (VisitFact) exchange.getIn().getBody(List.class).get(0);
        assertEquals(State.AGG_WAITING_FOR_RESPONSE, visitFact.getState());
        assertEquals(visitEventFact.getDnis()+visitEventFact.getStaffID()+visitEventFact.getAni(), exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.AGG_WAITING_FOR_RESPONSE.name(), exchange.getIn().getHeader("STATE"));
        
        //setClientEntered = true
        assertFalse(visitFact.isClientEntered());
        
        
    }
    
    
    @Test
    public void test_process_body_is_VisitFact_instance_general_case() throws Exception{
        
        //test data
        VisitFact visitFact = buildVisitFact(); 
        exchange.getIn().setBody(visitFact);
        
        //running test
        prepForAggregation.process(exchange);
        
        //assertions
       
        VisitFact visitFactResult = (VisitFact) exchange.getIn().getBody(List.class).get(0);
        assertEquals(visitFactResult.getDnis()+visitFactResult.getStaffId()+visitFactResult.getPatientId(), exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.AGG_INSERTED_FROM_RESPONSE.name(), exchange.getIn().getHeader("STATE"));
    }
    
    
    
    private VisitFact buildVisitFact(){
        VisitFact visitFact = new VisitFact();

        visitFact.setPatientId("pateintid");
        visitFact.setAni("Ani");
        visitFact.setStaffId("staffid");
        visitFact.setDnis("dnis");
        visitFact.setClientEntered(true);
        return visitFact;
    }
    
    
    private VisitEventFact buildVisitEventFact(){
        
        VisitEventFact visitEventFact = new VisitEventFact();
        visitEventFact.setAni("Ani");
        visitEventFact.setDnis("Dnis");
        visitEventFact.setStaffID("StaffId");
        visitEventFact.setPatientID("PatientID");
        
        return visitEventFact;
    }
   
}
