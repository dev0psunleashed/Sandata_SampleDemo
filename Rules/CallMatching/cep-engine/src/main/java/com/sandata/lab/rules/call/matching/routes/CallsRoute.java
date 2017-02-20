package com.sandata.lab.rules.call.matching.routes;


import com.sandata.lab.rules.call.matching.processors.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.spring.SpringRouteBuilder;
//import org.apache.camel.spring.spi.SpringTransactionPolicy;


import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

public class CallsRoute extends RouteBuilder {

	private ScheduleProcessorProducer scheduleProcessorProducer = new ScheduleProcessorProducer();
	private ScheduleEventRequestProcessor scheduleEventRequestProcessor = new ScheduleEventRequestProcessor();
	private ConfigsPropertiesAndLookupsProcessor configsPropertiesAndLookupsProcessor = new ConfigsPropertiesAndLookupsProcessor();
	private CheckForDuplicateCalls checkForDuplicateCalls = new CheckForDuplicateCalls();
	private AssignUniqueRequestID assignUniqueRequestID = new AssignUniqueRequestID();
	@Override
	public void configure() throws Exception {

		if(scheduleProcessorProducer == null) {
			scheduleProcessorProducer = new ScheduleProcessorProducer();
		}
		if(configsPropertiesAndLookupsProcessor == null) {
			configsPropertiesAndLookupsProcessor = new ConfigsPropertiesAndLookupsProcessor();
		}
		if(checkForDuplicateCalls == null) {
			checkForDuplicateCalls = new CheckForDuplicateCalls();
		}
		if(assignUniqueRequestID == null) {
			assignUniqueRequestID = new AssignUniqueRequestID();
		}


		from("activemq:queue:CEP_ENGINE_CALLS?destination.consumer.exclusive=true").routeId("CEP_ENGINE_CALLS")
				.process(checkForDuplicateCalls)
				.idempotentConsumer(header("DUPLICATE_ID")).messageIdRepository( MemoryIdempotentRepository.memoryIdempotentRepository()).skipDuplicate(false)
				.filter(exchangeProperty(Exchange.DUPLICATE_MESSAGE).isEqualTo(true))
					.log("duplicate ${header.DUPLICATE_ID} ")
					.to("direct:duplicate")
					.stop()
				.end()
				.process(assignUniqueRequestID)
				.multicast().parallelProcessing()
				.to("direct:aggregate", "direct:scheduleProcessor");

		from("direct:duplicate").routeId("duplicate_logger")
				.process(new DuplicateLoggerProcessor());

		from("direct:aggregate")
				.log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} patientId: ${body.patientID} - to AGGREGATE_VISIT_EVENTS")
				.to("activemq:queue:AGGREGATE_VISIT_EVENTS");

		from("direct:scheduleProcessor")
				.log("dnis : ${body.dnis}, staffID : ${body.staffID}, ani : ${body.ani} patientId: ${body.patientID} - to ScheduleEventRequestProcessor")
				.process(scheduleEventRequestProcessor)
				.to("activemq:queue:SCHEDULE_EVENT_REQUEST");

		from("seda:CEP_ENGINE_MESSAGES")
				.log(" message contained ${body}");

/*		from("direct:prepDNIS")
				.process(configsPropertiesAndLookupsProcessor)
				.choice().when(body().isNull())
					.log("Configs, Properties, and lookups all ready set and not time for refresh!")
				.otherwise()
					.to("activemq:queue:CEP_EXC_LKUP_REQUEST")
				.endChoice();
*/
				//.multicast()
				//.parallelProcessing()
				//.to("activemq:queue:CEP_CONFIG", "activemq:queue:CEP_VISIT_EXCEPTIONS")




	}
}
