package com.sandata.lab.rules.call.matching.rules.listeners;

import javax.jms.Queue;


import org.apache.activemq.command.ActiveMQQueue;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.runtime.KieRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.rules.call.matching.rules.spring.jms.bean.JmsMessageSender;
import com.sandata.lab.rules.vv.fact.VisitEventFact;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.VisitState;

/**
 * <p></p>
 *
 * @author thanhxle
 */
//will be deleted
public class CallMatchingAgendaListener implements AgendaEventListener {
    private static Logger logger = LoggerFactory.getLogger(CallMatchingAgendaListener.class);

       
    private String visitEventDnisAgendaGroup = "call-matching-dnis";

    private String visitEventScheduleAgendaGroup = "call-matching-schedule";

    private String callMatchingStaffAgendaGroup = "call-matching-staff";

    private String callMatchingPatientAgendaGroup = "call-matching-patient";


    private String visitEventSchedMatchAggregatorQueu = "SANDATA_CALL_MATCHING_VISIT_EVENT_SCHEDULE_MATCHING_AGGREGATOR";

    private String visitEventMatchedAggregatorQueue = "SANDATA_CALL_MATCHING_MATCHED_VISIT_EVENT_AGGREGATOR";
    
    private String visitEventStaffToMatchingAggregatorQueue = "SANDATA_CALL_MATCHING_VISIT_EVENT_STAFF_AGGREGATOR";

    private String visitEventPatientToMatchingAggregatorQueue = "SANDATA_CALL_MATCHING_VISIT_EVENT_PATIENT_AGGREGATOR";

