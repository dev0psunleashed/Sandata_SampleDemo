package com.sandata.lab.kie.server.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.apache.camel.Exchange;

/**
 * Created by khangle on 01/19/2017.
 */
public interface KieServerHandler {

    public void executeRules(Exchange exchange) throws SandataRuntimeException;

}
