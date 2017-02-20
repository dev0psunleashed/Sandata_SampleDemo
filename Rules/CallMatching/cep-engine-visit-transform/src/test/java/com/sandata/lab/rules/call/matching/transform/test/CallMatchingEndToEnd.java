/**
 * 
 */
package com.sandata.lab.rules.call.matching.transform.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;

/**
 * @author thanhxle
 *
 * @Description : no sch; known/known by ANI test cases
 *
 * @date Apr 28, 2016
 * 
 * 
 */
public class CallMatchingEndToEnd extends CamelBlueprintTestSupport {

    
    @Produce(uri = "activemq:queue:PROCESS_CALLS")
    private ProducerTemplate producerTemplatePROCESS_CALLS;
    
    @Override
    protected String getBlueprintDescriptor() {
        
        System.out.println("df");
        return "/OSGI-INF/blueprint/blueprint-test.xml";
    }
    
    
    protected Object executeTestAndReturn(List<VisitEventExt> visits,String dnis){
        
        
        VisitEventDNISGroup grp = new VisitEventDNISGroup();
        //List<com.sandata.lab.data.model.dl.model.extended.VisitEventExt> visits = new ArrayList<com.sandata.lab.data.model.dl.model.extended.VisitEventExt>();
        //visits.add(visit);
               
        //phone number of patient that created in schedule
        grp.setDnis(dnis);
        grp.setVisitEvents(visits);
        
        
        Exchange exchange =  new DefaultExchange(context);
        //for idempotent consumer , header DUPLICATED_ID
        String test= UUID.randomUUID().toString();
        exchange.getIn().setHeader("DUPLICATE_ID", test);
        exchange.getIn().setBody(grp);
        
        
        //send message to the active mq
        producerTemplatePROCESS_CALLS.send("activemq:queue:PROCESS_CALLS",exchange);
        //startEndpointRetractCall.sendBody(grp);
        
        Endpoint endpoint = context.getEndpoint("activemq:queue:PROCESSED_CALLS");
        //PollingConsumer consumer = endpoint.createPollingConsumer();
        
        Object result = consumer.receiveBody(endpoint);
        
        return result;
    }
    
