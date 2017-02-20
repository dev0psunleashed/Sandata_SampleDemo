package com.sandata.lab.rules.call.matching.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.runtime.impl.ExecutionResultImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/12/2015
 * Time: 9:00 PM
 */
public class ProcessBatchExecuteResult implements org.apache.camel.Processor {
    /**
     * Processes the message exchange
     *
     * @param exchange the message exchange
     * @throws Exception if an internal processing error has occurred.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in;
        Logger logger = LoggerFactory.getLogger(ProcessBatchExecuteResult.class);
        in = exchange.getIn();
        if(in.getBody() instanceof ExecutionResultImpl)
        {
            ExecutionResultImpl executionResult = in.getBody(ExecutionResultImpl.class);

            for (Map.Entry e : executionResult.getResults().entrySet()) {

                 logger.info(String.format("executionReuslt returned %s : %s", e.getKey(), e.getValue()));

            }

        }

    }
}
