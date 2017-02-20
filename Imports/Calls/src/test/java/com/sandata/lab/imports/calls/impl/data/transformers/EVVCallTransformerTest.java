package com.sandata.lab.imports.calls.impl.data.transformers;

import com.sandata.lab.imports.calls.BaseTestSupport;
import com.sandata.lab.imports.calls.model.external.EVV.Call;
import com.sandata.lab.imports.calls.model.external.EVV.CallInfo;
import com.sandata.lab.imports.calls.utils.date.DateTimeConverter;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import mockit.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.TimeZone;

public class EVVCallTransformerTest extends BaseTestSupport
{
  private EVVCallTransformer evvCallTransformer;

    @Override
    protected void onSetup() throws IOException {

        evvCallTransformer = new EVVCallTransformer();
        evvCallTransformer.setDateTimeConverter(new DateTimeConverter());
    }

    @Test
    public void  callresultset_contains_4calls_and_map_contains_4calls_contain_4callinfos_() throws Exception
    {
        ResultSet callResultSet = new MockUp<ResultSet>() {
            int callKey;

            @Mock
            boolean next() {
                callKey++;
                return callKey < 5;
            }

            @Mock
            String getString(String columnName) {
                if (columnName.equalsIgnoreCase("callkey")) {
                    return Integer.toString(callKey);
                }

                if (columnName.equalsIgnoreCase("call_dtime")) {
                    return "2015-11-02 21:12:50";
                }

                return null;
            }

        }.getMockInstance();

        ResultSet callInfoResultSet = new MockUp<ResultSet>() {
            int callKey = 0;
            int count = 0;
            @Mock
            boolean next() {

                if(callKey == 4)
                {
                    callKey = 1;
                }
                else
                {
                    callKey++;
                }

                count++;


                return count < 21;
            }

            @Mock
            String getString(String columnName) {
                if (columnName.equalsIgnoreCase("callkey")) {
                    return Integer.toString(callKey);
                }

                return null;
            }

        }.getMockInstance();

        List<Call> calls = evvCallTransformer.transformCallResultSetToCall(callResultSet, callInfoResultSet);

        int callSize = calls.size();

        assert(callSize == 4);

        for (Call value : calls) {

            List<CallInfo> callInfos = value.getCallInfos();

            int callInfoSize = callInfos.size();

            assertTrue(callInfoSize == 5);

            for(CallInfo callInfo: callInfos)
            {
                String callCallKey = value.getCallKey();
                String callInfoCallKey = callInfo.getCallKey();

                assertTrue(callCallKey.equalsIgnoreCase(callInfoCallKey));
            }
        }

    }

    @Test
    public void  callresultset_contains_4calls_and__callinforesult_and_map_contains_4calls() throws Exception
    {
        ResultSet callResultSet = new MockUp<ResultSet>() {
            int callKey;

            @Mock
            boolean next() {
                callKey++;
                return callKey < 5;
            }

            @Mock
            String getString(String columnName) {
                if (columnName.equalsIgnoreCase("callkey")) {
                    return Integer.toString(callKey);
                }

                if (columnName.equalsIgnoreCase("call_dtime")) {
                    return "2015-11-02 21:12:50";
                }

                if (columnName.equalsIgnoreCase("call_dtime_utc")) {
                    return "2015-11-02T23:12:50.000Z";
                }

                if (columnName.equalsIgnoreCase("hours_offset")) {
                    return "-2";
                }

                return null;
            }

            @Mock
             int getInt(String columnName) {

                if (columnName.equalsIgnoreCase("hours_offset")) {
                    return -2;
                }

                return 0;
            }

        }.getMockInstance();

        ResultSet callInfoResultSet = null;

        List<Call> calls = evvCallTransformer.transformCallResultSetToCall(callResultSet, callInfoResultSet);

        int callSize = calls.size();

        assert(callSize == 4);

        for (Call value : calls) {

            assertTrue(value.getCall_dtimeUTC().toString().equals("2015-11-02T23:12:50.000Z"));
            List<CallInfo> callInfos = value.getCallInfos();

            assertNull(callInfos);
        }

    }

    @Test
    public void  callresultset_is_null() throws Exception
    {
        ResultSet callResultSet = null;

        ResultSet callInfoResultSet = null;

        List<Call> calls = evvCallTransformer.transformCallResultSetToCall(callResultSet, callInfoResultSet);


        assertNull(calls);
    }

    @Test
    public void create_visitevent_from_fvvcall() throws Exception
    {
        Call call = new Call();
        call.setSid(Integer.toString(3));

        DateTime dateTime = new DateTime(DateTime.parse("2015-11-05T18:40:00.000Z"));
        call.setFobFlag("2");
        call.setCall_dtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));

        VisitEvent visitEvent = evvCallTransformer.createVisitEvent(call);

        assertNotNull(visitEvent);

        DateTime edateTime = new DateTime(visitEvent.getVisitEventDatetime());
        assertTrue(edateTime.toDateTime(DateTimeZone.UTC).toString().equals("2015-11-05T18:40:00.000Z"));
    }
}
