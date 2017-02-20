package com.sandata.lab.rest.staff.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.date.DateUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.StaffPayrollDetailExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffAvailabilitySpecific;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffTrainingClassEventEnrollmentExt;
import com.sandata.lab.data.model.dl.model.extended.staff.StaffTrainingExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.data.model.result.SequenceKeyValueResult;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.staff.api.DataService;
import com.sandata.lab.rest.staff.handler.AdminDataHandler;
import com.sandata.lab.rest.staff.model.AuditStaff;
import com.sandata.lab.rest.staff.model.FieldChangedLog;
import com.sandata.lab.rest.staff.model.StaffProfileChangedLog;
import com.sandata.lab.rest.staff.model.StaffLanguageItemRequest;
import com.sandata.lab.rest.staff.utils.data.DataHelper;
import com.sandata.lab.rest.staff.utils.log.OracleDataLogger;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.message.MessageContentsList;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.sandata.lab.common.utils.string.StringUtil.IsNullOrEmpty;
import static com.sandata.lab.rest.staff.utils.log.OracleDataLogger.CreateLogger;
import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 8:47 PM
 */
@SuppressWarnings("unchecked")
public class RestDataService implements DataService {

    private OracleDataService oracleDataService;

    public void findStaff(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = getOracleDataService().getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            StringBuilder staffFilterTyp = new StringBuilder();

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            List<String> params = new ArrayList<>();

            String keywordString = (String) mcl.get(9);

            if (!StringUtil.IsNullOrEmpty(keywordString)) {

                String[] keywords = keywordString.split(" ");
                if (keywords.length >= 2) {

                    // Example: Tom Hanks
                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[1].toUpperCase() + "%");

                    // Flip params around
                    // Example: Hanks Tom
                    params.add(keywords[1].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    // NOTE:    If there is a third word or more, it will be ignored!
                    //          Keyword is expected to be a name, first/last.
                    //          Check first/last in any order. For example: Tom Hanks or Hanks Tom
                    staffFilterTyp.append("((UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?) OR " +
                        "(UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?)) AND ");
                } else {
                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    staffFilterTyp.append("(UPPER(T1.STAFF_FIRST_NAME) LIKE ? " +
                        "OR UPPER(T1.STAFF_LAST_NAME) LIKE ?) AND ");
                }
            }
            
            String lastName = (String) mcl.get(0);
            if (!StringUtil.IsNullOrEmpty(lastName)) {
                staffFilterTyp.append("UPPER(T1.STAFF_LAST_NAME) LIKE ? AND ");
                params.add(lastName.toUpperCase(Locale.US) + "%");
            }
            
            String firstName = (String) mcl.get(1);
            if (!StringUtil.IsNullOrEmpty(firstName)) {
                staffFilterTyp.append("UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND ");
                params.add(firstName.toUpperCase(Locale.US) + "%");
            }

            String staffId = (String) mcl.get(2);
            if (staffId != null && staffId.length() > 0) {
                staffFilterTyp.append("UPPER(T1.STAFF_ID) LIKE ? AND ");
                params.add(staffId.toUpperCase(Locale.US) + "%");
            }

            String homePhone = (String) mcl.get(3);
            if (homePhone != null && homePhone.length() > 0) {
                staffFilterTyp.append("T1Phone.STAFF_PHONE LIKE ? AND ");
                params.add(homePhone + "%");
            }

            String ssn = (String) exchange.getIn().getHeader("ssn");
            if (!StringUtil.IsNullOrEmpty(ssn)) {

                params.add(ssn);
                staffFilterTyp.append(" T1.STAFF_TIN = ? AND ");

                params.add(TaxpayerIdentificationNumberQualifier.SSN.value().toUpperCase());
                staffFilterTyp.append(" UPPER(T1.STAFF_TIN_QLFR) = ? AND ");
            }
            
            /*
             * For further filter by any column in STAFF_CONT_ADDR table, just append to addressFilter for
             * looking up every address of Staff due to a staff/patient may have many addresses
             */
            StringBuilder addressFilter = new StringBuilder();
            String zipCode = (String) mcl.get(16);
            if (!StringUtil.IsNullOrEmpty(zipCode)) {
                addressFilter.append("UPPER(T2.STAFF_PSTL_CODE) = ? AND ");
                params.add(zipCode.toUpperCase(Locale.US));
            }

            // Handle city filter which accepts multiple values as a list
            List<String> cityList = RestUtil.normalizeParamList((List<String>) exchange.getIn().getHeader("city"));
            if (cityList != null && cityList.size() > 0) {
                addressFilter.append("(");
                for (int i = 0; i < cityList.size(); i++) {
                    params.add(cityList.get(i).toUpperCase(Locale.ENGLISH) + '%');
                    addressFilter.append("UPPER(T2.STAFF_CITY) LIKE ?");
                    if (i < (cityList.size() - 1)) {
                        addressFilter.append(" OR ");
                    }
                }
                addressFilter.append(") AND ");
            }
            // Add additional address filters address, state; SAN-3255.SAN-3420
            String address =  (String) mcl.get(20);
            if(!StringUtil.IsNullOrEmpty(address)) {
                params.add('%' + address.toUpperCase(Locale.ENGLISH) + '%');
                params.add('%' + address.toUpperCase(Locale.ENGLISH) + '%');
                addressFilter.append("( UPPER(T2.STAFF_ADDR1) LIKE ? OR UPPER(T2.STAFF_ADDR2) LIKE ? ) AND " );
            }
            String state =  (String) mcl.get(21);
            if(!StringUtil.IsNullOrEmpty(state)) {
                params.add(state.toUpperCase(Locale.ENGLISH));
                addressFilter.append("( UPPER(T2.STAFF_STATE) = ? ) AND " );
            }

            // if we have address filters, append to the filter 
            if (addressFilter.length() > 0) {
                staffFilterTyp.append("(T1.STAFF_ID IN ( ")
                    .append("SELECT DISTINCT STAFF_ID FROM STAFF_CONT_ADDR T2 ")
                    .append("WHERE T2.BE_ID = T1.BE_ID AND T2.STAFF_ID = T1.STAFF_ID ")
                    .append("    AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) ")
                    .append("    AND ").append(addressFilter)
                    .append("    (1 = 1)")
                    .append(")) AND ").toString();
            }
            //add language to filter; SAN-3255.SAN-3420
            String language = (String) mcl.get(22);
            if(!StringUtil.IsNullOrEmpty(language)) {
                staffFilterTyp.append("(T1.STAFF_ID IN ( ")
                        .append("SELECT DISTINCT STAFF_ID FROM STAFF_LANG LANG WHERE ")
                        .append("LANG.BE_ID = T1.BE_ID AND LANG.STAFF_ID = T1.STAFF_ID ")
                        .append("AND UPPER(LANG.LANG_CODE) = ? ")
                        .append("   AND (TO_CHAR(LANG.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND LANG.CURR_REC_IND = 1) )) AND " );
                params.add(language.toUpperCase(Locale.ENGLISH));
            }


            int page = (Integer) mcl.get(4);
            int pageSize = (Integer) mcl.get(5);

            // Settings: ln = last_name, fn = first_name, hp = home_phone, in = insurance_id
            // dob = date_of_birth, addr = address, sd = start_date, loc = location
            String sortOn = (String) mcl.get(6);

            String orderByColumn = sortOn;
            if (sortOn.equalsIgnoreCase("ln")) {
                orderByColumn = "UPPER(T1.STAFF_LAST_NAME)";
            } else if (sortOn.equalsIgnoreCase("fn")) {
                orderByColumn = "UPPER(T1.STAFF_FIRST_NAME)";
            } else if (sortOn.equalsIgnoreCase("hp")) {
                orderByColumn = "T1Phone.STAFF_PHONE";
            } else if (sortOn.equalsIgnoreCase("si")) {
                orderByColumn = "T1.STAFF_ID";
            } else if (sortOn.equalsIgnoreCase("dob")) {
                orderByColumn = "T1.STAFF_DOB";
            } else if (sortOn.equalsIgnoreCase("addr")) {
                orderByColumn = "UPPER(T1Addr.STAFF_ADDR1)";
            } else if (sortOn.equalsIgnoreCase("HireDate") || sortOn.equalsIgnoreCase("hd")) {
                orderByColumn = "T1.STAFF_HIRE_DATE";
            } else if (sortOn.equalsIgnoreCase("loc")) {
                orderByColumn = "UPPER(T2.BE_PRMY_ADDR1)";
            } else if (sortOn.equalsIgnoreCase("stt")) {
                orderByColumn = "UPPER(T1.STAFF_EMPLT_STATUS_TYP_NAME)";
            } else if (sortOn.equalsIgnoreCase("pos")) {
                orderByColumn = "UPPER(T1.STAFF_POSITION_NAME)";
            } else {
                // default sort by last name if sort option is not supported
                orderByColumn = "UPPER(T1.STAFF_LAST_NAME)";
            }

            String direction = (String) mcl.get(7);

            String bsnEntId = (String) mcl.get(8);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);

