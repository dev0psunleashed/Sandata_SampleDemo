package com.sandata.lab.rules.call.matching.test.blueprint.calls;


import com.sandata.lab.rules.call.matching.processors.ConfigsPropertiesAndLookupsProcessor;
import com.sandata.lab.rules.call.matching.processors.ScheduleEventRequestProcessor;
import com.sandata.lab.rules.call.matching.processors.ScheduleProcessorProducer;

import org.apache.camel.builder.RouteBuilder;

//import org.apache.camel.spring.SpringRouteBuilder;
//import org.apache.camel.spring.spi.SpringTransactionPolicy;

public class CallsRoute extends RouteBuilder {

	private ScheduleProcessorProducer scheduleProcessorProducer = new ScheduleProcessorProducer();
	private ScheduleEventRequestProcessor scheduleEventRequestProcessor = new ScheduleEventRequestProcessor();
	private ConfigsPropertiesAndLookupsProcessor configsPropertiesAndLookupsProcessor = new ConfigsPropertiesAndLookupsProcessor();
	@Override
	public void configure() throws Exception {

	 //older version   
	/*	if(scheduleProcessorProducer == null) {
			scheduleProcessorProducer = new ScheduleProcessorProducer();
		}

		from("activemq:queue:CEP_ENGINE_CALLS?destination.consumer.exclusive=true").routeId("CEP_ENGINE_CALLS")
				.multicast().parallelProcessing()
				.to("direct:aggregate", "direct:scheduleProcessor")
				.end();


		from("direct:aggregate").routeId("direct-aggregate")
				.log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} - to AGGREGATE_VISIT_EVENTS")
				.to("activemq:queue:AGGREGATE_VISIT_EVENTS");

		from("direct:scheduleProcessor").routeId("direct-scheduleProcessor")
				.log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} - to ScheduleEventRequestProcessor")
				.process(scheduleEventRequestProcessor)
				.to("activemq:queue:SCHEDULE_EVENT_REQUEST");

*/
		
		if(scheduleProcessorProducer == null) {
            scheduleProcessorProducer = new ScheduleProcessorProducer();
        }
        if(configsPropertiesAndLookupsProcessor == null) {
            configsPropertiesAndLookupsProcessor = new ConfigsPropertiesAndLookupsProcessor();
        }

        from("activemq:queue:CEP_ENGINE_CALLS?destination.consumer.exclusive=true").routeId("CEP_ENGINE_CALLS")
                .multicast().parallelProcessing()
                .to("direct:aggregate", "direct:scheduleProcessor", "activemq:queue:RETRACTED_CALLS", "direct:prepDNIS")
                .end();


        from("direct:aggregate")
                .log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} - to AGGREGATE_VISIT_EVENTS")
                .to("activemq:queue:AGGREGATE_VISIT_EVENTS");

        from("direct:scheduleProcessor")
                .log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} - to ScheduleEventRequestProcessor")
                .process(scheduleEventRequestProcessor)
                .to("activemq:queue:SCHEDULE_EVENT_REQUEST");

        from("seda:CEP_ENGINE_MESSAGES")
                .log(" message contained ${body}");

        from("direct:prepDNIS")
                .process(configsPropertiesAndLookupsProcessor)
                .choice().when(body().isNull())
                    .log("Configs, Properties, and lookups all ready set and not time for refresh!")
                .otherwise()
                    .to("activemq:queue:CEP_EXC_LKUP_REQUEST")
                .endChoice();

                //.multicast()
                //.parallelProcessing()
                //.to("activemq:queue:CEP_CONFIG", "activemq:queue:CEP_VISIT_EXCEPTIONS")




	}
}
