package com.sandata.lab.rules.callcreator.app;

import org.apache.camel.CamelContext;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 7/5/2016
 * Time: 12:10 PM
 */
public class AppContext {
    public static final int OFFSET_PAST = -24;
    public static final int OFFSET_FUTURE = 24;
    private static CamelContext camelContext;
    private AppContext() {}

    public static CamelContext getContext() { return camelContext;}

    public static CamelContext InitCamelContext(CamelContext context) {
        camelContext = context;
        return camelContext;
    }

}
