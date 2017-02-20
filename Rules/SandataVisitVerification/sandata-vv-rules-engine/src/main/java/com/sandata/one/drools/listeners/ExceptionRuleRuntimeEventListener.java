package com.sandata.one.drools.listeners;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionRuleRuntimeEventListener implements RuleRuntimeEventListener {
	         
	private static Logger logger = LoggerFactory.getLogger(ExceptionRuleRuntimeEventListener.class);
	
	public void objectInserted(ObjectInsertedEvent event) {
		logger.info("In ExceptionRuleRuntimeEventListener.objectInserted sandata.one.drools testing ....\n" + event.getObject());
	}

	public void objectUpdated(ObjectUpdatedEvent event) {
		logger.info("In ExceptionRuleRuntimeEventListener.objectUpdated sandata.one.drools testing ....");
	}

	public void objectDeleted(ObjectDeletedEvent event) {
		logger.info("In ExceptionRuleRuntimeEventListener.objectDeleted sandata.one.drools testing ....");
	}

}
