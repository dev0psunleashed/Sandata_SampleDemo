package com.sandata.lab.exports.evv.impl;

import com.google.gson.Gson;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;

import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.exports.evv.api.EvvExportDataService;
import com.sandata.lab.exports.evv.app.AppContext;
import com.sandata.lab.exports.evv.model.*;
import com.sandata.lab.exports.evv.model.request.UploadScheduleRequest;
import com.sandata.lab.exports.evv.utils.Log;
import com.sandata.lab.exports.evv.utils.data.DataMapper;
import com.sandata.lab.logger.api.LoggerService;

import oracle.jdbc.OracleTypes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 10/8/2016
 * Time: 8:12 AM
 */
public class EvvExportRepository implements EvvExportDataService {
    //private static final String EXPORTBE_ID="1";
    private static final String EXPORTBE_ID="13";
    //private CredentialHelper credentialHelper;
    private Date lastExport = null;
    private List<AccountInformation> accountInformationList;
    private Hashtable<String, AccountInformation> accountsForExport;
    private final String SQL_STAFF_MAPPING = "'SANDATA.ONE' AS VendorCode, " +
            "%s AS EMP_SSN, " +
            "S1.STAFF_ID AS EmployeeID, " +
            "S1.STAFF_LAST_NAME AS LastName, " +
            "S1.STAFF_FIRST_NAME AS FirstName, " +
            "S1.STAFF_EMPLT_STATUS_TYP_NAME AS Status, " +
            "S1.STAFF_MIDDLE_NAME AS MiddleName, " +
            "S1.STAFF_TEAM AS Department, " +
            "S1.STAFF_EMPLT_CLS_NAME AS EmpType, " +
            "S2.STAFF_ADDR1 AS Address1, " +
            "S2.STAFF_ADDR2 || ' ' || S2.STAFF_APT_NUM AS Address2, " +
            "S2.STAFF_CITY AS City, " +
            "S2.STAFF_STATE AS State, " +
            "CONCAT(S2.STAFF_PSTL_CODE, S2.STAFF_ZIP4) AS ZipCode, " +
            "S3.STAFF_PHONE AS EmpPhone, " +
            "NULL AS EmpPhoneAlt1, " +
            "NULL AS EmpPhoneAlt2, " +
            "S1.STAFF_GENDER_TYP_NAME AS Sex, " +
            "S1.STAFF_DOB AS DOB, " +
            "S1.STAFF_POSITION_NAME AS Discipline, " +
            "S4.STAFF_RATE_AMT AS PayRate, " +
            "NULL AS GPSPhone, " +
            "S1.STAFF_NPI AS EmployeeIDCustom1, " +
            "S1.STAFF_API AS EmployeeIDCustom2 ";
    private final String SQL_ACTIVITY_MAPPING = "'SANDATA.ONE' AS VendorCode, " +
            "A1.PT_ID AS ASGN_RecipientID, " +
            "%s AS ASGN_EMP_SSN, " +  /*must be the vv_id*/
            "A1.SCHED_EVNT_SK AS ActivityTblID," +
            "A1.SCHED_EVNT_START_DTIME AS ActivityDate, " +
            "A1.SCHED_EVNT_START_DTIME AS StartTime, " +
            "A1.SCHED_EVNT_END_DTIME AS EndTime, " +
            "A1.SCHED_EVNT_TOTAL_HRS AS ElapsedTime, " +
            "A1.STAFF_ID AS EmployeeID, " +
            /*"'' AS ARNumber, " + */
            /*"A1.SCHED_EVNT_PAY_RATE AS PayRate, " +
            "A1.SCHED_EVNT_BILL_RATE AS BillRate, " + */
            "'0' AS SchedulerFlag, " +
            /*"'0' AS DutyFree, " + */

