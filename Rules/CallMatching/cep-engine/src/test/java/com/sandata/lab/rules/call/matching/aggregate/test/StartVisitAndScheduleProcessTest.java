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
 * @date Apr 20, 2016
 * 
 *
 */
public class StartVisitAndScheduleProcessTest extends BaseTestSupport{


    com.sandata.lab.rules.call.matching.aggregate.StartVisitAndScheduleProcess startVisitAndScheduleProcess;
    /* (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.processors.test.BaseTestSupport#onSetup()
     */
    @Override
    protected void onSetup() {
        startVisitAndScheduleProcess = new com.sandata.lab.rules.call.matching.aggregate.StartVisitAndScheduleProcess();
    }
    
    
   @Test(expected=NullPointerException.class)
   public void test_processMatches_body_with_2_empty_VisitFacts() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     VisitFact vistiFact1 = new VisitFact();
     VisitFact vistiFact2 = new VisitFact();
     list.add(vistiFact1);
     list.add(vistiFact2);
     exchange.getIn().setBody(list);
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     //expectation , nullpointer exception , because, there's no check null before processing
     //@Test(expected=NullPointerException.class)
   }
   
    
   @Test
   public void test_processMatches_body_with_1_VisitFacts_and_state_is_NO_SCHEDULES() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     list.add(buildVisitFact(1,State.NO_SCHEDULES));
     exchange.getIn().setBody(list);
     //header  
     exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "Corrected_staff_id");
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     
     //assertion
     assertEquals(State.NO_SCHEDULES, exchange.getIn().getHeader("STATE"));
     
   }
   
   
   @Test
   public void test_processMatches_body_with_1_VisitFacts_and_state_is_INVALID_STAFF() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     list.add(buildVisitFact(1,State.INVALID_STAFF));
     exchange.getIn().setBody(list);
     //header  
     exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "Corrected_staff_id");
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     
     //assertion
     assertEquals(State.INVALID_STAFF, exchange.getIn().getHeader("STATE"));
     
   }
   
   
   @Test
   public void test_processMatches_body_with_2_VisitFacts_and_state_are_AGG_WAITING_FOR_RESPONSE_and_NO_SCHEDULES() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     list.add(buildVisitFact(1,State.NO_SCHEDULES));
     list.add(buildVisitFact(1,State.AGG_WAITING_FOR_RESPONSE));
     exchange.getIn().setBody(list);
     //header  
     exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "Corrected_staff_id");
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     
     //assertion
     assertEquals(State.POSSIBLE_MATCH, exchange.getIn().getHeader("STATE"));
     
   }
   
   
   
   @Test
   public void test_processMatches_body_with_2_VisitFacts_and_state_are_same_as_AGG_WAITING_FOR_RESPONSE() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     list.add(buildVisitFact(1,State.AGG_WAITING_FOR_RESPONSE));
     list.add(buildVisitFact(1,State.AGG_WAITING_FOR_RESPONSE));
     exchange.getIn().setBody(list);
     //header  
     exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "Corrected_staff_id");
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     
     //assertion
     assertEquals(State.NOT_MATCHED, exchange.getIn().getHeader("STATE"));
     
   }
   
   
   
   
   @Test
   public void test_processMatches_body_with_2_VisitFacts_and_state_are_AGG_WAITING_FOR_RESPONSE_and_INVALID_STAFF() throws CepException{
       
     //data test  
     List<VisitFact> list = new ArrayList<>();
     list.add(buildVisitFactWithCallIn(1,State.AGG_WAITING_FOR_RESPONSE));
     list.add(buildScheduleVisit(1,State.NO_SCHEDULES));
     exchange.getIn().setBody(list);
     //header  
     exchange.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, "Corrected_staff_id");
     
     //executing method
     startVisitAndScheduleProcess.processMatches(exchange);
     
     //assertion
     assertEquals(State.POSSIBLE_MATCH, exchange.getIn().getHeader("STATE"));
     List listResult = exchange.getIn().getBody(List.class);
     assertEquals(2, listResult.size());
     
     VisitFact inserted = (VisitFact) listResult.get(0);
     assertEquals(State.POSSIBLE_MATCH_WAITING_FOR_RESPONSE, inserted.getState());
     
     VisitFact scheduled = (VisitFact) listResult.get(1);
     assertEquals(State.POSSIBLE_MATCH_FROM_RESPONSE, scheduled.getState());
     
   }
   
   
   
   private VisitFact buildVisitFactWithCallIn(int uniqueKey,State state){
       
       VisitFact visitFact  = buildVisitFact(uniqueKey,state);
       Calendar cal =Calendar.getInstance();
       cal.add(Calendar.DAY_OF_MONTH, -1);
       visitFact.setCallIn(cal.getTime());
       visitFact.setAni("Ani"+uniqueKey);
       visitFact.setPatientId("Patient"+uniqueKey);
       visitFact.setClientEntered(false);
       
       
       return visitFact;
   }
   
   
   private VisitFact buildScheduleVisit (int uniqueKey,State state){
       VisitFact visitFact  = buildVisitFact(uniqueKey,state);
       Calendar cal =Calendar.getInstance();

       //call in must be between schedule from and scheudle to
       cal.add(Calendar.DAY_OF_MONTH, -2);
       visitFact.setScheduledFrom(cal.getTime());

       cal.add(Calendar.DAY_OF_MONTH, 3);
       visitFact.setScheduledTo(cal.getTime());
       
       visitFact.setAni("Ani"+uniqueKey);
       
       return visitFact;
   }
   
   
   private VisitFact buildVisitFact(int uniqueKey,State state){
       
       VisitFact vistiFact = new VisitFact();
       vistiFact.setState(state);
       VisitEventFact visitEvent = new VisitEventFact();
       visitEvent.setStaffID("Staff"+uniqueKey);
       vistiFact.setCallCallIn(visitEvent);
       CallPreferences callPreferences = new CallPreferences();
       
       vistiFact.setCallPreferences(callPreferences);
       
       
       return vistiFact;
       
   }
   
   
}
