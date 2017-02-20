/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt;
import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.matching.processors.ScheduleEventResponseProcessor;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 26, 2016
 * 
 * 
 */
public class ScheduleEventResponseProcessorTest extends BaseTestSupport{

    ScheduleEventResponseProcessor scheduleEventResponseProcessor;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        scheduleEventResponseProcessor = new ScheduleEventResponseProcessor();
    }
    
    
    @Test
    public void test_process_ScheduleEventsExt_not_set() throws Exception{

        //data test
        ScheduleEventResponse response = createScheduleEventResponse_general();
        exchange.getIn().setBody(response);
        
        //executing the mothod
        scheduleEventResponseProcessor.process(exchange);
        
        
        //assertions
        assertTrue(exchange.getIn().getBody() instanceof List);
        assertTrue(((List)exchange.getIn().getBody()).get(0) instanceof VisitFact);
        assertEquals("TestDnisTestStaffIDTestpatientID", exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        assertEquals(State.INVALID_STAFF, ((VisitFact)((List)exchange.getIn().getBody()).get(0)).getState());
        
        
    }
    
    
    
    @Test
    public void test_process_ScheduleEventsExt_is_set_and_ScheduleEventSK_is_null() throws Exception{

        //data test
        ScheduleEventResponse response = createScheduleEventResponse_general();
        updateScheduleEventExtToReponse(response);
        
        exchange.getIn().setBody(response);
        
        //executing the mothod
        scheduleEventResponseProcessor.process(exchange);
        
        
        //assertions
        assertTrue(exchange.getIn().getBody() instanceof List);
        assertTrue(((List)exchange.getIn().getBody()).get(0) instanceof VisitFact);
        assertEquals("TestDnisTestStaffIDTestpatientID", exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        
        //
        assertEquals(5, ((List)exchange.getIn().getBody()).size());
        assertEquals(State.NO_SCHEDULES, ((VisitFact)((List)exchange.getIn().getBody()).get(0)).getState());
        
        
    }
    
    
    @Test
    public void test_process_ScheduleEventsExt_is_set_and_ScheduleEventSK_is_not_null() throws Exception{

        //data test
        ScheduleEventResponse response = createScheduleEventResponse_general();
        updateScheduleEventExtToReponse_and_ScheduleEventSK(response);
        
        exchange.getIn().setBody(response);
        
        //executing the mothod
        scheduleEventResponseProcessor.process(exchange);
        
        
        //assertions
        assertTrue(exchange.getIn().getBody() instanceof List);
        assertTrue(((List)exchange.getIn().getBody()).get(0) instanceof VisitFact);
        assertEquals("TestDnisTestStaffIDTestpatientID", exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));
        
        //
        assertEquals(2, ((List)exchange.getIn().getBody()).size());
        assertEquals(State.NO_SCHEDULES, ((VisitFact)((List)exchange.getIn().getBody()).get(0)).getState());
        
        
    }
    
    
    
   private void updateScheduleEventExtToReponse_and_ScheduleEventSK(final ScheduleEventResponse response ){
        
        List<ScheduleEventExt> scheduleEventsExtList = new ArrayList<>();
        com.sandata.lab.data.model.dl.model.extended.ScheduleEventExt tmp ;
        for (int i = 0; i < 5; i++) {
            tmp = new ScheduleEventExt();
            
            if(i%2 == 0){
                tmp.setScheduleEventSK(new BigInteger("100"));
                
            }
            
            scheduleEventsExtList.add(tmp);
        }
        
        
        
        response.setScheduleEventsExt(scheduleEventsExtList);
        
    }
    
    
    
    private void updateScheduleEventExtToReponse(final ScheduleEventResponse response ){
        
        List<ScheduleEventExt> scheduleEventsExtList = new ArrayList<>();
        ScheduleEventExt tmp ;
        for (int i = 0; i < 5; i++) {
            tmp = new ScheduleEventExt();
            
            
            scheduleEventsExtList.add(tmp);
        }
        
        
        
        response.setScheduleEventsExt(scheduleEventsExtList);
        
    }
    
    
    private ScheduleEventResponse createScheduleEventResponse_general(){
        
        ScheduleEventResponse result = new ScheduleEventResponse();
        
        ScheduleEventRequest request = new ScheduleEventRequest();
        request.setAni("TestAni");
        request.setDnis("TestDnis");
        request.setStaffId("TestStaffID");
        request.setPatientId("TestpatientID");
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -2);
        request.setFromDate(cal.getTime());
        
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.HOUR, -2);
        request.setToDate(cal1.getTime());
        
        
        result.setScheduleEventRequest(request);
        return result;
    }

}
