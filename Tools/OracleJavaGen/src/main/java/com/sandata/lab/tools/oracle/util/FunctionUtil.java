package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.model.CustomMethod;
import com.sandata.lab.tools.oracle.model.Function;
import com.sandata.lab.tools.oracle.model.PLSQLScript;
import com.sandata.lab.tools.oracle.model.Parameter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Date: 8/19/15
 * Time: 4:58 PM
 */

public class FunctionUtil {

    public static Function CreateCustomWithFilterMethod(final String packageName, final CustomMethod method) {

        Function function = new Function();

        function.setMethodName(method.getMethodName());
        function.setReturnType(Parameter.CURSOR);

        Parameter filterParameter = new Parameter();
        filterParameter.setName(method.getCustomType().getName());
        filterParameter.setType(method.getCustomType().getTypeName());
        function.addParameter(filterParameter);

        Parameter orderByColumn = new Parameter();
        orderByColumn.setName("ORDER_BY_COLUMN");
        orderByColumn.setType(Parameter.VARCHAR2);
        function.addParameter(orderByColumn);

        Parameter pageParam = new Parameter();
        pageParam.setName("PAGE");
        pageParam.setType(Parameter.NUMBER);
        function.addParameter(pageParam);

        Parameter pageSizeParam = new Parameter();
        pageSizeParam.setName("PAGE_SIZE");
        pageSizeParam.setType(Parameter.NUMBER);
        function.addParameter(pageSizeParam);

        Parameter ascendingParam = new Parameter();
        ascendingParam.setName("ORDER_BY_DIRECTION");
        ascendingParam.setType(Parameter.VARCHAR2);
        function.addParameter(ascendingParam);

        PLSQLScript testSqlScript = new PLSQLScript();
        testSqlScript.setPackageName(packageName);
        testSqlScript.setMethodName(method.getMethodName());
        testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, method.getMethodName()));

        function.setTestSql(testSqlScript);

        return function;
    }

    public static Function CreateCustomSQLMethod(final String packageName, final CustomMethod method) {

        Function function = new Function();

        function.setMethodName(method.getMethodName());
        function.setReturnType(Parameter.CURSOR);

        Parameter primaryKeysArray = new Parameter();
        primaryKeysArray.setName("PARAMS_ARRAY");
        primaryKeysArray.setType(Parameter.NUMBER_ARRAY);

        function.addParameter(primaryKeysArray);

        PLSQLScript testSqlScript = new PLSQLScript();
        testSqlScript.setPackageName(packageName);
        testSqlScript.setMethodName(method.getMethodName());
        testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, method.getMethodName()));

        function.setTestSql(testSqlScript);

        return function;
    }

    public static Function GetMethodForPrimaryKeys(final String packageName, final String className) {

        Function function = new Function();

        String methodName = "get" + JavaUtil.CamelCase(className);

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);

        Parameter primaryKeysArray = new Parameter();
        primaryKeysArray.setName(className.toUpperCase() + "_ARRAY");
        primaryKeysArray.setType(Parameter.NUMBER_ARRAY);

        function.addParameter(primaryKeysArray);

        PLSQLScript testSqlScript = new PLSQLScript();
        testSqlScript.setPackageName(packageName);
        testSqlScript.setMethodName(methodName);
        testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, methodName));

        function.setTestSql(testSqlScript);

        return function;
    }

    public static Function GetMethodForPK(final String packageName, final String className) {

          Function function = new Function();

          String methodName = "get" + JavaUtil.CamelCase(className);

          function.setMethodName(methodName);
          function.setReturnType(Parameter.CURSOR);

          Parameter primaryKey = new Parameter();
          primaryKey.setName(className.toUpperCase() + "_KEY");
          primaryKey.setType(Parameter.NUMBER);

          function.addParameter(primaryKey);

          PLSQLScript testSqlScript = new PLSQLScript();
          testSqlScript.setPackageName(packageName);
          testSqlScript.setMethodName(methodName);
          testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, methodName));

          function.setTestSql(testSqlScript);

          return function;
    }

    public static Function InsertMethod(
            final Connection connection,
            final Table table,
            final String packageName) throws SQLException {

        final String structTyp = table.getShortName() + "_T";

        Function function = new Function();

        String methodName = "insert" + JavaUtil.CamelCase(table.getShortName());

        function.setMethodName(methodName);
        function.setReturnType(Parameter.NUMBER);

        Parameter primaryKey = new Parameter();
        primaryKey.setName(table.getShortName());
        primaryKey.setType(structTyp);

        function.addParameter(primaryKey);

        PLSQLScript testSqlScript = PLSQLTestUtil.CreateInsertTest(connection, table, structTyp, packageName, methodName);

        System.out.println("**** InsertMethod: SCRIPT START **** \n\n" + testSqlScript.getSource());
        System.out.println("\n\n**** InsertMethod: SCRIPT END ****");

        function.setTestSql(testSqlScript);

        return function;
    }

    public static Function UpdateMethod(
            final Connection connection,
            final Table table,
            final String packageName) throws SQLException {

        final String structTyp = table.getShortName() + "_T";

        Function function = new Function();

        String methodName = "update" + JavaUtil.CamelCase(table.getShortName());

        function.setMethodName(methodName);
        function.setReturnType(Parameter.NUMBER);

        Parameter primaryKey = new Parameter();
        primaryKey.setName(table.getShortName());
        primaryKey.setType(structTyp);

        function.addParameter(primaryKey);

        PLSQLScript testSqlScript = PLSQLTestUtil.CreateUpdateTest(connection, table, structTyp, packageName, methodName);

        System.out.println("**** UpdateMethod: SCRIPT START **** \n\n" + testSqlScript.getSource());
        System.out.println("\n\n**** UpdateMethod: SCRIPT END ****");

        function.setTestSql(testSqlScript);

        return function;
    }

    public static Function DeleteMethod(
            final Connection connection,
            final Table table,
            final String packageName) throws SQLException {

        Function function = new Function();

        String methodName = "delete" + JavaUtil.CamelCase(table.getShortName());

        function.setMethodName(methodName);
        function.setReturnType(Parameter.NUMBER);

        Parameter primaryKey = new Parameter();
        primaryKey.setName(table.getShortName() + "_KEY");
        primaryKey.setType(Parameter.NUMBER);

        function.addParameter(primaryKey);

        StringBuilder builder = new StringBuilder();

        builder.append("SET SERVEROUTPUT ON;\n");
        builder.append("DBMS_OUTPUT.ENABLE;\n");
        builder.append("DECLARE\n");
        builder.append("\treturnVal NUMBER;\n");
        builder.append("BEGIN\n\n");
        builder.append("returnVal := " + packageName + "." + methodName + "(1);\n");
        builder.append("DBMS_OUTPUT.PUT_LINE(returnVal);\n\n");
        builder.append("END;\n");

        PLSQLScript plsqlScript = new PLSQLScript();
        plsqlScript.setPackageName(packageName);
        plsqlScript.setMethodName(methodName);
        plsqlScript.setSource(builder.toString());

        System.out.println("**** DeleteMethod: SCRIPT START **** \n\n" + plsqlScript.getSource());
        System.out.println("\n\n**** DeleteMethod: SCRIPT END ****");

        function.setTestSql(plsqlScript);

        return function;
    }
}
