package com.sandata.lab.rest.visit.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.rest.visit.processors.ProcessRoundingRules;
import com.sandata.lab.rest.visit.processors.TestProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;

import static org.apache.camel.builder.PredicateBuilder.and;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 1/27/2017
 * Time: 8:04 AM
 */
public class VvRndngRules extends AbstractRouteBuilder{

    Predicate responseNotNull = body().isNotNull();
    Predicate headerNotNull = header("visit_sk").isNotNull();
    Predicate valid = and(responseNotNull, headerNotNull);
    Long succesfulResponse = 1L;
    Predicate updated = and(valid, body().isEqualTo(succesfulResponse));

    @Override
    protected void buildRoute() {

        from("{{vv_rndng_rules_queue}}").routeId("{{vv_rndng_rules_queue")
                .process(new ProcessRoundingRules())
                .bean("restDataService", "updateVVBillableHrs").choice()
                .when(updated)
                    .log(LoggingLevel.INFO, "Successfully updated ${header.visit_sk}")
                .when(valid)
                    .log(LoggingLevel.INFO, "billable hours were not updated ${header.visit_sk}")
                .otherwise()
                    .log(LoggingLevel.ERROR, "failure in vv rounding route");



         //from("timer://test?delay=60s&period=300s").process(new TestProcessor(745746)).to("{{vv_rndng_rules_queue}}");

    }
}
