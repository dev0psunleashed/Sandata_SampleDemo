package com.sandata.lab.rules.dl.app;

import org.apache.camel.CamelContext;


/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 6/7/2016
 * Time: 12:29 PM
 */
public class AppContext {
    private static CamelContext camelContext;

    private AppContext() {}

    public static CamelContext getContext() { return camelContext;}

    public static CamelContext InitCamelContext(CamelContext context) {
        camelContext = context;
        return camelContext;
    }
}
