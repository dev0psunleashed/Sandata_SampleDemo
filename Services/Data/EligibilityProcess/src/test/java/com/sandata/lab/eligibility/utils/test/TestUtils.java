package com.sandata.lab.eligibility.utils.test;

import org.apache.camel.Exchange;

public final class TestUtils {

    private TestUtils() {
        // no op
    }

    public static boolean isExchangeStopped(final Exchange exchange) {
        Boolean isStopped = exchange.getIn().getHeader(Exchange.ROUTE_STOP, Boolean.class);
        return isStopped != null && isStopped.booleanValue();
    }
}
