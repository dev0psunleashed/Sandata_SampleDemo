package com.sandata.lab.rules.call.matching.routes;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.data.model.response.visit.ScheduleEventResponse;
import com.sandata.lab.rules.call.matching.processors.ScheduleEventResponseProcessor;
import com.sandata.lab.rules.call.matching.splitter.ScheduleEventVisitSplitter;
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

        from("activemq:queue:SCHEDULE_EVENT_RESPONSE")
                .routeId("cep-engine:ScheduleResponseRoute")
                .process(scheduleEventResponseProcessor)
                .to("seda:processSchedules");

        from("seda:processSchedules")
                .log("processSchedule:::: array ")
                .to("activemq:queue:AGGREGATE_VISIT_EVENTS");

/*
        from("activemq:queue:EXECUTE_BATCH_EXECUTE_COMMAND")
                .to("kie:ksessioncallMatching1?action=execute")
                .log(" execute kieSession fireAllRules calls-matching rules complete!");
*/

    }
}
