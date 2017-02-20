package com.sandata.services.mobilehealth.processors.imports;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.MedicalExaminationItemCrosswalk;
import com.sandata.lab.data.model.dl.model.StaffMedicalHistory;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.services.mobilehealth.data.models.imports.MobileHealthGeorgeFileImport;
import com.sandata.services.mobilehealth.processors.BaseMobileHealthProcessor;
import com.sandata.services.mobilehealth.processors.OracleDataService;
import com.sandata.services.mobilehealth.utils.CommonUtils;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.up.commons.exception.SandataRuntimeException;

import static com.sandata.services.mobilehealth.utils.InterfaceItemLookupConstants.*;

public class ProcessImportFileProcessor extends BaseMobileHealthProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImportFileProcessor.class);
    
    @PropertyInject("{{process.downloadfile.file.cc}}")
    private String century;
    @PropertyInject("{{vendor.type}}")
    private String vendorType = "VENDOR";
    @PropertyInject("{{vendor.name}}")
    private String vendorName = "MOBILE_HEALTH";
    
    private OracleDataService oracleDataService;
    
    /**
     * Sort list of MobileHealthFixedLengthModel by vendor
     * 
     * @param exchange
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sortFixedLengthModelList(final Exchange exchange) {
        Object unmarshalledResult = exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_FIXED_LENGTH_LIST.toString());
        if (unmarshalledResult instanceof MobileHealthGeorgeFileImport) {
            LOGGER.info(LoggingUtils.getLogMessageForProcessor(getClass(), "sortFixedLengthModelList", 
                    "Unmarhalled result is a instance of MobileHealthGeorgeFileImport"));
            List<MobileHealthGeorgeFileImport> list = new ArrayList<MobileHealthGeorgeFileImport>();
            list.add((MobileHealthGeorgeFileImport) unmarshalledResult);
            // update list
            exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_FIXED_LENGTH_LIST.toString(), list);
        }

        List<MobileHealthGeorgeFileImport> list = (List) exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_FIXED_LENGTH_LIST.toString());

        List<MobileHealthGeorgeFileImport> sortedList = new ArrayList<MobileHealthGeorgeFileImport>(list);
        Collections.sort(sortedList);

        exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_FIXED_LENGTH_SORTED_LIST.toString(), sortedList);
    }
    
    /**
     * 
     * 
     * @param exchange
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getUniqueVendorList(final Exchange exchange) {
        List<MobileHealthGeorgeFileImport> sortedList = (List) exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_FIXED_LENGTH_SORTED_LIST.toString());
        
        Set<String> uniqueVendors = new LinkedHashSet<String>();
        
        for (MobileHealthGeorgeFileImport model : sortedList) {
            uniqueVendors.add(model.getVendor());
        }
        
        exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_UNIQUE_VENDOR_LIST.toString(), uniqueVendors);
        
        LOGGER.info(LoggingUtils.getLogMessageForProcessor(getClass(), "getUniqueVendorList", "VendorList={}"),
                    exchange.getIn().getHeader(Messaging.Names.PROCESS_FILE_UNIQUE_VENDOR_LIST.toString()));
    }
    
    /**
     * Filtering Fixed Length Model List by Vendor
     * 
     * @param exchange
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void filterFixedLengthModelsByVendor(final Exchange exchange) {
        String vendor = exchange.getIn().getHeader(Messaging.Names.PROCESS_FILE_VENDOR_ID_HEADER.toString(), String.class);
        List<MobileHealthGeorgeFileImport> sortedList = (List) exchange.getIn().getHeader(
                Messaging.Names.PROCESS_FILE_FIXED_LENGTH_SORTED_LIST.toString());
        
        List<MobileHealthGeorgeFileImport> filteredList = new ArrayList<MobileHealthGeorgeFileImport>();
        
        for (MobileHealthGeorgeFileImport model : sortedList) {
            if (model.getVendor().equals(vendor)) {
                filteredList.add(model);
            }
        }
        
        exchange.getIn().setHeader(Messaging.Names.PROCESS_FILE_FIXED_LENGTH_FILTERED_LIST.toString(), filteredList);
    }
    
    /**
     * import data which is unmashalled from input files
     * 
     * @param exchange
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void importData(final Exchange exchange) {
        List<MobileHealthGeorgeFileImport> listImportedRecords = 
                (List) exchange.getIn().getHeader(Messaging.Names.PROCESS_FILE_FIXED_LENGTH_FILTERED_LIST.toString());
        String vendorNumber = (String) exchange.getIn().getHeader(Messaging.Names.PROCESS_FILE_VENDOR_ID_HEADER.toString());
        
        LOGGER.info(LoggingUtils.getLogMessageForProcessor(getClass(), "importData", "Start importing {} record(s) for vendor = {}"), 
                listImportedRecords.size(),
                vendorNumber);
        long startTime = System.currentTimeMillis();
        try {
            String beId = oracleDataService.getBeIdFromVendorNumber(vendorNumber, vendorType, vendorName);
            if (beId == null) {
                LOGGER.warn(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "importData", 
                        "Ignored all {} record(s) with VendorNum={} due to not found mapped BE_ID value"),
                        listImportedRecords.size(),
                        vendorNumber);
                return;
            }
            
            for (MobileHealthGeorgeFileImport importedRecord : listImportedRecords) {
                String staffId = oracleDataService.getStaffIdFromSSN(importedRecord.getsSSN(), beId);
                if (staffId == null) {
                    LOGGER.warn(LoggingUtils.getLogMessageForProcessor(getClass(), "importData", 
                            "Ignored record number {} with VendorNum={} and SSN={} due to not found mapped STAFF_ID by SSN value"),
                            new Object[]{listImportedRecords.indexOf(importedRecord), importedRecord.getVendor(), 
                                    CommonUtils.hideSSN(importedRecord.getsSSN())});
                    continue;
                }
                
                // each Business Entity only provides a set of diseaseses for staffs 
                List<MedicalExaminationItemCrosswalk> medicalExamItemsXwalks = oracleDataService.getMedicalExamItemCrossWalks(beId);
                
                
                // with provided diseases, find and import data to Staff Medical History
                if (medicalExamItemsXwalks != null && !medicalExamItemsXwalks.isEmpty()) {
                    List<StaffMedicalHistory> existingStaffMedicalHistories = oracleDataService.getValidStaffMedicalHistory(beId, staffId);
                    
                    for (MedicalExaminationItemCrosswalk xwalk : medicalExamItemsXwalks) {
                        routeStaffMedicalExamItemForImporting(beId, staffId, importedRecord, xwalk, medicalExamItemsXwalks, existingStaffMedicalHistories);
                    }
                }
                
            }
            
        } catch (Exception e) {
            String errorMsg = "Error during importing data";
            LOGGER.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "importData", errorMsg), e);
            throw new SandataRuntimeException(errorMsg, e);
        } finally {
            LOGGER.info(LoggingUtils.getLogMessageForProcessor(getClass(), "importData", "Done importing {} record(s) for vendor = {} took {}"), 
                    new Object[]{listImportedRecords.size(), vendorNumber, LoggingUtils.getExecDuration(startTime, System.currentTimeMillis())});
        }
    }
    
    
    
    /**
     * 
     * @param conn
     * @param beId
     * @param staffId
     * @param importedRecord
     * @param medicalExamItemsXwalks            List of MedicalExamItemCrosswalk associates with Be_Id
     * @param xwalk
     * @param exitingStaffMedicalHistory        List of Staff Medical History which are imported for Staff
     */
    private void routeStaffMedicalExamItemForImporting( 
            String beId,
            String staffId,
            MobileHealthGeorgeFileImport importedRecord, 
            MedicalExaminationItemCrosswalk xwalk,
            List<MedicalExaminationItemCrosswalk> medicalExamItemsXwalks,
            List<StaffMedicalHistory> existingStaffMedicalHistories) {
        if (xwalk.getInterfaceItemName() == null || xwalk.getBusinessEntityMedicalExaminationItemName() == null) {
            return;
        }

        StaffMedicalHistory staffMedicalHistoryToImport = null;
        String interfaceItemName = xwalk.getInterfaceItemName().toUpperCase();
        if (XRAY_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getXrayDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getXrayCode(), null, importedRecord.getXrayDate());
            }
        } else if (FINGERPRINT_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getFingerPrintDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getFingerPrintDate());
            }
        } else if (DRUG_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getDrugTestDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getDrugTestResult(), null, importedRecord.getDrugTestDate());
            }
        } else if (RUBELLA_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getRubellaDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getRubellaResult(), importedRecord.getRubellaResultReading(), importedRecord.getRubellaDate());
            }
        } else if (MEASLES_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getMeaslesDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getMeaslesResult(), importedRecord.getMeaslesResultReading(), importedRecord.getMeaslesDate());
            }
        } else if (INFLUENZA_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getInfluenzaDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getInfluenzaDate());
            }
        } else if (PPD_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getPpdDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getPpdTest(), null, importedRecord.getPpdDate());
            }
        } else if (PPD_2_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getPpdStep1date())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getPpd2Test(), null, importedRecord.getPpdStep1date());
            }
        } else if (PHYSICAL_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getPhysicalDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getPhysicalDate());
            }
        } else if (TETANUS_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getTetanusDate())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getTetanusDate());
            }
        } else if (HBV_1_INTERFACE_NAMES.contains(interfaceItemName)) {
            if (StringUtils.isNotBlank(importedRecord.getHbvDate1())) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate1());
            }
        } else if (HBV_2_INTERFACE_NAMES.contains(interfaceItemName)) {
            // shot 2 when shot 1 is taken 
            if (StringUtils.isNotBlank(importedRecord.getHbvDate2())
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_1_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate2());
            }
        } else if (HBV_3_INTERFACE_NAMES.contains(interfaceItemName)) {
            // shot 3 when shot 2 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvDate3()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_2_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate3());
            }
        } else if (HBV_TEST_INTERFACE_NAMES.contains(interfaceItemName)) {
            // test result when shot 3 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvTitre1()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_3_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getHbvPositive(), null, importedRecord.getHbvTitre1());
            }
        } else if (HBV_4_INTERFACE_NAMES.contains(interfaceItemName)) {
            // shot 4 when shot 3 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvDate4()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_3_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate4());
            }
        } else if (HBV_5_INTERFACE_NAMES.contains(interfaceItemName)) {
            // shot 5 when shot 4 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvDate5()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_4_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate5());
            }
        } else if (HBV_6_INTERFACE_NAMES.contains(interfaceItemName)) {
            // shot 6 when shot 5 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvDate6()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_5_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, null, null, importedRecord.getHbvDate6());
            }
        } else if (HBV_TEST_2_INTERFACE_NAMES.contains(interfaceItemName)) {
            // test result 2 when shot 6 is taken
            if (StringUtils.isNotBlank(importedRecord.getHbvTitre2()) 
                    && findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medicalExamItemsXwalks, existingStaffMedicalHistories, HBV_6_INTERFACE_NAMES) != null) {
                staffMedicalHistoryToImport = createNewStaffMedicalHistory(xwalk, 
                        importedRecord.getHbvPositive2(), null, importedRecord.getHbvTitre2());
            }
        }
        
        if (staffMedicalHistoryToImport != null) {
            staffMedicalHistoryToImport.setBusinessEntityID(beId);
            staffMedicalHistoryToImport.setStaffID(staffId);
            importNewStaffMedicalHist(staffMedicalHistoryToImport);
        }
    }
    
    private StaffMedicalHistory createNewStaffMedicalHistory(MedicalExaminationItemCrosswalk xwalk,
            String staffMedicalResult,
            String staffMedicalResultReading,
            String staffMedicalItemDate) {
        StaffMedicalHistory staffMedicalHistory = new StaffMedicalHistory();
        
        Date now = new Date(System.currentTimeMillis());
        Date terminateDate = null;
        try {
            terminateDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        } catch (ParseException e) {
            // not process
        }
        
        // other information of imported record
        staffMedicalHistory.setRecordCreateTimestamp(now);
        staffMedicalHistory.setRecordUpdateTimestamp(now);
        staffMedicalHistory.setRecordEffectiveTimestamp(now);
        staffMedicalHistory.setRecordTerminationTimestamp(terminateDate);
        staffMedicalHistory.setCurrentRecordIndicator(true);
        staffMedicalHistory.setChangeVersionID(BigInteger.valueOf(1));
        
        staffMedicalHistory.setMedicalExaminationItemName(xwalk.getBusinessEntityMedicalExaminationItemName());
        staffMedicalHistory.setStaffMedicalResult(staffMedicalResult);
        staffMedicalHistory.setStaffMedicalResultReading(staffMedicalResultReading);
        staffMedicalHistory.setStaffMedicalItemDate(parseImportedDate(staffMedicalItemDate));
        
        return staffMedicalHistory;
    }
    
    /**
     * import new StaffMedicalHistory object to table STAFF_MEDCL_HIST
     * @param conn
     * @param beId
     * @param staffId
     * @param staffMedclHist
     */
    private void importNewStaffMedicalHist(StaffMedicalHistory staffMedicalHistory) {
        try {
            if (isValidStaffMedicalHist(staffMedicalHistory)) {
                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffMedicalHistory);
                Object jpubType = new DataMapper().map(staffMedicalHistory);
                oracleDataService.execute(ConnectionType.COREDATA, oracleMetadata.packageName(), oracleMetadata.insertMethod(), jpubType);
            }
        } catch (Exception e) {
            String errorMsg = String.format("Exception while importing StaffMedicalHistory for Disease=%s, beID=%s, staffID=%s", 
                    staffMedicalHistory.getMedicalExaminationItemName(), staffMedicalHistory.getBusinessEntityID(), staffMedicalHistory.getStaffID());
            LOGGER.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "importNewStaffMedicalHist", errorMsg), e);
            throw new SandataRuntimeException(errorMsg, e);
        }
    }
    
    /**
     * check Business Entity ID/Staff ID/Medical Exam Name/Medical Exam Date are not null or empty
     * @param staffMedicalHistory
     * @return
     */
    private boolean isValidStaffMedicalHist(StaffMedicalHistory staffMedicalHistory) {
        if (staffMedicalHistory == null) {
            return false;
        } else {
            return StringUtils.isNotBlank(staffMedicalHistory.getBusinessEntityID())
                    && StringUtils.isNotBlank(staffMedicalHistory.getStaffID()) 
                    && (staffMedicalHistory.getMedicalExaminationItemName() != null)
                    && (staffMedicalHistory.getStaffMedicalItemDate() != null);
        }
    }
    
    /**
     * parse dateString to java.util.Date from date format yyMMdd
     * @param dateString
     * @return
     */
    private Date parseImportedDate(String dateString) {
        try {
            if (dateString == null || dateString.isEmpty()) {
                return null;
            } else {
                return new SimpleDateFormat("yyyyMMdd").parse(century + dateString);
            }
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * @return the oracleDataService
     */
    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    /**
     * @param oracleDataService the oracleDataService to set
     */
    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }
}
