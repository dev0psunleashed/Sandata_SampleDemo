/**
 * 
 */
package com.sandata.lab.rules.call.matching.transform.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;

/**
 * @author thanhxle
 *
 * @Description : no sch; known/known by keyed ID test cases
 *
 * @date Apr 28, 2016
 * 
 * 
 */
public class CallMatchingEndToEnd_nosch_staffknown_patient_known_by_keyedID extends CallMatchingEndToEnd {
    
   
    
    @Test
    public void test_unschedule_knownstaff_knownpatient_by_unique_keyed_only_callin() throws Exception{
        //data test - tool free phone number which receive the call from patient 's homse
        String dnis1 = "8557214908";
        String staffID = "01091945";

        //patient ID is keyed in
        String patientId= "201602189992";
        boolean isKnownByAni = false;
        
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        //patient id is keyed, not use ANI
        String ani = "";
       
        //data input
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit = getVisit(ani, dnis1, staffID,patientId,isCallIn,isCallOut,isKnownByAni);
        List<VisitEventExt> visits = new ArrayList<>();
        visits.add(visit);
        //executing test
        Object result = executeTestAndReturn(visits,dnis1);
        //////////////////////////
        
        //do assertions
        assertNotNull(result);
        assertTrue(result instanceof VisitEvent);
       
        VisitEvent visitEvent = (VisitEvent) result;
        //assertEquals(1, visitEvent.get);
        
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String dateString = convertDateToString(visit.getVisitEventDatetime());
        System.out.println(dateString);
        //GET VISIT
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, patientId, staffID,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do assertsion
        assertEquals(1, resultSetVisit.size());
        //end visit dtime
        assertEquals(dateString, resultSetVisit.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(resultSetVisit.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffID, resultSetVisit.get(0).get("STAFF_ID"));
        
        String visit_sk = resultSetVisit.get(0).get("VISIT_SK").toString();
        
        //GET VISIT EVENT
        List<Map<String, Object>> visitEvents = getVisitEventByVisitSkID(jdbcTemplate, visit_sk);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(1, visitEvents.size());
        //time of starting the visit, call in dtime
        assertEquals(dateString, visitEvents.get(0).get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertNull(visitEvents.get(0).get("ANI"));
        assertEquals(staffID, visitEvents.get(0).get("STAFF_ID"));
        assertEquals(dnis1, visitEvents.get(0).get("DNIS"));
        assertEquals(patientId,visitEvents.get(0).get("PATIENT_ID"));
        assertEquals("1",visitEvents.get(0).get("CALL_IN_IND").toString());
        assertNull(visitEvents.get(0).get("CALL_OUT_IND"));
        
    }
    
    
    /*
     * no schedule 
     * staff known
     * patient known keyed ID
     * 1 call in 
     * 1 call out
     * (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.transform.test.CallMatchingEndToEnd#test_unschedule_knowstaff_knownpatient_unique_ani_callin_callout()
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_keyed_id_callin_callout() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        //patient home phone
        String ani = "1231232132";
        String staffId = "20160228083658680";
        String patientId = "1231111";
        boolean isKnownByAni = false;
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        
        //call in
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit = getVisit(ani, dnis1, staffId,patientId,isCallIn,isCallOut,isKnownByAni);
        //call out
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit2 = getVisit(ani, dnis1, staffId,patientId,isCallOut,isCallIn,isKnownByAni);
        List<VisitEventExt> visits = new ArrayList<>();
        visits.add(visit);
       
        //executing test - SENDING CALL IN FIRST
        Object result = executeTestAndReturn(visits,dnis1);
        
        //do assertions
        assertNotNull(result);
        assertTrue(result instanceof VisitEvent);
       
        VisitEvent visitEvent = (VisitEvent) result;
        //assertEquals(1, visitEvent.get);
        
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        //TODO:
        String dateString = convertDateToString(visit.getVisitEventDatetime());
        System.out.println(dateString);
        //GET VISIT
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do assertsion
        assertEquals(1, resultSetVisit.size());
        //end visit dtime
        assertEquals(dateString, resultSetVisit.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(resultSetVisit.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, resultSetVisit.get(0).get("STAFF_ID"));
        
        String visit_sk = resultSetVisit.get(0).get("VISIT_SK").toString();
        
        
        //SENDING CALL OUT
        visits =  new ArrayList<>();
        visits.add(visit2);
        Object callout_result = executeTestAndReturn(visits,dnis1);
        
        //waiting for routes complete to update to database
        Thread.sleep(20000);
        //GET VISIT EVENT - call in
        List<Map<String, Object>> visitEvents = getVisitEventByVisitSkID(jdbcTemplate, visit_sk);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(2, visitEvents.size());
        
        Map<String, Object> callIn = null;
        Map<String, Object> callOut = null;
        for (Map<String, Object> map : visitEvents) {
            if(map.get("CALL_IN_IND") != null && map.get("CALL_IN_IND").toString().equals("1")){
                callIn = map;
                
            }else{
                callOut = map;
            }
           
        }
        
        
        //time of starting the visit, call in dtime
        assertEquals(dateString, callIn.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertEquals(patientId,callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        //call out info 
        assertEquals(convertDateToString(visit2.getVisitEventDatetime()), callOut.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callOut.get("ANI"));
        assertEquals(staffId, callOut.get("STAFF_ID"));
        assertEquals(dnis1, callOut.get("DNIS"));
        assertEquals(patientId,callOut.get("PATIENT_ID"));
        assertEquals("1",callOut.get("CALL_OUT_IND").toString());
        assertNull(callOut.get("CALL_IN_IND"));
    }
    
   
    /*
     * no schedule 
     * staff known
     * patient known keyed ID
     * 1 call in 
     * 2 call out
     * Expectations: 3rd will be a new visit
     * 
     * (non-Javadoc)
     * @see com.sandata.lab.rules.call.matching.transform.test.CallMatchingEndToEnd#test_unschedule_knowstaff_knownpatient_unique_ani_callin_callout()
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_keyed_id_callin_2_callout() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        //patient home phone
        String ani = "1231231231";
        String staffId = "20160228105856404";
        String patientId = "311123123";
        boolean isKnownByAni = false;
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        
        //call in
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit = getVisit(ani, dnis1, staffId,patientId,isCallIn,isCallOut,isKnownByAni);
        //call out
        
        
        List<VisitEventExt> visits = new ArrayList<>();
        visits.add(visit);
       
        //executing test - SENDING CALL IN FIRST
        Object result = executeTestAndReturn(visits,dnis1);
        System.out.println("Running ....  waiting for 30 seconds");
        Thread.sleep(30000);
       
        
        //do assertions
        assertNotNull(result);
        assertTrue(result instanceof VisitEvent);
       
        VisitEvent visitEvent = (VisitEvent) result;
        //assertEquals(1, visitEvent.get);
        
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        //TODO:
        String dateString = convertDateToString(visit.getVisitEventDatetime());
        System.out.println(dateString);
        //GET VISIT
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do assertsion
        assertEquals(1, resultSetVisit.size());
        //end visit dtime
        assertEquals(dateString, resultSetVisit.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(resultSetVisit.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, resultSetVisit.get(0).get("STAFF_ID"));
        
        String visit_sk = resultSetVisit.get(0).get("VISIT_SK").toString();
        
        
        //SENDING CALL OUT
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit2 = getVisit(ani, dnis1, staffId,patientId,isCallOut,isCallIn,isKnownByAni);
        visits =  new ArrayList<>();
        visits.add(visit2);
        Object callout_result = executeTestAndReturn(visits,dnis1);
        System.out.println("Running ....  waiting for 30 seconds");
        Thread.sleep(30000);
       
        
        //third call
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit3 = getVisit(ani, dnis1, staffId,patientId,isCallOut,isCallIn,isKnownByAni);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);
        visit3.setVisitEventDatetime(cal.getTime());
        visits =  new ArrayList<>();
        visits.add(visit3);
        executeTestAndReturn(visits,dnis1);
        
        
        //waiting for routes complete to update to database
        Thread.sleep(30000);
        System.out.println("Running ....  waiting for 30 seconds");
        //GET VISIT EVENT - call in
        List<Map<String, Object>> visitEvents = getVisitEventByVisitSkID(jdbcTemplate, visit_sk);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(2, visitEvents.size());
        
        Map<String, Object> callIn = null;
        Map<String, Object> callOut = null;
        for (Map<String, Object> map : visitEvents) {
            if(map.get("CALL_IN_IND") != null && map.get("CALL_IN_IND").toString().equals("1")){
                callIn = map;
                
            }else{
                callOut = map;
            }
           
        }
        
        
        //time of starting the visit, call in dtime
        assertEquals(dateString, callIn.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertEquals(patientId,callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        //call out info 
        assertEquals(convertDateToString(visit2.getVisitEventDatetime()), callOut.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callOut.get("ANI"));
        assertEquals(staffId, callOut.get("STAFF_ID"));
        assertEquals(dnis1, callOut.get("DNIS"));
        assertEquals(patientId,callOut.get("PATIENT_ID"));
        assertEquals("1",callOut.get("CALL_OUT_IND").toString());
        assertNull(callOut.get("CALL_IN_IND"));
        
        
       
        
        //verifying the 3rd call
        System.out.println("========================= verifying the third call ===============================");
        
       
        
        String dateString_3rdcall = convertDateToString(visit3.getVisitEventDatetime());
        System.out.println(dateString_3rdcall);
        //GET VISIT
        List<Map<String, Object>>  thirdCall = getVisit(jdbcTemplate, dateString_3rdcall, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(thirdCall);
       
        //do assertsion
        assertEquals(1, thirdCall.size());
      
        //end visit dtime
        assertEquals(dateString_3rdcall, thirdCall.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(thirdCall.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, thirdCall.get(0).get("STAFF_ID"));
        
        String third_call_visit_sk = thirdCall.get(0).get("VISIT_SK").toString();
        List<Map<String, Object>> thirdcall_visitEvents = getVisitEventByVisitSkID(jdbcTemplate, third_call_visit_sk);
        //print out put
        printQueryResult(thirdcall_visitEvents);
        Map<String,Object> callInEvent = thirdcall_visitEvents.get(0);
        assertEquals(dateString_3rdcall, callInEvent.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callInEvent.get("ANI"));
        assertEquals(staffId, callInEvent.get("STAFF_ID"));
        assertEquals(dnis1, callInEvent.get("DNIS"));
        assertEquals(patientId,callInEvent.get("PATIENT_ID"));
        assertEquals("1",callInEvent.get("CALL_IN_IND").toString());
        assertEquals("0",callInEvent.get("CALL_OUT_IND").toString());
        
    }
    
    
    /**
     * No Schedule
     * Staff : known
     * Patient known keyed id
     * call in
     * call out : 24h lower than call in
     * 
     * expectation : the second call get matched to the first call
     * 
     * @throws Exception
     *
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_keyed_id_callin_callout_24h_lower_than_call_in() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        //patient home phone
        String ani = "1987654320";
        String staffId = "20160228105847838";
        String patientId = "20160229074214102";
        boolean isKnownByAni = false;
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        
        //call in
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit = getVisit(ani, dnis1, staffId,patientId,isCallIn,isCallOut,isKnownByAni);
        //call out
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit2 = getVisit(ani, dnis1, staffId,patientId,isCallOut,isCallIn,isKnownByAni);
        List<VisitEventExt> visits = new ArrayList<>();
        visits.add(visit);
       
        //executing test - SENDING CALL IN FIRST
        Object result = executeTestAndReturn(visits,dnis1);
        
        
        //do assertions
        assertNotNull(result);
        assertTrue(result instanceof VisitEvent);
       
        VisitEvent visitEvent = (VisitEvent) result;
        //assertEquals(1, visitEvent.get);
        
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        //TODO:
        String dateString = convertDateToString(visit.getVisitEventDatetime());
        System.out.println(dateString);
        //GET VISIT
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do assertsion
        assertEquals(1, resultSetVisit.size());
        //end visit dtime
        assertEquals(dateString, resultSetVisit.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(resultSetVisit.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, resultSetVisit.get(0).get("STAFF_ID"));
        
        String visit_sk = resultSetVisit.get(0).get("VISIT_SK").toString();
        
        
        //SENDING CALL OUT
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        visit2.setVisitEventDatetime(cal.getTime());
        visits =  new ArrayList<>();
        visits.add(visit2);
        System.out.println("Call out Dtime: "+ convertDateToString(visit2.getVisitEventDatetime()));
        Object callout_result = executeTestAndReturn(visits,dnis1);
        
        //waiting for routes complete to update to database
        Thread.sleep(20000);
        //GET VISIT EVENT - call in
        List<Map<String, Object>> visitEvents = getVisitEventByVisitSkID(jdbcTemplate, visit_sk);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(2, visitEvents.size());
        
        Map<String, Object> callIn = null;
        Map<String, Object> callOut = null;
        for (Map<String, Object> map : visitEvents) {
            if(map.get("CALL_IN_IND") != null && map.get("CALL_IN_IND").toString().equals("1")){
                callIn = map;
                
            }else{
                callOut = map;
            }
           
        }
        
        
        //time of starting the visit, call in dtime
        assertEquals(dateString, callIn.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertEquals(patientId,callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        //call out info 
        assertEquals(convertDateToString(visit2.getVisitEventDatetime()), callOut.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callOut.get("ANI"));
        assertEquals(staffId, callOut.get("STAFF_ID"));
        assertEquals(dnis1, callOut.get("DNIS"));
        assertEquals(patientId,callOut.get("PATIENT_ID"));
        assertEquals("1",callOut.get("CALL_OUT_IND").toString());
        assertNull(callOut.get("CALL_IN_IND"));
    }
    
    
    /**
     * No Schedule
     * Staff : known
     * Patient known keyed id
     * call in
     * call out : 24h greater than call in
     * 
     * expectation : the second call get matched to the first call
     * 
     * @throws Exception
     *
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_keyed_id_callin_callout_24h_greater_than_call_in() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        //patient home phone
        String ani = "1000000000";
        String staffId = "20160228081909607";
        String patientId = "201603171542";
        boolean isKnownByAni = false;
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        
        //call in
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit = getVisit(ani, dnis1, staffId,patientId,isCallIn,isCallOut,isKnownByAni);
        //call out
        com.sandata.lab.data.model.dl.model.extended.VisitEventExt visit2 = getVisit(ani, dnis1, staffId,patientId,isCallOut,isCallIn,isKnownByAni);
        List<VisitEventExt> visits = new ArrayList<>();
        visits.add(visit);
       
        //executing test - SENDING CALL IN FIRST
        Object result = executeTestAndReturn(visits,dnis1);
        
        
        //do assertions
        assertNotNull(result);
        assertTrue(result instanceof VisitEvent);
       
        VisitEvent visitEvent = (VisitEvent) result;
        //assertEquals(1, visitEvent.get);
        
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        //TODO:
        String dateString = convertDateToString(visit.getVisitEventDatetime());
        System.out.println(dateString);
        //GET VISIT
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do assertsion
        assertEquals(1, resultSetVisit.size());
        //end visit dtime
        assertEquals(dateString, resultSetVisit.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(resultSetVisit.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, resultSetVisit.get(0).get("STAFF_ID"));
        
        String visit_sk = resultSetVisit.get(0).get("VISIT_SK").toString();
        
        
        //SENDING CALL OUT
        Calendar cal = Calendar.getInstance();
        //more than 2 days
        cal.add(Calendar.DATE, 2);
        visit2.setVisitEventDatetime(cal.getTime());
        visits =  new ArrayList<>();
        visits.add(visit2);
        String callIn2Date = convertDateToString(visit2.getVisitEventDatetime());
        System.out.println("Call out Dtime: "+ callIn2Date);
        Object callout_result = executeTestAndReturn(visits,dnis1);
        
      //waiting for routes complete to update to database
        Thread.sleep(20000);
        //GET VISIT
        List<Map<String, Object>>  callIn2 = getVisit(jdbcTemplate, callIn2Date, patientId, staffId,isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(callIn2);
        assertEquals(1, callIn2.size());
        //end visit dtime
        assertEquals(callIn2Date, callIn2.get(0).get("VISIT_ACT_START_TMSTP").toString().split("\\.")[0]);
        assertNull(callIn2.get(0).get("VISIT_ACT_END_TMSTP"));
        assertEquals(staffId, callIn2.get(0).get("STAFF_ID"));
        String call_in_2 = callIn2.get(0).get("VISIT_SK").toString();
        
       
        
        
        
        //GET VISIT EVENT - call in
        List<Map<String, Object>> visitEvents = getVisitEventByVisitSkID(jdbcTemplate, visit_sk);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(1, visitEvents.size());
        
        Map<String, Object> callIn = null;
        Map<String, Object> callOut = null;
        for (Map<String, Object> map : visitEvents) {
            if(map.get("CALL_IN_IND") != null && map.get("CALL_IN_IND").toString().equals("1")){
                callIn = map;
                
            }else{
                callOut = map;
            }
           
        }
        
        
        //time of starting the visit, call in dtime
        assertEquals(dateString, callIn.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertEquals(patientId,callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        
        
        //call in 2 check
        List<Map<String, Object>> visitEvents_callin2 = getVisitEventByVisitSkID(jdbcTemplate, call_in_2);
        printQueryResult(visitEvents);
        assertEquals(dateString, visitEvents_callin2.get(0).get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani,visitEvents_callin2.get(0).get("ANI"));
        assertEquals(staffId, visitEvents_callin2.get(0).get("STAFF_ID"));
        assertEquals(dnis1, visitEvents_callin2.get(0).get("DNIS"));
        assertEquals(patientId,visitEvents_callin2.get(0).get("PATIENT_ID"));
        assertEquals("1",visitEvents_callin2.get(0).get("CALL_IN_IND").toString());
        assertEquals("0",visitEvents_callin2.get(0).get("CALL_OUT_IND").toString());
    }
    
}