            "A1.SCHED_EVNT_STATUS AS Cancelled, " +
            //"'' AS Weekend, " +
            "S1.STAFF_POSITION_NAME AS Discipline, " +
            /*"'' AS Service, " +
            "'' AS Contract, " +
            "'' AS Branch, " +
            "'' AS VisitType, " +
            "'' AS LiveInCase, " +
            "'' AS OTABHours, " +
            "'' AS OTABCode, " +
            "'' AS OTABApprover, " +
            "'' AS ProcedureCode, " +
            "'' AS CaseNumber, " +
            "'' AS caseSequence, " +*/
            "A1.TZ_NAME AS TimeZone, A1.REC_TERM_TMSTP AS deleteDate ";
            /*"'' AS BillCode, " +
            "'' AS ProcCodeQualifier, " +
            "'' AS Modifier1, " +
            "'' AS Modifier2, " +
            "'' AS Modifier3, " +
            "'' AS Modifier4 ";*/
    private final String SQL_RECIPIENT_MAPPING = "'SANDATA.ONE' AS VendorCode, " +
            "P1.PT_ID AS RecipientID, " +
            "P1.PT_LAST_NAME AS LastName, " +
            "P1.PT_FIRST_NAME AS FirstName, " +
            "P1.PT_DISCHARGE_DATE AS DischargeDate, " +
            "P2.PT_ADDR1 AS Address1, " +
            "P2.PT_ADDR2 || ' ' || P2.PT_APT_NUM AS Address2, " +
                    "P2.PT_CITY AS City, " +
                    "P2.PT_STATE AS State, " +
                    "P2.PT_PSTL_CODE AS ZipCode, " +
                    "P1.PT_TIN AS RecipientSSN, " +
                    /*" AS Contract, " +
                    " AS BillRate, " +
                    " AS Service, " +
                    " AS CaseNumber, " +
                    " AS CaseSequence, " +
                    " AS ProgramCode, " +*/
                    "P1.PT_MRN AS MedicalRecordNumber, " +
                    /*" AS ARNumber, " +
                    " AS Team, " +
                    " AS Branch," +*/
                    "P2.PT_BOROUGH AS Borough, " +
                    /*" AS Priority, " +
                    " AS Area, " +
                    " AS Weekend, " +
                    " AS CompanyNumber, " +*/
                    "P1.PT_GENDER_TYP_NAME AS Sex, " +
                    "P1.PT_DOB AS DOB, " +
                    "P1.TZ_NAME AS TimeZone, " +
                    "P1.PT_OTHER_ID AS RecipientIDCustom1, " +
                    "P1.PT_MEDICAID_ID AS RecipientIDCustom2, P3.PT_PHONE AS PhoneNbr ";

       private final String SQL_PHONENBR_MAPPING = "PT_ID AS RecipientID, " +
               "PT_PHONE AS PhoneNbr, " +
               "'SANDATA.ONE' AS VendorCode ";



    private LoggerService loggerService;
    private ConnectionPoolDataService connectionPoolDataService;
    private Log log = new Log();
    @Override
    public void getSchedules(Exchange exchange) throws SandataRuntimeException {

    }

    @Override
    public void getStaff(Exchange exchange) throws SandataRuntimeException {

    }

    @Override
    public void getPatient(Exchange exchange) throws SandataRuntimeException {

    }

    @Override
    public void getUploadSchedules(Exchange exchange) throws SandataRuntimeException {
        Message in = exchange.getIn();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(in.getBody()!= null && in.getBody() instanceof UploadScheduleRequest) {
            UploadScheduleRequest uploadScheduleRequest = in.getBody(UploadScheduleRequest.class);
            String bsnEntId = uploadScheduleRequest.getBsnEntId();
            if (bsnEntId == null || bsnEntId.isEmpty()) {
                String santraxAgencyId = uploadScheduleRequest.getSantraxId();
                if (santraxAgencyId == null || santraxAgencyId.isEmpty()) {
                    throw new SandataRuntimeException("SANTRAX AGENCY ID OR BSN_ENT_ID MUST BE PRESENT!");
                }
                bsnEntId = getBsnEntityBySantraxAgencyId(santraxAgencyId);
                uploadScheduleRequest.setBsnEntId(bsnEntId);
            }
            String uuid = UUID.randomUUID().toString();
            uploadScheduleRequest.setUuid(uuid);
            Date newExportDate = new Date();
            UploadSchedules uploadSchedules = getUploadSchedules(uploadScheduleRequest);
            getAccountsForExport().remove(bsnEntId);
            String dateString = sdf.format(newExportDate);
            setLastExportForBsnEntityId(bsnEntId, dateString);

            in.setBody(uploadSchedules);
        }
        else {
            throw new SandataRuntimeException("Missing UploadScheduleRequest!");
        }

    }

