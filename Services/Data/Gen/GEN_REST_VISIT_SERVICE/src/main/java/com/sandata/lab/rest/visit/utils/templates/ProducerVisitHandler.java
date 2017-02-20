package com.sandata.lab.rest.visit.utils.templates;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.Visit;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Map;

public class ProducerVisitHandler {

    Logger logger = LoggerFactory.getLogger(ProducerVisitHandler.class);

    public ProducerVisitHandler(Exchange exchange){
        buildProducerTemplate(exchange);
    }

    private void buildProducerTemplate(Exchange exchange){
      if(exchange == null || exchange.getContext() == null){
          throw new SandataRuntimeException("ProducerVisitHandler: the input Exchange cannot be null");
      }
      this.template = exchange.getContext().createProducerTemplate();
    }

    private ProducerTemplate template;
    public ProducerTemplate getTemplate() {
        return this.template;
    }

    public void setTemplate(ProducerTemplate template) {
        this.template = template;
    }

    public void sendBody(String endPoint, Object object) {
        logger.info("ProducerVisitHandler: sending class type of " + object.getClass().getName());
        //this.getTemplate().sendBody(endPoint, object);
    }

    public void sendVisitSKForExceptionsToClear(BigInteger visitSk) {
        logger.info("ProducerVisitHandler: sending class type of BigInteger VisitSK to Clear All Exceptions for Visit ");
        //this.getTemplate().sendBody("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST", visitSk);
    }

    public void sendVisit(Visit v) {
        logger.info("ProducerVisitHandler: sending class type of Visit ");
        //this.getTemplate().sendBody("activemq:queue:CREATE_VISIT_RESPONSE", v);
    }

    public void queueData(Object data, String queueName, Map<String, Object> headers, SandataLogger logger) {

        if (data == null) {
            logger.error(String.format("%s: ERROR: data == NULL", getClass().getName()));
            return;
        }

        logger.info(String.format("ProducerVisitHandler: %s: queueData: [Type: %s]: [QueueName: %s]: Data Queued!",
                getClass().getName(), data.getClass().getName(), queueName));

        this.getTemplate().sendBodyAndHeaders(queueName, data, headers);
    }
}

