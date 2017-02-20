/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.processors.ProcessResubmission;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 25, 2016
 * 
 * 
 */
public class ProcessResubmissionTest extends BaseTestSupport  {

    ProcessResubmission processResubmission;
    
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        processResubmission = new ProcessResubmission();
    }
    
    @Test
    public void test_process_not_list_instance() throws Exception{
        
        //test data
        VisitFact visitFact = new VisitFact();
        exchange.getIn().setBody(visitFact);
        processResubmission.process(exchange);
        
        //expectation
        assertNull(exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        
    }
    
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void test_process_not_list_empty_IndexOutOfBoundsException() throws Exception{
        
        //test data
        //VisitFact visitFact = new VisitFact();
        exchange.getIn().setBody(new ArrayList());
        processResubmission.process(exchange);
        
        //expectation
       //exception IndexOutOfBoundsException
        
    }
    
    @Test
    public void test_process_not_list_with_data() throws Exception{
        
        //test data
        VisitFact visitFact = new VisitFact();
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setPatientID("PatientID");
        visitFact.setAni("Ani");
        visitFact.setDnis("Dnis");
        visitFact.setStaffId("StaffID");
        visitFact.setCallCallIn(new VisitEventFact(visitEvent));
        
        List inputData = new ArrayList<>();
        inputData.add(visitFact);
        exchange.getIn().setBody(inputData);
        processResubmission.process(exchange);
        
        //expectation
        
        assertTrue( exchange.getIn().getBody() instanceof VisitEvent);
        
        VisitEvent visitEventResult = (VisitEvent) exchange.getIn().getBody() ;
        assertEquals("PatientID", visitEventResult.getPatientID());
    }

    
    @Test
    public void test_process_visitfact_isClientEntered_false() throws Exception{
        
        //test data
        VisitFact visitFact = new VisitFact();
        visitFact.setClientEntered(false);
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setPatientID("PatientID");
        visitFact.setAni("Ani");
        visitFact.setDnis("Dnis");
        visitFact.setStaffId("StaffID");
        visitFact.setCallCallIn(new VisitEventFact(visitEvent));
        
        List inputData = new ArrayList<>();
        inputData.add(visitFact);
        exchange.getIn().setBody(inputData);
        processResubmission.process(exchange);
        
        //expectation
       
        assertEquals("DnisStaffIDAni", exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    
    
    @Test
    public void test_process_visitfact_isClientEntered_true() throws Exception{
        
        //test data
        VisitFact visitFact = new VisitFact();
        visitFact.setClientEntered(true);
        VisitEvent visitEvent = new VisitEvent();
        visitEvent.setPatientID("PatientID");
        visitFact.setAni("Ani");
        visitFact.setDnis("Dnis");
        visitFact.setStaffId("StaffID");
        visitFact.setCallCallIn(new VisitEventFact(visitEvent));
        
        List inputData = new ArrayList<>();
        inputData.add(visitFact);
        exchange.getIn().setBody(inputData);
        processResubmission.process(exchange);
        
        //expectation
       
        assertEquals("DnisStaffIDPatientID", exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    
    
}
