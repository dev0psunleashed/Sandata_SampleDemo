package com.sandata.lab.rules.data.service.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.fact.AgencyGPSSetting;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.model.RouteDefinition;

/**
 * <p>Route for retrieving the agency gps threshold setting.</p>
 *
 * @author thanhxle
 */
public class GetAgencyGPSSetting extends AbstractRoute {

    //TODO: for temporary now
    @PropertyInject("{{visit.gps.distance.threshold.miles}}")
    private double visitGpsDistanceThreshold = 0.5f;

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE
                + App.ID.GET_AGENCY_GPS_DISTANCE_THRESHOLD_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_AGENCY_GPS_DISTANCE_THRESHOLD_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying AGENCY GPS DISTANCE THRESHOLD SETTING.")
        //TODO: GET FROM WICH TABLE , KEY ?
        //.beanRef("visitVerificationDataService", "getStaffEntities")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                AgencyGPSSetting agencyGPSSetting = new AgencyGPSSetting();
                agencyGPSSetting.setDistanceThreshold(visitGpsDistanceThreshold);
                exchange.getIn().setBody(agencyGPSSetting);
            }
        })
        .log(LoggingLevel.INFO, "AgencyGPSSetting: ${body}");
    }
}
