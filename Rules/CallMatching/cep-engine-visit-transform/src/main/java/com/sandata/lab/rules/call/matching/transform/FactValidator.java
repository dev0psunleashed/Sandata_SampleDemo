package com.sandata.lab.rules.call.matching.transform;

import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class FactValidator implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(FactValidator.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		if(in.getBody() instanceof VisitEventFact) {
			logger.info("Validated message body is of type VisitEventFact!");
			VisitEventFact c = in.getBody(VisitEventFact.class);
			logger.info("Call.ani = " + c.getAni() + " Call.dnis = " + c.getDnis()); 
		}
		else if (in.getBody() instanceof VisitEventExt) {
			logger.info("Validated message body is of type VisitEventExt!");
			VisitEventExt v = in.getBody(VisitEventExt.class);
			logger.info("VisitEvent.ani = " + v.getAutomaticNumberIdentification() + " VisitEvent.dnis = " + v.getDialedNumberIdentificationService());
		}
		else {

			logger.info("Type was not able to validate maybe was base type of VisitEvent!");
		}	/*if(in.getBody() instanceof VisitEvent) {
			logger.info("Validated message body is of type VisitEvent!");
			VisitEvent v = (VisitEvent)in.getBody();
			logger.info("VisitEvent.ani = " + v.getAutomaticNumberIdentification() + " VisitEvent.dnis = " + v.getDialedNumberIdentificationService());
		}*/


	}

}
