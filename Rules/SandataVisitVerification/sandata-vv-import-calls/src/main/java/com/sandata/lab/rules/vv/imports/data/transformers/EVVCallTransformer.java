package com.sandata.lab.rules.vv.imports.data.transformers;

import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.imports.model.EVVCallInfo;
import com.sandata.lab.rules.vv.imports.utils.date.DateTimeConverter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author thanhxle
 * This class used to transform result set from EVV db to VisitEventExt
 *
 */
public class EVVCallTransformer {

    private Logger archerVVCallImportLogger = LoggerFactory.getLogger(EVVCallTransformer.class);
    private DateTimeConverter dateTimeConverter;

    public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
        this.dateTimeConverter = dateTimeConverter;
    }

    /**
     * Transform ResultSet to EVV Call object
     * @param callResultSet = Call resultset from Oracle
     * @param callInfoResultSet = Tasks associated with call from Oracle
     * @return
     * @throws Exception
     */
    public List<EVVCall> transformCallResultSetToCall(ResultSet callResultSet, ResultSet callInfoResultSet) throws Exception {
        
        if(callResultSet == null) {
            return null;
        }

        //get EVVCall from ResultSet
        Map<String, EVVCall> calls = convertToEvvCall(callResultSet);

        if(callInfoResultSet != null) {
            
            while (callInfoResultSet.next()) {
                EVVCallInfo callInfo = new EVVCallInfo();

                callInfo.setCallExpKey(callInfoResultSet.getString("callexpkey"));
                callInfo.setAccount(callInfoResultSet.getString("account"));
                callInfo.setExportKey(callInfoResultSet.getString("exportkey"));
                callInfo.setSid(callInfoResultSet.getString("sid"));
                callInfo.setCallKey(callInfoResultSet.getString("callkey"));
                callInfo.setTaskId(callInfoResultSet.getString("task_id"));
                callInfo.setTaskInfo(callInfoResultSet.getString("task_info"));

                EVVCall call = calls.get(callInfo.getCallKey());

                if (call != null) {
                    List<EVVCallInfo> callCallInfo = call.getCallInfo();

                    if (callCallInfo == null) {
                        callCallInfo = new ArrayList<>();
                    }

                    callCallInfo.add(callInfo);

                    call.setCallInfo(callCallInfo);
                }
            }
        }

        return new ArrayList<>(calls.values());
    }

    /**
     * 
     * @param visitEventExt VisitEventExt
     * @return EVVCall
     * 
     * This method convert VisitEventExt back to EvvCall.
     */
    public EVVCall createCall(VisitEventExt visitEventExt) {
        
        EVVCall call = new EVVCall();
        call.setSid(visitEventExt.getSid());
        call.setAni(visitEventExt.getAutomaticNumberIdentification());
        call.setStxId(visitEventExt.getStaffID());
        call.setClientId(visitEventExt.getPatientID());
        call.setDnis(visitEventExt.getDialedNumberIdentificationService());

        if(visitEventExt.getVisitEventDatetime() != null) {
            DateTime dateTime = new DateTime(visitEventExt.getVisitEventDatetime());

            if (dateTime != null) {
                call.setCallDtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
            }
        }

        return call;
    }

    /**
     * Create VisitEvent object derived from Call object
     * @param call = Evv call object
     * @return VisitEvent = Call Import service object for visits (calls)
     */
    public VisitEventExt createVisitEvent(EVVCall call) throws Exception {

        VisitEventExt visitEventExt = new VisitEventExt();

        visitEventExt.setSid(call.getSid());
        visitEventExt.setDialedNumberIdentificationService(call.getDnis());
        visitEventExt.setAutomaticNumberIdentification(call.getAni());
        visitEventExt.setStaffID(call.getStxId());
        visitEventExt.setPatientID(call.getClientId());
        visitEventExt.setTimezoneName(call.getTimeZoneName());
        
        StringBuilder message = new StringBuilder(String.format("INPUT_EVV_CALL_PASSED_TO_PROCESS_MATCH: DNIS=%s , ANI=%s, Staff ID=%s, Patient ID=%s", 
                call.getDnis(), call.getAni(), call.getStxId(), call.getClientId()));

        if(call.getCallDtimeUTC() != null) {
            
            visitEventExt.setVisitEventDatetime(call.getCallDtimeUTC().toDateTime(DateTimeZone.UTC).toDate());
            message.append(String.format("VisitEventDatetime=%s", visitEventExt.getVisitEventDatetime()));
        }
        //archerVVCallImportLogger.info(message.toString());

        //Set event coordinates
        visitEventExt.setCoordinateLatitude(call.getLatitude());
        visitEventExt.setCoordinateLongitide(call.getLongitude());
        
        // check and set the call type
        setVisitEventTypeCode(visitEventExt,call);

        //set task list info to visit
        setTaskListToVisit(visitEventExt,call);

        return visitEventExt;
    }
    
    
    private void setTaskListToVisit (VisitEventExt visitEventExt, EVVCall call) {
        
        List<VisitTaskList> visitTasks = new ArrayList<>();
        
        if(call.getCallInfo() != null) {
            for (EVVCallInfo callInfo : call.getCallInfo()) {
                VisitTaskList visitTask = new VisitTaskList();

                visitTask.setVisitTaskListID(callInfo.getTaskId());
                visitTask.setTaskResultsReadingValue(callInfo.getTaskInfo());

                visitTasks.add(visitTask);

               // archerVVCallImportLogger.info(String.format("INPUT_DATA_FOR_CALL_MATCHING: TaskListID=%s , TaskResultsReadingValue= %s",callInfo.getTaskId(), callInfo.getTaskInfo()));
            }

            if (visitTasks.size() > 0) {
                visitEventExt.setVisitTaskLists(visitTasks);
            }
        }
        
    }
    
    
    
    private void setVisitEventTypeCode (VisitEventExt visitEventExt , EVVCall call) {
        
        if (call.getFobFlag() != null && !"0".equals(call.getFobFlag()) && StringUtils.isNotBlank(call.getFobValue())) {
            
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.FVV);
            
        } else if (StringUtils.isNotBlank(call.getAni())) {
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.TEL);
            
        } else if (call.isGpsFlag() && call.getLatitude() != null && call.getLongitude() != null) {
            
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.MVV);
        }
        
    }
    
    private Map<String, EVVCall> convertToEvvCall(ResultSet callResultSet) throws SQLException {
        Map<String, EVVCall> calls = new HashMap<>();

        while (callResultSet.next()) {
            
           EVVCall call = new EVVCall();

           call.setCallExpKey(callResultSet.getString("callexpkey"));
           call.setEventType(callResultSet.getString("event_type"));
           call.setAccount(callResultSet.getString("account"));
           call.setExportKey(callResultSet.getString("exportkey"));
           call.setSid(callResultSet.getString("sid"));
           call.setCallKey(callResultSet.getString("callkey"));
           call.setCallDtime(callResultSet.getString("call_dtime"));
           call.setAni(callResultSet.getString("ani"));
           call.setDnis(callResultSet.getString("dnis"));
           call.setN800num(callResultSet.getString("n800num"));
           call.setStxId(callResultSet.getString("stx_id"));
           call.setClientId(callResultSet.getString("client_id"));
           call.setParentCallKey(callResultSet.getString("parentcallkey"));
           call.setGpsFlag(callResultSet.getBoolean("gpsflag"));
           call.setLatitude(callResultSet.getBigDecimal("latitude"));
           call.setLongitude(callResultSet.getBigDecimal("longitude"));
           call.setCallerRecordFlag(callResultSet.getBoolean("callerrecordflag"));
           call.setCallerRecordFileName(callResultSet.getString("callerrecordfilename"));
           call.setSpeakerVFlag(callResultSet.getBoolean("speakervflag"));
           call.setInOutFlag(callResultSet.getString("inoutflag"));
           call.setHoursOffset(callResultSet.getInt("hours_offset"));
           call.setDuration(callResultSet.getInt("duration"));
           call.setFobFlag(callResultSet.getString("FOBflag"));
           call.setFobValue(callResultSet.getString("FOBValue"));
           call.setSelectedInOut(callResultSet.getString("selectedinout"));
           call.setTimeZoneName(callResultSet.getString("tzname"));

            if(StringUtils.isNotEmpty(call.getCallDtime())) {
                
                DateTime dateTime = dateTimeConverter.convertLocalTimeToUTC(call.getCallDtime(), call.getHoursOffset());
                call.setCallDtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
            }

           calls.put(call.getCallKey(), call);
        }
        
        return calls;

    }
}
