package com.sandata.lab.eligibility.utils.camel;

import org.apache.camel.Exchange;

/**
 * Utilities for Camel stuff
 */
public final class CamelUtils {

    private CamelUtils() {
        // no-op
    }

    public static void stopProcessingExchange(final Exchange exchange) {
        exchange.getIn().setHeader(Exchange.ROUTE_STOP, Boolean.TRUE);
    }
}
