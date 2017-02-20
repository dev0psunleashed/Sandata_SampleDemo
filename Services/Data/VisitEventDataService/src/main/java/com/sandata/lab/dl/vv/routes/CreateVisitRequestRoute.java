package com.sandata.lab.dl.vv.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.visit.Visit;
import com.sandata.lab.dl.vv.processors.CheckForDuplicatesAndNulls;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.processor.idempotent.MemoryIdempotentRepository;

/**
 * Handles request to create visits;
 * <p/>
 *
 * @author David Rutgos
 */
public class CreateVisitRequestRoute extends AbstractRouteBuilder {

    private static final String CREATE_VISIT_AUTH_REQUEST = "activemq:queue:CREATE_VISIT_AUTH_REQUEST";
    @Override
    protected void buildRoute() {
        CheckForDuplicatesAndNulls checkForDuplicatesAndNulls = new CheckForDuplicatesAndNulls();

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_REQUEST.toString())
                .routeId(Visit.EVENT.CREATE_VISIT_REQUEST.toString())
                .process(checkForDuplicatesAndNulls)
                .filter(header("NO_VISIT_EVENTS").isEqualTo("FALSE"))
                    .idempotentConsumer(header("DUPLICATE_ID")).messageIdRepository( MemoryIdempotentRepository.memoryIdempotentRepository()).skipDuplicate(false)
                    .filter(exchangeProperty(Exchange.DUPLICATE_MESSAGE).isEqualTo(true))
                        .log("duplicate ${header.DUPLICATE_ID} ")
                        .to("mock:duplicate")
                        .stop()
                        .end()
                .end()
                .beanRef("visitEventRepository", "createVisitRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_RESPONSE.toString())
                .log("CreateVisitRequest: createVisitRequest: Complete!");

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_EVENT_REQUEST.toString())
                .routeId(Visit.EVENT.CREATE_VISIT_EVENT_REQUEST.toString())
                .beanRef("visitEventRepository", "createVisitEventRequest")
                .to(Messaging.Names.COMPONENT_TYPE_QUEUE + Visit.EVENT.CREATE_VISIT_EVENT_RESPONSE.toString())
                .log("CreateVisitRequest: createVisitRequest: Complete!");

        from(CREATE_VISIT_AUTH_REQUEST).routeId("VISIT_EVENT_DATA_SERVICE_CREATE_VISIT_AUTH_REQUEST")
                .beanRef("visitEventRepository", "createVisitAuthRequest")
                .log(LoggingLevel.INFO, "${body}");

    }


}
