package com.sandata.lab.db.oracle.tools;

import com.sandata.lab.db.oracle.model.*;
import com.sandata.lab.db.oracle.model.Package;
import com.sandata.lab.db.oracle.util.ColumnUtil;
import com.sandata.lab.db.oracle.util.JavaUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Date: 8/21/15
 * Time: 8:27 AM
 */

public class GenerateLookupTables extends OracleTools {

    @Override
    public void generateCode() throws Exception {

        Connection connection = getConnection();
        connection.setAutoCommit(false);

        try {
            System.out.println("**** START: Processing: [LOOKUP TABLES]");

            Statement tabStatement = connection.createStatement();

            final String tabSql = "SELECT * FROM ALL_TABLES WHERE OWNER='COREDATA' AND " +
                    "TABLE_NAME LIKE '%_LOOKUP%'";

            ResultSet tabResultSet = tabStatement.executeQuery(tabSql);

            PackageManager packageManager = PackageManager.getInstance(connection);

            while (tabResultSet.next()) {

                String tableName = tabResultSet.getString("TABLE_NAME");

                String packageName = "PKG_LOOKUP";

                Package packageObj = packageManager.createPackage(packageName);

                final Table table = new Table();
                table.setLongName(tableName);
                table.setShortName(tableName);

                Statement colStatement = connection.createStatement();

                // REMINDER: "originalTableName" is the "actual" table name and should be used here.
                final String colSql = "SELECT * FROM ALL_TAB_COLUMNS WHERE OWNER='COREDATA' AND " +
                        "TABLE_NAME='" + table.getLongName() + "' ORDER BY COLUMN_ID";

                ResultSet colResultSet = colStatement.executeQuery(colSql);

                while (colResultSet.next()) {

                    Column column = new Column();
                    column.setTable(table.getLongName());
                    column.setId(colResultSet.getString(Column.ID));
                    column.setName(colResultSet.getString(Column.NAME));
                    column.setLength(colResultSet.getString(Column.LENGTH));
                    column.setType(colResultSet.getString(Column.TYPE));
                    column.setNullable(colResultSet.getString(Column.NULLABLE));
                    column.setPrecision(colResultSet.getString(Column.PRECISION));
                    column.setScale(colResultSet.getString(Column.SCALE));

                    table.addColumn(column);
                }

                colResultSet.close();

                JavaCode javaCode = createJavaClass(table);

                String executeJava = javaCode.oracleCreate();

                Statement oracleCreateStatement = connection.createStatement();
                oracleCreateStatement.setEscapeProcessing(false);
                oracleCreateStatement.execute(executeJava);
                oracleCreateStatement.close();

                // Create Packages

                // GET
                Function getPackageFunction = createGetPackageFunction(packageName, table.getShortName());
                getPackageFunction.setClassName(javaCode.getClassName());
                getPackageFunction.setTable(table);
                packageObj.addFunction(getPackageFunction);
            }

            packageManager.execute();

            tabStatement.close();

            System.out.println("**** END: Processing: [LOOKUP TABLES]");

            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    @Override
    protected JavaCode createJavaClass(Table table) throws Exception {

        JavaCode javaCode = new JavaCode();

        String className = JavaUtil.CamelCase(table.getShortName());

        StringBuilder source = new StringBuilder();

        // Imports
        for (String importVal : JavaUtil.Imports()) {
            source.append(importVal + "\n");
        }

        source.append("\n\n");

        source.append("public class " + className + " {\n\n");

        /**
         * REMINDER: Use the table "Long Name" which is the real table name but too long when creating Oracle Objects.
         */
        // Statics
        for (String staticVal : JavaUtil.Static(table)) {
            source.append(staticVal + "\n\n");
        }

        source.append(createGetLookup(className, table));

        // End the class
        source.append("\n\n}\n");

        javaCode.setSource(source.toString());
        javaCode.setClassName(className);

        return javaCode;
    }

    private String createGetLookup(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("get");

        builder.append("\tpublic static ResultSet ");
        for (String part : parts) {
            methodName.append(JavaUtil.FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("() throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s\", TABLE_NAME);\n\n",
                ColumnUtil.Select(table.getColumns())));

        builder.append("\t\t\t\t\treturn  new OracleQueryHandler().execute(sql);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    private Function createGetPackageFunction(final String packageName, final String className) {

        Function function = new Function();

        String methodName = "get" + JavaUtil.CamelCase(className);

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);

        PLSQLScript testSqlScript = new PLSQLScript();
        testSqlScript.setPackageName(packageName);
        testSqlScript.setMethodName(methodName);
        testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, methodName));

        function.setTestSql(testSqlScript);

        return function;
    }

    public GenerateLookupTables() throws Exception {
        super();
    }

    @Override
    protected void setup() throws Exception {

        System.out.println("***** SETUP: GenerateLookupTables *****");
    }

    @Override
    protected String tablePropertyResourceName() {
        return "lookup.tables.cfg";
    }
}
