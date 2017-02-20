/**
 * 
 */
package com.sandata.services.mobilehealth.processors;

import java.util.List;

import com.sandata.lab.data.model.dl.model.MedicalExaminationItemCrosswalk;
import com.sandata.lab.data.model.dl.model.StaffMedicalHistory;

/**
 * @author huyvo
 *
 */
public abstract class BaseMobileHealthProcessor {
    /**
     * may different interface item names will associate with one medical exam item name, find an matching one
     * @param medclExamItemXwalks
     * @param listMedclHistory
     * @param interfaceItemNames
     * @return
     */
    protected StaffMedicalHistory findPosibleStaffMedicalHistoryByInterfaceItemNames(
            List<MedicalExaminationItemCrosswalk> medclExamItemXwalks,
            List<StaffMedicalHistory> listMedclHistory,
            List<String> interfaceItemNames) {
        
        if (interfaceItemNames != null) {
            StaffMedicalHistory result = null;
            for (String interfaceItemName : interfaceItemNames) {
                result = getSpecifiedMedicalHistory(interfaceItemName, medclExamItemXwalks, listMedclHistory);
                if (result != null) {
                    return result;
                }
            }
        }
        
        return null;
    }
    
    /**
     * find Medical History with not null and latest date and specified Exam Item name
     * @param interfaceItemName         Specified and must be not null
     * @param medclExamItemXwalks       List of Medical Exam Item Crosswalk to decode the Medical Exam Name
     * @param listMedclHistory          List Medical History
     * @return
     */
    protected StaffMedicalHistory getSpecifiedMedicalHistory(String interfaceItemName,
            List<MedicalExaminationItemCrosswalk> medclExamItemXwalks,
            List<StaffMedicalHistory> listMedclHistory) {
        
        
        if (medclExamItemXwalks != null) {
            MedicalExaminationItemCrosswalk foundMeclExamItemXwalk = null;
            
            for (MedicalExaminationItemCrosswalk xwalk : medclExamItemXwalks) {
                if (interfaceItemName.equalsIgnoreCase(xwalk.getInterfaceItemName())) {
                    foundMeclExamItemXwalk = xwalk;
                    break;
                }
            }
            
            if (foundMeclExamItemXwalk != null) {
                StaffMedicalHistory latestStaffMedicalHistory = null;
                
                // get latest one
                for (StaffMedicalHistory staffMeclHis : listMedclHistory) {
                    if (foundMeclExamItemXwalk.getBusinessEntityMedicalExaminationItemName() != null
                            && foundMeclExamItemXwalk.getBusinessEntityMedicalExaminationItemName().equals(staffMeclHis.getMedicalExaminationItemName())
                            && staffMeclHis.getStaffMedicalItemDate() != null) {
                        if (latestStaffMedicalHistory == null
                                || latestStaffMedicalHistory.getStaffMedicalItemDate().compareTo(staffMeclHis.getStaffMedicalItemDate()) < 0) {
                            latestStaffMedicalHistory = staffMeclHis;
                        }
                    }
                }
                
                return latestStaffMedicalHistory;
            }
            
        }
        
        return null;
    }
}
