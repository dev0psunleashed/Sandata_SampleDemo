package com.sandata.lab.db.oracle.tools;

import com.sandata.lab.db.oracle.model.*;
import com.sandata.lab.db.oracle.util.FunctionUtil;
import com.sandata.lab.db.oracle.util.JavaUtil;
import com.sandata.lab.db.oracle.util.TableUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/21/15
 * Time: 7:14 AM
 */

public class GenerateTables extends OracleTools {

    private List<CustomMethod> executeWithFilterSqlArray;

    public GenerateTables() throws Exception {
        super();
    }

    @Override
    protected void setup() throws Exception {

        System.out.println("***** SETUP: GenerateTables *****");

        executeWithFilterSqlArray = new ArrayList<CustomMethod>();

        int index = 1;
        String executeWithFilterProperty = "executeWithFilter.sql";
        String executeWithFilterKey = String.format("%s.%d", executeWithFilterProperty, index++);
        String executeWithFilterSql = tableProperties.getProperty(executeWithFilterKey);

        while (executeWithFilterSql != null) {

            CustomMethod customMethod = new CustomMethod();
            customMethod.setSelectPattern(executeWithFilterSql);

            customMethod.setClassName(tableProperties.getProperty(String.format("%s.class", executeWithFilterKey)));
            customMethod.setMethodName(tableProperties.getProperty(String.format("%s.method", executeWithFilterKey)));

            int whereIndex = 1;
            String whereColumnKey = String.format("%s.where.%d", executeWithFilterKey, whereIndex++);
            String whereColumn = tableProperties.getProperty(whereColumnKey);
            while (whereColumn != null) {

                customMethod.addWhereColumn(whereColumn);

                // Get Next
                whereColumnKey = String.format("%s.where.%d", executeWithFilterKey, whereIndex++);
                whereColumn = tableProperties.getProperty(whereColumnKey);
            }

            CustomType customType = new CustomType();
            customType.setName(tableProperties.getProperty(String.format("%s.type.name", executeWithFilterKey)));
            customType.setCreateOrReplace(tableProperties.getProperty(String.format("%s.type.create", executeWithFilterKey)));
            customMethod.setCustomType(customType);
            executeWithFilterSqlArray.add(customMethod);

            // Get Next
            executeWithFilterKey = String.format("%s.%d", executeWithFilterProperty, index++);
            executeWithFilterSql = tableProperties.getProperty(executeWithFilterKey);
        }
    }

