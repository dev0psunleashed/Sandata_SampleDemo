/**
 * 
 */
package com.sandata.services.mobilehealth.processors.exports;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration;
import com.sandata.lab.data.model.dl.model.EmploymentStatusTypeName;
import com.sandata.lab.data.model.dl.model.GenderTypeName;
import com.sandata.lab.data.model.dl.model.MedicalExaminationItemCrosswalk;
import com.sandata.lab.data.model.dl.model.StaffMedicalHistory;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.services.mobilehealth.data.models.MobileHealthConfiguration;
import com.sandata.services.mobilehealth.data.models.exports.MobileHealthGeorgeFileExport;
import com.sandata.services.mobilehealth.processors.BaseMobileHealthProcessor;
import com.sandata.services.mobilehealth.processors.OracleDataService;
import com.sandata.services.mobilehealth.utils.CommonUtils;
import com.sandata.services.mobilehealth.utils.LoggingUtils;
import com.sandata.services.mobilehealth.utils.Messaging;
import com.sandata.up.commons.exception.SandataRuntimeException;

import static com.sandata.services.mobilehealth.utils.InterfaceItemLookupConstants.*;

/**
 * processing data to export file out of GEORGE
 * 
 * @author huyvo
 *
 */
public class ProcessExportFileProcessor extends BaseMobileHealthProcessor {
    
    private static final Logger LOG = LoggerFactory.getLogger(ProcessExportFileProcessor.class);
    
    @PropertyInject("{{file.export.timestamp}}")
    private String exportTimestampPattern = "MMddyyyy";
    @PropertyInject("{{process.downloadfile.file.cc}}")
    private String century = "20";
    @PropertyInject("{{file.export.agency.id.list}}")
    private String agencyIdListString = "";
    @PropertyInject("file.export.name.template")
    private String exportFileNameTemplate = "MHMEXP_%s.txt";
    
    @PropertyInject("{{vendor.type}}")
    private String vendorType = "VENDOR";
    @PropertyInject("{{vendor.name}}")
    private String vendorName = "MOBILE_HEALTH";
    
    @PropertyInject("{{export.configuration.key.name}}")
    private String exportConfigurationKeyName = "MW_MOBILE_HEALTH_EXPORT";
    
    @PropertyInject("{{export.local.folder}}")
    private String localSaveFolder = "target/MobileHealth/George";
    @PropertyInject("{{file.export.last.modified.format}}")
    private String lastModifiedFormat = "dd/MM/yy hh:mm aa";
    
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    private OracleDataService oracleDataService;
    