            // Validate schedule from date time
            String schedFromDateTimeString = (String) mcl.get(10);
            Date schedFromDateTime = null;
            if (!StringUtil.IsNullOrEmpty(schedFromDateTimeString)) {
                try {
                    schedFromDateTime = format.parse(schedFromDateTimeString);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(exchange,
                            format("Schedule From Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    schedFromDateTimeString));
                }
            }

            // Validate schedule to date time
            String schedToDateTimeString = (String) mcl.get(11);

            // Default the toDate to the fromDate if toDate is not provided
            if (schedFromDateTimeString != null && schedFromDateTimeString.length() > 0) {
                if (schedToDateTimeString == null || schedToDateTimeString.length() == 0) {
                    schedToDateTimeString = schedFromDateTimeString;
                }
            }

            Date schedToDateTime = null;
            if (!StringUtil.IsNullOrEmpty(schedToDateTimeString)) {
                try {
                    schedToDateTime = format.parse(schedToDateTimeString);
                } catch (ParseException pe) {
                    throw new SandataRuntimeException(exchange,
                            format("Schedule To Date Time: [%s]: Is NOT a valid date and/or the format is incorrect!",
                                    schedToDateTimeString));
                }
            }

            String staffPosition = (String) mcl.get(12);
            String patientId = (String) mcl.get(13);

            List<String> employmentStatusList = RestUtil.normalizeParamList((List<String>) mcl.get(14));

            String complianceStatus = (String) mcl.get(15);

            String coordinator = (String)exchange.getIn().getHeader("coordinator");

            String staffNurse = (String) mcl.get(23);

            Response response;

            if (IsNullOrEmpty(staffPosition) ||
                    (!StringUtil.IsNullOrEmpty(staffPosition) &&
                            StringUtil.IsNullOrEmpty(schedFromDateTimeString) && StringUtil.IsNullOrEmpty(schedToDateTimeString))) {
                response = oracleDataService.findStaff(
                    connection,
                    staffFilterTyp,
                    orderByColumn,
                    page,
                    pageSize,
                    direction.toUpperCase(),
                    bsnEntId,
                    schedFromDateTime,
                    schedToDateTime,
                    staffPosition,
                    employmentStatusList,
                    complianceStatus,
                    coordinator,
                    params,
                    staffNurse
                );

            } else {

                if (schedFromDateTime == null || schedToDateTime == null) {

                    throw new SandataRuntimeException(exchange, "Schedule From/To Dates are required!");
                }

                response = oracleDataService.findStaffForServiceSchedule(
                    connection,
                    staffFilterTyp,
                    orderByColumn,
                    page,
                    pageSize,
                    direction.toUpperCase(),
                    bsnEntId,
                    schedFromDateTime,
                    schedToDateTime,
                    staffPosition,
                    patientId,
                    employmentStatusList,
                    complianceStatus,
                    coordinator,
                    params,
                    staffNurse
                );
            }

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public void get(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getOracleConnection();

            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];
            String className = "com.sandata.lab.data.model.dl.model." + methodParts[3];

            Object result;
            Object body = exchange.getIn().getBody();

            if (exchange.getIn().getHeader("sequence_key") != null) {

                long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

                result = oracleDataService.executeGet(
                    packageName,
                    methodName,
                    className,
                    sequenceKey
                );

                ArrayList result2 = (ArrayList<Object>) result;

                if (result2 != null && result2.size() > 0) {
                    result = result2.get(0);

                    // Get the subtables that are required for the Main Model... for example, Patient would also need to get Patient Contact Details...
                    if (methodParts[3].equals("Staff")) {

                        exchange.getIn().setBody(getRelatedStaffData(connection, (Staff) result));
                    } else {
                        exchange.getIn().setBody(result);
                    }
                } else {
                    exchange.getIn().setBody(null);
                }
            } else {
                MessageContentsList mcl = (MessageContentsList) body;

                if (methodName.endsWith("ForID")) {

                    String staffId = (String) mcl.get(0);
                    if (staffId == null || staffId.length() == 0) {
                        throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
                    }

                    String bsnEntId = (String) mcl.get(1);
                    if (bsnEntId == null || bsnEntId.length() == 0) {
                        throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
                    }

                    String tableName = DataHelper.tableName(methodParts, 4);

                    if (tableName.equals("STAFF")) {
                        List<Staff> staffCollection = (List<Staff>) oracleDataService.getEntitiesForId(
                            DataHelper.houseKeeping("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ?", tableName),
                            className,
                            staffId,
                            bsnEntId);

                        List<StaffExt> staffExtCollection = new ArrayList<>();
                        for (Staff staff : staffCollection) {
                            StaffExt staffExt = getRelatedStaffData(connection, staff);
                            staffExtCollection.add(staffExt);
                        }

                        exchange.getIn().setBody(staffExtCollection);

                    } else if ("STAFF_TRNG".equals(tableName)) {
                        String sortOn = (String) exchange.getIn().getHeader("sort_on");
                        String direction = (String) exchange.getIn().getHeader("direction");

                        String sortByColumn = "STAFF_TRNG_START_DTIME"; // default sort
                        switch (sortOn) {
                            case "stsd":
                                sortByColumn = "STAFF_TRNG_START_DTIME";
                                break;
                            case "sted":
                                sortByColumn = "STAFF_TRNG_ENROLLED_DATE";
                                break;
                            case "strc":
                                sortByColumn = "STAFF_TRNG_RESULT_CODE";
                                break;
                            case "stln":
                                sortByColumn = "STAFF_TRNG_LOC_NAME";
                                break;
                            case "stc":
                                sortByColumn = "STAFF_TRNG_CODE";
                                break;
                        }

                        String sql = DataHelper.houseKeeping("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ?", tableName);
                        sql = sql + format(" ORDER BY %s %s", sortByColumn, direction);

                        List<StaffTraining> staffTrainings = (List<StaffTraining>) oracleDataService.getEntitiesForId(
                            sql,
                            className,
                            staffId,
                            bsnEntId);

                        List<StaffTrainingExt> staffTrainingExts = new ArrayList<StaffTrainingExt>();
                        for (StaffTraining staffTraining : staffTrainings) {
                            StaffTrainingExt staffTrainingExt = getStaffTrainingExt(staffTraining);
                            staffTrainingExts.add(staffTrainingExt);
                        }

                        exchange.getIn().setHeader("TOTAL_ROWS", staffTrainingExts.size());
                        exchange.getIn().setHeader("ORDER_BY_COLUMN", sortByColumn);
                        exchange.getIn().setHeader("ORDER_BY_DIRECTION", direction);
                        exchange.getIn().setBody(staffTrainingExts);

                    } else {
                        exchange.getIn().setBody(oracleDataService.getEntitiesForId(
                            DataHelper.houseKeeping("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ?", tableName),
                            className,
                            staffId,
                            bsnEntId));
                    }
                } else {
                    String[] params = new String[mcl.size()];

                    for (int index = 0; index < mcl.size(); index++) {
                        params[index] = (String) mcl.get(index);
                    }

                    result = oracleDataService.executeGet(
                        packageName,
                        methodName,
                        className,
                        params
                    );

                    if (methodParts[3].equals("StaffPayrollDetail")) {

                        List<StaffPayrollDetailExt> staffPayrollDetailExtList = new ArrayList<>();

                        List<StaffPayrollDetail> staffPayrollDetails = (List<StaffPayrollDetail>) result;
                        for (StaffPayrollDetail staffPayrollDetail : staffPayrollDetails) {

                            //TODO: Last Date Worked now requires the BE_ID since the staff_id can be duplicated in different agencies
                            /*StaffPayrollDetailExt staffPayrollDetailExt = new StaffPayrollDetailExt(staffPayrollDetail,
                                oracleDataService.getDateLastWorked(
                                        1L,
                                        staffPayrollDetail.getStaffID(),
                                        bsnEntId));*/
                            //TEMP: --
                            StaffPayrollDetailExt staffPayrollDetailExt = new StaffPayrollDetailExt(staffPayrollDetail, null);
                            //TEMP: --

                            staffPayrollDetailExtList.add(staffPayrollDetailExt);
                        }

                        exchange.getIn().setBody(staffPayrollDetailExtList);
                    } else {
                        exchange.getIn().setBody(result);
                    }
                }
            }
        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private StaffExt getRelatedStaffData(Connection connection, Staff staff) {

        StaffExt staffExt = new StaffExt(staff);

        // StaffWorkingPreference
        List<StaffWorkingPreference> list1 = (List<StaffWorkingPreference>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffWrkPref",
            "com.sandata.lab.data.model.dl.model.StaffWorkingPreference",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffWorkingPreference().addAll(list1);

        // StaffPayrollDetail
        List<StaffPayrollDetail> list2 = (List<StaffPayrollDetail>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffPrDet",
            "com.sandata.lab.data.model.dl.model.StaffPayrollDetail",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffPayrollDetail().addAll(list2);

        // StaffRate
        List<StaffRate> list4 = (List<StaffRate>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffRate",
            "com.sandata.lab.data.model.dl.model.StaffRate",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffRate().addAll(list4);

        // StaffCompliance
        List<StaffCompliance> list5 = (List<StaffCompliance>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffComp",
            "com.sandata.lab.data.model.dl.model.StaffCompliance",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffCompliance().addAll(list5);

        // StaffCredentials
        List<StaffCredential> list6 = (List<StaffCredential>) oracleDataService.getEntitiesForId(
            "SELECT * FROM STAFF_CREDENTIAL WHERE STAFF_ID = ? AND BE_ID = ?" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
            "com.sandata.lab.data.model.dl.model.StaffCredential",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffCredential().addAll(list6);

        // StaffSkills
        List<StaffSkill> list7 = (List<StaffSkill>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffSkill",
            "com.sandata.lab.data.model.dl.model.StaffSkill",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffSkill().addAll(list7);

        // StaffCertifications
        List<StaffCertification> list8 = (List<StaffCertification>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffCert",
            "com.sandata.lab.data.model.dl.model.StaffCertification",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffCertification().addAll(list8);

        // StaffAvailability
        List<StaffAvailability> list9 = (List<StaffAvailability>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffAvail",
            "com.sandata.lab.data.model.dl.model.StaffAvailability",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffAvailability().addAll(list9);

        // StaffNotes
        List<StaffNote> list10 = (List<StaffNote>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffNote",
            "com.sandata.lab.data.model.dl.model.StaffNote",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffNote().addAll(list10);

        // StaffNotes
        List<StaffNotificationMethod> list11 = (List<StaffNotificationMethod>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffNtfctnMthd",
            "com.sandata.lab.data.model.dl.model.StaffNotificationMethod",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        List<StaffComplianceSummary> staffComplianceSummaries = (List<StaffComplianceSummary>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffCompSummary",
            "com.sandata.lab.data.model.dl.model.StaffComplianceSummary",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffComplianceSummary().addAll(staffComplianceSummaries);

        // StaffContactPhone
        List<StaffContactPhone> staffContactPhone = (List<StaffContactPhone>) oracleDataService.executeGet(
            "PKG_STAFF",
            "getStaffContPhone",
            "com.sandata.lab.data.model.dl.model.StaffContactPhone",
            staffExt.getStaffID(),
            staffExt.getBusinessEntityID());

        staffExt.getStaffContactPhone().addAll(staffContactPhone);

        staffExt.setCompletedInServiceTrainingHours(
            oracleDataService.getStaffTrngCompletedInServiceHours(staffExt.getBusinessEntityID(), staffExt.getStaffID()));

        staffExt.setRequiredInServiceTrainingHours(
            oracleDataService.getStaffTrngRequiredInServiceHours(staffExt.getBusinessEntityID(), staffExt.getStaffPositionName()));

        staffExt.setCompliantIndicator(oracleDataService.getStaffCompliantIndicator(staffExt.getBusinessEntityID(), staffExt.getStaffID()));

        staffExt.getStaffNotificationMethod().addAll(list11);

        staffExt.setLastDateWorked(oracleDataService.getLastDateWorked(
                connection,
                staffExt.getStaffSK().longValue(),
                staffExt.getStaffID(),
                staffExt.getBusinessEntityID()));

        // GEOR-6139
        staffExt.setVerifiedStartDate(oracleDataService.getVerifiedStartDate(staffExt));

        AdminDataHandler adminDataHandler = new AdminDataHandler(oracleDataService);

        staffExt.setCoordinatorId(adminDataHandler.getCoordinatorIdForStaffId(
            staffExt.getBusinessEntityID(), staffExt.getStaffID()));

        staffExt.setNurseId(adminDataHandler.getNurseIdForStaffId(
            staffExt.getBusinessEntityID(), staffExt.getStaffID()));

        return staffExt;
    }

    private StaffTrainingExt getStaffTrainingExt(final StaffTraining staffTraining) {
        StaffTrainingExt staffTrainingExt = new StaffTrainingExt(staffTraining);
        List<BusinessEntityStaffTrainingLookup> beStaffTrainingLookups = (List<BusinessEntityStaffTrainingLookup>) oracleDataService.getEntitiesForId(
            "SELECT * FROM BE_STAFF_TRNG_LKUP WHERE STAFF_TRNG_CODE = ? AND BE_ID = ?" +
                " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
            "com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingLookup",
            staffTrainingExt.getStaffTrainingCode(),
            staffTrainingExt.getBusinessEntityID());

        if (!beStaffTrainingLookups.isEmpty()) {
            staffTrainingExt.setStaffTrainingCodeDescription(beStaffTrainingLookups.get(0).getStaffTrainingDescription());
            staffTrainingExt.setStaffTrainingName(beStaffTrainingLookups.get(0).getStaffTrainingName());
        }
        
        List<BusinessEntityStaffTrainingCategoryLookup> beStaffTrainingCategories = (List<BusinessEntityStaffTrainingCategoryLookup>) oracleDataService.getEntitiesForId(
                "SELECT T1.* FROM BE_STAFF_TRNG_CTGY_LKUP T1 " +
                "    INNER JOIN BE_STAFF_TRNG_CTGY_LST T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_TRNG_CTGY_CODE = T2.STAFF_TRNG_CTGY_CODE " +
                "WHERE T2.STAFF_TRNG_CODE = ? AND T1.BE_ID = ? " +
                "    AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                "    AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) ",
                "com.sandata.lab.data.model.dl.model.BusinessEntityStaffTrainingCategoryLookup",
                staffTrainingExt.getStaffTrainingCode(),
                staffTrainingExt.getBusinessEntityID());
        if (!beStaffTrainingCategories.isEmpty()) {
            staffTrainingExt.getBusinessEntityStaffTrainingCategoryLookup().addAll(beStaffTrainingCategories);
        }

        return staffTrainingExt;
    }

    @Override
    public void delete(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            String operationName = (String) exchange.getIn().getHeader("operationName");

            String[] methodParts = operationName.split("_");

            String packageName = methodParts[0] + "_" + methodParts[1];
            String methodName = methodParts[2];

            long sequenceKey = (long) exchange.getIn().getHeader("sequence_key");

            long result = oracleDataService.execute(
                packageName,
                methodName,
                sequenceKey
            );

            if (methodName.equalsIgnoreCase("deleteStaffTrngLocation")
                && result == 1) {
                // Need to also delete any schedules for this location.
                int numClassSchedulesDeleted = oracleDataService.deleteStaffTrngClsEvntForLoc(sequenceKey);
                logger.info(format("Deleted %s class event schedules for staff training location with SK %s.",
                    numClassSchedulesDeleted,
                    sequenceKey));
            } else if (methodName.equalsIgnoreCase("deleteStaffTrngClsEvntEnrol")
                && result == 1) {
                // Need to also delete StaffTrainingEntity.
                oracleDataService.deleteStaffTrngForClsEvntEnrol(sequenceKey);
            }

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    @Override
    public long update(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {
            Object data = exchange.getIn().getBody();

            if(data instanceof StaffWorkingPreference){
                StaffWorkingPreference workingPreference = (StaffWorkingPreference) data;

                if(StringUtil.IsNullOrEmpty(workingPreference.getStaffWorkingPreferenceName())){
                    throw new SandataRuntimeException(exchange, "WorkingPreferenceName cannot be null or empty");
                }
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(exchange, connection, data, false /* UPDATE */, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Update was not successful!");
            }

            return returnVal;

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    @Override
    public long insert(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {
            Object data = exchange.getIn().getBody();

            if(data instanceof Staff){
                Staff staff = (Staff) data;

                if(StringUtil.IsNullOrEmpty(staff.getStaffID())){
                    throw new SandataRuntimeException(exchange, "StaffID cannot be empty or null");
                }

                List<Staff> listActiveStaffByID = (List<Staff>) oracleDataService.getEntitiesForId(
                        DataHelper.houseKeeping("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ?", "STAFF"),
                        Staff.class.getName(),
                        staff.getStaffID(),
                        staff.getBusinessEntityID());

                if(listActiveStaffByID != null && listActiveStaffByID.size() > 0){
                    throw new SandataRuntimeException(exchange, String.format("The staff id [%s] is already being used", listActiveStaffByID.get(0).getStaffID()));
                }
            }

            if(data instanceof StaffWorkingPreference){
                StaffWorkingPreference workingPreference = (StaffWorkingPreference) data;

                if(StringUtil.IsNullOrEmpty(workingPreference.getStaffWorkingPreferenceName())){
                    throw new SandataRuntimeException(exchange, "WorkingPreferenceName cannot be null or empty");
                }
            }

            if(data instanceof StaffCompliance){
                StaffCompliance staffCompliance = (StaffCompliance) data;

                List<StaffCompliance> listActiveStaffCompliance = (List<StaffCompliance>) oracleDataService.getEntitiesForId(
                        DataHelper.houseKeeping("SELECT * FROM %s WHERE COMP_CODE = ? AND BE_ID = ?", "BE_COMP_LKUP"),
                        StaffCompliance.class.getName(),
                        staffCompliance.getComplianceCode(),
                        staffCompliance.getBusinessEntityID());

                if(listActiveStaffCompliance != null && listActiveStaffCompliance.size() == 0){
                    throw new SandataRuntimeException(exchange, String.format("The compliance code [%s] is not active in BE_COMP_LKUP", staffCompliance.getComplianceCode()));
                }
            }

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            long returnVal = executeRecursive(exchange, connection, data, true, -999);
            if (returnVal > 0) {

                connection.commit();
                exchange.getIn().setBody(returnVal);
            } else {
                throw new SandataRuntimeException(exchange, "Insert was not successful!");
            }

            return returnVal;

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private void setSk(final Object jpubType, final long sequenceKey, final String skSetMethodName) throws Exception {

        if (sequenceKey <= 0) {
            return;
        }

        try {

            Method method = jpubType.getClass().getDeclaredMethod(skSetMethodName, BigDecimal.class);
            method.invoke(jpubType, BigDecimal.valueOf(sequenceKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long executeRecursive(final Exchange exchange, final Connection connection, final Object data, final boolean bShouldInsert, long returnVal) throws SandataRuntimeException {

        try {
            // GEOR-6612
            String timezoneFieldErrMsg = RestUtil.validateRequiredTimezoneName(data);
            if (timezoneFieldErrMsg.length() > 0) {
                throw new SandataRuntimeException(timezoneFieldErrMsg);
            }
            
            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(data);

            Object jpubType = new DataMapper().map(data);

            setSk(jpubType, returnVal, "setStaffSk");

            long result = 0;

            if (bShouldInsert) {

                result = oracleDataService.execute(
                    connection,
                    ConnectionType.COREDATA,
                    oracleMetadata.packageName(),
                    oracleMetadata.insertMethod(),
                    jpubType
                );

            } else {

                // TODO: Need to find a better way of doing this...
                if (data instanceof Staff) {
                    returnVal = ((Staff) data).getStaffSK().intValue();

                    // UPDATE
                    result = oracleDataService.execute(
                            connection,
                            ConnectionType.COREDATA,
                            "PKG_HIST",
                            "updateStaff",
                            jpubType
                    );

                    returnVal = result;

                } else {

                    if (data instanceof StaffContactAddress) {
                        returnVal = ((StaffContactAddress) data).getStaffContactAddressSK().intValue();
                    } else if (data instanceof StaffContactPhone) {
                        returnVal = ((StaffContactPhone) data).getStaffContactPhoneSK().intValue();
                    } else if (data instanceof StaffRate) {
                        returnVal = ((StaffRate) data).getStaffRateSK().intValue();
                    } else if (data instanceof StaffAssociatedRate) {
                        returnVal = ((StaffAssociatedRate) data).getStaffAssociatedRateSK().intValue();
                    }

                    // UPDATE
                    result = oracleDataService.execute(
                            connection,
                            ConnectionType.COREDATA,
                            oracleMetadata.packageName(),
                            oracleMetadata.updateMethod(),
                            jpubType
                    );
                }
            }

            if (result > 0) {

                if (returnVal == -999) {
                    returnVal = result;
                }

                // Check if there are any lists that need to be inserted
                for (Field field : data.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    Object property = field.get(data);
                    if (property != null && property instanceof List) {

                        List list = (List) property;
                        for (Object object : list) {

                            // Try to insert the object!

                            // WARNING: RECURSIVE!!!!
                            long insertResponse = executeRecursive(exchange, connection, object, bShouldInsert, returnVal);
                            if (insertResponse == -1) {
                                if (bShouldInsert) {
                                    throw new SandataRuntimeException(exchange, format("INSERT: Failed: [%s]",
                                        object.getClass().getName()));
                                } else {
                                    throw new SandataRuntimeException(exchange, format("UPDATE: Failed: [%s]",
                                        object.getClass().getName()));
                                }
                            }
                        }
                    }
                }

                // SUCCESS
                return returnVal;

            } // if (result > 0)

            // FAILED
            return -1;
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public OracleDataService getOracleDataService() {
        return oracleDataService;
    }

    public void setOracleDataService(OracleDataService oracleDataService) {
        this.oracleDataService = oracleDataService;
    }

    public void excludedStaff(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) mcl.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }


            Response response = oracleDataService.excludedStaff(
                "T2.STAFF_LAST_NAME",
                "ASC",
                patientId,
                bsnEntId
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void excludedPatients(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            Response response = oracleDataService.excludedPatients(
                format("T1.%s", sortOn),
                direction,
                staffId,
                bsnEntId
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void validatePatientExists(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        try {

            logger.start();

            //dmr--ValidatePatientFilterTyp filterTyp = new ValidatePatientFilterTyp();

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) params.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String bsnEntId = (String) params.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            long result = oracleDataService.validatePatientExists(
                patientId,
                bsnEntId
            );

            exchange.getIn().setBody(result);

            logger.stop();
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }

    public void isPatientExcluded(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        try {

            logger.start();

            //dmr--IsPatientExclFilterTyp filterTyp = new IsPatientExclFilterTyp();

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) params.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String staffId = (String) params.get(1);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) params.get(2);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            long result = oracleDataService.isPatientExcluded(patientId, staffId, bsnEntId);

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void isStaffExcluded(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        try {

            logger.start();

            MessageContentsList params = (MessageContentsList) exchange.getIn().getBody();

            String patientId = (String) params.get(0);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            String staffId = (String) params.get(1);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) params.get(2);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            long result = oracleDataService.isStaffExcluded(patientId, staffId, bsnEntId);

            exchange.getIn().setBody(result);
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void staffScheduleEvents(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            List<String> params = new ArrayList<>();

            StringBuilder filterTyp = new StringBuilder();

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String keywordString = (String) mcl.get(0);
            if (keywordString.length() > 0) {

                String[] keywords = keywordString.split(" ");
                if (keywords.length >= 2) {

                    // Example: Tom Hanks
                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[1].toUpperCase() + "%");

                    // Flip params around
                    // Example: Hanks Tom
                    params.add(keywords[1].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    // NOTE:    If there is a third word or more, it will be ignored!
                    //          Keyword is expected to be a name, first/last.
                    //          Check first/last in any order. For example: Tom Hanks or Hanks Tom
                    filterTyp.append("((UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?) OR " +
                        "(UPPER(T1.STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.STAFF_LAST_NAME) LIKE ?)) AND ");
                } else {

                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    filterTyp.append("(UPPER(T1.STAFF_FIRST_NAME) LIKE ? " +
                        "OR UPPER(T1.STAFF_LAST_NAME) LIKE ?) AND ");
                }
            }

            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);

            Response response = oracleDataService.staffScheduleEvents(
                filterTyp.toString(),
                params,
                page,
                pageSize
            );

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void distinctFirstNames(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);

            Response response = oracleDataService.distinctFirstNames(bsnEntId, page, pageSize);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void distinctLastNames(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String bsnEntId = (String) mcl.get(0);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID is required!");
            }

            int page = (Integer) mcl.get(1);
            int pageSize = (Integer) mcl.get(2);

            Response response = oracleDataService.distinctLastNames(bsnEntId, page, pageSize);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", page);
            exchange.getIn().setHeader("PAGE_SIZE", pageSize);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffRelationship(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String relType = (String) mcl.get(0);
            if (relType == null || relType.length() == 0) {
                throw new SandataRuntimeException(exchange, "Relationship Type (rel_type) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getStaffRelationship(bsnEntId, relType);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffAdmins(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String staffId = (String) exchange.getIn().getHeader("staff_id");

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String staffRelType = (String) exchange.getIn().getHeader("staff_position");
            if (IsNullOrEmpty(staffRelType)) {
                throw new SandataRuntimeException(exchange, "Staff Admin Position (staff_position) is required!");
            }

            Response response = oracleDataService.getStaffAdmins(bsnEntId, staffId, staffRelType);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    //ToDo - To be depracated
    public void getStaffManagers(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getStaffManagers(bsnEntId, staffId);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    //ToDo - To be depracated
    public void getStaffCoordinators(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getStaffCoordinators(bsnEntId, staffId);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    //ToDo - To be depracated
    public void getStaffNurses(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);

            String bsnEntId = (String) mcl.get(1);
            if (IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Response response = oracleDataService.getStaffNurses(bsnEntId, staffId);

            exchange.getIn().setBody(response.getData());
        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void validateStaffId(Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            Boolean result = oracleDataService.validateStaffId(staffId, bsnEntId, logger);
            exchange.getIn().setBody(result);

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffHistoryWithSort(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_CREATE_TMSTP";
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getStaffHistoryWithSort(staffId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffHiringHistoryWithSort(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);
            if (sortOn == null || sortOn.length() == 0) {
                sortOn = "REC_EFF_TMSTP";
            } else {

                if (!sortOn.equals("REC_EFF_TMSTP")) {
                    throw new SandataRuntimeException(exchange, String.format("Order By value (sort_on) is not supported! [%s]", sortOn));
                }
            }

            String direction = (String) mcl.get(3);

            exchange.getIn().setBody(oracleDataService.getStaffHiringHistoryWithSort(staffId, bsnEntId, sortOn, direction));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffEmploymentStatusHistoryWithSort(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String staffId = (String)exchange.getIn().getHeader("staff_id");
            if (StringUtils.isEmpty(staffId)) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String)exchange.getIn().getHeader("bsn_ent_id");
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String)exchange.getIn().getHeader("sort_on");
            String direction = (String)exchange.getIn().getHeader("direction");

            exchange.getIn().setBody(oracleDataService.getStaffEmploymentStatusHistoryWithSort(staffId, bsnEntId, sortOn, direction, logger));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffCompWithSort(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String sortOn = (String) mcl.get(2);

            String direction = (String) mcl.get(3);
            int page = (int) mcl.get(4);
            int pageSize = (int) mcl.get(5);

            exchange.getIn().setBody(oracleDataService.getStaffCompWithSort(staffId, bsnEntId, sortOn, direction, page, pageSize));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffPatientHist(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (staffId == null || staffId.length() == 0) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String patientId = (String) mcl.get(2);
            if (patientId == null || patientId.length() == 0) {
                throw new SandataRuntimeException(exchange, "PatientID (patient_id) is required!");
            }

            exchange.getIn().setBody(oracleDataService.getStaffPatientHist(staffId, patientId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves all StaffTrainingLocation entities for specified parameters.
     *
     * @param exchange Specified Exchange.
     */
    public void getStaffTrngLocationForStaffTrngCode(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngCode = (String) exchange.getIn().getHeader("staff_trng_code");
            if (staffTrngCode == null
                || staffTrngCode.isEmpty()) {
                throw new SandataRuntimeException("Staff Training Code (staff_trng_code) required!");
            }

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getStaffTrngLocationForStaffTrngCode(bsnEntId,
                staffTrngCode,
                page,
                pageSize,
                sortOn,
                direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves all StaffTrainingClassEvent entities for specified parameters.
     *
     * @param exchange Specified Exchange.
     */
    public void getStaffTrngClsEvntForStaffTrngLocName(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngCode = (String) exchange.getIn().getHeader("staff_trng_code");
            if (staffTrngCode == null
                || staffTrngCode.isEmpty()) {
                throw new SandataRuntimeException("Staff Training Code (staff_trng_code) required!");
            }

            String staffTrngLocName = (String) exchange.getIn().getHeader("staff_trng_loc_name");
            if (staffTrngLocName == null
                || staffTrngLocName.isEmpty()) {
                throw new SandataRuntimeException("Staff Training Location Name (staff_trng_loc_name) required!");
            }

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getStaffTrngClsEvntForStaffTrngLocName(bsnEntId,
                staffTrngCode,
                staffTrngLocName,
                page,
                pageSize,
                sortOn,
                direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves all StaffTrainingClassEvent entities for specified parameters.
     *
     * @param exchange Specified Exchange.
     */
    public void getStaffTrngClsEvntByLocation(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }


            String staffTrngLocName = (String) exchange.getIn().getHeader("staff_trng_loc_name");
            if (staffTrngLocName == null
                || staffTrngLocName.isEmpty()) {
                throw new SandataRuntimeException("Staff Training Location Name (staff_trng_loc_name) required!");
            }

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            String sortColumn = "STAFF_TRNG_START_DTIME";


            Response response = oracleDataService.getStaffTrainingClassesByLocation(bsnEntId,
                staffTrngLocName,
                page,
                pageSize,
                sortColumn,
                direction);


            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());


        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    /**
     * Retrieves all FindCompSchedStaffResult containing details of Staff entity for specified parameters.
     *
     * @param exchange Specified Exchange.
     */
    public void findStaffForTrainingCategory(Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            // Extract parameters and null check.
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngCode = (String) exchange.getIn().getHeader("staff_trng_code");
            if (staffTrngCode == null
                || staffTrngCode.isEmpty()) {
                throw new SandataRuntimeException("Staff training category code (staff_trng_code) required!");
            }

            String staffTrngLocName = (String) exchange.getIn().getHeader("staff_trng_loc_name");
            if (staffTrngLocName == null
                || staffTrngLocName.isEmpty()) {
                throw new SandataRuntimeException("Staff training location name (staff_trng_loc_name) required!");
            }

            String staffTrngStartDateTimeString = (String) exchange.getIn().getHeader("staff_trng_start_dtime");
            if (staffTrngStartDateTimeString == null
                || staffTrngStartDateTimeString.isEmpty()) {
                throw new SandataRuntimeException("Staff training start time (staff_trng_start_dtime) required!");
            }

            Date staffTrngStartDateTime = null;
            try {
                staffTrngStartDateTime = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT).parse(staffTrngStartDateTimeString);
            } catch (ParseException e) {
                throw new SandataRuntimeException(String.format("Staff training start time must be in format %s!", DateUtil.SANDATA_UTC_DATE_TIME_FORMAT));
            }

            // Optional parameters.
            EmploymentStatusTypeName employmentStatusTypeName = null;
            String employmentStatusTypeNameString = (String) exchange.getIn().getHeader("employment_status_type_name");
            if (!StringUtil.IsNullOrEmpty(employmentStatusTypeNameString)) {

                try {
                    employmentStatusTypeName = EmploymentStatusTypeName.fromValue(employmentStatusTypeNameString);
                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("Employment Status Type Name (emp_status_type_name) is NOT valid! [%s]", employmentStatusTypeNameString));
                }
            }

            Boolean compliant = (Boolean) exchange.getIn().getHeader("compliant");
            String staffId = (String) exchange.getIn().getHeader("staff_id");
            String firstName = (String) exchange.getIn().getHeader("first_name");
            String lastName = (String) exchange.getIn().getHeader("last_name");

            // Defaulted parameters.
            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.findStaffForTrainingCategory(bsnEntId,
                staffTrngCode,
                staffTrngLocName,
                staffTrngStartDateTime,
                employmentStatusTypeName,
                compliant,
                staffId,
                firstName,
                lastName,
                page,
                pageSize,
                sortOn,
                direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }

    }

    public void insertStaff(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            AdminDataHandler adminDataHandler = new AdminDataHandler(getOracleDataService());

            StaffExt staffExt = exchange.getIn().getBody(StaffExt.class);

            // Insert Coordinator if one was provided
            if (!IsNullOrEmpty(staffExt.getCoordinatorId())) {

                adminDataHandler.insertCoordinator(
                    connection,
                    staffExt.getBusinessEntityID(),
                    staffExt.getStaffID(),
                    staffExt.getCoordinatorId());
            }

            // Insert Nurse if one was provided
            if (!IsNullOrEmpty(staffExt.getNurseId())) {

                adminDataHandler.insertNurse(
                    connection,
                    staffExt.getBusinessEntityID(),
                    staffExt.getStaffID(),
                    staffExt.getNurseId()
                );
            }

            Staff staff = staffExt.getStaff();

            exchange.getIn().setBody(staff);

            long staffSk = insert(exchange);

            Staff staffAfterInsert = getStaffForSK(staffSk);

            int result = oracleDataService.insertAuditEmploymentStatusHistory(
                    staffSk, (String)exchange.getIn().getHeader("SandataGUID"), staffAfterInsert);

            if (result != 1) {
                logger.error(String.format("%s: insertStaff: ERROR: insertAuditEmploymentStatusHistory failed!", getClass().getSimpleName()));
            }

            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    public void updateStaff(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            connection = oracleDataService.getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(false);

            AdminDataHandler adminDataHandler = new AdminDataHandler(getOracleDataService());

            StaffExt staffExt = exchange.getIn().getBody(StaffExt.class);

            List<SequenceKeyValueResult> resultList = adminDataHandler.getAdminIdForStaffId(
                connection,
                staffExt.getBusinessEntityID(),
                staffExt.getStaffID(),
                "Coordinator"
            );

            Staff staffBeforeUpdate = null;
            List<Staff> listActiveStaffByID = (List<Staff>) oracleDataService.getEntitiesForId(
                    connection,
                    DataHelper.houseKeeping("SELECT * FROM %s WHERE STAFF_ID = ? AND BE_ID = ?", "STAFF"),
                    Staff.class.getName(),
                    staffExt.getStaffID(),
                    staffExt.getBusinessEntityID());

            if (listActiveStaffByID.size() > 0) {
                staffBeforeUpdate = listActiveStaffByID.get(0);
            }

            if(staffExt.getStaffEmploymentStatusTypeName() != null && (staffExt.getStaffEmploymentStatusTypeName().equals(EmploymentStatusTypeName.HOLD) || staffExt.getStaffEmploymentStatusTypeName().equals(EmploymentStatusTypeName.TERMINATED) ) ) {
                Date lastDateWorked = oracleDataService.getLastDateWorked(
                        connection,
                        staffExt.getStaffSK().longValue(),
                        staffExt.getStaffID(),
                        staffExt.getBusinessEntityID());
                if(lastDateWorked != null && lastDateWorked.after(staffExt.getStaffEmploymentStatusChangeDate())) {
                    String emp_status_type_name = staffExt.getStaffEmploymentStatusTypeName().name();
                    Date affectiveDate = staffExt.getStaffEmploymentStatusChangeDate();
                    throw new SandataRuntimeException(String.format("Employment Status Type Name (emp_status_type_name) [%s] was changed with an affective date of [%s]; however, this type can not occur after the employees last date worked [%s}.", emp_status_type_name, affectiveDate.toString(), lastDateWorked.toString()));
                }
            }
            // If coordinator is null, then check to see if there is an association and delete it if one exists
            if (IsNullOrEmpty(staffExt.getCoordinatorId())) {
                adminDataHandler.deleteAdminStaffXrefForList(connection, resultList, logger);
            } else {
                boolean bFound = false;
                for (SequenceKeyValueResult skv : resultList) {

                    // The passed in coordinatorId is different
                    if (!skv.getValue().equals(staffExt.getCoordinatorId())) {
                        adminDataHandler.deleteAdminStaffXref(connection, skv, logger);
                    } else {
                        bFound = true;
                    }
                }

                // Coordinator Not found! Insert new record
                if (!bFound) {
                    adminDataHandler.insertCoordinator(
                        connection,
                        staffExt.getBusinessEntityID(),
                        staffExt.getStaffID(),
                        staffExt.getCoordinatorId());
                }
            }

            resultList = adminDataHandler.getAdminIdForStaffId(
                connection,
                staffExt.getBusinessEntityID(),
                staffExt.getStaffID(),
                "Nurse"
            );

            // If nurse is null, then check to see if there is an association and delete it if one exists
            if (IsNullOrEmpty(staffExt.getNurseId())) {
                adminDataHandler.deleteAdminStaffXrefForList(connection, resultList, logger);
            } else {
                boolean bFound = false;
                for (SequenceKeyValueResult skv : resultList) {

                    // The passed in nurseId is different
                    if (!skv.getValue().equals(staffExt.getNurseId())) {
                        adminDataHandler.deleteAdminStaffXref(connection, skv, logger);
                    } else {
                        bFound = true;
                    }
                }

                // Nurse Not found! Insert new record
                if (!bFound) {
                    adminDataHandler.insertNurse(
                        connection,
                        staffExt.getBusinessEntityID(),
                        staffExt.getStaffID(),
                        staffExt.getNurseId()
                    );
                }
            }

            Staff staff = staffExt.getStaff();
            AuditStaff auditStaffBeforeUpdate = mapStaffToAuditStaff(staffBeforeUpdate);

            exchange.getIn().setBody(staff);

            long staffSk = update(exchange);

            Staff staffAfterUpdate = getStaffForSK(staffSk);


            int result = oracleDataService.updateAuditEmploymentStatusHistory(
                    staffSk, (String)exchange.getIn().getHeader("SandataGUID"), staffBeforeUpdate.getStaffEmploymentStatusTypeName(), staffAfterUpdate);

            if (result == -1) {
                logger.info(String.format("%s: insertStaff: updateAuditEmploymentStatusHistory status did not change", getClass().getSimpleName()));
            }
            else if (result != 1) {
                logger.error(String.format("%s: insertStaff: ERROR: updateAuditEmploymentStatusHistory failed!", getClass().getSimpleName()));
            }

            try {
                AuditStaff auditStaffAfterUpdate = mapStaffToAuditStaff(staffAfterUpdate);
                StaffProfileChangedLog staffProfileChangedLog = getStaffProfileChangedLog(
                        exchange,
                        logger,
                        staffAfterUpdate,
                        auditStaffBeforeUpdate,
                        auditStaffAfterUpdate);

                int staffProfileChangedLogResult = oracleDataService.insertStaffProfileChangeLog(staffProfileChangedLog, staff.getBusinessEntityID(),
                        staff.getStaffID());

                if (staffProfileChangedLogResult == -1) {
                    logger.info(String.format("%s: updateStaff: insertStaffProfileChangeLog staff profile did not change", getClass().getSimpleName()));
                } else if (staffProfileChangedLogResult != 1) {
                    logger.error(String.format("%s: updateStaff: ERROR: insertStaffProfileChangeLog failed!", getClass().getSimpleName()));
                }
            } catch (Exception e) {
                logger.error(String.format("%s: updateStaff: ERROR: Error during insert staff profile changes: %s",
                        getClass().getSimpleName(), e.getMessage()));
            }


            connection.commit();

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Add list of staff languages into the database.
     *
     * @param exchange
     */
    public void insertStaffLanguageLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            StaffLanguageItemRequest staffLanguageItemRequest = handleStaffLanguageItemRequest(exchange);

            if (insertStaffLanguageLst(connection, staffLanguageItemRequest)) {
                connection.commit();

                exchange.getIn().setBody(0);
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Update list of staff languages into the database.
     *
     * @param exchange
     */
    public void updateStaffLanguageLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            StaffLanguageItemRequest staffLanguageItemRequest = handleStaffLanguageItemRequest(exchange);

            // Delete all languages first, then insert the new ones
            if (deleteStaffLanguageItemRequest(connection, staffLanguageItemRequest)) {

                if (insertStaffLanguageLst(connection, staffLanguageItemRequest)) {

                    connection.commit();
                }
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    /**
     * Delete list of staff languages into the database.
     *
     * @param exchange
     */
    public void deleteStaffLanguageLst(final Exchange exchange) {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;
        try {

            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);

            StaffLanguageItemRequest staffLanguageItemRequest = handleStaffLanguageItemRequest(exchange);

            // Delete all languages
            if (deleteStaffLanguageItemRequest(connection, staffLanguageItemRequest)) {

                connection.commit();
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private boolean insertStaffLanguageLst(Connection connection, StaffLanguageItemRequest staffLanguageItemRequest)
        throws SandataRuntimeException {

        try {

            oracleDataService.insertStaffLanguageLst(connection,
                staffLanguageItemRequest.getStaffId(),
                staffLanguageItemRequest.getBusinessEntityId(),
                staffLanguageItemRequest.getLanguages());

            return true;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }
    }

    private boolean deleteStaffLanguageItemRequest(Connection connection, StaffLanguageItemRequest staffLanguageItemRequest)
        throws SandataRuntimeException {

        try {

            oracleDataService.deleteStaffLanguageItemRequest(connection,
                staffLanguageItemRequest.getStaffId(),
                staffLanguageItemRequest.getBusinessEntityId());

            return true;

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);
        }

    }

    private StaffLanguageItemRequest handleStaffLanguageItemRequest(Exchange exchange) throws SandataRuntimeException {

        StaffLanguageItemRequest request = new StaffLanguageItemRequest();

        List<String> languages = exchange.getIn().getBody(List.class);
        if (languages == null || languages.size() == 0) {
            throw new SandataRuntimeException("Must provide at least one language to insert/update/delete!");
        }

        String staffId = (String) exchange.getIn().getHeader("staff_id");
        if (IsNullOrEmpty(staffId)) {
            throw new SandataRuntimeException("StaffID (staff_id) is required!");
        }

        String bsnEntID = (String) exchange.getIn().getHeader("bsn_ent_id");
        if (IsNullOrEmpty(bsnEntID)) {
            throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
        }

        request.setLanguages(languages);
        request.setStaffId(staffId);
        request.setBusinessEntityId(bsnEntID);

        return request;
    }

    public void getStaffTrngClsEvntEnrolForClsEvnt(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            if (bsnEntId == null
                || bsnEntId.isEmpty()) {
                throw new SandataRuntimeException("Business entity ID (bsn_ent_id) required!");
            }

            String staffTrngLocName = (String) exchange.getIn().getHeader("staff_trng_loc_name");
            if (staffTrngLocName == null
                || staffTrngLocName.isEmpty()) {
                throw new SandataRuntimeException("Staff training location name (staff_trng_loc_name) required!");
            }

            String staffTrngCode = (String) exchange.getIn().getHeader("staff_trng_code");
            if (staffTrngCode == null
                || staffTrngCode.isEmpty()) {
                throw new SandataRuntimeException("Staff training code (staff_trng_code) required!");
            }

            String staffTrngStartDateTime = (String) exchange.getIn().getHeader("staff_trng_start_dtime");
            if (staffTrngStartDateTime == null
                || staffTrngStartDateTime.isEmpty()) {
                throw new SandataRuntimeException("Staff training start date time (staff_trng_start_dtime) required!");
            }

            String utcFormat = DateUtil.SANDATA_UTC_DATE_TIME_FORMAT;
            String filterStartDateFormat = "yyyy-MM-dd HH:mm:ss";
            try {
                Date startDate = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT).parse(staffTrngStartDateTime);
                staffTrngStartDateTime = new SimpleDateFormat(filterStartDateFormat).format(startDate);
            } catch (ParseException e) {
                throw new SandataRuntimeException(String.format("Staff training start time must be in format %s!", utcFormat));
            }

            Integer page = (Integer) exchange.getIn().getHeader("page");
            Integer pageSize = (Integer) exchange.getIn().getHeader("page_size");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");

            Response response = oracleDataService.getStaffTrngClsEvntEnrolForClsEvnt(bsnEntId,
                staffTrngLocName,
                staffTrngCode,
                staffTrngStartDateTime,
                page,
                pageSize,
                sortOn,
                direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void updateStaffTrngClasEvntEnrolList(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            List<StaffTrainingClassEventEnrollmentExt> staffTrainingClassEventEnrollmentExtList =
                (List<StaffTrainingClassEventEnrollmentExt>) exchange.getIn().getBody();

            if (staffTrainingClassEventEnrollmentExtList == null) {
                throw new SandataRuntimeException("StaffTrainingClassEventEnrollmentExt List required!");
            }

            connection = oracleDataService.getOracleConnection();

            for (StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt : staffTrainingClassEventEnrollmentExtList) {

                InServiceTrainingResult inServiceTrainingResult;
                if (staffTrainingClassEventEnrollmentExt.isStaffTrainingCompletedIndicator()) {
                    inServiceTrainingResult = InServiceTrainingResult.COMPLETED;
                    // Create StaffTrainingClassEventAttendance entity.
                    insertStaffTrngClsEvntAttnd(connection,
                        createStaffTrngClsEvntAttnd(staffTrainingClassEventEnrollmentExt));
                } else if (staffTrainingClassEventEnrollmentExt.isStaffTrainingNoShowIndicator()) {
                    inServiceTrainingResult = InServiceTrainingResult.NO_SHOW;
                } else if (staffTrainingClassEventEnrollmentExt.isStaffTrainingDropIndicator()) {
                    inServiceTrainingResult = InServiceTrainingResult.DROPPED;
                } else {
                    inServiceTrainingResult = InServiceTrainingResult.ENROLLED;
                    // Delete StaffTrainingClassEventAttendance entity.
                    oracleDataService.deleteStaffTrngClsEvntAttndForEnrol(connection,
                        staffTrainingClassEventEnrollmentExt.getStaffTrainingClassEventEnrollmentSK().intValue());
                }

                StaffTraining staffTraining = oracleDataService.getStaffTrngForClsEvntEnrol(connection,
                    staffTrainingClassEventEnrollmentExt.getBusinessEntityID(),
                    staffTrainingClassEventEnrollmentExt.getStaffID(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingStartDatetime(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingLocationName(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingCode());

                // GEOR-6115 Need to update ChangeReasonCode
                staffTraining.setChangeReasonCode(staffTrainingClassEventEnrollmentExt.getChangeReasonCode());
                
                // Update StaffTraining entity result.
                insertOrUpdateStaffTrng(connection, staffTraining, inServiceTrainingResult, false);

                // Update StaffTrainingClassEventEnrollment provided.
                insertOrUpdateStaffTrngClasEvntEnrolExt(connection, staffTrainingClassEventEnrollmentExt, false);

                exchange.getIn().setBody(1);
            }

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private StaffTrainingClassEventAttendance createStaffTrngClsEvntAttnd(StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper(Collections.singletonList("dozer/staffTrainingClassEventAttendance.xml"));
        return dozerBeanMapper.map(staffTrainingClassEventEnrollmentExt, StaffTrainingClassEventAttendance.class);
    }

    private void insertStaffTrngClsEvntAttnd(Connection connection,
                                             StaffTrainingClassEventAttendance staffTrainingClassEventAttendance) {

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffTrainingClassEventAttendance);

        Object jpubType = new DataMapper().map(staffTrainingClassEventAttendance);

        long result = oracleDataService.execute(
            connection,
            ConnectionType.COREDATA,
            oracleMetadata.packageName(),
            oracleMetadata.insertMethod(),
            jpubType
        );

        if (result <= 0) {
            throw new SandataRuntimeException("Failed to insert StaffTrainingClassEventAttendance entity!");
        }
    }

    private void insertOrUpdateStaffTrng(Connection connection,
                                         StaffTraining staffTraining,
                                         InServiceTrainingResult inServiceTrainingResult,
                                         boolean isInsert) {

        staffTraining.setStaffTrainingResultCode(inServiceTrainingResult.toString());

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffTraining);

        Object jpubType = new DataMapper().map(staffTraining);

        String method;
        if (isInsert) {
            method = oracleMetadata.insertMethod();
        } else {
            method = oracleMetadata.updateMethod();
        }

        long result = oracleDataService.execute(
            connection,
            ConnectionType.COREDATA,
            oracleMetadata.packageName(),
            method,
            jpubType
        );

        if (result <= 0) {
            throw new SandataRuntimeException("Failed to insert/update StaffTraining entity!");
        }
    }

    private void insertOrUpdateStaffTrngClasEvntEnrolExt(Connection connection,
                                                         StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt,
                                                         boolean isInsert) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper(Collections.singletonList("dozer/staffTrainingClassEventEnrollmentExt.xml"));
        StaffTrainingClassEventEnrollment staffTrainingClassEventEnrollment = dozerBeanMapper.map(staffTrainingClassEventEnrollmentExt,
            StaffTrainingClassEventEnrollment.class);

        staffTrainingClassEventEnrollment.setStaffTrainingDropIndicator(staffTrainingClassEventEnrollmentExt.isStaffTrainingDropIndicator());

        OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(staffTrainingClassEventEnrollment);

        Object jpubType = new DataMapper().map(staffTrainingClassEventEnrollment);

        String method;
        if (isInsert) {
            method = oracleMetadata.insertMethod();
        } else {
            method = oracleMetadata.updateMethod();
        }

        long result = oracleDataService.execute(
            connection,
            ConnectionType.COREDATA,
            oracleMetadata.packageName(),
            method,
            jpubType
        );

        if (result <= 0) {
            throw new SandataRuntimeException(format("Failed to update StaffTrainingClassEventEnrollment entity with SK %s!",
                staffTrainingClassEventEnrollment.getStaffTrainingClassEventEnrollmentSK()));
        }
    }

    public void getStaffTrngClsEvntEnrolList(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            List<StaffTrainingClassEventEnrollmentExt> staffTrainingClassEventEnrollmentExtList = (List<StaffTrainingClassEventEnrollmentExt>) exchange.getIn().getBody();

            if (staffTrainingClassEventEnrollmentExtList == null) {
                throw new SandataRuntimeException("StaffTrainingClassEventEnrollmentExt List required!");
            }

            for (StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt : staffTrainingClassEventEnrollmentExtList) {

                // Determine if conflicts exist and set flag.
                staffTrainingClassEventEnrollmentExt.setStaffScheduleEventConflictExists(
                    oracleDataService.doesScheduleEventConflictExist(staffTrainingClassEventEnrollmentExt.getBusinessEntityID(),
                        staffTrainingClassEventEnrollmentExt.getStaffID(),
                        staffTrainingClassEventEnrollmentExt.getStaffTrainingLocationName(),
                        staffTrainingClassEventEnrollmentExt.getStaffTrainingCode(),
                        staffTrainingClassEventEnrollmentExt.getStaffTrainingStartDatetime()));
            }

            exchange.getIn().setHeader("TOTAL_ROWS", staffTrainingClassEventEnrollmentExtList.size());
            exchange.getIn().setHeader("PAGE", 1);
            exchange.getIn().setHeader("PAGE_SIZE", staffTrainingClassEventEnrollmentExtList.size());
            exchange.getIn().setBody(staffTrainingClassEventEnrollmentExtList);

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            logger.stop();
        }
    }

    public void insertStaffTrngClsEvntEnrolListConfirm(Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        Connection connection = null;

        try {

            List<StaffTrainingClassEventEnrollmentExt> staffTrainingClassEventEnrollmentExtList =
                (List<StaffTrainingClassEventEnrollmentExt>) exchange.getIn().getBody();

            if (staffTrainingClassEventEnrollmentExtList == null) {
                throw new SandataRuntimeException("StaffTrainingClassEventEnrollmentExt List required!");
            }

            connection = oracleDataService.getOracleConnection();

            for (StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt : staffTrainingClassEventEnrollmentExtList) {
                // Transform StaffTrainingClassEventEnrollmentExt to StaffTrainingClassEventEnrollment and insert
                insertOrUpdateStaffTrngClasEvntEnrolExt(connection, staffTrainingClassEventEnrollmentExt, true);

                // Create StaffTraining entity and insert.
                StaffTraining staffTraining = createStaffTrngForClsEvntEnrol(staffTrainingClassEventEnrollmentExt);
                staffTraining.setStaffTrainingEnrolledDate(staffTrainingClassEventEnrollmentExt.getStaffTrainingEnrollmentDate());
                insertOrUpdateStaffTrng(connection,
                    staffTraining,
                    InServiceTrainingResult.ENROLLED,
                    true);

                // Cancel any ScheduleEvent entities that conflict with class time.
                int numSchedEvntsCancelled = oracleDataService.cancelSchedEvntForStaffTrngClsEvntEnrol(connection,
                    staffTrainingClassEventEnrollmentExt.getBusinessEntityID(),
                    staffTrainingClassEventEnrollmentExt.getStaffID(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingCode(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingLocationName(),
                    staffTrainingClassEventEnrollmentExt.getStaffTrainingStartDatetime());

                logger.info(format("Cancelled %s schedule events for business entity ID %s and staff ID %s on %s.",
                    numSchedEvntsCancelled,
                    staffTrainingClassEventEnrollmentExt.getBusinessEntityID(),
                    staffTrainingClassEventEnrollmentExt.getStaffID(),
                    new SimpleDateFormat("MM/dd/yyyy").format(staffTrainingClassEventEnrollmentExt.getStaffTrainingStartDatetime())));

            }

            exchange.getIn().setBody(1);

        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {

            this.oracleDataService.closeOracleConnection(connection);

            logger.stop();
        }
    }

    private StaffTraining createStaffTrngForClsEvntEnrol(StaffTrainingClassEventEnrollmentExt staffTrainingClassEventEnrollmentExt) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper(Collections.singletonList("dozer/staffTraining.xml"));
        return dozerBeanMapper.map(staffTrainingClassEventEnrollmentExt, StaffTraining.class);
    }

    public void getStaffForSK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            Long staffSK = (Long) exchange.getIn().getHeader("staff_sk");
            if (staffSK == null || staffSK <= 0) {
                throw new SandataRuntimeException("StaffSK (staff_sk) is required!");
            }

            exchange.getIn().setBody(getStaffForSK(staffSK));

        } catch (Exception e) {

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    public void getStaffForPK(final Exchange exchange) throws SandataRuntimeException {

        SandataLogger logger = OracleDataLogger.CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            String staffId = (String) mcl.get(0);
            if (StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException("StaffID (staff_id) is required!");
            }

            String bsnEntId = (String) mcl.get(1);
            if (bsnEntId == null || bsnEntId.length() == 0) {
                throw new SandataRuntimeException("BusinessEntityID (bsn_ent_id) is required!");
            }

            exchange.getIn().setBody(getStaffForPK(staffId, bsnEntId));

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }

    private List<StaffExt> getStaffForPK(String staffId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleDataService().getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT PKG_STAFF_UTIL.LAST_HIRE_DATE(T1.STAFF_ID, T1.BE_ID) AS ORIGINAL_STAFF_HIRE_DATE, T2.VISIT_EVNT_DTIME AS LAST_CALL_DATE, T1.* " +
                    "FROM STAFF T1 " +
                    "    LEFT JOIN (" +
                    "        SELECT A1.VISIT_EVNT_DTIME, A2.BE_ID, A1.STAFF_ID, ROW_NUMBER() OVER(PARTITION BY A1.STAFF_ID, A2.BE_ID ORDER BY A1.VISIT_EVNT_DTIME DESC) AS CALL_DATE_ORDER" +
                    "        FROM VISIT_EVNT A1" +
                    "            JOIN VISIT A2 ON A1.VISIT_SK = A2.VISIT_SK" +
                    "        WHERE A1.VISIT_EVNT_DTIME IS NOT NULL" +
                    "            AND (TO_CHAR(A1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND A1.CURR_REC_IND = 1)" +
                    "    ) T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID AND T2.CALL_DATE_ORDER = 1" +
                    "WHERE T1.STAFF_ID = ? AND T1.BE_ID = ?" +
                    "    AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            List<StaffExt> staffExtCollection = new ArrayList<>();

            while (resultSet.next()) {

                Staff staff = (Staff)new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.Staff", 2);
                StaffExt staffExt = getRelatedStaffData(connection, staff);

                Timestamp originalHireDate = resultSet.getTimestamp("ORIGINAL_STAFF_HIRE_DATE");
                if (originalHireDate != null) {
                    staffExt.setOriginalStaffHireDate(new Date(originalHireDate.getTime()));
                }
                
                Timestamp lastCallDate = resultSet.getTimestamp("LAST_CALL_DATE");
                if (lastCallDate != null) {
                    staffExt.setLastCallDate(new Date(lastCallDate.getTime()));
                }

                staffExtCollection.add(staffExt);
            }

            return staffExtCollection;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);
        }
    }
    
    private StaffExt getStaffForSK(long staffSK) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getOracleDataService().getConnectionPoolDataService().getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT PKG_STAFF_UTIL.HIRE_DATE_BY_SK(T1.STAFF_SK) AS ORIGINAL_STAFF_HIRE_DATE, T2.VISIT_EVNT_DTIME AS LAST_CALL_DATE, " +
                    "T1.* FROM STAFF T1 " +
                    "    LEFT JOIN (" +
                    "        SELECT A1.VISIT_EVNT_DTIME, A2.BE_ID, A1.STAFF_ID, " +
                    "            ROW_NUMBER() OVER(PARTITION BY A1.STAFF_ID, A2.BE_ID ORDER BY A1.VISIT_EVNT_DTIME DESC) AS CALL_DATE_ORDER" +
                    "        FROM VISIT_EVNT A1" +
                    "            JOIN VISIT A2 ON A1.VISIT_SK = A2.VISIT_SK" +
                    "        WHERE A1.VISIT_EVNT_DTIME IS NOT NULL" +
                    "            AND (TO_CHAR(A1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND A1.CURR_REC_IND = 1)" +
                    "    ) T2 ON T1.BE_ID = T2.BE_ID AND T1.STAFF_ID = T2.STAFF_ID AND T2.CALL_DATE_ORDER = 1" +
                    "WHERE STAFF_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, staffSK);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Staff staff = (Staff) new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.Staff", 2);
                StaffExt staffExt = getRelatedStaffData(connection, staff);
                
                Timestamp originalStaffHireDate = resultSet.getTimestamp("ORIGINAL_STAFF_HIRE_DATE");
                if (originalStaffHireDate != null) {
                    staffExt.setOriginalStaffHireDate(new Date(originalStaffHireDate.getTime()));
                }
                
                Timestamp lastCallDate = resultSet.getTimestamp("LAST_CALL_DATE");
                if (lastCallDate != null) {
                    staffExt.setLastCallDate(new Date(lastCallDate.getTime()));
                }

                return staffExt;
            }

            return null;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.oracleDataService.closeOracleConnection(connection);
        }
    }

    /**
     * Gets all active Administrative Staffs for a specified Business Entity ID
     *
     * @param exchange the exchange
     */
    public void getAdminStaffs(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {

            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            int index = 0;
            String bsnEntId = (String) mcl.get(index++);
            if (StringUtils.isEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            int page = (Integer) mcl.get(index++);
            if (page <= 0) {
                page = 1;
            }

            int pageSize = (Integer) mcl.get(index++);
            if (pageSize <= 0) {
                pageSize = 10;
            }

            String sortOn = (String) mcl.get(index++);

            // Default
            String orderByColumn = "ADMIN_STAFF_FIRST_NAME";
            switch (sortOn) {
                case "AdministrativeStaffID":
                    orderByColumn = "ADMIN_STAFF_ID";
                    break;
                case "AdministrativeStaffFirstName":
                    orderByColumn = "ADMIN_STAFF_FIRST_NAME";
                    break;
                case "AdministrativeStaffLastName":
                    orderByColumn = "ADMIN_STAFF_LAST_NAME";
                    break;
            }

            String direction = (String) mcl.get(index++);
            if (StringUtils.isEmpty(direction)) {
                direction = "ASC";
            }

            Response response = oracleDataService.getAdminStaffs(bsnEntId, page, pageSize, orderByColumn, direction);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: getAdminStaffs: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }

    /**
     * Returns a list of categories with category hours, and number of enrolled hours by staffId
     *
     * @param exchange
     */
    public void getTrainingCategoryTotalHours(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);

        logger.start();

        try {
            MessageContentsList mcl = (MessageContentsList) exchange.getIn().getBody();

            int index = 0;
            String bsnEntId = (String) mcl.get(index++);
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException(exchange, "BusinessEntityID (bsn_ent_id) is required!");
            }

            String staffId = (String) mcl.get(index++);
            if (StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException(exchange, "StaffID (staff_id) is required!");
            }

            Response response = oracleDataService.getTrainingCategoryTotalHours(bsnEntId, staffId);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: getAdminStaffs: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);

        } finally {
            logger.stop();
        }
    }
    
    /**
     * seperate a date range into every day
     * @param exchange
     */
    public void insertStaffSpecificAvail(final Exchange exchange) {
        Connection connection = null;
        
        try {
            StaffAvailabilitySpecific staffAvailSpecific = (StaffAvailabilitySpecific) exchange.getIn().getBody();
            
            if (staffAvailSpecific.getAvailabilityStartDate() == null || staffAvailSpecific.getAvailabilityEndDate() == null
                    || staffAvailSpecific.getAvailabilityStartTime() == null || staffAvailSpecific.getAvailabilityEndTime() == null) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability must has Start Date and End Date, Start Hour and End Hour");
            }
            
            if (staffAvailSpecific.getAvailabilityStartDate().after(staffAvailSpecific.getAvailabilityEndDate())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Start Date must before or equal End Date");
            }
            
            if (staffAvailSpecific.getAvailabilityStartTime().isAfter(staffAvailSpecific.getAvailabilityEndTime())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Start Time must before or equal End Time");
            }
            
            if (staffAvailSpecific.getTimezoneName() == null || !Arrays.asList(TimeZone.getAvailableIDs()).contains(staffAvailSpecific.getTimezoneName())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Timezone must be valid and not null");
            }
            
            List<StaffAvailability> listStaffAvail = getListSpecificAvailability(staffAvailSpecific);
            
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            
            long result = 0;
            for (StaffAvailability staffAvail : listStaffAvail) {
                result = executeRecursive(exchange, connection, staffAvail, true, -999);
                if (result < 0) {
                    throw new SandataRuntimeException(exchange, "Insert StaffAvailability was not successful!");
                }
            }
            exchange.getIn().setBody(result);
            connection.commit();
            
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            this.oracleDataService.closeOracleConnection(connection);
        }
    }
    
    /**
     * seperate a date range into every day
     * @param exchange
     */
    public void updateStaffSpecificAvail(final Exchange exchange) {
        Connection connection = null;
        
        try {
            StaffAvailabilitySpecific staffAvailSpecific = (StaffAvailabilitySpecific) exchange.getIn().getBody();
            if (staffAvailSpecific.getAvailabilityStartDate() == null || staffAvailSpecific.getAvailabilityEndDate() == null
                    || staffAvailSpecific.getAvailabilityStartTime() == null || staffAvailSpecific.getAvailabilityEndTime() == null) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability must has Start Date and End Date, Start Hour and End Hour");
            }
            
            if (staffAvailSpecific.getAvailabilityStartDate().after(staffAvailSpecific.getAvailabilityEndDate())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Start Date must before or equal End Date");
            }
            
            if (staffAvailSpecific.getAvailabilityStartTime().isAfter(staffAvailSpecific.getAvailabilityEndTime())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Start Time must before or equal End Time");
            }
            
            if (staffAvailSpecific.getTimezoneName() == null || !Arrays.asList(TimeZone.getAvailableIDs()).contains(staffAvailSpecific.getTimezoneName())) {
                throw new SandataRuntimeException(exchange, "Staff Specific Availability Timezone must be valid and not null");
            }
            
            List<StaffAvailability> listStaffAvail = getListSpecificAvailability(staffAvailSpecific);
            
            connection = oracleDataService.getOracleConnection();
            connection.setAutoCommit(false);
            
            long result = 0;
            for (StaffAvailability staffAvail : listStaffAvail) {
                if (staffAvail.getStaffAvailabilitySK() != null && staffAvail.getStaffAvailabilitySK().longValue() > 0) {
                    result = executeRecursive(exchange, connection, staffAvail, false, -999);
                } else {
                    result = executeRecursive(exchange, connection, staffAvail, true, -999);
                }
                
                if (result < 0) {
                    throw new SandataRuntimeException(exchange, "Insert StaffAvailability was not successful!");
                }
            }
            exchange.getIn().setBody(result);
            connection.commit();
            
        } catch (Exception e) {

            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        } finally {
            this.oracleDataService.closeOracleConnection(connection);
        }
    }
    
    /**
     * get the Specific Available Time for specifc Staff and Business Entity
     * @param exchange
     */
    public void getStaffSpecificAvail(final Exchange exchange) {
        
        try {
            String staffId = (String) exchange.getIn().getHeader("staff_id");
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            String fromDateTimeInUTCString = (String) exchange.getIn().getHeader("from_date_time");
            String toDateTimeInUTCString = (String) exchange.getIn().getHeader("to_date_time");
            Boolean isAvailable = (Boolean) exchange.getIn().getHeader("is_available");
            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            String direction = (String) exchange.getIn().getHeader("direction");
            int page = (int) exchange.getIn().getHeader("page");
            int pageSize = (int) exchange.getIn().getHeader("page_size");
            
            StringBuilder filterConditions = new StringBuilder();
            List<Object> paramsConditions = new ArrayList<>();
            
            if (StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException("Staff ID (staff_id) is required!");
            }
            
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) is required!");
            }
            
            if (isAvailable != null) {
                filterConditions.append("(T1.STAFF_IS_AVAILABLE_IND = ?) AND ");
                paramsConditions.add(isAvailable);
            }
            
            if (!StringUtil.IsNullOrEmpty(fromDateTimeInUTCString) &&  !StringUtil.IsNullOrEmpty(toDateTimeInUTCString)) {
                String fromDateTimeOracleFormat = DateUtil.convertStringUTCtoStringDateTime(fromDateTimeInUTCString, "from_date_time");
                String toDateTimeOracleFormat = DateUtil.convertStringUTCtoStringDateTime(toDateTimeInUTCString, "to_date_time");
                
                filterConditions.append("(T1.AVAIL_START_HOUR BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND TO_DATE(?, 'YYYY-MM-DD HH24:MI')) AND ");
                paramsConditions.add(fromDateTimeOracleFormat);
                paramsConditions.add(toDateTimeOracleFormat);
            } else if (!StringUtil.IsNullOrEmpty(fromDateTimeInUTCString) &&  StringUtil.IsNullOrEmpty(toDateTimeInUTCString)) {
                throw new SandataRuntimeException("Staff Available' Date To (to_date_time) is required as (from_date_time) is provided!");
            } else if (StringUtil.IsNullOrEmpty(fromDateTimeInUTCString) &&  !StringUtil.IsNullOrEmpty(toDateTimeInUTCString)) {
                throw new SandataRuntimeException("Staff Available' Date From (from_date_time) is required as (to_date_time) is provided!");
            }
            
            Response response = oracleDataService.getStaffSpecificAvail(
                    staffId,
                    bsnEntId,
                    filterConditions.toString(),
                    paramsConditions,
                    sortOn,
                    direction,
                    page,
                    pageSize);
            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("PAGE", response.getPage());
            exchange.getIn().setHeader("PAGE_SIZE", response.getPageSize());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());
            exchange.getIn().setBody(response.getData());
            
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }
    
    /**
     * get the General Available Time for specifc Staff and Business Entity
     * 
     * @param exchange
     */
    public void getStaffGeneralAvail(final Exchange exchange) {
        
        try {
            String staffId = (String) exchange.getIn().getHeader("staff_id");
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            
            if (StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException("Staff ID (staff_id) is required!");
            }
            
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) is required!");
            }
            
            String sql = "SELECT * FROM STAFF_AVAIL WHERE AVAIL_DAY IS NOT NULL AND STAFF_ID = ? AND BE_ID = ?";
            
            exchange.getIn().setBody(oracleDataService.getEntitiesForId(sql, "com.sandata.lab.data.model.dl.model.StaffAvailability", staffId, bsnEntId));
            
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(exchange, errMsg, e);
        }
    }
    
    private List<StaffAvailability> getListSpecificAvailability(StaffAvailabilitySpecific staffAvailSpecific) {
        List<StaffAvailability> listStaffAvail = new ArrayList<>();
        
        String timezoneName = staffAvailSpecific.getTimezoneName();
        Date startDateInTargetTimezone = DateUtil.convertUTCToTargetTimeZone(
                staffAvailSpecific.getAvailabilityStartDate(), timezoneName);
        Date endDateInTargetTimezone = DateUtil.convertUTCToTargetTimeZone(
                staffAvailSpecific.getAvailabilityEndDate(), timezoneName);
        
        Calendar currentAvailDateCal = Calendar.getInstance();
        currentAvailDateCal.setTime(startDateInTargetTimezone);
        
        Calendar endDateCal = Calendar.getInstance();
        endDateCal.setTime(endDateInTargetTimezone);
        int i = 0;
        while (!currentAvailDateCal.after(endDateCal)) {
            StaffAvailability staffAvail = new StaffAvailability();
            BeanUtils.copyProperties(staffAvailSpecific, staffAvail);
            
            // more than a specific avail day
            if (i > 0) {
                staffAvail.setStaffAvailabilitySK(null);
            }
            staffAvail.setAvailabilityStartHour(
                    DateUtil.convertFromTimeZoneToUTC(
                            DateUtil.combineDateAndTime(currentAvailDateCal.getTime(), staffAvailSpecific.getAvailabilityStartTime()), 
                            timezoneName)
            );
            staffAvail.setAvailabilityEndHour(
                    DateUtil.convertFromTimeZoneToUTC(
                            DateUtil.combineDateAndTime(currentAvailDateCal.getTime(), staffAvailSpecific.getAvailabilityEndTime()), 
                            timezoneName)
            );
            
            listStaffAvail.add(staffAvail);
            currentAvailDateCal.add(Calendar.DATE, 1);
            i++;
        }
        
        
        return listStaffAvail;
    }
    
    public void validateExistStaffInformation(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();

        try {
            String bsnEntId = (String) exchange.getIn().getHeader("bsn_ent_id");
            Long editingStaffSK = (Long) exchange.getIn().getHeader("editing_staff_sk");
            String ssn = (String) exchange.getIn().getHeader("ssn");
            String tin = (String) exchange.getIn().getHeader("tin");
            String email = (String) exchange.getIn().getHeader("email");
            String lastName = (String) exchange.getIn().getHeader("last_name");
            String firstName = (String) exchange.getIn().getHeader("first_name");
            String phone = (String) exchange.getIn().getHeader("phone");
            
            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) must be provided!");
            }
            
            if (!StringUtil.IsNullOrEmpty(lastName) && StringUtil.IsNullOrEmpty(firstName)) {
                throw new SandataRuntimeException("Since last name (last_name) is provided, first name (first_name) must be provided!");
            }
            if (StringUtil.IsNullOrEmpty(lastName) && !StringUtil.IsNullOrEmpty(firstName)) {
                throw new SandataRuntimeException("Since first name (first_name) is provided, last name (last_name) must be provided!");
            }
            
            exchange.getIn().setBody(oracleDataService.validateExistStaffInformation(
                    bsnEntId,
                    editingStaffSK,
                    ssn,
                    tin,
                    email,
                    lastName,
                    firstName,
                    phone));
            
        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {

            logger.stop();
        }
    }

    public void getStaffProfileChangesHistory(final Exchange exchange) {
        SandataLogger logger = CreateLogger(exchange);
        logger.start();
        try {
            String bsnEntId = exchange.getIn().getHeader("bsn_ent_id", String.class);
            String staffId = exchange.getIn().getHeader("staff_id", String.class);

            if (StringUtil.IsNullOrEmpty(bsnEntId)) {
                throw new SandataRuntimeException("Business Entity ID (bsn_ent_id) must be provided!");
            }

            if (StringUtil.IsNullOrEmpty(staffId)) {
                throw new SandataRuntimeException("Staff ID (staff_id) must be provided!");
            }

            String sortOn = (String) exchange.getIn().getHeader("sort_on");
            if (StringUtils.isEmpty(sortOn)) {
                sortOn = "ChangedOn";
            }

            String direction = (String) exchange.getIn().getHeader("direction");
            if (StringUtils.isEmpty(direction)) {
                direction = "DESC";
            }

            Response response = oracleDataService.getStaffProfileChangesHistory(bsnEntId, staffId, sortOn, direction);

            exchange.getIn().setHeader("TOTAL_ROWS", response.getTotalRows());
            exchange.getIn().setHeader("ORDER_BY_COLUMN", response.getOrderByColumn());
            exchange.getIn().setHeader("ORDER_BY_DIRECTION", response.getOrderByDirection());

            exchange.getIn().setBody(response.getData());

        } catch (Exception e) {
            String errMsg = format("%s: %s", getClass().getName(), e.getMessage());
            throw new SandataRuntimeException(errMsg, e);

        } finally {
            logger.stop();
        }
    }
    
    public AuditStaff mapStaffToAuditStaff(Staff staff) {
    	AuditStaff auditStaff = new AuditStaff();
    	BeanUtils.copyProperties(staff, auditStaff);
    	return auditStaff;
    }

    public StaffProfileChangedLog getStaffProfileChangedLog(final Exchange exchange,
                                                            SandataLogger logger,
                                                            Staff staffAfterUpdate,
                                                            AuditStaff auditStaffBeforeUpdate,
                                                            AuditStaff auditStaffAfterUpate) {

        Date now = new Date();
        StaffProfileChangedLog changedLog = new StaffProfileChangedLog();
        changedLog.setUserGuid(exchange.getIn().getHeader("SandataGUID", String.class));
        changedLog.setChangedBy(staffAfterUpdate.getRecordUpdatedBy());
        changedLog.setNotes(staffAfterUpdate.getChangeReasonMemo());
        changedLog.setReasonCode(staffAfterUpdate.getChangeReasonCode());
        changedLog.setChangedOn(now);

        List<FieldChangedLog> fieldChangedLogs = buildFieldChangedLogs(auditStaffBeforeUpdate, auditStaffAfterUpate);

        //There is an issue on QA with a Task list that more than 1000 character length, so ignore the value more than 1000 characters
        List<FieldChangedLog> filteredFieldChangedLogs = new ArrayList<>();
        for(FieldChangedLog fieldChangedLog : fieldChangedLogs) {
            if (String.valueOf(fieldChangedLog.getChangedFrom()).length() <= 1000
                    && String.valueOf(fieldChangedLog.getChangedTo()).length() <= 1000) {
                filteredFieldChangedLogs.add(fieldChangedLog);
            } else {
                logger.info(String.format("%s: - Staff profile field has more than 1000 characters: Field name: %s, Old value: %s, New value: %s",
                        getClass().getSimpleName(), fieldChangedLog.getChangedField(),
                        fieldChangedLog.getChangedFrom(), fieldChangedLog.getChangedTo()));
            }
        }

        changedLog.getChangedFields().addAll(filteredFieldChangedLogs);


        return changedLog;
    }

    private List<FieldChangedLog> buildFieldChangedLogs(AuditStaff oldAuditStaff,
                                                        AuditStaff newAuditStaff) {
        List<FieldChangedLog> changedFields = new ArrayList<>();

        if (!equalsObject(oldAuditStaff.getStaffFirstName(), newAuditStaff.getStaffFirstName())) {
            changedFields.add(buildFieldChangedLog("StaffFirstName", oldAuditStaff.getStaffFirstName(),
                    newAuditStaff.getStaffFirstName()));
        }

        if (!equalsObject(oldAuditStaff.getStaffLastName(), newAuditStaff.getStaffLastName())) {
            changedFields.add(buildFieldChangedLog("StaffLastName", oldAuditStaff.getStaffLastName(),
                    newAuditStaff.getStaffLastName()));
        }

        if (!equalsObject(oldAuditStaff.getStaffMiddleName(), newAuditStaff.getStaffMiddleName())) {
            changedFields.add(buildFieldChangedLog("StaffMiddleName", oldAuditStaff.getStaffMiddleName(),
                    newAuditStaff.getStaffMiddleName()));
        }

        if (!equalsObject(oldAuditStaff.getStaffDateOfBirth(), newAuditStaff.getStaffDateOfBirth())) {
            changedFields.add(buildFieldChangedLog("StaffDateOfBirth",
                    parseDateToString(oldAuditStaff.getStaffDateOfBirth()),
                    parseDateToString(newAuditStaff.getStaffDateOfBirth())));
        }

        if (!equalsObject(oldAuditStaff.getStaffPositionName(), newAuditStaff.getStaffPositionName())) {
            changedFields.add(buildFieldChangedLog("StaffPositionName",
                    oldAuditStaff.getStaffPositionName() == null ? null : oldAuditStaff.getStaffPositionName().value(),
                    newAuditStaff.getStaffPositionName() == null ? null : newAuditStaff.getStaffPositionName().value()));
        }

        if (!equalsObject(oldAuditStaff.getStaffEmploymentStatusTypeName(), newAuditStaff.getStaffEmploymentStatusTypeName())) {
            changedFields.add(buildFieldChangedLog("StaffEmploymentStatusTypeName",
                    oldAuditStaff.getStaffEmploymentStatusTypeName() == null ? null : oldAuditStaff.getStaffEmploymentStatusTypeName().value(),
                    newAuditStaff.getStaffEmploymentStatusTypeName() == null ? null : newAuditStaff.getStaffEmploymentStatusTypeName().value()));
        }

        if (!equalsObject(oldAuditStaff.getStaffEmploymentStatusChangeDate(), newAuditStaff.getStaffEmploymentStatusChangeDate())) {
            changedFields.add(buildFieldChangedLog("StaffEmploymentStatusChangeDate",
                    parseDateToString(oldAuditStaff.getStaffEmploymentStatusChangeDate()),
                    parseDateToString(newAuditStaff.getStaffEmploymentStatusChangeDate())));
        }

        if (!equalsObject(oldAuditStaff.getStaffHireDate(), newAuditStaff.getStaffHireDate())) {
            changedFields.add(buildFieldChangedLog("StaffHireDate", parseDateToString(oldAuditStaff.getStaffHireDate()),
                    parseDateToString(newAuditStaff.getStaffHireDate())));
        }

        if (!equalsObject(oldAuditStaff.getStaffTaxpayerIdentificationNumberQualifier(), newAuditStaff.getStaffTaxpayerIdentificationNumberQualifier())) {
            changedFields.add(buildFieldChangedLog("StaffTaxpayerIdentificationNumberQualifier",
                    oldAuditStaff.getStaffTaxpayerIdentificationNumberQualifier() == null ? null : oldAuditStaff.getStaffTaxpayerIdentificationNumberQualifier().value(),
                    newAuditStaff.getStaffTaxpayerIdentificationNumberQualifier() == null ? null : newAuditStaff.getStaffTaxpayerIdentificationNumberQualifier().value()));
        }

        if (!equalsObject(oldAuditStaff.getStaffTaxpayerIdentificationNumber(), newAuditStaff.getStaffTaxpayerIdentificationNumber())) {
            changedFields.add(buildFieldChangedLog("StaffTaxpayerIdentificationNumber",
                    oldAuditStaff.getStaffTaxpayerIdentificationNumber(),
                    newAuditStaff.getStaffTaxpayerIdentificationNumber()));
        }

        if (!equalsObject(oldAuditStaff.getStaffEmail(), newAuditStaff.getStaffEmail())) {
            changedFields.add(buildFieldChangedLog("StaffEmail",
                    oldAuditStaff.getStaffEmail(), newAuditStaff.getStaffEmail()));
        }

        if (!equalsObject(oldAuditStaff.getStaffMaritalStatusName(), newAuditStaff.getStaffMaritalStatusName())) {
            changedFields.add(buildFieldChangedLog("StaffMaritalStatusName",
                    oldAuditStaff.getStaffMaritalStatusName() == null ? null : oldAuditStaff.getStaffMaritalStatusName().value(),
                    newAuditStaff.getStaffMaritalStatusName() == null ? null : newAuditStaff.getStaffMaritalStatusName().value()));
        }

        return changedFields;
    }

    private String parseDateToString(Date date) {

        if (date == null) {
            return null;
        }

        DateFormat format = new SimpleDateFormat(DateUtil.SANDATA_UTC_DATE_TIME_FORMAT, Locale.ENGLISH);
        return format.format(date);
    }
    private boolean equalsObject(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        }
        if (object1 != null && object2 == null) {
            return false;
        }
        return object2.equals(object1);
    }

    private FieldChangedLog buildFieldChangedLog(String changedField, String changedFrom, String changedTo) {
        FieldChangedLog fieldChangedLog = new FieldChangedLog();
        fieldChangedLog.setChangedField(changedField);
        fieldChangedLog.setChangedFrom(changedFrom);
        fieldChangedLog.setChangedTo(changedTo);
        return fieldChangedLog;
    }
}
