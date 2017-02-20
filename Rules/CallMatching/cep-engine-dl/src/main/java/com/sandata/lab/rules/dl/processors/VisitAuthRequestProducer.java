package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 6/7/2016
 * Time: 2:06 PM
 */
public class VisitAuthRequestProducer implements Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");
    static final String HAS_PATIENT_ID = "HAS_PATIENT_ID";
    @Override
    public void process(Exchange exchange) throws Exception {

        Message in = exchange.getIn();
        if(in.getBody() instanceof VisitFact) {
            VisitFact vf = in.getBody(VisitFact.class);
            BigInteger visitSk = vf.getVisit().getVisitSK();
            String patientId = vf.getPatientId();
            String bsnEntityId = vf.getVisit().getBusinessEntityID();
            Date visitDateTime = vf.getCallIn();
            HashMap<String, String> simpleVisitAuthRequest = new HashMap<>();
            if(visitDateTime == null) {
                visitDateTime = vf.getCallOut();
            }
            if(patientId == null) {
                in.setHeader(HAS_PATIENT_ID, "false");
                getVisitDlLog().warn(String.format("Visit :: VisitSK (%d) has no PatientId.  Will not try to get authorization at this time.", visitSk.intValue()));
            }
            else if (visitDateTime == null) {
                in.setHeader(HAS_PATIENT_ID, "false");
                getVisitDlLog().warn(String.format("Visit :: VisitSK (%d) with patientID (%s) had no callin or callout :: Will not try to get authorization at this time.", visitSk.intValue(), patientId));
            }
            else {
                in.setHeader(HAS_PATIENT_ID, "true");
                getVisitDlLog().info(String.format("Visit :: VisitSK (%d) has patientId (%s).  Will verify visit authorization record exist or will create a new one", visitSk.intValue(), patientId));
                simpleVisitAuthRequest.put("patientId", patientId);
                simpleVisitAuthRequest.put("visitSK", visitSk.toString());
                simpleVisitAuthRequest.put("visitDateTime", String.format("%d",visitDateTime.getTime()));
                simpleVisitAuthRequest.put("bsnEntityId", bsnEntityId);
            }
            in.setBody(simpleVisitAuthRequest, HashMap.class);
        }
    }
    public Logger getVisitDlLog() {
        if(this.visitDlLog != null) {
            return this.visitDlLog;
        }
        else {
            this.visitDlLog = LoggerFactory.getLogger("visitDlLog");
            return this.visitDlLog;
        }

    }

    public void setVisitDlLog(Logger visitDlLog) {
        this.visitDlLog = visitDlLog;
    }

}
