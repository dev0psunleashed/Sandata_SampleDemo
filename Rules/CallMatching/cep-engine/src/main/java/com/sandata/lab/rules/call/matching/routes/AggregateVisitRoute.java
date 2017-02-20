package com.sandata.lab.rules.call.matching.routes;

import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.rules.call.matching.aggregate.*;
import com.sandata.lab.rules.call.matching.processors.*;
import com.sandata.lab.rules.call.model.VisitFact;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by tom.dornseif on 11/24/2015.
 */
public class AggregateVisitRoute extends RouteBuilder{
    //private String sourceUri;
    //private String targetUri;
    public static final String SANTRAX_ID = "santraxId";


    @Override
    public void configure() throws Exception {

        Predicate isAggregate = header(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT);
/*        <bean id="scheduleVisitEventRepo" class="org.apache.camel.component.leveldb.LevelDBAggregationRepository">
        <property name="persistentFileName" value="target/data/repository/cep-engine-visit-repo.dat" />
        <property name="repositoryName" value="ScheduleVisitEventRepo" />
        </bean>
*/
        //this route accepts VisitEvents from call server and from schedule response and matches scheduled visits
        // 80% plus of all scheduled visits will fall here.  Thos that come back nshceduled (no perfact match or no
        // schedules will goto staff rules for staff rule matching logic.
        from("activemq:queue:AGGREGATE_VISIT_EVENTS?concurrentConsumers=1&maxMessagesPerTask=1&destination.consumer.exclusive=true&destination.consumer.priority=10").routeId("activemq-AGGREGATE_VISIT_EVENTS")
                .process(new PrepForAggregation())
                //will aggregate VisitEventFact and VisitFact (including schedule events) for further processing
                .aggregate(header(PrepForAggregation.REQUEST_UUID_FOR_AGGREGATION), new VisitAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionPredicate(new StartVisitPredicate()).completionTimeout(30000)
                .bean(StartVisitAndScheduleProcess.class, "processMatches")
                .choice()
                .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.INVALID_STAFF}")
                    .log("Processing Invalid Staff")
                    .process(new ProcessInvalidStaffAndUnplannedVisit())
                    .log("Sending to VisitEventRequest queue for saving to database.")
                    .to("direct:sendVisit")//.to("seda:sendVisitEvent")
                .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.TRY_FUZZY_MATCH_FOR_STAFF_ID}")
                    .to("direct:prepForStaffRules")
                .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.NO_SCHEDULES}")
                    .process(new ProcessInvalidStaffAndUnplannedVisit())
                    .to("direct:sendVisit")
                .otherwise()
                    .log("Otherwise we are running rules with state = ${header.STATE}")
                    .log("Value of STATE was not: NO_SCHEDULES, TRY_FUZZY_MATCH_FOR_STAFF_ID, INVALID_STAFF")
                    .to("direct:callMatchingRules");

        from("seda:sendVisitEvent")
                .to("activemq:queue:"+ Visit.EVENT.CREATE_VISIT_EVENT_REQUEST.toString()).routeId(Visit.EVENT.CREATE_VISIT_EVENT_REQUEST.toString());

        from("activemq:queue:" + Visit.EVENT.CREATE_VISIT_EVENT_RESPONSE.toString()).routeId(Visit.EVENT.CREATE_VISIT_EVENT_RESPONSE.toString())
                .log("VisitEvent ${body.staffID} VisitEvent_SK = ${body.visitEventSK} ");

        from("direct:aggregationCompleted").routeId("direct-aggregationCompleted")
                .onCompletion().onWhen( body().isInstanceOf(VisitFact.class) )
                    .to("log:visitFact processed")
                    .to("direct:sendVisit")
                .end()
                .log("aggregation completed!");

        from("direct:callMatchingRules")
                .process(new PrepareRulesBatch())
                .choice()
                    .when(body().isNull())
                        .log("ERROR CANNOT RUN RULES ON EMPTY OBJECT!")
                .otherwise()
                    .to("kie:ksession-cepschedule?action=execute")
                    .log("all rules have fired")
                .endChoice();




        from("direct:sendVisit").routeId("direct-sendVisit")
                .log("Sending visitFact for staff ${body.staffId}")
                //.to("mock:CEP_ENGINE_VISITS");
                .to("activemq:queue:CEP_ENGINE_VISITS");
