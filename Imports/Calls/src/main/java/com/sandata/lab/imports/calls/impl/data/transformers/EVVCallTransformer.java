package com.sandata.lab.imports.calls.impl.data.transformers;

import com.sandata.lab.imports.calls.model.external.EVV.Call;
import com.sandata.lab.imports.calls.model.external.EVV.CallInfo;
import com.sandata.lab.imports.calls.utils.date.DateTimeConverter;
import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EVVCallTransformer {
    private static final Logger LOG = LoggerFactory.getLogger(EVVCallTransformer.class);
    
    private DateTimeConverter dateTimeConverter;

    public void setDateTimeConverter(DateTimeConverter dateTimeConverter)
    {
        this.dateTimeConverter = dateTimeConverter;
    }

    /**
     * Transform ResultSet to EVV Call object
     * @param callResultSet = Call resultset from Oracle
     * @param callInfoResultSet = Tasks associated with call from Oracle
     * @return
     * @throws Exception
     */
    public List<Call> transformCallResultSetToCall(ResultSet callResultSet, ResultSet callInfoResultSet) throws Exception
    {
        if(callResultSet == null)
        {
            return null;
        }

        Map<String, Call> calls = new HashMap<>();

        while (callResultSet.next())
        {
           Call call = new Call();

           call.setCallExpKey(callResultSet.getString("callexpkey"));
           call.setEvent_type(callResultSet.getString("event_type"));
           call.setAccount(callResultSet.getString("account"));
           call.setExportKey(callResultSet.getString("exportkey"));
           call.setSid(callResultSet.getString("sid"));
           call.setCallKey(callResultSet.getString("callkey"));
           call.setCall_dtime(callResultSet.getString("call_dtime"));
           call.setAni(callResultSet.getString("ani"));
           call.setDnis(callResultSet.getString("dnis"));
           call.setN800num(callResultSet.getString("n800num"));
           call.setStx_id(callResultSet.getString("stx_id"));
           call.setClient_id(callResultSet.getString("client_id"));
           call.setParentcallKey(callResultSet.getString("parentcallkey"));
           call.setGpsFlag(callResultSet.getBoolean("gpsflag"));
           call.setLatitude(callResultSet.getBigDecimal("latitude"));
           call.setLongitude(callResultSet.getBigDecimal("longitude"));
           call.setCallerRecordFlag(callResultSet.getBoolean("callerrecordflag"));
           call.setCallerRecordFileName(callResultSet.getString("callerrecordfilename"));
           call.setSpeakerVFlag(callResultSet.getBoolean("speakervflag"));
           call.setInOutFlag(callResultSet.getString("inoutflag"));
           call.setHours_offset(callResultSet.getInt("hours_offset"));
           call.setDuration(callResultSet.getInt("duration"));
           call.setFobFlag(callResultSet.getString("FOBflag"));
           call.setFobValue(callResultSet.getString("FOBValue"));
           call.setSelectedInOut(callResultSet.getString("selectedinout"));
            call.setTzName(callResultSet.getString("tzname"));

            if(StringUtils.isNotEmpty(call.getCall_dtime()))
            {
                DateTime dateTime = dateTimeConverter.convertLocalTimeToUTC(call.getCall_dtime(), call.getHours_offset());
                call.setCall_dtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
            }

           calls.put(call.getCallKey(), call);
        }

        if(callInfoResultSet != null) {
            while (callInfoResultSet.next()) {
                CallInfo callInfo = new CallInfo();

                callInfo.setCallExpKey(callInfoResultSet.getString("callexpkey"));
                callInfo.setAccount(callInfoResultSet.getString("account"));
                callInfo.setExportKey(callInfoResultSet.getString("exportkey"));
                callInfo.setSid(callInfoResultSet.getString("sid"));
                callInfo.setCallKey(callInfoResultSet.getString("callkey"));
                callInfo.setTask_id(callInfoResultSet.getString("task_id"));
                callInfo.setTask_info(callInfoResultSet.getString("task_info"));

                Call call = calls.get(callInfo.getCallKey());

                if (call != null) {
                    List<CallInfo> callCallInfo = call.getCallInfos();

                    if (callCallInfo == null) {
                        callCallInfo = new ArrayList<>();
                    }

                    callCallInfo.add(callInfo);

                    call.setCallInfos(callCallInfo);
                }
            }
        }

        return new ArrayList<>(calls.values());
    }

    public Call createCall(VisitEventExt visitEventExt)
    {
        Call call = new Call();
        call.setAni(visitEventExt.getAutomaticNumberIdentification());
        call.setStx_id(visitEventExt.getStaffID());
        call.setClient_id(visitEventExt.getPatientID());
        call.setDnis(visitEventExt.getDialedNumberIdentificationService());

        if(visitEventExt.getVisitEventDatetime() != null) {
            DateTime dateTime = new DateTime(visitEventExt.getVisitEventDatetime());

            if (dateTime != null) {
                call.setCall_dtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
            }
        }

        return call;
    }

    /**
     * Create VisitEvent object derived from Call object
     * @param call = Evv call object
     * @return VisitEvent = Call Import service object for visits (calls)
     */
    public VisitEventExt createVisitEvent(Call call) throws Exception
    {
        VisitEventExt visitEventExt = new VisitEventExt();

        visitEventExt.setDialedNumberIdentificationService(call.getDnis());
        visitEventExt.setAutomaticNumberIdentification(call.getAni());
        visitEventExt.setStaffID(call.getStx_id());
        visitEventExt.setPatientID(call.getClient_id());
        visitEventExt.setTimezoneName(call.getTzName());
        
        LOG.info("INPUT_DATA_FOR_CALL_MATCHING: DNIS={} , ANI={}, Staff ID={},Patient ID={}", 
                new Object[]{ call.getDnis(), call.getAni(), call.getStx_id(), call.getClient_id() });

        if(call.getCall_dtimeUTC() != null)
        {
            visitEventExt.setVisitEventDatetime(call.getCall_dtimeUTC().toDateTime(DateTimeZone.UTC).toDate());
            LOG.info("INPUT_DATA_FOR_CALL_MATCHING: VisitEventDatetime={}", visitEventExt.getVisitEventDatetime());
        }


        //Set event coordinates
        visitEventExt.setCoordinateLatitude(call.getLatitude());
        visitEventExt.setCoordinateLongitide(call.getLongitude());
        
        // check the call type
        if (call.getFobFlag() != null
                && !"0".equals(call.getFobFlag())
                && StringUtils.isNotBlank(call.getFobValue())) {
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.FVV);
        } else if (StringUtils.isNotBlank(call.getAni())) {
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.TEL);
        } else if (call.getGpsFlag()
                && call.getLatitude() != null
                && call.getLongitude() != null) {
            visitEventExt.setVisitEventTypeCode(VisitEventTypeCode.MVV);
        }

        List<VisitTaskList> visitTasks = new ArrayList<>();


        if(call.getCallInfos() != null) {
            for (CallInfo callInfo : call.getCallInfos()) {
                VisitTaskList visitTask = new VisitTaskList();

                visitTask.setVisitTaskListID(callInfo.getTask_id());
                visitTask.setTaskResultsReadingValue(callInfo.getTask_info());

                visitTasks.add(visitTask);
                
                LOG.info("INPUT_DATA_FOR_CALL_MATCHING: TaskListID={} , TaskResultsReadingValue= {}", 
                        new Object[]{ callInfo.getTask_id(), callInfo.getTask_info() });
            }

            if (visitTasks.size() > 0) {
                visitEventExt.setVisitTaskLists(visitTasks);
            }
        }

        return visitEventExt;
    }
}
