package com.sandata.lab.rules.test;

import com.sandata.lab.rules.call.model.VisitEventFact;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/14/2015
 * Time: 5:59 AM
 */
public class CallsRouteTest extends CamelBlueprintTestSupport {

    private boolean debugBeforeMethodCalled;
    private boolean debugAfterMethodCalled;



    @Override
    public boolean isUseAdviceWith() {
        return true;//super.isUseAdviceWith();
    }

    @Override
    protected String getBlueprintDescriptor() {
        String tmp = super.getBlueprintDescriptor();
        //Must have predefined tmp which is String object returned from super
        //This is high overhead and should only be used for testing and debug!
        //Tom Dornseif
        Object o = new Object(){};
        String className = o.getClass().getEnclosingClass().getName();
        String methodName = o.getClass().getEnclosingMethod().getName();
        String typeName = o.getClass().getEnclosingMethod().getReturnType().getName();
        org.slf4j.LoggerFactory.getLogger(o.getClass().getEnclosingClass()).info(String.format(
                "%s : method = %s : super returned type : %s with value of %s ", className, methodName, typeName, tmp));
        return "/com/sandata/lab/rules/call/matching/test/blueprint/calls/call-route.xml";
    }
    @Override
    public void setUp() throws Exception {
        org.slf4j.LoggerFactory.getLogger(StaffAndVisitAggregationTest.class).info("CallsRouteTest : super.setUp()");
        replaceRouteFromWith("CEP_ENGINE_CALLS", "direct:CEP_ENGINE_CALLS");

        super.setUp();
    }
    @Test
    public void testCallsFromImports() throws Exception {

        context.getRouteDefinitions().get(1);
        //direct-aggregate route
        context.getRouteDefinitions().get(1).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                  interceptSendToEndpoint("activemq:queue:AGGREGATE_VISIT_EVENTS")
                          .skipSendToOriginalEndpoint()
                          .to("mock:AGGREGATE_VISIT_EVENTS");
            }
        });
        
        //direct-scheduleProcessor
        context.getRouteDefinitions().get(2).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("activemq:queue:SCHEDULE_EVENT_REQUEST")
                        .skipSendToOriginalEndpoint()
                        .to("mock:SCHEDULE_EVENT_REQUEST");
            }
        });
        
        //from("direct:prepDNIS")
        context.getRouteDefinitions().get(4).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("activemq:queue:CEP_EXC_LKUP_REQUEST")
                        .skipSendToOriginalEndpoint()
                        .to("mock:CEP_EXC_LKUP_REQUEST");
            }
        });
        
        
        //context.start();
        MockEndpoint mockOut1 = getMockEndpoint("mock:AGGREGATE_VISIT_EVENTS");
        MockEndpoint mockOut2 = getMockEndpoint("mock:SCHEDULE_EVENT_REQUEST");
        MockEndpoint mockOut3 = getMockEndpoint("mock:CEP_EXC_LKUP_REQUEST");
        VisitEventFact visitEventExt = new VisitEventFact();
        visitEventExt.setAutomaticNumberIdentification("1231231234");
        visitEventExt.setDialedNumberIdentificationService("1231231234");
        visitEventExt.setStaffID("123456789");
        Date date = new Date();
        visitEventExt.setVisitEventDatetime(date);
        
        template.sendBody("direct:aggregate", visitEventExt);
        template.sendBody("direct:scheduleProcessor", visitEventExt);
        template.sendBody("direct:prepDNIS", visitEventExt);
        
        
        mockOut1.setExpectedMessageCount(1);
        mockOut2.setExpectedMessageCount(1);
        mockOut3.setExpectedMessageCount(1);
        assertMockEndpointsSatisfied();
       // context.stop();
    }



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
