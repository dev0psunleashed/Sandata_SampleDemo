package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.data.model.response.visit.PatientStaffResponse;
import com.sandata.lab.rules.call.matching.aggregate.PrepForAggregation;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/11/2015
 * Time: 8:33 AM
 */
public class ProcessStaffResponse implements org.apache.camel.Processor {
    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        if(in.getBody() instanceof PatientStaffResponse) {
            PatientStaffResponse response = in.getBody(PatientStaffResponse.class);
            PatientStaffRequest request = response.getPatientStaffRequest();
            String dnis = request.getDnis();
            String staffId = request.getStaffId();
            String patientId = request.getPatientId();
            String ani = request.getAni();
            String dnisAndStaffIdAndPatient = dnis +staffId;
            if(request.isClientEntered())
                dnisAndStaffIdAndPatient += patientId;
            else
                dnisAndStaffIdAndPatient += ani;

            in.setHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT, dnisAndStaffIdAndPatient);
        }
    }
}
