package com.sandata.lab.rest.visit.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.provider.GSONProvider;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.query.QueryUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.ExceptionLookup;
import com.sandata.lab.data.model.dl.model.RateQualifierCode;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.data.model.dl.model.StaffContactPhone;
import com.sandata.lab.data.model.dl.model.Visit;
import com.sandata.lab.data.model.dl.model.VisitEvent;
import com.sandata.lab.data.model.dl.model.VisitEventPatientConfirmationQualifier;
import com.sandata.lab.data.model.dl.model.VisitException;
import com.sandata.lab.data.model.dl.model.VisitNote;
import com.sandata.lab.data.model.dl.model.VisitService;
import com.sandata.lab.data.model.dl.model.VisitStatusName;
import com.sandata.lab.data.model.dl.model.VisitTaskList;
import com.sandata.lab.data.model.dl.model.VisitTaskTypeCode;
import com.sandata.lab.data.model.dl.model.audit.AuditChanged;
import com.sandata.lab.data.model.dl.model.custom.AuditVisitHistory;
import com.sandata.lab.data.model.dl.model.extended.VisitExceptionExt;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.dl.model.extended.visit.VisitExt;
import com.sandata.lab.data.model.jpub.model.VisitSvcT;
import com.sandata.lab.data.model.jpub.model.VisitT;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.visit.api.OracleService;
import com.sandata.lab.rest.visit.model.AuditVisit;
import com.sandata.lab.rest.visit.model.AuditVisitException;
import com.sandata.lab.rest.visit.model.AuditVisitNote;
import com.sandata.lab.rest.visit.model.AuditVisitTask;
import com.sandata.lab.rest.visit.model.BusinessObject;
import com.sandata.lab.rest.visit.model.FindVisitDetailsResult;
import com.sandata.lab.rest.visit.model.FindVisitResult;
import com.sandata.lab.rest.visit.model.PatientVisit;
import com.sandata.lab.rest.visit.model.VisitRelatedSK;
import com.sandata.lab.rest.visit.utils.VisitQueryUtil;
import com.sandata.lab.rest.visit.utils.constants.App;
import com.sandata.lab.rest.visit.utils.data.DataHelper;
import com.sandata.lab.rest.visit.utils.log.OracleDataLogger;
import com.sandata.lab.rest.visit.utils.templates.ProducerVisitHandler;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final SimpleDateFormat ORACLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat AUDIT_VISIT_DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private static final String TERMINATION_TIME_STAMP = "9999-12-31";

    private ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
    }

    // TODO: Temporary Fix...
    private Map<String, BigDecimal> businessEntityMapSK = new HashMap<>();
    private Map<BigDecimal, String> businessEntityMapID = new HashMap<>();

    /**
     * Maps ResultSet to FindVisitDetailsResult instance
     *
     * @param resultSet
     * @param company
     * @return
     * @throws SQLException
     */
    private FindVisitDetailsResult mapVisitDetails(final ResultSet resultSet, String company) throws SQLException {
        FindVisitDetailsResult result = new FindVisitDetailsResult();

        result.setVisitSk(resultSet.getBigDecimal("VISIT_SK").longValue());

        Timestamp adjustedIn = resultSet.getTimestamp("VISIT_ADJ_START_TMSTP");
        result.setAdjustedIn(adjustedIn != null ? new java.util.Date(adjustedIn.getTime()) : null);

        Timestamp adjustedOut = resultSet.getTimestamp("VISIT_ADJ_END_TMSTP");
        result.setAdjustedOut(adjustedOut != null ? new java.util.Date(adjustedOut.getTime()) : null);

        result.setBillCode(resultSet.getString("BILLING_CODE"));
        result.setBillHours(resultSet.getBigDecimal("VISIT_BILL_HRS"));
        result.setCallHours(resultSet.getBigDecimal("CALL_HRS"));
        result.setVisitDoNotBillIndicator(resultSet.getBoolean("VISIT_DO_NOT_BILL_IND"));

        Timestamp callIn = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
        result.setCallIn(callIn != null ? new java.util.Date(callIn.getTime()) : null);

        Timestamp callOut = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
        result.setCallOut(callOut != null ? new java.util.Date(callOut.getTime()) : null);

        result.setCompany(company);
        result.setContractId(resultSet.getString("CONTR_ID"));
        result.setContractName(resultSet.getString("CONTR_DESC"));

        result.setPatientAudioSignatureDocId(resultSet.getString("PT_AUD_SIGNATURE_DOC_ID"));
        result.setPatientDigitalSignatureDocId(resultSet.getString("PT_DIG_SIGNATURE_DOC_ID"));
        result.setPatientVerifiedService(resultSet.getString("VISIT_SVC_CNFRM_QLFR"));
        result.setPatientVerifiedTime(resultSet.getString("VISIT_EVNT_PT_CNFRM_QLFR"));

        result.setPayerId(resultSet.getString("PAYER_ID"));
        result.setPayerName(resultSet.getString("PAYER_NAME"));
        result.setServiceName(resultSet.getString("SVC_NAME"));
        result.setServiceRateType(resultSet.getString("RATE_TYP_NAME"));
        result.setServiceRateQualifierCode(resultSet.getString("RATE_QLFR_CODE"));

        Timestamp visitStartDate = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
        result.setVisitStartDate(visitStartDate != null ? new java.util.Date(visitStartDate.getTime()) : null);

        Timestamp visitEndDate = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
        result.setVisitEndDate(visitEndDate != null ? new java.util.Date(visitEndDate.getTime()) : null);

        result.setVisitStatus(resultSet.getString("VISIT_STATUS_NAME"));
        result.setVisitTz(resultSet.getString("TZ_NAME"));

        return result;
    }

    private FindVisitResult mapVisits(final ResultSet resultSet, String company, List location) throws SQLException {

        FindVisitResult findVisitResult = new FindVisitResult();
        VisitExt visit = new VisitExt();
        findVisitResult.setVisitExt(visit);

        visit.setBusinessEntityID(resultSet.getString("BE_ID"));
        visit.setVisitSK(BigInteger.valueOf(resultSet.getBigDecimal("VISIT_SK").longValue()));
        //dmr--Visits no longer have VISIT_ID as per Vitaliy 5-10-2016
        //dmr--visit.setVisitID(resultSet.getString("VISIT_ID"));
        visit.setVisitPayByScheduleIndicator(resultSet.getBoolean("VISIT_PAY_BY_SCHED_IND"));

        BigDecimal schedEvntSk = resultSet.getBigDecimal("SCHED_EVNT_SK");
        if (schedEvntSk != null) {
            visit.setScheduleEventSK(BigInteger.valueOf(schedEvntSk.longValue()));
        }

        Timestamp startDate = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
        if (startDate != null) {
            visit.setVisitActualStartTimestamp(new java.util.Date(startDate.getTime()));
        }

        Timestamp endDate = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
        if (endDate != null) {
            visit.setVisitActualEndTimestamp(new java.util.Date(endDate.getTime()));
        }

        Timestamp adjStartDate = resultSet.getTimestamp("VISIT_ADJ_START_TMSTP");
        if (adjStartDate != null) {
            visit.setVisitAdjustedStartTimestamp(new java.util.Date(adjStartDate.getTime()));
        }

        Timestamp adjEndDate = resultSet.getTimestamp("VISIT_ADJ_END_TMSTP");
        if (adjEndDate != null) {
            visit.setVisitAdjustedEndTimestamp(new java.util.Date(adjEndDate.getTime()));
        }

        visit.setVisitCancelledIndicator(resultSet.getBoolean("VISIT_CANCELLED_IND"));
        visit.setVisitCancellationReason(resultSet.getString("VISIT_CANCELLATION_REASON"));
        visit.setMemo(resultSet.getString("MEMO"));
        visit.setVisitApprovedIndicator(resultSet.getBoolean("VISIT_APRVD_IND"));
        visit.setVisitVerifiedByScheduleIndicator(resultSet.getBoolean("VISIT_VFYD_BY_SCHED_IND"));
        visit.setVisitPayHours(resultSet.getBigDecimal("VISIT_PAY_HRS"));
        visit.setVisitBillHours(resultSet.getBigDecimal("VISIT_BILL_HRS"));
        visit.setUserName(resultSet.getString("USER_NAME"));
        //dmr--Removed--visit.setCurrentRecordIndicator(resultSet.getBoolean("CURR_REC_IND"));
        visit.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
        visit.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
        visit.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));
        visit.setVisitOvertimeAbsenceHours(resultSet.getBigDecimal("VISIT_OT_ABS_HRS"));

        BigDecimal changeVerId = resultSet.getBigDecimal("CHANGE_VERSION_ID");
        if (changeVerId != null) {
            visit.setChangeVersionID(BigInteger.valueOf(changeVerId.longValue()));
        }

        Timestamp recUpdateDate = resultSet.getTimestamp("REC_UPDATE_TMSTP");
        if (recUpdateDate != null) {
            visit.setRecordUpdateTimestamp(new java.util.Date(recUpdateDate.getTime()));
        }

        //dmr--Removed--Timestamp recTermDate = resultSet.getTimestamp("REC_TERM_TMSTP");
        //dmr--Removed--if(recTermDate != null) {
        //dmr--Removed--    visit.setRecordTerminationTimestamp(new java.util.Date(recTermDate.getTime()));
        //dmr--Removed--}

        //dmr--Removed--Timestamp recEffDate = resultSet.getTimestamp("REC_EFF_TMSTP");
        //dmr--Removed--if(recEffDate != null) {
        //dmr--Removed--    visit.setRecordEffectiveTimestamp(new java.util.Date(recEffDate.getTime()));
        //dmr--Removed--}

        Timestamp recCreateDate = resultSet.getTimestamp("REC_CREATE_TMSTP");
        if (recCreateDate != null) {
            visit.setRecordCreateTimestamp(new java.util.Date(recCreateDate.getTime()));
        }

        visit.setPatientID(resultSet.getString("PT_ID"));

        visit.setStaffID(resultSet.getString("STAFF_ID"));
        visit.setTimezoneName(resultSet.getString("TZ_NAME"));

        // SAN-869 - temporarily return mock data for Visit Status Name
        visit.setVisitStatusName(VisitStatusName.SCHEDULED.value());

        findVisitResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
        findVisitResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
        findVisitResult.setPatientPhone(resultSet.getString("PT_PHONE"));
        findVisitResult.setPatientId(resultSet.getString("PT_ID"));
        findVisitResult.setMedicaidId(resultSet.getString("PT_MEDICAID_ID"));


        findVisitResult.setStaffId(visit.getStaffID());
        findVisitResult.setStaffFirstName(resultSet.getString("STAFF_FIRST_NAME"));
        findVisitResult.setStaffLastName(resultSet.getString("STAFF_LAST_NAME"));

        List<StaffContactPhone> staffContactPhones = (List<StaffContactPhone>) getEntitiesForId(
                "SELECT * FROM STAFF_CONT_PHONE WHERE STAFF_ID = ? AND STAFF_PHONE_PRMY_IND = 1",
                "com.sandata.lab.data.model.dl.model.StaffContactPhone",
                visit.getStaffID()
        );

        if (staffContactPhones.size() > 0) {
            StaffContactPhone staffContactPhone = staffContactPhones.get(0);
            findVisitResult.setStaffPhone(staffContactPhone.getStaffPhone());
        }

        /*String staffPhone = resultSet.getString("STAFF_MOBILE_PHONE");
        if(staffPhone == null)
            staffPhone = resultSet.getString("STAFF_HOME_PHONE");

        findVisitResult.setStaffPhone(staffPhone);
        */

        findVisitResult.setStaffId(resultSet.getString("STAFF_ID"));

        findVisitResult.setPatientCoordinatorId(resultSet.getString("PT_COORDINATOR_ID"));
        findVisitResult.setPatientCoordinatorFirstName(resultSet.getString("PT_COORDINATOR_FIRST_NAME"));
        findVisitResult.setPatientCoordinatorLastName(resultSet.getString("PT_COORDINATOR_LAST_NAME"));
        findVisitResult.setPatientCoordinatorPhone(resultSet.getString("PT_COORDINATOR_MOBILE_PHONE"));

        findVisitResult.setStaffCoordinatorId(resultSet.getString("STAFF_COORDINATOR_ID"));
        findVisitResult.setStaffCoordinatorFirstName(resultSet.getString("STAFF_COORDINATOR_FIRST_NAME"));
        findVisitResult.setStaffCoordinatorLastName(resultSet.getString("STAFF_COORDINATOR_LAST_NAME"));
        findVisitResult.setStaffCoordinatorPhone(resultSet.getString("STAFF_COORDINATOR_MOBILE_PHONE"));

        //dmr-No longer on Visit--findVisitResult.setPocId(resultSet.getString("POC_ID"));

        findVisitResult.setCompany(company);
        findVisitResult.setLocation(location);
        //dmr--findVisitResult.setStaffManager(resultSet.getString("STAFF_MGR"));

        Timestamp scheduleEventStartDate = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
        if (scheduleEventStartDate != null) {
            findVisitResult.setScheduleEventStartDatetime(new java.util.Date(scheduleEventStartDate.getTime()));
        }

        Timestamp scheduleEventEndDate = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
        if (scheduleEventEndDate != null) {
            findVisitResult.setScheduleEventEndDatetime(new java.util.Date(scheduleEventEndDate.getTime()));
        }

        findVisitResult.setService(resultSet.getString("SVC_NAME"));
        findVisitResult.setScheduledHours(resultSet.getString("SCD_HRS"));
        findVisitResult.setCallHours(resultSet.getString("CALL_HRS"));
        findVisitResult.setAdjustedHours(resultSet.getString("ADJ_HRS"));

        //TODO: Need to discuss on Dev call if this was on purpose or by accident!
        //dmr--PRIM/SEC Indicators removed from PT_PAYER table...5/17/2016
        /**
         BigDecimal primaryPayerSk = resultSet.getBigDecimal("PRIMARY_PAYER_SK");
         if (primaryPayerSk != null) {
         findVisitResult.setPrimaryPayerSk(BigInteger.valueOf(primaryPayerSk.longValue()));
         findVisitResult.setPrimaryPayerName(resultSet.getString("PRIMARY_PAYER_NAME"));
         }

         BigDecimal secondaryPayerSk = resultSet.getBigDecimal("SECONDARY_PAYER_SK");
         if (secondaryPayerSk != null) {
         findVisitResult.setSecondaryPayerSk(BigInteger.valueOf(secondaryPayerSk.longValue()));
         findVisitResult.setSecondaryPayerName(resultSet.getString("SECONDARY_PAYER_NAME"));
         }
         */

        /*BigDecimal visitExceptionSK = resultSet.getBigDecimal("VISIT_EXCP_SK");
        if (visitExceptionSK != null) {
            VisitException visitException = new VisitException();
            visitException.setVisitSK(visit.getVisitSK());
            visitException.setVisitExceptionSK(BigInteger.valueOf(visitExceptionSK.longValue()));
            visitException.setVisitExceptionReason(resultSet.getString("VISIT_EXCP_REASON"));
            visitException.setVisitExceptionReasonDescription(resultSet.getString("VISIT_EXCP_REASON_DESC"));
            visitException.setVisitExceptionID(resultSet.getString("VISIT_EXCP_ID"));

            //dmr--GEOR-1084
            visitException.setChangeVersionID(BigInteger.valueOf(0));
            visitException.setRecordUpdateTimestamp(new Date());
            visitException.setRecordCreateTimestamp(new Date());

            visit.getVisitException().add(visitException);
        }*/

        // Get VisitNote data
        List<VisitNote> visitNotes = getVisitNote(visit.getVisitSK(), "VISIT_NOTE_SK", "ASC");
        if (visitNotes != null) {
            visit.getVisitNote().addAll(visitNotes);
        }

        BigDecimal taskCount = resultSet.getBigDecimal("TASK_COUNT");
        if (taskCount != null) {
            findVisitResult.setTaskCount(taskCount.intValue());
        }

        //dmr--GEOR-3245: Added VISIT_EXCP_COUNT so we can filter on exceptions
        BigDecimal visitExcpCount = resultSet.getBigDecimal("VISIT_EXCP_COUNT");
        if (visitExcpCount != null) {
            findVisitResult.setVisitExceptionCount(visitExcpCount.intValue());
        }

        /*
        Timestamp visitEvntDtime = resultSet.getTimestamp("VISIT_EVNT_DTIME");
        if(visitEvntDtime != null) {
            findVisitResult.setCallIn(new java.util.Date(visitEvntDtime.getTime()));
        }*/

        // Get VisitException data
        // Converting list of VisitException to a list of VisitExceptionExt
        visit.getVisitException().addAll(getListVisitExceptionByOrder(visit.getVisitSK()));

        List<VisitEvent> visitEvents = (List<VisitEvent>) getEntitiesForId(
                "SELECT * FROM VISIT_EVNT WHERE VISIT_SK = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ORDER BY VISIT_EVNT_DTIME ASC",
                "com.sandata.lab.data.model.dl.model.VisitEvent",
                visit.getVisitSK().longValue()
        );
        visit.getVisitEvent().addAll(visitEvents);

        findVisitResult.setCallIn(visit.getVisitActualStartTimestamp());
        findVisitResult.setCallOut(visit.getVisitActualEndTimestamp());


        return findVisitResult;
    }

    /**
     * SAN-3728: get list Visit Exceptions with Exception name and description in only one statement
     *
     * @param visitSK
     * @return
     */
    private List<VisitExceptionExt> getListVisitExceptionByOrder(BigInteger visitSK) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<VisitExceptionExt> listVisitExceptionExt = new ArrayList<>();

        try {
            connection = getOracleConnection();

            String sqlGetVisitWithNameByOrder = "SELECT * FROM ( " +
                    "SELECT T2.EXCP_NAME, T2.EXCP_DESC, T1.* " +
                    "FROM VISIT_EXCP T1 " +
                    "    INNER JOIN (SELECT * FROM EXCP_LKUP WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "        ON T1.EXCP_ID = T2.EXCP_ID " +
                    "WHERE T1.VISIT_SK = ? AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    ") R1 " +
                    "ORDER BY R1.EXCP_NAME ASC ";
            preparedStatement = connection.prepareStatement(sqlGetVisitWithNameByOrder);
            preparedStatement.setObject(1, visitSK);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String exceptionName = resultSet.getString("EXCP_NAME");
                String exceptionDesc = resultSet.getString("EXCP_DESC");

                VisitException visitException = (VisitException) new DataMapper().mapResultSet(resultSet, VisitException.class.getName(), 2);
                listVisitExceptionExt.add(new VisitExceptionExt(visitException, exceptionName, exceptionDesc));
            }

            return listVisitExceptionExt;

        } catch (Exception e) {

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

            connectionPoolDataService.close(connection);
        }
    }

    /**
     * Converts provided list of {@link VisitException} to a list of {@link VisitExceptionExt} by adding exception
     * name and exception description
     *
     * @param visitExceptions a list of {@link VisitException} to be converted
     * @return a list of {@link VisitExceptionExt}
     */
    public List<VisitExceptionExt> getVisitExceptionExts(List<VisitException> visitExceptions) {
        List<VisitExceptionExt> visitExceptionExts = new ArrayList<VisitExceptionExt>();
        for (VisitException visitException : visitExceptions) {
            ExceptionLookup exceptionLookup = getExceptionLookupByExceptionId(visitException.getExceptionID());
            if (exceptionLookup != null) {
                visitExceptionExts.add(new VisitExceptionExt(visitException, exceptionLookup.getExceptionName(),
                        exceptionLookup.getExceptionDescription()));
            } else {
                visitExceptionExts.add(new VisitExceptionExt(visitException, null, null));
            }
        }
        return visitExceptionExts;
    }

    /**
     * Get ExceptionLookup instance by exceptionID
     *
     * @param exceptionId
     * @return
     */
    private ExceptionLookup getExceptionLookupByExceptionId(BigInteger exceptionId) {

        List<ExceptionLookup> exceptionLookups = (List<ExceptionLookup>) getEntitiesForId(
                "SELECT * FROM EXCP_LKUP WHERE EXCP_ID = ? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)",
                "com.sandata.lab.data.model.dl.model.ExceptionLookup",
                exceptionId.longValue());

        if (exceptionLookups == null || exceptionLookups.isEmpty()) {
            return null;
        }

        return exceptionLookups.get(0);
    }

    private Staff getStaff(String staffId, String bsnEntId, Connection connection) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int index = 1;

        try {

            String sql = "SELECT * FROM STAFF WHERE STAFF_ID = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(index++, staffId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Staff> staffList = (List<Staff>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Staff");

            connection.commit();

            if (staffList == null || staffList.size() == 0)
                return null;

            return staffList.get(0);

        } catch (Exception e) {

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

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
        }
    }

    public int deleteVisitTasks(List<Long> visitTaskSks) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            StringBuilder stringBuilder = new StringBuilder("DELETE FROM VISIT_TASK_LST WHERE VISIT_TASK_LST_SK IN (?) ");

            String skList = visitTaskSks.toString();
            String skParams = QueryUtil.ParamList(visitTaskSks.size());

            String sql = stringBuilder.toString().replace("(?)", skParams);


            preparedStatement = connection.prepareStatement(sql);
            int index = 1;

            for (Long sk : visitTaskSks) {
                preparedStatement.setBigDecimal(index++, new BigDecimal(sk));
            }

            int result = preparedStatement.executeUpdate();

            connection.commit();

            return result;


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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public List<VisitTaskListExt> getVisitTaskList(final BigInteger visitSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = " SELECT * FROM VISIT_TASK_LST VTL " +
                    " " +
                    " LEFT JOIN (SELECT BE_ID, TASK_DESC, REC_TERM_TMSTP, BE_TASK_ID, CURR_REC_IND FROM TASK) T1" +
                    " ON VTL.BE_TASK_ID = T1.BE_TASK_ID " +
                    " AND VTL.BE_ID = T1.BE_ID AND T1.CURR_REC_IND = ? " +
                    " AND TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = ? " +
                    " WHERE VTL.VISIT_SK = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;

            preparedStatement.setBoolean(index++, true);
            preparedStatement.setString(index++, TERMINATION_TIME_STAMP);
            preparedStatement.setLong(index++, visitSk.longValue());

            resultSet = preparedStatement.executeQuery();

            connection.commit();


            return mapResultToVisitTaskListExt(resultSet);

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private List<VisitTaskListExt> mapResultToVisitTaskListExt(ResultSet resultSet) throws Exception {

        List<VisitTaskListExt> visitTaskListExts = new ArrayList<>();

        try {

            while (resultSet.next()) {

                VisitTaskListExt visitTaskListExt = new VisitTaskListExt(new VisitTaskList());

                visitTaskListExt.setVisitTaskListSK(resultSet.getBigDecimal("VISIT_TASK_LST_SK").toBigInteger());
                visitTaskListExt.setVisitTaskListID(resultSet.getString("VISIT_TASK_LST_ID"));
                visitTaskListExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                visitTaskListExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
                visitTaskListExt.setChangeVersionID(resultSet.getBigDecimal("CHANGE_VERSION_ID").toBigInteger());
                visitTaskListExt.setBusinessEntityID(resultSet.getString("BE_ID"));
                visitTaskListExt.setVisitSK(resultSet.getBigDecimal("VISIT_SK").toBigInteger());
                visitTaskListExt.setBusinessEntityTaskID(resultSet.getString("BE_TASK_ID"));
                visitTaskListExt.setCriticalTaskIndicator(resultSet.getBoolean("CRITICAL_TASK_IND"));
                visitTaskListExt.setVisitTaskScheduledIndicator(resultSet.getBoolean("VISIT_TASK_SCHEDULED_IND"));
                visitTaskListExt.setVisitTaskPerformedIndicator(resultSet.getBoolean("VISIT_TASK_PERF_IND"));
                visitTaskListExt.setVisitTaskNotPerformedReason(resultSet.getString("VISIT_TASK_NOT_PERF_REASON"));
                visitTaskListExt.setVisitTaskComment(resultSet.getString("VISIT_TASK_COMMENT"));
                visitTaskListExt.setTaskResultsReadingType(resultSet.getString("TASK_RESULTS_RDNG_TYP"));
                visitTaskListExt.setTaskResultsReadingValue(resultSet.getString("TASK_RESULTS_RDNG_VAL"));

                visitTaskListExt.setVisitTaskDescription(resultSet.getString("TASK_DESC"));

                try {

                    String resultQualifier = resultSet.getString("VISIT_TASK_CNFRM_QLFR");

                    if (!StringUtil.IsNullOrEmpty(resultQualifier)) {
                        visitTaskListExt.setVisitTaskConfirmationQualifier(VisitEventPatientConfirmationQualifier.fromValue(resultQualifier));
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

                try {

                    String resultQualifier = resultSet.getString("VISIT_TASK_TYP_CODE");

                    if (!StringUtil.IsNullOrEmpty(resultQualifier)) {
                        visitTaskListExt.setVisitTaskTypeCode(VisitTaskTypeCode.fromValue(resultQualifier));
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

                visitTaskListExts.add(visitTaskListExt);

            }
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }

        if (visitTaskListExts.size() < 0) {
            return null;
        }

        return visitTaskListExts;
    }

    private String getVisitTaskDescription(final String beTaskId, final SandataLogger logger) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT TASK_DESC FROM TASK WHERE BE_TASK_ID = ?";

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index, beTaskId);

            resultSet = preparedStatement.executeQuery();

            connection.commit();

            String visitTaskDesc = null;
            if (resultSet.next()) {
                visitTaskDesc = resultSet.getString("TASK_DESC");
            } else {
                logger.warn(String.format("getVisitTaskDescription: WARNING: TASK_ID=%s: Task Not Found!", beTaskId));
            }

            return visitTaskDesc;

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }

    }

    private List<VisitNote> getVisitNote(final BigInteger visitSk, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM VISIT_NOTE WHERE VISIT_SK = ? " +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setLong(index, visitSk.longValue());

            resultSet = preparedStatement.executeQuery();

            List<VisitNote> visitNoteList = (List<VisitNote>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitNote");

            if (visitNoteList == null || visitNoteList.size() == 0)
                return null;

            return visitNoteList;

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getVisitsForPatient(
            String patientId,
            String bsnEntId,
            Date fromDateTime,
            Date toDateTime,
            String sortOn,
            String direction,
            int page,
            int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        SandataLogger logger = OracleDataLogger.CreateLogger();

        try {

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            int index = 1;

            String dateFilter = "";

            if (fromDateTime != null) {
                dateFilter = " AND ((V1.VISIT_ACT_START_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                        "                  TO_DATE(?, 'YYYY-MM-DD HH24:MI')) OR " +
                        "               (V1.VISIT_ADJ_START_TMSTP BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND " +
                        "                  TO_DATE(?, 'YYYY-MM-DD HH24:MI')) " +
                        "              )";
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT V1.*, SE1.TZ_NAME " +
                            "        FROM VISIT V1 " +
                            "          LEFT JOIN SCHED_EVNT SE1 ON V1.SCHED_EVNT_SK = SE1.SCHED_EVNT_SK " +
                            "             AND (TO_CHAR(SE1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND SE1.CURR_REC_IND = 1) " +
                            "          WHERE V1.PT_ID = ? AND V1.BE_ID = ? " +
                            " " +
                            "          %s " +
                            " " +
                            //dmr--No Longer Historical--"          AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            " " +
                            "          ORDER BY V1.%s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    dateFilter,
                    sortOn,
                    direction,
                    fromRow,
                    toRow);

            Response response = new Response();
            response.setOrderByColumn(sortOn.toUpperCase());
            response.setOrderByDirection(direction.toUpperCase());
            response.setPage(page);
            response.setPageSize(pageSize);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index++, bsnEntId);

            if (fromDateTime != null) {
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(fromDateTime));
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(toDateTime));
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(fromDateTime));
                preparedStatement.setString(index++, ORACLE_DATE_TIME_FORMAT.format(toDateTime));
            }

            resultSet = preparedStatement.executeQuery();

            DataMapper mapper = new DataMapper();
            List<VisitExt> visitExts = new ArrayList<>();
            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                Visit visit = (Visit) mapper.mapResultSet(resultSet, "com.sandata.lab.data.model.dl.model.Visit", 2);
                VisitExt visitExt = new VisitExt(visit);
                visitExt.setTimezoneName(resultSet.getString("TZ_NAME"));
                visitExts.add(visitExt);

            }

            List<PatientVisit> patientVisitsList = new ArrayList<>();
            Map<String, Staff> staffMap = new HashMap<>();
            for (VisitExt visitExt : visitExts) {

                PatientVisit patientVisits = new PatientVisit();
                patientVisits.setVisitExt(visitExt);

                String staffId = visitExt.getStaffID();
                if (staffId == null) {
                    logger.warn("staffId == null");
                } else {

                    Staff staff;
                    if (staffMap.containsKey(staffId)) {
                        staff = staffMap.get(staffId);
                    } else {
                        staff = getStaff(staffId, bsnEntId, connection);
                        staffMap.put(staffId, staff);
                    }

                    if (staff == null) {
                        logger.warn(String.format("[VisitSK: %d][StaffID: %s]: staff == null",
                                visitExt.getVisitSK().longValue(), staffId));
                    } else {
                        patientVisits.setStaffFirstName(staff.getStaffFirstName());
                        patientVisits.setStaffLastName(staff.getStaffLastName());
                        patientVisits.setStaffId(staff.getStaffID());
                        patientVisits.setServiceName(staff.getStaffPositionName());
                        patientVisits.setStaffSk(staff.getStaffSK());
                        //dmr--Removed--patientVisits.setServiceName(getServiceName(patientVisits.getStaffPositionId(), bsnEntId, connection));
                    }
                }

                List<VisitTaskListExt> visitTaskListExts = getVisitTaskList(visitExt.getVisitSK());

                if (visitTaskListExts != null) {
                    visitExt.getVisitTaskList().addAll(visitTaskListExts);
                }

                List<VisitNote> visitNotesList = getVisitNote(visitExt.getVisitSK(), sortOn, direction);

                if (visitNotesList != null) {
                    visitExt.getVisitNote().addAll(visitNotesList);
                }

                patientVisitsList.add(patientVisits);
            }

            response.setData(patientVisitsList);

            connection.commit();

            return response;

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response findVisit(
            final String patientFirstName,
            final String patientLastName,
            final String patientId,
            final String patientCoordinator,
            final String staffFirstName,
            final String staffLastName,
            final String staffId,
            final String staffCoordinator,
            final String businessEntityId,
            final Date fromDateTime,
            final Date toDateTime,
            final String orderByColumn,
            final String orderByDirection,
            final String status,
            final Double scheduledHours,
            final int page,
            final int pageSize,
            final Long visitSK,
            final List<BigInteger> exceptionIdList,
            final Boolean excpOnlyFlag) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //dmr--GEOR-2763: Impl additional sorting options
        String orderByString = "UPPER(PT_LAST_NAME)"; // Default
        switch (orderByColumn) {
            case "pfn":
                orderByString = "UPPER(PT_FIRST_NAME)";
                break;
            case "pid":
                orderByString = "TO_NUMBER(PT_ID)";
                break;
            case "sln":
                orderByString = "UPPER(STAFF_LAST_NAME)";
                break;
            case "sfn":
                orderByString = "UPPER(STAFF_FIRST_NAME)";
                break;
            case "sid":
                orderByString = "TO_NUMBER(STAFF_ID)";
                break;
            case "vfd":
                orderByString = "VISIT_ACT_START_TMSTP";
                break;
            case "svc":
                orderByString = "UPPER(SVC_NAME)";
                break;
            case "schdin":
                orderByString = "SCHED_EVNT_START_DTIME";
                break;
            case "schdout":
                orderByString = "SCHED_EVNT_END_DTIME";
                break;
            case "schdhrs":
                orderByString = "SCD_HRS";
                break;
            case "callin":
                orderByString = "VISIT_ACT_START_TMSTP";
                break;
            case "callout":
                orderByString = "VISIT_ACT_END_TMSTP";
                break;
            case "callhrs":
                orderByString = "CALL_HRS";
                break;
            case "adjin":
                orderByString = "VISIT_ADJ_START_TMSTP";
                break;
            case "adjout":
                orderByString = "VISIT_ADJ_END_TMSTP";
                break;
            case "adjhrs":
                orderByString = "ADJ_HRS";
                break;
            case "payhrs":
                orderByString = "VISIT_PAY_HRS";
                break;
            case "billhrs":
                orderByString = "VISIT_BILL_HRS";
                break;
            case "vbyschd":
                orderByString = "VISIT_VFYD_BY_SCHED_IND";
                break;
            case "pbyschd":
                orderByString = "VISIT_PAY_BY_SCHED_IND";
                break;
            case "otabshrs":
                orderByString = "VISIT_OT_ABS_HRS";
                break;
            case "approved":
                orderByString = "VISIT_APRVD_IND";
                break;
            case "taskc":
                orderByString = "TASK_COUNT";
                break;
            // order by Exception
            case "exception":
            case "ExceptionLookupName":
                orderByString = "UPPER(EXCP_NAME)";
                break;
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            BusinessObject businessObject = getBusinessEntityIDGeneral(connection, businessEntityId);

            StringBuilder filterItems = new StringBuilder();
            StringBuilder outerFilter = new StringBuilder();

            String fromDateTimeFilter;
            String toDateTimeFilter;
            String fromDateTime1Filter;
            String toDateTime1Filter;
            String fromDateTime2Filter;
            String toDateTime2Filter;
            String fromDateTime3Filter;
            String toDateTime3Filter;
            String fromDateTime4Filter;
            String toDateTime4Filter;

            if (patientFirstName != null && patientFirstName.length() != 0) {
                filterItems.append("UPPER(T3.PT_FIRST_NAME) LIKE ? AND ");
            }

            if (patientLastName != null && patientLastName.length() != 0) {
                filterItems.append("UPPER(T3.PT_LAST_NAME) LIKE ? AND ");
            }

            if (patientId != null && patientId.length() != 0) {
                filterItems.append("UPPER(T3.PT_ID) LIKE ? AND ");
            }

            if (patientCoordinator != null && patientCoordinator.length() != 0) {
                filterItems.append("UPPER(A4.ADMIN_STAFF_ID) LIKE ? AND ");
            }

            if (staffFirstName != null && staffFirstName.length() != 0) {
                filterItems.append("UPPER(T2.STAFF_FIRST_NAME) LIKE ? AND ");
            }

            if (staffLastName != null && staffLastName.length() != 0) {
                filterItems.append("UPPER(T2.STAFF_LAST_NAME) LIKE ? AND ");
            }

            if (staffId != null && staffId.length() != 0) {
                filterItems.append("UPPER(T2.STAFF_ID) LIKE ? AND ");
            }

            if (staffCoordinator != null && staffCoordinator.length() != 0) {
                filterItems.append("UPPER(A1.ADMIN_STAFF_ID) LIKE ? AND ");
            }

            if (scheduledHours != null) {

                // IS NULL
                if (scheduledHours == -1) {
                    filterItems.append("((T1.VISIT_ACT_END_TMSTP-T1.VISIT_ACT_START_TMSTP)*24) IS NULL AND ");
                }
                // IS NOT NULL
                else if (scheduledHours == -2) {
                    filterItems.append("((T1.VISIT_ACT_END_TMSTP-T1.VISIT_ACT_START_TMSTP)*24) IS NOT NULL AND ");
                } else {
                    filterItems.append("((T1.VISIT_ACT_END_TMSTP-T1.VISIT_ACT_START_TMSTP)*24) = ? AND ");
                }
            }

            if (excpOnlyFlag != null && excpOnlyFlag) {
                outerFilter.append("WHERE VISIT_EXCP_COUNT > 0");
            }

            /** 'all' is the default value **/
            //dmr--GEOR-3245: joining to visit_excp will cause dups. Instead we will filter by count.
            if (status.toLowerCase().equals("unapproved")) {

                // Visits w/o exceptions that are NOT approved
                //dmr--GEOR-3245: filterItems.append("T6.VISIT_EXCP_SK IS NULL AND (T1.VISIT_APRVD_IND IS NULL OR T1.VISIT_APRVD_IND=0) AND ");
                filterItems.append("(T1.VISIT_APRVD_IND IS NULL OR T1.VISIT_APRVD_IND=0) AND ");
            } else if (status.toLowerCase().equals("approved")) {

                // Visits that are approved
                filterItems.append("T1.VISIT_APRVD_IND=1 AND ");
            }

            String formattedFromDateTime = ORACLE_DATE_TIME_FORMAT.format(fromDateTime);
            String formattedToDateTime = ORACLE_DATE_TIME_FORMAT.format(toDateTime);

            fromDateTimeFilter = String.format(" (((T1.VISIT_ACT_START_TMSTP BETWEEN TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND", formattedFromDateTime);
            toDateTimeFilter = String.format("TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND ", formattedToDateTime);

            // GEOR-6442
            fromDateTime1Filter = String.format(" T1.VISIT_ACT_END_TMSTP BETWEEN TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND", formattedFromDateTime);
            toDateTime1Filter = String.format("TO_DATE('%s', 'YYYY-MM-DD HH24:MI')) OR ", formattedToDateTime);

            //GEOR-6636 -TD
            fromDateTime2Filter = String.format(" (T1.VISIT_ACT_START_TMSTP BETWEEN TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND", formattedFromDateTime);
            toDateTime2Filter = String.format("TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND T1.VISIT_ACT_END_TMSTP IS NULL ) OR ", formattedToDateTime);

            fromDateTime3Filter = String.format(" (T1.VISIT_ACT_START_TMSTP IS NULL AND T1.VISIT_ACT_END_TMSTP BETWEEN TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND", formattedFromDateTime);
            toDateTime3Filter = String.format("TO_DATE('%s', 'YYYY-MM-DD HH24:MI')) OR ", formattedToDateTime);

            //dmr GEOR-2738
            fromDateTime4Filter = String.format(" (T1.VISIT_ACT_START_TMSTP IS NULL AND T1.VISIT_ACT_END_TMSTP IS NULL AND T4.SCHED_EVNT_START_DTIME BETWEEN TO_DATE('%s', 'YYYY-MM-DD HH24:MI') AND", formattedFromDateTime);
            toDateTime4Filter = String.format("TO_DATE('%s', 'YYYY-MM-DD HH24:MI')))) AND ", formattedToDateTime);


            //dmr--GEOR-3510: Temporary implementation to support current UI design.
            if (visitSK != null) {
                filterItems.append("T1.VISIT_SK = ? AND ");
            }

            String exceptionFilter = "";
            if (exceptionIdList != null && !exceptionIdList.isEmpty()) {

                StringBuilder exceptionIdConditions = new StringBuilder();
                exceptionIdConditions.append(" (");
                for (int i = 0; i < exceptionIdList.size(); i++) {
                    exceptionIdConditions.append("VISIT_EXCP.EXCP_ID = ?");
                    if (i < (exceptionIdList.size() - 1)) {
                        exceptionIdConditions.append(" OR ");
                    }
                }
                exceptionIdConditions.append(")");
                // Should only check if Visit has Exception in exception id filter list
                filterItems.append(String.format("EXISTS ( " +
                                "SELECT 1 FROM (" +
                                "        SELECT VISIT_SK, EXCP_ID, EXCP_CLRD_MANUAL_IND " +
                                "        FROM VISIT_EXCP " +
                                "        WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'AND CURR_REC_IND = 1 " +
                                "      ) VISIT_EXCP " +
                                "      " +
                                "      INNER JOIN (" +
                                "        SELECT EXCP_LKUP_SK, EXCP_ID, EXCP_NAME, EXCP_DESC, EXCP_NON_FIXABLE_IND, EXCP_ACK_IND " +
                                "        FROM EXCP_LKUP " +
                                "        WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'AND CURR_REC_IND = 1 " +
                                "      ) EXCP_LKUP " +
                                "      ON VISIT_EXCP.EXCP_ID = EXCP_LKUP.EXCP_ID " +
                                "WHERE T1.VISIT_SK = VISIT_EXCP.VISIT_SK " +
                                "    AND %s " +
                                ") AND ",
                        exceptionIdConditions.toString())
                );
            }

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sql = String.format("SELECT *" +
                            "FROM" +
                            "  (SELECT ROWNUM ROW_NUMBER," +
                            "    COUNT(*) OVER() TOTAL_ROWS," +
                            "    R1.*" +
                            "  FROM" +
                            "    (SELECT *" +
                            "    FROM (" +
                            "      (SELECT T4.SCHED_SK," +
                            "        T1.VISIT_SK," +
                            "        T1.SCHED_EVNT_SK," +
                            "        T1.STAFF_ID," +
                            "        T1.PT_ID," +
                            "        T1.VISIT_ACT_START_TMSTP," +
                            "        T1.VISIT_ACT_END_TMSTP," +
                            "        T1.VISIT_ADJ_START_TMSTP," +
                            "        T1.VISIT_ADJ_END_TMSTP," +
                            "        NVL(T1.VISIT_APRVD_IND, 0)         AS VISIT_APRVD_IND," +
                            "        NVL(T1.VISIT_VFYD_BY_SCHED_IND, 0) AS VISIT_VFYD_BY_SCHED_IND," +
                            "        T1.VISIT_PAY_HRS," +
                            "        T1.VISIT_BILL_HRS," +
                            "        T1.VISIT_OT_ABS_HRS," +
                            "        T1.USER_NAME," +
                            "        NVL(T1.VISIT_CANCELLED_IND, 0) AS VISIT_CANCELLED_IND," +
                            "        T1.VISIT_CANCELLATION_REASON," +
                            "        T1.MEMO," +
                            "        NVL(T1.VISIT_PAY_BY_SCHED_IND, 0) AS VISIT_PAY_BY_SCHED_IND," +
                            "        T1.CHANGE_REASON_MEMO," +
                            "        T1.REC_CREATED_BY," +
                            "        T1.REC_UPDATED_BY," +
                            "        T1.CHANGE_VERSION_ID," +
                            "        T1.REC_UPDATE_TMSTP," +
                            "        T1.REC_CREATE_TMSTP," +
                            "        ROUND(((T4.SCHED_EVNT_END_DTIME-T4.SCHED_EVNT_START_DTIME)*24),2) SCD_HRS," +
                            "        ROUND(((T1.VISIT_ACT_END_TMSTP -T1.VISIT_ACT_START_TMSTP)*24),2) CALL_HRS," +
                            "        ROUND(((T1.VISIT_ADJ_END_TMSTP -T1.VISIT_ADJ_START_TMSTP)*24),2) ADJ_HRS," +
                            "        T2.STAFF_FIRST_NAME," +
                            "        T2.STAFF_LAST_NAME," +
                            "        T3.PT_FIRST_NAME," +
                            "        T3.PT_LAST_NAME," +
                            "        T3.PT_MEDICAID_ID, " +
                            "        P2.PT_PHONE," +
                            "        T4.SCHED_EVNT_START_DTIME," +
                            "        T4.SCHED_EVNT_END_DTIME," +
                            "        T4.TZ_NAME," +
                            "        T7.SVC_NAME," +
                            "        (SELECT COUNT(*)" +
                            "        FROM VISIT_TASK_LST" +
                            "        WHERE T1.VISIT_SK = VISIT_TASK_LST.VISIT_SK" +
                            "        ) TASK_COUNT," +
                            "        (SELECT COUNT(*)" +
                            "        FROM VISIT_EXCP" +
                            "        WHERE T1.VISIT_SK = VISIT_EXCP.VISIT_SK" +
                            "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                            "        ) VISIT_EXCP_COUNT," +
                            "        T1.BE_ID," +
                            "        A1.ADMIN_STAFF_ID           AS STAFF_COORDINATOR_ID," +
                            "        A1.ADMIN_STAFF_FIRST_NAME   AS STAFF_COORDINATOR_FIRST_NAME," +
                            "        A1.ADMIN_STAFF_LAST_NAME    AS STAFF_COORDINATOR_LAST_NAME," +
                            "        A1.ADMIN_STAFF_MOBILE_PHONE AS STAFF_COORDINATOR_MOBILE_PHONE," +
                            "        A4.ADMIN_STAFF_ID           AS PT_COORDINATOR_ID," +
                            "        A4.ADMIN_STAFF_FIRST_NAME   AS PT_COORDINATOR_FIRST_NAME," +
                            "        A4.ADMIN_STAFF_LAST_NAME    AS PT_COORDINATOR_LAST_NAME," +
                            "        A4.ADMIN_STAFF_MOBILE_PHONE AS PT_COORDINATOR_MOBILE_PHONE," +
                            "        E1.EXCP_NAME " +
                            "      FROM VISIT T1" +
                            "      LEFT JOIN" +
                            "        (SELECT BE_ID," +
                            "          PT_ID," +
                            "          PT_FIRST_NAME," +
                            "          PT_LAST_NAME," +
                            "          PT_MEDICAID_ID, " +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND" +
                            "        FROM PT" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T3" +
                            "      ON T1.PT_ID  = T3.PT_ID" +
                            "      AND T1.BE_ID = T3.BE_ID" +
                            "      LEFT JOIN" +
                            "        (SELECT BE_ID," +
                            "          PT_ID," +
                            "          PT_PHONE_PRMY_IND," +
                            "          PT_PHONE," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND" +
                            "        FROM PT_CONT_PHONE" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) P2" +
                            "      ON T3.PT_ID           = P2.PT_ID" +
                            "      AND T3.BE_ID          = P2.BE_ID" +
                            "      AND PT_PHONE_PRMY_IND = 1" +
                            "      LEFT JOIN" +
                            "        (SELECT BE_ID," +
                            "          STAFF_ID," +
                            "          STAFF_FIRST_NAME," +
                            "          STAFF_LAST_NAME," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND" +
                            "        FROM STAFF" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T2" +
                            "      ON T1.STAFF_ID = T2.STAFF_ID" +
                            "      AND T1.BE_ID   = T2.BE_ID" +
                            "      LEFT JOIN" +
                            "        (SELECT SCHED_EVNT_SK," +
                            "          BE_ID," +
                            "          SCHED_SK," +
                            "          SCHED_EVNT_START_DTIME," +
                            "          SCHED_EVNT_END_DTIME," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND, TZ_NAME" +
                            "        FROM SCHED_EVNT" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T4" +
                            "      ON T1.SCHED_EVNT_SK = T4.SCHED_EVNT_SK" +
                            "      AND T1.BE_ID        = T4.BE_ID" +
                            "      LEFT JOIN" +
                            "        (SELECT VISIT_SK,SVC_NAME FROM VISIT_SVC WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND = 1)" +
                            "        ) T7" +
                            "      ON T1.VISIT_SK = T7.VISIT_SK " +
                            "      LEFT JOIN" +
                            "        (SELECT J1.BE_ID," +
                            "          J1.ADMIN_STAFF_ID," +
                            "          J1.STAFF_ID," +
                            "          J1.ADMIN_STAFF_STAFF_EFF_DATE," +
                            "          J1.ADMIN_STAFF_STAFF_TERM_DATE," +
                            "          J1.REC_TERM_TMSTP," +
                            "          J1.CURR_REC_IND," +
                            "          J2.ADMIN_STAFF_ROLE_EFF_DATE," +
                            "          J2.ADMIN_STAFF_ROLE_TERM_DATE," +
                            "          J3.ADMIN_STAFF_FIRST_NAME," +
                            "          J3.ADMIN_STAFF_LAST_NAME," +
                            "          J3.ADMIN_STAFF_MOBILE_PHONE" +
                            "        FROM ADMIN_STAFF_STAFF_XREF J1" +
                            "        INNER JOIN" +
                            "          (SELECT BE_ID," +
                            "            ADMIN_STAFF_ID," +
                            "            ADMIN_STAFF_ROLE_EFF_DATE," +
                            "            ADMIN_STAFF_ROLE_TERM_DATE" +
                            "            FROM ADMIN_STAFF_ROLE_XREF" +
                            "          WHERE UPPER(ADMIN_STAFF_ROLE_NAME)      = 'COORDINATOR'" +
                            "          AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "          AND CURR_REC_IND                           = 1)" +
                            "          ) J2" +
                            "        ON J1.BE_ID           = J2.BE_ID" +
                            "        AND J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID" +
                            "        INNER JOIN" +
                            "          (SELECT BE_ID," +
                            "            ADMIN_STAFF_ID," +
                            "            ADMIN_STAFF_FIRST_NAME," +
                            "            ADMIN_STAFF_LAST_NAME," +
                            "            ADMIN_STAFF_MOBILE_PHONE," +
                            "            REC_TERM_TMSTP," +
                            "            CURR_REC_IND" +
                            "          FROM ADMIN_STAFF" +
                            "          WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "          AND CURR_REC_IND                            = 1" +
                            "          ) J3" +
                            "        ON J1.BE_ID                                     = J3.BE_ID" +
                            "        AND J1.ADMIN_STAFF_ID                           = J3.ADMIN_STAFF_ID" +
                            "        WHERE (TO_CHAR(J1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND J1.CURR_REC_IND                             = 1 )" +
                            "        ) A1 ON T1.STAFF_ID                             = A1.STAFF_ID" +
                            "      AND T1.BE_ID                                      = A1.BE_ID" +
                            "      AND T4.SCHED_EVNT_START_DTIME BETWEEN A1.ADMIN_STAFF_STAFF_EFF_DATE AND A1.ADMIN_STAFF_STAFF_TERM_DATE" +
                            "      AND T4.SCHED_EVNT_START_DTIME BETWEEN A1.ADMIN_STAFF_ROLE_EFF_DATE AND A1.ADMIN_STAFF_ROLE_TERM_DATE" +
                            "      LEFT JOIN" +
                            "        (SELECT J1.BE_ID," +
                            "          J1.ADMIN_STAFF_ID," +
                            "          J1.PT_ID," +
                            "          J1.ADMIN_STAFF_PT_EFF_DATE," +
                            "          J1.ADMIN_STAFF_PT_TERM_DATE," +
                            "          J1.REC_TERM_TMSTP," +
                            "          J1.CURR_REC_IND," +
                            "          J2.ADMIN_STAFF_ROLE_EFF_DATE," +
                            "          J2.ADMIN_STAFF_ROLE_TERM_DATE," +
                            "          J3.ADMIN_STAFF_FIRST_NAME," +
                            "          J3.ADMIN_STAFF_LAST_NAME," +
                            "          J3.ADMIN_STAFF_MOBILE_PHONE" +
                            "        FROM ADMIN_STAFF_PT J1" +
                            "        INNER JOIN" +
                            "          (SELECT BE_ID," +
                            "            ADMIN_STAFF_ID," +
                            "            ADMIN_STAFF_ROLE_EFF_DATE," +
                            "            ADMIN_STAFF_ROLE_TERM_DATE" +
                            "            FROM ADMIN_STAFF_ROLE_XREF" +
                            "          WHERE UPPER(ADMIN_STAFF_ROLE_NAME)      = 'COORDINATOR'" +
                            "          AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "          AND CURR_REC_IND                           = 1)" +
                            "          ) J2" +
                            "        ON J1.BE_ID           = J2.BE_ID" +
                            "        AND J1.ADMIN_STAFF_ID = J2.ADMIN_STAFF_ID" +
                            "        INNER JOIN" +
                            "          (SELECT BE_ID," +
                            "            ADMIN_STAFF_ID," +
                            "            ADMIN_STAFF_FIRST_NAME," +
                            "            ADMIN_STAFF_LAST_NAME," +
                            "            ADMIN_STAFF_MOBILE_PHONE," +
                            "            REC_TERM_TMSTP," +
                            "            CURR_REC_IND" +
                            "          FROM ADMIN_STAFF" +
                            "          WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "          AND CURR_REC_IND                            = 1" +
                            "          ) J3" +
                            "        ON J1.BE_ID                                     = J3.BE_ID" +
                            "        AND J1.ADMIN_STAFF_ID                           = J3.ADMIN_STAFF_ID" +
                            "        WHERE (TO_CHAR(J1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND J1.CURR_REC_IND                             = 1 )" +
                            "        ) A4 ON T3.PT_ID                                = A4.PT_ID" +
                            "      AND T3.BE_ID                                      = A4.BE_ID" +
                            "      AND T4.SCHED_EVNT_START_DTIME BETWEEN A4.ADMIN_STAFF_PT_EFF_DATE AND A4.ADMIN_STAFF_PT_TERM_DATE" +
                            "      AND T4.SCHED_EVNT_START_DTIME BETWEEN A4.ADMIN_STAFF_ROLE_EFF_DATE AND A4.ADMIN_STAFF_ROLE_TERM_DATE" +
                            // Left join to very first exception which is sorted by exception name assescending 
                            "      LEFT JOIN ( " +
                            "           " +
                            "       SELECT K1.VISIT_SK, K2.EXCP_NAME, " +
                            // sort the exceptions of Visit before sorting the whole Visits by exception
                            "               ROW_NUMBER() OVER(PARTITION BY K1.VISIT_SK, K2.EXCP_NAME ORDER BY UPPER(K2.EXCP_NAME) ASC) AS EXCP_SORTING_ORDER  " +
                            "           FROM VISIT_EXCP K1 " +
                            "               INNER JOIN (SELECT * FROM EXCP_LKUP WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) K2 " +
                            "                   ON K1.EXCP_ID = K2.EXCP_ID " +
                            "           WHERE (TO_CHAR(K1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND K1.CURR_REC_IND = 1) " +
                            "      ) E1 ON E1.VISIT_SK = T1.VISIT_SK AND E1.EXCP_SORTING_ORDER = 1 " +

                            " " +
                            exceptionFilter +
                            " " +
                            "          WHERE %s " +
                            " " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            "                %s " +
                            " " +
                            "              T1.BE_ID = ? " +
                            " " +
                            "              ORDER BY %s %s, UPPER(EXCP_NAME) ASC " +
                            "       ) " +
                            " " +
                            "  ) %s " +
                            " " +
                            ") R1) " +
                            " " +
                            " WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filterItems.toString(),
                    fromDateTimeFilter,
                    toDateTimeFilter,
                    fromDateTime1Filter,
                    toDateTime1Filter,
                    fromDateTime2Filter,
                    toDateTime2Filter,
                    fromDateTime3Filter,
                    toDateTime3Filter,
                    fromDateTime4Filter,
                    toDateTime4Filter,
                    orderByString,
                    orderByDirection,
                    outerFilter.toString(),
                    fromRow,
                    toRow
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            if (patientFirstName != null && patientFirstName.length() != 0) {
                preparedStatement.setString(index++, patientFirstName.toUpperCase() + "%");
            }

            if (patientLastName != null && patientLastName.length() != 0) {
                preparedStatement.setString(index++, patientLastName.toUpperCase() + "%");
            }

            if (patientId != null && patientId.length() != 0) {
                preparedStatement.setString(index++, patientId.toUpperCase() + "%");
            }

            if (patientCoordinator != null && patientCoordinator.length() != 0) {
                preparedStatement.setString(index++, patientCoordinator.toUpperCase() + "%");
            }

            if (staffFirstName != null && staffFirstName.length() != 0) {
                preparedStatement.setString(index++, staffFirstName.toUpperCase() + "%");
            }

            if (staffLastName != null && staffLastName.length() != 0) {
                preparedStatement.setString(index++, staffLastName.toUpperCase() + "%");
            }

            if (staffId != null && staffId.length() != 0) {
                preparedStatement.setString(index++, staffId.toUpperCase() + "%");
            }

            if (staffCoordinator != null && staffCoordinator.length() != 0) {
                preparedStatement.setString(index++, staffCoordinator.toUpperCase() + "%");
            }

            if (scheduledHours != null && scheduledHours >= 0) {
                preparedStatement.setDouble(index++, scheduledHours);
            }

            //dmr--GEOR-3510: Temporary implementation to support current UI design.
            if (visitSK != null) {
                preparedStatement.setLong(index++, visitSK);
            }

            if (exceptionIdList != null) {
                for (BigInteger excpId : exceptionIdList) {
                    preparedStatement.setBigDecimal(index++, new BigDecimal(excpId));
                }
            }

            preparedStatement.setString(index++, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            List<FindVisitResult> findVisitResultList = new ArrayList<>();

            Response response = new Response();
            response.setOrderByColumn(orderByString);
            response.setOrderByDirection(orderByDirection.toUpperCase());
            response.setData(findVisitResultList);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                findVisitResultList.add(mapVisits(resultSet, businessObject.getCompany(), businessObject.getLocations()));
            }

            return response;

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Find Visit details by visitSk
     *
     * @param visitSk
     * @return
     * @throws SandataRuntimeException
     */
    public Response findVisitDetails(final String bsnEntId,
                                     final Long visitSk,
                                     final String orderByColumn,
                                     final String orderByDirection,
                                     final int page,
                                     final int pageSize) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String orderByString = "v.visit_sk"; // Default
        switch (orderByColumn) {
            case "vsk":
                orderByString = "v.visit_sk";
                break;
        }

        try {
            String visitSkFilter = visitSk != null ? " AND v.visit_sk = ? " : "";

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            BusinessObject businessObject = getBusinessEntityIDGeneral(connection, bsnEntId);

            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sql = String.format(
                    "  SELECT *" +
                            "  FROM (SELECT ROWNUM row_number," +
                            "               COUNT(*) over() total_rows," +
                            "               v.visit_sk," +
                            "               v.visit_act_start_tmstp," +
                            "               v.visit_act_end_tmstp," +
                            "               v.visit_adj_start_tmstp," +
                            "               v.visit_adj_end_tmstp," +
                            "               v.visit_status_name, " +
                            "               v.visit_bill_hrs, " +
                            "               v.visit_do_not_bill_ind," +
                            "               round(((v.visit_act_end_tmstp - v.visit_act_start_tmstp) * 24), 2) call_hrs," +
                            "               nvl(se.tz_name, p.tz_name) tz_name," +
                            "               pyr.payer_id," +
                            "               pyr.payer_name," +
                            "               cntr.contr_id," +
                            "               cntr.contr_desc," +
                            "               vs.svc_name," +
                            "               vs.rate_typ_name, " +
                            "               vs.rate_qlfr_code," +
                            "               srv.billing_code," +
                            "               ve.visit_evnt_pt_cnfrm_qlfr," +
                            "               vs.visit_svc_cnfrm_qlfr," +
                            "               pds.doc_id pt_dig_signature_doc_id," +
                            "               pas.doc_id pt_aud_signature_doc_id" +
                            "          FROM visit v" +
                            "          LEFT JOIN visit_evnt ve ON ve.visit_sk = v.visit_sk" +
                            "                                 AND ve.call_out_ind = 1" +
                            "                                 AND ve.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                 AND ve.curr_rec_ind = 1" +
                            "        " +
                            "          LEFT JOIN pt p ON p.be_id = v.be_id" +
                            "                        AND p.pt_id = v.pt_id" +
                            "                        AND p.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                        AND p.curr_rec_ind = 1" +
                            "        " +
                            "          LEFT JOIN visit_svc vs ON vs.visit_sk = v.visit_sk" +
                            "                                AND vs.be_id = v.be_id" +
                            "                                AND vs.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                AND vs.curr_rec_ind = 1" +
                            "          " +
                            "          LEFT JOIN svc srv ON srv.be_id = vs.be_id" +
                            "                           AND srv.svc_name = vs.svc_name" +
                            "                           AND srv.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                           AND srv.curr_rec_ind = 1" +
                            "        " +
                            "          LEFT JOIN pt_payer ptpyr ON ptpyr.be_id = v.be_id" +
                            "                                  AND ptpyr.pt_id = v.pt_id" +
                            "                                  AND ptpyr.svc_name = vs.svc_name" +
                            "                                  AND ptpyr.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                  AND ptpyr.curr_rec_ind = 1" +
                            "                                  AND ptpyr.payer_rank_name = 'PRIMARY'" +
                            "        " +
                            "          LEFT JOIN payer pyr ON pyr.be_id = v.be_id" +
                            "                             AND pyr.payer_id = ptpyr.payer_id" +
                            "                             AND pyr.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                             AND pyr.curr_rec_ind = 1" +
                            "        " +
                            "          LEFT JOIN contr cntr ON cntr.be_id = v.be_id" +
                            "                              AND cntr.payer_id = ptpyr.payer_id" +
                            "                              AND cntr.contr_id = ptpyr.contr_id" +
                            "                              AND cntr.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                              AND cntr.curr_rec_ind = 1" +
                            "        " +
                            "        " +
                            "          LEFT JOIN staff st ON st.be_id = v.be_id" +
                            "                            AND st.staff_id = v.staff_id" +
                            "                            AND st.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                            AND st.curr_rec_ind = 1" +
                            "        " +
                            "        " +
                            "          LEFT JOIN sched_evnt se ON se.sched_evnt_sk = v.sched_evnt_sk" +
                            "                                 AND se.be_id = v.be_id" +
                            "                                 AND se.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                 AND se.curr_rec_ind = 1" +
                            "        " +
                            "          LEFT JOIN visit_doc_xwalk pds ON pds.be_id = v.be_id" +
                            "                                       AND pds.visit_sk = v.visit_sk" +
                            "                                       AND pds.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                       AND pds.curr_rec_ind = 1" +
                            "                                       AND upper(pds.doc_clas_name) = 'PATIENT DIGITAL SIGNATURE'" +
                            "        " +
                            "          LEFT JOIN visit_doc_xwalk pas ON pas.be_id = v.be_id" +
                            "                                       AND pas.visit_sk = v.visit_sk" +
                            "                                       AND pas.rec_term_tmstp = to_date('31-dec-9999 00:00:00', 'dd-mon-rrrr hh24:mi:ss')" +
                            "                                       AND pas.curr_rec_ind = 1" +
                            "                                       AND upper(pas.doc_clas_name) = 'PATIENT AUDIO SIGNATURE'" +
                            "         WHERE v.be_id = ? " + visitSkFilter +
                            "         ORDER BY %s %s )" +
                            "  WHERE row_number BETWEEN %d AND %d", orderByString, orderByDirection, fromRow, toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            if (visitSk != null) {
                preparedStatement.setLong(index++, visitSk);
            }

            resultSet = preparedStatement.executeQuery();

            List<FindVisitDetailsResult> resultList = new ArrayList<>();
            Response response = new Response();
            response.setOrderByColumn(orderByString);
            response.setData(resultList);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                resultList.add(mapVisitDetails(resultSet, businessObject.getCompany()));
            }

            return response;

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
                    e.getClass().getName(), e.getMessage()));

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
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * Update Visit Details
     *
     * @param visitDetails
     * @throws SandataRuntimeException
     */
    public long updateVisitDetails(final FindVisitDetailsResult visitDetails) throws SandataRuntimeException {
        Connection connection = null;

        try {
            long visitSk = visitDetails.getVisitSk();
            long result = 0;

            // Get old Visit
            OracleMetadata visitOracleMetaData = DataMapper.getOracleMetadata(new Visit());
            List<Visit> oldVisitList = (List) executeGet(
                    visitOracleMetaData.packageName(),
                    visitOracleMetaData.getMethod(),
                    "com.sandata.lab.data.model.dl.model.Visit",
                    visitSk);

            if (oldVisitList == null || oldVisitList.isEmpty()) {
                throw new SandataRuntimeException("Visit (visit_sk=" + visitSk + ") not found");
            }
            Visit oldVisit = oldVisitList.get(0);

            connection = getOracleConnection();
            connection.setAutoCommit(false);

            Date currentDate = new Date();

            // Get old Visit Service
            if (!StringUtil.IsNullOrEmpty(visitDetails.getServiceName())) {
                OracleMetadata visitServiceOracleMetaData = DataMapper.getOracleMetadata(new VisitService());
                List<VisitService> oldVisitServiceList = (List) executeGet(
                        visitServiceOracleMetaData.packageName(),
                        visitServiceOracleMetaData.getMethod(),
                        "com.sandata.lab.data.model.dl.model.VisitService",
                        new Object[]{oldVisit.getVisitSK(), oldVisit.getBusinessEntityID()});

                VisitService oldVisitService;
                if (oldVisitServiceList != null && !oldVisitServiceList.isEmpty()) {
                    oldVisitService = oldVisitServiceList.get(0);

                    // Service Name has been changed
                    if (!visitDetails.getServiceName().equals(oldVisitService.getServiceName())) {

                        // Logically delete old record
                        oldVisitService.setRecordUpdateTimestamp(currentDate);
                        oldVisitService.setRecordTerminationTimestamp(currentDate);
                        oldVisitService.setRecordUpdatedBy("VISIT DATA SERVICE");
                        VisitSvcT visitSvcJpubType = (VisitSvcT) new DataMapper().map(oldVisitService);
                        result = execute(connection, visitServiceOracleMetaData.packageName(), visitServiceOracleMetaData.updateMethod(), visitSvcJpubType);
                        if (result < 0) {
                            throw new SandataRuntimeException("Cannot logically delete VisitService (VisitServiceSK=" + oldVisitService.getVisitServiceSK() + ")");
                        }

                        // Create new record
                        oldVisitService.setServiceName(visitDetails.getServiceName());
                        oldVisitService.setRateTypeName(visitDetails.getServiceRateType());
                        if (!StringUtil.IsNullOrEmpty(visitDetails.getServiceRateQualifierCode())) {
                            oldVisitService.setRateQualifierCode(RateQualifierCode.valueOf(visitDetails.getServiceRateQualifierCode()));
                        }
                        oldVisitService.setRecordCreateTimestamp(currentDate);
                        oldVisitService.setRecordEffectiveTimestamp(currentDate);
                        oldVisitService.setRecordTerminationTimestamp(null);
                        oldVisitService.setRecordCreatedBy("VISIT DATA SERVICE");
                        visitSvcJpubType = (VisitSvcT) new DataMapper().map(oldVisitService);
                        result = execute(connection, visitServiceOracleMetaData.packageName(), visitServiceOracleMetaData.insertMethod(), visitSvcJpubType);
                        if (result < 0) {
                            throw new SandataRuntimeException("Cannot insert new VisitService");
                        }
                    }

                } else { // There was no VisitService, create new VisitService from what user selected in UI
                    VisitService newVisitService = new VisitService();
                    newVisitService.setRecordCreateTimestamp(currentDate);
                    newVisitService.setRecordUpdateTimestamp(currentDate);
                    newVisitService.setRecordEffectiveTimestamp(currentDate);
                    newVisitService.setRecordCreatedBy("VISIT DATA SERVICE");
                    newVisitService.setRecordUpdatedBy("VISIT DATA SERVICE");
                    newVisitService.setBusinessEntityID(oldVisit.getBusinessEntityID());
                    newVisitService.setVisitSK(oldVisit.getVisitSK());
                    newVisitService.setServiceName(visitDetails.getServiceName());
                    newVisitService.setRateTypeName(visitDetails.getServiceRateType());
                    if (!StringUtil.IsNullOrEmpty(visitDetails.getServiceRateQualifierCode())) {
                        newVisitService.setRateQualifierCode(RateQualifierCode.valueOf(visitDetails.getServiceRateQualifierCode()));
                    }

                    VisitSvcT visitSvcJpubType = (VisitSvcT) new DataMapper().map(newVisitService);
                    result = execute(connection, visitServiceOracleMetaData.packageName(), visitServiceOracleMetaData.insertMethod(), visitSvcJpubType);
                    if (result < 0) {
                        throw new SandataRuntimeException("Cannot insert new VisitService");
                    }
                }
            }

            oldVisit.setRecordUpdatedBy("VISIT DATA SERVICE");
            oldVisit.setRecordUpdateTimestamp(currentDate);
            oldVisit.setVisitAdjustedStartTimestamp(visitDetails.getAdjustedIn());
            oldVisit.setVisitAdjustedEndTimestamp(visitDetails.getAdjustedOut());
            oldVisit.setVisitBillHours(visitDetails.getBillHours());
            oldVisit.setVisitDoNotBillIndicator(visitDetails.isVisitDoNotBillIndicator());

            VisitT visitJpubType = (VisitT) new DataMapper().map(oldVisit);
            result = execute(connection, visitOracleMetaData.packageName(), visitOracleMetaData.updateMethod(), visitJpubType);
            if (result < 0) {
                throw new SandataRuntimeException("Cannot update Visit (VisitSK=" + oldVisit.getVisitSK() + ")");
            }

            connection.commit();

            return result;

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }

    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, Object... params)
            throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = String.format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public BigDecimal getBusinessEntitySK(final String businessEntityId) throws SandataRuntimeException {

        BigDecimal bsnEntSk = businessEntityMapSK.get(businessEntityId);
        if (bsnEntSk == null) {
            bsnEntSk = DataHelper.getBusinessEntitySK(businessEntityId, this);
            businessEntityMapSK.put(businessEntityId, bsnEntSk);
        }

        return bsnEntSk;
    }

    private BusinessObject getBusinessEntityIDGeneral(final Connection connection, final String businessEntityId) throws SandataRuntimeException {

        BusinessObject businessObject = new BusinessObject();
        businessObject.setBsnEntId(businessEntityId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT B1.BE_SK AS COMPANY_BE_SK, B1.BE_NAME AS COMPANY, " +
                "        B2.BE_PAR_ID, B2.BE_ID AS LOCATION_BE_ID, " +
                "        B3.BE_NAME AS LOCATION FROM BE B1 " +
                "JOIN (SELECT BE_ID,BE_PAR_ID " +
                "  FROM BE_REL WHERE UPPER(BE_REL_STATUS) = 'ACTIVE' AND UPPER(BE_REL_TYP) = 'BE_LOCATION' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                ") B2 " +
                "ON B1.BE_ID = B2.BE_PAR_ID " +
                "JOIN (SELECT BE_ID,BE_NAME " +
                "  FROM BE WHERE UPPER(BE_TYP) = 'BE_LOCATION' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) B3 " +
                "ON B2.BE_ID = B3.BE_ID " +
                "WHERE B1.BE_ID = ? AND (TO_CHAR(B1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND B1.CURR_REC_IND = 1)";

        try {

            preparedStatement = connection.prepareStatement(sql);
            int index = 1;
            preparedStatement.setString(index, businessEntityId);

            resultSet = preparedStatement.executeQuery();

            List locations = new ArrayList<>();

            if (resultSet.next()) {
                businessObject.setCompany(resultSet.getString("COMPANY"));
                locations.add(resultSet.getString("LOCATION"));
            }

            while (resultSet.next()) {
                locations.add(resultSet.getString("LOCATION"));
            }

            businessObject.setLocations(locations);

            return businessObject;

        } catch (Exception e) {

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
        }
    }

    public Object getEntitiesForId(final String sql, final String className, final Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public long acceptCalls(List<VisitEvent> visitEvents, ProducerVisitHandler producerVisitHandler) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            if (visitEvents == null) {
                throw new SandataRuntimeException("acceptCalls: visitEvents == null");
            }

            if (visitEvents.size() == 0) {
                throw new SandataRuntimeException("acceptCalls: visitEvents.size() == 0");
            }

            if (visitEvents.size() != 2) {
                throw new SandataRuntimeException("acceptCalls: Expecting two visits only!");
            }

            VisitEvent visitEvent1 = visitEvents.get(0);
            if (visitEvent1.getVisitEventDatetime() == null) {
                throw new SandataRuntimeException("acceptCalls: visitEvent1.getVisitEventDatetime() == null");
            }

            VisitEvent visitEvent2 = visitEvents.get(1);
            if (visitEvent2.getVisitEventDatetime() == null) {
                throw new SandataRuntimeException("acceptCalls: visitEvent2.getVisitEventDatetime() == null");
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = "{?=call PKG_VISIT_UTIL.CLEAR_VISIT_EVT_CALL_IN_OUT(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            callableStatement.setLong(2, visitEvent1.getVisitSK().longValue());

            callableStatement.execute();

            long result = callableStatement.getLong(1);
            if (result <= 0) {
                throw new SandataRuntimeException(String.format("acceptCalls: executeUpdate: ALL CALLS: [RESULT=%d]", result));
            }

            callableStatement.close();

            java.util.Date startTime = null;
            java.util.Date endTime = null;

            // Update Call IN
            callMethod = "{?=call PKG_VISIT_UTIL.SET_VISIT_EVT_CALL_IN(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);

            if (visitEvent1.getVisitEventDatetime().before(visitEvent2.getVisitEventDatetime())) {
                callableStatement.setLong(2, visitEvent1.getVisitEventSK().longValue());
                startTime = visitEvent1.getVisitEventDatetime();
            } else {
                callableStatement.setLong(2, visitEvent2.getVisitEventSK().longValue());
                startTime = visitEvent2.getVisitEventDatetime();
            }

            callableStatement.execute();

            result = callableStatement.getLong(1);
            if (result <= 0) {
                throw new SandataRuntimeException(String.format("acceptCalls: executeUpdate: CALL IN: [RESULT=%d]", result));
            }

            callableStatement.close();

            // Update Call OUT
            callMethod = "{?=call PKG_VISIT_UTIL.SET_VISIT_EVT_CALL_OUT(?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            if (visitEvent1.getVisitEventDatetime().after(visitEvent2.getVisitEventDatetime())) {
                callableStatement.setLong(2, visitEvent1.getVisitEventSK().longValue());
                endTime = visitEvent1.getVisitEventDatetime();
            } else {
                callableStatement.setLong(2, visitEvent2.getVisitEventSK().longValue());
                endTime = visitEvent2.getVisitEventDatetime();

            }

            callableStatement.execute();

            result = callableStatement.getLong(1);
            if (result <= 0) {
                throw new SandataRuntimeException(String.format("acceptCalls: executeUpdate: CALL OUT: [RESULT=%d]", result));
            }
            callableStatement.close();

            // Update visit
            callMethod = "{?=call PKG_VISIT_UTIL.UPDATE_VISIT_ACT_STRT_END_TIME(?,?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);

            int index = 2;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            callableStatement.setString(index++, simpleDateFormat.format(startTime));
            callableStatement.setString(index++, simpleDateFormat.format(endTime));
            callableStatement.setLong(index, visitEvent2.getVisitSK().longValue());

            callableStatement.execute();
            result = callableStatement.getLong(1);

            if (result <= 0) {
                throw new SandataRuntimeException(String.format("acceptCalls: executeUpdate: CALL OUT: [RESULT=%d]", result));
            }

            connection.commit();
            this.updateExceptions(visitEvent2.getVisitSK(), producerVisitHandler);
            return result;

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
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    private void updateExceptions(BigInteger visitSk, ProducerVisitHandler producerVisitHandler) {

        producerVisitHandler.sendVisitSKForExceptionsToClear(visitSk);

        long sequenceKey = visitSk.longValue();
        List<Visit> list = (List) this.executeGet("PKG_VISIT", "getVisit", "com.sandata.lab.data.model.dl.model.Visit", sequenceKey);
        Visit visit = null;
        if (list != null && list.size() > 0) {
            visit = (Visit) list.get(0);
            List<VisitTaskList> visitTaskList = (List<VisitTaskList>) executeGet(
                    "PKG_VISIT",
                    "getVisitTaskLst",
                    "com.sandata.lab.data.model.dl.model.VisitTaskList",
                    visit.getVisitSK());

            for (VisitTaskList item : visitTaskList) {

                //dmr--REMINDER: GEOR-1053:
                VisitTaskListExt visitTaskListExt = new VisitTaskListExt(item);
                visit.getVisitTaskList().add(visitTaskListExt);
            }
            producerVisitHandler.sendVisit(visit);
        }

    }

    public Response getVisitHistDetail(
            Long visitSk,
            int page,
            int pageSize,
            String sortOn,
            String direction) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        String orderByColumn = "CHANGED_ON";
        switch (sortOn.toLowerCase()) {
            case "first_name":
                orderByColumn = "USER_FIRST_NAME";
                break;
            case "last_name":
                orderByColumn = "USER_LAST_NAME";
                break;
            case "data_point":
                orderByColumn = "DATA_POINT";
                break;
            case "changed_from":
                orderByColumn = "CHANGED_FROM";
                break;
            case "changed_to":
                orderByColumn = "CHANGED_TO";
                break;
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT " +
                    "    T1.BE_ID, " +
                    "    T2.VISIT_EXCP_SK, " +
                    "    T3.VISIT_ACTIVITY_SK, " +
                    "    T4.VISIT_AUTH_SK, " +
                    "    T5.VISIT_DOC_XWALK_SK, " +
                    "    T6.VISIT_EVNT_SK, " +
                    "    T7.VISIT_HIST_SK, " +
                    "    T8.VISIT_NOTE_SK, " +
                    "    T9.VISIT_TASK_LST_SK " +
                    "FROM VISIT T1 " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_EXCP_SK " +
                    "  FROM VISIT_EXCP " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "ON T1.VISIT_SK = T2.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_ACTIVITY_SK " +
                    "  FROM VISIT_ACTIVITY) T3 " +
                    "ON T1.VISIT_SK = T3.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_AUTH_SK " +
                    "  FROM VISIT_AUTH) T4 " +
                    "ON T1.VISIT_SK = T4.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_DOC_XWALK_SK " +
                    "  FROM VISIT_DOC_XWALK " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                    "ON T1.VISIT_SK = T5.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_EVNT_SK " +
                    "  FROM VISIT_EVNT " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T6 " +
                    "ON T1.VISIT_SK = T6.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_HIST_SK " +
                    "  FROM VISIT_HIST) T7 " +
                    "ON T1.VISIT_SK = T7.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_NOTE_SK " +
                    "  FROM VISIT_NOTE " +
                    "    WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T8 " +
                    "ON T1.VISIT_SK = T8.VISIT_SK " +
                    " " +
                    "LEFT JOIN (SELECT VISIT_SK,VISIT_TASK_LST_SK " +
                    "  FROM VISIT_TASK_LST) T9 " +
                    "ON T1.VISIT_SK = T9.VISIT_SK " +
                    " " +
                    "WHERE T1.VISIT_SK = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            String bsnEntId = null;
            VisitRelatedSK visitRelatedSK = null;
            while (resultSet.next()) {

                if (bsnEntId == null) {
                    bsnEntId = resultSet.getString("BE_ID");
                    visitRelatedSK = new VisitRelatedSK(bsnEntId, visitSk);
                }

                visitRelatedSK.addVisitException(resultSet.getBigDecimal("VISIT_EXCP_SK"));
                visitRelatedSK.addVisitActivities(resultSet.getBigDecimal("VISIT_ACTIVITY_SK"));
                visitRelatedSK.addVisitAuth(resultSet.getBigDecimal("VISIT_AUTH_SK"));
                visitRelatedSK.addVisitDocXwalk(resultSet.getBigDecimal("VISIT_DOC_XWALK_SK"));
                visitRelatedSK.addVisitEvents(resultSet.getBigDecimal("VISIT_EVNT_SK"));
                visitRelatedSK.addVisitHistory(resultSet.getBigDecimal("VISIT_HIST_SK"));
                visitRelatedSK.addVisitNotes(resultSet.getBigDecimal("VISIT_NOTE_SK"));
                visitRelatedSK.addVisitTasks(resultSet.getBigDecimal("VISIT_TASK_LST_SK"));
            }

            // Clean Up
            resultSet.close();
            preparedStatement.close();
            this.connectionPoolDataService.close(connection);

            Response response = new Response();

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("METADATA.STRING_ARRAY", connection);
            ARRAY hostKeysArray = new ARRAY(des, connection, visitRelatedSK.getParams());

            String callMethod = "{?=call METADATA.PKG_AUDIT.GET_CHANGES_PAGING(?,?,?,?,?)}";

            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, hostKeysArray);
            callableStatement.setObject(3, orderByColumn);
            callableStatement.setObject(4, direction);
            callableStatement.setObject(5, fromRow);
            callableStatement.setObject(6, toRow);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            List<AuditChanged> auditChangedList = new ArrayList<>();
            response.setData(auditChangedList);

            while (resultSet.next()) {

                AuditChanged auditChanged = new AuditChanged();

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                String userFirstName = resultSet.getString("USER_FIRST_NAME");
                if (StringUtil.IsNullOrEmpty(userFirstName)) {
                    auditChanged.setUserFirstName("George"); // Default if NULL
                } else {
                    auditChanged.setUserFirstName(userFirstName);
                }

                String userLastName = resultSet.getString("USER_LAST_NAME");
                if (StringUtil.IsNullOrEmpty(userLastName)) {
                    auditChanged.setUserLastName("Sandata"); // Default if NULL
                } else {
                    auditChanged.setUserLastName(userLastName);
                }

                auditChanged.setDataPoint(resultSet.getString("DATA_POINT"));
                auditChanged.setChangedFrom(resultSet.getString("CHANGED_FROM"));
                auditChanged.setChangedTo(resultSet.getString("CHANGED_TO"));

                Timestamp changedOn = resultSet.getTimestamp("CHANGED_ON");
                if (changedOn != null) {
                    auditChanged.setChangedOn(new java.util.Date(changedOn.getTime()));
                }

                auditChangedList.add(auditChanged);
            }

            response.setOrderByColumn(orderByColumn);

            return response;

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
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    /**
     * @param visitSk
     * @param bsntId
     * @return
     * @throws SandataRuntimeException
     */
    public Object getVisitNoteList(Long visitSk, String bsntId) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            String sql = "SELECT VISIT_NOTE_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP,REC_CREATED_BY,REC_UPDATED_BY"
                    + ",CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,VISIT_SK,VISIT_NOTE_TYP_NAME,VISIT_NOTE FROM VISIT_NOTE"
                    + " WHERE VISIT_SK=? AND BE_ID=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') "
                    + " ORDER BY REC_CREATE_TMSTP DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);
            preparedStatement.setString(2, bsntId);

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitNote");
            return result;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public Object getEntitiesForId(final String sql, Connection connection, final String className, final Object... params) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            Object result = new DataMapper().map(resultSet, className);

            return result;

        } catch (Exception e) {
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
        }
    }

    public AuditVisit getAuditVisit(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql =
                    "      SELECT " +
                            "        T1.VISIT_SK," +
                            "        T1.BE_ID," +
                            "        T1.CHANGE_REASON_CODE," +
                            "        T1.CHANGE_REASON_MEMO," +
                            "        T1.PT_ID," +
                            "        T3.PT_FIRST_NAME," +
                            "        T3.PT_LAST_NAME," +
                            "        T1.STAFF_ID," +
                            "        T2.STAFF_FIRST_NAME," +
                            "        T2.STAFF_LAST_NAME," +
                            "        T8.SVC_NAME," +
                            "        T4.SCHED_EVNT_START_DTIME," +
                            "        T4.SCHED_EVNT_END_DTIME," +
                            "        T1.VISIT_ACT_START_TMSTP," +
                            "        T1.VISIT_ACT_END_TMSTP," +
                            "        T1.VISIT_ADJ_START_TMSTP," +
                            "        T1.VISIT_ADJ_END_TMSTP," +
                            "        ROUND(((T4.SCHED_EVNT_END_DTIME-T4.SCHED_EVNT_START_DTIME)*24),2) SCD_HRS," +
                            "        ROUND(((T1.VISIT_ACT_END_TMSTP -T1.VISIT_ACT_START_TMSTP)*24),2) CALL_HRS," +
                            "        ROUND(((T1.VISIT_ADJ_END_TMSTP -T1.VISIT_ADJ_START_TMSTP)*24),2) ADJ_HRS," +
                            "        T1.VISIT_STATUS_NAME," +
                            "        T1.VISIT_PAY_HRS," +
                            "        T1.VISIT_BILL_HRS," +
                            "        T1.VISIT_OT_ABS_HRS," +
                            "        NVL(T1.VISIT_APRVD_IND, 0)         AS VISIT_APRVD_IND," +
                            "        NVL(T1.VISIT_VFYD_BY_SCHED_IND, 0) AS VISIT_VFYD_BY_SCHED_IND," +
                            "        NVL(T1.VISIT_PAY_BY_SCHED_IND, 0) AS VISIT_PAY_BY_SCHED_IND" +

                            "      FROM VISIT T1" +
                            "      LEFT JOIN" +
                            "        (SELECT BE_ID," +
                            "          PT_ID," +
                            "          PT_FIRST_NAME," +
                            "          PT_LAST_NAME," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND" +
                            "        FROM PT" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T3" +
                            "      ON T1.PT_ID  = T3.PT_ID" +
                            "      AND T1.BE_ID = T3.BE_ID" +

                            "      LEFT JOIN" +
                            "        (SELECT BE_ID," +
                            "          STAFF_ID," +
                            "          STAFF_FIRST_NAME," +
                            "          STAFF_LAST_NAME," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND" +
                            "        FROM STAFF" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T2" +
                            "      ON T1.STAFF_ID = T2.STAFF_ID" +
                            "      AND T1.BE_ID   = T2.BE_ID" +

                            "      LEFT JOIN" +
                            "        (SELECT SCHED_EVNT_SK," +
                            "          BE_ID," +
                            "          SCHED_SK," +
                            "          SCHED_EVNT_START_DTIME," +
                            "          SCHED_EVNT_END_DTIME," +
                            "          REC_TERM_TMSTP," +
                            "          CURR_REC_IND, TZ_NAME" +
                            "        FROM SCHED_EVNT" +
                            "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31'" +
                            "        AND CURR_REC_IND                             = 1)" +
                            "        ) T4" +
                            "      ON T1.SCHED_EVNT_SK = T4.SCHED_EVNT_SK" +
                            "      AND T1.BE_ID        = T4.BE_ID" +

                            "      LEFT JOIN" +
                            "        (SELECT SCHED_SK,SVC_SK FROM SCHED_SVC" +
                            "        ) T7" +
                            "      ON T4.SCHED_SK = T7.SCHED_SK" +
                            "      LEFT JOIN" +
                            "        (SELECT SVC_SK,BE_ID,SVC_NAME FROM SVC WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                            "        ) T8" +
                            "      ON T7.SVC_SK = T8.SVC_SK" +

                            "      WHERE T1.VISIT_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);

            resultSet = preparedStatement.executeQuery();

            AuditVisit result = mapAuditVisit(resultSet);

            return result;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private AuditVisit mapAuditVisit(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            AuditVisit auditVisit = new AuditVisit();

            auditVisit.setBusinessEntityID(resultSet.getString("BE_ID"));
            auditVisit.setVisitSk(resultSet.getBigDecimal("VISIT_SK").longValue());
            auditVisit.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
            auditVisit.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));

            auditVisit.setPatientId(resultSet.getString("PT_ID"));
            auditVisit.setPatientName(
                    getFullName(resultSet.getString("PT_FIRST_NAME"), resultSet.getString("PT_LAST_NAME")));

            auditVisit.setStaffId(resultSet.getString("STAFF_ID"));
            auditVisit.setStaffName(
                    getFullName(resultSet.getString("STAFF_FIRST_NAME"), resultSet.getString("STAFF_LAST_NAME")));

            auditVisit.setService(resultSet.getString("SVC_NAME"));

            // schedule in
            Timestamp scheduleEventStartDate = resultSet.getTimestamp("SCHED_EVNT_START_DTIME");
            if (scheduleEventStartDate != null) {
                auditVisit.setScheduleIn(new java.util.Date(scheduleEventStartDate.getTime()));
            }

            // schedule out
            Timestamp scheduleEventEndDate = resultSet.getTimestamp("SCHED_EVNT_END_DTIME");
            if (scheduleEventEndDate != null) {
                auditVisit.setScheduleOut(new java.util.Date(scheduleEventEndDate.getTime()));
            }

            auditVisit.setScheduledHours(resultSet.getString("SCD_HRS"));

            // call in
            Timestamp startDate = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
            if (startDate != null) {
                auditVisit.setCallIn(new java.util.Date(startDate.getTime()));
            }

            // call out
            Timestamp endDate = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
            if (endDate != null) {
                auditVisit.setCallOut(new java.util.Date(endDate.getTime()));
            }

            auditVisit.setCallHours(resultSet.getString("CALL_HRS"));

            // adjusted in
            Timestamp adjStartDate = resultSet.getTimestamp("VISIT_ADJ_START_TMSTP");
            if (adjStartDate != null) {
                auditVisit.setAdjustIn(new java.util.Date(adjStartDate.getTime()));
            }

            // adjusted out
            Timestamp adjEndDate = resultSet.getTimestamp("VISIT_ADJ_END_TMSTP");
            if (adjEndDate != null) {
                auditVisit.setAdjustOut(new java.util.Date(adjEndDate.getTime()));
            }

            auditVisit.setAdjustHours(resultSet.getString("ADJ_HRS"));

            auditVisit.setPayHours(resultSet.getBigDecimal("VISIT_PAY_HRS"));
            auditVisit.setBillHours(resultSet.getBigDecimal("VISIT_BILL_HRS"));

            auditVisit.setStatus(resultSet.getString("VISIT_STATUS_NAME"));

            auditVisit.setVerifiedByScheduleIndicator(resultSet.getBoolean("VISIT_VFYD_BY_SCHED_IND"));
            auditVisit.setPayByScheduleIndicator(resultSet.getBoolean("VISIT_PAY_BY_SCHED_IND"));
            auditVisit.setOvertimeAbsenceHours(resultSet.getBigDecimal("VISIT_OT_ABS_HRS"));
            auditVisit.setApprovedIndicator(resultSet.getBoolean("VISIT_APRVD_IND"));

            return auditVisit;
        }

        return null;
    }

    private String getFullName(String firstName, String lastName) {
        if (StringUtil.IsNullOrEmpty(lastName)) {
            return null;
        }

        return lastName + (StringUtil.IsNullOrEmpty(firstName) ? "" : ", " + firstName);
    }

    public int insertAuditVisitHistory(String userGuid, AuditVisit oldVisit, AuditVisit newVisit) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
