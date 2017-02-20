package com.sandata.lab.logger.api;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

public interface DataService {
    void insert(Exchange exchange) throws SandataRuntimeException;
    void update(Exchange exchange) throws SandataRuntimeException;
    void delete(Exchange exchange) throws SandataRuntimeException;
    void get(Exchange exchange) throws SandataRuntimeException;
}
