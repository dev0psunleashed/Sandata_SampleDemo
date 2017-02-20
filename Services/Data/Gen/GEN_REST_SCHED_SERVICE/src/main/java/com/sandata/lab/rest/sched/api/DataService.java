package com.sandata.lab.rest.sched.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public interface DataService {

    void insert(Exchange exchange) throws SandataRuntimeException;
    void update(Exchange exchange) throws SandataRuntimeException;
    void delete(Exchange exchange) throws SandataRuntimeException;
    void get(Exchange exchange) throws SandataRuntimeException;
    void findStaff(Exchange exchange) throws SandataRuntimeException;
    void findSchedule(Exchange exchange) throws SandataRuntimeException;
    void deleteSchedule(Exchange exchange) throws SandataRuntimeException;
    void saveSchedEvents(Exchange exchange) throws SandataRuntimeException;
}
