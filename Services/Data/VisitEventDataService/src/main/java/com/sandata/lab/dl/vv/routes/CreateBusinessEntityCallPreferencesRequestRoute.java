package com.sandata.lab.dl.vv.routes;


import org.apache.camel.builder.RouteBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/7/2016
 * Time: 8:15 AM
 */
public class CreateBusinessEntityCallPreferencesRequestRoute extends RouteBuilder {
    public static final String ACTIVEMQ = "activemq:queue:";
    public static final String DIRECT = "direct:reply_";

    public static String BSN_ENTITY_CALL_PREFERENCES_QRY = "BSN_ENTITY_CALL_PREFERENCES_QRY";
    public static final String GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE = "GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE";

    public static final String BSN_ENTITY_AGENCY_ID_QRY = "BSN_ENTITY_AGENCY_ID_QRY";
    public static final String BSN_ENTITY_AGENCY_ID_RESPONSE = "BSN_ENTITY_AGENCY_ID_RESPONSE";

    public static final String GET_AGENCY_ID_FOR_DNIS_2 = "GET_AGENCY_ID_FOR_DNIS_2";
    public static final String BSN_ENTITY_AGENCY_ID_QRY_2 = "BSN_ENTITY_AGENCY_ID_QRY_2";
    public static final String BSN_ENTITY_AGENCY_ID_RESPONSE_2 = "BSN_ENTITY_AGENCY_ID_RESPONSE_2";

    @Override
    public void configure() throws Exception {

        from(ACTIVEMQ+BSN_ENTITY_CALL_PREFERENCES_QRY+"?exchangePattern=InOut").routeId(BSN_ENTITY_CALL_PREFERENCES_QRY)
                .beanRef("visitEventRepository", "createCallPreferencesRequest")
        .to(ACTIVEMQ + GET_CALL_PREFERENCES_FOR_AGENCY_ID_RESPONSE);


        from(ACTIVEMQ+BSN_ENTITY_AGENCY_ID_QRY).routeId(BSN_ENTITY_AGENCY_ID_QRY)
                .beanRef("visitEventRepository", "createAgencyIdRequest")
                .to(ACTIVEMQ + BSN_ENTITY_AGENCY_ID_RESPONSE);

        from(ACTIVEMQ+BSN_ENTITY_AGENCY_ID_QRY_2).routeId(BSN_ENTITY_AGENCY_ID_QRY_2)
                .beanRef("visitEventRepository", "createAgencyIdRequest")
                .to(ACTIVEMQ + BSN_ENTITY_AGENCY_ID_RESPONSE_2);
    }

}
