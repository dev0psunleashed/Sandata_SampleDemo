package com.sandata.lab.rest.payer.api;

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
    void getPayerByPatient(Exchange exchange) throws SandataRuntimeException;
    void getPayerByBsnEnt(Exchange exchange) throws SandataRuntimeException;
}
