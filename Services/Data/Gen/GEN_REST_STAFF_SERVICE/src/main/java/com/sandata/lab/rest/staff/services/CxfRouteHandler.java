package com.sandata.lab.rest.staff.services;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.staff.app.AppContext;
import com.sandata.lab.rest.staff.impl.OSGIDataService;
import com.sandata.lab.rest.staff.impl.RestDataService;
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
    
            OSGIDataService osgiDataService = (OSGIDataService) context.getRegistry().lookupByName("osgiDataService");
    
            if (httpMethod.equals("POST") && operationName.equals("PKG_STAF_validateExistStaffInformation_StaffExt")) {
                restDataService.validateExistStaffInformation(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffForSK_Staff")) {
                restDataService.getStaffForSK(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffForPK_Staff")) {
                restDataService.getStaffForPK(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getStaffTrngLoc_")
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getStaffTrngLocationForStaffTrngCode(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getStaffTrngClsEvnt_")
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getStaffTrngClsEvntForStaffTrngLocName(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_findStaffForTrainingCategory_")) {
                restDataService.findStaffForTrainingCategory(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("_getStaffTrngClsEvntEnrol_")
                    && exchange.getIn().getHeader("sequence_key") == null) {
                restDataService.getStaffTrngClsEvntEnrolForClsEvnt(exchange);
            } else if (httpMethod.equals("POST")
                    && operationName.contains("_getStaffTrngClsEvntEnrolList_")) {
                restDataService.getStaffTrngClsEvntEnrolList(exchange);
            } else if (httpMethod.equals("POST")
                    && operationName.contains("_insertStaffTrngClsEvntEnrolListConfirm_")) {
                restDataService.insertStaffTrngClsEvntEnrolListConfirm(exchange);
            } else if (httpMethod.equals("POST")
                    && operationName.contains("PKG_STAFF_insertStaffSpecificAvail_StaffAvailabilitySpecific")) {
                // SAN-914
                restDataService.insertStaffSpecificAvail(exchange);
            } else if (httpMethod.equals("PUT")
                    && operationName.contains("PKG_STAFF_updateStaffSpecificAvail_StaffAvailabilitySpecific")) {
                // SAN-914
                restDataService.updateStaffSpecificAvail(exchange);
                
            } else if (httpMethod.equals("GET")
                    && operationName.contains("PKG_STAFF_getStaffSpecificAvail_StaffAvailabilitySpecific")) {
                // SAN-914
                restDataService.getStaffSpecificAvail(exchange);
            } else if (httpMethod.equals("GET")
                    && operationName.contains("PKG_STAFF_getStaffAvailForID_StaffAvailability_STAFF_AVAIL")) {
                //SAN-914
                restDataService.getStaffGeneralAvail(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffCompWithSort")) {
                restDataService.getStaffCompWithSort(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffHistoryWithSort_Staff")) {
                restDataService.getStaffHistoryWithSort(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffHiringHistoryWithSort_StaffHiringHistory")) {
                restDataService.getStaffHiringHistoryWithSort(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffEmploymentStatusHistoryWithSort_Staff")) {
                restDataService.getStaffEmploymentStatusHistoryWithSort(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffTrngClsEvntByLocation")) {
                restDataService.getStaffTrngClsEvntByLocation(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_validateStaffId")) {
                restDataService.validateStaffId(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getAdminStaffRel")) {
                restDataService.getStaffAdmins(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffNurses")) {
                restDataService.getStaffNurses(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffCoordinators")) {
                restDataService.getStaffCoordinators(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffManagers")) {
                restDataService.getStaffManagers(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_getStaffRelationship")) {
                restDataService.getStaffRelationship(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_distinctFirstNames")) {
                restDataService.distinctFirstNames(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_distinctLastNames")) {
                restDataService.distinctLastNames(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_findStaff")) {
                restDataService.findStaff(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_excludedPatients")) {
                restDataService.excludedPatients(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_excludedStaff")) {
                restDataService.excludedStaff(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_validatePatientExists")) {
                restDataService.validatePatientExists(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_isPatientExcluded")) {
                restDataService.isPatientExcluded(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_isStaffExcluded")) {
                restDataService.isStaffExcluded(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_staffScheduleEvents")) {
                restDataService.staffScheduleEvents(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_staffExists")) {
                osgiDataService.staffExists(exchange);
            } else if (httpMethod.equals("GET") && operationName.contains("_findClassByStaff")) {
                osgiDataService.findClassesForStaff(exchange);
            } else if (httpMethod.equals("POST") && operationName.contains("_addStaffRate")) {
                osgiDataService.addStaffRate(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffAssocRatePK_StaffAssociatedRate")) {
                osgiDataService.getStaffAssocRatePK(exchange);
            } else if (httpMethod.equals("POST") && operationName.equals("PKG_STAFF_insertStaff_Staff")) {
                restDataService.insertStaff(exchange);
            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_HIST_updateStaff_Staff")) {
                restDataService.updateStaff(exchange);
            } else if (httpMethod.equals("POST") && operationName.equals("PKG_STAFF_insertStaffLanguageLst_StaffLanguageList")) {
                restDataService.insertStaffLanguageLst(exchange);
            } else if (httpMethod.equals("PUT") && operationName.equals("PKG_STAFF_updateStaffLanguageLst_StaffLanguageList")) {
                restDataService.updateStaffLanguageLst(exchange);
            } else if (httpMethod.equals("DELETE") && operationName.equals("PKG_STAFF_deleteStaffLanguageLst_StaffLanguageList")) {
                restDataService.deleteStaffLanguageLst(exchange);
            } else if (httpMethod.equals("PUT")
                    && operationName.contains("_updateStaffTrngClsEvntEnrolList_")) {
                restDataService.updateStaffTrngClasEvntEnrolList(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_ADMIN_getAdminStaffs_AdministrativeStaff")) {
                restDataService.getAdminStaffs(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getTrainingCategoryTotalHours_TrainingCategoryTotalHoursResult")) {
                restDataService.getTrainingCategoryTotalHours(exchange);
            } else if (httpMethod.equals("GET") && operationName.equals("PKG_STAFF_getStaffProfileChangesHistory_StaffProfileChangedLog")) {
            	restDataService.getStaffProfileChangesHistory(exchange);
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
