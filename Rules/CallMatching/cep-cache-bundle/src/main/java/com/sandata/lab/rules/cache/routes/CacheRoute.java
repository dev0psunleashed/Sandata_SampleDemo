package com.sandata.lab.rules.cache.routes;

import com.sandata.lab.rules.cache.processors.ProcessCacheEvents;
import com.sandata.lab.rules.cache.processors.ProcessorNotInCache;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cache.CacheConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/5/2016
 * Time: 12:21 PM
 * the settings timeToLiveSeconds will not be used when eternal=true, setting eternal = true means you dont want it to expire.
 *
 */
public class CacheRoute extends RouteBuilder {
    public static final String BSN_ENTITY_CALL_PREFERENCES_QRY = "BSN_ENTITY_CALL_PREFERENCES_QRY";
    public static final String GET_CALL_PREFERENCES_FOR_AGENCY_ID = "GET_CALL_PREFERENCES_FOR_AGENCY_ID";
    public static final String GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE = "GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE";

    public static final String GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS = "GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS";
    public static final String BSN_ENTITY_AGENCY_ID_QRY = "BSN_ENTITY_AGENCY_ID_QRY";
    public static final String BSN_ENTITY_AGENCY_ID_RESPONSE = "BSN_ENTITY_AGENCY_ID_RESPONSE";

    public static final String GET_AGENCY_ID_FOR_DNIS_2 = "GET_AGENCY_ID_FOR_DNIS_2";
    public static final String BSN_ENTITY_AGENCY_ID_QRY_2 = "BSN_ENTITY_AGENCY_ID_QRY_2";
    public static final String BSN_ENTITY_AGENCY_ID_RESPONSE_2 = "BSN_ENTITY_AGENCY_ID_RESPONSE_2";

    public static final String CALL_MATCHING_KEY = "CallMatchingKey";
    public static final String CACHE_CONFIG = "cache://CEPCache" +
            "?maxElementsInMemory=1000" +
            "&memoryStoreEvictionPolicy=MemoryStoreEvictionPolicy.LFU" +
            "&overflowToDisk=true" +
            "&eternal=true" +
            "&timeToLiveSeconds=300" +
            "&timeToIdleSeconds=300" +
            "&diskPersistent=true" +
            "&diskExpiryThreadIntervalSeconds=300";

    @Override
    public void configure() throws Exception {
        from(CACHE_CONFIG).routeId("ConfiguringCEP_CACHE")
                .process(new ProcessCacheEvents());

        from("activemq:queue:" + GET_CALL_PREFERENCES_FOR_AGENCY_ID).routeId(GET_CALL_PREFERENCES_FOR_AGENCY_ID)
                .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_GET))
                .setHeader(CacheConstants.CACHE_KEY, constant(body().toString()))
                .setHeader(CALL_MATCHING_KEY, constant(body().toString()))
                .to(CACHE_CONFIG)
                .log("Checked Cache ===>")
                .process(new ProcessorNotInCache())
                .choice()
                    .when(header(CacheConstants.CACHE_ELEMENT_WAS_FOUND).isNull())
                        .to("activemq:queue:" + BSN_ENTITY_CALL_PREFERENCES_QRY)
                        .stop()
                    .otherwise()
                        .log("Value found in cache!")
                    .end()
                .to("direct:reply_" + GET_CALL_PREFERENCES_FOR_AGENCY_ID);



        from("activemq:queue:" + GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE).routeId(GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE)
                .choice()
                    .when(body().isNotNull())
                        .log("Checked Cache returned null so we queried Database  ===>")
                        .process(new ProcessorNotInCache())
                        .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_ADD))
                        .to(CACHE_CONFIG)
                        .log("Wrote results to Cache, now returning ===>")
                    .otherwise()
                        .log("Results were null ===>")
                    .end()
                .to("direct:reply_" + GET_CALL_PREFERENCES_FOR_AGENCY_ID);

//GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS request and response
        from("activemq:queue:" + GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS).routeId(GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS)
                .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_GET))
                .setHeader(CacheConstants.CACHE_KEY, constant(body().toString()))
                .setHeader(CALL_MATCHING_KEY, constant(body().toString()))
                .to(CACHE_CONFIG)
                .log("Checked Cache ===>")
                .process(new ProcessorNotInCache())
                .choice()
                    .when(header(CacheConstants.CACHE_ELEMENT_WAS_FOUND).isNull())
                        .to("activemq:queue:" + BSN_ENTITY_AGENCY_ID_QRY)
                    .stop()
                    .otherwise()
                        .log("Value found in cache!")
                    .end()
                .to("direct:reply_" + GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS);

        from("activemq:queue:" + BSN_ENTITY_AGENCY_ID_RESPONSE).routeId(BSN_ENTITY_AGENCY_ID_RESPONSE)
                .choice()
                    .when(body().isNotNull())
                        .log("Checked Cache returned null so we queried Database  ===>")
                        .process(new ProcessorNotInCache())
                        .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_ADD))
                        .to(CACHE_CONFIG)
                        .log("Wrote results to Cache, now returning ===>")
                    .otherwise()
                        .log("Results were null ===>")
                    .end()
                .to("direct:reply_"+ GET_CALL_PREFERENCES_FOR_AGENCY_ID_FOR_DNIS);

 //GET_AGENCY_ID_FOR_DNIS_2 request and response

        from("activemq:queue:" + GET_AGENCY_ID_FOR_DNIS_2).routeId(GET_AGENCY_ID_FOR_DNIS_2)
                .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_GET))
                .setHeader(CacheConstants.CACHE_KEY, constant(body().toString()))
                .setHeader(CALL_MATCHING_KEY, constant(body().toString()))
                .to(CACHE_CONFIG)
                .log("Checked Cache ===>")
                .process(new ProcessorNotInCache())
                .choice()
                    .when(header(CacheConstants.CACHE_ELEMENT_WAS_FOUND).isNull())
                        .to("activemq:queue:" + BSN_ENTITY_AGENCY_ID_QRY_2)
                    .stop()
                .otherwise()
                    .log("Value found in cache!")
                .end()
                .to("direct:reply_"+GET_AGENCY_ID_FOR_DNIS_2);


        from("activemq:queue:" + BSN_ENTITY_AGENCY_ID_RESPONSE_2).routeId(BSN_ENTITY_AGENCY_ID_RESPONSE_2)
                .choice()
                    .when(body().isNotNull())
                        .log("Checked Cache returned null so we queried Database  ===>")
                        .process(new ProcessorNotInCache())
                        .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_ADD))
                        .to(CACHE_CONFIG)
                        .log("Wrote results to Cache, now returning ===>")
                    .otherwise()
                        .log("Results were null ===>")
                .end()
                .to("direct:reply_"+GET_AGENCY_ID_FOR_DNIS_2);

    }
}