    /**
     * no sch/known staff , patient known by Ani , call in (only) first call
     * 
     *
     */
    @Test
    public void test_unschedule_knownstaff_knownpatient_by_unique_ani_only_callin() throws Exception{
        //data test - tool free phone number which receive the call from patient 's homse
        String dnis1 = "8557214908";
        String staffID = "080342494";
        String patientId= "";
        boolean isKnownByAni = true;
        boolean isUncheduled = true;
        boolean isCallIn = true;
        boolean isCallOut = false;
        String ani = "1987654320";
        
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
    
   
    /**
     * 
     * call keyed in as OUT call
     *
     */
    @Test
    public void test_unschedule_knowstaff_knownpatinet_unique_ani_callout_call_keyed_in_as_OUT_call() throws Exception{
        //data test - tool free phone number which receive the call from patient 's homse
        String dnis1 = "8557214908";
        String staffID = "005964368";
        String patientId= "";
        boolean isKnownByAni = true;
        boolean isUncheduled = true;
        boolean isCallIn = false;
        boolean isCallOut = true;
        String ani = "1111111111";
       
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
        
        //GET VISIT - because in thi case, don;t add new visit, just add a call in record into the visit_evnt table
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, visitEvent.getPatientID(), visitEvent.getStaffID(),isKnownByAni,isUncheduled);
        //show result  - visit that just created
        printQueryResult(resultSetVisit);
       
        //do expectation - no new visit was added
        assertEquals(0, resultSetVisit.size());
       
        
        //GET VISIT EVENT
        List<Map<String, Object>> visitEvents = getVisitEvent(jdbcTemplate,dateString);
        //print out put
        printQueryResult(visitEvents);
        
        //do assertions - expectation , is call in , with ani , staffid , visit event dtime , time-zone,dnis,patient id is null,call in indecator is true
        assertEquals(1, visitEvents.size());
        //time of ending the visit, call out dtime
        assertEquals(dateString, visitEvents.get(0).get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani, visitEvents.get(0).get("ANI"));
        assertEquals(staffID, visitEvents.get(0).get("STAFF_ID"));
        assertEquals(dnis1, visitEvents.get(0).get("DNIS"));
        assertNull(visitEvents.get(0).get("PATIENT_ID"));
        assertEquals("1",visitEvents.get(0).get("CALL_OUT_IND").toString());
        assertNull(visitEvents.get(0).get("CALL_INT_IND"));
        
    }
    
    
    /**
     * no schedule
     * staff : known
     * patient : known by unique ani
     * 2 calls - one is call in , other is call out
     *
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_unique_ani_callin_callout() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        String dnsi2 = "8776379471";
        //patient home phone
        String ani = "1900000000";
        String staffId = "1";
        String patientId = "";
        boolean isKnownByAni = true;
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
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, visitEvent.getPatientID(), visitEvent.getStaffID(),isKnownByAni,isUncheduled);
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
        Thread.sleep(30000);
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
        assertEquals(ani, callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertNull(callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        //call out info 
        assertEquals(convertDateToString(visit2.getVisitEventDatetime()), callOut.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani, callOut.get("ANI"));
        assertEquals(staffId, callOut.get("STAFF_ID"));
        assertEquals(dnis1, callOut.get("DNIS"));
        assertNull(callOut.get("PATIENT_ID"));
        assertEquals("1",callOut.get("CALL_OUT_IND").toString());
        assertNull(callOut.get("CALL_IN_IND"));
    }
    
    
    
    /**
     * no schedule
     * staff:known
     * patient: known by ANI on > 2 patients
     * call in : any
     * call out: any
     * 
     *
     */
    @Test
    public void test_unschedule_knowstaff_knownpatient_one_ani_on_more_than_2_patients() throws Exception{
        //data test - tool free phone number which receive the call from patient 's home
        String dnis1 = "8557214908";
        String dnsi2 = "8776379471";
        //patient home phone - share among many patients
        String ani = "1000000000";
        String staffId = "1";
        String patientId = "";
        boolean isKnownByAni = true;
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
        List<Map<String, Object>>  resultSetVisit = getVisit(jdbcTemplate, dateString, visitEvent.getPatientID(), visitEvent.getStaffID(),isKnownByAni,isUncheduled);
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
        Thread.sleep(30000);
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
        assertEquals(ani, callIn.get("ANI"));
        assertEquals(staffId, callIn.get("STAFF_ID"));
        assertEquals(dnis1, callIn.get("DNIS"));
        assertNull(callIn.get("PATIENT_ID"));
        assertEquals("1",callIn.get("CALL_IN_IND").toString());
        assertEquals("0",callIn.get("CALL_OUT_IND").toString());
        
        //call out info 
        assertEquals(convertDateToString(visit2.getVisitEventDatetime()), callOut.get("VISIT_EVNT_DTIME").toString().split("\\.")[0]);
        assertEquals(ani, callOut.get("ANI"));
        assertEquals(staffId, callOut.get("STAFF_ID"));
        assertEquals(dnis1, callOut.get("DNIS"));
        assertNull(callOut.get("PATIENT_ID"));
        assertEquals("1",callOut.get("CALL_OUT_IND").toString());
        assertNull(callOut.get("CALL_IN_IND"));
    }
    
    
    
    
    
    
    protected com.sandata.lab.data.model.dl.model.extended.VisitEventExt getVisit(String ani, String dnis, String staffId,String patientId,boolean isCallIn,boolean isCallOut,boolean isKnownByAni) {
        VisitEventExt visit = new VisitEventExt();
        
        
        //TODO:Callin and call out flag
        //visit.setCallOutIndicator(true);
        if(isCallIn){
            //call in date time
            visit.setVisitEventDatetime(Calendar.getInstance().getTime());
            visit.setCallInIndicator(true);
        }
        
        if(isCallOut){
            
            //CALL OUT MUST BE AT LEAST 6 MINS AFTER CALL IN 
            visit.setCallOutIndicator(true);
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 10);
            visit.setVisitEventDatetime(cal.getTime());
        }
        
        //TODO: what is scheduled visit
        //visit.set
        
        
        //set visit task list
        setVisitTaskList(visit);
        
        //----------------------------------------------------------------
        //just need the 3 conditions below to convert to call fact
        
       
        //dnis
        visit.setDialedNumberIdentificationService(dnis);
       
        //patientid
        
