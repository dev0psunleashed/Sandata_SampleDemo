package com.sandata.lab.eligibility.utils;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.data.model.constants.eligibility.Eligibility;

/**
 * Utilities for Camel routes
 */
public final class RouteUtils {
    private RouteUtils() {
        // no operation
    }

    /**
     * Gets request handler endpoint for each PT_PAYER_SK
     * 
     * @return Endpoint = activemq:queue:ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST_HANDLER
     */
    public static String getInquiryRequestHandlerEndpoint() {
        return new StringBuilder()
                .append(Messaging.Names.COMPONENT_TYPE_QUEUE).append(Eligibility.EVENT.ELIGIBILITY_PROCESS_SENDS_INQUIRY_270_REQUEST)
                .append("_HANDLER")
                .toString();
    }
}
