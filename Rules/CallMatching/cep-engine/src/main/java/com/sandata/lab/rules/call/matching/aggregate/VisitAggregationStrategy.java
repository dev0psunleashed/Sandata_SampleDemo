package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.rules.call.matching.routes.AggregateVisitRoute;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 11/24/2015.
 */
public class VisitAggregationStrategy implements AggregationStrategy {
    Logger logger = LoggerFactory.getLogger(VisitAggregationStrategy.class);


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        ArrayList data = (ArrayList) newExchange.getIn().getBody(ArrayList.class);
        String requestId = (String) newExchange.getIn().getHeader(PrepForAggregation.REQUEST_UUID_FOR_AGGREGATION);
        //new exchange will contain the newly arrived exchange fro db request
        String dnis = (String)newExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT);
        String  correctedStaffId = (String)newExchange.getIn().getHeader(PrepForAggregation.CORRECTED_STAFF_ID);
        String databaseProvidedStaffId = (String)newExchange.getIn().getHeader(PrepForAggregation.DATABASE_PROVIDED_STAFF_ID);
        String name = (String)newExchange.getIn().getHeader("STATE");

        logger.info("VisitAggregationStrategy.aggregate() : correctedStaffId = " + correctedStaffId + ";");
        logger.info("VisitAggregationStrategy:: newExchange Header {}",
                (String)newExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT) );
        logger.info(String.format("VisitAggregationStrategy:: newExchange Header (%s)", requestId));
        ArrayList aggregatedData;
        Exchange result = newExchange;
        //to keep dnisAndStaffId header
        // first time
        if(oldExchange == null) {
            logger.warn(" VisitAggregationStrategy:: oldExchange was null");
            aggregatedData = new ArrayList();

        }
        else {
            aggregatedData = oldExchange.getIn().getBody(ArrayList.class);
            if(oldExchange.getIn().getHeader("STATE") != null && oldExchange.getIn().getHeader("STATE").equals(State.AGG_WAITING_FOR_RESPONSE.name())) {
                name = State.AGG_WAITING_FOR_RESPONSE.name();
                logger.info("Setting STATE = AGG_WAITING FOR RESPONSE");
            }
            logger.info("VisitAggregationStrategy:: oldExchange Header {} newExchange Header {}",
                    (String)oldExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT),
                    (String)newExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT) );
            if(correctedStaffId != null) {
                result.getIn().setHeader(PrepForAggregation.CORRECTED_STAFF_ID, correctedStaffId);
            }
            if(databaseProvidedStaffId != null) {
                result.getIn().setHeader(PrepForAggregation.DATABASE_PROVIDED_STAFF_ID, databaseProvidedStaffId);
            }

        }
        aggregatedData.addAll(data);
        result.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnis);
        result.getIn().setHeader(PrepForAggregation.REQUEST_UUID_FOR_AGGREGATION, requestId);

        result.getIn().setHeader("STATE", name);
        result.getIn().setBody(aggregatedData);

        logger.info("inside aggregation strategy: Returned Result exchange with size {}", aggregatedData.size());

        return result;
    }
}
