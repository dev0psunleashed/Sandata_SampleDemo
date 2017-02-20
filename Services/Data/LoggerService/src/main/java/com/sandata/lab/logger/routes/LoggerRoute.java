package com.sandata.lab.logger.routes;

import com.sandata.lab.common.utils.camel.AbstractRouteBuilder;
import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.logger.Logger;

/**
 * Intercepts logging messages.
 * <p/>
 *
 * @author David Rutgos
 */
public class LoggerRoute extends AbstractRouteBuilder {

    @Override
    protected void buildRoute() {

        from(Messaging.Names.COMPONENT_TYPE_QUEUE + Logger.EVENT.LOGGER_PROCESS_MESSAGE.toString())
                .routeId(Logger.EVENT.LOGGER_PROCESS_MESSAGE.toString())
                .threads().executorServiceRef("loggerProcessThreadPool")
                .beanRef("loggerDataService", "logHandler");
    }
}