    private UploadSchedules getUploadSchedules(UploadScheduleRequest request) throws SandataRuntimeException {
        UploadSchedules uploadSchedules = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql;
        String aniFilter = "";
        if(filterByAniEnbaled()) {
            aniFilter = "AND PT_PHONE_ANI_ENABLED_IND=1";
        }
        //get username password.
        uploadSchedules = getAccountCredentials(request.getBsnEntId());
        request.setVvId(getVisitVerificationFieldFromPreferences(request.getBsnEntId()));
        if(uploadSchedules == null || uploadSchedules.getUsername() == null || uploadSchedules.getPassword() == null) {
            throw new SandataRuntimeException("UploadSchedules could not be created.  Likely bad Agency Information was provided or Agency was not setup correctly!");
        }
        //GET STAFF FIRST!
        sql = String.format("SELECT %s FROM STAFF S1 " +
                "LEFT JOIN " +
                "( SELECT * FROM STAFF_CONT_ADDR " +
                "WHERE ( REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') AND CURR_REC_IND = 1) " +
                ") S2 " +
                "ON S1.STAFF_ID = S2.STAFF_ID AND S1.BE_ID = S2.BE_ID " +
                "LEFT JOIN " +
                "( SELECT BE_ID, STAFF_ID, REC_CREATE_TMSTP, REC_UPDATE_TMSTP, STAFF_PHONE, STAFF_PHONE_PRMY_IND FROM STAFF_CONT_PHONE " +
                "WHERE ( STAFF_CONT_PHONE.REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') AND CURR_REC_IND = 1 AND STAFF_PHONE_PRMY_IND = 1 )" +
                " group by BE_ID, STAFF_ID, REC_CREATE_TMSTP, REC_UPDATE_TMSTP, STAFF_PHONE, STAFF_PHONE_PRMY_IND ) S3 " +
                "ON S1.STAFF_ID = S3.STAFF_ID  AND S1.BE_ID = S3.BE_ID " +
                "LEFT JOIN STAFF_RATE S4 ON S1.STAFF_ID = S4.STAFF_ID AND S1.BE_ID = S4.BE_ID " +
                "WHERE ( ( S1.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S1.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S2.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S2.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S3.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S3.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S4.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S4.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') )" +
                "AND S1.BE_ID=? ) ", String.format(SQL_STAFF_MAPPING, request.getVvId()));
        uploadSchedules.getRealTimeSantraxScheduleWs().getActivityOrEmployeeOrRecipient().addAll(getUploadScheduleEmployees(request, sql));

        sql = String.format("SELECT DISTINCT %s FROM SCHED_EVNT A1 LEFT JOIN STAFF S1 ON A1.STAFF_ID = S1.STAFF_ID AND A1.BE_ID = S1.BE_ID " +
                "WHERE ( ( A1.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR A1.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S1.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR S1.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ) AND A1.BE_ID=? )", String.format(SQL_ACTIVITY_MAPPING, request.getVvId()));

        uploadSchedules.getRealTimeSantraxScheduleWs().getActivityOrEmployeeOrRecipient().addAll(getUploadScheduleActivities(request, sql));
        sql = String.format("SELECT %s FROM " +
                "( SELECT * FROM PT WHERE REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') AND CURR_REC_IND = 1 ) P1 " +
                "LEFT JOIN ( SELECT * FROM PT_CONT_ADDR WHERE ADDR_PRIO_NAME = 'PRIMARY' AND REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') " +
                " AND CURR_REC_IND = 1 ) P2 ON P1.PT_ID = P2.PT_ID AND P1.BE_ID = P2.BE_ID " +
                "LEFT JOIN ( SELECT * FROM PT_CONT_PHONE WHERE REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') " +
                " AND CURR_REC_IND = 1 AND PT_PHONE_PRMY_IND = 1 ) P3 ON P1.PT_ID = P3.PT_ID AND P1.BE_ID = P3.BE_ID " +
                "WHERE " +
                "( " +
                "   P1.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "   OR P1.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "   OR P2.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "   OR P2.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "   OR P3.REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "   OR P3.REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                ") AND P1.BE_ID=? ", SQL_RECIPIENT_MAPPING);
        ExportObjectList exportObjectList = getUploadScheduleObject(request, sql, 3);
        uploadSchedules.getRealTimeSantraxScheduleWs().getActivityOrEmployeeOrRecipient().addAll(getUploadScheduleRecipients(exportObjectList));
        uploadSchedules.getRealTimeSantraxScheduleWs().getActivityOrEmployeeOrRecipient().addAll(getUploadSchedulePhoneNbr(exportObjectList));

        sql = String.format("SELECT %s FROM PT_CONT_PHONE WHERE ( REC_TERM_TMSTP = TO_DATE('9999-12-31', 'YYYY-MM-DD') AND CURR_REC_IND = 1 ) " +
                "AND PT_PHONE_PRMY_IND = 1 AND ( REC_CREATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') " +
                "OR REC_UPDATE_TMSTP >= TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ) AND BE_ID = ? %s ", SQL_PHONENBR_MAPPING, aniFilter);

        //uploadSchedules.getRealTimeSantraxScheduleWs().getActivityOrEmployeeOrRecipient().addAll(getUploadSchedulePhoneNbr(request, sql));

        return uploadSchedules;
    }

