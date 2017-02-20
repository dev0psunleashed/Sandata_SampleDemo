package com.sandata.lab.dl.vv.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;

/**
 * Date: 11/12/15
 * Time: 2:08 PM
 */

public class ScheduleEventRequestRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.SCHEDULE_EVENT_REQUEST.toString())
                .routeId(Visit.EVENT.SCHEDULE_EVENT_REQUEST.toString())
                .beanRef("visitEventRepository", "getScheduleEventsRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.SCHEDULE_EVENT_RESPONSE.toString())
                .log("ScheduleEventRequestRoute: getScheduleEventsRequest: Complete!");


        from(Messaging.Names.COMPONENT_TYPE_QUEUE + "ScheduledVisitExceptionsCheckRequest")
                .routeId("ScheduledVisitExceptionsCheckRequest")
                .beanRef("visitEventRepository", "doScheduledVisitExceptionsCheck")
                .multicast()
                .to("activemq:queue:ScheduledVisitExceptionsCheckResponse", "activemq:queue:continueScheduledVisitExceptionCleaning");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + "VisitExceptionsCheckRequest?concurrentConsumers=1")
                .routeId("VisitExceptionsCheckRequest")
                .beanRef("visitEventRepository", "doVisitExceptionsCheck")
                .multicast()
                .to("activemq:queue:VisitExceptionsCheckResponse", "activemq:queue:startCleaner");


        //SHORT TIME


        from(Messaging.Names.COMPONENT_TYPE_QUEUE + "shortTimeScheduledVisitExceptionsCheckRequest")
                .routeId("shortTimeScheduledVisitExceptionsCheckRequest")
                .beanRef("visitEventRepository", "doScheduledVisitExceptionsCheck")
                .to("activemq:queue:ScheduledVisitExceptionsCheckResponse");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + "shortTimeVisitExceptionsCheckRequest?concurrentConsumers=1")
                .routeId("shortTimeVisitExceptionsCheckRequest")
                .beanRef("visitEventRepository", "doVisitExceptionsCheck")
                .multicast()
                .to("activemq:queue:VisitExceptionsCheckResponse", "activemq:queue:callNextShortTimeProcess");



    }
}
