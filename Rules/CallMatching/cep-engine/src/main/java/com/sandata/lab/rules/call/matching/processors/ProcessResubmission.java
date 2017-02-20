package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 12/19/2015.
 */
public class ProcessResubmission implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        if(in.getBody() instanceof ArrayList) {
            ArrayList list = in.getBody(ArrayList.class);
            VisitFact visitFact = (VisitFact)list.get(0);
            VisitEvent visitEvent = visitFact.getCallCallIn();
            //visitEvent.setCallInIndicator(true);
            in.setBody(visitEvent);
            String dnisAndStafIdAndPatient = visitFact.getDnis()+visitFact.getStaffId();
            if(visitFact.isClientEntered()) {
                dnisAndStafIdAndPatient += visitEvent.getPatientID();
            }
            else {
                dnisAndStafIdAndPatient += visitFact.getAni();
            }


            //get the agency task ID for call out and set here
            in.setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnisAndStafIdAndPatient);
        }
    }
}
