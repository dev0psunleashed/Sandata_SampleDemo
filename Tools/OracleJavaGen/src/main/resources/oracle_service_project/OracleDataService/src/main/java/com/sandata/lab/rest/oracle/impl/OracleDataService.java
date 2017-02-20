package com.sandata.lab.rest.oracle.impl;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.oracle.api.OracleService;
import com.sandata.lab.rest.oracle.jpub.model.PatientTyp;
import com.sandata.lab.rest.oracle.utils.data.DataMapper;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OracleDataService implements OracleService {

    private SandataOracleConnection sandataOracleConnection;

    public void setSandataOracleConnection(SandataOracleConnection sandataOracleConnection) {
        this.sandataOracleConnection = sandataOracleConnection;
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, String... primaryKeys) throws SandataRuntimeException {

        Connection connection = null;

        try {
            connection = sandataOracleConnection.getConnection();

            ArrayDescriptor des = ArrayDescriptor.createDescriptor("COREDATA.STRING_ARRAY", connection);
            ARRAY primaryKeysArray = new ARRAY(des, connection, primaryKeys);

            CallableStatement callableStatement =
                    connection.prepareCall(String.format("{?=call %s.%s(?)}", packageName, methodName));

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setArray(2, primaryKeysArray);

            callableStatement.execute();
            ResultSet resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            resultSet.close();

            return result;

        } catch (Exception e) {

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, Object... parameters) throws SandataRuntimeException {

        Connection connection = null;

        try {
            connection = sandataOracleConnection.getConnection();

            String columns = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";//max of 20 parameters
            columns = columns.substring(0, (parameters.length * 2) - 1);
            CallableStatement callableStatement =
                    connection.prepareCall(String.format("{?=call %s.%s(%s)}", packageName, methodName, columns));

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            int parPos = 2;
            for(Object param : parameters) {
                if(param instanceof String) {
                    callableStatement.setString(parPos++, (String)param);
                }else if(param instanceof BigDecimal) {
                    callableStatement.setBigDecimal(parPos++, (BigDecimal) param);
                }else if(param instanceof Integer) {
                    callableStatement.setBigDecimal(parPos++, new BigDecimal( (Integer)param));
                }else if(param instanceof Boolean) {
                    callableStatement.setBoolean(parPos++, (Boolean)param);
                }

            }



            callableStatement.execute();
            ResultSet resultSet = (ResultSet)callableStatement.getObject(1);

            Object result = new DataMapper().map(resultSet, className);

            resultSet.close();

            return result;

        } catch (Exception e) {

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object executeGet(String packageName, String methodName, String className, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = sandataOracleConnection.getConnection();
            connection.setAutoCommit(false);

            ResultSet resultSet = (ResultSet)sandataOracleConnection.call(connection,
                    String.format("{?=call %s.%s(?)}", packageName, methodName), OracleTypes.CURSOR, new Object[]{sequenceKey});

            connection.commit();

            Object result = new DataMapper().map(resultSet, className);

            resultSet.close();

            return result;

        } catch (Exception e) {

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public int execute(String packageName, String methodName, int sequenceKey) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = sandataOracleConnection.getConnection();
            connection.setAutoCommit(false);

            Integer result = (Integer) sandataOracleConnection.call(connection,
                    String.format("{?=call %s.%s(?)}", packageName, methodName), OracleTypes.INTEGER, new Object[]{sequenceKey});

            connection.commit();

            return result;

        } catch (Exception e) {

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public int execute(String packageName, String methodName, Object jpubType) throws SandataRuntimeException {

        Connection connection = null;

        try {

            connection = sandataOracleConnection.getConnection();
            connection.setAutoCommit(false);

            Integer result = (Integer) sandataOracleConnection.call(connection,
                    String.format("{?=call %s.%s(?)}", packageName, methodName), OracleTypes.INTEGER, new Object[]{jpubType});

            connection.commit();

            return result;

        } catch (Exception e) {

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
                    e.getClass().getName(), e.getMessage()));
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    @Override
    public int execute(Connection connection, String packageName, String methodName, Object jpubType) throws SQLException {

        return (Integer) sandataOracleConnection.call(connection,
                String.format("{?=call %s.%s(?)}", packageName, methodName), OracleTypes.INTEGER, new Object[]{jpubType});
    }

    public Connection getOracleConnection() throws SQLException {
        return sandataOracleConnection.getConnection();
    }
}
