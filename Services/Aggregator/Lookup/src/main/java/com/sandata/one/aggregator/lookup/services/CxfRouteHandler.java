package com.sandata.one.aggregator.lookup.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.one.aggregator.lookup.app.AppContext;
import com.sandata.one.aggregator.lookup.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
        String operationName = (String)exchange.getIn().getHeader("operationName");

        CamelContext context = AppContext.getContext();
        RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");

        if (httpMethod.equals("GET") && operationName.endsWith("_REF")) {
            restDataService.getReferenceValue(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getPayerLookup_Payer")) {
            restDataService.getPayerLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getContractLookup_Contract")) {
            restDataService.getContractLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getBeExcpLst_BusinessEntityExceptionListExt")) {
            restDataService.getBeExcpLst(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getCoordinator_CoordinatorLookup")) {
            restDataService.getCoordinatorLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getPatient_PatientLookup")) {
            restDataService.getPatientLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getStaff_StaffLookup")) {
            restDataService.getStaffLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getAgency_AgencyLookup")) {
            restDataService.getAgencyLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getRoles_ApplicationRole")) {
            restDataService.getAppRoleLookup(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_getContractService_ContractServiceList")) {
            restDataService.getContractService(exchange);

        }  else if (httpMethod.equals("GET") && operationName.equals("PKG_APP_getUserPermission_ApplicationSecureGroup")) {
            restDataService.getAllPermission(exchange);

        } else if (httpMethod.equals("GET") && operationName.equals("PKG_APP_getUserPermissionByRole_ApplicationSecureGroup")) {
            restDataService.getPermissionForRole(exchange);

        } else if (httpMethod.equals("GET")) {
            restDataService.get(exchange);
        }
    }
}
