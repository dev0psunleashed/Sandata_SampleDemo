package com.sandata.lab.dl.vv.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public interface VisitEventDataService {

    void getScheduleEvents(Exchange exchange) throws SandataRuntimeException;
    void getScheduleEventsRequest(Exchange exchange) throws SandataRuntimeException;
    void getPatientStaffRequest(Exchange exchange) throws SandataRuntimeException;
    void createVisitRequest(Exchange exchange) throws SandataRuntimeException;
    void createVisitEventRequest(Exchange exchange) throws SandataRuntimeException;
    void createVisitExceptionRequest(Exchange exchange) throws SandataRuntimeException;
    void clearExceptions(Exchange excahnge) throws SandataRuntimeException;
    void clearExceptionsArray(Exchange exchange) throws  SandataRuntimeException;
    void clearScheduledVisitisExceptionsArray(Exchange exchange) throws SandataRuntimeException;
    void createExcLookupRequest(Exchange exchange) throws SandataRuntimeException;
}
