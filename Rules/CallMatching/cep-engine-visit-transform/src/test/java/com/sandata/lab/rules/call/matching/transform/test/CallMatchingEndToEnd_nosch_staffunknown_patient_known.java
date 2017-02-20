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
 * @Description : no sch; staff unknown patient known by ANI
 *
 * @date Apr 28, 2016
 * 
 * 
 */
public class CallMatchingEndToEnd_nosch_staffunknown_patient_known extends CallMatchingEndToEnd {
    
   
    
    @Test
    public void test_unschedule_unknownstaff_knownpatient_by_unique_ani_only_callin() throws Exception{
        //data test - tool free phone number which receive the call from patient 's homse
        String dnis1 = "8557214908";
        String staffID = "fdgfdg";
        String patientId= "";
        boolean isKnownByAni = true;
        
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        String ani = "1111111111";
       
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
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, visitEvent.getPatientID(), visitEvent.getStaffID(),isKnownByAni,isUncheduled);
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
        assertEquals(ani, visitEvents.get(0).get("ANI"));
        assertEquals(staffID, visitEvents.get(0).get("STAFF_ID"));
        assertEquals(dnis1, visitEvents.get(0).get("DNIS"));
        assertNull(visitEvents.get(0).get("PATIENT_ID"));
        assertEquals("1",visitEvents.get(0).get("CALL_IN_IND").toString());
        assertNull(visitEvents.get(0).get("CALL_OUT_IND"));
        
    }
    
    
    //more test cases
    
    
}
