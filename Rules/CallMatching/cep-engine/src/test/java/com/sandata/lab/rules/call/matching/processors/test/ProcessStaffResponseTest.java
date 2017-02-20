/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import org.junit.Test;

import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.processors.ProcessStaffResponse;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 27, 2016
 * 
 * 
 */
public class ProcessStaffResponseTest  extends BaseTestSupport{

    ProcessStaffResponse processStaffResponse;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        processStaffResponse = new ProcessStaffResponse();
    }
    
    
    @Test
    public void test_process_body_is_not_PatientStaffResponse_instance() throws Exception{
        //data input 
        Integer input = new Integer(100);
        exchange.getIn().setBody(input);
        
        //executing
        processStaffResponse.process(exchange);
        
        //asserts - expectation - the exchange is not updated/modified
        assertNull(exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    
    
    @Test
    public void test_process_body_is_PatientStaffResponse() throws Exception{
        //data input 
        PatientStaffResponse reponse = new PatientStaffResponse();
        PatientStaffRequest request = new PatientStaffRequest();
        request.setAni("TestAni");
        request.setDnis("TestDnis");
        request.setPatientId("TestPatientId");
        request.setStaffId("TestStaffId");

        reponse.setPatientStaffRequest(request);
        
        exchange.getIn().setBody(reponse);
        
        //executing
        processStaffResponse.process(exchange);
        
        //asserts - expectation - the exchange is not updated/modified
        assertEquals("TestDnisTestStaffIdTestAni",exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    
    
    
    @Test
    public void test_process_body_is_PatientStaffResponse_ClientEntered_true() throws Exception{
        //data input 
        PatientStaffResponse reponse = new PatientStaffResponse();
        PatientStaffRequest request = new PatientStaffRequest();
        request.setAni("TestAni");
        request.setDnis("TestDnis");
        request.setPatientId("TestPatientId");
        request.setStaffId("TestStaffId");
        
        request.setClientEntered(true);

        reponse.setPatientStaffRequest(request);
        
        exchange.getIn().setBody(reponse);
        
        //executing
        processStaffResponse.process(exchange);
        
        //asserts - expectation - the exchange is not updated/modified
        assertEquals("TestDnisTestStaffIdTestPatientId",exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    

}
