package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.rules.call.model.OraStaffFact;
import com.sandata.lab.rules.call.model.StaffFact;
import com.sandata.lab.rules.call.model.State;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

        import java.util.ArrayList;

/**
 * Created by tom.dornseif on 12/23/2015.
 */
public class StaffAndVisitEventAggregationStrategy implements org.apache.camel.processor.aggregate.AggregationStrategy {

    Logger logger = LoggerFactory.getLogger(StaffAndVisitEventAggregationStrategy.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        ArrayList aggregatedData;
        Exchange result = newExchange;
        Message in = newExchange.getIn();
        String dnis;
        String staffId;
        String dnisAndStaffId = null;
        if (oldExchange == null)
            aggregatedData = new ArrayList();
        else
            aggregatedData = oldExchange.getIn().getBody(ArrayList.class);

        if (in.getBody() instanceof StaffFact) {
            StaffFact staff = in.getBody(StaffFact.class);
            staffId = staff.getStaffId();
            if (oldExchange == null) {
                logger.error("visitEvent should get here before staffId is returned from database!");
                logger.error("staffId was " + staffId);
            } else {
                VisitEvent visitEvent = (VisitEvent) aggregatedData.get(0);
                visitEvent.setStaffID(staffId);
                aggregatedData.remove(0);
                aggregatedData.add(0, visitEvent);
            }


            aggregatedData.add(staff);
        } else if (in.getBody() instanceof VisitEvent) {
            VisitEvent visitEvent = in.getBody(VisitEvent.class);
            String visitEventStaffId = visitEvent.getStaffID();
            logger.info(String.format("VisitEvent waiting in queue aggregate for correct staff to be found, incorrect staff : %s, ani : %s, event time : ", visitEventStaffId, visitEvent.getAutomaticNumberIdentification(), visitEvent.getVisitEventDatetime().toString()) );
            aggregatedData.add(0, visitEvent);

        }
        //result.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID, dnisAndStaffId);
        result.getIn().setBody(aggregatedData);
        logger.info("StaffAggregationStrategy:: newExchange Header {}", (String) newExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT));

        logger.info("inside aggregation strategy: Returned Result exchange with size {}", aggregatedData.size());

        return result;
    }
}
