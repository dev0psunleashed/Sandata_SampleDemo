package com.sandata.lab.rules.data.service.routes;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * <p>Route for retrieving exceptions setting by agency/BE_ID.</p>
 *
 * @author thanhxle
 */
public class GetAgencyLevelExceptionListRoute extends AbstractRoute {

    public static final String AGENCY_LEVEL_EXCP_LIST = "AGENCY_LEVEL_EXCP_LIST_";
    //public static final String PAYER_CONTRACT_LIST_ = "PAYER_CONTRACT_LIST_";

    @Override
    protected String getStartEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.GET_AGENCY_LEVEL_EXCP_LIST_ROUTE.toString();
    }

    @Override
    protected String getRouteId() {
        return App.ID.GET_AGENCY_LEVEL_EXCP_LIST_ROUTE.toString();
    }

    @Override
    protected void addMoreRouteDefinition(RouteDefinition definition) {

        definition
        .threads().executorServiceRef("sandataVvDataServiceThreadPool")
        .log(LoggingLevel.INFO, "Querying visit exception list at agency level from database or REDIS.")
        .process(new Processor() {
			
			@Override
			public void process(final Exchange exchange) throws Exception {
				List objects = exchange.getIn().getBody(List.class);
				VisitFact visitFact = null;
				for (Object object : objects) {
					if (object instanceof VisitFact) {
						//
						visitFact = (VisitFact) object;
						break;
					}
				}
				
				if (visitFact != null) {
					exchange.getIn().setHeader("cacheKey", AGENCY_LEVEL_EXCP_LIST + visitFact.getBusinessEntityId());
					exchange.getIn().setHeader("visitFact", visitFact);
				}
				
			}
		})
        .bean("visitVerificationCacheHandler","getCachedData")
        .log("Visit exception setting for agency level from cache : ${body}")
        .choice()
        .when(body().isNull())
        // in case of there no data from cache, then get from database
        .bean("visitVerificationExceptionDataService", "getBeExcpLst")
        //put result from database to REDIS
        .bean("visitVerificationCacheHandler","insertData")
        .otherwise()
        //convert cached string data to call preferences
        .bean("visitVerificationCacheHandler","fromJsonToBusinessEntityExceptionListExt")
        .end()
        .log(LoggingLevel.INFO, "Visit exception setting result: ${body}");
    }
}
