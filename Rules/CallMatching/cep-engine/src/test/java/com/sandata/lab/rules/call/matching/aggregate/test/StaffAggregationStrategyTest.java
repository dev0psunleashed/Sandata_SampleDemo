/**
 * 
 */
package com.sandata.lab.rules.call.matching.aggregate.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.aggregate.StaffAggregationStrategy;
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.StaffFact;
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
public class StaffAggregationStrategyTest  extends BaseTestSupport{

    StaffAggregationStrategy staffAggregationStrategy;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        staffAggregationStrategy = new StaffAggregationStrategy();
    }
    
    
    @Test
    public void test_aggregate_first_time_run_StaffFact_instance(){
        
        //input data
        Exchange oldExchange =null;
        
        //new exchange
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("Staffact_Dnis");
        staffFact.setStaffId("StaffID");
        exchange.getIn().setBody(staffFact);
        

        //executing the method
        
        Exchange result = staffAggregationStrategy.aggregate(oldExchange, exchange);
        List resultList = (List) result.getIn().getBody();
        
        //assertions
        assertEquals("Staffact_DnisStaffID", result.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(1, resultList.size());
        assertTrue(resultList.get(0) instanceof StaffFact );
        
    }
    
    
    @Test
    public void test_aggregate_second_time_run_PatientStaffResponse_instance(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        
        //new exchange
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("Staffact_Dnis");
        staffFact.setStaffId("StaffID");
        
        List oldExchangeBody = new ArrayList();
        oldExchangeBody.add(staffFact);
        oldExchange.getIn().setBody(oldExchangeBody);
        
       
        
        
        PatientStaffResponse patientStaffResponse = new PatientStaffResponse();
        List staffList = new ArrayList<>();
        Staff staff ;
        for(int i =0;i<5;i++){
            staff = new Staff();
            staff.setStaffID("StaffID"+1);
            staffList.add(staff);
        }
        
        patientStaffResponse.setStaffList(staffList);
        PatientStaffRequest request = new PatientStaffRequest();
        request.setAni("Ani");
        request.setDnis("Dnis");
        request.setStaffId("StaffID1");
        request.setPatientId("patientID");
        
        patientStaffResponse.setPatientStaffRequest(request);
        
        //new exchange data
        exchange.getIn().setBody(patientStaffResponse);
        
        

        //executing the method
        Exchange result = staffAggregationStrategy.aggregate(oldExchange, exchange);
        List resultList = (List) result.getIn().getBody();
        
        //assertions
        assertEquals("DnisStaffID1Ani", result.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(6, resultList.size());
        assertTrue(resultList.get(0) instanceof StaffFact );
        assertEquals(State.AGG_INSERTED_FROM_RESPONSE.name(), result.getIn().getHeader("STATE"));
        
        
    }
    
    
    @Test
    public void test_aggregate_second_time_run_PatientStaffResponse_instance_stafflist_empty(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        
        //new exchange
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("Staffact_Dnis");
        staffFact.setStaffId("StaffID");
        
        List oldExchangeBody = new ArrayList();
        oldExchangeBody.add(staffFact);
        oldExchange.getIn().setBody(oldExchangeBody);
        
       
        
        
        PatientStaffResponse patientStaffResponse = new PatientStaffResponse();
        List staffList = new ArrayList<>();
        
        
        patientStaffResponse.setStaffList(staffList);
        PatientStaffRequest request = new PatientStaffRequest();
        request.setAni("Ani");
        request.setDnis("Dnis");
        request.setStaffId("StaffID1");
        request.setPatientId("patientID");
        
        patientStaffResponse.setPatientStaffRequest(request);
        
        //new exchange data
        exchange.getIn().setBody(patientStaffResponse);
        
        

        //executing the method
        Exchange result = staffAggregationStrategy.aggregate(oldExchange, exchange);
        List resultList = (List) result.getIn().getBody();
        
        //assertions
        assertEquals("DnisStaffID1Ani", result.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.NOT_MATCHED.name(), result.getIn().getHeader("STATE"));
        
        
    }
    
    @Test
    public void test_aggregate_second_time_run_PatientStaffResponse_instance_isClientEntered_true(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        
        //new exchange
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("Staffact_Dnis");
        staffFact.setStaffId("StaffID");
        
        List oldExchangeBody = new ArrayList();
        oldExchangeBody.add(staffFact);
        oldExchange.getIn().setBody(oldExchangeBody);
        
       
        
        
        PatientStaffResponse patientStaffResponse = new PatientStaffResponse();
        List staffList = new ArrayList<>();
        
        
        patientStaffResponse.setStaffList(staffList);
        PatientStaffRequest request = new PatientStaffRequest();
        request.setAni("Ani");
        request.setDnis("Dnis");
        request.setStaffId("StaffID1");
        request.setPatientId("patientID");
        
        request.setClientEntered(true);
        patientStaffResponse.setPatientStaffRequest(request);
        
        //new exchange data
        exchange.getIn().setBody(patientStaffResponse);
        
        

        //executing the method
        Exchange result = staffAggregationStrategy.aggregate(oldExchange, exchange);
        List resultList = (List) result.getIn().getBody();
        
        //assertions
        assertEquals("DnisStaffID1patientID", result.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.NOT_MATCHED.name(), result.getIn().getHeader("STATE"));
        
    }
    
    
    
    
    @Test
    public void test_aggregate_second_time_run_array_list_instance(){
        
        //input data
        Exchange oldExchange = new DefaultExchange(context);
        
        //new exchange
        StaffFact staffFact = new StaffFact();
        staffFact.setDnis("Staffact_Dnis");
        staffFact.setStaffId("StaffID");
        
        List oldExchangeBody = new ArrayList();
        oldExchangeBody.add(staffFact);
        oldExchange.getIn().setBody(oldExchangeBody);
        
 
        
        //new exchange data
        exchange.getIn().setHeader("staffId", "header_staffID");
        exchange.getIn().setHeader("dnis", "header_dnis");
        exchange.getIn().setBody(oldExchangeBody);
        
        

        //executing the method
        Exchange result = staffAggregationStrategy.aggregate(oldExchange, exchange);
        List resultList = (List) result.getIn().getBody();
        
        //assertions
        assertEquals("header_dnisheader_staffID", result.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.AGG_INSERTED_FROM_RESPONSE, result.getIn().getHeader("STATE"));
        
    }
    

}
