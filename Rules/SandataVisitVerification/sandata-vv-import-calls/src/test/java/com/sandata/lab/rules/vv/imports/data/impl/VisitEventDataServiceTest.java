package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.redis.connection.jedis.SandataJedisConnectionFactory;
import com.sandata.lab.redis.services.impl.SandataRedisCacheService;
import com.sandata.lab.rules.vv.imports.BaseTestSupport;
import com.sandata.lab.rules.vv.imports.data.transformers.EVVCallTransformer;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import com.sandata.lab.rules.vv.imports.utils.date.DateTimeConverter;
import mockit.Expectations;
import mockit.integration.junit4.JMockit;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mockit.Deencapsulation.*;

@RunWith(JMockit.class)
public class VisitEventDataServiceTest extends BaseTestSupport {

    private SandataJedisConnectionFactory jedisConnectionFactory;
    private RedisTemplate redisTemplate;
    private SandataRedisCacheService redisCacheService;
    private EVVRedisCallStore evvCallStore;

    private EVVOracleDataService evvOracleDataService;
    private VisitEventDataService visitEventDataService;

    private EVVCallTransformer evvCallTransformer;

    @Override
    protected void onSetup() throws IOException {

        jedisConnectionFactory = new SandataJedisConnectionFactory();
        jedisConnectionFactory.setHostName("172.31.0.91");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setDatabase(4);
        jedisConnectionFactory.setPassword("sandatalab");
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        redisCacheService = new SandataRedisCacheService("SandataVVImportCalls", 600, redisTemplate);

        evvCallStore = new EVVRedisCallStore();
        evvCallStore.setSandataRedisService(redisCacheService);

        visitEventDataService = new VisitEventDataService();

        evvCallTransformer = new EVVCallTransformer();
        evvCallTransformer.setDateTimeConverter(new DateTimeConverter());

        visitEventDataService.setEvvCallTransformer(evvCallTransformer);

        //visitEventDataService.setEvvCallStore(evvCallStore);
    }

    @Test
    public void transform_eight_calls_to_three_visiteventgroups() throws Exception
    {
        String dnis ="some800number";
        String dnis2 = "some888number";
        String dnis3 = "some877number";

        final List<EVVCall> calls = new ArrayList<>();
        final List<EVVCall> newcalls = new ArrayList<>();

        for(int i = 0; i < 7; i++)
        {
            EVVCall call = new EVVCall();

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

        exchange.getIn().setBody(calls);
        //List<VisitEventDNISGroup> visitEventDNISGroups = 
        visitEventDataService.transformCallsToVisitEventGroups(exchange);
        
        List<VisitEventDNISGroup> visitEventDNISGroups = (List<VisitEventDNISGroup>) exchange.getIn().getBody();

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

        final List<EVVCall> calls = new ArrayList<>();

        final List<EVVCall> newcalls = new ArrayList<>();

        for(int i = 0; i < 3; i++)
        {
            EVVCall call = new EVVCall();
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

        exchange.getIn().setBody(calls);
        //List<VisitEventDNISGroup> visitEventDNISGroups = 
        visitEventDataService.transformCallsToVisitEventGroups(exchange);
        
        List<VisitEventDNISGroup> visitEventDNISGroups = (List<VisitEventDNISGroup>) exchange.getIn().getBody();

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

        final List<EVVCall> calls = new ArrayList<>();


        final EVVCall call = new EVVCall();
        call.setSid(Integer.toString(1));

        call.setDnis(dnis);
        call.setAni(1 + "0");
        calls.add(call);

        final EVVCall call2 = new EVVCall();
        call2.setSid(Integer.toString(2));
        call2.setDnis(dnis);
        call2.setAni(1 + "1");
        calls.add(call2);

        EVVCall call3 = new EVVCall();
        call3.setSid(Integer.toString(3));
        call3.setDnis(dnis);
        call3.setAni(1 + "2");
        calls.add(call3);

        final List<EVVCall> newcalls = new ArrayList<>();
        newcalls.add(call);
        newcalls.add(call3);

        new Expectations(visitEventDataService) {{

            invoke(visitEventDataService, "returnNewCalls", calls);
            result = newcalls; times = 1;
        }};

        exchange.getIn().setBody(calls);
        //List<VisitEventDNISGroup> visitEventDNISGroups = 
        visitEventDataService.transformCallsToVisitEventGroups(exchange);
        
        List<VisitEventDNISGroup> visitEventDNISGroups = (List<VisitEventDNISGroup>) exchange.getIn().getBody();

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

        final List<EVVCall> calls = new ArrayList<>();


        final EVVCall call = new EVVCall();
        call.setSid(Integer.toString(1));

        DateTime dateTime = new DateTime();

        if (dateTime != null) {
            call.setCallDtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
        }


        call.setDnis(dnis);
        call.setAni(1 + "0");
        calls.add(call);

        evvCallStore.storeCalls(calls);

        VisitEventExt visitEventExt = new VisitEventExt();
        visitEventExt.setAutomaticNumberIdentification(call.getAni());
        visitEventExt.setDialedNumberIdentificationService(dnis);
        visitEventExt.setVisitEventDatetime(call.getCallDtimeUTC().toDateTime(DateTimeZone.UTC).toDate());

        EVVCall call2 = evvCallTransformer.createCall(visitEventExt);


        EVVCall storedCall = evvCallStore.getCallFromStore(call2);

        assertEquals(call,storedCall);

        }


        @Test
        public void test_collectionutiles_subtract_lsit () {

            String dnis ="some800number";
            final EVVCall call = new EVVCall();
            call.setSid(Integer.toString(1));

            DateTime dateTime = new DateTime();

            if (dateTime != null) {
                call.setCallDtimeUTC(dateTime.toDateTime(DateTimeZone.UTC));
            }


            call.setDnis(dnis);
            call.setAni(1 + "0");

            VisitEventExt visitEventExt = new VisitEventExt();
            visitEventExt.setAutomaticNumberIdentification(call.getAni());
            visitEventExt.setDialedNumberIdentificationService(dnis);
            visitEventExt.setVisitEventDatetime(call.getCallDtimeUTC().toDateTime(DateTimeZone.UTC).toDate());


            EVVCall call1 = evvCallTransformer.createCall(visitEventExt);
            EVVCall call2 = evvCallTransformer.createCall(visitEventExt);

            List<EVVCall> lst1 = new ArrayList<EVVCall>();
            lst1.add(call1);

            List<EVVCall> lst2 = new ArrayList<EVVCall>();
            lst2.add(call2);

            EVVCall call3 = evvCallTransformer.createCall(visitEventExt);
            call3.setAni("000000000");
            lst2.add(call3);

            List<EVVCall> newCalls = (List<EVVCall>) CollectionUtils.subtract(lst1, lst2);

            assertEquals(0,newCalls.size());

        }

}
