package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/10/2015
 * Time: 4:03 PM
 */
public class VisitToStaffRequestAndAggregation implements org.apache.camel.Processor {
    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            VisitFact visitFact = (VisitFact)list.get(0);
            StaffFact staff = new StaffFact(visitFact.getStaffId(), visitFact.getDnis(), State.LOADED);
            exchange.getIn().setBody(staff, StaffFact.class);

        }
        else if(in.getBody() instanceof VisitFact) {
            //this visit is all set to send as an unplanned visit but we need to match it to a Staff first
            VisitFact visitFact = in.getBody(VisitFact.class);
            StaffFact staff = new StaffFact(visitFact.getStaffId(), visitFact.getDnis(), State.LOADED);
            exchange.getIn().setBody(staff, StaffFact.class);
            //exchange.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID, staff.getDnis() + staff.getStaffId().substring(3,5));
        }
    }
}
