package com.sandata.lab.rules.call.matching.listeners;

import org.kie.api.event.rule.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/9/2015
 * Time: 4:05 PM
 */
public class AgendaListener implements AgendaEventListener{
    @Override
    public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
        writeMsg("matchCreated", matchCreatedEvent.getMatch().getObjects());
    }

    @Override
    public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
        writeMsg("matchCanceled", matchCancelledEvent.getMatch().getObjects());
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
        writeMsg("beforeMatchCreated", beforeMatchFiredEvent.getMatch().getObjects());
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
        writeMsg("afterMatchCreated", afterMatchFiredEvent.getMatch().getObjects());
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {
        writeAgendaMsg(agendaGroupPoppedEvent.getAgendaGroup().getName());
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {
        writeAgendaMsg(agendaGroupPushedEvent.getAgendaGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        writeAgendaMsg("Before " + ruleFlowGroupActivatedEvent.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
        writeAgendaMsg("After " + ruleFlowGroupActivatedEvent.getRuleFlowGroup().getName());
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        writeAgendaMsg("Before " + ruleFlowGroupDeactivatedEvent.getRuleFlowGroup().getName());
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
        writeAgendaMsg("After " + ruleFlowGroupDeactivatedEvent.getRuleFlowGroup().getName());
    }

    private void writeAgendaMsg(String msg) {
        System.out.println(String.format("%s ", msg ));
    }

    private void writeMsg(String msg, List list) {
            System.out.println(String.format("%s %s : %s", msg, list.get(0), list.get(1) ));

    }
}