        //if verified by patient home phone, not use patient id/ patientid not keyed
        if(isKnownByAni == false){
            visit.setPatientID(patientId);
        }
        //known by Ani
        //ani - home phone of patient
        visit.setAutomaticNumberIdentification(ani);
        
        //--------------------------------------------------------------------
        
        //gps information
        //not larger than 16 degits
        //visit.setCoordinateAccuracy(new BigDecimal("4825"));
        //visit.setCoordinateAccuracy(new BigDecimal("4828"));
        //visit.setCoordinateAltitude(new BigDecimal("48216"));
        //visit.setCoordinateLatitude(new BigDecimal("482235"));
        //visit.setCoordinateLongitide(new BigDecimal("492267"));
        
        //time zone information
        visit.setTimezoneName("Europe/Copenhagen");
        visit.setStaffID(staffId);
        return visit;
    }
    
    
    
    private void setVisitTaskList(final VisitEventExt visitEventExt){
        
        List<VisitTaskList> visitTaskList = new ArrayList<>();
        VisitTaskList temp ;
        for (int i = 0; i < 2; i++) {
            temp = new VisitTaskList();
            //need to set task ID
            if(i == 0){
                //real task id from db, table Task
                temp.setVisitTaskListID("0099");
            }else if(i == 1){
                temp.setVisitTaskListID("0100");
            }else if(i == 2){
                temp.setVisitTaskListID("0101");
            }
            temp.setVisitTaskComment("Visit Task Comment No."+i+1);
            visitTaskList.add(temp);
        }
        visitEventExt.setVisitTaskLists(visitTaskList);
    }
    

    
    /**
     * 
     * get the visit event by the visit_sk 
     * 
     * @param jdbcTemplate
     * @param visit_sk
     * @return
     *
     */
    protected List<Map<String, Object>> getVisitEventByVisitSkID(JdbcTemplate jdbcTemplate,String visit_sk){
        String sql = String.format("SELECT * FROM VISIT_EVNT WHERE VISIT_SK = %s", visit_sk);
        System.out.println(sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
    
    
    protected List<Map<String, Object>> getVisitEvent(JdbcTemplate jdbcTemplate, String vistEnentDtime){
        String sql = String.format("SELECT * FROM VISIT_EVNT WHERE VISIT_EVNT_DTIME = TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS')", vistEnentDtime);
        System.out.println(sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
    
    

    protected List<Map<String, Object>> getVisit(JdbcTemplate jdbcTemplate,String rec_create_tmstp,String patientId, String staffId,boolean isKnownByAni,boolean isUncheduled){
        String sql = String.format("select * from visit where  VISIT_ACT_START_TMSTP =  TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS') ", rec_create_tmstp,staffId);
        
        //in case of call by ANI, not use patient ID
        if(!isKnownByAni){
            sql = sql.concat(" and patient_id= '%s'");
            sql = String.format(sql, patientId);
        }
        
        //if(!isUncheduled){
            //just in case of planned visit, need verify staff id
            sql = sql.concat(" and staff_id = '%s'");
            sql = String.format(sql, staffId);
            
            
       // }
        
        System.out.println(sql);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        
        return result;
        
    }
    
    protected  org.springframework.jdbc.core.JdbcTemplate getJdbcTemplate(){
        
        
        String userName = "coredata";
        String pwd = "Z4fEIRn7D2";
        String server = "stxdevdb.sandata.com";
        String port = "1526";
        String sid = "sdbdev2";
        Integer maxActive = new Integer(5);
        Integer maxIdle=new Integer(20);
        Integer initSize = new Integer(5);
        
        SantraxOracleDataSource ds =  new SantraxOracleDataSource(userName, pwd, server, port, sid, initSize, maxActive, maxIdle);
        
        org.springframework.jdbc.core.JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(ds);
        // List<Map<String, Object>> result = jdbcTemplate.queryForList(getFileNameQuery);
        // String getFileNameQuery = String.format("SELECT filename from inbox.exports where sid=%s", sid);
        return jdbcTemplate;
       // return getBean("configProperty", BeanConfigProperty.class);
    }
    
    //09-MAY-16 15:37:21
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected String convertDateToString(Date date){
        
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        return dateTimeFormat.format(date );
    }
        
    
    protected void printQueryResult (List<Map<String, Object>> queryResult){
        
        for (Map<String, Object> map : queryResult) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            }
        }
        
    }
    
}
