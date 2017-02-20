package com.sandata.lab.rules.dl.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitEventTypeCode;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.call.model.State;
import com.sandata.lab.rules.call.model.UnplannedVisit;
import com.sandata.lab.rules.call.model.VisitEventFact;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom.dornseif on 11/22/2015.
 */
public class VisitProcessorProducer implements Processor {
    private Logger visitDlLog = LoggerFactory.getLogger("visitDlLog");

    @Override
    public void process(Exchange exchange) throws Exception {

        if(exchange.getIn().getBody() instanceof VisitFact) {

            VisitFact visitFact = exchange.getIn().getBody(VisitFact.class);
            String santraxId = visitFact.getVv_id() == null ? "(null)" : visitFact.getVv_id();
            getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : VisitFact with dnis, ani, staffId, and santraxId (%s, %s, %s, and %s)",
                    visitFact.getDnis(), visitFact.getAni(), visitFact.getStaffId(), santraxId));

            if(visitFact.getTaskList() != null && visitFact.getTaskList().size() > 0) {
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : size = (%s)", Integer.toString(visitFact.getTaskList().size())));
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : get(0).getVisitTaskListID() = (%s)", visitFact.getTaskList().get(0).getVisitTaskListID()));
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : get(0).getTaskResultsReadingValue() = (%s)", visitFact.getTaskList().get(0).getTaskResultsReadingValue()));
                getVisitDlLog().info(String.format("VisitProcessorProducer - Visit.VisitTaskList : size = (%s)", Integer.toString(visitFact.getVisit().getVisitTaskList().size())));
            }
            else {
                //previously logged in PrepForAggregation - TaskList : size <= compare
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : previously logged in PrepForAggregation - TaskList <= compare"));
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList : size = (0)"));
                getVisitDlLog().info(String.format("VisitProcessorProducer - TaskList =============================================="));
            }
            if(visitFact.getVv_id() != null && !visitFact.getVv_id().isEmpty()) {
                String staffId = visitFact.getStaffId();
                String vv_id = visitFact.getVv_id();
                String visitEventStaffId = null;
                if(vv_id != null && !vv_id.isEmpty() && visitFact.getVisit()!= null &&
                        visitFact.getVisit().getVisitEvent() != null &&
                        visitFact.getVisit().getVisitEvent().size() > 0 &&
                        visitFact.getVisit().getVisitEvent().get(0) != null) {
                    visitEventStaffId = visitFact.getVisit().getVisitEvent().get(0).getStaffID();
                }
                if(visitEventStaffId != null && !visitEventStaffId.isEmpty() && vv_id == visitEventStaffId) {
                    String infoString = String.format("Let the visitEvent keep the StaffId keyed in at phone.  Staff on event now = %s, staffId Matched in Sandata.one = %s",
                            visitEventStaffId, staffId);
                    getVisitDlLog().info(infoString);
                }
            }
            Visit visit = processVisit(visitFact.getVisit());
            visit.getVisitEvent();
            if(visitFact.getState() == State.CREATE_UNPLANNED_VISIT) {
                if(visitFact.getCallCallIn() != null) {
                    VisitEvent visitEvent = visitFact.getCallCallIn().getVisit();
                    visit.getVisitEvent().add(visitEvent);
                }
                if(visit.getVisitEvent().size() == 0) {
                    VisitEvent visitEvent = new VisitEvent();
                    visitEvent.setVisitEventTypeCode(VisitEventTypeCode.TEL);
                    //We need another column maybe.  In order to be able to maintain what was actually entered.
                    // for now staffId will hold what was entered.
                    //visitEvent.setStaffID(visitFact.getStaffId());
                    visitEvent.setDialedNumberIdentificationService(visitFact.getDnis());
                    visitEvent.setAutomaticNumberIdentification(visitFact.getAni());
                    visitEvent.setPatientID(visitFact.getPatientId());
                    visitEvent.setVisitEventDatetime(visitFact.getCallIn());
                    visit.getVisitEvent().add(visitEvent);
                }
            }

            getVisitDlLog().info(String.format("VisitProcessorProducer - Visit.VisitEvents : size = (%s)", Integer.toString(visit.getVisitEvent().size())));

            if(visitFact.getExtraneousCalls() != null && visitFact.getExtraneousCalls().size() > 0) {
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitFact.getExtraneousCalls : size = (%s)", Integer.toString(visitFact.getExtraneousCalls().size())));
                List<VisitEvent> listVisitEvents = new ArrayList<VisitEvent>();
                for(VisitEventFact vef : visitFact.getExtraneousCalls()) {
                    listVisitEvents.add(vef.getVisit());
                }
                visit.getVisitEvent().addAll(listVisitEvents);
            }
            getVisitDlLog().info(" ==================================== ");
            getVisitDlLog().info(" Putting a Visit on the queue! ");
            getVisitDlLog().info(String.format("VisitProcessorProducer - VisitProcessorProducer - After conversion VisitFact() to Visit() TaskList to VisitTaskList : VisitFact with dnis, ani, and staffId (%s, %s, and %s)",
            visitFact.getDnis(), visitFact.getAni(), visitFact.getStaffId()));
            if(visit.getVisitTaskList() != null && visit.getVisitTaskList().size() > 0) {
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList : size = (%s)", Integer.toString(visit.getVisitTaskList().size())));
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList : get(0).getVisitTaskListID() = (%s)", visit.getVisitTaskList().get(0).getVisitTaskListID()));
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList : get(0).getTaskResultsReadingValue() = (%s)", visit.getVisitTaskList().get(0).getTaskResultsReadingValue()));
            }
            else {
                //previously logged in PrepForAggregation - TaskList : size <= compare
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList : previously logged in VisitProcessorProducer - TaskList <= compare"));
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList : size = (0)"));
                getVisitDlLog().info(String.format("VisitProcessorProducer - VisitTaskList =============================================="));
            }
            exchange.getIn().setBody(visit, Visit.class);
        }
        else if( exchange.getIn().getBody() instanceof Visit ) {
            Visit visit = exchange.getIn().getBody(Visit.class);

            processVisit(visit);
            getVisitDlLog().info(" Exchange was already a visit. Putting this Visit on the queue! ");
        }
    }
    private Visit processVisit(Visit visit) {

        if(visit.getVisitTaskList()!= null ) {
            getVisitDlLog().info("Visit.VisitTaskList : size(" + Integer.toString(visit.getVisitTaskList().size()) + ")");
            getVisitDlLog().info("visit.getVisitTaskList : {}", visit.getVisitTaskList());
        }
        else {
            getVisitDlLog().info("Visit.VisitTaskList : <NULL>");
        }
        getVisitDlLog().info("Visit.VisitEvent : {}", visit.getVisitEvent());

        return visit;
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
