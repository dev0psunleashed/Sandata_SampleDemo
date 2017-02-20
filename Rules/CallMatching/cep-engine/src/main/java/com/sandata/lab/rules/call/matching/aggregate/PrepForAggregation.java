package com.sandata.lab.rules.call.matching.aggregate;


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
public class PrepForAggregation implements Processor {
    public static final String DNIS_AND_STAFFID_AND_PATIENT = "dnisAndStaffIdAndPatient";
    public static final java.lang.String CORRECTED_STAFF_ID = "CORRECTED_STAFF_ID";
    public static final String DATABASE_PROVIDED_STAFF_ID = "databaseProvidedStaffId";
    public static final String REQUEST_UUID_FOR_AGGREGATION = "RequestIdForAggregation";
    Logger logger = LoggerFactory.getLogger(PrepForAggregation.class);
    @Override
    public void process(Exchange exchange) throws Exception {

        if(exchange.getIn().getBody() instanceof VisitEventFact ) {
            //CAME FROM CALL SERVER
            VisitEventFact visitEventFact = exchange.getIn().getBody(VisitEventFact.class);
            String dnisandstaffidandpatient =  visitEventFact.getDnis();
            String requestId = visitEventFact.getRequestId().toString();
            dnisandstaffidandpatient += visitEventFact.getStaffID();
            String patientId = "<null>";
            String ani = visitEventFact.getAni();
            VisitFact visitFact = new VisitFact(visitEventFact, State.AGG_WAITING_FOR_RESPONSE);
            visitFact.setBusinessEntityId(visitEventFact.getBusinessEntityId());
            if(visitEventFact.getPatientID() != null && visitEventFact.getPatientID().length() > 0) {
                patientId = visitEventFact.getPatientID();
                dnisandstaffidandpatient += patientId;
                
                visitFact.setClientEntered(true);
            }
            else
            {
                dnisandstaffidandpatient += ani;
            }
            logger.info(String.format("PrepForAggregation - TaskList : VisitEventFact with dnis, ani, staffId and patientId (%s, %s, %s and %s)",
                    visitEventFact.getDnis(), visitEventFact.getAni(), visitEventFact.getStaffID(), patientId));
            if(visitEventFact.getTasks() != null && visitEventFact.getTasks().size() > 0) {
                logger.info(String.format("PrepForAggregation::32 - TaskList : size = (%s)", Integer.toString(visitEventFact.getTasks().size())));
                //logger.info(String.format("PrepForAggregation - TaskList : get(0).getVisitTaskListID() = (%s)", visitEventFact.getTasks().get(0).getVisitTaskListID()));
                //logger.info(String.format("PrepForAggregation - TaskList : get(0).getTaskResultsReadingValue() = (%s)", visitEventFact.getTasks().get(0).getTaskResultsReadingValue()));
            }
            else
                logger.info(String.format("PrepForAggregation::37 - TaskList : size = (0)"));

            //waiting for schedule event response, then will matched to aggregator completion
            visitFact.setState(State.AGG_WAITING_FOR_RESPONSE);



            ArrayList<VisitFact> list = new ArrayList<VisitFact>();
            list.add(visitFact);

            exchange.getIn().setBody(list);
            exchange.getIn().setHeader(DNIS_AND_STAFFID_AND_PATIENT, dnisandstaffidandpatient);
            exchange.getIn().setHeader("STATE",State.AGG_WAITING_FOR_RESPONSE.name());
            exchange.getIn().setHeader(REQUEST_UUID_FOR_AGGREGATION, requestId);

        }
        else if(exchange.getIn().getBody() instanceof VisitFact ) {
            
            //came from schedule event response

            VisitFact visitFact = exchange.getIn().getBody(VisitFact.class);
            String dnisandstaffidandpatient = visitFact.getDnis();
            dnisandstaffidandpatient += visitFact.getStaffId();
            if(visitFact.isClientEntered()) {
                dnisandstaffidandpatient += visitFact.getPatientId();
                logger.info("Response indicates PatientId should override ani!");
            } else {
                dnisandstaffidandpatient += visitFact.getAni();
            }
            exchange.getIn().setHeader(DNIS_AND_STAFFID_AND_PATIENT, dnisandstaffidandpatient);
            if(visitFact.getState()== State.AGG_INSERTED_FROM_RESPONSE) {
                logger.info("=======================");
                logger.info("   State was set to ");
                logger.info("AGG_INSERTED_FROM_RESPONSE!");
                logger.info("=======================");
            }
            else if(visitFact.getState() == State.CREATE_UNPLANNED_VISIT) {
                logger.info("=======================");
                logger.info("   State was set to ");
                logger.info("CREATE_UNPLANNED_VISIT!");
                logger.info("=======================");
            }
            else
            {
                logger.error(" =======================");
                logger.error("Bad State was encountered");
                logger.error("          {}             ", visitFact.getState());
                logger.error(" =======================");

            }
            ArrayList<VisitFact> list = new ArrayList<VisitFact>();
            list.add(visitFact);
            exchange.getIn().setBody(list);
            exchange.getIn().setHeader(DNIS_AND_STAFFID_AND_PATIENT, dnisandstaffidandpatient);
            exchange.getIn().setHeader("STATE",State.AGG_INSERTED_FROM_RESPONSE.name());
            logger.info("set visitFact {}", visitFact);
        }
        else if(exchange.getIn().getBody() instanceof ArrayList ) {
            //where this come from ?
            ArrayList list = exchange.getIn().getBody(ArrayList.class);
            int s = list.size();
            String dnisandstaffidandpatient = (String)exchange.getIn().getHeader(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT);
            logger.info("====================================");
            logger.info("  ArrayList recieved from Response  ");
            logger.info("           SIZE was ( " + Integer.toString(s) + " )  ");
            logger.info("====================================");
            exchange.getIn().setHeader(DNIS_AND_STAFFID_AND_PATIENT, dnisandstaffidandpatient);
            exchange.getIn().setBody(list);
            exchange.getIn().setHeader("STATE",State.AGG_INSERTED_FROM_RESPONSE.name());
            logger.info(String.format("======> The header contained a UUID of [%s]", exchange.getIn().getHeader(REQUEST_UUID_FOR_AGGREGATION)));

        }
        else
        {
            logger.error("unexpected objected {}", exchange.getIn().getBody());
            exchange.getIn().setBody(null);
            exchange.getIn().setHeader("STATE",State.INVALID.name());
        }
    }
}
