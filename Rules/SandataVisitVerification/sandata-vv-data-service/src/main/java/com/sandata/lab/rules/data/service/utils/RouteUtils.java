package com.sandata.lab.rules.data.service.utils;

import com.sandata.lab.common.utils.messaging.Messaging;
import com.sandata.lab.rules.vv.log.utils.App;

/**
 * Created by thanhxle on 10/28/2016.
 */
public class RouteUtils {

    private RouteUtils () {

    }

    public static String getVisitAuthEndpoint () {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.CREATE_VISIT_AUTH_ROUTE.toString();
    }

    public static String getVisitExcpCalAggregateEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.VISIT_EXCEPTION_ENTRY_ROUTE.toString();
    }

    public static String getVisitTaskExcpEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.VISIT_TASK_EXCEPTION_ROUTE.toString();
    }

    public static String getGpsExcpEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.VISIT_GPS_EXCEPTION_ROUTE.toString();
    }

    public static String getProcessedCallsEndpoint() {
        return Messaging.Names.COMPONENT_TYPE_QUEUE.toString()
                + App.ID.PROCESSED_CALL_ROUTE.toString();
    }
}
