package com.sandata.rules.compliance.services;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jasons on 9/9/2016.
 */
public class RuleListener implements RuleRuntimeEventListener {

    private static Logger logger = LoggerFactory.getLogger(RuleListener.class);

    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
        logger.info("objectInserted : {}", objectInsertedEvent.getObject());
    }

    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        logger.info("objectUpdated : {}", objectUpdatedEvent.getObject());
    }

    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
        logger.info("objectDeleted : {}", objectDeletedEvent.getOldObject());
    }
}