    private List<PhoneNbr> getUploadSchedulePhoneNbr(ExportObjectList exportObjectList) {
        List<PhoneNbr> phones = new ArrayList<>();
        List<Object> objList = exportObjectList.getPhoneNbrs();
        Integer row = 1;
        for(Object obj : objList) {
            if(obj instanceof PhoneNbr) {
                PhoneNbr p = (PhoneNbr)obj;
                p.setRowOrder(row.toString());
                String id = "PhoneNbr"+row.toString();
                p.setId(id);
                row++;
                phones.add((PhoneNbr)obj);
            }
        }
        return phones;
    }
    private List<PhoneNbr> getUploadSchedulePhoneNbr(UploadScheduleRequest request, String sql) {
        ExportObjectList exportObjectList = getUploadScheduleObject(request, sql, 4);
        return getUploadSchedulePhoneNbr(exportObjectList);
    }
    private List<Recipient> getUploadScheduleRecipients(ExportObjectList exportObjectList) {
        List<Recipient> recipients = new ArrayList<>();
        List<Object> objList = exportObjectList.getClients();
        for(Object obj : objList) {
            if(obj instanceof Recipient) {
                recipients.add((Recipient)obj);
            }
        }
        return recipients;
    }
    private List<Recipient> getUploadScheduleRecipients(UploadScheduleRequest request, String sql) {

        //List<Object> objList = getUploadScheduleObject(request, sql, 3);
        ExportObjectList exportObjectList = getUploadScheduleObject(request, sql, 3);

        return getUploadScheduleRecipients(exportObjectList);
    }


    private List<Activity> getUploadScheduleActivities(UploadScheduleRequest request, String sql) {
        List<Activity> activities = new ArrayList<>();
        List<Object> objList = getUploadScheduleObject(request, sql, 2).getSchedules();
        Integer row = 1;
        for(Object obj : objList) {
            if(obj instanceof Activity) {
                Activity a = (Activity)obj;
                a.setRowOrder(row.toString());
                String id = "Activity"+row.toString();
                a.setId(id);
                row++;
                activities.add((Activity)obj);
            }
        }
        return activities;
    }

    private List<Employee> getUploadScheduleEmployees(UploadScheduleRequest request, String sql) {
        List<Employee> employees = new ArrayList<>();
        List<Object> objList = getUploadScheduleObject(request, sql, 1).getStaff();
        for(Object obj : objList) {
            if(obj instanceof Employee) {
                employees.add((Employee)obj);
            }
        }
        return employees;
    }

