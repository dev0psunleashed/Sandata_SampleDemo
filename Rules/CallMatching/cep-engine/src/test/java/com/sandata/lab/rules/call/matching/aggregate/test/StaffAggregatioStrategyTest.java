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
import com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport;
import com.sandata.lab.rules.call.model.OraStaffFact;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.State;


/**
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 20, 2016
 * 
 *
 */
public class StaffAggregatioStrategyTest extends BaseTestSupport{


    com.sandata.lab.rules.call.matching.aggregate.StaffAggregationStrategy staffAggregationStrategy;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        staffAggregationStrategy = new com.sandata.lab.rules.call.matching.aggregate.StaffAggregationStrategy();
    }
    
    
    @Test
    public void test_aggregate_StaffFact_instance_first_time_run(){
        //first time run, old exchange is null
        Exchange oldExchange = null;
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("Staffid");
        staffFact.setDnis("dnis");
       
        exchange.getIn().setBody(staffFact);
        Exchange newExchange = exchange;
        
        //executing method
        Exchange resultExchange = staffAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(1,actualListSize );
        assertEquals(staffFact.getDnis()+staffFact.getStaffId(), resultExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
    }
    
    
    
    @Test
    public void test_aggregate_StaffFact_instance_the_second_time_run(){
       
        //first time run, old exchange is null
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("Staffid");
        staffFact.setDnis("dnis");
        List list = new ArrayList<>();
        list.add(staffFact);
        
        exchange.getIn().setBody(list);
        Exchange oldExchange = exchange;
        
        Exchange newExchange = new DefaultExchange(context);
        newExchange.getIn().setBody(staffFact);
        
        //executing method
        Exchange resultExchange = staffAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(2,actualListSize );
    }
    
    @Test
    public void test_aggregate_PatientStaffResponse_instance_request_ClientEntered_is_true(){
       
        //first time run, old exchange is null
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("Staffid");
        staffFact.setDnis("dnis");
        List list = new ArrayList<>();
        list.add(staffFact);
        
        exchange.getIn().setBody(list);
        Exchange oldExchange = exchange;
        
        Exchange newExchange = new DefaultExchange(context);
        PatientStaffResponse patientStaffResponse = buildPatientStaffResponse();
        newExchange.getIn().setBody(patientStaffResponse);
        
        //executing method
        Exchange resultExchange = staffAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(2,actualListSize );
        assertEquals(State.AGG_INSERTED_FROM_RESPONSE.name(), resultExchange.getIn().getHeader("STATE"));
        
        OraStaffFact oracStaff = (OraStaffFact) resultExchange.getIn().getBody(List.class).get(1);
        assertEquals("StaffID", oracStaff.getStaffId());
        assertEquals("Dnis", oracStaff.getDnis());
       
    }
    
    
    @Test
    public void test_aggregate_PatientStaffResponse_instance_request_Stafflist_empty(){
       
        //first time run, old exchange is null
        StaffFact staffFact = new StaffFact();
        staffFact.setStaffId("Staffid");
        staffFact.setDnis("dnis");
        List list = new ArrayList<>();
        list.add(staffFact);
        
        exchange.getIn().setBody(list);
        Exchange oldExchange = exchange;
        
        Exchange newExchange = new DefaultExchange(context);
        PatientStaffResponse patientStaffResponse = buildPatientStaffResponse();
        patientStaffResponse.setStaffList(new ArrayList<Staff>());
        newExchange.getIn().setBody(patientStaffResponse);
        
        //executing method
        Exchange resultExchange = staffAggregationStrategy.aggregate(oldExchange, newExchange);
        
        //assertions
        int actualListSize = resultExchange.getIn().getBody(List.class).size();
        assertEquals(2,actualListSize );
        assertEquals(State.NOT_MATCHED.name(), resultExchange.getIn().getHeader("STATE"));
    }
    
    
    
    private PatientStaffResponse buildPatientStaffResponse(){
        
        PatientStaffResponse patientStaffResponse =  new PatientStaffResponse();
        List<Staff> staffs = new ArrayList<>();
        Staff temp = new Staff();
        temp.setStaffID("StaffID");
        staffs.add(temp);
        patientStaffResponse.setStaffList(staffs);
        
        
        PatientStaffRequest request = new PatientStaffRequest();
        request.setStaffId("StaffId");
        request.setDnis("Dnis");
        request.setPatientId("patientid");
        patientStaffResponse.setPatientStaffRequest(request);
        
        
        //
        
        return patientStaffResponse;
    }
    
    
}