    public void buildSQLForGettingStaffToExport(final Exchange exchange) {
        ApplicationTenantKeyConfiguration appTenantConfig = exchange.getIn().getHeader(
                Messaging.Names.EXPORT_CONFIGURATION_HEADER.toString(),
                ApplicationTenantKeyConfiguration.class);
        Date exportTime = exchange.getIn().getHeader(
                Messaging.Names.EXPORT_CURRENT_EXPORT_TIME.toString(),
                Date.class);
        StringBuilder sql = new StringBuilder();
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
            
            // all columns need to be exported
            sql.append("SELECT COUNT(*) OVER() TOTAL_ROWS, ");
            sql.append("BX1.BE_ID_NUM AS VENDOR_NUMBER, ");
            sql.append("ST.BE_ID AS BE_ID, ST.STAFF_ID AS STAFF_ID, ST.STAFF_TIN AS STAFF_TIN, ST.STAFF_LAST_NAME AS STAFF_LAST_NAME, ");
            sql.append("ST.STAFF_FIRST_NAME AS STAFF_FIRST_NAME, ST.STAFF_GENDER_TYP_NAME AS STAFF_GENDER_TYP_NAME, ");
            sql.append("ST.STAFF_LAST_RAISE_DATE AS STAFF_LAST_RAISE_DATE, ST.STAFF_EMPLT_STATUS_TYP_NAME AS STAFF_EMPLT_STATUS_TYP_NAME, ");
            sql.append("ST.STAFF_DOB, ST.REC_UPDATE_TMSTP, ");
            sql.append("SCA1.STAFF_ADDR1 AS STAFF_ADDR1, SCA1.STAFF_ADDR2 AS STAFF_ADDR2, SCA1.STAFF_CITY AS STAFF_CITY, SCA1.STAFF_STATE AS STAFF_STATE, ");
            sql.append("SCA1.STAFF_PSTL_CODE AS STAFF_PSTL_CODE, SCP1.STAFF_PHONE AS STAFF_PHONE, ");
            // gets list of MEDCL_EXAM_ITEM_XWALK associate with BE_ID
            sql.append("(SELECT ");
            sql.append(         "'[' ");
            sql.append(         "|| LISTAGG('{\"InterfaceItemName\":\"' || MEIX.INTF_ITEM_NAME || '\",' ");
            sql.append(         "|| '\"BusinessEntityMedicalExaminationItemName\":\"' || MEIX.BE_MEDCL_EXAM_ITEM_NAME || '\"}', ',') ");
            sql.append(         "WITHIN GROUP (ORDER BY MEIX.BE_ID) ");
            sql.append(         "|| ']' ");
            sql.append("FROM MEDCL_EXAM_ITEM_XWALK MEIX ");
            sql.append("WHERE MEIX.BE_ID = ST.BE_ID ");
            sql.append(         "AND (TO_CHAR(MEIX.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND MEIX.CURR_REC_IND = 1) ");
            sql.append(") AS LIST_MEDCL_EXAM_ITEM_XWALK, ");
            // gets list of LIST_STAFF_MEDCL_HIST associate with BE_ID and STAFF_ID
            sql.append("(SELECT ");
            sql.append(         "'[' ");
            sql.append(         "|| LISTAGG('{\"MedicalExaminationItemName\":\"' ||SMH1.MEDCL_EXAM_ITEM_NAME || '\",' ");
            sql.append(         "|| '\"StaffMedicalItemDate\":\"' || TO_CHAR(SMH1.STAFF_MEDCL_ITEM_DATE, 'YYYY-MM-DD hh24:mi:ss') || '\",' ");
            sql.append(         "|| '\"StaffMedicalResult\":\"' || SMH1.STAFF_MEDCL_RESULT || '\"}', ',') WITHIN GROUP (ORDER BY SMH1.STAFF_ID) ");
            sql.append(         "|| ']' ");
            sql.append("FROM( ");
            sql.append(         "SELECT SMH.*, ROW_NUMBER() OVER(PARTITION BY MEDCL_EXAM_ITEM_NAME, STAFF_ID ORDER BY STAFF_MEDCL_ITEM_DATE DESC) AS ROW_NUM ");
            sql.append(         "FROM STAFF_MEDCL_HIST SMH ");
            sql.append(         "WHERE (TO_CHAR(SMH.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SMH.CURR_REC_IND = 1) ");
            sql.append(         ") SMH1 ");
            sql.append("WHERE SMH1.ROW_NUM = 1 AND SMH1.STAFF_ID = ST.STAFF_ID AND SMH1.BE_ID = ST.BE_ID ");
            sql.append(") AS LIST_STAFF_MEDCL_HIST ");
            // join all neccessary tables
            sql.append("FROM STAFF ST ");
            // Vendor Number must exist
            sql.append("JOIN ( ");
            sql.append(         "SELECT BX.*,ROW_NUMBER() OVER(PARTITION BY BE_ID,BE_ID_TYP,BE_ID_QLFR ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM FROM BE_ID_XWALK BX ");
            sql.append(         "WHERE (TO_CHAR(BX.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') ");
            sql.append(String.format(
                    ") BX1 ON ST.BE_ID = BX1.BE_ID AND BX1.BE_ID_TYP = '%s' AND BX1.BE_ID_QLFR = '%s' AND BX1.ROW_NUM = 1 ",
                    vendorType,
                    vendorName));
            // finds Staff's Address first matched and latest updated
            sql.append("LEFT JOIN ( ");
            sql.append(         "SELECT SCA.*, ROW_NUMBER() OVER(PARTITION BY BE_ID, STAFF_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM FROM STAFF_CONT_ADDR SCA ");
            sql.append(         "WHERE (TO_CHAR(SCA.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SCA.CURR_REC_IND = 1) ");
            sql.append(") SCA1 ON  ST.STAFF_ID = SCA1.STAFF_ID AND ST.BE_ID = SCA1.BE_ID AND SCA1.ROW_NUM = 1 ");
            // find Staff's Phone first matched and latest updated
            sql.append("LEFT JOIN ( ");
            sql.append(         "SELECT SCP.*, ROW_NUMBER() OVER(PARTITION BY BE_ID, STAFF_ID ORDER BY REC_UPDATE_TMSTP DESC) AS ROW_NUM FROM STAFF_CONT_PHONE SCP ");
            sql.append(         "WHERE (TO_CHAR(SCP.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SCP.CURR_REC_IND = 1) ");
            sql.append(         ") SCP1 ON  ST.STAFF_ID = SCP1.STAFF_ID AND ST.BE_ID = SCP1.BE_ID AND SCP1.ROW_NUM = 1 ");
            sql.append("WHERE (TO_CHAR(ST.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND ST.CURR_REC_IND = 1) ");
            
            Date lastExportTime = dateFormatter.parse("1970-01-01 00:00:00");
            
            MobileHealthConfiguration configuration = (MobileHealthConfiguration) GSONProvider.FromJson(
                    appTenantConfig.getTenantKeyConfigurationValue(), MobileHealthConfiguration.class);
            if (configuration != null && configuration.getLastUpdatedDate() != null) {
                lastExportTime = configuration.getLastUpdatedDate();
            }
            
            // increamental export
            sql.append(
                    String.format("AND (ST.REC_UPDATE_TMSTP BETWEEN TO_DATE('%s', 'YYYY-MM-DD hh24:mi:ss') AND TO_DATE('%s', 'YYYY-MM-DD hh24:mi:ss')) ", 
                    dateFormatter.format(lastExportTime), 
                    dateFormatter.format(exportTime)));
            
            // restriction on BusinessEntity Ids if provided
            if (agencyIdListString != null && !agencyIdListString.isEmpty()) {
                sql.append("AND ST.BE_ID in (").append(parseListStringForOracleSQL(agencyIdListString)).append(")");
            }
            
            exchange.getIn().setBody(sql.toString());
        } catch (Exception e) {
            String errMsg = "ERROR during building SQL for getting Staffs -- SQL: " + sql.toString();
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "buildSQLForGettingStaffToExport", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    /**
     * Get Staffs for exporting and transform to Export file fixed length format
     * @param exchange
     */
    @SuppressWarnings("unchecked")
    public void mapDataForExporting(final Exchange exchange) {
        Map<String, Object> staff = exchange.getIn().getBody(Map.class);
        Map<String, Object> exportedRowMap = null;
        
        try {
            if (staff != null) {
                // validate vendor number associated with business entity id
                String beId = (String) staff.get("BE_ID");
                String staffId = (String) staff.get("STAFF_ID");
                String staffSsn = (String) staff.get("STAFF_TIN");
                //String vendorNumber = oracleDataService.getVendorNumberFromBeId(beId, vendorType, vendorName);
                String vendorNumber = (String) staff.get("VENDOR_NUMBER");
                if (vendorNumber == null) {
                    LOG.warn(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "mapDataForExporting", 
                            "Ignored Staff with Id={}, beId={} and SSN={} due to not supported vendor number"),
                            new Object[]{staffId, beId, CommonUtils.hideSSN(staffSsn)});
                    // stop processing and return no data to body
                    exchange.getIn().setBody(null);
                    return;
                }
                
                exportedRowMap = new HashMap<>();
                MobileHealthGeorgeFileExport exportedRow = new MobileHealthGeorgeFileExport();
                
                exportedRow.setVendorNumber(vendorNumber);
                exportedRow.setAttendantLastName((String) staff.get("STAFF_LAST_NAME"));
                exportedRow.setAttendantFirstName((String) staff.get("STAFF_FIRST_NAME"));
                
                exportedRow.setAttendantAddress1((String) staff.get("STAFF_ADDR1"));
                exportedRow.setAttendantAddress2((String) staff.get("STAFF_ADDR2"));
                exportedRow.setAttendantCity((String) staff.get("STAFF_CITY"));
                exportedRow.setAttendantState((String) staff.get("STAFF_STATE"));
                exportedRow.setAttendantPostalCode((String) staff.get("STAFF_PSTL_CODE"));
                exportedRow.setAttendantPhone((String) staff.get("STAFF_PHONE"));

                exportedRow.setAttendantSS(staffSsn);
                
                exportedRow.setAttendantSex(formatStaffGender((String) staff.get("STAFF_GENDER_TYP_NAME")));
                exportedRow.setWorkingStatus(formatStaffWorkingStatus((Date) staff.get("STAFF_LAST_RAISE_DATE")));
                exportedRow.setTermStatus(formatStaffTermStatus((String) staff.get("STAFF_EMPLT_STATUS_TYP_NAME")));
                exportedRow.setEmployeeDateOfBirth(formatDateExport((Date) staff.get("STAFF_DOB")));
                exportedRow.setEmployeeLastModified(formatLastModifiedDate((Date) staff.get("REC_UPDATE_TMSTP")));
                
                String staffMedicalHistoriesString = (String) staff.get("LIST_STAFF_MEDCL_HIST");
                List<StaffMedicalHistory> staffMedicalHistories = parseStaffMedicalHistory(staffMedicalHistoriesString);
                
                if (staffMedicalHistories != null && !staffMedicalHistories.isEmpty()) {
                    String medclExamItemXwalksString = (String) staff.get("LIST_MEDCL_EXAM_ITEM_XWALK");
                    List<MedicalExaminationItemCrosswalk> medclExamItemXwalks = parseMedicalExamItemXwalk(medclExamItemXwalksString);
                    
                    StaffMedicalHistory ppdMedicalHistory = findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medclExamItemXwalks, staffMedicalHistories, 
                            PPD_INTERFACE_NAMES);
                    if (ppdMedicalHistory != null) {
                        exportedRow.setPpdDate(formatDateExport(ppdMedicalHistory.getStaffMedicalItemDate()));
                        exportedRow.setPpdCode(ppdMedicalHistory.getStaffMedicalResult());
                    }
                
                    StaffMedicalHistory ppd2MedicalHistory = findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medclExamItemXwalks, staffMedicalHistories, 
                            PPD_2_INTERFACE_NAMES);
                    if (ppd2MedicalHistory != null) {
                        exportedRow.setPpd2Date(formatDateExport(ppd2MedicalHistory.getStaffMedicalItemDate()));
                        exportedRow.setPpd2Code(ppd2MedicalHistory.getStaffMedicalResult());
                    }
                    
                    StaffMedicalHistory xrayMedicalHistory = findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medclExamItemXwalks, staffMedicalHistories, 
                            XRAY_INTERFACE_NAMES);
                    if (xrayMedicalHistory != null) {
                        exportedRow.setXrayDate(formatDateExport(xrayMedicalHistory.getStaffMedicalItemDate()));
                        exportedRow.setPpdChestXrayCode(xrayMedicalHistory.getStaffMedicalResult());
                    }
                    
                    StaffMedicalHistory physicalMedicalHistory = findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medclExamItemXwalks, staffMedicalHistories, 
                            PHYSICAL_INTERFACE_NAMES);
                    if (physicalMedicalHistory != null) {
                        exportedRow.setPhysicalDate(formatDateExport(physicalMedicalHistory.getStaffMedicalItemDate()));
                    }
                    
