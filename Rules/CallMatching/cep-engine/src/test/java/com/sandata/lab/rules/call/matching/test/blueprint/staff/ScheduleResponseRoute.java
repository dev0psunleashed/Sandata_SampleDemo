package com.sandata.lab.rules.call.matching.test.blueprint.staff;

import com.sandata.lab.rules.call.matching.processors.ScheduleEventResponseProcessor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by tom.dornseif on 11/21/2015.
 */
public class ScheduleResponseRoute extends RouteBuilder {

    //SCHEDULE_EVENT_REQUEST("SCHEDULE_EVENT_REQUEST"),
    //SCHEDULE_EVENT_RESPONSE("SCHEDULE_EVENT_RESPONSE"),

    private ScheduleEventResponseProcessor scheduleEventResponseProcessor;

    @Override
    public void configure() throws Exception {


        scheduleEventResponseProcessor = new ScheduleEventResponseProcessor();

        from("{{activeMQ.endpoint.SCHEDULE_EVENT_RESPONSE}}")
                .routeId("cep-engine:ScheduleResponseRoute")
                .process(scheduleEventResponseProcessor)
                .to("direct:processSchedules");

        from("direct:processSchedules")
                .log("processSchedule:::: array ")
                .to("{{activeMQ.endpoint.AGGREGATE_VISIT_EVENTS_TO}}");


/*
        from("activemq:queue:EXECUTE_BATCH_EXECUTE_COMMAND")
                .to("kie:ksessioncallMatching1?action=execute")
                .log(" execute kieSession fireAllRules calls-matching rules complete!");
*/

    }
}