    private String visitEventValidatorQueue = "SANDATA_CALL_MATCHING_CALL_VALIDATOR";

    
    public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
        logger.debug("matchCreated : {}", matchCreatedEvent.getMatch().getRule());
        for (Object o : matchCreatedEvent.getMatch().getObjects()) {
            logger.info(o.toString());
        }
    }

    public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
        logger.debug("matchCancelled : {}", matchCancelledEvent.getMatch().getRule());
    }

    public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
        logger.debug("beforeMatchFired : {}", beforeMatchFiredEvent.getMatch().getRule());
    }

    public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
        
        logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
        		, this.getClass(),"afterMatchFired", "AfterMatchFiredEvent : {}"), afterMatchFiredEvent.getMatch().getRule());
        for (Object o : afterMatchFiredEvent.getMatch().getObjects()) {
            logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
            		, this.getClass(),"afterMatchFired", o.toString()));
        }
    }
    

    public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {
        logger.info("agendaGroupPopped : {}", agendaGroupPoppedEvent.getAgendaGroup());
        
        KieRuntime kieRuntime = agendaGroupPoppedEvent.getKieRuntime();

        if (kieRuntime != null
            && kieRuntime.getObjects() != null) {
            String agendaGroupName = agendaGroupPoppedEvent.getAgendaGroup().getName();
            if (agendaGroupName.equalsIgnoreCase(visitEventDnisAgendaGroup)) {
                for (Object object : kieRuntime.getObjects()) {
                    if (object instanceof VisitEventFact) {
                        //this case for call from server
                        sendVisitEventForValidator ((VisitEventFact) object);
                        logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                        		, this.getClass(),"agendaGroupPopped", "Sent call info from call server: {}"), object);
                    }
                }
            } else if (agendaGroupName.equalsIgnoreCase(visitEventScheduleAgendaGroup)) {
                for (Object object : kieRuntime.getObjects()) {
                    if (object instanceof VisitEventFact) {
                        VisitEventFact visitEventFact = ( VisitEventFact ) object;
                        if (visitEventFact.getVisitState() != null && visitEventFact.getVisitState() == VisitState.NOT_MATCHED) {
                            // send visit for unplanned visit
                            sendVisitEventStaffToMatchingAggregator(visitEventFact);
                            
                            logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                            		, this.getClass(),"agendaGroupPopped", "Sent  visit info that un-matched to any schedules for staff matching : {}"), visitEventFact);

                        } else if (visitEventFact.getVisitState() != null && visitEventFact.getVisitState() == VisitState.MATCHED) {
                            // send matched visit for create planned visit
                            sendVisitEventToMatchedVisitEventAggregator(visitEventFact);
                            
                            logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                            		, this.getClass(),"agendaGroupPopped", "Send matched visit for create planned visit : {}"), visitEventFact);
                        }
                    }
                }
            } else if (agendaGroupName.equalsIgnoreCase(callMatchingStaffAgendaGroup)) {
                for (Object object : kieRuntime.getObjects()) {
                    if (object instanceof VisitEventFact) {
                        VisitEventFact visitEventFact = ( VisitEventFact ) object;
                        if (visitEventFact.getVisitState() != null && visitEventFact.getVisitState() == VisitState.NOT_MATCHED
                                && visitEventFact.getStaffFact() != null) {
                            // send visit  for patient matching
                            sendVisitEventPatientToMatchingAggregator(visitEventFact);
                            logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                            		, this.getClass(),"agendaGroupPopped", "Send  visit info that un-matched for patient matching : {}"), visitEventFact);
                        }
                    }
                }
            } else if (agendaGroupName.equalsIgnoreCase(callMatchingPatientAgendaGroup)) {
                for (Object object : kieRuntime.getObjects()) {
                    if (object instanceof VisitEventFact) {
                        VisitEventFact visitEventFact = ( VisitEventFact ) object;
                        if (visitEventFact.getVisitState() != null && visitEventFact.getVisitState() == VisitState.NOT_MATCHED
                                && visitEventFact.getPatientFact() != null) {
                            // send visit  for inserting vv to database
                            sendVisitEventToMatchedVisitEventAggregator(visitEventFact);
                            logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                            		, this.getClass(),"agendaGroupPopped", "Send  visit info that un-matched after patient matching for inserting VV : {}"), visitEventFact);
                        }
                    }
                }
            }

        }
    }


    public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {
        logger.debug("agendaGroupPushed : {}", agendaGroupPushedEvent.getAgendaGroup());
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.debug("beforeRuleFlowGroupActivated : {}", ruleFlowGroupActivatedEvent.getRuleFlowGroup());
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.debug("afterRuleFlowGroupActivated : {}", ruleFlowGroupActivatedEvent.getRuleFlowGroup());
    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        logger.debug("beforeRuleFlowGroupDeactivated : {}", ruleFlowGroupDeactivatedEvent.getRuleFlowGroup());
    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        logger.info("afterRuleFlowGroupDeactivated : {}", ruleFlowGroupDeactivatedEvent.getRuleFlowGroup());
    }

    
    //TODO: will clean this code later
    public void sendVisitEventToScheduleMatchingAggregator(VisitEventFact visitEventFact) {
    	Queue queue = new ActiveMQQueue(visitEventSchedMatchAggregatorQueu);
    	JmsMessageSender.getInstance().sendObject(queue,visitEventFact);
    }

    public void sendVisitEventToMatchedVisitEventAggregator(VisitEventFact visitEventFact) {
        Queue queue = new ActiveMQQueue(visitEventMatchedAggregatorQueue);
        JmsMessageSender.getInstance().sendObject(queue,visitEventFact);
    }

    public void sendVisitEventStaffToMatchingAggregator(VisitEventFact visitEventFact) {
        Queue queue = new ActiveMQQueue(visitEventStaffToMatchingAggregatorQueue);
        JmsMessageSender.getInstance().sendObject(queue,visitEventFact);
    }

    public void sendVisitEventPatientToMatchingAggregator(VisitEventFact visitEventFact) {
        Queue queue = new ActiveMQQueue(visitEventPatientToMatchingAggregatorQueue);
        JmsMessageSender.getInstance().sendObject(queue,visitEventFact);
    }

    public void sendVisitEventForValidator(VisitEventFact visitEventFact) {
        
        Queue queue = new ActiveMQQueue(visitEventValidatorQueue);
        JmsMessageSender.getInstance().sendObject(queue,visitEventFact);
    }


}