                    StaffMedicalHistory tetanusMedicalHistory = findPosibleStaffMedicalHistoryByInterfaceItemNames(
                            medclExamItemXwalks, staffMedicalHistories, 
                            TETANUS_INTERFACE_NAMES);
                    if (tetanusMedicalHistory != null) {
                        exportedRow.setTetanusDate(formatDateExport(tetanusMedicalHistory.getStaffMedicalItemDate()));
                    }
                }
                
                formatLengthForExportingFile(exportedRow);
                
                exportedRowMap.put(MobileHealthGeorgeFileExport.class.getName(), exportedRow);
            }
            
            exchange.getIn().setBody(exportedRowMap);
        } catch (Exception e) {
            String errMsg = "Unexpected exception occured when getting data for export";
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "mapDataForExporting", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    /**
     * builds export file name with export date
     * @param exchange
     */
    public void buildFileNameForExport(final Exchange exchange) { 
        Date exportTime = new Date();
        String fileName = String.format(exportFileNameTemplate, new SimpleDateFormat(exportTimestampPattern).format(exportTime));
        
        exchange.getIn().setHeader(Exchange.FILE_NAME, fileName);
        exchange.getIn().setHeader(Messaging.Names.EXPORT_CURRENT_EXPORT_TIME.toString(), exportTime);
    }
    
    /**
     * clean up all files in local folder preparing to export
     * @param exchange
     */
    public void cleanUpLocalSavedFolder(final Exchange exchange) { 
        try {
            File saveFolder = new File(localSaveFolder);
            if (!saveFolder.exists()) {
                saveFolder.mkdirs();
            }
            
            // clean up all files in local save folder
            if (saveFolder.exists() && saveFolder.isDirectory()) {
                File files[] = saveFolder.listFiles();
                for (File file : files) {
                    FileUtil.deleteFile(file);
                }
            }
        } catch (Exception e) {
            
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<StaffMedicalHistory> parseStaffMedicalHistory(String staffMedicalHistoriesString) {
        try {
            if (StringUtils.isNotBlank(staffMedicalHistoriesString)) {
                // make sure the parser not throw exception on Date Field is empty String
                String handledStaffMedclHistString = staffMedicalHistoriesString.replace(
                        "\"StaffMedicalItemDate\":\"\"", "\"StaffMedicalItemDate\":null");
                
                return (List<StaffMedicalHistory>) GSONProvider.FromJson(handledStaffMedclHistString, 
                        new TypeToken<List<StaffMedicalHistory>>(){}.getType());
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            String errMsg = "Unexpected exception occured when parsing StaffMedicalHistory from String";
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "parseStaffMedicalHistory", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    private List<MedicalExaminationItemCrosswalk> parseMedicalExamItemXwalk(String medclExamItemXwalksString) {
        try {
            if (StringUtils.isNotBlank(medclExamItemXwalksString)) {
                return (List<MedicalExaminationItemCrosswalk>) GSONProvider.FromJson(medclExamItemXwalksString, 
                        new TypeToken<List<MedicalExaminationItemCrosswalk>>(){}.getType());
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            String errMsg = "Unexpected exception occured when parsing MedicalExaminationItemCrosswalk from String";
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "parseMedicalExamItemXwalk", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    /**
     * encapsulate the list value with single quote to list Oracle String format  
     * @param text
     * @return
     */
    private String parseListStringForOracleSQL(String text) {
        StringBuilder sb = new StringBuilder();
        if (text != null) {
            for (String element : text.split(",")) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                
                sb.append("'").append(element).append("'");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * parse date to format yyMMdd to export file
     * @param date
     * @return
     */
    private String formatDateExport(Date date) {
        if (date == null) {
            return null;
        } else {
            return new SimpleDateFormat("yyMMdd").format(date);
        }
    }
    
    /**
     * formats staff's term status: If the status is Active, return space, else return "T"
     * 
     * @param status
     * @return
     */
    private String formatStaffTermStatus(String status) {
        if (EmploymentStatusTypeName.ACTIVE.value().equalsIgnoreCase(status)) {
            return " ";
        } else {
            return "T";
        }
    }
    
    private String formatLastModifiedDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return new SimpleDateFormat(lastModifiedFormat).format(date);
        }
    }
    
    /**
     * "Y" if attendant has worked hours in last 5 months, otherwise "N"
     * 
     * @param staff
     * @return
     */
    private String formatStaffWorkingStatus(Date stafflastDateOfWork) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -5);
        Date last5Months = calendar.getTime();
        
        if (stafflastDateOfWork != null && stafflastDateOfWork.compareTo(last5Months) > 0) {
            return "Y";
        } else {
            return "N";
        }
    }
    
    /**
     * cut off the begin characters if length is longer than defined
     * @param exportedRow
     */
    private void formatLengthForExportingFile(MobileHealthGeorgeFileExport exportedRow) {
        
        try {
            Field[] fields = MobileHealthGeorgeFileExport.class.getDeclaredFields();
            Method[] methods =  MobileHealthGeorgeFileExport.class.getMethods();
            
            if (fields != null && methods != null) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    
                    DataField dataFieldAnnotation = field.getAnnotation(DataField.class);
                    if (dataFieldAnnotation != null) {
                        String fieldValue = (String) field.get(exportedRow);
                        
                        // if data length is over defined length
                        if (fieldValue != null && fieldValue.length() > dataFieldAnnotation.length()) {
                            fieldValue = fieldValue.substring(fieldValue.length() - dataFieldAnnotation.length());
                            Method methodSetField = getSetMethod(methods, field);
                            
                            if (methodSetField != null) {
                                methodSetField.invoke(exportedRow, fieldValue);
                            }
                        }
                    }
                    
                }
            }
        } catch (Exception e) {
            String errMsg = "Cannot get trim MobileHealthGeorgeFileExport fields length due to Exception";
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "formatLengthForExportingFile", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    /**
     * get set method for specified field
     * @param methods
     * @param field
     * @return
     */
    private Method getSetMethod(Method[] methods, Field field) {
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("set" + field.getName())) {
                return method;
            }
        }
        return null;
    }
    
    private String formatStaffGender(String genderTypeName) {
        if (GenderTypeName.MALE.value().equalsIgnoreCase(genderTypeName)) {
            return "M";
        } else if (GenderTypeName.FEMALE.value().equalsIgnoreCase(genderTypeName)) {
            return "F";
        } else {
            return " ";
        }
    }
    
    /**
     * get configuration for incrementally export
     * @param exchange
     */
    @SuppressWarnings("unchecked")
    public void getExportConfiguration(final Exchange exchange) {
        try {
            String getExportConfiguraiton = "SELECT * FROM APP_TENANT_KEY_CONF WHERE KEY_NAME = ? AND ROWNUM = 1";
            List<ApplicationTenantKeyConfiguration> appTenantConfigs = (List<ApplicationTenantKeyConfiguration>) oracleDataService.getEntitiesForId(
                    ConnectionType.METADATA,
                    getExportConfiguraiton, 
                    "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration", 
                    exportConfigurationKeyName);
            
            ApplicationTenantKeyConfiguration appTenantConfig;
            if (appTenantConfigs != null && !appTenantConfigs.isEmpty()) {
                appTenantConfig = appTenantConfigs.get(0);
            } else {
                appTenantConfig = new ApplicationTenantKeyConfiguration();
                appTenantConfig.setTenantKeyConfigurationValue("");
            }
            
            exchange.getIn().setHeader(Messaging.Names.EXPORT_CONFIGURATION_HEADER.toString(), appTenantConfig);
        } catch (Exception e) {
            String errMsg = "Cannot get configuration for EXPORT with KEY_NAME = " + exportConfigurationKeyName;
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "getExportConfiguration", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
        }
    }
    
    
    /**
     * udpate configuration for next export
     * @param exchange
     */
    public void updateExportConfiguration(final Exchange exchange) {
        try {
            ApplicationTenantKeyConfiguration appTenantConfig = exchange.getIn().getHeader(
                    Messaging.Names.EXPORT_CONFIGURATION_HEADER.toString(),
                    ApplicationTenantKeyConfiguration.class);
            Date exportTime = exchange.getIn().getHeader(
                    Messaging.Names.EXPORT_CURRENT_EXPORT_TIME.toString(),
                    Date.class);
            
            if (appTenantConfig.getApplicationTenantKeyConfigurationSK() == null) {
                appTenantConfig = new ApplicationTenantKeyConfiguration();
                appTenantConfig.setRecordCreateTimestamp(exportTime);
                appTenantConfig.setApplicationTenantKeyConfigurationParentSK(null);
                // currently we do not specify the AppTenantSK 
                appTenantConfig.setApplicationTenantSK(BigInteger.valueOf(1));
                appTenantConfig.setKeyName(exportConfigurationKeyName);
                appTenantConfig.setTenantKeyConfigurationValue("");
            }
            appTenantConfig.setRecordUpdateTimestamp(exportTime);
            
            MobileHealthConfiguration exportConfiguration = (MobileHealthConfiguration) GSONProvider.FromJson(
                    appTenantConfig.getTenantKeyConfigurationValue(), MobileHealthConfiguration.class);
            if (exportConfiguration == null) {
                exportConfiguration = new MobileHealthConfiguration();
                exportConfiguration.setDescription("MobileHealth Export configuration");
            }
            exportConfiguration.setLastUpdatedDate(exportTime);
            
            // update configuration value to AppTenantConfig
            appTenantConfig.setTenantKeyConfigurationValue(GSONProvider.ToJson(exportConfiguration));
            
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(appTenantConfig);
            Object jpubType = new DataMapper().map(appTenantConfig);
            String method = appTenantConfig.getApplicationTenantKeyConfigurationSK() == null ? oracleMetadata.insertMethod() : oracleMetadata.updateMethod();
            
            oracleDataService.execute(ConnectionType.METADATA, oracleMetadata.packageName(), method, jpubType);
        } catch (Exception e) {
            String errMsg = "Cannot update configuration for EXPORT with KEY_NAME = " + exportConfigurationKeyName;
            LOG.error(LoggingUtils.getErrorLogMessageForProcessor(getClass(), "updateExportConfiguration", errMsg), e);
            throw new SandataRuntimeException(errMsg, e);
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
