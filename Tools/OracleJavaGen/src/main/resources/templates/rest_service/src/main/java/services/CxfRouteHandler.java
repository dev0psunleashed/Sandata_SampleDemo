package __GROUP_ID__.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import __GROUP_ID__.app.AppContext;
import __GROUP_ID__.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");

        CamelContext context = AppContext.getContext();
        RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");

        // Handle Insert
        if (httpMethod.equals("POST")) {
            restDataService.insert(exchange);
        }
        // Handle Update
        else if (httpMethod.equals("PUT")) {
            restDataService.update(exchange);
        }
        // Handle Delete
        else if (httpMethod.equals("DELETE")) {
            restDataService.delete(exchange);
        }
        // Handle GET
        else if (httpMethod.equals("GET")) {
            restDataService.get(exchange);
        }
    }
}
