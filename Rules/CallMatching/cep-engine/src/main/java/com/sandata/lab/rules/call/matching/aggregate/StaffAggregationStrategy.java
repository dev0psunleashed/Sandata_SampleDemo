package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.data.model.dl.model.Staff;
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
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/10/2015
 * Time: 7:18 PM
 */
public class StaffAggregationStrategy implements org.apache.camel.processor.aggregate.AggregationStrategy {
    Logger logger = LoggerFactory.getLogger(StaffAggregationStrategy.class);
    /**
     * Aggregates an old and new exchange together to create a single combined exchange
     *
     * @param oldExchange the oldest exchange (is <tt>null</tt> on first aggregation as we only have the new exchange)
     * @param newExchange the newest exchange (can be <tt>null</tt> if there was no data possible to acquire)
     * @return a combined composite of the two exchanges
     */
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        ArrayList aggregatedData;
        Exchange result = newExchange;
        Message in = newExchange.getIn();
        String dnis;
        String staffId;
        String ani = null;
        String patientId;
        String dnisAndStaffId = null;
        if(oldExchange == null)
            aggregatedData = new ArrayList();
        else
            aggregatedData = oldExchange.getIn().getBody(ArrayList.class);

        if(in.getBody() instanceof StaffFact) {
            StaffFact staff = in.getBody(StaffFact.class);
            dnis = staff.getDnis();
            staffId = staff.getStaffId();
            dnisAndStaffId = dnis + staffId;
            aggregatedData.add(staff);

        }
        else if(in.getBody() instanceof PatientStaffResponse) {
            PatientStaffResponse patientStaffResponse = in.getBody(PatientStaffResponse.class);
            PatientStaffRequest request = patientStaffResponse.getPatientStaffRequest();
            dnis = request.getDnis();
            staffId = request.getStaffId();
            patientId = request.getPatientId();
            ani = request.getAni();
            dnisAndStaffId = dnis + staffId;
            if(request.isClientEntered()) {
                dnisAndStaffId += patientId;
            }
            else {
                dnisAndStaffId += ani;
            }

            ArrayList<StaffFact> list = new ArrayList<>();
            if(patientStaffResponse.getStaffList().isEmpty()) {
                list.add(new StaffFact(dnis, staffId, State.NOT_MATCHED));
                result.getIn().setHeader("STATE", State.NOT_MATCHED.name());

            }
            else {
                for (Staff s : patientStaffResponse.getStaffList()
                     ) {
                    OraStaffFact staffFact = new OraStaffFact(s.getStaffID(), dnis);
                    list.add(staffFact);

                }
                result.getIn().setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE.name());
            }
            aggregatedData.addAll(list);
        }
        else if(in.getBody() instanceof ArrayList) {
            //TESTING
            ArrayList list = in.getBody(ArrayList.class);
            staffId = (String)in.getHeader("staffId");
            dnis = (String)in.getHeader("dnis");
            result.getIn().setHeader("STATE", State.AGG_INSERTED_FROM_RESPONSE);
            aggregatedData.addAll(list);
            dnisAndStaffId = dnis + staffId;
        }
        result.getIn().setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnisAndStaffId);
        result.getIn().setBody(aggregatedData);
        logger.info("StaffAggregationStrategy:: newExchange Header {}",  (String)newExchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT) );

        logger.info("inside aggregation strategy: Returned Result exchange with size {}", aggregatedData.size());

        return result;
    }
}
