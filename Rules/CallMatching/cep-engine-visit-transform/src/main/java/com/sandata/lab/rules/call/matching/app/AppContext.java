package com.sandata.lab.rules.call.matching.app;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tom.dornseif on 11/16/2015.
 */
public class AppContext {



    private static CamelContext context;

    private AppContext() {}

    public static CamelContext getContext() { return context;}

    public static CamelContext initCamelContext(final CamelContext context) {

        Logger logger = LoggerFactory.getLogger("transformLog");
        logger.info("Starting Transform bundle");
        AppContext.context = context;
        return AppContext.context;
    }
}
