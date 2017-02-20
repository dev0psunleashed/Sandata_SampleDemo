package com.sandata.services.mobilehealth.routes;


import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import com.sandata.services.mobilehealth.utils.LoggingUtils;


/**
 * <p>AbstractRouteBuilder.java</p>
 * <p/>
 * <p><t>Generic route builder.</t></p>
 *
 * @author jasonscott
 */

public abstract class BaseMobileHealthRoute extends RouteBuilder {

    @Override
    public final void configure() throws Exception {

        onException(Exception.class)
                .maximumRedeliveries(0)
                .handled(true)
                .log(LoggingLevel.ERROR, 
                        LoggingUtils.getErrorLogMessageForRoute("Error message: ${exception.message} - stacktrace: ${exception.stacktrace}"));

        buildRoute();
    }

    /**
     *
     */
    protected abstract void buildRoute();
}