//FUZZY LOGIC STAFF MATCHING
        from("direct:prepForStaffRules")
                .multicast().parallelProcessing()
                .to("direct:prepStaffToAggregation", "direct:makeStaffRequest", "direct:holdUnplannedVisit")
                .end();

        from("direct:prepStaffToAggregation").routeId("direct-prepStaffToAggregation")
                .process(new VisitToStaffRequestAndAggregation())
                .to("activemq:queue:AGGREGATE_STAFF");

        from("direct:makeStaffRequest")
                .process(new CreateStaffRequestFromVisitFact())
                .to("activemq:queue:PATIENT_STAFF_REQUEST");

        from("direct:holdUnplannedVisit").routeId("ScheduleRequestResubmission")
                .process(new ProcessResubmission())
                .to("seda:StaffToCallEventAggregator");


       from("seda:StaffToCallEventAggregator")
               .aggregate(header(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT), new StaffAndVisitEventAggregationStrategy())
               .ignoreInvalidCorrelationKeys()
               .completionPredicate(new StartStaffPredicate()).completionTimeout(60000)
               .bean(ResubmissionAggregation.class, "processMatches")
               .to("activemq:queue:CEP_ENGINE_CALLS");


        from("activemq:queue:AGGREGATE_STAFF").routeId("AGGREGATE_STAFF")
                .aggregate(header(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT), new StaffAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionPredicate(new StartStaffPredicate()).completionTimeout(60000)
                .choice()
                    .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.AGG_INSERTED_FROM_RESPONSE}")
                        .process(new ProcessStaffRules())
                        .choice()
                            .when(body().isNull())
                                .log("No Staff Found in Database!")
                            .otherwise()
                                .to("kie:ksessioncallMatching2?action=execute")
                                .log(" execute kieSession fireAllRules calls-matching rules complete!")
                        .endChoice()
                    .otherwise()
                        .log("Header contained NO_MATCH = No Staff Found in Database!")
                .endChoice();





        from("activemq:queue:PATIENT_STAFF_RESPONSE")
                .process(new ProcessStaffResponse())
                .to("activemq:queue:AGGREGATE_STAFF");




        from("activemq:queue:AGG_SCHEDULE_REQUEST_RESUBMISSION")
                .aggregate(header(PrepForAggregation.DNIS_AND_STAFFID_AND_PATIENT), new ResubmitAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionPredicate(new StartStaffPredicate()).completionTimeout(60000)
                .bean(ScheduleResubmission.class, "processMatches")
                .choice()
                    .when(body().isNull())
                        .log("Body was null!")
                    .otherwise()
                        .log("AGG_SCHEDULE_REQUEST_RESUBMISSION ${body}")
                .endChoice();

/*
        from("activemq:queue:AGG_VISIT_EVENT_CORRECTED_STAFF")
        .process(new PrepForAggregation())
                .aggregate(header(PrepForAggregation.DNIS_AND_STAFFID), new VisitAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionPredicate(new StartVisitPredicate()).completionTimeout(30000)
                .bean(StartVisitAndScheduleProcess.class, "processMatches")
                .choice()
                .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.INVALID_STAFF}")
                .log("Processing Invalid Staff")
                .process(new ProcessInvalidStaffAndUnplannedVisit())
                .log("Sending to VisitEventRequest queue for saving to database.")
                .to("seda:sendVisitEvent")
                //.when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.TRY_FUZZY_MATCH_FOR_STAFF_ID}")
                //.to("direct:prepForStaffRules")
                .when().simple("${header.STATE} == ${type:com.sandata.lab.rules.call.model.State.NO_SCHEDULES}")
                .process(new ProcessInvalidStaffAndUnplannedVisit())
                .to("direct:sendVisit")
                .otherwise()
                .log("Otherwise we are running rules")
                .log("Value of STATE was not: NO_SCHEDULES, TRY_FUZZY_MATCH_FOR_STAFF_ID, INVALID_STAFF")
                .to("direct:callMatchingRules");
*/
//.aggregationRepositoryRef("scheduleVisitEventRepo")




    }
}
