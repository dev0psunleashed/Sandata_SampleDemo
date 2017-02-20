package com.sandata.lab.rest.am.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.am.app.AppContext;
import com.sandata.lab.rest.am.impl.AdminRestDataService;
import com.sandata.lab.rest.am.impl.ReportRestDataService;
import com.sandata.lab.rest.am.impl.RestDataService;
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
            AdminRestDataService adminRestDataService = (AdminRestDataService) context.getRegistry().lookupByName("adminRestDataService");
            ReportRestDataService reportRestDataService = (ReportRestDataService) context.getRegistry().lookupByName("reportRestDataService");

            if (httpMethod.equals("GET") && (operationName.equals("PKG_AM_findBeHcpLkup_BusinessEntityHealthcareProfessionalLookup"))) {

                restDataService.findBeHcpLkup(exchange);

            } else if (httpMethod.equals("GET") && (operationName.equals("PKG_AM_getBeStaffNoteTypLkupByBEID_BusinessEntityStaffNoteTypeLookup"))) {

                restDataService.getBeStaffNoteTypLkupByBEID(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getBeChangeReasonLkupByModuleAndStatus_BusinessEntityChangeReasonLookup")) {

                restDataService.getBeChangeReasonLkupByModuleAndStatus(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getApplicationModuleLookup_ApplicationModuleLookup")) {

                restDataService.getApplicationModuleLookup(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_LOOKUP_getPayerBillingCodeLkupByPayerID_PayerBillingCodeLookup")) {

                restDataService.getPayerBillingCodeByBEAndPayerID(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getBeChangeReasonLkupStatusByModule_BusinessEntityChangeReasonLookup")) {

                restDataService.getBeChangeReasonLkupStatusByModule(exchange);

            } else if (httpMethod.equals("GET") && (operationName.equals("PKG_AM_getBePtNoteTypLkupByBEID_BusinessEntityPatientNoteTypeLookup"))) {

                restDataService.getBePtNoteTypLkupByBEID(exchange);

            } else if (httpMethod.equals("GET") && (operationName.equals("PKG_APP_getAppTenantKeyConfForName_ApplicationTenantKeyConfiguration"))) {

                restDataService.getAppTenantKeyConfForName(exchange);

            } else if (httpMethod.equals("POST") && (operationName.equals("PKG_APP_insertAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration"))) {

                restDataService.insertAppTenantKeyConfForBEID(exchange);

            } else if (httpMethod.equals("PUT") && (operationName.equals("PKG_APP_updateAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration"))) {

                restDataService.updateAppTenantKeyConfForBEID(exchange);

            } else if (httpMethod.equals("DELETE") && (operationName.equals("PKG_APP_deleteAppTenantKeyConfForBEID_ApplicationTenantKeyConfiguration"))) {

                restDataService.deleteAppTenantKeyConfForBEID(exchange);

            } else if (httpMethod.equals("GET") && (operationName.contains("PKG_APP_generateStaffId") || operationName.contains("PKG_APP_generatePatientId"))) {
                //generated staffID/PatientID then return
                restDataService.getGeneratedStaffOrPatientID(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getBeStaffTrngLkup_") //Eliminate for the case of PKG_AM_getBeStaffTrngCtgyLkup_BusinessEntityStaffTrainingCategoryLookup
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getBeStaffTrngWithPaginationSortAndOption(exchange);

                // Begin GEOR-5267
            } else if (httpMethod.equals("GET")
                    && (
                    operationName.contains("_getBeReligionLst_") || operationName.contains("_getBeSftyMeasureLkup_")
                            || operationName.contains("_getBeLangLst_") || operationName.contains("_getBeRaceEthnicityLst_")
                            || operationName.contains("_getBeRateTypLkup_") || operationName.contains("_getBeMedDosageFreqLkup_")
                            || operationName.contains("_getBeSkillLkup_") || operationName.contains("_getBeEmpltClsLkup_")
                            || operationName.contains("_getBeEvacLvlLkup_") || operationName.contains("_getBeEmpltStatusTypLkup_")
                            || operationName.contains("_getBeStaffWrkPrefLkup_") || operationName.contains("_getBeRfrlTypLkup_")
                            || operationName.contains("_getBePtPrioLvlLkup_") || operationName.contains("_getBeNutrRqmtLkup_")
                            || operationName.contains("_getBeBillingCodeLkup_")

                            // GEOR-4909
                            || operationName.contains("_getBeStaffTrngCtgyLkup_") || operationName.contains("_getBeCompCtgyLkup_")

                            || operationName.contains("_getBePtRqmtLkup_")
            )
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getBeObjectsWithSortOption(exchange);
                // End GEOR-5267

                // GEOR-6616
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_checkComplianceIsUsedAsRequisiteForOthers_")) {
                restDataService.checkComplianceIsUsedAsRequisiteForOthers(exchange);

                // GEOR-6684
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_checkComplianceUniqueName_")) {
                restDataService.checkComplianceUniqueName(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getBeCompLkup_")) {
                restDataService.getBEComplianceLookup(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getBeCompLkupForName_")) {
                restDataService.getBeCompLkupWithPaginationSortAndOption(exchange);
            } else if (httpMethod.equals("GET") && (operationName.contains("_getStaffPosition"))) {
                restDataService.getStaffPosition(exchange);
            } else if (httpMethod.equals("GET") && (operationName.contains("_getBEStaffTrngLocations"))) {

                restDataService.getStaffTrainingLocationByBE(exchange);
            } else if (operationName.contains("_getBEStaffTrngClassesByService")) {
                restDataService.getStaffTrngClassesByService(exchange);

            } else if (operationName.contains("_getBEStaffTrngClassesByCategory")) {
                restDataService.getStaffTrngClassesByCategory(exchange);

            } else if (operationName.contains("_getBECompByService")) {
                restDataService.getCompClassesByService(exchange);
            } else if (httpMethod.equals("GET") && (operationName.contains("_getBeCompLkupByCtgy_"))) {
                restDataService.getCompByCategory(exchange);
            } else if (operationName.contains("_insertAppTenantKeyConf_")) {
                restDataService.insertApplicationTenantKeyConfiguration(exchange);

            } else if (operationName.contains("_updateAppTenantKeyConf_")) {
                restDataService.updateApplicationTenantKeyConfiguration(exchange);

            } else if (operationName.contains("_getAppTenantKeyConf_")
                    && exchange.getIn().getHeader("bsn_ent_id") != null) {
                restDataService.getApplicationTenantKeyConfigurationForBsnEntIdAndKeyName(exchange);

            } else if (operationName.contains("_getAppSysKeyConfForName_")) {
                restDataService.getApplicationSystemKeyConfigurationForKeyName(exchange);

            } else if (httpMethod.equals("GET") && (operationName.contains("_getStaffWorkedHoursByRange_"))) {
                // GEOR-4420
                restDataService.getStaffWorkedHoursByRange(exchange);

            } else if (httpMethod.equals("GET") && (operationName.contains("_getPatientScheduledHoursByRange_"))) {
                // GEOR-4422
                restDataService.getPatientScheduledHoursByRange(exchange);

            } else if (httpMethod.equals("POST") && operationName.contains("_insertPayerServiceList")) {
                restDataService.insertPayerSvcLst(exchange);

            } else if (httpMethod.equals("PUT") && operationName.contains("_updatePayerServiceList")) {
                restDataService.updatePayerSvcLst(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePayerServiceList")) {
                restDataService.deletePayerSvcLst(exchange);

            } else if (httpMethod.equals("POST") && operationName.contains("_insertPayerBillingCodeLstIdList")) {
                restDataService.insertPayerBillingCodeLkupIdList(exchange);

            } else if (httpMethod.equals("POST") && operationName.contains("_insertPayerLobLstList")) {
                restDataService.insertPayerLobLstList(exchange);

            } else if (httpMethod.equals("POST") && operationName.contains("_insertPayerMdfrLstList")) {
                restDataService.insertPayerMdfrLstList(exchange);

            } else if (httpMethod.equals("POST") && operationName.contains("_insertPayerRateTypLstList")) {
                restDataService.insertPayerRateTypLstList(exchange);
            } else if (httpMethod.equals("POST") && operationName.contains("_saveStaffTrngCtgySvcList")) {
                restDataService.saveStaffTrngCtgySvcList(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getAdminStaffPK")) {
                adminRestDataService.getAdminStaffPK(exchange);

            } else if (httpMethod.equals("GET") && operationName.contains("_getAdminStaffPtPK")) {
                adminRestDataService.getAdminStaffPtPK(exchange);

            } else if (httpMethod.equals("GET") && operationName.contains("_getAdminStaffStaffXwalkPK")) {
                adminRestDataService.getAdminStaffStaffXwalkPK(exchange);

            } else if (httpMethod.equals("GET") && operationName.contains("_getAdminStaffStaffXrefPK")) {
                adminRestDataService.getAdminStaffStaffXref(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_REPORT_getReportTypes_List")) {
                reportRestDataService.getReportTypes(exchange);

            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffTrngCtgySvcList")) {
                restDataService.getStaffTrngCtgySvcList(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_REPORT_getReportNames_List")) {
                reportRestDataService.getReportNames(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_REPORT_getReportParams_List")) {
                reportRestDataService.getReportParams(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getBeLobLkupPK_BusinessEntityLineOfBusinessLookup")) {
                restDataService.getBeLobLkupPK(exchange);

            } else if (httpMethod.equals("POST") && operationName.equals("PKG_AM_insertBeLobLkup_BusinessEntityLineOfBusinessLookup")) {
                restDataService.insertBeLobLkup(exchange);

            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_AM_updateBeLobLkup_BusinessEntityLineOfBusinessLookup")) {
                restDataService.updateBeLobLkup(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_AM_deleteBeLobLkup_BusinessEntityLineOfBusinessLookup")) {
                restDataService.deleteBeLobLkup(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getBeExcpLst_BusinessEntityExceptionListExt")) {
                restDataService.getBeExcpLstSk(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getBeExcpLstPk_BusinessEntityExceptionListExt")) {
                restDataService.getBeExcpLstPk(exchange);

            } else if (httpMethod.equals("POST") && operationName.equals("PKG_AM_insertBeExcpLst_BusinessEntityExceptionListExt")) {
                restDataService.insertBeExcpLst(exchange);

            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_AM_updateBeExcpLst_BusinessEntityExceptionListExt")) {
                restDataService.updateBeExcpLst(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_AM_deleteBeExcpLst_BusinessEntityExceptionListExt")) {
                restDataService.deleteBeExcpLst(exchange);

            } else if (httpMethod.equals("POST") && operationName.equals("PKG_AM_insertContrExcpLst_ContractExceptionListExt")) {
                restDataService.insertContrExcpLst(exchange);

            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_AM_updateContrExcpLst_ContractExceptionListExt")) {
                restDataService.updateContrExcpLst(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_AM_deleteContrExcpLst_ContractExceptionListExt")) {
                restDataService.deleteContrExcpLst(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getContrExcpLst_ContractExceptionListExt")) {
                restDataService.getContrExcpLst(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_AM_getContrExcpLstForID_ContractExceptionListExt")) {
                restDataService.getContrExcpLst(exchange);

            } else if (httpMethod.equals("POST") && operationName.equals("PKG_LOOKUP_insertVvRndingRuleSettings_VisitVerificationRoundingRuleSetting")) {
                restDataService.insertOrUpdateVvRndingRuleSettings(exchange, true);

            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_LOOKUP_updateVvRndingRuleSettings_VisitVerificationRoundingRuleSetting")) {
                restDataService.insertOrUpdateVvRndingRuleSettings(exchange, false);

            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_LOOKUP_deleteVvRndingRuleSettings_VisitVerificationRoundingRuleSetting")) {
                restDataService.deleteVvRndingRuleSettings(exchange);

            } else if (httpMethod.equals("GET") && operationName.equals("PKG_LOOKUP_getVvRndingRuleSettings_VisitVerificationRoundingRuleSetting")) {
                restDataService.getVvRndingRuleSettings(exchange);

            } else if (httpMethod.equals("GET") && operationName.endsWith("_REF")) {
                restDataService.getReferenceValue(exchange);

            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getBeStaffTrngRel_")
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getBeStaffTrngRelForStaffTrngCode(exchange);
            } else if (httpMethod.equals("POST")) {
                // Handle Insert.
                restDataService.insert(exchange);
            } else if (httpMethod.equals("GET") && (operationName.equals("PKG_CONTRACT_getContrTasks_ContractTask"))) {

                restDataService.getContractTasksbyContractID(exchange);


            } else if (httpMethod.equals("PUT") && operationName.contains("_updatePayerBillingCodeLstIdList")) {
                restDataService.updatePayerBillingCodeLstIdList(exchange);

            } else if (httpMethod.equals("PUT") && operationName.contains("_updatePayerLobLstList")) {
                restDataService.updatePayerLobLstList(exchange);

            } else if (httpMethod.equals("PUT") && operationName.contains("_updatePayerMdfrLstList")) {
                restDataService.updatePayerMdfrLstList(exchange);

            } else if (httpMethod.equals("PUT") && operationName.contains("_updatePayerRateTypLstList")) {
                restDataService.updatePayerRateTypLstList(exchange);

            } else if (httpMethod.equals("PUT")) {
                // Handle Update.
                restDataService.update(exchange);
            } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePayerBillingCodeLstIdList")) {
                restDataService.deletePayerBillingCodeLkupIdList(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePayerLobLstList")) {
                restDataService.deletePayerLobLstList(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePayerMdfrLstList")) {
                restDataService.deletePayerMdfrLstList(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.contains("_deletePayerRateTypLstList")) {
                restDataService.deletePayerRateTypLstList(exchange);

            } else if (httpMethod.equals("DELETE") && operationName.contains("_deleteStaffTrngCtgySvcList")) {
                restDataService.deleteStaffTrngCtgySvcList(exchange);

            } else if (httpMethod.equals("DELETE")) {
                // Handle Delete.
                restDataService.delete(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffTrngCtgryNameCanAdd")) {
                restDataService.getStaffTrngCtgryNameCanAdd(exchange);
            } else if (httpMethod.equals("GET")) {
                // Handle Get.
                restDataService.get(exchange);
            }
        } finally {
            exchange.getIn().removeHeader("Content-Length");
        }
    }
}
