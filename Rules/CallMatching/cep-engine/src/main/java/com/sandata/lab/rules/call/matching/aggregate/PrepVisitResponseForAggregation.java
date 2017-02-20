package com.sandata.lab.rules.call.matching.aggregate;


import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 11/24/2015.
 */
public class PrepVisitResponseForAggregation implements Processor {
    public static final String DNIS_AND_STAFFID = "dnisAndStaffId";
    Logger logger = LoggerFactory.getLogger(PrepVisitResponseForAggregation.class);
    @Override
    public void process(Exchange exchange) throws Exception {

        if(exchange.getIn().getBody() instanceof Visit) {
            Visit visit = exchange.getIn().getBody(Visit.class);
            VisitFact visitFact = new VisitFact(visit);

            String dnisandstaffid = visitFact.getDnis();
            dnisandstaffid += visitFact.getStaffId();
            exchange.getIn().setHeader(DNIS_AND_STAFFID, dnisandstaffid);
            visitFact.setState(State.AGG_INSERTED_FROM_RESPONSE);
            ArrayList<VisitFact> list = new ArrayList<VisitFact>();
            list.add(visitFact);
            exchange.getIn().setBody(list);
            logger.info("set visitFact {}", visitFact);
        }
        else
        {
            logger.info("unexpected objected {}", exchange.getIn().getBody());
        }
    }
}
