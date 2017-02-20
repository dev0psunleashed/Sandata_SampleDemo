package com.sandata.lab.rules.call.matching.aggregate;

import com.google.gson.Gson;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.rules.call.matching.exceptions.CepException;
import com.sandata.lab.rules.call.matching.processors.ProducerPojo;
import com.sandata.lab.rules.call.model.CallPreferences;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tom.dornseif on 11/24/2015.
 */
public class StartVisitAndScheduleProcess {
    Logger logger = LoggerFactory.getLogger(StartVisitAndScheduleProcess.class);
    ProducerPojo producerPojo;

    @Handler
    public void processMatches(Exchange exchange) throws CepException {
        Message in = exchange.getIn();
        if (in.getBody() instanceof ArrayList){
            ArrayList list = exchange.getIn().getBody(ArrayList.class);
            String correctedHeader = (String)exchange.getIn().getHeader(PrepForAggregation.CORRECTED_STAFF_ID);
            ArrayList newList = null;
            CallPreferences callPreferences = null;
            boolean possibleMatchFound = false;
            VisitFact inserted = null;
            VisitFact scheduled = null;
            int iSize = list.size();
            for(Iterator<VisitFact> iterator = list.iterator(); iterator.hasNext();) {
                VisitFact visitFact = iterator.next();
                //came from call server
                if(visitFact.getState()==State.AGG_WAITING_FOR_RESPONSE) {
                    inserted = visitFact;
                    logger.info(String.format("AGG_WAITING_FOR_RESPONSE : size of array : (%d)", iSize));
                    if(correctedHeader != null) {
                        logger.info(String.format("correctedHeader not null!  setting old staffId %s to new staffId %s",
                                visitFact.getStaffId(), correctedHeader));
                        logger.warn(String.format("call with staffId %s may not be retracted", visitFact.getStaffId()));
                        inserted.getCallCallIn().setStaffID(correctedHeader);
                        inserted.setStaffId(correctedHeader);
                    }
                    callPreferences = inserted.getCallPreferences();

                }
                //these cases came from schedule event response
                else if(visitFact.getState()==State.NO_SCHEDULES) {
                    scheduled = visitFact;
                    in.setHeader("STATE", State.NO_SCHEDULES);
                    logger.info(String.format("NO_SCHEDULES : size of array : (%d)", iSize));
                }
                else if(visitFact.getState()==State.INVALID_STAFF) {
                    in.setHeader("STATE", State.INVALID_STAFF);
                    logger.info(String.format("Encountered INVALID_STAFF ArrayList size = (%d)", list.size() ));
                    return;
                }
                else {
                    logger.info(String.format("%s : size of array : (%d) From: %s To: %s ", visitFact.getState().name(), iSize, visitFact.getScheduledFrom().toString(), visitFact.getScheduledTo().toString()));

                }

            }

            if (inserted == null) {
                //Because shit happens!  This is an impossible state.
                // We have to have and inserted call that triggered this
                // or we couldnt be here.  Test anyway.
                logger.error("Aggregating a scheduled visit : There was no call event trigger! (com.sandata.lab.rules.call.matching.aggregate.StartVisitAndScheduleProcess line 52) ArrayList size : " + Integer.toString(list.size()) );
                //throw new CepException("No Call triggered");
            }


            if (list.size() > 1) {
                //This is expected case
                for (int i = 0; i < list.size(); i++) {
                    VisitFact v = (VisitFact) list.get(i);
                    /*
                    try {
                        logger.info("Scheduled From Was ============================= " + v.getScheduledFrom().toString());
                        logger.info("Scheduled To Was ============================= " + v.getScheduledTo().toString());
                    }
                    catch(Exception ex) {
                        logger.error("Calling Schedule from or to threw an exception probably nullPointer!");
                    }*/
                    if (v.getState() != State.AGG_WAITING_FOR_RESPONSE) {
                        if (v.getScheduledTo() != null && v.getScheduledFrom() != null && inserted.getCallIn().after(v.getScheduledFrom()) &&
                                inserted.getCallIn().before(v.getScheduledTo())) {
                            if ( ( !inserted.isClientEntered() && (v.getAni() != null && v.getAni().equals(inserted.getAni()) ) ) || (inserted.getPatientId() != null && inserted.getPatientId().equals( ((Visit)v.getVisit()).getPatientID()))) {
                                if(scheduled == null || scheduled.getVisit() == null || scheduled.getVisit().getScheduleEventSK() == null ) {
                                    scheduled = v;

                                    if(v.getState() == State.NO_SCHEDULES) {
                                        logger.info("Leave set to NO_SCHEDULES");
                                    }
                                    else {
                                        scheduled.setState(State.POSSIBLE_MATCH_FROM_RESPONSE);
                                        inserted.setState(State.POSSIBLE_MATCH_WAITING_FOR_RESPONSE);
                                        logger.info("This call that occurred at : " + inserted.getCallIn().toString() + " will be inserted into the visit that is scheduled from : " + scheduled.getScheduledFrom().toString() + " to : " + scheduled.getScheduledTo().toString());
                                        if (scheduled.getVisit() != null && scheduled.getVisit().getScheduleEventSK() != null)
                                            logger.info(String.format("setting visit as schedule with schedulesk of (%d)", scheduled.getVisit().getScheduleEventSK().intValue()));
                                        else
                                            logger.info("ScheduleSK was null");
                                    }
                                }
                                else
                                {
                                    logger.warn("Had more than 1 exact possible match!  This is highly unlikely an indicates corrupted data or multi-call.");
                                    logger.warn("Had more than 1 exact possible match!  Maybe Patients number was changed and original given to a new patient and staff is now there.  This is highly unlikely an indicates Edge case created by QA.");
                                    logger.warn("If 2 matches match with schedule event will take priority.");
                                }
                            }
                        }
                    }
                }//end of iteration

                if (scheduled == null) {
                    //Run  rules with original arrayList
                    in.setHeader("STATE", State.NOT_MATCHED);

                }
                else {
                    if(inserted != null && inserted.getStaffId() != null && scheduled != null &&
                            scheduled.getVv_id()!= null && scheduled.getStaffId() != null &&
                            inserted.getStaffId().equals(scheduled.getVv_id())) {
                        inserted.setStaffId(scheduled.getStaffId());
                    }
                    logger.info(" Inserted visit {}", inserted);
                    logger.info(" scheduled {} ", scheduled);
                    newList = new ArrayList();
                    newList.add(inserted);
                    newList.add(scheduled);
                    in.setBody(newList, ArrayList.class);
                    in.setHeader("STATE", State.POSSIBLE_MATCH);
                }
            }
            else if(list.size() == 1){
                logger.error("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess list was size of 1 (timeout)!");
                //throw new CepException("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess list was size of 1 (timeout)!");
            }
            else {
                logger.error("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess list was empty unknown aggregation error!");
                //throw new CepException("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess list was empty unknown aggregation error!");
            }
        }
        else{
            logger.error("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess Exchange Body was not expect type of ArrayList!");
            //throw new CepException("activemq-AGGREGATE_VISIT_EVENTS:StartVisitAndScheduleProcess Exchange Body was not expect type of ArrayList!");
        }
    }
}

