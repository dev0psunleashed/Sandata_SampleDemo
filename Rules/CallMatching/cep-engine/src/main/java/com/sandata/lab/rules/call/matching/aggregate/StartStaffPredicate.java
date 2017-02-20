package com.sandata.lab.rules.call.matching.aggregate;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/10/2015
 * Time: 8:07 PM
 */
public class StartStaffPredicate implements Predicate {
    Logger logger = LoggerFactory.getLogger(StartStaffPredicate.class);

    /**
     * Evaluates the predicate on the message exchange and returns true if this
     * exchange matches the predicate
     *
     * @param exchange the message exchange
     * @return true if the predicate matches
     */
    @Override
    public boolean matches(Exchange exchange) {

        //could eventually get a visit a visit event and a schedule
        logger.info(" IN StartVisitPredicate ");
        if (exchange.getIn().getBody() instanceof ArrayList) {
            ArrayList list = exchange.getIn().getBody(ArrayList.class);
            if (list.size() > 1) {
                logger.info(" StartStaffPredicate : size was greater than 1 returning true ");
                return true;
            }

        }
        logger.info(" In StartStaffPredicate : returning false");
        return false;
    }
}
