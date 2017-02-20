package com.sandata.one.aggregator.lookup.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.admin.AdminStaffRelTypeResponse;
import com.sandata.lab.data.model.dl.model.extended.exception.BusinessEntityExceptionListExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.dl.db.pool.utils.constants.ConnectionType;
import com.sandata.one.aggregator.lookup.api.OracleService;
import com.sandata.one.aggregator.lookup.model.AgencyLookup;
import com.sandata.one.aggregator.lookup.model.PatientLookup;
import com.sandata.one.aggregator.lookup.model.StaffLookup;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDataService.class);

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

    @Override
    public Object getLookupWithBsnEntId(String methodName, String className, String BsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call PKG_LOOKUP.%s(?)}", methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, BsnEntId);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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
                    methodName, className,
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
    public Object getLookup(String methodName, String className) throws SandataRuntimeException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String callMethod = String.format("{?=call PKG_LOOKUP.%s()}", methodName);
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

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
                    methodName, className,
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

    public Response getPayerLookup(int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {
    
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String orderByColumn = "PAYER_NAME"; // Default
    
            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT * FROM ( " +
                    " " +
                    "      (SELECT * FROM PAYER " +
                    "        WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "           AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) " +
                    "                BETWEEN PAYER_EFF_DATE AND PAYER_TERM_DATE " +
                    "        ORDER BY %s %s " +
                    "    ) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            //preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Payer> resultList =
                        (List<Payer>) new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.Payer", 2);

                response.setData(resultList);
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

    public Response getContractLookup(int page, int pageSize, String sortOn, String direction, String... payerIdList) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String payerIdListParams = "?";

            if (payerIdList.length > 1) {
                for(int i = 1; i < payerIdList.length; i++) {
                    payerIdListParams += ",?";
                }
            }

            String orderByColumn = "CONTR_CONT_NAME"; // Default

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM CONTR " +
                            "        WHERE PAYER_ID IN (%s) " +
                            "          AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "          AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) " +
                            "                BETWEEN CONTR_EFF_DATE AND CONTR_TERM_DATE " +
                            "        ORDER BY %s %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    payerIdListParams,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (String payerId : payerIdList) {
                preparedStatement.setString(index++, payerId);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Contract> resultList =
                        (List<Contract>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.Contract", 2);

                response.setData(resultList);
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

    public Response getBeExcpLst(int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection metadataConnection = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String orderByColumn = "BE_ID"; // Default
            switch (sortOn) {
                case "ExceptionID":
                    orderByColumn = "EXCP_ID";
                    break;

                case "ApplicationModuleName":
                    orderByColumn = "APP_MOD_NAME";
                    break;
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT ExcpLkup.EXCP_NAME, ExcpLst.* " +
                            "        FROM BE_EXCP_LST ExcpLst " +
                            " " +
                            "        INNER JOIN (SELECT EXCP_ID,EXCP_NAME " +
                            "          FROM EXCP_LKUP " +
                            "            WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) ExcpLkup " +
                            "        ON ExcpLst.EXCP_ID = ExcpLkup.EXCP_ID " +
                            " " +
                            "        WHERE (TO_CHAR(ExcpLst.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND ExcpLst.CURR_REC_IND = 1) " +
                            "        ORDER BY %s %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            List<BusinessEntityExceptionListExt> resultList = new ArrayList<>();

            metadataConnection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            metadataConnection.setAutoCommit(true);

            while (resultSet.next()) {

                if (response.getTotalRows() == 0) {
                    response.setTotalRows(resultSet.getInt("TOTAL_ROWS"));
                }

                resultList.add(mapExceptionsList(metadataConnection, resultSet));
            }

            response.setData(resultList);

            return response;

        } catch (Exception e) {

            // Rollback
            safeRollback(connection);
            safeRollback(metadataConnection);

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
            this.connectionPoolDataService.close(metadataConnection);
        }
    }

    private BusinessEntityExceptionListExt mapExceptionsList(Connection metadataConnection, ResultSet resultSet) throws SQLException {

        BusinessEntityExceptionList businessEntityExceptionList = (BusinessEntityExceptionList)new DataMapper().mapWithOffsetEntityNext(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntityExceptionList", 3);

        BusinessEntityExceptionListExt beExcpLstExt = new BusinessEntityExceptionListExt(businessEntityExceptionList);
        beExcpLstExt.setExceptionName(resultSet.getString("EXCP_NAME"));
        beExcpLstExt.setAdditionalSettings(getBeExcpLstSetting(
                metadataConnection,
                businessEntityExceptionList.getExceptionID().toString(),
                businessEntityExceptionList.getBusinessEntityID()));
        return beExcpLstExt;
    }

    /**
     * @param connection
     * @param beExcpId
     * @param bsnEntId
     * @return Additional settings for beExcpId
     * @throws SandataRuntimeException
     */
    private String getBeExcpLstSetting(Connection connection, String beExcpId, String bsnEntId) throws SandataRuntimeException {

        CallableStatement callableStatement = null;

        try {
            String callMethod = "{?=call PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING(?,?)}";
            callableStatement = connection.prepareCall(callMethod);
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);

            int index = 2;
            callableStatement.setString(index++, beExcpId);
            callableStatement.setString(index++, bsnEntId);

            callableStatement.execute();
            return callableStatement.getString(1);

        } catch (Exception e) {

            // caller will take care of Rollback connection if needed

            throw new SandataRuntimeException(String.format("[PKG_APP_UTIL.GET_BE_EXCP_LIST_SETTING]: beExcpId=%s, bsnEntId=%s)",
                    beExcpId, bsnEntId,
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the Statement
            safeClose(callableStatement);

            // caller will take care of closing connection;
        }
    }

    public Response getCoordintorLookup(String coordinatorName, String coordinatorId, String bsnEntId, int page, int pageSize, String sortOn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            List<String> params = new ArrayList<String>();

            StringBuilder coordinatorFilter = new StringBuilder();
            if (!StringUtil.IsNullOrEmpty(coordinatorId)) {
                coordinatorFilter.append(" AND T1.ADMIN_STAFF_ID = ? ");
                params.add(coordinatorId);
            }

            if (!StringUtil.IsNullOrEmpty(bsnEntId)) {
                coordinatorFilter.append(" AND T1.BE_ID = ? ");
                params.add(bsnEntId);
            }

            if (!StringUtil.IsNullOrEmpty(coordinatorName)) {

                String[] keywords = coordinatorName.split(" ");
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
                    coordinatorFilter.append(" AND ((UPPER(T1.ADMIN_STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.ADMIN_STAFF_LAST_NAME) LIKE ?) OR " +
                            "(UPPER(T1.ADMIN_STAFF_FIRST_NAME) LIKE ? AND UPPER(T1.ADMIN_STAFF_LAST_NAME) LIKE ?))");
                } else {

                    params.add(keywords[0].toUpperCase() + "%");
                    params.add(keywords[0].toUpperCase() + "%");

                    coordinatorFilter.append(" AND (UPPER(T1.ADMIN_STAFF_FIRST_NAME) LIKE ? " +
                            "OR UPPER(T1.ADMIN_STAFF_LAST_NAME) LIKE ?) ");
                }

            }

            String orderByColumn = "T1.ADMIN_STAFF_LAST_NAME"; // Default
            switch (sortOn) {

                case "FirstName":
                    orderByColumn = "T1.ADMIN_STAFF_FIRST_NAME";
                    break;

                case "CoordinatorId":
                    orderByColumn = "T1.ADMIN_STAFF_ID";
                    break;

                case "BusinessEntityID":
                    orderByColumn = "T1.BE_ID";
                    break;

                default:
                    orderByColumn = "T1.ADMIN_STAFF_LAST_NAME";
                    break;
            }

            String coordinatorJoin =
                    "INNER JOIN (SELECT BE_ID, ADMIN_STAFF_ID,ADMIN_STAFF_ROLE_NAME,REC_TERM_TMSTP,CURR_REC_IND, " +
                    "              ADMIN_STAFF_ROLE_EFF_DATE,ADMIN_STAFF_ROLE_TERM_DATE,ADMIN_STAFF_ROLE_XREF_SK " +
                    "  FROM ADMIN_STAFF_ROLE_XREF " +
                    "    WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1 " +
                    "      AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) " +
                    "      BETWEEN ADMIN_STAFF_ROLE_EFF_DATE AND ADMIN_STAFF_ROLE_TERM_DATE) T2 " +
                    "ON T1.ADMIN_STAFF_ID = T2.ADMIN_STAFF_ID AND T1.BE_ID = T2.BE_ID ";

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT DISTINCT T1.ADMIN_STAFF_SK,T1.ADMIN_STAFF_ID,T1.ADMIN_STAFF_FIRST_NAME,T1.ADMIN_STAFF_LAST_NAME, " +
                            "          T2.ADMIN_STAFF_ROLE_XREF_SK,T2.ADMIN_STAFF_ROLE_NAME " +
                            "  FROM ADMIN_STAFF T1 " +
                            "          %s " +
                            "        WHERE " +
                            "          (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                            "          %s " +
                            "        ORDER BY %s %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    coordinatorJoin,
                    coordinatorFilter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for(String param : params) {
                preparedStatement.setString(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<AdminStaffRelTypeResponse> adminStaffRelTypeResponseList = new ArrayList<>();
            Response response = new Response();
            response.setData(adminStaffRelTypeResponseList);
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            while (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                AdminStaffRelTypeResponse adminStaffRelTypeResponse = new AdminStaffRelTypeResponse();
                adminStaffRelTypeResponse.setAdministrativeStaffSK(resultSet.getBigDecimal("ADMIN_STAFF_SK").toBigInteger());
                adminStaffRelTypeResponse.setAdministrativeStaffRoleCrossReferenceSK(resultSet.getBigDecimal("ADMIN_STAFF_ROLE_XREF_SK").toBigInteger());
                adminStaffRelTypeResponse.setAdministrativeStaffID(resultSet.getString("ADMIN_STAFF_ID"));
                adminStaffRelTypeResponse.setAdministrativeStaffFirstName(resultSet.getString("ADMIN_STAFF_FIRST_NAME"));
                adminStaffRelTypeResponse.setAdministrativeStaffLastName(resultSet.getString("ADMIN_STAFF_LAST_NAME"));
                adminStaffRelTypeResponse.setAdministrativeStaffRoleName(resultSet.getString("ADMIN_STAFF_ROLE_NAME"));
                adminStaffRelTypeResponseList.add(adminStaffRelTypeResponse);

            }

            return response;

        } catch (Exception e) {

            // Rollback
            safeRollback(connection);

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getPatientLookup(String lastName, String firstName, String patientId, String medicaidId, int page, int pageSize, String sortOn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            List<String> params = new ArrayList<String>();
            
            StringBuilder patientFilter = new StringBuilder();
            if (!StringUtil.IsNullOrEmpty(lastName)) {
                patientFilter.append(" AND UPPER(PT_LAST_NAME) LIKE ? ");
                params.add(lastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(firstName)) {
                patientFilter.append(" AND UPPER(PT_FIRST_NAME) LIKE ? ");
                params.add(firstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(patientId)) {
                patientFilter.append(" AND PT_ID = ? ");
                params.add(patientId);
            }

            if (!StringUtil.IsNullOrEmpty(medicaidId)) {
                patientFilter.append(" AND UPPER(PT_MEDICAID_ID) = ? ");
                params.add(medicaidId.toUpperCase());
            }

            String orderByColumn = "PT_LAST_NAME"; // Default
            switch (sortOn) {

                case "PatientId":
                    orderByColumn = "PT_ID";
                    break;
    
                case "LastName":
                    orderByColumn = "PT_LAST_NAME";
                    break;

                case "FirstName":
                    orderByColumn = "PT_FIRST_NAME";
                    break;

                case "MiddleName":
                    orderByColumn = "PT_MIDDLE_NAME";
                    break;

                case "MedicaidId":
                    orderByColumn = "PT_MEDICAID_ID";
                    break;

                default:
                    orderByColumn = "PT_LAST_NAME";
                    break;
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM PT " +
                            "        WHERE " +
                            "          (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "          %s " +
                            "        ORDER BY %s %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    patientFilter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for(String param : params) {
                preparedStatement.setString(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Patient> patients =
                        (List<Patient>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.Patient", 2);

                List<PatientLookup> patientLookups = new ArrayList<PatientLookup>();

                for (Patient patient : patients) {
                    PatientLookup patientLookup = new PatientLookup();
                    patientLookup.setPatientId(patient.getPatientID());
                    patientLookup.setFirstName(patient.getPatientFirstName());
                    patientLookup.setLastName(patient.getPatientLastName());
                    patientLookup.setMiddleName(patient.getPatientMiddleName());
                    patientLookup.setMedicaidId(patient.getPatientMedicaidID());

                    patientLookups.add(patientLookup);
                }

                response.setData(patientLookups);
            }

            return response;

        } catch (Exception e) {

            // Rollback
            safeRollback(connection);

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getStaffLookup(String lastName, String firstName, String staffId, String staffSSN, int page, int pageSize, String sortOn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            List<String> params = new ArrayList<String>();
            
            StringBuilder patientFilter = new StringBuilder();
            if (!StringUtil.IsNullOrEmpty(lastName)) {
                patientFilter.append(" AND UPPER(STAFF_LAST_NAME) LIKE ? ");
                params.add(lastName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(firstName)) {
                patientFilter.append(" AND UPPER(STAFF_FIRST_NAME) LIKE ? ");
                params.add(firstName.toUpperCase() + "%");
            }

            if (!StringUtil.IsNullOrEmpty(staffId)) {
                patientFilter.append(" AND UPPER(STAFF_ID) = ? ");
                params.add(staffId.toUpperCase());
            }

            if (!StringUtil.IsNullOrEmpty(staffSSN)) {
                patientFilter.append(" AND UPPER(STAFF_TIN) = ? AND UPPER(STAFF_TIN_QLFR) = 'SSN'");
                params.add(staffSSN.toUpperCase());
            }

            String orderByColumn = "STAFF_LAST_NAME"; // Default
            switch (sortOn) {

                case "StaffId":
                    orderByColumn = "STAFF_ID";
                    break;
    
                case "LastName":
                    orderByColumn = "STAFF_LAST_NAME";
                    break;

                case "FirstName":
                    orderByColumn = "STAFF_FIRST_NAME";
                    break;

                case "MiddleName":
                    orderByColumn = "STAFF_MIDDLE_NAME";
                    break;

                case "StaffSSN":
                    orderByColumn = "STAFF_TIN";
                    break;

                default:
                    orderByColumn = "STAFF_LAST_NAME";
                    break;
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM STAFF " +
                            "        WHERE " +
                            "          (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "          %s " +
                            "        ORDER BY %s %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    patientFilter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for(String param : params) {
                preparedStatement.setString(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Staff> staffs =
                        (List<Staff>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.Staff", 2);

                List<StaffLookup> staffLookups = new ArrayList<StaffLookup>();

                for (Staff staff : staffs) {
                    StaffLookup staffLookup = new StaffLookup();
                    staffLookup.setStaffId(staff.getStaffID());
                    staffLookup.setFirstName(staff.getStaffFirstName());
                    staffLookup.setLastName(staff.getStaffLastName());
                    staffLookup.setMiddleName(staff.getStaffMiddleName());
                    staffLookup.setStaffSSN(staff.getStaffTaxpayerIdentificationNumber());

                    staffLookups.add(staffLookup);
                }

                response.setData(staffLookups);
            }

            return response;

        } catch (Exception e) {

            // Rollback
            safeRollback(connection);

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getAgencyLookup(int page, int pageSize, String sortOn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.COREDATA);
            connection.setAutoCommit(true);

            String orderByColumn = "BE_NAME"; // Default
            switch (sortOn) {

                case "BusinessEntityID":
                    orderByColumn = "BE_ID";
                    break;
    
                case "BusinessEntityName":
                    orderByColumn = "BE_NAME";
                    break;

                case "BusinessEntityLegalName":
                    orderByColumn = "BE_LEGAL_NAME";
                    break;

                default:
                    orderByColumn = "BE_NAME";
                    break;
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * FROM BE " +
                            "        WHERE " +
                            "          (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "          AND BE_TYP IS NULL " +
                            "        ORDER BY UPPER(%s) %s " +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<BusinessEntity> businessEntities =
                        (List<BusinessEntity>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.BusinessEntity", 2);

                List<AgencyLookup> agencyLookups = new ArrayList<AgencyLookup>();

                for (BusinessEntity be : businessEntities) {
                    AgencyLookup agencyLookup = new AgencyLookup();
                    agencyLookup.setBusinessEntityID(be.getBusinessEntityID());
                    agencyLookup.setBusinessEntityName(be.getBusinessEntityName());
                    agencyLookup.setBusinessEntityLegalName(be.getBusinessEntityLegalName());

                    agencyLookups.add(agencyLookup);
                }

                response.setData(agencyLookups);
            }

            return response;

        } catch (Exception e) {

            // Rollback
            safeRollback(connection);

            throw new SandataRuntimeException(String.format("%s: %s",
                    e.getClass().getName(), e.getMessage()), e);

        } finally {

            // Close the ResultSet
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
        }
    }

    public Response getAppRoleLookup(int page, int pageSize, String sortOn, String direction) throws SandataRuntimeException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Calculate row range.
        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String orderByColumn = "ROLE_NAME"; // Default

            String sql = String.format(
                    "SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "   SELECT * FROM APP_ROLE " +
                    "   ORDER BY %s %s " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setPage(page);
            response.setPageSize(pageSize);
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<Payer> resultList =
                        (List<Payer>) new DataMapper().mapWithOffset(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationRole", 2);

                response.setData(resultList);
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
            safeClose(resultSet);

            // Close the statement
            safeClose(preparedStatement);

            // Close the connection
            this.connectionPoolDataService.close(connection);
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

    private void safeRollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException sqle) {
                LOGGER.warn("Error happened when rollback connection: {}", sqle.getMessage(), sqle);
            }
        }
    }

    public List<String> getContractService(Optional<String> payerId, Optional<String> contractId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            final StringBuilder filter = new StringBuilder("WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) ");
            final List params = new ArrayList();

            if (payerId.isPresent()) {
                filter.append("AND PAYER_ID = ? ");
                params.add(payerId.get());

                contractId.ifPresent(s -> {
                    filter.append("AND CONTR_ID = ? ");
                    params.add(contractId.get());
                });

            } else if (contractId.isPresent()) {
                filter.append("AND CONTR_ID = ? ");
                params.add(contractId.get());
            }

            String sql = "SELECT DISTINCT SVC_NAME FROM CONTR_SVC_LST " +
                            filter.toString() +
                            " ORDER BY SVC_NAME ASC";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            resultSet = preparedStatement.executeQuery();

            List<String> resultList = new ArrayList<>();
            while (resultSet.next()) {
                resultList.add(resultSet.getString("SVC_NAME"));
            }

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }


    

    /**
     * Get all the existing permission
     */
    public Response getAllPermission() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT * FROM APP_SECR_GRP ORDER BY SECR_GRP_NAME";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            List<ApplicationSecureGroup> resultList =
                    (List<ApplicationSecureGroup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSecureGroup");

            response.setData(resultList);

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

    /**
     *
     * @param appRoleSk Application Role Sequence key
     * @return
     */
    public Response getPermissionForRole(long appRoleSk) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection(ConnectionType.METADATA);
            connection.setAutoCommit(true);

            String sql = "SELECT T1.* "
                    + "FROM APP_SECR_GRP T1 "
                    + "INNER JOIN APP_SECR_GRP_ROLE_MAP T2 "
                    + "ON T1.APP_SECR_GRP_SK = T2.APP_SECR_GRP_SK "
                    + "INNER JOIN APP_ROLE T3 "
                    + "ON T2.APP_ROLE_SK    = T3.APP_ROLE_SK "
                    + "WHERE T3.APP_ROLE_SK = %d "
                    + "ORDER BY SECR_GRP_NAME";

            sql = String.format(sql,appRoleSk);

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Response response = new Response();

            List<ApplicationSecureGroup> resultList =
                    (List<ApplicationSecureGroup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.ApplicationSecureGroup");
            response.setData(resultList);

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
}
