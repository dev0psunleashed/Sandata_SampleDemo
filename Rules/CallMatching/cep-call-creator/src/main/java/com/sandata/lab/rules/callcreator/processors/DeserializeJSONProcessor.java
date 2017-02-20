package com.sandata.lab.rules.callcreator.processors;

import com.google.gson.Gson;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/20/2016
 * Time: 11:29 AM
 */
public class DeserializeJSONProcessor implements Processor {
    private Logger callCreatorLog = LoggerFactory.getLogger("callCreatorLog");


    @Override
    public void process(Exchange exchange) throws Exception {
        String json = exchange.getIn().getBody(String.class);
        Gson gson = new Gson();
        VisitEventDNISGroup grp = gson.fromJson(json, VisitEventDNISGroup.class);

        getCallCreatorLog().info("import_call_manual_data:" +json);

        //for idempotent consumer , header DUPLICATED_ID
        String test= UUID.randomUUID().toString();

        exchange.getIn().setHeader("DUPLICATE_ID", test);

        exchange.getIn().setBody(grp);
    }

    public Logger getCallCreatorLog() {
        if(this.callCreatorLog != null ) {
            return this.callCreatorLog;
        }
        else {
            this.callCreatorLog = LoggerFactory.getLogger("callCreatorLog");
            return this.callCreatorLog;
        }
    }

    public void setCallCreatorLog(Logger callCreatorLog) {
        this.callCreatorLog = callCreatorLog;
    }
}
