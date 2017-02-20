package com.sandata.lab.rest.lookup.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.lookup.app.AppContext;
import com.sandata.lab.rest.lookup.impl.RestDataService;
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

        if (httpMethod.equals("GET") && operationName.endsWith("_ENUM")) {
            restDataService.getEnumLookup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getStaffServiceName_ServiceName_REF")) {
            restDataService.getReferenceValueWithSorting(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.endsWith("_REF")) {
            restDataService.getReferenceValue(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.endsWith("_Dashboard")) {
            restDataService.getEnumLookup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_LOOKUP_getVisitTaskLst_VisitTaskList")) {
            restDataService.getVisitTaskLst(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getTaskLookupByBsnEntTaskList_Task")) {
            restDataService.getTaskLookup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.contains("getStaffPositionLookup")) {
            restDataService.getStaffPositionLookup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getServicesForPatientID_LIST")) {
            restDataService.getServiceLookup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_lookupFedTaxId_BusinessEntityFedTaxLookup")) {
            restDataService.lookupFedTaxId(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_lookupBiller_BusinessEntityBillerLookup")) {
            restDataService.lookupBiller(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getHcpcsLkup_HCPCSLookup")) {
            restDataService.getHcpcsLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getIcdDiagnosisLkup_ICDDiagnosisLookup")) {
            restDataService.getIcdDiagnosisLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getRaceEthnicityLkup_RaceEthnicityLookup")) {
            restDataService.getRaceEthnicityLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getContrChangeReasonLkup_ContractChangeReasonLookup")) {
            restDataService.getContrChangeReasonLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getPayerBillingCodeLkup_PayerBillingCodeLookup")) {
            restDataService.getPayerBillingCodeLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getVvRndingRuleLkup_VisitVerificationRoundingRuleLookup")) {
            restDataService.getVvRndingRuleLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getMedLkup_MedicationLookup")) {
            restDataService.getMedLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getTransportModeLkup_TransportationModeLookup")) {
            restDataService.getTransportModeLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getLangLkup_LanguageLookup")) {
            restDataService.getLangLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getCountyLkup_CountyLookup")) {
            restDataService.getCountyLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getPstlCodeLkup_PostalCodeLookup")) {
            restDataService.getPstlCodeLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getGenderTypLkup_GenderTypeLookup")) {
            restDataService.getGenderTypLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getAddrTypLkup_AddressTypeLookup")) {
            restDataService.getAddrTypLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getPhoneTypLkup_PhoneTypeLookup")) {
            restDataService.getPhoneTypLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getMrtlStatusLkup_MaritalStatusLookup")) {
            restDataService.getMrtlStatusLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getContMthdLkup_ContactMethodLookup")) {
            restDataService.getContMthdLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getCountySubdivLkup_CountySubdivisionLookup")) {
            restDataService.getCountySubdivLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getExcpLkup_ExceptionLookup")) {
            restDataService.getExcpLkup(exchange);
        }
        else if (httpMethod.equals("GET") && operationName.equals("PKG_getStateLkup_StateLookup")) {
            restDataService.getStateLookup(exchange);
        }
        else if (httpMethod.equals("GET")) {
            restDataService.get(exchange);
        }
    }
}
