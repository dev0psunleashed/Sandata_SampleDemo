package com.sandata.one.drools.listeners;


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

import com.sandata.lab.rules.vv.fact.VisitFact;
import com.sandata.lab.rules.vv.log.utils.App;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.one.drools.bean.JmsMessageSender;
import com.sandata.one.drools.utils.Constants;


public class ExceptionAgendaEventListerner implements AgendaEventListener {

	private static Logger logger = LoggerFactory.getLogger(ExceptionAgendaEventListerner.class);

	private String validationAggregatorQueue = App.ID.SANDATA_VALIDATION_AGGREGATOR.toString(); // "SANDATA_VALIDATION_AGGREGATOR";

	public void matchCreated(MatchCreatedEvent event) {
	}

	public void matchCancelled(MatchCancelledEvent event) {
	}

	public void beforeMatchFired(BeforeMatchFiredEvent event) {
	}

	public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
		
		String matchedName = afterMatchFiredEvent.getMatch().getRule().getName();
		logger.info("afterMatchFired : 10001 afterMatchFiredEvent.getMatch().getRule().getName()=" +  matchedName);

		KieRuntime kieRuntime = afterMatchFiredEvent.getKieRuntime();

		if (kieRuntime != null && kieRuntime.getObjects() != null) {
			logger.info("afterMatchFired : {} 102");
			for (Object object : kieRuntime.getObjects()) {
				logger.info("afterMatchFired : {} 103 object =" + object.getClass().getName());
				if (object instanceof VisitFact) {
					logger.info("afterMatchFired : {} 104");
					VisitFact visitFact = ( VisitFact ) object;
					if (visitFact.getVisitExceptions() != null 
							&& visitFact.getVisitExceptions().size() > 0
							&& Constants.ALL_TIME_RULE_NAME.equals(matchedName)) {
						// send visit for unplanned visit
						sendVisitEventForValidation(visitFact);
						logger.info("afterMatchFired : {} 106");
						logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_VISIT_VALIDATION_SERVICE_KEYWORD
								, this.getClass(),"afterMatchFired", "Sent visit info for validation vv_data_content: {}"), visitFact);
						logger.info("afterMatchFired : {} 107");
					}
				}
			}            	
		}		
		logger.info("getClass : {} 108");

	}

	public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {
	}
	


	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
	}

	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}

	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}

	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}

	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}

	public void sendVisitEventForValidation(VisitFact visitFact) {
		Queue queue = new ActiveMQQueue(validationAggregatorQueue);
		JmsMessageSender.getInstance().sendObject(queue,visitFact);
	}	

}
