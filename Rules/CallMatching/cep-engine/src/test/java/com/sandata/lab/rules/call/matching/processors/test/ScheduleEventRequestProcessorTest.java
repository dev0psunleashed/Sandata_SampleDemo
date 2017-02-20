/**
 * 
 */
package com.sandata.lab.rules.call.matching.processors.test;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.sandata.lab.data.model.request.visit.ScheduleEventRequest;
import com.sandata.lab.rules.call.matching.processors.ScheduleEventRequestProcessor;
import com.sandata.lab.rules.call.model.VisitEventFact;

/**
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 26, 2016
 * 
 * 
 */
public class ScheduleEventRequestProcessorTest extends BaseTestSupport{

    ScheduleEventRequestProcessor scheduleEventRequestProcessor;
    
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        scheduleEventRequestProcessor = new ScheduleEventRequestProcessor();
    }
    
    
    @Test
    public void test_process_body_is_VisitEventFact_instance() throws Exception{
        
        //input data
        VisitEventFact visitEventFact = new VisitEventFact();
        visitEventFact.setAni("Ani");
        visitEventFact.setDnis("Dnis");
        visitEventFact.setStaffID("StaffId");
        visitEventFact.setPatientID("patientID");
        Calendar cal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        visitEventFact.setCallInTime(cal.getTime());
        
        cal.add(Calendar.HOUR, -24);
        Date expectedFromDate  = cal.getTime();
        toCal.add(Calendar.HOUR, 24);
        
        Date expectedToDate = toCal.getTime();
        
        
        exchange.getIn().setBody(visitEventFact);
        
        //executing the method
        scheduleEventRequestProcessor.process(exchange);
        
        
        //assertions 
        assertEquals("Ani", exchange.getIn().getHeader("ani"));
        ScheduleEventRequest scheduleEventRequest = exchange.getIn().getBody(ScheduleEventRequest.class); 
        assertEquals("StaffId", scheduleEventRequest.getStaffId());
        assertEquals("Ani", scheduleEventRequest.getAni());
        assertEquals("Dnis", scheduleEventRequest.getDnis());
        
        //compare from date and to date
        assertEquals(expectedFromDate, scheduleEventRequest.getFromDate());
        assertEquals(expectedToDate, scheduleEventRequest.getToDate());
       
    }
    
    
    @Test
    public void test_process_body_is_VisitEventFact_patient_id_start_with_0() throws Exception{
        
        //input data
        VisitEventFact visitEventFact = new VisitEventFact();
        visitEventFact.setPatientID("00003434");
        visitEventFact.setAni("Ani");
        visitEventFact.setDnis("Dnis");
        visitEventFact.setStaffID("StaffId");
        Calendar cal = Calendar.getInstance();
        visitEventFact.setCallInTime(cal.getTime());
        
        
        exchange.getIn().setBody(visitEventFact);
        
        //executing the method
        scheduleEventRequestProcessor.process(exchange);
        
        
        //assertions 
        ScheduleEventRequest scheduleEventRequest = exchange.getIn().getBody(ScheduleEventRequest.class); 
        assertEquals("3434", scheduleEventRequest.getPatientId());
        
       
    }

    
  
    
}
