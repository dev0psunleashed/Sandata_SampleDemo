package com.sandata.lab.rules.call.matching.rules.listeners;

import java.util.List;


import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.call.matching.rules.spring.jms.bean.JmsMessageSender;
import com.sandata.lab.rules.vv.log.utils.LoggingUtils;
import com.sandata.lab.rules.vv.model.VisitEventDNISGroupWrapper;

/**
 * <p></p>
 *
 * @author thanhxle
 */
//will be deleted

public class CallMatchingRuleListener implements RuleRuntimeEventListener {

    private static Logger logger = LoggerFactory.getLogger(CallMatchingRuleListener.class);
    
    //this route in call import route
    String processedCallIMportQueue = "SANDATA_PROCESSED_VV_CALLS_IMPORT_ROUTE";

    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
        logger.debug("objectInserted : {}", objectInsertedEvent.getObject().toString());
        if (objectInsertedEvent != null) {
            Object insertedObject = objectInsertedEvent.getObject();
            if (insertedObject instanceof VisitEventDNISGroupWrapper) {
            	VisitEventDNISGroupWrapper visitEventDNISGroupWrapper = (VisitEventDNISGroupWrapper) insertedObject;
            	if (visitEventDNISGroupWrapper.isInvalidDNIS()) {
            		
                VisitEventDNISGroup visitEventDNISGroup = visitEventDNISGroupWrapper.getVisitEventDNISGroup();
                List<VisitEventExt> visitEventExtList = visitEventDNISGroup.getVisitEvents();
                if(visitEventExtList != null) {
                    for (VisitEventExt visitEventExt :visitEventExtList) {
                        //send to queue that back to Call import to mark as processed calls
                        Queue queue = new ActiveMQQueue(processedCallIMportQueue);
                        JmsMessageSender.getInstance().sendObject(queue,visitEventExt);
                        logger.info(LoggingUtils.getLogMessageForProcessor(LoggingUtils.SUB_APP_CALL_MATCHING_SERVICE_KEYWORD
                        		, this.getClass(),"thanhxleobjectInserted ANI", "already sent call back to call import to marked as processed: {}"), visitEventExt);
                        //logger.info("objectInserted : already sent call back to call import to marked as processed {}", visitEventExt);
                    	}
                	}
            	}
            }
        }

    }

    
    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        logger.debug("objectUpdated : {}", objectUpdatedEvent.getObject());
    }

    
    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
        logger.debug("objectDeleted : {}", objectDeletedEvent.getOldObject());
       
    }
}
