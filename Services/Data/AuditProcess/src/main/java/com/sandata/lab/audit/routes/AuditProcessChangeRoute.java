package com.sandata.lab.audit.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.audit.Audit;

/**
 * Route processes any changes that to data points.
 *
 * @author David Rutgos
 */
public class AuditProcessChangeRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Audit.EVENT.AUDIT_CHANGED_EVENT.toString())
                .routeId(Audit.EVENT.AUDIT_CHANGED_EVENT.toString())
                .threads().executorServiceRef("auditProcessThreadPool")
                .beanRef("processDataPoints", "handler")
                .log("AuditProcessChangeRoute: Complete!");
    }
}
