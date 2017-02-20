package com.sandata.lab.rest.payer.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payer.app.AppContext;
import com.sandata.lab.rest.payer.impl.RestDataService;
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
    
            if (operationName.contains("PKG_APP")) {
                exchange.getIn().setHeader("connectionType", ConnectionType.METADATA);
            } else {
                exchange.getIn().setHeader("connectionType", ConnectionType.COREDATA);
            }
    
            CamelContext context = AppContext.getContext();
            RestDataService restDataService = (RestDataService) context.getRegistry().lookupByName("restDataService");
    
            if (httpMethod.equals("GET")
                && (operationName.contains("_getAppTenantKeyConfContract_")
                || operationName.contains("_getAppTenantKeyConfPayer_"))) {
                restDataService.getAppTenantKeyConfForSk(exchange);
            } else if (httpMethod.equals("GET")
                && operationName.contains("_getAppTenantKeyConf_")) {
                restDataService.getAppTenantKeyConfForBePayerContAndKey(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerForID")) {
                restDataService.getPayerForID(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerContractHeadersByPayer")) {
                restDataService.getPayerContractHeadersByPayer(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getPayerHeadersByBsnEnt_PayerHdr")) {
                restDataService.getPayerHdrsByBsnEnt(exchange);
            } else if (httpMethod.equals("GET") && (operationName.contains("_getPrRateMatrixBsnEntPayerContr") ||
                operationName.contains("_getBillingRateMatrixBsnEntPayerContr") || operationName.contains("_getContrMdfrLstBsnEntPayerContr") ||
                operationName.contains("_getPayerContractBillingCodeBsnEntPayerContr") || operationName.contains("_getContrRateTypLstBsnEntPayerContr") ||
                operationName.contains("_getContrSvcLstBsnEntPayerContr") || operationName.contains("_getContrLobLstBsnEntPayerContr"))) {
                restDataService.getPayerContractListsByBsnEntPayerContr(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerByBsnEntAndPayer")) {
                restDataService.getPayerByBsnEntAndPayer(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerSvcLstBsnEntPayer")) {
                restDataService.getPayerSvcLstBsnEntPayer(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerRateTypLstBsnEntPayer")) {
                restDataService.getPayerRateTypLstBsnEntPayer(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerBillingCodeLstByBsnEntPayer")) {
                restDataService.getPayerBillingCodeLstByBsnEntPayer(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerMdfrLkupByBsnEntPayer")) {
                restDataService.getPayerMdfrLstByBsnEntPayer(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerByBsnEnt")) {
                restDataService.getPayerByBsnEnt(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerLineOfBusinessListByBsnEnt")) {
                restDataService.getPayerLobByBsnEnt(operationName, exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getPayerByPatient")) {
                restDataService.getPayerByPatient(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("PKG_CONTRACT_getContr_Contract")
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getPayerContract(exchange);
            } else if (httpMethod.equals("POST")
                && operationName.contains("_insertAppTenantKeyConf_")) {
                restDataService.createAppTenantKeyConfForPayerOrContract(exchange);
            } else if (httpMethod.equals("POST") && operationName.equals("PKG_PAYER_insertPayer_Payer")) {
                restDataService.insertPayer(exchange);
            }
            // Handle Insert
            else if (httpMethod.equals("POST")) {
                if (operationName.contains("_insertPayerContract_")) {
                    exchange.getIn().setHeader("operationName", operationName.replace("_insertPayerContract_", "_insertPayerContr_"));
                }
                restDataService.insert(exchange);
            } else if (httpMethod.equals("PUT")
                && (operationName.contains("_updateAppTenantKeyConfContract_")
                || operationName.contains("_updateAppTenantKeyConfPayer_"))) {
                restDataService.updateAppTenantKeyConf(exchange);

            } else if (httpMethod.equals("PUT") && operationName.contains("PKG_PAYER_updatePayer_Payer")) {
                restDataService.updatePayer(exchange);
            }
    
            // Handle Update
            else if (httpMethod.equals("PUT")) {
                if (operationName.contains("_updatePayerContract_")) {
                    exchange.getIn().setHeader("operationName", operationName.replace("_updatePayerContract_", "_updatePayerContr_"));
                }
    
                restDataService.update(exchange);
            } else if (httpMethod.equals("DELETE")
                && (operationName.contains("_deleteAppTenantKeyConfContract_")
                || operationName.contains("_deleteAppTenantKeyConfPayer_"))) {
                restDataService.deleteAppTenantKeyConf(exchange);
            }
            // Handle Delete
            else if (httpMethod.equals("DELETE")) {
                if (operationName.contains("_deletePayerContract_")) {
                    exchange.getIn().setHeader("operationName", operationName.replace("_deletePayerContract_", "_deletePayerContr_"));
                }
    
                restDataService.delete(exchange);
            }
            // Handle GET
            else if (httpMethod.equals("GET")) {
                if (operationName.contains("_getPayerContract_")) {
                    exchange.getIn().setHeader("operationName", operationName.replace("_getPayerContract_", "_getPayerContr_"));
                }
                restDataService.get(exchange);
            }
        } finally {
            // this should be used for displaying response message of POST/PUT endpoints once the Exceptions happen
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
