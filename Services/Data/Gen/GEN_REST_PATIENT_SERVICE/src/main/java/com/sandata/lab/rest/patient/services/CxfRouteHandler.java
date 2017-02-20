package com.sandata.lab.rest.patient.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.patient.app.AppContext;
import com.sandata.lab.rest.patient.impl.OSGIDataService;
import com.sandata.lab.rest.patient.impl.RestDataService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

/**
 * Date: 9/2/15
 * Time: 3:03 AM
 */

public class CxfRouteHandler {

    public void handler(Exchange exchange) throws SandataRuntimeException {

        try {
            String httpMethod = (String)exchange.getIn().getHeader("CamelHttpMethod");
            String operationName = (String)exchange.getIn().getHeader("operationName");
    
            CamelContext context = AppContext.getContext();
            RestDataService restDataService = (RestDataService)context.getRegistry().lookupByName("restDataService");
    
            OSGIDataService osgiDataService = (OSGIDataService) context.getRegistry().lookupByName("osgiDataService");
    
    
            if (httpMethod.equals("GET") && operationName.contains("_findPatientsForStaffAgency")) {
                restDataService.findPatientsForStaffAgency(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("PKG_PATIENT_getPatientPayerFull_PatientPayer")) {
                restDataService.getPatientPayerFull(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPdContactDetlForID")) {
                restDataService.getPdContactDetlForID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientForID")) {
                restDataService.getPatientForID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_patientExists")) {
                osgiDataService.patientExists(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientContactDetails")) {
                restDataService.getPatientContactDetails(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientContactEmailID")) {
                restDataService.getPatientContactEmailID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientContactPhoneID")) {
                restDataService.getPatientContactPhoneID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientCntctAddrForID")) {
                restDataService.getPatientCntctAddrForID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientAllergyForID")) {
                restDataService.getPatientAllergyForID(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_validatePatientId")) {
                restDataService.validatePatientId(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientNoteForID")) {
                restDataService.getPatientNoteForID(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.contains("_insertPatientDmeAndSupplyList")) {
                restDataService.insertPatientDmeAndSupplyList(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.contains("_updatePatientDmeAndSupplyList")) {
                restDataService.updatePatientDmeAndSupplyList(exchange);
            }
            else if (httpMethod.equals("DELETE") && operationName.contains("_deletePatientDmeAndSupplyList")) {
                restDataService.deletePatientDmeAndSupplyList(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.contains("_insertPatientAllergyList")) {
            	restDataService.insertPatientAllergyList(exchange);
            }
            else if (httpMethod.equals("DELETE") && operationName.contains("_deletePatientAllergyList")) {
            	restDataService.deletePatientAllergyList(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.contains("_updatePatientAllergyList")) {
            	restDataService.updatePatientAllergyList(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_findPatients")) {
                restDataService.findPatients(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_distinctFirstNames")) {
                restDataService.distinctFirstNames(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_distinctLastNames")) {
                restDataService.distinctLastNames(exchange);
            }
            // Handle Update PatientNutrRqmnt
            else if (httpMethod.equals("PUT") && operationName.contains("_UpdatePatientNutrRqmt")) {
            	restDataService.updatePatientNutrRqmt(exchange);
            }
            // Handle add new PatientNutrRqmnt
            else if (httpMethod.equals("POST") && operationName.contains("_AddNewPatientNutrRqmt")) {
            	restDataService.insertPatientNutrRqmntList(exchange);
            }
            else if (httpMethod.equals("DELETE") && operationName.contains("_DeletePatientNutrRqmt")) {
            	restDataService.deletePatientNutrRqmt(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.contains("_updatePatientPayerEligibility")) {
                restDataService.updatePatientPayerEligibility(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientPayerEligibility")) {
                restDataService.getPatientPayerEligibility(exchange);
    
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getPatientCoordinators")) {
                restDataService.getPatientCoordinators(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getAddressByCityAndState_")) {
                restDataService.getAddressByCityAndState(exchange);
            }
            else if (httpMethod.equals("GET") && operationName.contains("_getCityByState_")) {
                restDataService.getCityByState(exchange);
            }
            else if (httpMethod.equals("POST") && operationName.equals("PKG_PATIENT_insertPt_PatientExt")) {
                restDataService.insertPatient(exchange);
            }
            else if (httpMethod.equals("PUT") && operationName.equals("PKG_HIST_updatePatient_PatientExt")) {
                restDataService.updatePatient(exchange);
    
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_PATIENT_getPatientDiagnosis_PatientDiagnosis")) {
                restDataService.getPatientDiagnosis(exchange);
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
