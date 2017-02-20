package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.model.PLSQLScript;
import com.sandata.lab.tools.oracle.model.Parameter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Date: 8/20/15
 * Time: 8:01 AM
 */

public class PLSQLTestUtil {

    private static String defaultDate = "TO_DATE('2015-08-20 00:00:01', 'yyyy-mm-dd hh24:mi:ss')";

    public static PLSQLScript CreateInsertTest(final Connection connection, final Table table, final String structType,
                                               final String packageName, final String insertMethodName) throws SQLException {

        Integer sequenceKey = PLSQLTestUtil.getNextSequenceKey(connection, table.getLongName());
        if (sequenceKey == null) {
            System.out.println(String.format("ERROR: CreateInsertTest: Could not get NEXTVAL SEQ for [%s]: NULL",
                    table.getLongName()));

            return null;
        }

        PLSQLScript plsqlScript =  PLSQLTestUtil.CreateStructTest(table, structType, packageName, insertMethodName);
        plsqlScript.setSequenceKey(sequenceKey);
        plsqlScript.setTable(table);
        return plsqlScript.buildScript();
    }

    public static PLSQLScript CreateUpdateTest(final Connection connection, final Table table, final String structType,
                                                final String packageName, final String insertMethodName) throws SQLException {

        PLSQLScript plsqlScript = PLSQLTestUtil.CreateStructTest(table, structType, packageName, insertMethodName);
        plsqlScript.setSequenceKey(1); // TODO: Should get a record first then use the SK from that record?
        plsqlScript.setTable(table);
        return plsqlScript.buildScript();
    }

    private static PLSQLScript CreateStructTest(final Table table, final String structType,
                                               final String packageName, final String insertMethodName) throws SQLException {

        PLSQLScript plsqlScript = new PLSQLScript();
        plsqlScript.setPackageName(packageName);
        plsqlScript.setMethodName(insertMethodName);

        Parameter methodParameter = new Parameter();
        methodParameter.setName("oracleTypeObj");
        methodParameter.setType(structType);
        plsqlScript.addParameter(methodParameter);

        Parameter returnParameter = new Parameter();
        returnParameter.setName("returnVal");
        returnParameter.setType(Parameter.NUMBER);
        plsqlScript.setReturnType(returnParameter);

        return plsqlScript;
    }

    private static Integer getNextSequenceKey(final Connection connection, final String tableName) throws SQLException {

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + tableName+ "_SEQ.NEXTVAL FROM DUAL");
            if (!resultSet.next()) {

                System.out.println(String.format("ERROR: getNextSequenceKey: Could not get NEXTVAL SEQ for [%s]", tableName));

                return null;
            }

            return resultSet.getInt("NEXTVAL");
        }
        catch (Exception e) {
            throw new SQLException("PLSQLTestUtil: getNextSequenceKey: " +
                    e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            if (statement != null) {
                // close statement
                statement.close();
            }
        }
    }

    public static String GetValue(final String type, final String nullable) {

        if (nullable.equals("Y")) {
            return "null";
        }

        if (type.equals(Parameter.CHAR)) {
            return "0";
        }

        if (type.equals(Parameter.NUMBER)) {
            return "1";
        }

        if (type.equals(Parameter.VARCHAR2)) {
            return "'1'";
        }

        if (type.equals(Parameter.DATE)) {
            return defaultDate;
        }

        return "null";
    }
}
