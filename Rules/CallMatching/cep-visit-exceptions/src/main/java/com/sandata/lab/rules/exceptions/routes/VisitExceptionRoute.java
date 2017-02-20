package com.sandata.lab.rules.exceptions.routes;

import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.rules.call.model.VisitFact;
import com.sandata.lab.rules.exceptions.aggregation.StartVisitAndSchedulePredicate;
import com.sandata.lab.rules.exceptions.aggregation.TimedOutProcess;
import com.sandata.lab.rules.exceptions.aggregation.VisitAndScheduleAggregationStrategy;
import com.sandata.lab.rules.exceptions.bean.ExceptionCheck;
import com.sandata.lab.rules.exceptions.processors.*;


import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

import java.math.BigInteger;
import java.util.Hashtable;

/**
 * Created by tom.dornseif on 2/17/2016.
 */
public class VisitExceptionRoute extends RouteBuilder {

    Predicate invalidSk = header(PrepareForAggregation.SCHEDULE_EVNT_SK).isEqualTo("INVALID");
    Predicate isNullSk = header(PrepareForAggregation.SCHEDULE_EVNT_SK).isNull();
    Predicate isInvalidSK_Or_isNullSk = PredicateBuilder.or(invalidSk, isNullSk);
    private ProcessVisitAndScheduleExceptionRules processVisitAndScheduleExceptionRules = new ProcessVisitAndScheduleExceptionRules();
    private ProcessVisitExceptionRules processVisitExceptionRules = new ProcessVisitExceptionRules();
    private VisitExceptionProcessor visitExceptionProcessor = new VisitExceptionProcessor();
    private LogVisitExceptionProcessor logVisitExceptionProcessor = new LogVisitExceptionProcessor();
    public static int exceptionLookupSize = 0;
    private static Hashtable<BigInteger, ExceptionLookup> exceptionLookupHashtable = new Hashtable<>();
    private ExceptionListProcessor exceptionListProcessor = new ExceptionListProcessor();
    private ExceptionCheck exceptionCheck = new ExceptionCheck();
    public static Hashtable<BigInteger, ExceptionLookup> getExceptionLookupHashtable() {
        if(exceptionLookupHashtable == null || exceptionLookupHashtable.isEmpty()) {

        }
        return exceptionLookupHashtable;
    }

    public static void setExceptionLookupHashtable(Hashtable<BigInteger, ExceptionLookup> exceptionLookupHashtable) {
        VisitExceptionRoute.exceptionLookupHashtable = exceptionLookupHashtable;
    }

