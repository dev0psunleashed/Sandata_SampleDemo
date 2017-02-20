package com.sandata.lab.rules.call.matching.service.app;

import org.apache.camel.CamelContext;

/**
 * <p>Application context for initializing camel context.</p>
 *
 * @author jasonscott
 */
public class AppContext {

    /**
     * The application camel context.
     **/
    private static CamelContext context;

    /**
     * Utility classes should not have a public or default constructor.
     */
    private AppContext() {
    }

    /**
     * Returns the application camel context.
     **/
    public static CamelContext getContext() {
        return context;
    }

    /**
     * Returns initialized application context.
     *
     * @param context Specified context to be initialized
     * @return CamelContext
     */
    public static CamelContext initCamelContext(final CamelContext context) {
        AppContext.context = context;
        return AppContext.context;
    }
}
