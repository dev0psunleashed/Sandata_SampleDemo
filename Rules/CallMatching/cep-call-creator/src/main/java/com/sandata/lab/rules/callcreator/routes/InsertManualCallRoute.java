package com.sandata.lab.rules.callcreator.routes;

import com.sandata.lab.rules.callcreator.processors.DeserializeJSONProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DescriptiveResource;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 8/20/2016
 * Time: 9:04 AM
 */
public class InsertManualCallRoute extends RouteBuilder {

    DeserializeJSONProcessor deserializeJSONProcessor = new DeserializeJSONProcessor();
    @Override
    public void configure() throws Exception {
        if(deserializeJSONProcessor == null) {
            deserializeJSONProcessor = new DeserializeJSONProcessor();
        }

        from("file://target/CallMatching/importCalls/.insert?move=.done")
                .routeId("manual_callcreator_importcall")
                .log(LoggingLevel.INFO, "import calls from dummy test")
                .process(deserializeJSONProcessor)
                .to("activemq:queue:PROCESS_CALLS");


    }


}
