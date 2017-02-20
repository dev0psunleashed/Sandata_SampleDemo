package com.sandata.lab.rest.sched.utils.data;

import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.util.ExchangeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by khangle on 01/31/2017.
 */
public class VisitExceptionHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitExceptionHelper.class);

    public static void triggerCheckVisitException(final Exchange exchange, long visitSk) {
        Exchange newExchange = ExchangeHelper.createCopy(exchange, false);
        newExchange.setPattern(ExchangePattern.InOnly);

        try {
            ProducerTemplate producerTemplate = newExchange.getContext().createProducerTemplate();
            producerTemplate.sendBody("activemq:queue:SANDATA_VISIT_EXCEPTION_ENTRY_ROUTE", String.valueOf(visitSk));
        } catch (Exception e) {
            LOGGER.error("There's issue when sending visitSk=" + visitSk + " to SANDATA_VISIT_EXCEPTION_ENTRY_ROUTE queue", e.getStackTrace());
        }
    }
}
