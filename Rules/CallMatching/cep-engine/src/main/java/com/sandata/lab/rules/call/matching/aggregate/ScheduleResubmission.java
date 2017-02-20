package com.sandata.lab.rules.call.matching.aggregate;

import com.sandata.lab.rules.call.matching.exceptions.CepException;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by tom.dornseif on 12/23/2015.
 */
public class ScheduleResubmission {
    Logger logger = LoggerFactory.getLogger(ScheduleResubmission.class);

    @Handler
    public void processMatches(Exchange exchange) throws CepException {
        Message in = exchange.getIn();
        if(in.getBody() instanceof ArrayList) {

        }
    }

}
