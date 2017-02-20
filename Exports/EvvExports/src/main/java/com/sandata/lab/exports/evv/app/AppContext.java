package com.sandata.lab.exports.evv.app;

import com.sandata.lab.exports.evv.utils.Log;
import org.apache.camel.CamelContext;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 7:51 AM
 */
public class AppContext {
    private static CamelContext context;
    private AppContext() {}
    public static CamelContext getContext() {
        return context;
    }
    public static CamelContext initCamelContext(final CamelContext context) {
        AppContext.context = context;
        return AppContext.context;
    }
}