    @Override
    public void configure() throws Exception {

        if(processVisitAndScheduleExceptionRules == null) {
            processVisitAndScheduleExceptionRules = new ProcessVisitAndScheduleExceptionRules();
        }

        if(processVisitExceptionRules == null) {
            processVisitExceptionRules = new ProcessVisitExceptionRules();
        }

        if(visitExceptionProcessor == null) {
            visitExceptionProcessor = new VisitExceptionProcessor();
        }

        if(logVisitExceptionProcessor == null) {
            logVisitExceptionProcessor = new LogVisitExceptionProcessor();
        }
        if(exceptionListProcessor == null) {
            exceptionListProcessor = new ExceptionListProcessor();
        }
        from("{{activeMQ.endpoint.CEP_ENGINE_VISIT_EXCEPTION_ROUTE}}")
                .process(processVisitExceptionRules)
                .choice().when(body().isNotNull())
                    .to("kie:ksessionVisitExceptions?action=execute")
                .otherwise()
                    .log("CEP_ENGINE_VISIT_EXCEPTION_ROUTE BODY WAS NULL SO NOT SENDING TO RULES ENGINE!")
                .endChoice();


        from("activemq:queue:AGGREGATE_VISIT_AND_SCHEDULE_EXCP_EVENTS?concurrentConsumers=1&maxMessagesPerTask=1&destination.consumer.exclusive=true&destination.consumer.priority=10").routeId("activemq-AGGREGATE_VISIT_AND_SCHEDULE_EXCP_EVENTS")
                .choice()
                    .when(isInvalidSK_Or_isNullSk)
                        .log("Invalid ScheduleEventSK Visit will not be aggregated!")
                    .otherwise()
                        .to("direct:goodScheduledVisit");


        from("direct:goodScheduledVisit")
                .process(new PrepareForAggregation())
                .choice()
                .when(body().isNotNull())
                    .to("activemq:queue:aggregateVisitToScheduledVisit")
                .otherwise()
                    .log("Body -VISIT was null or not scheduled (unplanned)");

        from("activemq:queue:aggregateVisitToScheduledVisit")
                .aggregate(header(PrepareForAggregation.SCHEDULE_EVNT_SK), new VisitAndScheduleAggregationStrategy())
                .ignoreInvalidCorrelationKeys()
                .completionPredicate(new StartVisitAndSchedulePredicate()).completionTimeout(45000)
                .bean(TimedOutProcess.class, "processMatches")
                .choice()
                    .when(body().isNotNull())
                        .log("running rules")
                        .to("{{activeMQ.endpoint.CEP_ENGINE_VISIT_AND_SCHEDULE_EXCEPTION_ROUTE}}")
                        .log("completed aggregation route")
                    .otherwise()
                        .log("Probably timedout waiting for a visit to return from createSchedule because no match was found.  Body was null from TimedoutProcess.processMatches")
                        .log("completed aggregation route");



        from("{{activeMQ.endpoint.CEP_ENGINE_VISIT_AND_SCHEDULE_EXCEPTION_ROUTE}}")
                .process(processVisitAndScheduleExceptionRules)
                .to("kie:ksessionScheduledVisitExceptions?action=execute");

        from("{{activeMQ.endpoint.CEP_VISIT_EXCEPTIONS}}")
                .process(visitExceptionProcessor)
                .idempotentConsumer(header("DUPLICATE_ID"), MemoryIdempotentRepository.memoryIdempotentRepository(50))
                .to("{{activeMQ.endpoint.CEP_VISIT_EXCEPTIONS_REQUEST}}");

        from("{{activeMQ.endpoint.CEP_VISIT_EXCEPTIONS_RESPONSE}}")
                .process(logVisitExceptionProcessor);

        from("{{activeMQ.endpoint.CEP_EXC_LKUP_RESPONSE}}")
                .process(exceptionListProcessor)
                .log("Finished processing Visit Exceptions!");

        from("timer://ExceptionLookupTimer?fixedRate=true&period={{timerPeriodPropertyExceptionLookup}}&delay={{timerDelayPropertyExceptionLookup}}")
                .to("activemq:queue:CEP_EXC_LKUP_REQUEST")
                .log("ExceptionLookupTimer ROUTE FIRED AND REQUEST SENT");

        /*from("timer://scheduledVisitsExceptionsTimer?fixedRate=true&period={{timerPeriodPropertyScheduledVisitExceptions}}&delay={{timerDelayPropertyScheduledVisitExceptions}}&repeatCount={{timerRepeatCountPropertyScheduledVisitExceptions}}")
                .process(new InitFromToValuesProcesor())
                .to("activemq:queue:ScheduledVisitExceptionsCheckRequest")
        .log("scheduledVisitsExceptionsTimer ROUTE FIRED AND REQUEST SENT");

        from("activemq:queue:ScheduledVisitExceptionsCheckResponse").routeId("ScheduledVisitExceptionsCheckResponse")
                .split(simple("${body}"))
                .process( new PrepCheckerResponseForAggregation())
                .to("activemq:queue:aggregateVisitToScheduledVisit");
        */

        from("direct:startScheduleVisitCleaner").routeId("entryToScheduledVisitCleaner")
                .process(new InitFromToValuesProcesor())
                .to("activemq:queue:continueScheduledVisitExceptionCleaning")
                .log("ScheduledVisitExceptionsCleaner starting clean process.");

        from("activemq:queue:continueScheduledVisitExceptionCleaning?concurrentConsumers=1").routeId("continuedScheduledVisitExceptionCleaning")
                .throttle(1)
                .timePeriodMillis(20 * 1000)
                .process(new FromToValuesProcessor())
                .choice().when(body().isNotNull())
                    .to("activemq:queue:ScheduledVisitExceptionsCheckRequest")
                    .log("ScheduledVisitExceptionsCleaner call Visit Event Data Service to check Scheduled Visit Exceptions with days back decremented.")
                .otherwise()
                    .log("ScheduledVisitExceptionsCleaner Route Completed!  Processing will halt now.")
                .endChoice();

        from("activemq:queue:ScheduledVisitExceptionsCheckResponse").routeId("ScheduledVisitExceptionsCheckResponse")
                .split(simple("${body}"))
                .wireTap("activemq:queue:ScheduledVisitExceptionsWiretap")
                .process( new PrepCheckerResponseForAggregation())
                .to("activemq:queue:aggregateVisitToScheduledVisit");

        from("activemq:queue:ScheduledVisitExceptionsCheckResponseArrayTemplate").routeId("ScheduledVisitExceptionsCheckResponseArrayTemplate")
                .split(simple("${body}"))
                .wireTap("activemq:queue:ScheduledVisitExceptionsWiretap")
                .process( new PrepCheckerResponseForAggregation())
                .to("activemq:queue:aggregateVisitToScheduledVisit");


        from("activemq:queue:ScheduledVisitExceptionsWiretap?concurrentConsumers=2&destination.consumer.priority=9")
                .routeId("ScheduledVisitExceptionsWiretap")
                .process(new ProcessSchdlVisitExcpWireTapLog());
     //  Visit Exceptions

        from("timer://VisitsExceptionsTimer?fixedRate=true&period={{timerPeriodPropertyVisitExceptions}}&delay={{timerDelayPropertyVisitExceptions}}&repeatCount={{timerRepeatCountPropertyVisitExceptions}}")
                .process(new InitFromToValuesProcesor())
                .to("activemq:queue:startCleaner")
                .log("VisitsExceptionsTimer ROUTE FIRED AND REQUEST SENT");

        from("activemq:queue:startCleaner?concurrentConsumers=1").routeId("cleanerEntryPoint")
                .throttle(1)
                .timePeriodMillis(10 * 1000)
                .process(new FromToValuesProcessor())
                .choice()
                .when(body().isNotNull())
                    .to("activemq:queue:VisitExceptionsCheckRequest")
                    .log("visitExceptionCheckRequestCalled recall startCleaner with period back decremented.")
                .otherwise()
                    .log("VisitExceptionCleaner route completed!  Start Scheduled Visit Exceptions checker now.")
                    .to("direct:startScheduleVisitCleaner")
                .endChoice();

        from("activemq:queue:VisitExceptionsCheckResponse").routeId("VisitExceptionsCheckResponse")
                .split(body())
                .wireTap("activemq:queue:VisitExceptionsWiretap")
                .multicast()
                .to("activemq:queue:CEP_CLEAR_VISIT_EXCEPTIONS_REQUEST?exchangePattern=InOut", "{{activeMQ.endpoint.CEP_ENGINE_VISIT_EXCEPTION_ROUTE}}");



         from("activemq:queue:VisitExceptionsCheckResponseArrayTemplate").routeId("VisitExceptionsCheckResponseArrayTemplateProducer")
                 .split(simple("${body}"))
                 .wireTap("activemq:queue:VisitExceptionsWiretap")
                 .to("{{activeMQ.endpoint.CEP_ENGINE_VISIT_EXCEPTION_ROUTE}}");

         from("activemq:queue:VisitExceptionsWiretap?concurrentConsumers=2&destination.consumer.priority=9")
                 .routeId("VisitExceptionsWiretap")
                 .process(new ProcessWireTapLog());





        //run 1 time back for 5 minutes only.
        from("timer://autoCheckExceptionsShort?period={{autoCheckExceptionsShortPeriod}}&delay={{autoCheckExceptionsShortDelay}}&repeatCount={{autoCheckExceptionsShortRepeatCount}}")
                .process(new CheckShortTimeProcessor())
                .to("activemq:queue:shortTimeVisitExceptionsCheckRequest");


        from("activemq:queue:callNextShortTimeProcess")
                .routeId("callNextShortTimeProcess")

                .process(new CheckShortTimeProcessor())
                .to("activemq:queue:shortTimeScheduledVisitExceptionsCheckRequest");

    }







}