    private ExportObjectList getUploadScheduleObject(UploadScheduleRequest request, String sql, int objectNumber) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //List<Object> objList = new ArrayList<>();
        ExportObjectList exportObjectList = new ExportObjectList();
        exportObjectList.setObjectNumber(objectNumber);
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i = 1;
        try {
            connection = getConnectionPoolDataService().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.setString(i++, sdf.format(request.getLastExport()));
            preparedStatement.setString(i++, sdf.format(request.getLastExport()));
            if(objectNumber < 4) {
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
            }
            if(objectNumber == 3) {
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));

            }
            if(objectNumber == 1) {
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
                preparedStatement.setString(i++, sdf.format(request.getLastExport()));
            }

            preparedStatement.setString(i++, request.getBsnEntId());
            resultSet = preparedStatement.executeQuery();
            List<Object> result = new ArrayList<>();

            if(!resultSet.isBeforeFirst()) {
                return null;
            }
            String accountId = ((AccountInformation)getAccountsForExport().get(request.getBsnEntId())).getEvvAcount();


            while(resultSet.next()){
                try {
                    switch (objectNumber) {
                        case 1://staff
                            exportObjectList.getStaff().add(new DataMapper().mapEmployeeFromStaff(resultSet, accountId));
                            break;
                        case 2://Schedule
                            exportObjectList.getSchedules().add(new DataMapper().mapActivityFromSchedule(resultSet, accountId));
                            break;
                        case 3://Patient
                            exportObjectList.getClients().add(new DataMapper().mapRecipientFromPatient(resultSet, accountId));
                        case 4:
                            exportObjectList.getPhoneNbrs().add(new DataMapper().mapPhoneNbrFromPatient(resultSet, accountId));
                            break;
                        default:
                            result = null;
                            break;
                    }
                }
                catch(Exception rowLevelException) {
                    //write out the exception and continue if we can.
                    log.getExportExceptions().info("rowLevelException occured we will try to continue iterating rows." + rowLevelException.getMessage());
                }
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
            log.getExportSqlExceptions().info(String.format("%s, %s", sqle.getMessage(), sqle.getStackTrace().toString()));
        }
        catch(Exception e) {
            log.getExportExceptions().info(String.format("UUID=%s, BE_ID=%s, Excepion=%s",
                            request.getUuid(), request.getBsnEntId(), e.getLocalizedMessage()));
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()),e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    log.getExportSqlExceptions().info(sqle.getMessage());
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    log.getExportSqlExceptions().info(sqle.getMessage());
                }
            }

            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                    log.getExportSqlExceptions().info(sqle.getMessage());
                }
            }
            return exportObjectList;
        }

    }


    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    private Date getLastMinutes(Date todaysDateNow, int minusMinutes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(todaysDateNow);
        cal.add(Calendar.MINUTE, minusMinutes);
        return cal.getTime();
    }
    private boolean filterByAniEnbaled() {
        boolean checkAni = false;
        String aniFilterValue = "0";
        CamelContext context = AppContext.getContext();
        try {
            aniFilterValue = context.resolvePropertyPlaceholders("{{filterByANIEnabled}}");
            if(aniFilterValue != null && !aniFilterValue.isEmpty() && aniFilterValue.equals("1")) {
                checkAni = true;
            }
        }
        catch(Exception e) {
            log.getExportExceptions().info("Exception thrown and caught while trying to get property for filterByANIEnabled");
        }
        return checkAni;
    }
    private List<AccountInformation> getGsonObject(String propertyValue) {
        Gson gson = new Gson();
        List<AccountInformation> list = new ArrayList();
        AccountInformation[] accountInformationArray = gson.fromJson(propertyValue, AccountInformation[].class);
        for(AccountInformation a : accountInformationArray) {
            list.add(a);
        }
        return list;

    }
    public void getAccountsScheduledToBeExported(Exchange exchange) throws SandataRuntimeException {
        //should be a fired time in the header.
        Message in = exchange.getIn();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<String> list = new ArrayList<>();
        CamelContext context = AppContext.getContext();
        String accountInformationJson = null;
        String msg = "";
        String dtString = null;

        try {
            Date now = new Date();
            Date from = getLastMinutes(now, -30);
            accountInformationJson = context.resolvePropertyPlaceholders("{{Accounts}}");
            accountInformationList = getGsonObject(accountInformationJson);
            for( AccountInformation accountInformation : accountInformationList) {
                String bsnEntityId = accountInformation.getBsnEntId();
                String appTenantKeyConfLastExportDate = null;
                if(!getAccountsForExport().isEmpty() && getAccountsForExport().containsKey(bsnEntityId)) {
                    if(accountInformation.getLastExported() == null) {
                        appTenantKeyConfLastExportDate = getLastExportForBsnEntityId(bsnEntityId, true);
                    }
                    else {
                        appTenantKeyConfLastExportDate = sdf.format(accountInformation.getLastExported());
                    }
                    if(appTenantKeyConfLastExportDate == null) {
                        continue;
                    }
                    dtString = appTenantKeyConfLastExportDate;
                    list.add(0, bsnEntityId);

                    msg += String.format("Export for business entity (%s) was delayed so moving to top of list last exported %s \n",
                            bsnEntityId, dtString);
                    //we will leave the last date for this one but change login credentials in case they were changed.
                    AccountInformation a = (AccountInformation)getAccountsForExport().get(bsnEntityId);
                    a.setEvvAcount(accountInformation.getEvvAcount());
                    a.setUserName(accountInformation.getUserName());
                    a.setPassword(accountInformation.getPassword());
                    a.setDescription(accountInformation.getDescription());
                    //verbose but
                    getAccountsForExport().put(bsnEntityId, a);

                }
                else {
                    appTenantKeyConfLastExportDate = getLastExportForBsnEntityId(bsnEntityId, true);
                    if(appTenantKeyConfLastExportDate == null) {
                        log.getExportExceptions().info(String.format("EVV_EXPORT_ERROR:  MDW_LAST_EVV_EXPORT was missing from APP_TENANT_KEY_CONF table for be_id = (%s)", bsnEntityId));
                        continue;
                    }
                    dtString = appTenantKeyConfLastExportDate;
                    msg += String.format("New export date set:  %s", dtString);
                    list.add(bsnEntityId);
                    accountInformation.setLastExported(lastExport);
                    getAccountsForExport().put(bsnEntityId, accountInformation);
                }

            }
            if(msg != null && !msg.isEmpty()) {
                log.getExportExceptions().info(msg);
            }

        }
        catch(Exception e) {
            log.getExportExceptions().info("Exception thrown and caught while trying to get property for account");
        }


        in.setBody(list);
        in.setHeader("evvLastExportDate", dtString);//"2016-10-13 05:00:00");

    }

    private String getLastExportForBsnEntityId(String bsnEntityId, boolean defaultLastExport ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ResultSet resultSet = null;
        int index = 1;
        String lastExportStr = null;
        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);
            String sql = "SELECT T1.TENANT_KEY_CONF_VAL, T2.BE_ID " +
                    "FROM APP_TENANT_KEY_CONF T1 JOIN APP_TENANT_BE_XWALK T2 ON T1.APP_TENANT_SK = T2.APP_TENANT_SK  " +
                    "WHERE T2.BE_ID = ? AND T1.KEY_NAME = 'MDW_LAST_EVV_EXPORT'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntityId);


            resultSet  = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()) {
                log.getExportExceptions().info("**********************   No lastExport time returned!  **********************");
            }
            else {
                while(resultSet.next()) {
                    lastExportStr = resultSet.getString("TENANT_KEY_CONF_VAL");
                }
            }

        }
        catch(Exception e) {
            log.getExportExceptions().info(String.format("Exception thrown while getting lastExport.%s, %s", bsnEntityId, e.getLocalizedMessage()));
        }
        finally {
            if(lastExportStr == null && defaultLastExport) {
                if(this.lastExport == null) {
                 lastExport = new Date();
                }
            }
            else if (defaultLastExport) {
                try {
                    lastExport = sdf.parse(lastExportStr);
                }
                catch(ParseException pex) {
                    lastExport = new Date();
                }
            }
            return lastExportStr;
        }
    }

    private String getBsnEntityBySantraxAgencyId(String santraxAgencyId) {
        return EXPORTBE_ID;
    }
    public void setLastExportForBsnEntityId(String bsnEntityId, String dateString) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        int result = -1;
        int index = 1;
        try {
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);
            String callMethod = "{?=call PKG_APP_UTIL.UPDATE_CONF_LAST_EVV_EXPORT(?,?)}";
            callableStatement = connection.prepareCall(callMethod);

            callableStatement.registerOutParameter(index++, OracleTypes.INTEGER);
            callableStatement.setString(index++, dateString);
            callableStatement.setString(index++, bsnEntityId);

            callableStatement.execute();
            result = callableStatement.getInt(1);

            if(result < 1) {
                log.getExportLog().info(String.format("Could not update lastExport!! date = %s, be_id = %s", dateString, bsnEntityId));
            }
        }
        catch(Exception e) {
            log.getExportExceptions().info(String.format("Exception thrown while updating lastExport.%s, %s,  %s", dateString,bsnEntityId, e.getLocalizedMessage()));
        }
    }
    private String getVisitVerificationFieldFromPreferences(String bsnEntId) {
        if(bsnEntId.equals(EXPORTBE_ID)) {
            //return "S1.STAFF_TIN";
            log.getExportLog().info("we may need to pull STAFF_TIN ");
        }

        return "S1.STAFF_ID";
    }

    private UploadSchedules getAccountCredentials(String bsnEntId) throws SandataRuntimeException {
        UploadSchedules uploadSchedules = new UploadSchedules();
        RealTimeSantraxScheduleWs realTimeSantraxScheduleWs = new RealTimeSantraxScheduleWs();
        uploadSchedules.setRealTimeSantraxScheduleWs(realTimeSantraxScheduleWs);
        String username = ((AccountInformation)getAccountsForExport().get(bsnEntId)).getUserName();
        String password = ((AccountInformation)getAccountsForExport().get(bsnEntId)).getPassword();
        if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            uploadSchedules.setUsername(username);
            uploadSchedules.setPassword(password);

        }
        else if(bsnEntId.equals("13") || bsnEntId.equals("10001")) {
            uploadSchedules.setUsername("10001");
            uploadSchedules.setPassword("qtjdw10001");
        }
        else if(bsnEntId.equals("1")) {
            uploadSchedules.setUsername("0221");
            uploadSchedules.setPassword("prpm0221");
        }
        return uploadSchedules;
    }

    public List<AccountInformation> getAccountInformationList() {
        return accountInformationList;
    }

    public void setAccountInformationList(List<AccountInformation> accountInformationList) {
        this.accountInformationList = accountInformationList;
    }

    public Hashtable getAccountsForExport() {
        if(this.accountsForExport == null) {
            accountsForExport = new Hashtable();
        }
        return accountsForExport;
    }

    public void setAccountsForExport(Hashtable accountsForExport) {
        this.accountsForExport = accountsForExport;
    }


    public class ExportObjectList {
        private List <Object> clients;
        private List <Object> phoneNbrs;
        private List <Object> staff;
        private List <Object> schedules;
        private int objectNumber;

        public List<Object> getClients() {
            if(this.clients == null) {
                this.clients = new ArrayList<>();
            }
            return clients;
        }

        public void setClients(List<Object> clients) {
            this.clients = clients;
        }

        public List<Object> getPhoneNbrs() {
            if(this.phoneNbrs == null) {
                this.phoneNbrs = new ArrayList<>();
            }
            return phoneNbrs;
        }

        public void setPhoneNbrs(List<Object> phoneNbrs) {
            this.phoneNbrs = phoneNbrs;
        }

        public List<Object> getStaff() {
            if(this.staff == null) {
                this.staff = new ArrayList<>();
            }
            return staff;
        }

        public void setStaff(List<Object> staff) {
            this.staff = staff;
        }

        public List<Object> getSchedules() {
            if(this.schedules == null) {
                this.schedules = new ArrayList<>();
            }
            return schedules;
        }

        public void setSchedules(List<Object> schedules) {
            this.schedules = schedules;
        }

        public int getObjectNumber() {
            return objectNumber;
        }

        public void setObjectNumber(int objectNumber) {
            this.objectNumber = objectNumber;
        }
    }
    private class AccountInformation implements Serializable {
        private String bsnEntId;
        private String evvAcount;
        private String userName;
        private String password;
        private String description;
        private Date lastExported;
        private static final long serialVersionID = 1L;
        public AccountInformation()
        {

        }
        public final AccountInformation getAccountInformationFromJson(String json) throws Exception {
            Gson gson = new Gson();
            AccountInformation info = gson.fromJson(json, AccountInformation.class);
            return info;

        }

        public String getBsnEntId() {
            return bsnEntId;
        }

        public void setBsnEntId(String bsnEntId) {
            this.bsnEntId = bsnEntId;
        }

        public String getEvvAcount() {
            return evvAcount;
        }

        public void setEvvAcount(String evvAcount) {
            this.evvAcount = evvAcount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getLastExported() {
            return lastExported;
        }

        public void setLastExported(Date lastExported) {
            this.lastExported = lastExported;
        }
    }
}
