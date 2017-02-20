package com.sandata.lab.rules.exceptions.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.exceptions.app.AppContext;
import com.sandata.lab.rules.call.model.Schedule;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.*;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tom.dornseif on 11/21/2015.
 */
public class ProducerPojo {

    Logger logger = LoggerFactory.getLogger(ProducerPojo.class);
    @Produce
    private ProducerTemplate template;

    public void sendBatchExecuteCommand(BatchExecutionCommandImpl command) {
        logger.info("Sending BatchExecuteCommand now ");
        this.getTemplate().sendBody("activemq:queue:EXECUTE_BATCH_EXECUTE_COMMAND", command);
    }

    public void sendSchedule(Schedule schedule) {

        this.getTemplate().sendBody("activemq:queue:VISIT_EVENTS", schedule);
    }

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
                if(c==null) {
            //this only happens when the bundle starts
                    try {
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException ie) {
                        logger.info("interrupted exception");
                    }
                    c = AppContext.getContext();
                    if(c==null) {

            /* try {
                    c.start();
                }
                catch(Exception ex)
                {
                    logger.info("Exception couldnt start Context but it should have been running or we couldnt be here." + ex.getLocalizedMessage());

                }*/
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

    public void sendVisit(Visit v) {
        logger.info("sending class type of Visit ");
        this.getTemplate().sendBody("activemq:queue:CEP_ENGINE_VISITS", v);
    }
}
