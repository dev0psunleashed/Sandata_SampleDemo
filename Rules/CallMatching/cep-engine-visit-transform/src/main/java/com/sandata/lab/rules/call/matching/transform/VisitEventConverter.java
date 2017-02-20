package com.sandata.lab.rules.call.matching.transform;


import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;
import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisitEventConverter implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(CallFactConverter.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
        Thread.sleep(1000);
		Message in = exchange.getIn();
		if(in.getBody() instanceof VisitEventFact) {
			logger.info(" Validated message body is of type VisitEvent Fact!");
            VisitEventFact visitEventFact = in.getBody(VisitEventFact.class);
            logger.info("visitEventFact.ani : " + visitEventFact.getAni() + " visitEventFact.dnis : " + visitEventFact.getDnis());
            VisitEventExt visitEventExt = visitEventFact.getVisitEventExt();
            logger.info(String.format(" Before placing Back In Body of Exchange cast to VisitEventExt with dnis : %s , with ani : %s ", visitEventExt.getDialedNumberIdentificationService(), visitEventExt.getAutomaticNumberIdentification()));
            	exchange.getIn().setBody(visitEventExt);
		}
		else if(in.getBody() instanceof VisitEvent) {
			VisitEventFact vf = new VisitEventFact(in.getBody(VisitEvent.class));
			VisitEventExt visitEventExt = vf.getVisitEventExt();
			logger.info(String.format(" Before placing Back In Body of Exchange cast to VisitEventExt with dnis : %s , with ani : %s ", visitEventExt.getDialedNumberIdentificationService(), visitEventExt.getAutomaticNumberIdentification()));
			exchange.getIn().setBody(visitEventExt);

		}
		

	}
		



}