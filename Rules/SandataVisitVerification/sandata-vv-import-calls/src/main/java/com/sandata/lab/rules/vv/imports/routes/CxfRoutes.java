/**
 * 
 */
package com.sandata.lab.rules.vv.imports.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.rules.vv.imports.utils.constants.Messaging;

/**
 * 
 * @author thanhxle
 * Receiving request info from REST client for dummy call.
 *
 */
public class CxfRoutes extends AbstractRouteBuilder{

    private final static String EVV_CALL_IMPORT_DUMMY_ROUTE = "EVV_CALL_IMPORT_DUMMY_ROUTE";
    @Override
    protected final void buildRoute() {
        from("cxfrs://bean://rsServer?bindingStyle=SimpleConsumer")
                .routeId(EVV_CALL_IMPORT_DUMMY_ROUTE)
                .threads().executorServiceRef("importCallContextThreadPool")
                .removeHeader("Content-Length")
                .recipientList(simple(Messaging.Names.COMPONENT_TYPE_SEDA + "${header.operationName}?concurrentConsumers=10"))
                .beanRef("formatTransformer", "toResponse");
        
    }
    
}
