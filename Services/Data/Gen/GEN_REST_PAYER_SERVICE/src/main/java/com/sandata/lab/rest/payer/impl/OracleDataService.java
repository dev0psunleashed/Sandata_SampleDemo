package com.sandata.lab.rest.payer.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.payroll.PayrollRateMatrixExchange;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.lab.rest.payer.api.OracleService;
import com.sandata.lab.rest.payer.model.PayerContractHdr;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static java.lang.String.format;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

    private ConnectionPoolDataService connectionPoolDataService;

    public Connection getOracleConnection() throws SandataRuntimeException {
        return connectionPoolDataService.getConnection();
    }

    public Connection getOracleConnection(ConnectionType connectionType) throws SandataRuntimeException {
        return connectionPoolDataService.getConnection(connectionType);
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
    public Response getPayerByBsnEnt(
        final String bsnEntId,
        final int page,
        final int pageSize,
        final String payerTypeQualifier,
        boolean payerActiveIndicator,
        String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        if (StringUtil.IsNullOrEmpty(sortOn) || "name".equalsIgnoreCase(sortOn)) {
            sortOn = "PAYER_NAME";
        } else if (sortOn.toUpperCase().endsWith("_IND")) { // All indicator columns are Number and Nullable, convert to 0 if null
            sortOn = "NVL(" + sortOn + ", 0)";
        }

        if(StringUtil.IsNullOrEmpty(direction)){
            direction = "ASC";
        }

        StringBuilder filterItems = new StringBuilder();

        filterItems.append(String.format(" AND PAYER_ACTIVE_IND = %d ", payerActiveIndicator ? 1 : 0));

        if (!StringUtil.IsNullOrEmpty(payerTypeQualifier)){
            filterItems.append(String.format(" AND UPPER(PAYER_TYP_QLFR) = '%s' ", payerTypeQualifier.toUpperCase()));
        }

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                " " +
                "  SELECT * FROM ( " +
                " " +
                "      (SELECT DISTINCT PAYER_SK,PAYER_ID,PAYER_NAME, PAYER_ACTIVE_IND, PAYER_TYP_QLFR, PAYER_TERM_DATE FROM PAYER " +
                "          WHERE BE_ID = ? AND " +
                "            (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31') %s " +
                "          ORDER BY UPPER(%s) %s) " +
                "  ) " +
                " " +
                " ) R1) " +
                " " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d ", filterItems.toString(), sortOn, direction, fromRow, toRow);

            int index = 1;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<FindPayerSimpleResult> results = new ArrayList<>();
            Response response = new Response();
            response.setData(results);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                FindPayerSimpleResult result = new FindPayerSimpleResult();
                result.setPayerSK(BigInteger.valueOf(resultSet.getBigDecimal("PAYER_SK").longValue()));
                result.setPayerId(resultSet.getString("PAYER_ID"));
                result.setPayerName(resultSet.getString("PAYER_NAME"));
                result.setPayerActiveIndicator(resultSet.getBoolean("PAYER_ACTIVE_IND"));
                result.setPayerTypeQualifier(PayerTypeQualifier.fromValue(resultSet.getString("PAYER_TYP_QLFR")));
                result.setPayerTermDate(resultSet.getDate("PAYER_TERM_DATE")); //not null enforced by database.

                results.add(result);
            }

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

    public List<Payer> getPayerByPatient(final String bsnEntId, final String patientId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            List<Payer> payers = new ArrayList<>();
            Map<String, List<PatientPayer>> patientPayersMap = new HashMap<>();

            connection = connectionPoolDataService.getConnection();

            String sql = "SELECT * FROM PT_PAYER WHERE PT_ID = ? AND BE_ID = ?" +
                    " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patientId);
            preparedStatement.setString(2, bsnEntId);

            resultSet = preparedStatement.executeQuery();
            List<PatientPayer> patientPayerList = (List<PatientPayer>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.PatientPayer");

            resultSet.close();
            preparedStatement.close();
            preparedStatement = null;

            for (PatientPayer patientPayer : patientPayerList) {

                String payerId = patientPayer.getPayerID();
                List<PatientPayer> patientPayerListVal = patientPayersMap.get(payerId);
                if (patientPayerListVal == null) {
                    patientPayerListVal = new ArrayList<>();
                }

                patientPayerListVal.add(patientPayer);
                patientPayersMap.put(payerId, patientPayerListVal);
            }

            Iterator itr = patientPayersMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry pair = (Map.Entry) itr.next();

                String payerId = (String) pair.getKey();
                String payerSQL = "SELECT * "  +
                        " FROM PAYER WHERE PAYER_ID=? AND BE_ID = ? " +
                        " AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ";
                preparedStatement = connection.prepareStatement(payerSQL);
                preparedStatement.setString(1, payerId);
                preparedStatement.setString(2, bsnEntId);

                resultSet = preparedStatement.executeQuery();
                List<Payer> payerListResult = (List<Payer>) new DataMapper().map(resultSet,
                    "com.sandata.lab.data.model.dl.model.Payer");

                if (payerListResult != null && payerListResult.size() > 0) {

                    Payer payer = payerListResult.get(0);
                    payer.getPatientPayer().addAll((List) pair.getValue());
                    payers.add(payer);
                }

                resultSet.close();
                preparedStatement.close();
                preparedStatement = null;
            }

            return payers;

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

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, String entityId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call %s.%s.%s(?)}", connectionType, packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, entityId);
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, Object... params)
        throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, params);

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            if (packageName.contains("LOOKUP")) {
                callMethod = String.format("{?=call PKG_LOOKUP.%s()}", methodName);
            }
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            if (!packageName.contains("LOOKUP")) {
                callableStatement.setArray(2, primaryKeysArray);
            }

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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public Object executeGet(ConnectionType connectionType, String packageName, String methodName, String className, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(ConnectionType connectionType, String packageName, String methodName, long sequenceKey) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
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

            // Close the Connection
            this.connectionPoolDataService.close(connection);
        }
    }

    @Override
    public long execute(ConnectionType connectionType, String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {

            connection = connectionPoolDataService.getConnection(connectionType);
            connection.setAutoCommit(false);

            String callMethod = format("{?=call %s.%s(?)}", packageName, methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.BIGINT);
            callableStatement.setObject(2, jpubType);
            callableStatement.execute();
            long result = (long) callableStatement.getObject(1);

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

            // Close the Connection
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

    public Payer getPayerForID(String payerId, String bsnEntId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * " +
                    " FROM PAYER WHERE PAYER_ID = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Payer> resultList =
                (List<Payer>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Payer");

            connection.commit();

            if (resultList.size() > 0) {
                return resultList.get(0);
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

    public Response getPayerByBsnEntAndPayer(String className, String payerId, String bsnEntId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            int index = 1;
            String sql = "SELECT * FROM PAYER WHERE BE_ID=? AND PAYER_ID=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);

            resultSet = preparedStatement.executeQuery();

            List<Payer> results = new ArrayList<>();
            Response response = new Response();
            response.setData(results);


            Object result = new DataMapper().map(resultSet, className);
            for (Payer p : (List<Payer>) result) {

                p.getPayerLineOfBusinessList().addAll(getPayerLobByBsnEntList(connection, "com.sandata.lab.data.model.dl.model.PayerLineOfBusinessList", payerId, bsnEntId));
                p.getPayerBillingCodeLookup().addAll(getPayerBillingCodeLstByBsnEntPayerLookup(connection,"com.sandata.lab.data.model.dl.model.PayerBillingCodeLookup", payerId, bsnEntId));
                p.getPayerModifierLookup().addAll(getPayerMdfrLstByBsnEntPayerList(connection, "com.sandata.lab.data.model.dl.model.PayerModifierLookup", payerId, bsnEntId));
                p.getPayerServiceList().addAll(getPayerSvcLstBsnEntPayerList(connection, "com.sandata.lab.data.model.dl.model.PayerServiceList", payerId, bsnEntId));
                p.getPayerRateTypeList().addAll(getPayerRateTypeByBsnEntList(connection, "com.sandata.lab.data.model.dl.model.PayerRateTypeList", payerId, bsnEntId));
                p.getContract().addAll(getContractByPayerId(connection, p.getPayerID(),p.getBusinessEntityID()));
            }

            results.addAll((List<Payer>) result);

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

    public Response getPayerHdrsByBsnEnt(String bsnEntId, Boolean payerActiveIndicator,
                                         String sortOn, String direction, int page, int pageSize) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {
            
            if ("name".equalsIgnoreCase(sortOn)) {
                sortOn = "PAYER_NAME";
            } else if (sortOn.toUpperCase().endsWith("_IND")) { // All indicator columns are Number and Nullable, convert to 0 if null
                sortOn = "NVL(" + sortOn + ", 0)";
            }

            // do not use UPPER for columns with DATE type as UPPER converts date value to string (e.g. 02-JAN-17 14:25:46)
            // so it causes sorting DATE columns work incorrectly
            if (!sortOn.toUpperCase().endsWith("_DATE") && !sortOn.toUpperCase().endsWith("_TMSTP")) {
                sortOn = "UPPER(" + sortOn + ")";
            }

            String filterItem = "";
            if (payerActiveIndicator != null) {
                filterItem = String.format(" AND PAYER_ACTIVE_IND = %d ", payerActiveIndicator.booleanValue() ? 1 : 0);
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT * " +
                            " " +
                            "       FROM PAYER " +
                            " " +
                            "       WHERE BE_ID = ? " +
                            "           %s" +  // filterItem
                            "           AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            " " +
                            "  ORDER BY %s %s) " +
                            " " +
                            ") ) R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    filterItem,
                    sortOn,
                    direction,
                    fromRow,
                    toRow
            );

            int index = 1;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            if (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }

                List<Payer> payerList = (List<Payer>)new DataMapper().mapWithOffsetNext(
                                resultSet,
                                "com.sandata.lab.data.model.dl.model.Payer",
                                2
                );

                response.setData(payerList);
            }
            
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);
            response.setTotalRows(response.getTotalRows());

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
     * Get payers by businessEntityID, payerID, effectiveDate and terminationDate
     *
     * @param businessEntityID
     * @param payerID
     * @param effectiveDate
     * @param terminationDate
     * @return
     * @throws SandataRuntimeException
     */
    public List<Payer> getPayerByIDAndEffectiveRange(String businessEntityID, String payerID, Long excludedPayerSk,
                                                     Date effectiveDate, Date terminationDate) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String query =
                    "SELECT * " +
                    "FROM PAYER " +
                    "WHERE BE_ID = ? AND PAYER_ID = ? " +
                    "AND (     (PAYER_EFF_DATE BETWEEN ? AND ?) " +
                    "       OR (PAYER_TERM_DATE BETWEEN ? AND ?) " +
                    "       OR (? BETWEEN PAYER_EFF_DATE AND PAYER_TERM_DATE) " +
                    "       OR (? BETWEEN PAYER_EFF_DATE AND PAYER_TERM_DATE) " +
                    "    ) " +
                    "AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ";

            if (excludedPayerSk != null && excludedPayerSk > 0) {
                query += " AND PAYER_SK != ? ";
            }

            int index = 1;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(index++, businessEntityID);
            preparedStatement.setString(index++, payerID);
            preparedStatement.setDate(index++, new java.sql.Date(effectiveDate.getTime()));
            preparedStatement.setDate(index++, new java.sql.Date(terminationDate.getTime()));
            preparedStatement.setDate(index++, new java.sql.Date(effectiveDate.getTime()));
            preparedStatement.setDate(index++, new java.sql.Date(terminationDate.getTime()));
            preparedStatement.setDate(index++, new java.sql.Date(effectiveDate.getTime()));
            preparedStatement.setDate(index++, new java.sql.Date(terminationDate.getTime()));
            if (excludedPayerSk != null && excludedPayerSk > 0) {
                preparedStatement.setLong(index++, excludedPayerSk);
            }

            resultSet = preparedStatement.executeQuery();

            return (List<Payer>)new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Payer");

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

    public Response getPayerLobByBsnEnt(Connection connection, final String className, String payerId, String bsnEntId) {
        Response response = new Response();
        List<PayerLineOfBusinessList> results = getPayerLobByBsnEntList(connection, className, payerId, bsnEntId);
        response.setData(results);
        return response;
    }

    private List<PayerLineOfBusinessList> getPayerLobByBsnEntList(Connection connection, final String className, String payerId, String bsnEntId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            int index = 1;

            String sql = "SELECT * FROM PAYER_LOB_LST WHERE BE_ID=? AND PAYER_ID=? "
                + "AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);

            resultSet = preparedStatement.executeQuery();

            List<PayerLineOfBusinessList> results = new ArrayList<>();

            Object result = new DataMapper().map(resultSet, className);
            results.addAll((List<PayerLineOfBusinessList>) result);

            return results;

        } catch (Exception e) {

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
        }
    }

    public Response getPayerSvcLstBsnEntPayer(Connection connection, String className, String payerId, String bsnEntId) {
        Response response = new Response();
        List<PayerServiceList> results = getPayerSvcLstBsnEntPayerList(connection, className, payerId, bsnEntId);
        response.setData(results);
        return response;
    }

    private List<PayerServiceList> getPayerSvcLstBsnEntPayerList(Connection connection, String className, String payerId, String bsnEntId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int index = 1;
            String sql = "SELECT * FROM PAYER_SVC_LST WHERE BE_ID=? AND PAYER_ID=? "
                + "AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            resultSet = preparedStatement.executeQuery();
            List<PayerServiceList> results = new ArrayList<>();

            Object result = new DataMapper().map(resultSet, className);
            results.addAll((List<PayerServiceList>) result);
            return results;
        } catch (Exception e) {

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
        }
    }

    public Response getPayerRateTypLstBsnEntPayer(Connection connection, String className, String payerId, String bsnEntId) {
        Response response = new Response();
        List<PayerRateTypeList> results = getPayerRateTypeByBsnEntList(connection, className, payerId, bsnEntId);
        response.setData(results);
        return response;
    }

    private List<PayerRateTypeList> getPayerRateTypeByBsnEntList(Connection connection, String className, String payerId, String bsnEntId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int index = 1;
            String sql = "SELECT * FROM PAYER_RATE_TYP_LST WHERE BE_ID=? AND PAYER_ID=? "
                + "AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            resultSet = preparedStatement.executeQuery();
            List<PayerRateTypeList> results = new ArrayList<>();

            Object result = new DataMapper().map(resultSet, className);
            results.addAll((List<PayerRateTypeList>) result);
            return results;
        } catch (Exception e) {

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
        }

    }

    public Response getPayerBillingCodeLstByBsnEntPayer(Connection connection, String className, String payerId, String bsnEntId) {
        Response response = new Response();
        List<PayerBillingCodeLookup> results = getPayerBillingCodeLstByBsnEntPayerLookup(connection, className, payerId, bsnEntId);
        response.setData(results);
        return response;
    }

    private List<PayerBillingCodeLookup> getPayerBillingCodeLstByBsnEntPayerLookup(Connection connection, String className, String payerId, String bsnEntId) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int index = 1;
            String sql = "SELECT * FROM PAYER_BILLING_CODE_LKUP WHERE BE_ID=? AND PAYER_ID=? "

                // Check for not getting deleted records
                + "AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            resultSet = preparedStatement.executeQuery();
            List<PayerBillingCodeLookup> results = new ArrayList<>();

            Object result = new DataMapper().map(resultSet, className);
            results.addAll((List<PayerBillingCodeLookup>) result);

            return results;
        } catch (Exception e) {

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
        }
    }

    public Response getPayerMdfrLstByBsnEntPayer(Connection connection, String className, String payerId, String bsnEntId) {
        Response response = new Response();
        List<PayerModifierLookup> results = getPayerMdfrLstByBsnEntPayerList(connection, className, payerId, bsnEntId);
        response.setData(results);
        return response;

    }

    private List<PayerModifierLookup> getPayerMdfrLstByBsnEntPayerList(Connection connection, String className, String payerId, String bsnEntId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int index = 1;
            String sql = "SELECT * FROM PAYER_MDFR_LKUP WHERE BE_ID=? AND PAYER_ID=? "
                + "AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            resultSet = preparedStatement.executeQuery();
            List<PayerModifierLookup> results = new ArrayList<>();

            Object result = new DataMapper().map(resultSet, className);
            results.addAll((List<PayerModifierLookup>) result);
            return results;
        } catch (Exception e) {

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
        }
    }

    public Response getPayerContractHeadersByPayer(long sequenceKey, int page, int pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                " " +
                "  SELECT * FROM ( " +
                " " +
                "      (SELECT P1.PAYER_SK, P1.BE_ID, P1.PAYER_ID, P1.PAYER_NAME, P1.PAYER_ACTIVE_IND, C1.CONTR_SK AS PAYER_CONTR_SK, " +
                "       C1.CONTR_ID, C1.CONTR_DESC, P1.PAYER_EFF_DATE, " +
                "       C1.CONTR_ACTIVE_IND " +
                "       FROM PAYER P1  JOIN CONTR C1 ON P1.BE_ID = C1.BE_ID AND P1.PAYER_ID = C1.PAYER_ID " +
                "       WHERE P1.PAYER_SK = ? AND (TO_CHAR(P1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND P1.CURR_REC_IND = 1)" +
                "       AND (TO_CHAR(C1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND C1.CURR_REC_IND = 1) " +
                "       ORDER BY C1.CONTR_ID ASC) " +
                "  ) " +
                " " +
                " ) R1) " +
                " " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d ", fromRow, toRow);

            int index = 1;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(index, BigDecimal.valueOf(Long.valueOf(sequenceKey).longValue()));
            resultSet = preparedStatement.executeQuery();
            PayerContractHdr payerContractHdr = null;
            Response response = new Response();
            Boolean first = true;
            while (resultSet.next()) {
                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());
                }
                if (first) {
                    first = false;
                    payerContractHdr = new PayerContractHdr(resultSet);
                } else {
                    payerContractHdr.addContract(resultSet);
                }
            }
            response.setData(payerContractHdr);
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

    /****
     * @param packageName
     * @param methodName
     * @param className
     * @param params
     * @return List<PayerContr_(list name)_Lst
     * _getPrRateMatrixBsnEntPayerContr
     * _getBillingRateMatrixBsnEntPayerContr
     * _getPayerContrMdfrLstBsnEntPayerContr
     */

    public Response getPayerContractSubtable(String packageName, String methodName, String className, Object[] params) throws Exception {
        Object result = null;
        String tableName = null;
        String bsnEntId = (String) params[0];
        String payerId = (String) params[1];
        String contractId = (String) params[2];
        int page = (Integer) params[3];
        int pageSize = (Integer) params[4];
        int totalRows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);
        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
            tableName = getPayerContractSubTableName(methodName);
            String innerSql = String.format("SELECT DISTINCT * FROM %s WHERE BE_ID = ? AND PAYER_ID = ? AND CONTR_ID = ? AND " +
                " (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')", tableName);
            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                "  SELECT * FROM ( " +
                "      (%s) " +
                "  ) " +
                " ) R1) " +
                "WHERE ROW_NUMBER BETWEEN %d AND %d ", innerSql, fromRow, toRow);

            int index = 1;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, contractId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRows = resultSet.getBigDecimal("TOTAL_ROWS").intValue();
                result = new DataMapper().mapWithOffsetNext(resultSet, className, 2);
            }

            return getPayerContractSubTableResponse(methodName, result, page, totalRows);

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

    private String getPayerContractSubTableName(String methodName) throws SandataRuntimeException {
        String tableName = null;
        switch (methodName) {
            case "getContrLobLst":
                tableName = "CONTR_LOB_LST";
                break;
            case "getContrSvcLst":
                tableName = "CONTR_SVC_LST";
                break;
            case "getContrRateTypLst":
                tableName = "CONTR_RATE_TYP_LST";
                break;
            case "getContrMdfrLst":
                tableName = "CONTR_MDFR_LST";
                break;

            case "getPayerContrLobLst":
                tableName = "PAYER_CONTR_LOB_LST";
//                        "SELECT PAYER_CONTR_LOB_LST_SK,REC_CREATE_TMSTP,REC_UPDATE_TMSTP,REC_EFF_TMSTP,REC_TERM_TMSTP," +
//                        "REC_CREATED_BY,REC_UPDATED_BY,CHANGE_REASON_MEMO,CURR_REC_IND,CHANGE_VERSION_ID,BE_ID,PAYER_ID," +
//                        "CONTR_ID,BE_LOB FROM PAYER_CONTR_LOB_LST WHERE BE_ID = ? AND PAYER_ID = ? AND CONTR_ID = ? AND  " +
//                        "(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1'";
                break;

            case "getPayerContrSvcLst":
                tableName = "PAYER_CONTR_SVC_LST";
                break;
            case "getPayerContrRateTypLst":
                tableName = "PAYER_CONTR_RATE_TYP_LST";
                break;
            case "getPayerContrBillingCodeLst":
                tableName = "PAYER_CONTR_BILLING_CODE_LST";

                break;
            case "getPayerContrMdfrLst":
                tableName = "PAYER_CONTR_MDFR_LST";
                break;
            case "getBillingRateMatrix":
                tableName = "BILLING_RATE_MATRIX";

                break;
            case "getPrRateMatrix":
                tableName = "PR_RATE_MATRIX";

                break;
            default:
                throw new SandataRuntimeException(String.format("INVALID METHOD NAME.EXCEPTION in OracleDataService.getPayerContractSubtable:(%s)", methodName));
        }
        return tableName;
    }

    private Response getPayerContractSubTableResponse(String methodName, Object object, int page, int totalRows) throws SandataRuntimeException {
        Response response = new Response();
        switch (methodName) {
            case "getContrSvcLst":
                List<ContractServiceList> dataCtrSvc = (List<ContractServiceList>) object;
                response.setData(dataCtrSvc);
                break;
            case "getContrRateTypLst":
                List<ContractRateTypeList> dataCtrRateTyp = (List<ContractRateTypeList>) object;
                response.setData(dataCtrRateTyp);
                break;
            case "getContrMdfrLst":
                List<ContractModifierList> dataCtrMdfr = (List<ContractModifierList>) object;
                response.setData(dataCtrMdfr);
                break;
            case "getContrBillingCodeLst":
                List<ContractBillingCodeList> dataBillingCode = (List<ContractBillingCodeList>) object;
                response.setData(dataBillingCode);
                break;
            case "getBillingRateMatrix":
                List<BillingRateMatrix> dataBillingRateMatrix = (List<BillingRateMatrix>) object;
                response.setData(dataBillingRateMatrix);
                break;
            case "getPrRateMatrix":
                List<PayrollRateMatrix> dataPayrollRateMatrix = (List<PayrollRateMatrix>) object;
                response.setData(dataPayrollRateMatrix);
                break;
            default:
                throw new SandataRuntimeException(String.format("INVALID METHOD NAME.EXCEPTION in OracleDataService.getPayerContractSubtable:(%s)", methodName));
        }
        return response;
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
    
    public Object getEntitiesForId(Connection connection, final String sql, final String className, final Object... params) throws SandataRuntimeException {

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
        }
    }

    public String getPayerIdForSk(long payerSk) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT PAYER_ID FROM PAYER WHERE PAYER_SK = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, payerSk);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return resultSet.getString("PAYER_ID");
            }

            throw new SandataRuntimeException(String.format("Not Found! PAYER_SK=%d", payerSk));

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

    public List<PayrollRateMatrixExchange> getPrRateMatrixExchange(String bsnId, String payerId, String contractId,
                                                                   SandataLogger logger) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        logger.trace(String.format("%s: getPrRateMatrixExchange: START",
                getClass().getName())
        );

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM PR_RATE_MATRIX " +
                            "WHERE BE_ID = ? " +
                            "  AND PAYER_ID = ? " +
                            "  AND CONTR_ID = ? " +
                            "  AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)";

            logger.debug(String.format("%s: getPrRateMatrixExchange: [SQL=%s]",
                    getClass().getName(),
                    sql)
            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnId);
            preparedStatement.setString(index++, payerId);
            preparedStatement.setString(index++, contractId);

            resultSet = preparedStatement.executeQuery();

            List<PayrollRateMatrix> resultList =
                    (List<PayrollRateMatrix>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.PayrollRateMatrix");

            // There are no records that match the query
            if (resultList.size() == 0) {
                logger.info(String.format("getPrRateMatrixExchange: No data found! [SQL=%s]", sql));
                return null;
            }

            Map<String, PayrollRateMatrixExchange> payrollRateMatrixExchangeMap = new HashMap<>();
            for (PayrollRateMatrix payrollRateMatrix : resultList) {

                String key = String.format("%s-%s", payrollRateMatrix.getRateTypeName(), payrollRateMatrix.getServiceName());
                PayrollRateMatrixExchange payrollRateMatrixExchange = payrollRateMatrixExchangeMap.get(key);
                if (payrollRateMatrixExchange == null) {
                    payrollRateMatrixExchange = new PayrollRateMatrixExchange();
                    payrollRateMatrixExchangeMap.put(key, payrollRateMatrixExchange);
                }

                switch(payrollRateMatrix.getRateSubTypeName()) {
                    case REGULAR:
                        payrollRateMatrixExchange.setRegularPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setRegularChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setRegularRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setRegularServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                    case WEEKEND:
                        payrollRateMatrixExchange.setWeekendPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setWeekendChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setWeekendRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setWeekendServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                    case HOLIDAY:
                        payrollRateMatrixExchange.setHolidayPayrollRateMatrixSK(payrollRateMatrix.getPayrollRateMatrixSK());
                        payrollRateMatrixExchange.setHolidayChangeVersionID(payrollRateMatrix.getChangeVersionID());
                        payrollRateMatrixExchange.setHolidayRateAmount(payrollRateMatrix.getRateAmount());
                        payrollRateMatrixExchange.setHolidayServiceUnitName(payrollRateMatrix.getServiceUnitName());
                        payrollRateMatrixExchange.setPayrollCode(payrollRateMatrix.getPayrollCode());
                        break;
                }

                // Init is run once for common properties
                if (StringUtil.IsNullOrEmpty(payrollRateMatrixExchange.getBusinessEntityID())) {
                    payrollRateMatrixExchange.setBusinessEntityID(payrollRateMatrix.getBusinessEntityID());
                    payrollRateMatrixExchange.setBusinessEntityLocationID(payrollRateMatrix.getBusinessEntityLocationID());
                    payrollRateMatrixExchange.setPayrollRateMatrixNote(payrollRateMatrix.getPayrollRateMatrixNote());
                    payrollRateMatrixExchange.setPayerID(payrollRateMatrix.getPayerID());
                    payrollRateMatrixExchange.setContractID(payrollRateMatrix.getContractID());
                    payrollRateMatrixExchange.setEffectiveDate(payrollRateMatrix.getPayrollRateMatrixEffectiveDate());
                    payrollRateMatrixExchange.setServiceName(payrollRateMatrix.getServiceName());
                    payrollRateMatrixExchange.setRateTypeName(payrollRateMatrix.getRateTypeName());
                }
            }

            return new ArrayList<>(payrollRateMatrixExchangeMap.values());

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

            logger.trace(String.format("%s: getPrRateMatrixExchange: END",
                    getClass().getName())
            );
        }
    }

    /**
     * 
     * @param payerId Payer Id
     * @return
     * @throws SandataRuntimeException
     */
    public List<Contract>  getContractByPayerId(Connection connection, String payerId,String bsnEntId) throws SandataRuntimeException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM CONTR WHERE PAYER_ID = ? AND BE_ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, payerId);
            preparedStatement.setString(2, bsnEntId);

            resultSet = preparedStatement.executeQuery();
           
            List<Contract> resultList =
                    (List<Contract>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Contract");
            
            return resultList;

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

    /**
     * Returns unique ApplicationTenantKeyConfiguration for specified application
     * tenant SK and key name.
     *
     * @param appTenantSk Specified application tenant SK.
     * @param keyName     Specified key name.
     * @return ApplicationTenantKeyConfiguration entity for payers parent.
     */
    public ApplicationTenantKeyConfiguration getAppTenantKeyConfForTenantSkAndKeyName(long appTenantSk,
                                                                                      String keyName) {

        ApplicationTenantKeyConfiguration appTenantKeyConf = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ? AND KEY_NAME=?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, appTenantSk);
            preparedStatement.setString(index, keyName);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> appTenantKeyConfList = (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");

            if (!appTenantKeyConfList.isEmpty()) {

                if (appTenantKeyConfList.size() != 1) {
                    throw new SandataRuntimeException(String.format("There can be only one ApplicationTenantKeyConfiguration for tenant SK %s and key name %s!", appTenantSk, keyName));
                }

                appTenantKeyConf = appTenantKeyConfList.get(0);
            }

            return appTenantKeyConf;

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

    /**
     * Returns unique ApplicationTenantKeyConfiguration for specified parent and
     * key name.
     *
     * @param appTenantSk              Specified application tenant SK.
     * @param parentAppTenantKeyConfSk Specified parent ApplicationTenantKeyConfiguration SK.
     * @param keyName                  Specified payer ID.
     * @return ApplicationTenantKeyConfiguration for payer.
     */
    public ApplicationTenantKeyConfiguration getAppTenantKeyConfForTenantParentAndKeyName(long appTenantSk,
                                                                                          long parentAppTenantKeyConfSk,
                                                                                          String keyName) {

        ApplicationTenantKeyConfiguration payerAppTenantKeyConf = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM APP_TENANT_KEY_CONF WHERE APP_TENANT_SK = ? AND APP_TENANT_KEY_CONF_PAR_SK = ? AND KEY_NAME = ?";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setLong(index++, appTenantSk);
            preparedStatement.setLong(index++, parentAppTenantKeyConfSk);
            preparedStatement.setString(index, keyName);

            resultSet = preparedStatement.executeQuery();

            List<ApplicationTenantKeyConfiguration> payerAppTenantKeyConfList = (List<ApplicationTenantKeyConfiguration>) new DataMapper().map(resultSet,
                "com.sandata.lab.data.model.dl.model.ApplicationTenantKeyConfiguration");

            if (!payerAppTenantKeyConfList.isEmpty()) {

                if (payerAppTenantKeyConfList.size() != 1) {
                    throw new SandataRuntimeException(String.format("There can be only one ApplicationTenantKeyConfiguration for key name %s, parent SK %s, and tenant SK %s!",
                        keyName,
                        parentAppTenantKeyConfSk,
                        appTenantSk));
                }

                payerAppTenantKeyConf = payerAppTenantKeyConfList.get(0);
            }

            return payerAppTenantKeyConf;


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
}

