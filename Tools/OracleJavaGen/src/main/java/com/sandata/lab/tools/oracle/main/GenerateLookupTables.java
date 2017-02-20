package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.tools.oracle.creator.RestServicesCreator;
import com.sandata.lab.tools.oracle.manager.PackageManager;
import com.sandata.lab.tools.oracle.model.*;
import com.sandata.lab.tools.oracle.model.Package;
import com.sandata.lab.tools.oracle.util.ColumnUtil;
import com.sandata.lab.tools.oracle.util.JavaUtil;
import com.sandata.lab.tools.oracle.util.PhysicalModelUtil;
import com.sandata.lab.tools.oracle.util.TableUtil;
import com.sandata.lab.util.process.JPubProcess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Date: 8/21/15
 * Time: 8:27 AM
 */

public class GenerateLookupTables extends OracleTools {

    public static void main(String[] args) throws Exception {

        PackageManager packageManager = PackageManager.getInstance();

        // LOOKUP
        OracleTools lookupTables = new GenerateLookupTables(packageManager);
        lookupTables.generateCode();
        lookupTables.close();

        //MetadataMapper.getInstance(lookupTables.getMetadataMap()).process();
    }

    @Override
    public void generateCode() throws Exception {

        Connection connection = getConnection();
        connection.setAutoCommit(false);

        try {
            System.out.println("**** START: Processing: [LOOKUP TABLES]");

            String currentProcessTable = "LOOKUP";
            String groupId = String.format("com.sandata.lab.rest.%s", currentProcessTable.toLowerCase());
            String jpubPackage = String.format("%s.jpub.model", groupId);
            RestServicesTemplate restServicesTemplate = RestServicesTemplate.getInstance()
                    .TableName(currentProcessTable)
                    .GroupID(groupId)
                    .JpubPackage(jpubPackage);

            RestServicesCreator restServicesCreator = RestServicesCreator.getInstance()
                    .ProjectName(String.format("GEN_REST_%s_SERVICE", currentProcessTable))
                    .TableName(currentProcessTable)
                    .GroupID(groupId)
                    .ArtifactID("sandata-rest-" + currentProcessTable.toLowerCase() + "-data-service")
                    .Description(String.format("Sandata REST %s Service", currentProcessTable))
                    .Version("1.0");

            // JPUB: Generate Java classes from created types...
            JPubProcess jPubProcess = JPubProcess.getInstance();

            // Important! Set connection for packageManager
            packageManager.setConnection(connection);

            Statement tabStatement = connection.createStatement();

            final String tabSql = "SELECT * FROM ALL_TABLES WHERE OWNER='COREDATA' AND " +
                    "TABLE_NAME LIKE '%_LKUP%'";

            ResultSet tabResultSet = tabStatement.executeQuery(tabSql);

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

                // Object Type
                Statement typStatement = connection.createStatement();
                typStatement.setEscapeProcessing(false);
                typStatement.execute(TableUtil.CreateOrReplaceTyp(table));
                typStatement.close();

                JavaCode javaCode = createJavaClass(table);

                String executeJava = javaCode.oracleCreate();

                Statement oracleCreateStatement = connection.createStatement();
                oracleCreateStatement.setEscapeProcessing(false);
                oracleCreateStatement.execute(executeJava);
                oracleCreateStatement.close();

                // Create Packages

                // GET
                Function getPackageFunction = createGetPackageFunction(packageName, table);
                getPackageFunction.setClassName(javaCode.getClassName());
                getPackageFunction.setTable(table);
                packageObj.addFunction(getPackageFunction);

                String logicalClassName = PhysicalModelUtil.LogicalClass(abbreviationUtil, table.getLongName());
                String restPathName = RestUtil.PathName(logicalClassName, currentProcessTable);

                // REST Endpoint
                GetLookupEndpoint getLookupEndpoint = new GetLookupEndpoint();
                getLookupEndpoint.setPathName(restPathName);
                getLookupEndpoint.setModelClass(logicalClassName);
                getLookupEndpoint.setOraclePackage(packageName);
                getLookupEndpoint.setMethodName(getPackageFunction.getMethodName());
                restServicesTemplate.addRestEndpoint(getLookupEndpoint);

                TypMappingMetadata metadata = new TypMappingMetadata();
                metadata.setTypePackageName(jpubPackage);
                metadata.setTable(table);
                metadata.setPackageName(packageObj.getName());
                metadata.setModelClass(logicalClassName);

                // JPUB: Create Batch File / Since executable is only on Windows
                jPubProcess.createTyp(metadata.getTypName(), metadata.getTypeClassName(), "com.sandata.lab.data.model.jpub.model");
                restServicesCreator.AddExecProcess(jPubProcess.toString());

                addTypMappingMetadata(table.getShortName(), metadata);

                //dmr--jPubProcess.execute();
            }

            packageManager.execute();

            tabStatement.close();

            System.out.println("**** END: Processing: [LOOKUP TABLES]");

            connection.commit();

            restServicesCreator
                    .RestServiceTemplate(restServicesTemplate)
                    .Create();
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
         * REMINDER: Use the table "Long Name" which is the real table name but too long when creating OracleMetadata Objects.
         */
        // Statics
        for (String staticVal : JavaUtil.Static(table)) {
            source.append(staticVal + "\n\n");
        }

        if (!table.hasBsnEntColumn()) {
            source.append(createGetLookup(className, table));
        }
        else {
            source.append(JavaUtil.GetLookupWithBsnEnt(className, table));
        }
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

    private Function createGetPackageFunction(final String packageName, final Table table) {

        Function function = new Function();

        String methodName = "get" + JavaUtil.CamelCase(table.getShortName());

        if (table.hasBsnEntColumn()) {
            Parameter parameter = new Parameter();
            parameter.setName("BSN_ENT_ID");
            parameter.setType(Parameter.VARCHAR2);
            function.addParameter(parameter);
        }

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);

        PLSQLScript testSqlScript = new PLSQLScript();
        testSqlScript.setPackageName(packageName);
        testSqlScript.setMethodName(methodName);
        testSqlScript.setSource(String.format("SELECT %s.%s(1) FROM DUAL", packageName, methodName));

        function.setTestSql(testSqlScript);

        return function;
    }

    public GenerateLookupTables(PackageManager packageManager) throws Exception {
        super(packageManager);
    }

    @Override
    protected int abbreviationType() {
        return AbbreviationUtil.COREDATA_TYPE;
    }

    @Override
    protected void initProperties() throws Exception {
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
