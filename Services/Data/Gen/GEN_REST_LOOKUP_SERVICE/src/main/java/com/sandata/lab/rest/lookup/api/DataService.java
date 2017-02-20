package com.sandata.lab.rest.lookup.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */

public interface DataService {

    void get(Exchange exchange) throws SandataRuntimeException;
    void getEnumLookup(Exchange exchange) throws SandataRuntimeException;
    void getDashboardLookup(Exchange exchange) throws SandataRuntimeException;
}
