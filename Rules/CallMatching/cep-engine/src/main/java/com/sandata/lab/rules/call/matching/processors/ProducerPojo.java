package com.sandata.lab.rules.call.matching.processors;

import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.rules.call.matching.app.AppContext;
import com.sandata.lab.rules.call.model.Schedule;
import org.apache.camel.*;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tom.dornseif on 11/21/2015.
 */
public class ProducerPojo {

    Logger rulesDefaultLogger = LoggerFactory.getLogger("defaultRulesLogger");
    @Produce
    private ProducerTemplate template;
    private static CamelContext camelContext;

    public void sendBatchExecuteCommand(BatchExecutionCommandImpl command) {
        rulesDefaultLogger.info("Sending BatchExecuteCommand now ");
        this.getTemplate().sendBody("activemq:queue:EXECUTE_BATCH_EXECUTE_COMMAND", command);
    }
    public ProducerPojo(){}
    public ProducerPojo(CamelContext context){
        camelContext = context;
        this.template = context.createProducerTemplate();
    }

    public void sendSchedule(Schedule schedule) {

        this.getTemplate().sendBody("activemq:queue:VISIT_EVENTS", schedule);
    }

    public ProducerTemplate getTemplate() {
        if(this.template == null) {
            rulesDefaultLogger.info("Create ProducerTemplate from camelContext ");
            CamelContext context = AppContext.getContext();
            if(context.getStatus() != ServiceStatus.Started)
                try{context.start();}catch(Exception ex) { rulesDefaultLogger.info(" Couldn't start camelContext"); }
            this.template = context.createProducerTemplate();

        }

        return this.template;
    }

    public void setTemplate(ProducerTemplate template) {
        this.template = template;
    }

    public void sendBody(String endPoint, Object object) {
        rulesDefaultLogger.info("sending class type of " + object.getClass().getName());
        this.getTemplate().sendBody(endPoint, object);
    }

    public void sendVisit(Visit v) {
        rulesDefaultLogger.info("sending class type of Visit ");
        this.getTemplate().sendBody("activemq:queue:CEP_ENGINE_VISITS", v);
    }
}
