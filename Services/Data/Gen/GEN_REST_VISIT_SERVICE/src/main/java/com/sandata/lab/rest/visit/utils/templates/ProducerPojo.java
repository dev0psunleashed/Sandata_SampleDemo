package com.sandata.lab.rest.visit.utils.templates;

import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rest.visit.app.AppContext;
import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by tom.dornseif on 3/13/2016.
 */
public class ProducerPojo {

    Logger logger = LoggerFactory.getLogger(ProducerPojo.class);

    @Produce
    private ProducerTemplate template;

    public ProducerTemplate getTemplate() {
        if(this.template == null) {
            logger.info("Create ProducerTemplate from camelContext ");
            CamelContext c = AppContext.getContext();
            if (c == null) {
                try {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ie) {
                    logger.info("interrupted exception");
                }
                c = AppContext.getContext();
                if(c==null) {//this only happens when the bundle starts
                    try {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ie) { logger.info("interrupted exception"); }
                    c = AppContext.getContext();
                    if(c==null) {
                        logger.info("AppContext didn't return non-null camelContext.  Routes may not be started yet. ");
                    }}}
            if(c.getStatus() != ServiceStatus.Started)
                try{c.start();}catch(Exception ex) { logger.info(" Couldn't start camelContext"); }
            this.template = c.createProducerTemplate();
        }
        return this.template;
    }

    public void setTemplate(ProducerTemplate template) {
        this.template = template;
    }

    public void sendBody(String endPoint, Object object) {
        logger.info("sending class type of " + object.getClass().getName());
        this.getTemplate().sendBody(endPoint, object);
    }

    public void sendVisitSKForExceptionsToClear(BigInteger visitSk) {
        logger.info("sending class type of BigInteger VisitSK to Clear All Exceptions for Visit ");
        this.getTemplate().sendBody("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST", visitSk);
    }

    public void sendVisit(Visit v) {
        logger.info("sending class type of Visit ");
        this.getTemplate().sendBody("activemq:queue:CREATE_VISIT_RESPONSE", v);
    }

    public void queueData(Object data, String queueName, Map<String, Object> headers, SandataLogger logger) {

        if (data == null) {
            logger.error(String.format("%s: ERROR: data == NULL", getClass().getName()));
            return;
        }

        logger.info(String.format("%s: queueData: [Type: %s]: [QueueName: %s]: Data Queued!",
                getClass().getName(), data.getClass().getName(), queueName));

        this.getTemplate().sendBodyAndHeaders(queueName, data, headers);
    }
}
