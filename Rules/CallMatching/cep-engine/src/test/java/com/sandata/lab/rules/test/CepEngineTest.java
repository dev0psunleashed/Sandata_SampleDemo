package com.sandata.lab.rules.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/14/2015
 * Time: 6:15 AM
 */
public class CepEngineTest extends CamelBlueprintTestSupport {
    private boolean debugBeforeMethodCalled;
    private boolean debugAfterMethodCalled;


    @Override
    protected void debugBefore(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id, String label) {
        //super.debugBefore(exchange, processor, definition, id, label);
        log.info("Before " + definition + " with body " + exchange.getIn().getBody());
        debugBeforeMethodCalled = true;
    }

    @Override
    protected void debugAfter(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id, String label, long timeTaken) {
        //super.debugAfter(exchange, processor, definition, id, label, timeTaken);
        log.info("After " + definition + " with body " + exchange.getIn().getBody());
        debugAfterMethodCalled = true;
    }
}