/*
            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(false);

            AuditVisitHistory auditVisitHistory = getAuditVisitHistory(userGuid, oldVisit, newVisit);
            Object jpubType = new DataMapper().map(auditVisitHistory);

            String callMethod = String.format("{?=call %s.PKG_AUDIT.INSERT_AUDIT_VISIT_HIST(?)", ConnectionType.METADATA);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();

            int resultVal = callableStatement.getInt(1);

            connection.commit();

            return resultVal;
*/
return 1;
        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // TODO: should not throw exception here, just log errors in order to not stop insert/update/delete action
            // for Visit, Exception, Task, Note
            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()));

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public long getVisitAuthSkByVisitSk(long visitSk) {
        String sql = "SELECT AUTH_SK FROM VISIT_AUTH WHERE VISIT_SK = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long result = -999;
        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                result = resultSet.getLong(1);
            }

            return result;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    public long getVisitWithVVBillableHrs(long visitSk) {
        long retValue = 0;
        Object result;
        //PKG_VISIT_getVisitAuth_VisitAuthorization
        long authSk = getVisitAuthSkByVisitSk(visitSk);
        if (authSk > 0) {
            //PKG_VISIT_getVisit_Visit
            String className = Visit.class.getName();
            result = executeGet(
                    "PKG_VISIT",
                    "getVisit",
                    className,
                    visitSk
            );
            Visit visit = null;
            if (result != null) {
                if (result instanceof ArrayList) {
                    visit = (Visit) ((ArrayList) result).get(0);
                } else if (result instanceof Visit) {
                    visit = (Visit) result;
                }
            } else {
                return 0;
            }
            String sql = "SELECT VV_RNDING_RULE_ID FROM " +
                    "(SELECT CONTR.VV_RNDING_RULE_ID FROM CONTR JOIN AUTH ON CONTR.CONTR_ID = AUTH.CONTR_ID AND CONTR.BE_ID = AUTH.BE_ID AND CONTR.REC_TERM_TMSTP = AUTH.REC_TERM_TMSTP AND CONTR.CURR_REC_IND = AUTH.CURR_REC_IND WHERE " +
                    "(TO_CHAR(CONTR.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CONTR.CURR_REC_IND = '1' ) AND CONTR.BE_ID = ? AND AUTH.AUTH_SK = ? )";

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            String bsntId = visit.getBusinessEntityID();
            String vvRoundingRule = null;
            if ((visit.getVisitDoNotBillIndicator() != null && visit.getVisitDoNotBillIndicator()) || (visit.getVisitActualStartTimestamp() == null || visit.getVisitActualEndTimestamp() == null)) {
                return 0;
            }
            try {
                connection = connectionPoolDataService.getConnection();
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, bsntId);
                preparedStatement.setLong(2, authSk);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.isBeforeFirst()) {
                    resultSet.next();
                    vvRoundingRule = resultSet.getString(1);
                }
                if (vvRoundingRule == null || vvRoundingRule.isEmpty()) {
                    retValue = 0;
                } else {
                    long diff = visit.getVisitActualEndTimestamp().getTime() - visit.getVisitActualStartTimestamp().getTime();

                    switch (vvRoundingRule) {
                        case "N":
                            long diffseconds = diff / 1000;
                            visit.setVisitBillHours(new BigDecimal(diffseconds));
                            break;
                        case "A":
                            long diffminutes = diff / (1000 * 60);
                            diffminutes = diffminutes * 60; //time in whole minutes rounded but need to store in seconds.
                            visit.setVisitBillHours(new BigDecimal(diffminutes));
                            break;
                        default:
                            //TBD CALL VisitRoundingrule fromula here to get billing value.

                            break;
                    }
                    visit.setUserGloballyUniqueIdentifier(UUID.randomUUID().toString());
                    VisitT jpubType = (VisitT) (new DataMapper().map(visit));
                    OracleMetadata oracleMetaData = DataMapper.getOracleMetadata(visit);
                    String pkgVisit = oracleMetaData.packageName();
                    String updateMethod = oracleMetaData.updateMethod();
                    retValue = execute(pkgVisit, updateMethod, jpubType);
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
                this.connectionPoolDataService.close(connection);

            }
        }
        return retValue;
    }

    public AuditVisit getAuditVisitForExceptions(long visitSk) {
        AuditVisit auditVisit = getAuditVisitWithBasicInfo(visitSk);

        if (auditVisit != null) {
            auditVisit.getVisitExceptions().addAll(getAuditVisitExceptions(visitSk));

            return auditVisit;
        }

        return null;
    }

    private List<AuditVisitException> getAuditVisitExceptions(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql =
                    " SELECT VISIT_EXCP.EXCP_ID, ExceptionLookup.EXCP_NAME " +
                            " " +
                            " FROM VISIT_EXCP " +
                            "   JOIN (SELECT EXCP_ID, EXCP_NAME, EXCP_DESC FROM EXCP_LKUP " +
                            "         WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            AND APP_MOD_NAME = 'VISIT VERIFICATION') ExceptionLookup " +
                            "       ON VISIT_EXCP.EXCP_ID = ExceptionLookup.EXCP_ID " +
                            " " +
                            " WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "   AND VISIT_SK = ? " +
                            " " +
                            " ORDER BY VISIT_EXCP_SK ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<AuditVisitException> auditVisitExceptions = new ArrayList<AuditVisitException>();

            while (resultSet.next()) {
                auditVisitExceptions.add(mapVisitException(resultSet));
            }

            return auditVisitExceptions;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private AuditVisitException mapVisitException(ResultSet resultSet) throws SQLException {
        AuditVisitException auditVisitException = new AuditVisitException();

        auditVisitException.setExceptionID(BigInteger.valueOf(resultSet.getBigDecimal("EXCP_ID").longValue()));
        auditVisitException.setExceptionName(resultSet.getString("EXCP_NAME"));

        return auditVisitException;
    }

    public AuditVisit getAuditVisitForNotes(long visitSk) {
        AuditVisit auditVisit = getAuditVisitWithBasicInfo(visitSk);

        if (auditVisit != null) {
            auditVisit.getVisitNotes().addAll(getAuditVisitNotes(visitSk));

            return auditVisit;
        }

        return null;
    }

    private List<AuditVisitNote> getAuditVisitNotes(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql =
                    " SELECT VISIT_NOTE_TYP_NAME, VISIT_NOTE " +
                            " " +
                            " FROM VISIT_NOTE " +
                            " WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "   AND VISIT_SK = ? " +
                            " " +
                            " ORDER BY VISIT_NOTE_SK ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<AuditVisitNote> auditVisitNotes = new ArrayList<AuditVisitNote>();

            while (resultSet.next()) {
                auditVisitNotes.add(mapVisitNote(resultSet));
            }

            return auditVisitNotes;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private AuditVisitNote mapVisitNote(ResultSet resultSet) throws SQLException {
        AuditVisitNote auditVisitNote = new AuditVisitNote();

        auditVisitNote.setVisitNoteTypeName(resultSet.getString("VISIT_NOTE_TYP_NAME"));
        auditVisitNote.setVisitNote(resultSet.getString("VISIT_NOTE"));

        return auditVisitNote;
    }

    public AuditVisit getAuditVisitForTasks(long visitSk) {
        AuditVisit auditVisit = getAuditVisitWithBasicInfo(visitSk);

        if (auditVisit != null) {
            auditVisit.getVisitTasks().addAll(getAuditVisitTasks(visitSk));

            return auditVisit;
        }

        return null;
    }

    private List<AuditVisitTask> getAuditVisitTasks(long visitSk) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String sql =
                    " SELECT Task.BE_TASK_NAME, Task.TASK_ID, VISIT_TASK_LST.* " +
                            " " +
                            " FROM VISIT_TASK_LST " +
                            "   JOIN (SELECT BE_ID, TASK_ID, BE_TASK_ID, BE_TASK_NAME FROM TASK " +
                            "         WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) Task " +
                            "       ON VISIT_TASK_LST.BE_ID = Task.BE_ID " +
                            "           AND VISIT_TASK_LST.BE_TASK_ID = Task.BE_TASK_ID " +
                            " " +
                            " WHERE VISIT_SK = ?" +
                            " " +
                            " ORDER BY VISIT_TASK_LST_SK ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, visitSk);

            resultSet = preparedStatement.executeQuery();

            List<AuditVisitTask> auditVisitTasks = new ArrayList<AuditVisitTask>();

            while (resultSet.next()) {
                auditVisitTasks.add(mapVisitTask(resultSet));
            }

            return auditVisitTasks;

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
            this.connectionPoolDataService.close(connection);
        }
    }

    private AuditVisit getAuditVisitWithBasicInfo(long visitSk) {
        List<Visit> result = (ArrayList<Visit>) executeGet("PKG_VISIT", "getVisit", "com.sandata.lab.data.model.dl.model.Visit", visitSk);
        if (result != null && result.size() > 0) {
            Visit visit = result.get(0);

            AuditVisit auditVisit = new AuditVisit();
            auditVisit.setVisitSk(visit.getVisitSK().longValue());
            auditVisit.setBusinessEntityID(visit.getBusinessEntityID());
            auditVisit.setChangeReasonCode(visit.getChangeReasonCode());
            auditVisit.setChangeReasonMemo(visit.getChangeReasonMemo());

            return auditVisit;
        }

        return null;
    }

    private AuditVisitTask mapVisitTask(ResultSet resultSet) throws SQLException {
        AuditVisitTask auditVisitTask = new AuditVisitTask();

        auditVisitTask.setTaskId(resultSet.getString("TASK_ID"));
        auditVisitTask.setTaskDescription(resultSet.getString("BE_TASK_NAME"));
        auditVisitTask.setCriticalTaskIndicator(resultSet.getBoolean("CRITICAL_TASK_IND"));
        auditVisitTask.setTaskResultsReadingValue(resultSet.getString("TASK_RESULTS_RDNG_VAL"));
        auditVisitTask.setVisitTaskPerformedIndicator(resultSet.getBoolean("VISIT_TASK_PERF_IND"));

        return auditVisitTask;
    }

    private AuditVisitHistory getAuditVisitHistory(String userGuid, AuditVisit oldVisit, AuditVisit newVisit) {
        AuditVisitHistory auditVisitHistory = new AuditVisitHistory();

        auditVisitHistory.setAuditHost(String.format("%s|%d", newVisit.getBusinessEntityID(), newVisit.getVisitSk()));
        auditVisitHistory.setUserGuid(userGuid);
        auditVisitHistory.setReasonCode(newVisit.getChangeReasonCode());
        auditVisitHistory.setNotes(newVisit.getChangeReasonMemo());

        // DB function INSERT_AUDIT_VISIT_HIST will take care of detecting changes between old and new
        if (oldVisit != null) {
            putOldVisitToAuditVisitHistory(auditVisitHistory, oldVisit);
        }

        putNewVisitToAuditVisitHistory(auditVisitHistory, newVisit);

        return auditVisitHistory;
    }

    private void putNewVisitToAuditVisitHistory(AuditVisitHistory auditVisitHistory, AuditVisit newVisit) {
        auditVisitHistory.setPatientIdNew(newVisit.getPatientId());
        auditVisitHistory.setPatientNameNew(newVisit.getPatientName());
        auditVisitHistory.setStaffIdNew(newVisit.getStaffId());
        auditVisitHistory.setStaffNameNew(newVisit.getStaffName());

        auditVisitHistory.setServiceNew(newVisit.getService());

        if (newVisit.getScheduleIn() != null) {
            auditVisitHistory.setScheduleInNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getScheduleIn()));
        }

        if (newVisit.getScheduleOut() != null) {
            auditVisitHistory.setScheduleOutNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getScheduleOut()));
        }

        auditVisitHistory.setScheduleHoursNew(newVisit.getScheduledHours());

        if (newVisit.getCallIn() != null) {
            auditVisitHistory.setCallInNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getCallIn()));
        }

        if (newVisit.getCallOut() != null) {
            auditVisitHistory.setCallOutNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getCallOut()));
        }

        auditVisitHistory.setCallHoursNew(newVisit.getCallHours());

        if (newVisit.getAdjustIn() != null) {
            auditVisitHistory.setAdjustInNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getAdjustIn()));
        }

        if (newVisit.getAdjustOut() != null) {
            auditVisitHistory.setAdjustOutNew(AUDIT_VISIT_DATE_TIME_FORMAT.format(newVisit.getAdjustOut()));
        }

        auditVisitHistory.setAdjustHoursNew(newVisit.getAdjustHours());

        if (newVisit.getPayHours() != null) {
            auditVisitHistory.setPayHoursNew(newVisit.getPayHours().toString());
        }

        if (newVisit.getBillHours() != null) {
            auditVisitHistory.setBillHoursNew(newVisit.getBillHours().toString());
        }

        auditVisitHistory.setStatusNew(newVisit.getStatus());

        auditVisitHistory.setVerifyByScheduleNew(String.valueOf(newVisit.getVerifiedByScheduleIndicator()));
        auditVisitHistory.setPayByScheduleNew(String.valueOf(newVisit.getPayByScheduleIndicator()));

        if (newVisit.getOvertimeAbsenceHours() != null) {
            auditVisitHistory.setOtAbsHoursNew(newVisit.getOvertimeAbsenceHours().toString());
        }

        auditVisitHistory.setApprovedNew(String.valueOf(newVisit.getApprovedIndicator()));

        if (!newVisit.getVisitExceptions().isEmpty()) {
            auditVisitHistory.setVisitExceptionsNew(GSONProvider.ToJson(newVisit.getVisitExceptions()));
        }

        if (!newVisit.getVisitNotes().isEmpty()) {
            auditVisitHistory.setVisitNotesNew(GSONProvider.ToJson(newVisit.getVisitNotes()));
        }

        if (!newVisit.getVisitTasks().isEmpty()) {
            auditVisitHistory.setVisitTasksNew(GSONProvider.ToJson(newVisit.getVisitTasks()));
        }
    }

    private void putOldVisitToAuditVisitHistory(AuditVisitHistory auditVisitHistory, AuditVisit oldVisit) {
        auditVisitHistory.setPatientIdOld(oldVisit.getPatientId());
        auditVisitHistory.setPatientNameOld(oldVisit.getPatientName());
        auditVisitHistory.setStaffIdOld(oldVisit.getStaffId());
        auditVisitHistory.setStaffNameOld(oldVisit.getStaffName());

        auditVisitHistory.setServiceOld(oldVisit.getService());

        if (oldVisit.getScheduleIn() != null) {
            auditVisitHistory.setScheduleInOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getScheduleIn()));
        }

        if (oldVisit.getScheduleOut() != null) {
            auditVisitHistory.setScheduleOutOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getScheduleOut()));
        }

        auditVisitHistory.setScheduleHoursOld(oldVisit.getScheduledHours());

        if (oldVisit.getCallIn() != null) {
            auditVisitHistory.setCallInOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getCallIn()));
        }

        if (oldVisit.getCallOut() != null) {
            auditVisitHistory.setCallOutOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getCallOut()));
        }

        auditVisitHistory.setCallHoursOld(oldVisit.getCallHours());

        if (oldVisit.getAdjustIn() != null) {
            auditVisitHistory.setAdjustInOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getAdjustIn()));
        }

        if (oldVisit.getAdjustOut() != null) {
            auditVisitHistory.setAdjustOutOld(AUDIT_VISIT_DATE_TIME_FORMAT.format(oldVisit.getAdjustOut()));
        }

        auditVisitHistory.setAdjustHoursOld(oldVisit.getAdjustHours());

        if (oldVisit.getPayHours() != null) {
            auditVisitHistory.setPayHoursOld(oldVisit.getPayHours().toString());
        }

        if (oldVisit.getBillHours() != null) {
            auditVisitHistory.setBillHoursOld(oldVisit.getBillHours().toString());
        }

        auditVisitHistory.setStatusOld(oldVisit.getStatus());

        auditVisitHistory.setVerifyByScheduleOld(String.valueOf(oldVisit.getVerifiedByScheduleIndicator()));
        auditVisitHistory.setPayByScheduleOld(String.valueOf(oldVisit.getPayByScheduleIndicator()));

        if (oldVisit.getOvertimeAbsenceHours() != null) {
            auditVisitHistory.setOtAbsHoursOld(oldVisit.getOvertimeAbsenceHours().toString());
        }

        auditVisitHistory.setApprovedOld(String.valueOf(oldVisit.getApprovedIndicator()));

        if (!oldVisit.getVisitExceptions().isEmpty()) {
            auditVisitHistory.setVisitExceptionsOld(GSONProvider.ToJson(oldVisit.getVisitExceptions()));
        }

        if (!oldVisit.getVisitNotes().isEmpty()) {
            auditVisitHistory.setVisitNotesOld(GSONProvider.ToJson(oldVisit.getVisitNotes()));
        }

        if (!oldVisit.getVisitTasks().isEmpty()) {
            auditVisitHistory.setVisitTasksOld(GSONProvider.ToJson(oldVisit.getVisitTasks()));
        }
    }



    
	/**
	 * 
	 * @param beId
	 * @param lastName
	 * @param firstName
	 * @param patientId
	 * @param serviceName
	 * @param visitDateTime
	 * @param page
	 * @param pageSize
	 * @param sortOn
	 * @param direction
	 * @return
	 * @throws SandataRuntimeException
	 */
	public Response findPatientsForSearch(
			final String beId,
			final String lastName,
			final String firstName,
			final String patientId,
			final List<String> serviceNameList,
			final String visitDateTimeString,
			final int page,
			final int pageSize,
			final String sortOn,
			String direction
			) throws SandataRuntimeException {

        String orderByString = "UPPER(P.PT_LAST_NAME)"; // Default
        switch (sortOn) {

            case "pfn":
                orderByString = "UPPER(P.PT_FIRST_NAME)";
                break;
            case "pid":
                orderByString = "UPPER(P.PT_ID)";
                break;
        }

		int toRow = page * pageSize;
		int fromRow = toRow - (pageSize - 1);

		VisitQueryUtil queryUtil = new VisitQueryUtil();
		List<String> allServiceNameList = null;
		
		if(serviceNameList == null || serviceNameList.size() == 0) {
			allServiceNameList = findServiceNames();
		}
		
		List<App.DATA_TYPE> types = new ArrayList<App.DATA_TYPE>();
		types.add(App.DATA_TYPE.VARCHAR);
		types.add(App.DATA_TYPE.VARCHAR);
		types.add(App.DATA_TYPE.VARCHAR);

		List<String> columns = new ArrayList<String>();
		columns.add("P.PT_LAST_NAME");
		columns.add("P.PT_FIRST_NAME");
		columns.add("P.PT_ID");

		List<String> values = new ArrayList<String>();
		values.add(StringUtils.isEmpty(lastName) ? "": lastName.toUpperCase());
		values.add(StringUtils.isEmpty(firstName) ? "": firstName.toUpperCase());
		values.add(StringUtils.isEmpty(patientId) ? "": patientId.toUpperCase());

		String conditionClause = queryUtil.buildConditionClause(types, columns, values);
		String serviceNamesSelectClause = queryUtil.buildServiceNamesSelectClause(serviceNameList, allServiceNameList);
		String conditionClauseForListValues = queryUtil.buildListValueForColumn("PP.SVC_NAME", serviceNameList);

		List<String> orderColumns = new ArrayList<String>();
		orderColumns.add(orderByString);
		String orderByClause = queryUtil.buildOrderByClause(orderColumns, direction);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = connectionPoolDataService.getConnection();
			connection.setAutoCommit(false);

			
			String sql = String.format("SELECT * FROM " +
					"  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM " +
					"    (SELECT DISTINCT P.PT_SK, P.PT_ID, P.PT_FIRST_NAME, P.PT_LAST_NAME, P.PT_MEDICARE_ID, P.PT_MEDICAID_ID, P.BE_ID " +
					"     %s " +
					"     FROM PT P, PT_PAYER PP " +
					"       WHERE UPPER(P.BE_ID) = ?  AND P.PT_ID=PP.PT_ID AND UPPER(P.PT_STATUS_NAME)='ACTIVE' AND P.CURR_REC_IND=1 AND PP.CURR_REC_IND=1 " + 
					"            AND TO_DATE(?, 'MM-DD-YYYY') BETWEEN P.REC_EFF_TMSTP AND P.REC_TERM_TMSTP " +
					"            AND TO_DATE(?, 'MM-DD-YYYY') BETWEEN PP.REC_EFF_TMSTP AND PP.REC_TERM_TMSTP " +
					"            %s  " +
					"            %s " +
					"            %s " +
					"      ) R1) " +
					"    WHERE ROW_NUMBER BETWEEN %d AND %d ",

					serviceNamesSelectClause,
					conditionClause,
					conditionClauseForListValues,
					orderByClause,
					fromRow,
					toRow
					);

			preparedStatement = connection.prepareStatement(sql);

			int index = 1;
			preparedStatement.setString(index++, beId);
			App.DATE_FORMAT.setTimeZone(App.UTC_TZ);
			Date date = App.DATE_FORMAT.parse(visitDateTimeString.replaceAll("Z$", "+0000")); 
			App.DB_DATE_FORMAT.setTimeZone(App.UTC_TZ);
			String dateString = App.DB_DATE_FORMAT.format(date);
			preparedStatement.setString(index++, dateString);
			preparedStatement.setString(index++, dateString);

			resultSet = preparedStatement.executeQuery();

			Response response = queryUtil.parseResultSetForPatient(resultSet);

			response.setOrderByColumn(sortOn.toUpperCase());
			response.setOrderByDirection(direction.toUpperCase());
			response.setPage(page);
			response.setPageSize(pageSize);

			connection.commit();

			return response;


		} catch (Exception e) {

			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()));

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
		}
	}        

	/**
	 * 
	 * @param beId
	 * @param lastName
	 * @param firstName
	 * @param staffId
	 * @param staffPositionName
	 * @param visitDateTime
	 * @param page
	 * @param pageSize
	 * @param sortOn
	 * @param direction
	 * @return
	 * @throws SandataRuntimeException
	 */
	public Response findStaffsForSearch(
			final String beId,
			final String lastName,
			final String firstName,
			final String staffId,
			final List<String> staffPositionNameList,
			final String visitDateTimeString,
			final int page,
			final int pageSize,
			final String sortOn,
			String direction
			) throws SandataRuntimeException {


        String orderByString = "UPPER(STAFF_LAST_NAME)"; // Default
        switch (sortOn) {

            case "sfn":
                orderByString = "UPPER(STAFF_FIRST_NAME)";
                break;
            case "sid":
                orderByString = "UPPER(STAFF_ID)";
                break;
            case "spn":
                orderByString = "UPPER(STAFF_POSITION_NAME)";
                break;
        }

		int toRow = page * pageSize;
		int fromRow = toRow - (pageSize - 1);

		VisitQueryUtil queryUtil = new VisitQueryUtil();

		List<App.DATA_TYPE> types = new ArrayList<App.DATA_TYPE>();
		types.add(App.DATA_TYPE.VARCHAR);
		types.add(App.DATA_TYPE.VARCHAR);
		types.add(App.DATA_TYPE.VARCHAR);

		List<String> columns = new ArrayList<String>();
		columns.add("STAFF_ID");
		columns.add("STAFF_FIRST_NAME");
		columns.add("STAFF_LAST_NAME");

		List<String> values = new ArrayList<String>();
		values.add(StringUtils.isEmpty(staffId) ? "": staffId.toUpperCase());
		values.add(StringUtils.isEmpty(firstName) ? "": firstName.toUpperCase());
		values.add(StringUtils.isEmpty(lastName) ? "": lastName.toUpperCase());

		String conditionClause = queryUtil.buildConditionClause(types, columns, values);
		String conditionClauseForListValues = queryUtil.buildListValueForColumn("STAFF_POSITION_NAME", staffPositionNameList);

		List<String> orderColumns = new ArrayList<String>();
		orderColumns.add(orderByString);
		String orderByClause = queryUtil.buildOrderByClause(orderColumns, direction);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = connectionPoolDataService.getConnection();
			connection.setAutoCommit(false);

			String sql = String.format("SELECT * FROM " +
					"  (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM " +
					"    (SELECT STAFF_SK, STAFF_ID, STAFF_FIRST_NAME, STAFF_LAST_NAME, BE_ID, STAFF_POSITION_NAME from STAFF  " +
					"       where UPPER(BE_ID) = ? AND CURR_REC_IND = 1 " + 
					"            and TO_DATE(?, 'MM-DD-YYYY') between REC_EFF_TMSTP AND REC_TERM_TMSTP " +
					"            %s  " +
					"            %s " +
					"            %s " +
					"      ) R1) " +
					"    WHERE ROW_NUMBER BETWEEN %d AND %d ",

					conditionClause,
					conditionClauseForListValues,
					orderByClause,
					fromRow,
					toRow
					);

			preparedStatement = connection.prepareStatement(sql);

			int index = 1;
			preparedStatement.setString(index++, beId);
			App.DATE_FORMAT.setTimeZone(App.UTC_TZ);
			Date date = App.DATE_FORMAT.parse(visitDateTimeString.replaceAll("Z$", "+0000")); 
			App.DB_DATE_FORMAT.setTimeZone(App.UTC_TZ);
			String dateString = App.DB_DATE_FORMAT.format(date);
			preparedStatement.setString(index, dateString);
			
			resultSet = preparedStatement.executeQuery();
			
			Response response = queryUtil.parseResultSetForStaff(resultSet);

			response.setOrderByColumn(sortOn.toUpperCase());
			response.setOrderByDirection(direction.toUpperCase());
			response.setPage(page);
			response.setPageSize(pageSize);
			
			connection.commit();

			return response;


		} catch (Exception e) {

			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()));

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
		}
	}    	
    
	public List<String> findServiceNames() throws SandataRuntimeException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String serviceName = "";
		List<String> serviceNameList = new ArrayList<String>();

		try {

			connection = connectionPoolDataService.getConnection();
			connection.setAutoCommit(false);

			String sql = "SELECT DISTINCT SVC_NAME FROM SVC WHERE SVC_NAME IS NOT NULL ORDER BY SVC_NAME";
			
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
						
			while (resultSet.next()) {
				serviceName = resultSet.getString(1);
				if(!StringUtils.isEmpty(serviceName)) {
					serviceNameList.add(serviceName);
				}
			}
		} catch (SQLException e ) {
			throw new SandataRuntimeException(String.format("%s: %s",
					e.getClass().getName(), e.getMessage()));

		} 
		
		return serviceNameList;
	}    	
    	

}
