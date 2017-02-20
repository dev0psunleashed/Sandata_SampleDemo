package com.sandata.lab.rest.task.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public interface DataService {

    long insert(Exchange exchange) throws SandataRuntimeException;
    long update(Exchange exchange) throws SandataRuntimeException;
    long delete(Exchange exchange) throws SandataRuntimeException;
    void get(Exchange exchange) throws SandataRuntimeException;
}
