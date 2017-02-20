package com.sandata.lab.billing.impl;

import com.sandata.lab.billing.api.OracleService;
import com.sandata.lab.billing.model.BillingResponse;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.logger.api.LoggerService;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

    private LoggerService loggerService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public ConnectionPoolDataService getConnectionPoolDataService() {
        return connectionPoolDataService;
    }

    public void setConnectionPoolDataService(ConnectionPoolDataService connectionPoolDataService) {
        this.connectionPoolDataService = connectionPoolDataService;
    }

    public LoggerService getLoggerService() {
        return loggerService;
    }

    public void setLoggerService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void closeOracleConnection(Connection connection) throws SandataRuntimeException {
        this.connectionPoolDataService.close(connection);
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

            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
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
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
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
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setLong(2, sequenceKey);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);

            connection.commit();

            return result;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            int result = (Integer)callableStatement.getObject(1);
            return result;
        }
        catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(String.format("[%s][%s]: %s: %s",
                    packageName, methodName,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public int executeUpdate(String sql, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            int resultValue = preparedStatement.executeUpdate();
            if (resultValue < 0) {

                throw new SandataRuntimeException(String.format("resultValue [%d] < 0", resultValue));
            }

            connection.commit();

            return resultValue;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public int executeCount(String sql, String countColumnName, Object... params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                BigDecimal countValue = resultSet.getBigDecimal(countColumnName);
                return countValue.intValue();
            }

            return 0;

        } catch (Exception e) {

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                }
                catch (SQLException sqle) {
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
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public BillingResponse getBillingResponse(Connection connection, long visitSk, SandataLogger logger) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {

            String sql = "SELECT T1.BE_ID,T1.PT_ID,T1.STAFF_ID,T1.SCHED_EVNT_SK,T1.VISIT_DO_NOT_BILL_IND,T1.VISIT_CANCELLED_IND, " +
                    "          T1.VISIT_ACT_START_TMSTP,T1.VISIT_ACT_END_TMSTP,T1.VISIT_ADJ_START_TMSTP,T1.VISIT_ADJ_END_TMSTP, " +
                    "          ROUND((24 * 3600 * (T1.VISIT_ACT_END_TMSTP - T1.VISIT_ACT_START_TMSTP)),0) AS ACT_VISIT_SEC, " +
                    "          ROUND((24 * 3600 * (T1.VISIT_ADJ_END_TMSTP - T1.VISIT_ADJ_START_TMSTP)),0) AS ADJ_VISIT_SEC, " +
                    "          T1.BE_ID AS BE_LOB_ID, T1.BE_ID AS BE_LOC_ID, " +
                    " " +
                    "          T2.PAYER_ID,T2.CONTR_ID, " +
                    " " +
                    "          T4_SVC.SVC_NAME, " +
                    " " +
                    "          T4.AUTH_SK,T4.AUTH_ID, " +
                    " " +
                    "          T5.PT_INS_ID_NUM, " +
                    " " +
                    "          'PENDING' AS BILLING_STATUS_NAME, " +
                    "          'FIRST_TIME_SUBMISSION' AS BILLING_SUBM_TYP_NAME, " +
                    " " +
                    "          T6.BILLING_SK, " +
                    " " +
                    "          T7.BILLING_CODE,T7.MDFR_1_CODE,T7.MDFR_2_CODE,T7.MDFR_3_CODE,T7.MDFR_4_CODE,T7.REV_CODE, " +
                    "          T7.RATE_TYP_NAME,T7.RATE_QLFR_CODE,T7.SVC_UNIT_NAME,T7.RATE_AMT " +
                    " " +
                    "  FROM VISIT T1 " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID,PAYER_ID,CONTR_ID,PT_PAYER_EFF_DATE,PT_PAYER_TERM_DATE " +
                    "  FROM PT_PAYER WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                    "ON T1.BE_ID = T2.BE_ID AND T1.PT_ID = T2.PT_ID " +
                    "  AND PKG_BILLING_UTIL.GET_BILLING_DATE(T1.VISIT_SK) BETWEEN T2.PT_PAYER_EFF_DATE AND T2.PT_PAYER_TERM_DATE " +
                    "  AND T2.CONTR_ID IS NOT NULL " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,AUTH_SK,AUTH_ID,PT_ID,PAYER_ID,AUTH_START_TMSTP,AUTH_END_TMSTP " +
                    "  FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PT_ID = T4.PT_ID AND T2.PAYER_ID = T4.PAYER_ID " +
                    "  AND PKG_BILLING_UTIL.GET_BILLING_DATE(T1.VISIT_SK) BETWEEN AUTH_START_TMSTP AND AUTH_END_TMSTP " +
                    " " +
                    "LEFT JOIN (SELECT AUTH_SK,SVC_NAME " +
                    "  FROM AUTH_SVC) T4_SVC " +
                    "ON T4.AUTH_SK = T4_SVC.AUTH_SK " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID,PAYER_ID,PT_INS_ID_NUM " +
                    "  FROM PT_PAYER_INS WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                    "ON T1.BE_ID = T4.BE_ID AND T1.PT_ID = T5.PT_ID " +
                    "  AND T2.PAYER_ID = T5.PAYER_ID " +
                    " " +
                    "LEFT JOIN (SELECT BILLING_SK,BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,AUTH_ID, " +
                    "              PT_ID,PT_INS_ID_NUM,BILLING_START_DATE,BILLING_END_DATE " +
                    "  FROM BILLING WHERE NOT EXISTS (SELECT 1 FROM BILLING_HIST WHERE BILLING_HIST.BILLING_SK = BILLING_SK AND ACTION_CODE = 'D') " +
                    " AND BILLING_SUBM_TYP_NAME = 'FIRST_TIME_SUBMISSION' AND BILLING_STATUS_NAME = 'PENDING') T6 " +
                    "ON T1.BE_ID = T6.BE_ID AND T1.BE_ID = T6.BE_LOB_ID AND T1.BE_ID = T6.BE_LOC_ID " +
                    "  AND T1.PT_ID = T6.PT_ID AND T2.PAYER_ID = T6.PAYER_ID AND T2.CONTR_ID = T6.CONTR_ID AND T4.AUTH_ID = T6.AUTH_ID " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,BE_LOB_ID,BE_LOC_ID,PAYER_ID,CONTR_ID,BILLING_RATE_MATRIX_EFF_DATE,BILLING_RATE_MATRIX_TERM_DATE,SVC_NAME, " +
                    "            RATE_TYP_NAME,RATE_QLFR_CODE,RATE_AMT,SVC_UNIT_NAME,BILLING_CODE,MDFR_1_CODE,MDFR_2_CODE,MDFR_3_CODE,MDFR_4_CODE,REV_CODE " +
                    "  FROM BILLING_RATE_MATRIX WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T7 " +
                    "ON T1.BE_ID = T7.BE_ID AND T1.BE_ID = T7.BE_LOB_ID AND T1.BE_ID = T7.BE_LOC_ID " +
                    "  AND T2.PAYER_ID = T7.PAYER_ID AND T2.CONTR_ID = T7.CONTR_ID AND T4_SVC.SVC_NAME = T7.SVC_NAME " +
                    " " +
                    "LEFT JOIN (SELECT BE_ID,PT_ID " +
                    "  FROM PT WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T8 " +
                    "ON T1.BE_ID = T8.BE_ID AND T1.PT_ID = T8.PT_ID " +
                    " " +
                    "WHERE T1.VISIT_SK = ? " +
                    "  AND (VISIT_DO_NOT_BILL_IND = 0 OR VISIT_DO_NOT_BILL_IND IS NULL) " +
                    "  AND (VISIT_CANCELLED_IND = 0 OR VISIT_CANCELLED_IND IS NULL)";

            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setLong(index++, visitSk);

            resultSet = preparedStatement.executeQuery();

            BillingResponse billingResponse = new BillingResponse();

            // Expecting only one response
            if (resultSet.next()) {

                Billing billing = new Billing();

                // Init house keeping fields
                billing.setRecordCreateTimestamp(new java.util.Date());
                billing.setRecordUpdateTimestamp(new java.util.Date());
                billing.setRecordCreatedBy("MW: Billing Process");
                billing.setRecordUpdatedBy("N/A");
                billing.setChangeVersionID(BigInteger.ZERO);

                billingResponse.setBilling(billing);

                BigDecimal billingSk = resultSet.getBigDecimal("BILLING_SK");
                if (billingSk != null) {
                    billing.setBillingSK(billingSk.toBigInteger());
                }

                billing.setBusinessEntityID(resultSet.getString("BE_ID"));
                billing.setBusinessEntityLineOfBusinessID(resultSet.getString("BE_LOB_ID"));
                billing.setBusinessEntityLocationID(resultSet.getString("BE_LOC_ID"));
                billing.setPayerID(resultSet.getString("PAYER_ID"));
                billing.setContractID(resultSet.getString("CONTR_ID"));
                billing.setAuthorizationID(resultSet.getString("AUTH_ID"));
                billing.setPatientID(resultSet.getString("PT_ID"));
                billing.setPatientInsuranceIDNumber(resultSet.getString("PT_INS_ID_NUM"));
                billing.setBillingStatusName(resultSet.getString("BILLING_STATUS_NAME"));
                billing.setBillingSubmissionTypeName(resultSet.getString("BILLING_SUBM_TYP_NAME"));

                billingResponse.setActualVisitSeconds(resultSet.getBigDecimal("ACT_VISIT_SEC"));
                billingResponse.setAdjustedVisitSeconds(resultSet.getBigDecimal("ADJ_VISIT_SEC"));

                Timestamp actVisitStartDate = resultSet.getTimestamp("VISIT_ACT_START_TMSTP");
                if (actVisitStartDate != null) {
                    billingResponse.setActualVisitStartDate(new java.util.Date(actVisitStartDate.getTime()));
                }

                Timestamp actVisitEndDate = resultSet.getTimestamp("VISIT_ACT_END_TMSTP");
                if (actVisitEndDate != null) {
                    billingResponse.setActualVisitEndDate(new java.util.Date(actVisitEndDate.getTime()));
                }

                Timestamp adjVisitStartDate = resultSet.getTimestamp("VISIT_ADJ_START_TMSTP");
                if (adjVisitStartDate != null) {
                    billingResponse.setAdjustedVisitStartDate(new java.util.Date(adjVisitStartDate.getTime()));
                }

                Timestamp adjVisitEndDate = resultSet.getTimestamp("VISIT_ADJ_END_TMSTP");
                if (adjVisitEndDate != null) {
                    billingResponse.setAdjustedVisitEndDate(new java.util.Date(adjVisitEndDate.getTime()));
                }

                billingResponse.initBillingDates(logger);

                billing.setBillingDate(new java.util.Date());

                // ** Billing Details **
                BillingDetail billingDetail = new BillingDetail();

                // Init house keeping fields
                billingDetail.setRecordCreateTimestamp(new java.util.Date());
                billingDetail.setRecordUpdateTimestamp(new java.util.Date());
                billingDetail.setRecordCreatedBy("MW: Billing Process");
                billingDetail.setRecordUpdatedBy("N/A");
                billingDetail.setChangeVersionID(BigInteger.ZERO);

                billingResponse.setBillingDetail(billingDetail);

                billingDetail.setBusinessEntityID(resultSet.getString("BE_ID"));
                billingDetail.setPayerID(resultSet.getString("PAYER_ID"));
                billingDetail.setContractID(resultSet.getString("CONTR_ID"));
                billingDetail.setPatientID(resultSet.getString("PT_ID"));

                billingDetail.setBillingDetailDate(billing.getBillingStartDate());

                String serviceName = resultSet.getString("SVC_NAME");
                if (serviceName == null) {

                    throw new SandataRuntimeException("getBillingResponse: In order to create a billing record, the service must be known! " +
                            "Does an auth exist? Is there a billing matrix record? [SVC_NAME == NULL]");
                }

                try {
                    billingDetail.setServiceName(ServiceName.fromValue(serviceName));

                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("getBillingResponse: Unknown service name! [SVC_NAME == %s]", serviceName));
                }


                billingDetail.setBillingCode(resultSet.getString("BILLING_CODE"));
                billingDetail.setModifier1Code(resultSet.getString("MDFR_1_CODE"));
                billingDetail.setModifier2Code(resultSet.getString("MDFR_2_CODE"));
                billingDetail.setModifier3Code(resultSet.getString("MDFR_3_CODE"));
                billingDetail.setModifier4Code(resultSet.getString("MDFR_4_CODE"));
                billingDetail.setRevenueCode(resultSet.getString("REV_CODE"));

                billingDetail.setRateTypeName(resultSet.getString("RATE_TYP_NAME"));

                billingDetail.setRateQualifierCode(RateQualifierCode.BILL);

                String serviceUnit = resultSet.getString("SVC_UNIT_NAME");
                if (serviceUnit == null) {
                    throw new SandataRuntimeException("getBillingResponse: In order to create a billing record, the service unit must be known! " +
                            "Does an auth exist? Is there a billing matrix record? [SVC_UNIT_NAME == NULL]");
                }

                try {
                    billingDetail.setServiceUnitName(ServiceUnitName.fromValue(serviceUnit));

                } catch (Exception e) {
                    throw new SandataRuntimeException(String.format("getBillingResponse: Unknown service unit! [SVC_UNIT_NAME == %s]", serviceUnit));
                }

                billingDetail.setBillingDetailRateAmount(resultSet.getBigDecimal("RATE_AMT"));

                billingResponse.initBillingTotalUnits(logger);
                billingResponse.initBillingTotalAmount(logger);
            }

            return billingResponse;
    
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
    
    public Billing getBillingRecord(Connection connection, long billingSk, SandataLogger logger) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        logger.trace(String.format("getBillingRecord: START: billingSk=%d", billingSk));

        try {

            String sql = "SELECT * FROM BILLING WHERE BILLING_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, billingSk);

            resultSet = preparedStatement.executeQuery();

            List<Billing> resultList =
                    (List<Billing>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Billing");

            if (resultList.size() > 0) {
                return resultList.get(0);
            }

            return null;

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

            logger.trace(String.format("getBillingRecord: END: billingSk=%d", billingSk));
        }
    }
}
