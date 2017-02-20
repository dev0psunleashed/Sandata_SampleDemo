package com.sandata.lab.rest.bsn_ent.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.bsn_ent.app.AppContext;
import com.sandata.lab.rest.bsn_ent.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {
        try {
            String httpMethod = (String) exchange.getIn().getHeader("CamelHttpMethod");
            String operationName = (String) exchange.getIn().getHeader("operationName");
    
            CamelContext context = AppContext.getContext();
            RestDataService restDataService = (RestDataService) context.getRegistry().lookupByName("restDataService");
    
            if (httpMethod.equals("POST") && operationName.contains("_insertBeRateExchange")) {
                restDataService.insertBeRateExchange(exchange);
    
            } else if (httpMethod.equals("PUT") && operationName.contains("_updateBeRateExchange")) {
                restDataService.updateBeRateExchange(exchange);
    
            } else if (httpMethod.equals("DELETE") && operationName.contains("_deleteBeRateExchange")) {
                restDataService.deleteBeRateExchange(exchange);
    
            } else if (httpMethod.equals("GET") && operationName.contains("_getBeRateExchange")) {
                restDataService.getBeRateExchange(exchange);
    
            } else if (httpMethod.equals("GET") && operationName.contains("_getBeLocation")) {
                restDataService.getBsnEntLocation(exchange);
    
            } else if (httpMethod.equals("GET") && operationName.contains("_getBeRateForBsnEntID")) {
                restDataService.getBeRateForBsnEntID(exchange);
    
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_BSN_getBeRel_BusinessEntityRelationshipForPK")) {
                restDataService.getBusinessEntityRelationshipForPK(exchange);
    
            } else if (operationName.equals("PKG_BSN_getBeRateTypPrCodeXwalk_BusinessEntityRateTypePayrollCodeCrosswalk")) {
                restDataService.get(exchange);
    
            } else if (operationName.equals("PKG_BSN_getBeStaffRole_BusinessEntityStaffRole")) {
                restDataService.get(exchange);
    
            } else if (operationName.equals("PKG_BSN_getBePayer_BusinessEntityPayer")) {
                restDataService.get(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.equals("PKG_BSN_getBeCompRelDetPK_BusinessEntityComplianceRelationshipDetail")) {
                restDataService.getBeCompRelDetPK(exchange);
            }

            //dmr--Don't like this Impl, can cause bugs
            //TODO: Fix this
            else if ((httpMethod.equals("GET")
                && operationName.contains("getBe")
                && exchange.getIn().getHeader("bsn_ent_id") != null)
                    //dmr--
                    && !operationName.equals("PKG_BSN_getBeCompRel_BusinessEntityComplianceRelationship")) {
                restDataService.getBusinessEntityForPK(exchange);
            }
            // Handle Insert
            else if (httpMethod.equals("POST")) {
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
        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
