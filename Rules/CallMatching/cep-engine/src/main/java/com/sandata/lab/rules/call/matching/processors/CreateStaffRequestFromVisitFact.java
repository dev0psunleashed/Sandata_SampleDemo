package com.sandata.lab.rules.call.matching.processors;

import com.google.gson.Gson;
import com.sandata.lab.data.model.constants.filter.Filter;
import com.sandata.lab.data.model.request.visit.PatientStaffRequest;
import com.sandata.lab.rules.cache.client.CacheClient;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/11/2015
 * Time: 7:28 AM
 */
public class CreateStaffRequestFromVisitFact implements org.apache.camel.Processor {
    Logger logger = LoggerFactory.getLogger(CreateStaffRequestFromVisitFact.class);
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
            PatientStaffRequest request = getPatientStaffRequest(visitFact);
            //in.setHeader(PrepForAggregation.DNIS_AND_STAFFID, dnisAndStaffId);
            in.setBody(request, PatientStaffRequest.class);
        }
        else if(in.getBody() instanceof VisitFact) {
            VisitFact visitFact = in.getBody(VisitFact.class);
            PatientStaffRequest request = getPatientStaffRequest(visitFact);
            //in.setHeader(PrepForAggregation.DNIS_AND_STAFFID, dnisAndStaffId);
            in.setBody(request, PatientStaffRequest.class);
        }



    }
    private PatientStaffRequest getPatientStaffRequest(VisitFact visitFact) {
        PatientStaffRequest request = new PatientStaffRequest();
        request.setFilter(Filter.OPTIONS.CONTAINS);
        request.setDnis(visitFact.getDnis());
        String dnis = visitFact.getDnis();
        String staffId = visitFact.getStaffId();
        //String dnisAndStaffId = dnis+staffId;
        request.setStaffId(visitFact.getStaffId());
        request.setPatientId(visitFact.getPatientId());
        request.setAni(visitFact.getAni());
        if(visitFact.isClientEntered())
            request.setClientEntered(true);
        //TBD once configuration service is create call here
        CacheClient client = null;
        try {
            client = CacheClient.getClient();
        }
        catch(Exception ex) {
            logger.error(ex.getMessage());
        }
        //CallPreferences callPreferences = client.getCallPreferencesForAgencyId(agencyId);
        CallPreferences callPreferences = null;
        if(client != null) {
            String json = client.getCallPreferencesForDnis(dnis);
            Gson gson = new Gson();
            callPreferences = gson.fromJson(json, CallPreferences.class);
        }
        else {
            callPreferences = new CallPreferences();
            logger.error("CALL PREFERENCE FAILED:::::::::::::::::SETTING DEFAULT!!!!!!");
        }

        //ovverride default for testing purposes.
        callPreferences.getStaffIdMatchLength();
        int minMatch = callPreferences.getStaffIdMatchLength();
        //set list up for 6 of 9 matching
        switch(minMatch) {
            case 6:
                for (int i = 0; i < 4; i++) {
                    String staff = staffId.substring(i, i + 5);
                    request.getStaffIdFilterList().add(staff);
                }
                break;
            case 7:
                for (int i = 0; i < 3; i++) {
                    String staff = staffId.substring(i, i + 6);
                    request.getStaffIdFilterList().add(staff);
                }
                break;
        }
        return request;
    }
}
