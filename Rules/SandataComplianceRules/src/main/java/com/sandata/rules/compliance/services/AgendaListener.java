package com.sandata.rules.compliance.services;

import org.kie.api.event.rule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jasons on 9/9/2016.
 */
public class AgendaListener implements AgendaEventListener {
    private static Logger logger = LoggerFactory.getLogger(AgendaListener.class);

    public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
        logger.info("matchCreated : {}", matchCreatedEvent.getMatch().getRule());
        for (Object o : matchCreatedEvent.getMatch().getObjects()) {
            logger.info(o.toString());
        }
    }

    public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
        logger.info("matchCancelled : {}", matchCancelledEvent.getMatch().getRule());
    }

    public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
        logger.info("beforeMatchFired : {}", beforeMatchFiredEvent.getMatch().getRule());
    }

    public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
        logger.info("AfterMatchFiredEvent : {}", afterMatchFiredEvent.getMatch().getRule());
    }

    public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {
        logger.info("agendaGroupPopped : {}", agendaGroupPoppedEvent.getAgendaGroup());
    }

    public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {
        logger.info("agendaGroupPushed : {}", agendaGroupPushedEvent.getAgendaGroup());
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.info("beforeRuleFlowGroupActivated : {}", ruleFlowGroupActivatedEvent.getRuleFlowGroup());
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        logger.info("afterRuleFlowGroupActivated : {}", ruleFlowGroupActivatedEvent.getRuleFlowGroup());
    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        logger.info("beforeRuleFlowGroupDeactivated : {}", ruleFlowGroupDeactivatedEvent.getRuleFlowGroup());
    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        logger.info("afterRuleFlowGroupDeactivated : {}", ruleFlowGroupDeactivatedEvent.getRuleFlowGroup());
    }
}
