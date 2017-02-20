package com.sandata.lab;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date: 11/27/13
 * Time: 3:15 PM
 */

public class AppContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppContext.class);
    
    private static CamelContext _context;

    public static CamelContext getContext() {
        return _context;
    }

    public static CamelContext InitCamelContext(CamelContext context) {
        LOGGER.info("[QuartzDataSource][AppContext][InitCamelContext] System props: {}", System.getProperties().toString());
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
        LOGGER.info("[QuartzDataSource][AppContext][InitCamelContext] System props after setting SERIALIZABLE_PACKAGES: {}", System.getProperties().toString());
        
        _context = context;
        return _context;
    }
}
