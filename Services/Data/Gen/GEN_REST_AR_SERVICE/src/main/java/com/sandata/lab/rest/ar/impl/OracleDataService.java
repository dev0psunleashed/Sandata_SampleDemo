package com.sandata.lab.rest.ar.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.money.MoneyUtil;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail;
import com.sandata.lab.data.model.dl.model.InvoiceDetail;
import com.sandata.lab.data.model.dl.model.PaymentTypeQualifier;
import com.sandata.lab.data.model.dl.model.ServiceName;
import com.sandata.lab.data.model.dl.model.extended.ar.AccountsReceivableExt;
import com.sandata.lab.data.model.dl.model.find.AccountsReceivableDetail;
import com.sandata.lab.data.model.dl.model.extended.ar.AccountsReceivableUnappliedBalanceExt;
import com.sandata.lab.data.model.dl.model.find.AccountsReceivableFindResult;
import com.sandata.lab.data.model.dl.model.find.AccountsReceivableTransactionBatchDetailExt;
import com.sandata.lab.data.model.dl.model.find.ArTxnBatchManualPostFindResult;
import com.sandata.lab.data.model.icdcodes.ICDCode;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.ar.api.OracleService;
import com.sandata.lab.rest.ar.model.ArBatchFindResult;
import com.sandata.lab.rest.ar.model.ArInvoiceFindResult;
import com.sandata.lab.rest.ar.utils.constants.Columns;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDataService.class);

    private ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(ConnectionType.COREDATA);
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            //Close connection
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            //Close connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, bsnEntId);
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

            throw new SandataRuntimeException(format("[%s][%s][%s]: %s: %s",
                    packageName, methodName, className,
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

            // Close the Statement
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
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

            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

            this.connectionPoolDataService.close(connection);

        }
    }

    @Override
    public long execute(Connection connection, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = callableStatement.getLong(1);
            return result;
        } catch (Exception e) {

            // NOTE: Rollback (if necessary) should be handled by caller since they passed in the connection...
            throw new SandataRuntimeException(format("[%s][%s]: %s: %s",
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

    private ICDCode mapICDCode(final ResultSet resultSet) throws SQLException {

        ICDCode icdCode = new ICDCode();

        icdCode.setIcdDiagnosisCodeSk(BigInteger.valueOf(resultSet.getBigDecimal("ICD_DX_LKUP_SK").longValue()));
        icdCode.setIcdDiagnosisCode(resultSet.getString("ICD_DX_CODE"));
        icdCode.setIcdDiagnosisCodeRevision(resultSet.getString("ICD_DX_CODE_REVISION"));

        Timestamp createTmstp = resultSet.getTimestamp("REC_CREATE_TMSTP");
        if (createTmstp != null) {
            icdCode.setRecordCreateTimestamp(new Date(createTmstp.getTime()));
        }

        Timestamp updateTmstp = resultSet.getTimestamp("REC_UPDATE_TMSTP");
        if (updateTmstp != null) {
            icdCode.setRecordUpdateTimestamp(new Date(updateTmstp.getTime()));
        }

        BigDecimal currentVersionId = resultSet.getBigDecimal("CHANGE_VERSION_ID");
        if (currentVersionId != null) {
            icdCode.setChangeVersionID(BigInteger.valueOf(currentVersionId.longValue()));
        }

        icdCode.setIcdDiagnosisCodeShortDescription(resultSet.getString("ICD_DX_CODE_SHORT_DESC"));
        icdCode.setIcdDiagnosisCodeLongDescription(resultSet.getString("ICD_DX_CODE_LONG_DESC"));

        Timestamp effTmstp = resultSet.getTimestamp("ICD_DX_CODE_EFF_DATE");
        if (effTmstp != null) {
            icdCode.setIcdDiagnosisCodeEffectiveDate(new Date(effTmstp.getTime()));
        }

        Timestamp termTmstp = resultSet.getTimestamp("ICD_DX_CODE_TERM_DATE");
        if (termTmstp != null) {
            icdCode.setIcdDiagnosisCodeTerminationDate(new Date(termTmstp.getTime()));
        }

        return icdCode;
    }


    public Response getARSummary(long sk) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            MockDataHelper mockDataHelper = new MockDataHelper();

            Response response = new Response();


            response.setData(mockDataHelper.accountsReceivableAutoPostSummary());


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

            throw new SandataRuntimeException(format("%s: %s",
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

            this.connectionPoolDataService.close(connection);
        }
    }


    public Response findUnappliedBalances(String bsnEntId,
                                          String payerID, String fromDate, String toDate,
                                          String transactionCode, String transactionType,
                                          String userName, String paymentNumber, String checkDate,
                                          String batchNumber, BigDecimal unappliedBalanceRangeFrom,
                                          BigDecimal unappliedBalanceRangeTo, String sortOn, String direction,
                                          int page, int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            StringBuilder whereClause = new StringBuilder();
            List<Object> params = new ArrayList<>();

            String sortColumn = "T1.REC_CREATE_TMSTP";

            if (sortOn.equalsIgnoreCase("user_name")) {

                sortColumn = " T1.USER_NAME ";

            } else if (sortOn.equalsIgnoreCase("balance")) {

                sortColumn = " T1.AR_TXN_AMT ";

            } else if (sortOn.equalsIgnoreCase("check_num")) {

                sortColumn = "T2.PMT_TYP_NUM";

            } else if (sortOn.equalsIgnoreCase("batch_number")) {

                sortColumn = "  T2.AR_TXN_BATCH_ID ";
            } else if (sortOn.equalsIgnoreCase("check_date")) {

                sortColumn = "T2.CHECK_DATE";
            }

            params.add(bsnEntId);
            whereClause.append(" T1.BE_ID = ? AND T1.AR_UNAPPLIED_BALANCE_IND = 1 AND T1.AR_TXN_AMT > 0 ");

            if (!StringUtil.IsNullOrEmpty(batchNumber)) {
                params.add(batchNumber.toUpperCase());
                whereClause.append(" AND UPPER(T2.AR_TXN_BATCH_ID) = ?");
            }

            if (!StringUtil.IsNullOrEmpty(payerID)) {
                params.add(payerID.toUpperCase());
                whereClause.append(" AND UPPER(T1.PAYER_ID) = ?");
            }

            if (!StringUtil.IsNullOrEmpty(paymentNumber)) {
                params.add(paymentNumber.toUpperCase());
                whereClause.append(" AND UPPER(T2.PMT_TYP_NUM) = ? ");
            }

            if (!StringUtil.IsNullOrEmpty(checkDate)) {
                params.add(checkDate);
                whereClause.append(" AND T2.CHECK_DATE = TO_DATE(?, 'YYYY-MM-DD')");
            }


            if (!StringUtil.IsNullOrEmpty(transactionCode)) {
                params.add(transactionCode.toUpperCase());
                whereClause.append(" AND UPPER(T1.AR_TXN_CODE) = ? ");
            }

            if (!StringUtil.IsNullOrEmpty(transactionType)) {
                params.add(transactionType.toUpperCase());
                whereClause.append(" AND UPPER(T3.AR_TXN_SIGN_QLFR) = ? ");
            }

            if (!StringUtil.IsNullOrEmpty(userName)) {
                params.add(userName.toUpperCase());
                whereClause.append(" AND UPPER(T1.USER_NAME) = ? ");
            }

            if (unappliedBalanceRangeFrom != null) {
                params.add(unappliedBalanceRangeFrom);
                whereClause.append(" AND T1.AR_TXN_AMT >= ?");
            }

            if (unappliedBalanceRangeTo != null) {
                params.add(unappliedBalanceRangeTo);
                whereClause.append(" AND T1.AR_TXN_AMT <= ?");
            }


            // Build SQL query.
            String sql = format(
                    "SELECT * FROM " +
                            "	(SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (" +
                            "   SELECT  " +
                            "    T1.AR_SK, " +
                            "    T1.BE_ID, " +
                            "    T1.AR_TXN_BATCH_DET_SK," +
                            "    T1.REC_CREATE_TMSTP, " +
                            "    T1.USER_NAME, " +
                            "    T1.AR_TXN_CODE, " +
                            "    T1.AR_TXN_AMT, " +
                            "    T1.AR_NOTE, " +
                            "    T1.REC_UPDATE_TMSTP, " +
                            "    T1.AR_UNAPPLIED_BALANCE_IND, " +
                            "    T2.AR_TXN_BATCH_ID, " +
                            "    T2.CHECK_DATE, " +
                            "    T2.PMT_TYP_NUM, " +
                            "    T3.AR_TXN_SIGN_QLFR, " +
                            "    T3.AR_TXN_DESC, " +
                            "    T4.PAYER_ID, " +
                            "    T4.PAYER_NAME," +
                            "                  SUM (   " +
                            "                                     CASE   " +
                            "                                       WHEN T1.AR_TXN_AMT      IS NOT NULL   " +
                            "                                       AND UPPER(T3.AR_TXN_SIGN_QLFR) = 'CREDIT'   " +
                            "                                       THEN T1.AR_TXN_AMT    " +
                            "                                       WHEN T1.AR_TXN_AMT      IS NOT NULL   " +
                            "                                       AND UPPER(T3.AR_TXN_SIGN_QLFR) = 'DEBIT'   " +
                            "                                       THEN 0 - T1.AR_TXN_AMT   " +
                            "                                       ELSE 0    " +
                            "                                     END) AS TOTAL_TXN_AMT    " +
                            "     " +
                            "FROM AR T1 " +
                            "    " +
                            "    INNER JOIN (SELECT  " +
                            "                    BE_ID,  " +
                            "                    AR_TXN_BATCH_DET_SK,  " +
                            "                    AR_TXN_BATCH_ID, " +
                            "                    CHECK_DATE, " +
                            "                    PMT_TYP_NUM, " +
                            "                    PAYER_ID " +
                            "                 FROM COREDATA.AR_TXN_BATCH_DET WHERE AR_TXN_BATCH_POST_IND = 1) T2 " +
                            "    ON T1.BE_ID = T2.BE_ID AND T1.AR_TXN_BATCH_DET_SK = T2.AR_TXN_BATCH_DET_SK " +
                            "       " +
                            "    INNER JOIN (SELECT  " +
                            "                    AR_TXN_CODE, " +
                            "                    AR_TXN_SIGN_QLFR,  " +
                            "                    AR_TXN_DESC  " +
                            "                FROM COREDATA.AR_TXN_LKUP " +
                            "                WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) T3 " +
                            "    ON T1.AR_TXN_CODE = T3.AR_TXN_CODE " +
                            "     " +
                            "   LEFT JOIN  ( SELECT  " +
                            "                    PAYER_ID,  " +
                            "                    PAYER_NAME,  " +
                            "                    BE_ID,  " +
                            "                    REC_TERM_TMSTP,  " +
                            "                    CURR_REC_IND  " +
                            "                FROM PAYER " +
                            "                WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4  " +
                            "   ON T1.PAYER_ID = T4.PAYER_ID AND T1.BE_ID = T4.BE_ID " +
                            " " +
                            " WHERE %s " +
                            " GROUP BY " +
                            "   T1.AR_SK, " +
                            "         T1.BE_ID, " +
                            "        T1.AR_TXN_BATCH_DET_SK, " +
                            "        T1.REC_CREATE_TMSTP, " +
                            "        T1.USER_NAME, " +
                            "        T1.AR_TXN_CODE, " +
                            "        T1.AR_TXN_AMT, " +
                            "        T1.AR_NOTE, " +
                            "        T1.REC_UPDATE_TMSTP, " +
                            "        T1.AR_UNAPPLIED_BALANCE_IND, " +
                            "        T2.AR_TXN_BATCH_ID, " +
                            "        T2.CHECK_DATE, " +
                            "        T2.PMT_TYP_NUM, " +
                            "        T3.AR_TXN_SIGN_QLFR, " +
                            "        T3.AR_TXN_DESC, " +
                            "        T4.PAYER_ID, " +
                            "        T4.PAYER_NAME" +
                            " ORDER BY %s %s " +
                            ")) R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    whereClause,
                    sortColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index++, object);
            }

            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableUnappliedBalanceExt> unappliedBalancesFindResultList = new ArrayList<>();

            int totalRows = 0;

            while (resultSet.next()) {
                if (totalRows == 0) {
                    totalRows = resultSet.getInt("TOTAL_ROWS");
                }


                AccountsReceivableUnappliedBalanceExt accountsReceivableUnappliedBalanceExt = new AccountsReceivableUnappliedBalanceExt();

                accountsReceivableUnappliedBalanceExt.setAccountsReceivableBatchDetailSk(resultSet.getBigDecimal("AR_TXN_BATCH_DET_SK").toBigInteger());
                accountsReceivableUnappliedBalanceExt.setAccountsReceivableSk(resultSet.getBigDecimal("AR_SK").toBigInteger());
                accountsReceivableUnappliedBalanceExt.setUserName(resultSet.getString("USER_NAME"));

                accountsReceivableUnappliedBalanceExt.setBatchNumber(resultSet.getString("AR_TXN_BATCH_ID"));
                accountsReceivableUnappliedBalanceExt.setCheckNumber(resultSet.getString("PMT_TYP_NUM"));
                accountsReceivableUnappliedBalanceExt.setCheckDate(resultSet.getTimestamp("CHECK_DATE"));

                accountsReceivableUnappliedBalanceExt.setPayerID(resultSet.getString("PAYER_ID"));
                accountsReceivableUnappliedBalanceExt.setPayerName(resultSet.getString("PAYER_NAME"));

                accountsReceivableUnappliedBalanceExt.setNotes(resultSet.getString("AR_NOTE"));

                BigDecimal balance = resultSet.getBigDecimal("TOTAL_TXN_AMT");

                if (balance.compareTo(BigDecimal.ZERO) == -1) {

                    balance = BigDecimal.ZERO;
                }

                accountsReceivableUnappliedBalanceExt.setUnappliedBalance(MoneyUtil.formatCurrency(balance));


                accountsReceivableUnappliedBalanceExt.setTransactionCode(resultSet.getString("AR_TXN_CODE"));


                accountsReceivableUnappliedBalanceExt.setTransactionDate(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                accountsReceivableUnappliedBalanceExt.setTransactionType(resultSet.getString("AR_TXN_SIGN_QLFR"));
                accountsReceivableUnappliedBalanceExt.setTransactionDescription(resultSet.getString("AR_TXN_DESC"));

                unappliedBalancesFindResultList.add(accountsReceivableUnappliedBalanceExt);
            }

            Response response = new Response();
            response.setData(unappliedBalancesFindResultList);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setTotalRows(totalRows);


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

            throw new SandataRuntimeException(format("%s: %s",
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


    /**
     * Get List of Transactions By Invoice Number
     *
     * @param bsnEntId
     * @param invoiceNumber
     * @param page
     * @param pageSize
     * @param sortOn
     * @param direction
     * @return
     */
    public Response getTransactionsByARInvoiceNumber(String bsnEntId, String invoiceNumber,
                                                     int page,
                                                     int pageSize,
                                                     String sortOn,
                                                     String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);


            sortOn = "T5.CHECK_DATE ";

            // Build SQL query.
            String sql = format(
                    "SELECT * FROM " +
                            "	(SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM (" +
                            "   SELECT * FROM COREDATA.AR T1 " +
                            " " +
                            "   LEFT JOIN COREDATA.AR_TXN_BATCH_DET T5 " +
                            "       ON T5.BE_ID = T1.BE_ID AND T5.PAYER_ID = T1.PAYER_ID AND T1.AR_TXN_BATCH_DET_SK = T5.AR_TXN_BATCH_DET_SK " +

                            "   INNER JOIN COREDATA.AR_TXN_LKUP T2 ON T1.AR_TXN_CODE = T2.AR_TXN_CODE AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1 " +
                            " " +
                            "   INNER JOIN COREDATA.AR_TXN_CTGY_LKUP T3 ON T2.AR_TXN_CTGY_CODE = T3.AR_TXN_CTGY_CODE AND TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = '1' " +
                            " " +
                            "   LEFT OUTER JOIN COREDATA.BE_AR_NOTE_TYP_LKUP T4 ON T1.BE_ID = T4.BE_ID AND T1.AR_NOTE_TYP_CODE = T4.AR_NOTE_TYP_CODE " +
                            " AND TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1 " +

                            " " +
                            "   INNER JOIN (SELECT INV_NUM, BE_ID, CRN FROM COREDATA.INV ) T6" +
                            " ON T6.BE_ID = T1.BE_ID AND T6.INV_NUM = T1.INV_NUM" +
                            " " +
                            "WHERE T1.BE_ID = ? AND T1.INV_NUM = ? " +
                            " ORDER BY %s %s " +
                            ")) R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    sortOn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, invoiceNumber);


            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableDetail> accountsRecvDetails = new ArrayList<>();

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);


            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                AccountsReceivableDetail accountsReceivableDetail = new AccountsReceivableDetail();
                accountsReceivableDetail.setAccountsReceivableTransactionBatchDetailSK(resultSet.getBigDecimal("AR_TXN_BATCH_DET_SK").toBigInteger());
                accountsReceivableDetail.setPaymentTypeNumber(resultSet.getString("PMT_TYP_NUM"));
                accountsReceivableDetail.setTransactionType(resultSet.getString("AR_TXN_SIGN_QLFR"));
                accountsReceivableDetail.setCheckDate(resultSet.getTimestamp("CHECK_DATE"));
                accountsReceivableDetail.setPaymentTypeNumber(resultSet.getString("PMT_TYP_NUM"));
                accountsReceivableDetail.setTransactionCode(resultSet.getString("AR_TXN_CODE"));

                String paymentQualifier = resultSet.getString("PMT_TYP_QLFR");

                if (!StringUtil.IsNullOrEmpty(paymentQualifier)) {

                    try {

                        PaymentTypeQualifier paymentTypeQualifier = PaymentTypeQualifier.fromValue(paymentQualifier);
                        accountsReceivableDetail.setPaymentTypeQualifier(paymentTypeQualifier);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

                accountsReceivableDetail.setCheckDepositDate(resultSet.getTimestamp("CHECK_DEPOSIT_DATE"));
                accountsReceivableDetail.setCheckReceivedDate(resultSet.getTimestamp("CHECK_RCVD_DATE"));
                accountsReceivableDetail.setAccountsReceivableTransactionBatchID(resultSet.getString("AR_TXN_BATCH_ID"));
                accountsReceivableDetail.setTransactionDescription(resultSet.getString("AR_TXN_DESC"));
                accountsReceivableDetail.setTransactionDate(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                accountsReceivableDetail.setNoteDescription(resultSet.getString("AR_NOTE_TYP_DESC"));
                accountsReceivableDetail.setNoteType(resultSet.getString("AR_NOTE_TYP_CODE"));
                ;
                accountsReceivableDetail.setPaymentAmount(MoneyUtil.formatCurrency(resultSet.getBigDecimal("AR_TXN_AMT")));
                accountsReceivableDetail.setAdjustmentCode(resultSet.getString("AR_TXN_CTGY_CODE"));
                accountsReceivableDetail.setDenialCode("AR_REMIT_CODE");
                accountsReceivableDetail.setAccountsReceivableTransactionBatchPostIndicator(resultSet.getBoolean("AR_TXN_BATCH_POST_IND"));

                accountsReceivableDetail.setInvoiceNumber(resultSet.getString("INV_NUM"));
                accountsReceivableDetail.setCustomReferenceNumber(resultSet.getString("CRN"));


                accountsRecvDetails.add(accountsReceivableDetail);


            }

            response.setData(accountsRecvDetails);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

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

            throw new SandataRuntimeException(format("%s: %s",
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

            throw new SandataRuntimeException(format("%s: %s",
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

    /**
     * @param bsnEntId
     * @param batchNumber
     * @param invoiceNumber
     * @param page
     * @param pageSize
     * @param sortOn
     * @param direction
     * @return
     */
    public Response findArByBatchNumber(String bsnEntId,
                                        String batchNumber,
                                        String invoiceNumber,
                                        long batchDetailSK,
                                        Boolean unappliedInd,
                                        Integer page,
                                        Integer pageSize,
                                        String sortOn,
                                        String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String invoiceNumberFilter = "";
        if (!StringUtil.IsNullOrEmpty(invoiceNumber)) {
            invoiceNumberFilter = " AND  T1.INV_NUM = ? ";
        }

        String batchDetSKFilter = "";

        if (batchDetailSK > 0) {
            batchDetSKFilter = " AND T1.AR_TXN_BATCH_DET_SK = ? ";
        }

        String unappliedFilter = "";

        if (unappliedInd != null && unappliedInd.booleanValue() == true) {

            unappliedFilter = " AND T1.AR_UNAPPLIED_BALANCE_IND = ? ";
        }

        String orderByColumn = "PT_LAST_NAME";
        switch (sortOn) {
            case "pfn":
                orderByColumn = "PT_FIRST_NAME";
                break;
            case "pid":
                orderByColumn = "PT_ID";
                break;
            case "ivn":
                orderByColumn = "INV_NUM";
                break;
            case "dos":
                orderByColumn = "INV_DET_DATE";
                break;
            case "bill":
                orderByColumn = "BILLING_CODE";
                break;
            case "remit":
                orderByColumn = "AR_REMIT_CODE";
                break;
            case "code":
                orderByColumn = "AR_TXN_CODE";
                break;
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            String sql = format("SELECT * FROM " +
                            "   (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM ( " +
                            "     SELECT T1.AR_SK," +
                            "            T1.CRN," +
                            "            T1.REC_CREATE_TMSTP, " +
                            "            T1.AR_TXN_BATCH_DET_SK," +
                            "            T1.INV_NUM, " +
                            "            T2.PT_FIRST_NAME, " +
                            "            T2.PT_LAST_NAME, " +
                            "            T1.USER_NAME," +
                            "            T2.PT_MIDDLE_NAME, " +
                            "            T2.PT_ID, " +
                            "            T3.PT_INS_ID_NUM, " +
                            "            T1.INV_DET_DATE, " +
                            "            T1.BILLING_CODE, " +
                            "            T1.AR_REMIT_CODE, " +
                            "            T1.AR_TXN_CODE, " +
                            "            T1.AR_TXN_AMT, " +
                            "            T5.AR_TXN_SIGN_QLFR, " +
                            "            T1.MDFR_1_CODE, " +
                            "            T1.MDFR_2_CODE, " +
                            "            T1.MDFR_3_CODE, " +
                            "            T1.MDFR_4_CODE, " +
                            "            T1.AR_UNAPPLIED_BALANCE_IND, " +
                            "            T5.AR_TXN_DESC, " +
                            "            T4.INV_TOTAL_AMT, " +
                            "            T4.INV_STATUS_CODE, " +
                            "            T1.AR_NOTE_TYP_CODE, " +
                            "            T1.AR_NOTE, " +
                            "            T1.PAYER_ID," +
                            "            T6.INV_DET_ID," +
                            "            T6.INV_DET_SVC_DATE," +
                            "            T6.INV_DET_SK," +
                            "            T6.INV_DET_TOTAL_AMT," +
                            "            T6.AR_TXN_BATCH_ID " +
                            "       FROM AR T1 " +
                            " " +
                            "           LEFT OUTER JOIN (SELECT BE_ID,INV_NUM,INV_TOTAL_AMT,INV_STATUS_CODE, PT_ID " +
                            "              FROM INV) T4 " +
                            "           ON T4.BE_ID = T1.BE_ID AND T4.INV_NUM = T1.INV_NUM " +
                            "" +
                            "           LEFT OUTER JOIN (SELECT BE_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_ID " +
                            "              FROM PT WHERE (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')) T2 " +
                            "           ON T2.BE_ID = T1.BE_ID AND T2.PT_ID = T4.PT_ID " +
                            " " +
                            "           LEFT OUTER JOIN (SELECT BE_ID,PT_ID,PAYER_ID,PT_INS_ID_NUM " +
                            "              FROM PT_PAYER_INS WHERE (CURR_REC_IND=1 AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31')) T3 " +
                            "           ON T3.BE_ID = T2.BE_ID AND T3.PT_ID = T2.PT_ID AND T3.PAYER_ID = T1.PAYER_ID " +
                            " " +


                            "           LEFT OUTER JOIN (SELECT INV_DET_SK, INV_DET_TOTAL_AMT, BE_ID,INV_NUM, INV_DET_ID, INV_DET_SVC_DATE " +
                            "              FROM INV_DET) T6 " +
                            "           ON T6.BE_ID = T1.BE_ID AND T6.INV_NUM = T1.INV_NUM AND T6.INV_DET_ID = T1.INV_DET_ID" +

                            " " +
                            "           INNER JOIN (SELECT AR_TXN_CODE,AR_TXN_SIGN_QLFR,AR_TXN_DESC, CURR_REC_IND, REC_TERM_TMSTP " +
                            "              FROM AR_TXN_LKUP) T5 " +
                            "           ON T5.AR_TXN_CODE = T1.AR_TXN_CODE AND  (T5.CURR_REC_IND=1 AND TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') " +
                            " " +
                            "" +
                            "           INNER JOIN (SELECT BE_ID, AR_TXN_BATCH_ID, AR_TXN_BATCH_DET_SK FROM AR_TXN_BATCH_DET) T6 " +
                            " ON T1.BE_ID = T6.BE_ID AND T1.AR_TXN_BATCH_DET_SK = T6.AR_TXN_BATCH_DET_SK " +

                            "         WHERE T1.BE_ID = ? AND T6.AR_TXN_BATCH_ID = ? %s %s %s" +
                            " " +
                            "      ORDER BY %s %s)) R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    invoiceNumberFilter,
                    batchDetSKFilter,
                    unappliedFilter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, batchNumber);

            if (!StringUtil.IsNullOrEmpty(invoiceNumber)) {
                preparedStatement.setString(index++, invoiceNumber);
            }

            if (batchDetailSK > 0) {
                preparedStatement.setBigDecimal(index++, new BigDecimal(batchDetailSK));
            }

            if (!StringUtil.IsNullOrEmpty(unappliedFilter)) {
                preparedStatement.setBoolean(index++, unappliedInd);
            }

            resultSet = preparedStatement.executeQuery();
            List<ArBatchFindResult> arBatchFindResultList = new ArrayList<>();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setData(arBatchFindResultList);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                ArBatchFindResult arBatchFindResult = new ArBatchFindResult();
                arBatchFindResult.setAccountsReceivableSK(resultSet.getBigDecimal("AR_SK").toBigInteger());
                arBatchFindResult.setCrn(resultSet.getString("CRN"));
                arBatchFindResult.setInvoiceNumber(resultSet.getString("INV_NUM"));
                arBatchFindResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                arBatchFindResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                arBatchFindResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));
                arBatchFindResult.setPatientInsuranceId(resultSet.getString("PT_INS_ID_NUM"));
                arBatchFindResult.setPatientId(resultSet.getString("PT_ID"));
                arBatchFindResult.setDateOfService(resultSet.getTimestamp("INV_DET_SVC_DATE"));
                arBatchFindResult.setBillCode(resultSet.getString("BILLING_CODE"));
                arBatchFindResult.setRemittanceCode(resultSet.getString("AR_REMIT_CODE"));
                arBatchFindResult.setTransactionCode(resultSet.getString("AR_TXN_CODE"));
                arBatchFindResult.setTransactionType(resultSet.getString("AR_TXN_SIGN_QLFR"));
                arBatchFindResult.setModifier1(resultSet.getString("MDFR_1_CODE"));
                arBatchFindResult.setModifier2(resultSet.getString("MDFR_2_CODE"));
                arBatchFindResult.setModifier3(resultSet.getString("MDFR_3_CODE"));
                arBatchFindResult.setModifier4(resultSet.getString("MDFR_4_CODE"));
                arBatchFindResult.setTransactionDescription(resultSet.getString("AR_TXN_DESC"));
                arBatchFindResult.setTransactionDate(resultSet.getTimestamp("REC_CREATE_TMSTP"));


                BigDecimal paymentAmount = (BigDecimal) resultSet.getBigDecimal("AR_TXN_AMT");

                if (paymentAmount != null) {
                    arBatchFindResult.setPayment(MoneyUtil.formatCurrency(paymentAmount));
                } else {
                    arBatchFindResult.setPayment(new BigDecimal(0));
                }

                BigDecimal invoiceDetailAmount = null;

                if (resultSet.getBigDecimal("INV_DET_SK") != null) {

                    invoiceDetailAmount = resultSet.getBigDecimal("INV_DET_TOTAL_AMT");
                } else {

                    invoiceDetailAmount = resultSet.getBigDecimal("INV_TOTAL_AMT");
                }

                //Format before any operations
                invoiceDetailAmount = MoneyUtil.formatCurrency(invoiceDetailAmount);

                BigDecimal balance = null;

                if (invoiceDetailAmount != null && invoiceDetailAmount.compareTo(BigDecimal.ZERO) == 1) {
                    balance = invoiceDetailAmount.subtract(arBatchFindResult.getPayment());
                }

                arBatchFindResult.setBalance(MoneyUtil.formatCurrency(balance));


                arBatchFindResult.setInvoiceStatus(resultSet.getString("INV_STATUS_CODE"));
                arBatchFindResult.setNoteType(resultSet.getString("AR_NOTE_TYP_CODE"));
                arBatchFindResult.setNote(resultSet.getString("AR_NOTE"));
                arBatchFindResult.setPayerId(resultSet.getString("PAYER_ID"));

                arBatchFindResult.setUnappliedBalanceIND(resultSet.getBoolean("AR_UNAPPLIED_BALANCE_IND"));

                arBatchFindResult.setUserName(resultSet.getString("USER_NAME"));

                arBatchFindResultList.add(arBatchFindResult);
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

            throw new SandataRuntimeException(format("%s: %s",
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


    /***
     *
     * @param bsnEnt
     * @param payerId
     * @param contractId
     * @param invoiceDateFrom
     * @param invoiceDateTo
     * @param invoiceNumber
     * @param invoiceStartDate
     * @param invoiceEndDate
     * @param ptLastName
     * @param ptFirstName
     * @param location
     * @param lineOfBusiness
     * @param billedAmount
     * @param paidAmount
     * @param openBalance
     * @param invStatusCode
     * @param arTxnCode
     * @param page
     * @param pageSize
     * @param sortOn
     * @param direction
     * @return
     * @throws Exception
     */
    public Response findAR(String bsnEnt,
                           String payerId,
                           String contractId,
                           String invoiceDateFrom,
                           String invoiceDateTo,
                           String invoiceNumber,
                           String invoiceStartDate,
                           String invoiceEndDate,
                           String ptLastName,
                           String ptFirstName,
                           String location,
                           String lineOfBusiness,
                           BigDecimal billedAmount,
                           BigDecimal paidAmount,
                           boolean openBalance,
                           String invStatusCode,
                           String arTxnCode,
                           int page,
                           int pageSize,
                           String sortOn,
                           String direction) throws Exception {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String orderByString = " UPPER(PT_LAST_NAME)"; // Default
        switch (sortOn) {
            case "pfn":
                orderByString = " UPPER(PT_FIRST_NAME)";
                break;
            case "ptln":
                orderByString = " UPPER(PT_LAST_NAME)";
                break;
            case "contrid":
                orderByString = " TO_NUMBER(CONTR_ID)";
                break;
            //TODO: more sort column here

        }

        try {

            StringBuilder whereClause = new StringBuilder();

            List<Object> params = new ArrayList<>();
            //TODO: need to confirm

            params.add(bsnEnt);


            if (!StringUtil.IsNullOrEmpty(payerId)) {
                whereClause.append(" AND T1." + Columns.PAYER_ID.getValue() + " = ?");
                params.add(payerId);
            }


            if (!StringUtil.IsNullOrEmpty(contractId)) {
                whereClause.append(" AND T1." + Columns.CONTR_ID.getValue() + " = ?");
                params.add(contractId);
            }


            if (!StringUtil.IsNullOrEmpty(invoiceDateFrom)) {
                whereClause.append(" AND T1." + Columns.INV_DATE.getValue() + " >=  TO_DATE(?, 'YYYY-MM-DD')");
                params.add(invoiceDateFrom);
            }


            if (!StringUtil.IsNullOrEmpty(invoiceDateTo)) {
                whereClause.append(" AND T1." + Columns.INV_DATE.getValue() + " <=  TO_DATE(?, 'YYYY-MM-DD')");
                params.add(invoiceDateTo);
            }


            if (!StringUtil.IsNullOrEmpty(invoiceNumber)) {
                whereClause.append(" AND T1." + Columns.INV_NUM.getValue() + " = ?");
                params.add(invoiceNumber);
            }


            if (!StringUtil.IsNullOrEmpty(invoiceStartDate)) {
                whereClause.append(" AND T1." + Columns.INV_START_DATE.getValue() + " >=  TO_DATE(?, 'YYYY-MM-DD')");
                params.add(invoiceStartDate);
            }


            if (!StringUtil.IsNullOrEmpty(invoiceEndDate)) {
                whereClause.append(" AND T1." + Columns.INV_END_DATE.getValue() + " <=  TO_DATE(?, 'YYYY-MM-DD')");
                params.add(invoiceEndDate);
            }


            if (!StringUtil.IsNullOrEmpty(ptLastName)) {

                whereClause.append(" AND UPPER(T4." + Columns.PT_LAST_NAME.getValue() + ") LIKE ?");
                params.add(ptLastName.toUpperCase() + '%');
            }


            if (!StringUtil.IsNullOrEmpty(ptFirstName)) {

                whereClause.append(" AND UPPER(T4." + Columns.PT_FIRST_NAME.getValue() + ") LIKE ?");
                params.add(ptFirstName.toUpperCase() + '%');
            }


            if (!StringUtil.IsNullOrEmpty(location)) {

                whereClause.append(" AND T1." + Columns.BE_LOC_ID.getValue() + " = ?");
                params.add(location);
            }


            if (!StringUtil.IsNullOrEmpty(lineOfBusiness)) {

                whereClause.append(" AND T1." + Columns.BE_LOB_ID.getValue() + " = ?");
                params.add(lineOfBusiness);
            }

            if (billedAmount != null) {

                whereClause.append(" AND T1." + Columns.INV_TOTAL_AMT.getValue() + " =  ?");
                params.add(billedAmount);
            }


            //Figure out how to calculate total paid amount (sum of paid transactions for invoice?)
            if (paidAmount != null) {

                whereClause.append(" AND T2.PAID_AMT =  ?");
                params.add(paidAmount);
            }


            //TODO: TRUE
            if (openBalance) {

                whereClause.append("  AND (T1.INV_TOTAL_AMT - T2.PAID_AMT) > 0");
            }


            if (!StringUtil.IsNullOrEmpty(invStatusCode)) {

                whereClause.append(" AND T1." + Columns.INV_STATUS_CODE.getValue() + " =  ?");
                params.add(invStatusCode);
            }


            if (!StringUtil.IsNullOrEmpty(arTxnCode)) {

                whereClause.append(" AND T1." + Columns.AR_TXN_CODE.getValue() + " =  ?");
                params.add(arTxnCode);
            }

            StringBuilder selectStatement = new StringBuilder();


            selectStatement.append("SELECT DISTINCT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    "  SELECT * FROM (  SELECT DISTINCT * FROM COREDATA.INV T1 " +

                    //payer
                    " LEFT JOIN  ( SELECT PAYER_ID , PAYER_NAME, BE_ID, REC_TERM_TMSTP, CURR_REC_IND FROM PAYER) T3 ON " +
                    " T1.PAYER_ID = T3.PAYER_ID AND T1.BE_ID = T3.BE_ID " +
                    " AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                    //patient
                    "" +
                    " LEFT JOIN  ( SELECT BE_ID, PT_ID, PT_FIRST_NAME, PT_LAST_NAME, REC_TERM_TMSTP, CURR_REC_IND  FROM  PT) T4 ON " +
                    " T1.PT_ID = T4.PT_ID AND T1.BE_ID = T4.BE_ID AND " +
                    " (TO_CHAR(T4.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T4.CURR_REC_IND = 1) " +
                    " " +
                    "" +
                    " LEFT JOIN (" +

                    "SELECT INV_NUM,BE_ID, " +
                    "SUM( " +
                    "        CASE " +
                    "          WHEN J1.AR_TXN_AMT IS NOT NULL AND UPPER(J2.AR_TXN_SIGN_QLFR) = 'CREDIT'  " +
                    "          THEN J1.AR_TXN_AMT " +
                    "          WHEN J1.AR_TXN_AMT IS NOT NULL AND UPPER(J2.AR_TXN_SIGN_QLFR) = 'DEBIT'  " +
                    "          THEN 0 - J1.AR_TXN_AMT " +
                    "          ELSE 0 " +
                    "      END) AS PAID  " +
                    "FROM COREDATA.AR J1 " +
                    "" +
                    "" +
                    " INNER JOIN " +
                    "        (SELECT AR_TXN_SIGN_QLFR , AR_TXN_CODE " +
                    "            FROM AR_TXN_LKUP " +
                    "            WHERE UPPER(AR_TXN_SIGN_QLFR) IN ('CREDIT','DEBIT') " +
                    "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    "            AND CURR_REC_IND                           = 1 ) " +
                    "        ) J2 " +
                    "      ON J1.AR_TXN_CODE = J2.AR_TXN_CODE" +
                    "" +
                    "" +
                    "            GROUP BY BE_ID, INV_NUM)  T7" +
                    "   ON T1.BE_ID = T7.BE_ID AND T1.INV_NUM = T7.INV_NUM " +
                    "" +

                    " LEFT JOIN ( SELECT INV_SUBM_TYP_DESC, INV_SUBM_TYP_CODE, REC_TERM_TMSTP, CURR_REC_IND FROM INV_SUBM_TYP_LKUP) T8 " +
                    " ON T8.INV_SUBM_TYP_CODE = T1.INV_SUBM_TYP_CODE " +
                    " AND (TO_CHAR(T8.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T8.CURR_REC_IND = 1) " +
                    "" +
                    " LEFT JOIN ( SELECT INV_STATUS_DESC, INV_STATUS_NAME, INV_STATUS_CODE, REC_TERM_TMSTP, CURR_REC_IND FROM INV_STATUS_LKUP) T9 " +
                    " ON T9.INV_STATUS_CODE = T1.INV_STATUS_CODE " +
                    " AND (TO_CHAR(T9.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T9.CURR_REC_IND = 1) " +
                    "" +
                    " LEFT JOIN (SELECT CONTR_ID,BE_ID, CONTR_DESC FROM CONTR" +
                    "       WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T10" +
                    "   ON T1.BE_ID = T10.BE_ID AND T1.CONTR_ID = T10.CONTR_ID " +
                    "" +
                    " LEFT JOIN (SELECT BE_ID, BE_LOB, BE_LOB_DESC FROM BE_LOB_LKUP " +
                    " WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T11" +
                    "   ON T1.BE_LOB_ID = T11.BE_ID " +
                    "" +
//                    " LEFT JOIN (SELECT BE_ID,BE_LOB " +
//                    "   FROM BE_LOB_LKUP WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) T11 " +
//                    " ON T1.BE_LOB_ID = T11.BE_ID " +
//                    "" +
                    " WHERE T1.BE_ID = ? ");
            selectStatement.append(whereClause);

            selectStatement.append(" AND T1.INV_SUBM_TYP_CODE IN (?,?) ");
            selectStatement.append(" AND T1.INV_STATUS_CODE = ? ");
            selectStatement.append(format(" ORDER BY %s %s " +
                    " ) " +
                    ") R1) " +

                    " WHERE ROW_NUMBER BETWEEN ? AND ?", orderByString, direction));

            //for pagination
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            //T1.INV_SUBM_TYP_CODE params
            params.add("1");
            params.add("2");

            //INV_STATUS_CODE param
            params.add("1");

            params.add(fromRow);
            params.add(toRow);


            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(selectStatement.toString());

            int index = 1;
            for (Object object : params) {
                preparedStatement.setObject(index, object);
                index++;
            }
            //executing prepare statement
            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableFindResult> arFindResultList = new ArrayList<>();

            Response response = new Response();
            response.setData(arFindResultList);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                // mapping to object
                arFindResultList.add(mapAR(resultSet));
            }


            return response;
        } catch (Exception e) {
            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
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


    /**
     * @param resultSet
     * @return
     */

    private AccountsReceivableFindResult mapAR(ResultSet resultSet) throws SQLException {

        AccountsReceivableFindResult result = new AccountsReceivableFindResult();

        // result.setAccountsReceivableSK(resultSet.getBigDecimal("AR_SK"));
        result.setInvoiceSK(resultSet.getBigDecimal("INV_SK"));

        result.setLineOfBusiness(resultSet.getString("BE_LOB"));
        result.setPayerName(resultSet.getString("PAYER_NAME"));
        result.setContractName(resultSet.getString("CONTR_DESC"));

        Timestamp startDate = resultSet.getTimestamp("INV_START_DATE");
        if (startDate != null) {
            result.setInvoiceStartDate(new java.util.Date(startDate.getTime()));
        }

        Timestamp endDate = resultSet.getTimestamp("INV_END_DATE");
        if (endDate != null) {
            result.setInvoiceEndDate(new java.util.Date(endDate.getTime()));
        }

        Timestamp invDate = resultSet.getTimestamp("INV_DATE");
        if (invDate != null) {
            result.setInvoiceDate(new java.util.Date(invDate.getTime()));
        }

        result.setInvoiceNumber(resultSet.getString("INV_NUM"));
        result.setPatienttLastName(resultSet.getString("PT_LAST_NAME"));
        result.setPatienttFirstName(resultSet.getString("PT_FIRST_NAME"));

        BigDecimal billed = resultSet.getBigDecimal("INV_TOTAL_AMT");
        if (billed != null) {
            result.setBilledAmount(MoneyUtil.formatCurrency(billed));
        }

        BigDecimal paid = resultSet.getBigDecimal("PAID");
        if (paid != null) {
            result.setPaidAmount(MoneyUtil.formatCurrency(paid));
        } else {
            result.setPaidAmount(new BigDecimal(0));
        }

        //balance amount
        if (result.getBilledAmount() != null && result.getBilledAmount().compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal paidAmnt = result.getPaidAmount() == null ? new BigDecimal(0) : result.getPaidAmount();
            result.setBalanceAmount(MoneyUtil.formatCurrency(result.getBilledAmount().subtract(paidAmnt)));
        } else {
            result.setBalanceAmount(MoneyUtil.formatCurrency(BigDecimal.ZERO));
        }

        result.setContractId(resultSet.getString("CONTR_ID"));
        result.setPayerId(resultSet.getString("PAYER_ID"));

        // result.setTransactionCode(resultSet.getString("AR_TXN_CODE"));
        result.setInvoiceSubmissionStatusCode(resultSet.getString("INV_SUBM_TYP_CODE"));
        result.setInvoiceStatusCode(resultSet.getString("INV_STATUS_CODE"));
        result.setInvoiceStatusDescription(resultSet.getString("INV_STATUS_NAME"));
        result.setInvoiceSubmissionStatusDescription(resultSet.getString("INV_SUBM_TYP_DESC"));
        //  result.setDenialCode(resultSet.getString("AR_REMIT_CODE"));


        return result;
    }

    public ArInvoiceFindResult findInvoiceByInvoiceNumber(String invoiceNumber, String payerId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String payerIdFilter = "";
            if (!StringUtil.IsNullOrEmpty(payerId)) {
                payerIdFilter = " AND T3.PAYER_ID = ? ";
            }

            String sql = String.format("SELECT DISTINCT T1.*, " +
                            "          T2.BE_LOB,T3.PAYER_NAME,T4.CONTR_DESC,T5.PT_FIRST_NAME,T5.PT_LAST_NAME, " +
                            "          T3.PAYER_ID,T4.CONTR_ID,T5.PT_ID,T5.PT_MIDDLE_NAME,T5.PT_MEDICARE_ID,T5.PT_MEDICAID_ID,T5.PT_DOB,T5.PT_GENDER_TYP_NAME, " +
                            "          T5.PT_TIN_QLFR,T5.PT_TIN,T5.PT_DISCHARGE_DATE, " +
                            "          T7.PT_ADDR1,PT_ADDR2,T7.PT_APT_NUM,T7.PT_CITY,T7.PT_STATE,T7.PT_PSTL_CODE, " +
                            "          T7.PT_ZIP4,T7.PT_COUNTY,T7.PT_REGION,T7.PT_BOROUGH, " +
                            "          T8.PT_CONT_PHONE_QLFR,T8.PT_PHONE,T8.PT_PHONE_EXT, " +
                            "          T9.BE_ID,T9.BE_FEDERALTAX_ID,T9.BE_NPI, " +
                            "          T10.AUTH_START_TMSTP, " +
                            "          T11.PT_INS_GRP_NUM,T11.PT_INS_GRP_NAME " +
                            " " +
                            "        FROM INV T1 " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,BE_LOB " +
                            "            FROM BE_LOB_LKUP " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                            "            ON T1.BE_LOB_ID = T2.BE_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,PAYER_NAME " +
                            "            FROM PAYER " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T3 " +
                            "            ON T1.BE_ID = T3.BE_ID AND T1.PAYER_ID = T3.PAYER_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PAYER_ID,CONTR_ID,CONTR_DESC " +
                            "            FROM CONTR " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T4 " +
                            "            ON T1.BE_ID = T4.BE_ID AND T1.PAYER_ID = T4.PAYER_ID AND T1.CONTR_ID = T4.CONTR_ID " +
                            " " +
                            "           LEFT JOIN (SELECT BE_ID,PT_ID,PT_FIRST_NAME,PT_LAST_NAME,PT_MIDDLE_NAME,PT_MEDICARE_ID,PT_MEDICAID_ID, " +
                            "                        PT_DOB,PT_GENDER_TYP_NAME,PT_TIN_QLFR,PT_TIN,PT_DISCHARGE_DATE " +
                            "            FROM PT " +
                            "              WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T5 " +
                            "            ON T1.BE_ID = T5.BE_ID AND T1.PT_ID = T5.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT ICD_DX_CODE,ICD_DX_CODE_SHORT_DESC " +
                            "            FROM ICD_DX_LKUP) T6 " +
                            "          ON T1.ICD_DX_CODE_PRMY = T6.ICD_DX_CODE " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PT_ID,PT_ADDR1,PT_ADDR2,PT_APT_NUM,PT_CITY,PT_STATE, " +
                            "                      PT_PSTL_CODE,PT_ZIP4,PT_COUNTY,PT_REGION,PT_BOROUGH " +
                            "            FROM PT_CONT_ADDR " +
                            "              WHERE (ADDR_PRIO_NAME = 'PRIMARY' AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T7 " +
                            "          ON T5.BE_ID = T7.BE_ID AND T5.PT_ID = T7.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PT_ID,PT_CONT_PHONE_QLFR,PT_PHONE,PT_PHONE_EXT " +
                            "            FROM PT_CONT_PHONE " +
                            "              WHERE ((ADDR_PRIO_NAME = 'PRIMARY' OR PT_PHONE_PRMY_IND = 1) AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T8 " +
                            "          ON T5.BE_ID = T8.BE_ID AND T5.PT_ID = T8.PT_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_FEDERALTAX_ID,BE_ID,BE_NPI " +
                            "            FROM BE WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T9 " +
                            "          ON T1.BE_ID = T9.BE_ID " +
                            " " +
                            "          LEFT JOIN (SELECT AUTH_START_TMSTP,PAYER_ID,PT_ID,BE_ID,AUTH_ID " +
                            "            FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T10 " +
                            "          ON T1.BE_ID = T10.BE_ID AND T1.PT_ID = T10.PT_ID AND T1.PAYER_ID = T10.PAYER_ID AND T1.AUTH_ID = T10.AUTH_ID " +
                            " " +
                            "          LEFT JOIN (SELECT BE_ID,PT_ID,PAYER_ID,PT_INS_GRP_NUM,PT_INS_GRP_NAME " +
                            "            FROM PT_PAYER_INS WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T11 " +
                            "          ON T1.BE_ID = T11.BE_ID AND T1.PT_ID = T11.PT_ID AND T1.PAYER_ID = T11.PAYER_ID " +
                            " " +
                            "WHERE INV_NUM = ? %s AND T1.BE_ID = ? ",

                    payerIdFilter);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, invoiceNumber);

            if (!StringUtil.IsNullOrEmpty(payerIdFilter)) {
                preparedStatement.setString(index++, payerId);
            }
            preparedStatement.setString(index++, bsnEntId);

            ArInvoiceFindResult arInvoiceFindResult = null;

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                arInvoiceFindResult = new ArInvoiceFindResult();

                arInvoiceFindResult.setInvoiceSk(BigInteger.valueOf(resultSet.getBigDecimal("INV_SK").longValue()));
                arInvoiceFindResult.setInvoiceNumber(resultSet.getString("INV_NUM"));

                arInvoiceFindResult.setBusinessEntityId(resultSet.getString("BE_ID"));
                arInvoiceFindResult.setPatientMedicareID(resultSet.getString("PT_MEDICARE_ID"));
                arInvoiceFindResult.setPatientMedicaidID(resultSet.getString("PT_MEDICAID_ID"));

                Timestamp patientDob = resultSet.getTimestamp("PT_DOB");
                if (patientDob != null) {
                    arInvoiceFindResult.setPatientDateOfBirth(new Date(patientDob.getTime()));
                }

                arInvoiceFindResult.setPatientGender(resultSet.getString("PT_GENDER_TYP_NAME"));

                arInvoiceFindResult.setPatientLastName(resultSet.getString("PT_LAST_NAME"));
                arInvoiceFindResult.setPatientFirstName(resultSet.getString("PT_FIRST_NAME"));
                arInvoiceFindResult.setPatientMiddleName(resultSet.getString("PT_MIDDLE_NAME"));
                arInvoiceFindResult.setPatientInsuranceIdNumber(resultSet.getString("PT_INS_ID_NUM"));
                arInvoiceFindResult.setPatientId(resultSet.getString("PT_ID"));

                arInvoiceFindResult.setInvoiceStatus(resultSet.getString("INV_STATUS_CODE"));
                arInvoiceFindResult.setCrn(resultSet.getString("CRN"));

                BigDecimal balance = resultSet.getBigDecimal("INV_TOTAL_AMT");
                if (balance != null) {
                    arInvoiceFindResult.setBalance(balance);
                }

                // Invoice Details
                arInvoiceFindResult.setInvoiceDetailList(getInvoiceDetailList(invoiceNumber, bsnEntId));
            }

            return arInvoiceFindResult;

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

    private List<InvoiceDetail> getInvoiceDetailList(String invoiceNumber, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM INV_DET " +
                    "  WHERE INV_NUM = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, invoiceNumber);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<InvoiceDetail> resultList =
                    (List<InvoiceDetail>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.InvoiceDetail");

            return resultList;

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

    public Response findArTxnBatch(String bsnEntId,
                                   String batchNumber,
                                   String payerId,
                                   String checkNumber,
                                   String checkDepositDateString,
                                   BigDecimal totalAmount,
                                   BigDecimal paymentPostedUnapplied,
                                   BigDecimal totalDenied,
                                   BigDecimal totalPaid,
                                   BigDecimal totalOpen,
                                   Boolean batchPosted,
                                   Integer page,
                                   Integer pageSize,
                                   String sortOn,
                                   String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);

            StringBuilder whereClause = new StringBuilder();
            List<Object> params = new ArrayList<>();

            params.add(bsnEntId);
            whereClause.append(" WHERE T1.BE_ID = ? ");

            if (batchNumber != null
                    && !batchNumber.isEmpty()) {
                params.add(batchNumber);
                whereClause.append(" AND T1.AR_TXN_BATCH_ID = ?");
            }

            if (payerId != null
                    && !payerId.isEmpty()) {
                params.add(payerId);
                whereClause.append(" AND T1.PAYER_ID = ?");
            }

            if (checkNumber != null
                    && !checkNumber.isEmpty()) {
                params.add(checkNumber);
                whereClause.append(" AND T1.PMT_TYP_NUM = ? ");
            }

            if (checkDepositDateString != null
                    && !checkDepositDateString.isEmpty()) {
                params.add(checkDepositDateString);
                whereClause.append(" AND T1.CHECK_DATE = TO_DATE(?, 'YYYY-MM-DD')");
            }

            if (totalAmount != null) {
                params.add(totalAmount);
                whereClause.append(" AND T1.TOTAL_AMT = ?");
            }

            if (paymentPostedUnapplied != null) {
                params.add(paymentPostedUnapplied);
                whereClause.append(" AND T1.PAYMENT_POSTED_UNAPPLIED = ? ");
            }

            if (totalDenied != null) {
                params.add(totalDenied);
                whereClause.append(" AND T2.TOTAL_DENIED = ? ");
            }

            if (totalPaid != null) {
                params.add(totalPaid);
                whereClause.append(" AND T2.TOTAL_PAID = ? ");
            }


            if (totalOpen != null) {
                params.add(totalOpen);
                whereClause.append(" AND T2.TOTAL_OPEN = ? ");
            }

            if (batchPosted != null) {
                params.add(batchPosted);
                whereClause.append(" AND T1.AR_TXN_BATCH_POST_IND = ?");
            }

            String groupBy = " GROUP BY T1.PMT_TYP_NUM, " +
                    "            T1.AR_TXN_BATCH_DET_SK, " +
                    "            T2.TOTAL_DENIED, " +
                    "            T1.AR_TXN_BATCH_ID, " +
                    "            T1.CHECK_DEPOSIT_DATE, " +
                    "            T1.CHECK_RCVD_DATE, " +
                    "            T3.PAYER_NAME, " +
                    "            T3.PAYER_ID, " +
                    "            T1.AR_TXN_BATCH_POST_IND, " +
                    "            T1.CHECK_DATE, " +
                    "            T1.PMT_AMT, " +
                    "            T1.PMT_TYP_QLFR, " +
                    "            TOTAL_PAID," +
                    "            T2.TOTAL_AMT," +
                    "            T2.PAYMENT_POSTED_UNAPPLIED," +
                    "            T2.WRITE_OFF_DEBITS_TOTAL," +
                    "            T2.ADJUSTMENT_CREDITS_TOTAL," +
                    "            T2.ADJUSTMENT_DEBITS_TOTAL," +
                    "            T2.CHECK_PAYMENTS " ;

            String sql = String.format("SELECT * FROM (" +
                            " SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (" +
                            "   SELECT " +
                            "      T1.AR_TXN_BATCH_DET_SK, " +
                            "      T1.PMT_TYP_NUM, " +
                            "      T1.CHECK_DATE, " +
                            "      T1.CHECK_DEPOSIT_DATE, " +
                            "      T1.CHECK_RCVD_DATE, " +
                            "      T1.PMT_AMT, " +
                            "      T2.TOTAL_DENIED, " +
                            "      T1.AR_TXN_BATCH_ID," +
                            "      T1.PMT_TYP_QLFR, " +
                            "      T3.PAYER_NAME, " +
                            "      T3.PAYER_ID, " +
                            "      T1.AR_TXN_BATCH_POST_IND," +
                            "      T2.TOTAL_PAID," +
                            "      T2.TOTAL_AMT, " +
                            "      T2.PAYMENT_POSTED_UNAPPLIED, " +
                            "      T2.WRITE_OFF_DEBITS_TOTAL, " +
                            "      T2.ADJUSTMENT_CREDITS_TOTAL," +
                            "      T2.ADJUSTMENT_DEBITS_TOTAL," +
                            "      T2.CHECK_PAYMENTS, " +
                            "       ( T2.TOTAL_AMT - TOTAL_PAID - T2.TOTAL_DENIED ) AS TOTAL_OPEN " +
                            "       " +
                            "       " +
                            "    FROM AR_TXN_BATCH_DET T1 " +

                            "  LEFT OUTER JOIN " +
                            "      (SELECT J1.BE_ID, " +
                            "        J1.AR_TXN_BATCH_DET_SK, " +
                            "" +
                            "        SUM( " +
                            "        CASE " +
                            "          WHEN J3.INV_DET_TOTAL_AMT IS NOT NULL AND J1.AR_UNAPPLIED_BALANCE_IND = 0 " +
                            "          AND J1.AR_TXN_AMT          < J3.INV_DET_TOTAL_AMT " +
                            "          THEN (J3.INV_DET_TOTAL_AMT - J1.AR_TXN_AMT) " +
                            "          WHEN J1.AR_TXN_AMT < J2.INV_TOTAL_AMT AND J1.AR_UNAPPLIED_BALANCE_IND = 0 " +
                            "          THEN (J2.INV_TOTAL_AMT - J1.AR_TXN_AMT) " +
                            "          ELSE 0 " +
                            "        END) AS TOTAL_DENIED, " +
                            "" +
                            "        SUM(J2.INV_TOTAL_AMT)/COUNT(J2.INV_NUM) AS TOTAL_AMT, " +

                            "        SUM ( " +
                            "        CASE " +
                            "          WHEN J1.AR_TXN_AMT      IS NOT NULL " +
                            "          AND UPPER(J4.AR_TXN_SIGN_QLFR) = 'CREDIT' " +
                            "          THEN J1.AR_TXN_AMT " +
                            "          WHEN J1.AR_TXN_AMT      IS NOT NULL " +
                            "          AND UPPER(J4.AR_TXN_SIGN_QLFR) = 'DEBIT' " +
                            "          THEN 0 - J1.AR_TXN_AMT " +
                            "          ELSE 0 " +
                            "        END) AS TOTAL_PAID, " +

                            "        SUM ( " +
                                "        CASE " +
                                "          WHEN J1.AR_TXN_AMT IS NOT NULL " +
                                "          AND UPPER(J4.AR_TXN_SIGN_QLFR) = 'CREDIT' AND UPPER(J4.AR_TXN_CTGY_CODE) = 'PAYMENT' " +
                                "          THEN J1.AR_TXN_AMT " +
                                "          WHEN J1.AR_TXN_AMT IS NOT NULL " +
                                "          AND UPPER(J4.AR_TXN_SIGN_QLFR) = 'DEBIT' AND UPPER(J4.AR_TXN_CTGY_CODE) = 'PAYMENT' " +
                                "          THEN 0 - J1.AR_TXN_AMT " +
                                "          ELSE 0 " +
                                "        END) AS CHECK_PAYMENTS, " +
                            "" +
                            "        SUM ( " +
                            "        CASE " +
                            "          WHEN J1.AR_TXN_AMT IS NOT NULL " +
                            "          AND J1.AR_UNAPPLIED_BALANCE_IND = 1 " +
                            "          THEN J1.AR_TXN_AMT " +
                            "          ELSE 0 " +
                            "        END) AS PAYMENT_POSTED_UNAPPLIED," +
                            "" +
                            "       SUM (" +
                            "           CASE " +
                            "               WHEN UPPER(J4.AR_TXN_CTGY_CODE) = 'WRITE-OFF' AND " +
                            "                     UPPER(J4.AR_TXN_SIGN_QLFR) = 'DEBIT'" +
                            "               THEN J1.AR_TXN_AMT" +
                            "               ELSE 0" +
                            "           END) AS WRITE_OFF_DEBITS_TOTAL, " +

                            "       SUM (" +
                            "           CASE " +
                            "               WHEN UPPER(J4.AR_TXN_CTGY_CODE) = 'ADJUSTMENT' AND" +
                            "                     UPPER(J4.AR_TXN_SIGN_QLFR) = 'CREDIT'" +
                            "               THEN J1.AR_TXN_AMT" +
                            "               ELSE 0" +
                            "           END) AS ADJUSTMENT_CREDITS_TOTAL, " +
                            "" +

                            "       SUM (" +
                            "           CASE " +
                            "               WHEN UPPER(J4.AR_TXN_CTGY_CODE) = 'ADJUSTMENT' AND" +
                            "                     UPPER(J4.AR_TXN_SIGN_QLFR) = 'DEBIT'" +
                            "               THEN J1.AR_TXN_AMT" +
                            "               ELSE 0" +
                            "           END) AS ADJUSTMENT_DEBITS_TOTAL " +

                            "      FROM COREDATA.AR J1 " +
                            "" +
                            "      LEFT OUTER JOIN " +
                            "        (SELECT INV_NUM, BE_ID, INV_TOTAL_AMT FROM INV " +
                            "        ) J2 " +
                            "      ON J1.BE_ID    = J2.BE_ID " +
                            "      AND J1.INV_NUM = J2.INV_NUM " +
                            "" +
                            "" +
                            "      LEFT OUTER JOIN " +
                            "        (SELECT INV_NUM,BE_ID,INV_DET_TOTAL_AMT, INV_DET_ID FROM INV_DET " +
                            "        ) J3 " +
                            "      ON J1.BE_ID        = J3.BE_ID " +
                            "      AND J1.INV_NUM     = J3.INV_NUM " +
                            "      AND J1.INV_DET_ID IS NOT NULL " +
                            "      AND J1.INV_DET_ID  = J3.INV_DET_ID " +
                            "" +
                            "" +
                            "      INNER JOIN " +
                            "        (SELECT AR_TXN_CODE," +
                            "          AR_TXN_CTGY_CODE, " +
                            "          AR_TXN_SIGN_QLFR, " +
                            "          CURR_REC_IND, " +
                            "          REC_TERM_TMSTP " +
                            "        FROM AR_TXN_LKUP " +
                            "        WHERE UPPER(AR_TXN_SIGN_QLFR) IN ('CREDIT','DEBIT') " +
                            "        AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                            "        AND CURR_REC_IND = 1) " +
                            "        ) J4 " +
                            "      ON J1.AR_TXN_CODE = J4.AR_TXN_CODE " +
                            "" +
                            "" +
                            "      GROUP BY J1.BE_ID,  " +
                            "        J1.AR_TXN_BATCH_DET_SK " +
                            "      ) T2 ON T1.BE_ID = T2.BE_ID " +
                            "    AND T1.AR_TXN_BATCH_DET_SK = T2.AR_TXN_BATCH_DET_SK" +
                            " " +
                            " " +
                            "    LEFT OUTER JOIN  ( SELECT PAYER_ID , PAYER_NAME, BE_ID, REC_TERM_TMSTP, CURR_REC_IND FROM PAYER) T3 ON " +
                            "                     T1.PAYER_ID = T3.PAYER_ID AND T1.BE_ID = T3.BE_ID  " +
                            "                     AND (TO_CHAR(T3.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T3.CURR_REC_IND = 1) " +
                            " %s  %s " +
                            "    ORDER BY T1.%s %s) R1)    " +
                            " WHERE ROW_NUMBER BETWEEN %s AND %s",
                    whereClause.toString(),
                    groupBy,
                    sortOn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<ArTxnBatchManualPostFindResult> accountsReceivableTransactionBatchManualPostFindResults = new ArrayList<>();
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setData(accountsReceivableTransactionBatchManualPostFindResults);

            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                accountsReceivableTransactionBatchManualPostFindResults.add(mapResultSetToAccountsReceivableTransactionBatchManualPostFindResult(resultSet));
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

    private ArTxnBatchManualPostFindResult mapResultSetToAccountsReceivableTransactionBatchManualPostFindResult(ResultSet resultSet) throws SQLException {
        ArTxnBatchManualPostFindResult result = new ArTxnBatchManualPostFindResult();

        result.setBatchDetailSK(resultSet.getBigDecimal("AR_TXN_BATCH_DET_SK").toBigInteger());
        result.setBatchNumber(resultSet.getString("AR_TXN_BATCH_ID"));
        result.setBatchDepositDate(resultSet.getTimestamp("CHECK_DEPOSIT_DATE"));
        result.setCheckReceived((resultSet.getTimestamp("CHECK_RCVD_DATE")));
        result.setBatchPayerName(resultSet.getString("PAYER_NAME"));
        result.setBatchCheckNumber(resultSet.getString("PMT_TYP_NUM"));
        result.setCheckDate(resultSet.getTimestamp("CHECK_DATE"));
        result.setPaymentType(resultSet.getString("PMT_TYP_QLFR"));

        result.setBatchTotalAmount(MoneyUtil.formatCurrency(resultSet.getBigDecimal("TOTAL_AMT")));
        result.setBatchUnappliedPaymentPosted(MoneyUtil.formatCurrency(resultSet.getBigDecimal("PAYMENT_POSTED_UNAPPLIED")));
        result.setBatchTotalDenied(MoneyUtil.formatCurrency(resultSet.getBigDecimal("TOTAL_DENIED")));
        result.setBatchTotalPaid(MoneyUtil.formatCurrency(resultSet.getBigDecimal("TOTAL_PAID")));


        BigDecimal totalOpen = MoneyUtil.formatCurrency(resultSet.getBigDecimal("TOTAL_OPEN"));

        if (totalOpen != null && totalOpen.compareTo(BigDecimal.ZERO) == -1) {
            totalOpen = BigDecimal.ZERO;
        }
        else if(totalOpen != null && totalOpen.compareTo(result.getBatchTotalAmount()) == 1){

            totalOpen = result.getBatchTotalAmount();
        }

        result.setBatchTotalOpen(MoneyUtil.formatCurrency(totalOpen));

        result.setBatchPosted(resultSet.getBoolean("AR_TXN_BATCH_POST_IND"));
        result.setBatchPayerID(resultSet.getString("PAYER_ID"));
        result.setBatchCheckAmount(MoneyUtil.formatCurrency(resultSet.getBigDecimal("PMT_AMT")));

        BigDecimal paymentBalance = null;
        BigDecimal checkPayments = resultSet.getBigDecimal("CHECK_PAYMENTS");

        if(checkPayments == null){

             checkPayments = BigDecimal.ZERO;
        }

        paymentBalance = result.getBatchCheckAmount().subtract(checkPayments);

        //Check balance cant be a negative value
        if (paymentBalance.compareTo(BigDecimal.ZERO) == -1) {

            paymentBalance = BigDecimal.ZERO;

        }else if(paymentBalance.compareTo(result.getBatchCheckAmount()) == 1) {

            paymentBalance = result.getBatchCheckAmount();
        }

        result.setWriteOffDebitTotal(MoneyUtil.formatCurrency(resultSet.getBigDecimal("WRITE_OFF_DEBITS_TOTAL")));
        result.setAdjustmentCreditTotal(MoneyUtil.formatCurrency(resultSet.getBigDecimal("ADJUSTMENT_CREDITS_TOTAL")));
        result.setAdjustmentDebitTotal(MoneyUtil.formatCurrency(resultSet.getBigDecimal("ADJUSTMENT_DEBITS_TOTAL")));

        result.setBatchPaymentBalance(MoneyUtil.formatCurrency(paymentBalance));


        return result;
    }

    public Response getARServiceDetails(String bsnEnt,
                                        String payerId,
                                        String contractId,
                                        String invoiceNumber,
                                        int page,
                                        int pageSize,
                                        String sortOn,
                                        String direction) throws Exception {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            StringBuilder whereClause = new StringBuilder();

            String orderByString = null;

            if (sortOn.equalsIgnoreCase("dos")) {

                orderByString = "INV_DET_SVC_DATE";
            }


            StringBuilder selectStatement = new StringBuilder();


            selectStatement.append(String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    "  SELECT * FROM (  SELECT * FROM COREDATA.INV_DET T1 " +
                    "       LEFT JOIN INV T2 ON T1.BE_ID = T2.BE_ID AND T1.INV_NUM = T2.INV_NUM " +
                    "     " +
                    "       LEFT OUTER JOIN (SELECT AR_REMIT_CODE, AR_TXN_TOTAL_UNIT, AR_TXN_UNIT_RATE, AR_TXN_CODE, BE_ID, INV_DET_ID, SUM (CASE WHEN AR_TXN_AMT IS NOT NULL THEN AR_TXN_AMT ELSE 0 END) AS PAID  " +
                    "       FROM AR GROUP BY AR_REMIT_CODE,  AR_TXN_TOTAL_UNIT, " +
                    "          AR_TXN_UNIT_RATE, AR_TXN_CODE, BE_ID, INV_DET_ID) T3 ON T1.BE_ID = T3.BE_ID AND T1.INV_DET_ID = T3.INV_DET_ID " +
                    "     " +
                    "" +

                    // Credits
                    "        LEFT OUTER JOIN (SELECT BE_ID, INV_DET_ID, SUM (CASE WHEN AR_TXN_AMT IS NOT NULL THEN AR_TXN_AMT ELSE 0 END) AS CREDITS" +
                    "                         FROM AR INNER JOIN AR_TXN_LKUP ON AR.AR_TXN_CODE = AR_TXN_LKUP.AR_TXN_CODE" +
                    "                         WHERE UPPER(AR_TXN_LKUP.AR_TXN_SIGN_QLFR) = 'CREDIT' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                    "                         GROUP BY BE_ID, INV_DET_ID) CREDITS_T" +
                    "        ON T1.BE_ID = CREDITS_T.BE_ID AND T1.INV_DET_ID = CREDITS_T.INV_DET_ID" +


                    // Debits
                    "        LEFT OUTER JOIN (SELECT BE_ID, INV_DET_ID, SUM (CASE WHEN AR_TXN_AMT IS NOT NULL THEN AR_TXN_AMT ELSE 0 END) AS DEBITS" +
                    "                         FROM AR INNER JOIN AR_TXN_LKUP ON AR.AR_TXN_CODE = AR_TXN_LKUP.AR_TXN_CODE" +
                    "                         WHERE UPPER(AR_TXN_LKUP.AR_TXN_SIGN_QLFR) = 'DEBIT' AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)" +
                    "                         GROUP BY BE_ID, INV_DET_ID) DEBITS_T" +
                    "        ON T1.BE_ID = DEBITS_T.BE_ID AND T1.INV_DET_ID = DEBITS_T.INV_DET_ID" +

                    "" +
                    "       LEFT OUTER JOIN COREDATA.INV_DET_STATUS_LKUP T4 " +
                    "           ON T1.INV_DET_STATUS_CODE = T4.INV_DET_STATUS_CODE " +
                    " " +
                    "" +
                    "       LEFT OUTER JOIN COREDATA.AR_TXN_LKUP T5 " +
                    "           ON T3.AR_TXN_CODE = T5.AR_TXN_CODE AND (TO_CHAR(T5.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T5.CURR_REC_IND = 1) " +
                    " " +
                    "       LEFT OUTER JOIN COREDATA.AR_TXN_CTGY_LKUP T6 " +
                    "           ON T5.AR_TXN_CTGY_CODE = T6.AR_TXN_CTGY_CODE AND (TO_CHAR(T6.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T6.CURR_REC_IND = 1) " +
                    " " +
                    "       LEFT OUTER JOIN COREDATA.INV_STATUS_LKUP T7 " +
                    "           ON T7.INV_STATUS_CODE = T2.INV_STATUS_CODE AND (TO_CHAR(T7.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T7.CURR_REC_IND = 1) " +

                    " " +
                    "   WHERE T1.BE_ID = ? AND T2.PAYER_ID = ? AND T1.INV_NUM = ? " +
                    "   ORDER BY %s %s " +
                    " ) " +
                    ") R1) " +

                    " WHERE ROW_NUMBER BETWEEN ? AND ?", orderByString, direction));

            //for pagination
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);


            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(selectStatement.toString());


            preparedStatement.setString(1, bsnEnt);
            preparedStatement.setString(2, payerId);
            //   preparedStatement.setString(3, contractId);
            preparedStatement.setString(3, invoiceNumber);
            preparedStatement.setInt(4, fromRow);
            preparedStatement.setInt(5, toRow);

            //executing prepare statement
            resultSet = preparedStatement.executeQuery();

            Response response = mapARExtResultSet(resultSet);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);


            return response;
        } catch (Exception e) {
            e.printStackTrace();

            // Rollback
            if (connection != null) {

                try {
                    connection.rollback();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            throw new SandataRuntimeException(format("%s: %s",
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

    private Response mapARExtResultSet(ResultSet resultSet) throws Exception {

        List<AccountsReceivableExt> accountsReceivableExts = new ArrayList<>();

        Response response = new Response();

        while (resultSet.next()) {

            if (response.getTotalRows() == 0) {
                response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
            }

            AccountsReceivableExt accountsReceivableExt = new AccountsReceivableExt();
            //     accountsReceivableExt.setAccountsReceivableSK(resultSet.getBigDecimal("AR_SK").toBigInteger());

            accountsReceivableExt.setDateOfService(resultSet.getTimestamp("INV_DET_SVC_DATE"));
            accountsReceivableExt.setRevenueCode(resultSet.getString("REV_CODE"));

            accountsReceivableExt.setModifier1Code(resultSet.getString("MDFR_1_CODE"));
            accountsReceivableExt.setModifier2Code(resultSet.getString("MDFR_2_CODE"));
            accountsReceivableExt.setModifier3Code(resultSet.getString("MDFR_3_CODE"));
            accountsReceivableExt.setModifier4Code(resultSet.getString("MDFR_4_CODE"));

            accountsReceivableExt.setAccountsReceivableTransactionCode(resultSet.getString("AR_TXN_CODE"));
            accountsReceivableExt.setDenialCode(resultSet.getString("AR_REMIT_CODE"));

            accountsReceivableExt.setServiceStatusName(resultSet.getString("INV_DET_STATUS_NAME"));
            accountsReceivableExt.setInvoiceStatusName(resultSet.getString("INV_STATUS_NAME"));
            accountsReceivableExt.setServiceSubmissionStatusName(resultSet.getString("INV_DET_SUBM_STATUS_NAME"));
            accountsReceivableExt.setInvoiceDetailID(resultSet.getString("INV_DET_ID"));

            String serviceName = resultSet.getString("SVC_NAME");

            if (!StringUtil.IsNullOrEmpty(serviceName)) {

                try {

                    ServiceName serviceName1 = ServiceName.fromValue(serviceName);
                    accountsReceivableExt.setServiceName(serviceName1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String rateTypeValue = resultSet.getString("RATE_TYP_NAME");


            accountsReceivableExt.setRateTypeName(rateTypeValue);

            BigDecimal unitRate = resultSet.getBigDecimal("AR_TXN_UNIT_RATE");

            if (unitRate == null) {
                unitRate = resultSet.getBigDecimal("INV_DET_RATE_AMT");
            }

            accountsReceivableExt.setRateAmount(unitRate);

            BigDecimal units = resultSet.getBigDecimal("AR_TXN_TOTAL_UNIT");

            if (units != null) {

                units.divide(new BigDecimal(60), MathContext.DECIMAL128).divide(new BigDecimal(15), MathContext.DECIMAL128);
            } else {
                units = new BigDecimal(0);
            }

            accountsReceivableExt.setInvoiceTotalUnit(units);

            BigDecimal invTotal = resultSet.getBigDecimal("INV_DET_TOTAL_AMT");

            accountsReceivableExt.setInvoiceTotalAmount(invTotal);
            accountsReceivableExt.setBillingCode(resultSet.getString("BILLING_CODE"));

            BigDecimal transactionAmount = resultSet.getBigDecimal("CREDITS");
            accountsReceivableExt.setAccountsReceivableTransactionAmount(transactionAmount);

            BigDecimal credits = resultSet.getBigDecimal("CREDITS");
            accountsReceivableExt.setCredits(credits != null ? credits : new BigDecimal(0));

            BigDecimal debits = resultSet.getBigDecimal("DEBITS");
            accountsReceivableExt.setDebits(debits != null ? debits : new BigDecimal(0));

            if (invTotal != null && accountsReceivableExt.getInvoiceTotalAmount().compareTo(BigDecimal.ZERO) == 1) {
                BigDecimal balance = accountsReceivableExt.getInvoiceTotalAmount().subtract(accountsReceivableExt.getCredits()).add(accountsReceivableExt.getDebits());
                accountsReceivableExt.setBalance(MoneyUtil.formatCurrency(balance));
            } else {
                accountsReceivableExt.setBalance(MoneyUtil.formatCurrency(BigDecimal.ZERO));
            }

            accountsReceivableExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
            accountsReceivableExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
            accountsReceivableExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));
            accountsReceivableExt.setChangeVersionID(resultSet.getBigDecimal("CHANGE_VERSION_ID").toBigInteger());
            accountsReceivableExt.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
            accountsReceivableExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
            accountsReceivableExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));


            accountsReceivableExts.add(accountsReceivableExt);
        }

        response.setData(accountsReceivableExts);


        return response;
    }

    public AccountsReceivableTransactionBatchDetailExt getAccountsReceivableTransactionBySK(BigInteger accountsReceivableSK) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            String sql = " SELECT * " +
                    "FROM AR_TXN_BATCH_DET T1 " +
                    "LEFT JOIN " +
                    "  (SELECT  " +
                    "      SUM( " +
                    "        CASE " +
                    "          WHEN J1.AR_TXN_AMT IS NOT NULL AND UPPER(J2.AR_TXN_SIGN_QLFR) = 'DEBIT' AND UPPER(AR_TXN_CTGY_CODE) = 'PAYMENT' " +
                    "          THEN 0 - ABS(J1.AR_TXN_AMT) " +
                    "          WHEN J1.AR_TXN_AMT IS NOT NULL AND UPPER(J2.AR_TXN_SIGN_QLFR) = 'CREDIT' AND UPPER(AR_TXN_CTGY_CODE) = 'PAYMENT' " +
                    "          THEN J1.AR_TXN_AMT " +
                    "          ELSE 0 " +
                    "      END) AS TOTAL_TXN_AMT, " +
                    "      J1.BE_ID, " +
                    "      J1.AR_TXN_BATCH_DET_SK " +
                    "  FROM AR J1 " +
                    "      INNER JOIN " +
                    "        (SELECT AR_TXN_SIGN_QLFR , AR_TXN_CODE, AR_TXN_CTGY_CODE " +
                    "            FROM AR_TXN_LKUP " +
                    "            WHERE UPPER(AR_TXN_SIGN_QLFR)  IN ('DEBIT','CREDIT') " +
                    "            AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' " +
                    "            AND CURR_REC_IND  = 1 ) " +
                    "        ) J2 " +
                    "      ON J1.AR_TXN_CODE = J2.AR_TXN_CODE " +
                    "  GROUP BY J1.BE_ID, " +
                    "    J1.AR_TXN_BATCH_DET_SK " +
                    "  ) T3 ON T1.BE_ID           = T3.BE_ID " +
                    "AND T1.AR_TXN_BATCH_DET_SK   = T3.AR_TXN_BATCH_DET_SK " +
                    "WHERE T1.AR_TXN_BATCH_DET_SK = ? ";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, new BigDecimal(accountsReceivableSK));

            resultSet = preparedStatement.executeQuery();

            AccountsReceivableTransactionBatchDetailExt accountsReceivableTransactionBatchDetailExt = null;

            while (resultSet.next()) {

                accountsReceivableTransactionBatchDetailExt = new AccountsReceivableTransactionBatchDetailExt();

                accountsReceivableTransactionBatchDetailExt.setAccountsReceivableTransactionBatchDetailSK(resultSet.getBigDecimal("AR_TXN_BATCH_DET_SK").toBigInteger());

                accountsReceivableTransactionBatchDetailExt.setCheckReceivedDate(resultSet.getTimestamp("CHECK_RCVD_DATE"));
                accountsReceivableTransactionBatchDetailExt.setCheckDepositDate(resultSet.getTimestamp("CHECK_DEPOSIT_DATE"));
                accountsReceivableTransactionBatchDetailExt.setCheckDate(resultSet.getTimestamp("CHECK_DATE"));

                accountsReceivableTransactionBatchDetailExt.setRecordCreatedBy(resultSet.getString("REC_CREATED_BY"));
                accountsReceivableTransactionBatchDetailExt.setRecordUpdatedBy(resultSet.getString("REC_UPDATED_BY"));

                accountsReceivableTransactionBatchDetailExt.setRecordCreateTimestamp(resultSet.getTimestamp("REC_CREATE_TMSTP"));
                accountsReceivableTransactionBatchDetailExt.setRecordUpdateTimestamp(resultSet.getTimestamp("REC_UPDATE_TMSTP"));

                accountsReceivableTransactionBatchDetailExt.setBusinessEntityID(resultSet.getString("BE_ID"));
                accountsReceivableTransactionBatchDetailExt.setPayerID(resultSet.getString("PAYER_ID"));
                accountsReceivableTransactionBatchDetailExt.setAccountsReceivableTransactionBatchID(resultSet.getString("AR_TXN_BATCH_ID"));
                accountsReceivableTransactionBatchDetailExt.setInvoiceNumber(resultSet.getString("INV_NUM"));

                accountsReceivableTransactionBatchDetailExt.setPaymentAmount(resultSet.getBigDecimal("PMT_AMT"));
                accountsReceivableTransactionBatchDetailExt.setPaymentTypeNumber(resultSet.getString("PMT_TYP_NUM"));

                String pmtQualifier = resultSet.getString("PMT_TYP_QLFR");

                try {
                    accountsReceivableTransactionBatchDetailExt.setPaymentTypeQualifier(PaymentTypeQualifier.fromValue(pmtQualifier));
                } catch (Exception e) {

                    e.printStackTrace();
                }


                accountsReceivableTransactionBatchDetailExt.setAccountsReceivableNoteTypeCode(resultSet.getString("AR_NOTE_TYP_CODE"));
                accountsReceivableTransactionBatchDetailExt.setAccountsReceivableTransactionNote(resultSet.getString("AR_TXN_NOTE"));
                accountsReceivableTransactionBatchDetailExt.setAccountsReceivableTransactionBatchPostIndicator(resultSet.getBoolean("AR_TXN_BATCH_POST_IND"));

                accountsReceivableTransactionBatchDetailExt.setChangeReasonMemo(resultSet.getString("CHANGE_REASON_MEMO"));
                accountsReceivableTransactionBatchDetailExt.setChangeReasonCode(resultSet.getString("CHANGE_REASON_CODE"));
                accountsReceivableTransactionBatchDetailExt.setChangeVersionID(resultSet.getBigDecimal("CHANGE_VERSION_ID").toBigInteger());


                BigDecimal totalAmount = resultSet.getBigDecimal("PMT_AMT");
                BigDecimal paymentBalance = null;

                if (totalAmount.compareTo(BigDecimal.ZERO) == 1) {

                    paymentBalance = totalAmount.subtract(MoneyUtil.formatCurrency(resultSet.getBigDecimal("TOTAL_TXN_AMT")));
                } else if (totalAmount.compareTo(BigDecimal.ZERO) == -1) {

                    paymentBalance = BigDecimal.ZERO;
                }

                accountsReceivableTransactionBatchDetailExt.setPaymentBalance(MoneyUtil.formatCurrency(paymentBalance));
            }

            return accountsReceivableTransactionBatchDetailExt;


        } catch (Exception e) {

            safeRollback(connection);

            throw new SandataRuntimeException(format("%s: getAccountsReceivableTransactionOpenBatch: %s",
                    getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }


    public AccountsReceivableTransactionBatchDetail getAccountsReceivableTransactionOpenBatch(String userName, String userGuid, String payerId, String bsnEntId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            StringBuilder whereClause = new StringBuilder("");

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                whereClause.append(" AND T1.PAYER_ID = ? ");
            }


            String sql = String.format(" SELECT * " +

                    " FROM AR_TXN_BATCH_DET T1" +
                    "    " +
                    " INNER JOIN AR_TXN_BATCH T2 ON T1.BE_ID = T2.BE_ID " +
                    "  AND T1.AR_TXN_BATCH_ID = T2.AR_TXN_BATCH_ID  " +
                    "  AND TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1  AND " +
                    "   T2.USER_NAME = ? AND T2.USER_GUID = ? " +
                    "" +
                    " WHERE T1.BE_ID = ? AND T1.AR_TXN_BATCH_POST_IND = 0 %s ", whereClause);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, userName);
            preparedStatement.setString(index++, userGuid);

            preparedStatement.setString(index++, bsnEntId);

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                preparedStatement.setString(index++, payerId);
            }

            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableTransactionBatchDetail> result = (List<AccountsReceivableTransactionBatchDetail>) new DataMapper()
                    .map(resultSet, "com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail");

            connection.commit();

            if (!result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {

            safeRollback(connection);

            throw new SandataRuntimeException(format("%s: getAccountsReceivableTransactionOpenBatch: %s",
                    getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }

    private void safeClose(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when closing result set: {}", sqle.getMessage(), sqle);
            }
        }
    }

    private void safeClose(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when closing statement: {}", sqle.getMessage(), sqle);
            }
        }
    }

    private void safeClose(Connection connection) {
        this.connectionPoolDataService.close(connection);
    }

    private void safeRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when rollback connection: {}", sqle.getMessage(), sqle);
            }
        }
    }

    /**
     * Find a list of AccountsReceivableTransactionBatch by payerId, bsnEntId and batchNumber
     *
     * @param payerId
     * @param bsnEntId
     * @param batchNumber
     * @return
     * @throws SandataRuntimeException
     */
    public List<AccountsReceivableTransactionBatchDetail> findBatchByPayer(String payerId, String bsnEntId, String batchNumber)
            throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM COREDATA.AR_TXN_BATCH_DET WHERE BE_ID = ? AND AR_TXN_BATCH_ID = ? %s ORDER BY AR_TXN_BATCH_ID";

            sql = String.format(sql, StringUtil.IsNullOrEmpty(payerId) ? "" : " AND PAYER_ID = ? ");

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;

            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, batchNumber);

            if (!StringUtil.IsNullOrEmpty(payerId)) {
                preparedStatement.setString(index++, payerId);
            }

            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableTransactionBatchDetail> resultList = (List<AccountsReceivableTransactionBatchDetail>) new DataMapper()
                    .map(resultSet, "com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail");

            return resultList;

        } catch (Exception e) {

            safeRollback(connection);
            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }

    public List<AccountsReceivableTransactionBatchDetail> getOpenCheckInformation(String payerId, String bsnEntId, String batchNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();

            String sql = "SELECT * FROM AR_TXN_BATCH_DET ARB "
                    + "WHERE BE_ID = ? AND PAYER_ID = ? AND AR_TXN_BATCH_ID = ? AND PMT_TYP_NUM IS NOT NULL "
                    // check for open check here
                    + "    AND PMT_AMT > "
                    + "    ( "
                    + "        SELECT SUM(AR_TXN_AMT) "
                    + "        FROM AR "
                    + "        WHERE BE_ID = ? AND PAYER_ID = ? AND AR_TXN_BATCH_ID = ? "
                    + "    ) ";


            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, batchNumber);
            // for summing transaction ammouts
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, batchNumber);

            resultSet = preparedStatement.executeQuery();

            List<AccountsReceivableTransactionBatchDetail> resultList = (List<AccountsReceivableTransactionBatchDetail>) new DataMapper()
                    .map(resultSet, "com.sandata.lab.data.model.dl.model.AccountsReceivableTransactionBatchDetail");

            return resultList;

        } catch (Exception e) {

            safeRollback(connection);
            throw new SandataRuntimeException(format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {
            safeClose(resultSet);
            safeClose(preparedStatement);
            safeClose(connection);
        }
    }

    public Response getUnappliedUsers(String bsnEntId,
                                      int page,
                                      int pageSize,
                                      String sortOn,
                                      String direction) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            // Calculate row range.
            int toRow = page * pageSize;
            int fromRow = toRow - (pageSize - 1);


            String sortColumn = " T1.USER_NAME ";


            // Build SQL query.
            String sql = format(
                    "SELECT * FROM " +
                            "	(SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (SELECT * FROM ( " +
                            "   SELECT DISTINCT USER_NAME FROM AR T1" +
                            "   WHERE T1.BE_ID = ? AND T1.AR_TXN_AMT > 0 AND T1.AR_UNAPPLIED_BALANCE_IND = ?" +

                            " ORDER BY %s %s " +
                            ")) R1) " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    sortColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setBoolean(2, true);


            resultSet = preparedStatement.executeQuery();

            List<String> usernames = new ArrayList<>();
            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);


            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                usernames.add(resultSet.getString("USER_NAME"));
            }

            response.setData(usernames);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

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

            throw new SandataRuntimeException(format("%s: %s",
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

            this.connectionPoolDataService.close(connection);
        }
    }

    public Response checkExists(String bsnEntId,
                                String payerId,
                                String paymentNumber,
                                String paymentAmount
    ) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);


            // Build SQL query.
            String sql = "SELECT 1 AS CHECK_EXISTS FROM DUAL WHERE EXISTS " +
                    "  ( " +
                    "    SELECT  " +
                    "        BE_ID, PAYER_ID, PMT_TYP_NUM, PMT_AMT FROM AR_TXN_BATCH_DET " +
                    "    WHERE BE_ID = ? AND PAYER_ID = ? AND PMT_AMT = ? AND PMT_TYP_NUM = ? " +
                    "  )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bsnEntId);
            preparedStatement.setString(2, payerId);
            preparedStatement.setString(3, paymentAmount);
            preparedStatement.setString(4, paymentNumber);


            resultSet = preparedStatement.executeQuery();


            Response response = new Response();


            boolean exists = false;


            while (resultSet.next()) {

                exists = resultSet.getBoolean("CHECK_EXISTS");
            }

            response.setData(exists);


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

            throw new SandataRuntimeException(format("%s: %s",
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
}

 
