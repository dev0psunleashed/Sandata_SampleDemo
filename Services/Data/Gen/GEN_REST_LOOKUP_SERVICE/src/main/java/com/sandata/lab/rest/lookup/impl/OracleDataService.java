package com.sandata.lab.rest.lookup.impl;

import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.extended.VisitTaskListExt;
import com.sandata.lab.data.model.response.Response;
import com.sandata.lab.dl.db.pool.api.ConnectionPoolDataService;
import com.sandata.lab.rest.lookup.api.OracleService;
import com.sandata.lab.rest.lookup.model.BusinessEntityBillerLookup;
import com.sandata.lab.rest.lookup.model.BusinessEntityFedTaxLookup;
import com.sandata.lab.rest.lookup.model.StaffPositionLookup;
import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class OracleDataService implements OracleService {

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
    
    public List<VisitTaskListExt> getVisitTaskLst(Long visitSk) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
    
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
    
            String sql = "SELECT * FROM VISIT_TASK_LST WHERE VISIT_SK = ?";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setLong(index, visitSk);
    
            resultSet = preparedStatement.executeQuery();
    
            List<VisitTaskList> resultList =
                    (List<VisitTaskList>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.VisitTaskList");

            List<VisitTaskListExt> visitTasks = new ArrayList<>();
            for (VisitTaskList visitTask : resultList) {

                VisitTaskListExt visitTaskListExt = new VisitTaskListExt(visitTask);
                visitTasks.add(visitTaskListExt);
            }

            return visitTasks;
    
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

    public List<StateLookup> getStateLookup(String orderBy) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = String.format("SELECT * FROM STATE_LKUP ORDER BY %s ASC", orderBy);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<StateLookup> resultList =
                    (List<StateLookup>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.StateLookup");

            connection.commit();

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
    
    public List<Task> getTaskLookup(String bsnEntId) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
    
        try {
    
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);
    
            String sql = "SELECT * FROM TASK WHERE BE_ID = ? ORDER BY TASK_ID ASC";
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
    
            resultSet = preparedStatement.executeQuery();
    
            List<Task> resultList =
                    (List<Task>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Task");
    
            connection.commit();
    
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

    private List<Service> getServicesForBsn(String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM SVC WHERE BE_ID = ? ORDER BY SVC_NAME";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Service> resultList =
                    (List<Service>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Service");

            connection.commit();

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

    private List<Service> getServiceForPayer(String patientId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if (patientId == null || patientId.length() == 0) {
                return getServicesForBsn(bsnEntId);
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT DISTINCT T1.* " +
                    "    FROM SVC T1 " +
                    " " +
                    "    INNER JOIN (SELECT SVC_SK,BE_ID,PT_ID " +
                    "      FROM PT_PAYER) T2 " +
                    "    ON T1.BE_ID = T2.BE_ID AND T1.SVC_SK = T2.SVC_SK " +
                    " " +
                    "    WHERE T2.PT_ID = ? AND T2.BE_ID = ?" +
                    " " +
                    "    ORDER BY T1.SVC_NAME";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Service> resultList =
                    (List<Service>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Service");

            //dmr--02/15/2016: GEOR-1192
            // No Results...
            if (resultList.size() == 0) {
                connection.commit();
                return getServicesForBsn(bsnEntId);
            }

            connection.commit();

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

    public List<Service> getServiceLookup(String patientId, String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if (patientId == null || patientId.length() == 0) {
                return getServicesForBsn(bsnEntId);
            }

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT DISTINCT T1.* " +
                        "    FROM SVC T1 " +
                        " " +
                        "    INNER JOIN (SELECT AUTH_SK,BE_ID,PT_ID,REC_TERM_TMSTP,CURR_REC_IND " +
                        "      FROM AUTH WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1)) T2 " +
                        "    ON T1.BE_ID = T2.BE_ID " +
                        " " +
                        "    INNER JOIN (SELECT AUTH_SK,SVC_SK " +
                        "      FROM AUTH_SVC) T3 " +
                        "    ON T2.AUTH_SK = T3.AUTH_SK " +
                        " " +
                        "    WHERE T2.PT_ID = ? AND T2.BE_ID = ?" +
                        "    ORDER BY T1.SVC_NAME";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, patientId);
            preparedStatement.setString(index, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Service> resultList =
                    (List<Service>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Service");

            //dmr--02/15/2016: GEOR-1192
            // No Results...
            if (resultList.size() == 0) {
                connection.commit();
                return getServiceForPayer(patientId, bsnEntId);
            }

            connection.commit();

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

    public List<StaffPositionLookup> getStaffPositionLookup(String bsnEntId, String sortOn, String direction) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM SVC WHERE BE_ID = ? " +
                         " ORDER BY %s %s", sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<Service> resultList =
                    (List<Service>) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model.Service");

            List<StaffPositionLookup> result = new ArrayList<>();
            for (Service service : resultList) {
                StaffPositionLookup staffPosition = new StaffPositionLookup();
                staffPosition.setName(service.getServiceName().toString());
                staffPosition.setDesc(service.getServiceDescription());
                //dmr--RemovedFromModel--staffPosition.setType(service.getServiceType());
                staffPosition.setRecordCreated(service.getRecordCreateTimestamp());
                staffPosition.setRecordUpdated(service.getRecordUpdateTimestamp());
                result.add(staffPosition);
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

    public List<BusinessEntityFedTaxLookup> lookupFedTaxId(String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.BE_ID,T1.BE_PAR_ID,T2.BE_NAME,T2.BE_FEDERALTAX_ID " +
                    "  FROM BE_REL T1 " +
                    "INNER JOIN (SELECT BE_ID,BE_NAME,BE_FEDERALTAX_ID,REC_TERM_TMSTP,CURR_REC_IND FROM BE) T2 " +
                    "ON T1.BE_ID = T2.BE_ID " +
                    " " +
                    "WHERE T2.BE_FEDERALTAX_ID IS NOT NULL " +
                    "  AND (T1.BE_PAR_ID = ? OR T1.BE_ID = ?) " +
                    "  AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1)";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityFedTaxLookup> resultList = new ArrayList<>();

            while (resultSet.next()) {

                BusinessEntityFedTaxLookup busEntFedTaxLkup = new BusinessEntityFedTaxLookup();

                busEntFedTaxLkup.setBusinessEntityID(resultSet.getString("BE_ID"));
                busEntFedTaxLkup.setBusinessEntityParentID(resultSet.getString("BE_PAR_ID"));
                busEntFedTaxLkup.setBusinessEntityName(resultSet.getString("BE_NAME"));
                busEntFedTaxLkup.setBusinessEntityFederalTaxIdentifier(resultSet.getString("BE_FEDERALTAX_ID"));

                resultList.add(busEntFedTaxLkup);
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

    public List<BusinessEntityBillerLookup> lookupBiller(String bsnEntId) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = "SELECT DISTINCT T1.PAYER_SK,T1.BILLER_USER_GUID,T2.PAYER_ID,T2.PAYER_NAME " +
                    "  FROM BE_PAYER T1 " +
                    "INNER JOIN (SELECT PAYER_SK,PAYER_ID,PAYER_NAME,REC_TERM_TMSTP,CURR_REC_IND " +
                    "  FROM PAYER) T2 " +
                    "ON T1.PAYER_SK = T2.PAYER_SK " +
                    " " +
                    "WHERE BE_ID = ? " +
                    "  AND (TO_CHAR(T1.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T1.CURR_REC_IND = 1) " +
                    "  AND (TO_CHAR(T2.REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND T2.CURR_REC_IND = 1) ";

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            preparedStatement.setString(index++, bsnEntId);

            resultSet = preparedStatement.executeQuery();

            List<BusinessEntityBillerLookup> resultList = new ArrayList<>();

            while (resultSet.next()) {

                BusinessEntityBillerLookup busEntBillerLkup = new BusinessEntityBillerLookup();

                busEntBillerLkup.setPayerSK(BigInteger.valueOf(resultSet.getBigDecimal("PAYER_SK").longValue()));
                busEntBillerLkup.setBillerUserGloballyUniqueIdentifier(resultSet.getString("BILLER_USER_GUID"));
                busEntBillerLkup.setPayerID(resultSet.getString("PAYER_ID"));
                busEntBillerLkup.setPayerName(resultSet.getString("PAYER_NAME"));

                resultList.add(busEntBillerLkup);
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

    public Response getHcpcsLkup(
                        String filter,
                        int page,
                        int pageSize,
                        String orderByColumn,
                        String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM HCPCS_LKUP " +
                    "          %s " +
                    "          ORDER BY %s %s) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<HCPCSLookup> resultList =
                        (List<HCPCSLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.HCPCSLookup", 2);

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

    public Response getIcdDiagnosisLkup(
            String termDate,
            String filter,
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM ICD_DX_LKUP " +
                    "          WHERE ICD_DX_CODE_TERM_DATE = TO_DATE('%s', 'YYYY-MM-DD') " +
                    "                %s " +
                    "          ORDER BY %s %s) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",
                    termDate,
                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<ICDDiagnosisLookup> resultList =
                        (List<ICDDiagnosisLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.ICDDiagnosisLookup", 2);

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

    public Response getRaceEthnicityLkup(
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * " +
                            "          FROM RACE_ETHNICITY_LKUP " +
                            "           WHERE LEVEL = ? " +
                            "  START WITH race_ethnicity_code = " +
                            "  (SELECT race_ethnicity_code " +
                            "  FROM race_ethnicity_lkup " +
                            "  WHERE UPPER(race_ethnicity_name) = ? " +
                            "  ) " +
                            "  CONNECT BY prior race_ethnicity_code = race_ethnicity_par_code " +
                            "          ORDER BY %s %s) " +
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

            int i = 1;
            preparedStatement.setInt(i++, 2);
            preparedStatement.setString(i++, "RACE");


            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<RaceEthnicityLookup> resultList =
                        (List<RaceEthnicityLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.RaceEthnicityLookup", 2);

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

            e.printStackTrace();

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

    public Response getContrChangeReasonLkup(
            String appModule,
            String payerId,
            String contractId,
            String code,
            String name,
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder filter = new StringBuilder();
            List params = new ArrayList();

            if (StringUtils.isNotEmpty(appModule)) {

                filter.append(" AND UPPER(APP_MOD_NAME) = ?");
                params.add(appModule.toUpperCase());
            }

            if (StringUtils.isNotEmpty(payerId)) {

                filter.append(" AND UPPER(PAYER_ID) = ?");
                params.add(payerId.toUpperCase());
            }

            if (StringUtils.isNotEmpty(contractId)) {

                filter.append(" AND UPPER(CONTR_ID) = ?");
                params.add(contractId.toUpperCase());
            }

            if (StringUtils.isNotEmpty(code)) {

                filter.append(" AND UPPER(CHANGE_REASON_CODE) = ?");
                params.add(code.toUpperCase());
            }

            if (StringUtils.isNotEmpty(name)) {

                filter.append(" AND UPPER(CHANGE_REASON_NAME) LIKE ?");
                params.add("%" + name.toUpperCase() + "%");
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (  " +
                            "  SELECT * FROM (  " +
                            "      (SELECT *  " +
                            "          FROM CONTR_CHANGE_REASON_LKUP  " +
                            "          WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1' " +
                            "            AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) BETWEEN  " +
                            "            CHANGE_REASON_EFF_DATE AND CHANGE_REASON_TERM_DATE  " +
                            "  " +
                            "            %s  " +
                            "  " +
                            "          ORDER BY %s %s)  " +
                            "  )  " +
                            "  " +
                            ") R1)  " +
                            "  " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d  ",

                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            for (Object param : params) {
                preparedStatement.setObject(i++, param);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<ContractChangeReasonLookup> resultList =
                        (List<ContractChangeReasonLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.ContractChangeReasonLookup", 2);

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

            e.printStackTrace();

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

    public Response getPayerBillingCodeLkup(
            String bsnEntId,
            String payerId,
            String code,
            String description,
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder filter = new StringBuilder();
            List params = new ArrayList();

            if (StringUtils.isNotEmpty(payerId)) {

                filter.append(" AND UPPER(PAYER_ID) = ?");
                params.add(payerId.toUpperCase());
            }

            if (StringUtils.isNotEmpty(code)) {

                filter.append(" AND UPPER(BILLING_CODE) = ?");
                params.add(code.toUpperCase());
            }

            if (StringUtils.isNotEmpty(description)) {

                filter.append(" AND (UPPER(BILLING_CODE_SHORT_DESC) LIKE ?");
                filter.append(" OR UPPER(BILLING_CODE_LONG_DESC) LIKE ?)");
                params.add("%" + description.toUpperCase() + "%");
                params.add("%" + description.toUpperCase() + "%");
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            "  SELECT * FROM ( " +
                            "      (SELECT * " +
                            "          FROM PAYER_BILLING_CODE_LKUP " +
                            "          WHERE BE_ID = ? " +
                            "            AND TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1' " +
                            "            AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) BETWEEN " +
                            "            BILLING_CODE_EFF_DATE AND BILLING_CODE_TERM_DATE " +
                            " " +
                            "            %s " +
                            " " +
                            "          ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            preparedStatement.setString(i++, bsnEntId);

            for (Object param : params) {
                preparedStatement.setObject(i++, param);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<PayerBillingCodeLookup> resultList =
                        (List<PayerBillingCodeLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.PayerBillingCodeLookup", 2);

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

            e.printStackTrace();

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

    public Response getVvRndingRuleLkup(
            String id,
            String name,
            String description,
            String qualifier,
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            StringBuilder filter = new StringBuilder();
            List params = new ArrayList();

            if (StringUtils.isNotEmpty(id)) {

                filter.append(" AND UPPER(VV_RNDING_RULE_ID) = ?");
                params.add(id.toUpperCase());
            }

            if (StringUtils.isNotEmpty(name)) {

                filter.append(" AND UPPER(VV_RNDING_RULE_NAME) LIKE ?");
                params.add("%" + name.toUpperCase() + "%");
            }

            if (StringUtils.isNotEmpty(description)) {

                filter.append(" AND UPPER(VV_RNDING_RULE_DESC) LIKE ?");
                params.add("%" + description.toUpperCase() + "%");
            }

            if (StringUtils.isNotEmpty(qualifier)) {

                filter.append(" AND UPPER(VV_RNDING_RULE_QLFR) = ?");
                params.add(qualifier.toUpperCase());
            }

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM (  " +
                            "  SELECT * FROM (  " +
                            "      (SELECT *  " +
                            "          FROM VV_RNDING_RULE_LKUP  " +
                            "          WHERE TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1'  " +
                            "            AND CAST(SYS_EXTRACT_UTC(CURRENT_TIMESTAMP) AS DATE) BETWEEN  " +
                            "            VV_RNDING_RULE_EFF_DATE AND VV_RNDING_RULE_TERM_DATE  " +
                            "  " +
                            "            %s  " +
                            "  " +
                            "          ORDER BY %s %s)  " +
                            "  )  " +
                            "  " +
                            ") R1)  " +
                            "  " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d  ",

                    filter.toString(),
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int i = 1;
            for (Object param : params) {
                preparedStatement.setObject(i++, param);
            }

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                List<VisitVerificationRoundingRuleLookup> resultList =
                        (List<VisitVerificationRoundingRuleLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.VisitVerificationRoundingRuleLookup", 2);

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

            e.printStackTrace();

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

    public Response getMedLkup(
            String filter,
            int page,
            int pageSize,
            String orderByColumn,
            String direction
    ) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * " +
                            "          FROM MED_LKUP " +
                            "          %s " +
                            "          ORDER BY %s %s) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d ",

                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            List<MedicationLookup> resultList = new ArrayList<>();
            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                resultList.addAll((List<MedicationLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.MedicationLookup", 2));
            }

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
     * Get values from lookup table with sorting option
     *
     * @return
     * @throws SandataRuntimeException
     */
    public Response getLookupData(String tableName, String sortOn, String direction, String entityName) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(new StringBuilder()
                    .append("SELECT * FROM %s ")
                    .append("WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') ")
                    .append("ORDER BY UPPER(%s) %s")
                    .toString(), tableName, sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();

            String className = String.format("com.sandata.lab.data.model.dl.model.%s", entityName);
            List<?> resultList = (List) new DataMapper().map(resultSet, className);

            response.setTotalRows(resultList.size());
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
     * Get values from lookup table with sorting option
     *
     * @return
     * @throws SandataRuntimeException
     */
    public Response getLanguageData(String sortOn, String direction, String entityName) throws SandataRuntimeException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format(new StringBuilder()
                    .append("SELECT * FROM LANG_LKUP ")
                    .append("WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1') " +
                            " AND LANG_ACTIVE_IND = ?  ")
                    .append("ORDER BY UPPER(%s) %s")
                    .toString(),  sortOn, direction);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1,true);

            resultSet = preparedStatement.executeQuery();

            Response response = new Response();


            List<?> resultList = (List) new DataMapper().map(resultSet, "com.sandata.lab.data.model.dl.model." + entityName);

            response.setTotalRows(resultList.size());
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

    public Response getCountyLkup(
                String filter,
                int page,
                int pageSize,
                String orderByColumn,
                String direction,
                List<Object> params) throws SandataRuntimeException {
    
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);
    
            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM COUNTY_LKUP " +
                    "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    "            %s " +
                    "          ORDER BY %s %s " +
                    "    ) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow

            );
    
            preparedStatement = connection.prepareStatement(sql);
    
            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            resultSet = preparedStatement.executeQuery();

            List<CountyLookup> resultList = new ArrayList<>();
            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                resultList.addAll((List<CountyLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.CountyLookup", 2));
            }

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

    public Response getCountySubdivLkup(
            String filter,
            int page,
            int pageSize,
            String orderByColumn,
            String direction,
            List<Object> params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                            " " +
                            "  SELECT * FROM ( " +
                            " " +
                            "      (SELECT * " +
                            "          FROM COUNTY_SUBDIV_LKUP " +
                            "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                            "            %s " +
                            "          ORDER BY %s %s" +
                            "    ) " +
                            "  ) " +
                            " " +
                            ") R1) " +
                            " " +
                            "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow

            );

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            resultSet = preparedStatement.executeQuery();

            List<CountySubdivisionLookup> resultList = new ArrayList<>();
            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                resultList.addAll((List<CountySubdivisionLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.CountySubdivisionLookup", 2));
            }

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

    public Response getPstlCodeLkup(
                    String filter,
                    int page,
                    int pageSize,
                    String orderByColumn,
                    String direction,
                    List<Object> params) throws SandataRuntimeException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT DISTINCT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM PSTL_CODE_LKUP " +
                    "          WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    " " +
                    "          %s " +
                    " " +
                    "          ORDER BY %s %s" +
                    "    ) " +
                    "  ) " +
                    " " +
                    ") R1) " +
                    " " +
                    "WHERE ROW_NUMBER BETWEEN %d AND %d",

                    filter,
                    orderByColumn,
                    direction,
                    fromRow,
                    toRow);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            Response response = new Response();
            response.setOrderByColumn(orderByColumn);
            response.setOrderByDirection(direction);
            response.setPage(page);
            response.setPageSize(pageSize);

            resultSet = preparedStatement.executeQuery();

            List<PostalCodeLookup> resultList = new ArrayList<>();
            if (resultSet.next()) {

                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                resultList.addAll((List<PostalCodeLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.PostalCodeLookup", 2));
            }

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
    
    public Response getExcpLkup(String filterTyp, List<Object> params, String sortOn, String direction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = connectionPoolDataService.getConnection();
            connection.setAutoCommit(true);

            String sql = String.format("SELECT * FROM (SELECT ROWNUM ROW_NUMBER, COUNT(*) OVER() TOTAL_ROWS, R1.* FROM ( " +
                    " " +
                    "  SELECT DISTINCT * FROM ( " +
                    " " +
                    "      (SELECT * " +
                    "          FROM EXCP_LKUP " +
                    "          WHERE " +
                    "              %s" +
                    "              (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = 1) " +
                    " " +
                    " " +
                    "          ORDER BY %s %s" +
                    "    ) " +
                    "  ) " +
                    " " +
                    ") R1)",

                    filterTyp,
                    sortOn,
                    direction);

            preparedStatement = connection.prepareStatement(sql);

            int index = 1;
            for (Object param : params) {
                preparedStatement.setObject(index++, param);
            }

            Response response = new Response();
            response.setOrderByColumn(sortOn);
            response.setOrderByDirection(direction);

            resultSet = preparedStatement.executeQuery();

            List<ExceptionLookup> resultList = new ArrayList<>();
            if (resultSet.next()) {
                response.setTotalRows(resultSet.getBigDecimal("TOTAL_ROWS").intValue());

                resultList.addAll((List<ExceptionLookup>) new DataMapper().mapWithOffsetNext(resultSet, "com.sandata.lab.data.model.dl.model.ExceptionLookup", 2));
            }

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
