package com.sandata.lab.imports.calls.impl.data;

import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.imports.calls.BaseTestSupport;
import com.sandata.lab.imports.calls.impl.VisitEventDataService;
import com.sandata.lab.imports.calls.impl.data.transformers.EVVCallTransformer;
import com.sandata.lab.imports.calls.model.external.EVV.Call;
import com.sandata.lab.imports.calls.utils.date.DateTimeConverter;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import static mockit.Deencapsulation.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JMockit.class)
public class VisitEventDataServiceTest extends BaseTestSupport {

    private EVVOracleDataService evvOracleDataService;
    private VisitEventDataService visitEventDataService;


    private EVVCallStore evvCallStore;

    private EVVCallTransformer evvCallTransformer;

    @Override
    protected void onSetup() throws IOException {

        visitEventDataService = new VisitEventDataService();

        evvCallTransformer = new EVVCallTransformer();
        evvCallTransformer.setDateTimeConverter(new DateTimeConverter());

        visitEventDataService.setEvvCallTransformer(evvCallTransformer);

        visitEventDataService.setEvvCallStore(evvCallStore);
    }

    @Test
    public void transform_eight_calls_to_three_visiteventgroups() throws Exception
    {
        String dnis ="some800number";
        String dnis2 = "some888number";
        String dnis3 = "some877number";

        final List<Call> calls = new ArrayList<>();
        final List<Call> newcalls = new ArrayList<>();

        for(int i = 0; i < 7; i++)
        {
            Call call = new Call();

            call.setSid(Integer.toString(i));

            if(i < 2) {
                call.setDnis(dnis);
            }
            else if (i < 4)
            {
                call.setDnis(dnis2);
            }
            else if (i < 7)
            {
                call.setDnis(dnis3);
            }

            call.setAni(1 + "i");

            calls.add(call);
            newcalls.add(call);
        }

        new Expectations(visitEventDataService) {{

            invoke(visitEventDataService, "returnNewCalls", calls);
            result = newcalls; times = 1;
        }};

        List<VisitEventDNISGroup> visitEventDNISGroups = visitEventDataService.transformCallsToVisitEventGroups(null,calls);

        int groupSize = visitEventDNISGroups.size();

        assertTrue(groupSize == 3);


        for(VisitEventDNISGroup visitEventDNISGroup: visitEventDNISGroups)
        {
            int size = visitEventDNISGroup.getVisitEvents().size();

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis))
            {
                assertTrue(size == 2);
            }

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis2))
            {
                assertTrue(size == 2);
            }

            if(visitEventDNISGroup.getDnis().equalsIgnoreCase(dnis3))
            {
                assertTrue(size == 3);
            }

        }
    }

    @Test
    public void transform_three_calls_same_dnis_to_a_visiteventgroup() throws Exception
    {
        String dnis ="some800number";

        final List<Call> calls = new ArrayList<>();

        final List<Call> newcalls = new ArrayList<>();

        for(int i = 0; i < 3; i++)
        {
            Call call = new Call();
            call.setSid(Integer.toString(i));

            call.setDnis(dnis);
            call.setAni(1 + "i");

            calls.add(call);
            newcalls.add(call);
        }

        new Expectations(visitEventDataService) {{

            invoke(visitEventDataService, "returnNewCalls", calls);
            result = newcalls; times = 1;
        }};

        List<VisitEventDNISGroup> visitEventDNISGroups = visitEventDataService.transformCallsToVisitEventGroups(null, calls);

        int groupSize = visitEventDNISGroups.size();

        assertTrue(groupSize == 1);


        int eventSize= 0;

        for(VisitEventDNISGroup visitEventDNISGroup: visitEventDNISGroups)
        {
            eventSize = visitEventDNISGroup.getVisitEvents().size();

        }

        assertTrue(eventSize == 3);
    }

    @Test
    public void transform_three_calls_same_dnis_and_one_already_exists_in_store() throws Exception
    {
        String dnis ="some800number";

        final List<Call> calls = new ArrayList<>();


        final Call call = new Call();
        call.setSid(Integer.toString(1));

        call.setDnis(dnis);
        call.setAni(1 + "0");
        calls.add(call);

        final Call call2 = new Call();
        call2.setSid(Integer.toString(2));
        call2.setDnis(dnis);
        call2.setAni(1 + "1");
        calls.add(call2);

        Call call3 = new Call();
        call3.setSid(Integer.toString(3));
        call3.setDnis(dnis);
        call3.setAni(1 + "2");
        calls.add(call3);

        final List<Call> newcalls = new ArrayList<>();
        newcalls.add(call);
        newcalls.add(call3);

        new Expectations(visitEventDataService) {{

            invoke(visitEventDataService, "returnNewCalls", calls);
            result = newcalls; times = 1;
        }};

        List<VisitEventDNISGroup> visitEventDNISGroups = visitEventDataService.transformCallsToVisitEventGroups(null, calls);

        int groupSize = visitEventDNISGroups.size();

        assertTrue(groupSize == 1);


        int eventSize= 0;

        for(VisitEventDNISGroup visitEventDNISGroup: visitEventDNISGroups)
        {
            eventSize = visitEventDNISGroup.getVisitEvents().size();

        }

        assertTrue(eventSize == 2);
    }


    @Test
    public void process_called_and_deleted_from_store() throws Exception
    {
        String dnis ="some800number";

        final List<Call> calls = new ArrayList<>();


        final Call call = new Call();
        call.setSid(Integer.toString(1));

        DateTime dateTime = new DateTime();

        if (dateTime != null) {
            call.setCall_dtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
        }


        call.setDnis(dnis);
        call.setAni(1 + "0");
        calls.add(call);

        EVVCallStore evvCallStore2 = new EVVCallStore();

        evvCallStore2.storeCalls(calls);


        VisitEventExt visitEventExt = new VisitEventExt();
        visitEventExt.setAutomaticNumberIdentification(call.getAni());
        visitEventExt.setDialedNumberIdentificationService(dnis);
        visitEventExt.setVisitEventDatetime(call.getCall_dtimeUTC().toDateTime(DateTimeZone.UTC).toDate());

        Call call2 = evvCallTransformer.createCall(visitEventExt);


        Call storedCall = evvCallStore2.getCallFromStore(call2);

        assertEquals(call,storedCall);

        }



}
