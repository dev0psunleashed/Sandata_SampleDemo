package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.processors.ProducerPojo;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 11/24/2015.
 */
public class StartVisitPredicate implements Predicate {
    Logger logger = LoggerFactory.getLogger(StartVisitPredicate.class);
    @Override
    public boolean matches(Exchange exchange) {
        //could eventually get a visit a visit event and a schedule
        logger.info(" IN StartVisitPredicate ");
        if (exchange.getIn().getBody() instanceof ArrayList) {
            ArrayList list = exchange.getIn().getBody(ArrayList.class);
            if (list.size() > 1 && exchange.getIn().getHeader("STATE") != null && ((String)exchange.getIn().getHeader("STATE")).equals(State.AGG_WAITING_FOR_RESPONSE.name())) {

                logger.info("==============================================================");
                logger.info(" StartVisitPredicate : size was greater than 1 returning true ");
                logger.info("==============================================================");
                return true;
            }
            if(exchange.getIn().getHeader("STATE") != null && !((String)exchange.getIn().getHeader("STATE")).equals(State.AGG_WAITING_FOR_RESPONSE.name())) {
                logger.info("header STATE was " + (String)exchange.getIn().getHeader("STATE"));
            }

        }
        logger.info(" IN StartVisitPredicate : returning false");
        return false;
    }
}