    @Override
    public void generateCode() throws Exception {

        Connection connection = getConnection();
        connection.setAutoCommit(false);

        try {
            for (String processTable : tables) {

                System.out.println(String.format("**** START: Processing: [%s]", processTable));

                Statement tabStatement = connection.createStatement();

                final String tabSql = String.format("SELECT * FROM ALL_TABLES WHERE OWNER='COREDATA' AND " +
                        "TABLE_NAME LIKE '%s%%'", processTable);

                ResultSet tabResultSet = tabStatement.executeQuery(tabSql);

                PackageManager packageManager = PackageManager.getInstance(connection);

                while (tabResultSet.next()) {

                    String originalTableName = tabResultSet.getString("TABLE_NAME");

                    String shortName = getShortestTableName(originalTableName);

                    if (shortName == null) {
                        System.out.println("WARNING: [TABLE: NULL]: " + tabResultSet.getString("TABLE_NAME") + ": IS NOT CONFIGURED!");
                        continue;
                    }

                    // Get the packageName using the "originalTableName" as the key since this is the "original" value set to
                    // the package name. The "shortName" could have changed "getShortestTableName" to make sure the
                    // table name length is small enough otherwise Oracle would complain.
                    String packageName = tablePackageHash.get(originalTableName);

                    // Skip the table if it is NOT defined in the config
                    if (packageName == null) {

                        System.out.println("WARNING: " + shortName + ": IS NOT CONFIGURED!");
                        continue;
                    }

                    com.sandata.lab.db.oracle.model.Package packageObj = packageManager.createPackage(packageName);

                    final Table table = new Table();
                    table.setLongName(originalTableName);
                    table.setShortName(shortName);

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

                        // *** HACK*** Determine if the column is the Sequence Key (SK)...
                        // Since the format per table varies, this may *** NOT *** work for some tables!!
                        if (column.getName().contains("_SK")) {
                            table.setSequenceKeyColumn(column.getName());
                        }
                    }

                    colResultSet.close();

                    /**
                     * DROP
                     */
                    // Try to drop TAB type first since we can not create/replace the Type
                    TableUtil.DropType(connection, String.format("%s_TAB_TYP", table.getShortName()));

                    // Object Type
                    Statement typStatement = connection.createStatement();
                    typStatement.setEscapeProcessing(false);
                    typStatement.execute(TableUtil.CreateOrReplaceTyp(table));
                    typStatement.close();

                    System.out.println(String.format("**** Creating Table: [DONE]-->[%s][%d] ****",
                            table.getShortName(), table.getShortName().length()));

                    // Object Table Type
                    Statement tabTypeStatement = connection.createStatement();
                    tabTypeStatement.execute(String.format("CREATE OR REPLACE TYPE %s_TAB_TYP AS TABLE OF %s_TYP",
                            table.getShortName(), table.getShortName()));
                    tabTypeStatement.close();

                    JavaCode javaCode = createJavaClass(table);

                    /**
                     * Create/Replace Custom Types
                     */
                    if (executeWithFilterSqlArray != null) {

                        for (CustomMethod customMethod : executeWithFilterSqlArray) {

                            if (customMethod.getClassName().equals(javaCode.getClassName())) {
                                Statement customTypeStatement = connection.createStatement();
                                customTypeStatement.setEscapeProcessing(false);
                                customTypeStatement.execute(customMethod.getCustomType().getCreateOrReplace());
                                customTypeStatement.close();
                            }
                        }
                    }

                    String executeJava = javaCode.oracleCreate();

                    Statement oracleCreateStatement = connection.createStatement();
                    oracleCreateStatement.setEscapeProcessing(false);
                    oracleCreateStatement.execute(executeJava);
                    oracleCreateStatement.close();

                    // Create Packages

                    // CUSTOM
                    if (executeWithFilterSqlArray != null) {

                        for (CustomMethod customMethod : executeWithFilterSqlArray) {

                            if (customMethod.getClassName().equals(javaCode.getClassName())) {

                                Function customFunction = FunctionUtil.CreateCustomMethod(packageName, customMethod);
                                customFunction.setClassName(customMethod.getClassName());
                                customFunction.setTable(table);
                                packageObj.addFunction(customFunction);
                            }
                        }
                    }

                    // GET for PK's
                    Function getMethodForPK = FunctionUtil.GetMethodForPrimaryKeys(packageName, table.getShortName());
                    getMethodForPK.setClassName(javaCode.getClassName());
                    getMethodForPK.setTable(table);
                    packageObj.addFunction(getMethodForPK);

                    // GET for SK
                    Function getMethodForSK = FunctionUtil.GetMethodForPK(packageName, table.getShortName());
                    getMethodForSK.setClassName(javaCode.getClassName());
                    getMethodForSK.setTable(table);
                    packageObj.addFunction(getMethodForSK);

                    // INSERT
                    Function insertMethod = FunctionUtil.InsertMethod(connection, table, packageObj.getName());
                    insertMethod.setClassName(javaCode.getClassName());
                    insertMethod.setTable(table);
                    packageObj.addFunction(insertMethod);

                    // UPDATE
                    Function updateMethod = FunctionUtil.UpdateMethod(connection, table, packageObj.getName());
                    updateMethod.setClassName(javaCode.getClassName());
                    updateMethod.setTable(table);
                    packageObj.addFunction(updateMethod);

                    // DELETE
                    Function deleteMethod = FunctionUtil.DeleteMethod(connection, table, packageObj.getName());
                    deleteMethod.setClassName(javaCode.getClassName());
                    deleteMethod.setTable(table);
                    packageObj.addFunction(deleteMethod);
                }

                packageManager.execute();

                tabStatement.close();

                System.out.println(String.format("**** END: Processing: [%s]", processTable));
            }

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

        if (executeWithFilterSqlArray != null) {

            for (CustomMethod customMethod : executeWithFilterSqlArray) {

                if (customMethod.getClassName().equals(className)) {
                    source.append(JavaUtil.CreateCustomExecuteWithFilter(customMethod));
                }
            }
        }

        String[] primaryKeyColumns = getPrimaryKeyColumnsProps(table.getLongName());
        if (primaryKeyColumns != null) {
            source.append(JavaUtil.GetMethodForPrimaryKeys(primaryKeyColumns, className, table));
        }

        source.append(JavaUtil.GetMethodForPK(className, table));
        source.append(JavaUtil.InsertMethod(className, table));
        source.append(JavaUtil.UpdateMethod(className, table));
        source.append(JavaUtil.DeleteMethod(className, table));

        // End the class
        source.append("\n\n}\n");

        javaCode.setSource(source.toString());
        javaCode.setClassName(className);

        return javaCode;
    }

    private String[] getPrimaryKeyColumnsProps(final String tableName) {

        String key = "primary.keys." + tableName;

        String primaryKeyColumnsString = tableProperties.getProperty(key);
        if (primaryKeyColumnsString != null) {

            String[] primaryKeyColumns = primaryKeyColumnsString.split("\\|");
            return primaryKeyColumns;
        }

        return null;
    }

    @Override
    protected String tablePropertyResourceName() {
        return "general.tables.cfg";
    }
}
