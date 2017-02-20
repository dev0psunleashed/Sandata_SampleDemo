package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.rest.RestUtil;
import com.sandata.lab.tools.oracle.creator.RestServicesCreator;
import com.sandata.lab.tools.oracle.manager.PackageManager;
import com.sandata.lab.tools.oracle.manager.TableManager;
import com.sandata.lab.tools.oracle.model.*;
import com.sandata.lab.tools.oracle.model.Package;
import com.sandata.lab.tools.oracle.util.*;
import com.sandata.lab.util.process.JPubProcess;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/21/15
 * Time: 7:14 AM
 */

public class GenerateTables extends OracleTools {

    public static void main(String[] args) throws Exception {

        PackageManager packageManager = PackageManager.getInstance();

        // GENERAL
        OracleTools generateTables = new GenerateTables(packageManager);
        generateTables.generateCode();
        generateTables.close();

        //MetadataMapper.getInstance(generateTables.getMetadataMap()).process();

        /*
        Map<String, TypMappingMetadata> metadataMap = generateTables.getMetadataMap();
        for (Object entrySet : metadataMap.entrySet()) {
            Map.Entry pair = (Map.Entry) entrySet;

            //TypMappingMetadata metadata = (TypMappingMetadata) pair.getValue();

            System.out.println(pair.getKey());
        }*/
    }

    private List<CustomMethod> executeWithFilterSqlArray;
    private List<CustomMethod> executeCustomSqlArray;
    private List<CustomType> customTypesArray;

    public GenerateTables(PackageManager packageManager) throws Exception {
        super(packageManager);
    }

    @Override
    protected int abbreviationType() {
        return AbbreviationUtil.COREDATA_TYPE;
    }

    private void setupCustomFilters() {
        executeWithFilterSqlArray = new ArrayList<>();

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

    private void setupCustomTypes() {
        customTypesArray = new ArrayList<>();

        String customTypeProperty = "custom.type";
        // loop until we don't find any more custom types
        for (int index = 1; /* Until No More Custom Types */; index++) {

            String typeName = tableProperties.getProperty(String.format("%s.%d.name", customTypeProperty, index));
            if (typeName == null) {
                break;
            }

            CustomType type = new CustomType();
            type.setName(typeName);

            String create = tableProperties.getProperty(String.format("%s.%d.create", customTypeProperty, index));
            if (create == null) {
                throw new SandataRuntimeException(String.format("GenerateTables: setupCustomTypes: [%s] is NOT configured!", create));
            }

            type.setCreateOrReplace(create);
            customTypesArray.add(type);
        }

    }

    private void setupCustomSQL() {
        executeCustomSqlArray = new ArrayList<>();

        int index = 1;
        String propertyName = "executeCustom.sql";
        String key = String.format("%s.%d", propertyName, index++);
        String sql = tableProperties.getProperty(key);

        while (sql != null) {
            CustomMethod customMethod = new CustomMethod();
            customMethod.setSelectPattern(sql);

            customMethod.setClassName(tableProperties.getProperty(String.format("%s.class", key)));
            customMethod.setMethodName(tableProperties.getProperty(String.format("%s.method", key)));

            int whereIndex = 1;
            String whereColumnKey = String.format("%s.where.%d", key, whereIndex++);
            String whereColumn = tableProperties.getProperty(whereColumnKey);
            while (whereColumn != null) {
                customMethod.addWhereColumn(whereColumn);

                // Get Next
                whereColumnKey = String.format("%s.where.%d", key, whereIndex++);
                whereColumn = tableProperties.getProperty(whereColumnKey);
            }

            executeCustomSqlArray.add(customMethod);

            // Get Next
            key = String.format("%s.%d", propertyName, index++);
            sql = tableProperties.getProperty(key);
        }
    }

    @Override
    protected void setup() throws Exception {

        System.out.println("***** SETUP: GenerateTables *****");

        setupCustomFilters();
        setupCustomTypes();
        setupCustomSQL();
    }

    @Override
    public void generateCode() throws Exception {

        Connection connection = getConnection();
        connection.setAutoCommit(false);

        try {

            // Important! Set connection for packageManager
            packageManager.setConnection(connection);

            TableManager tableManager = TableManager.getInstance(connection, packageManager);

            while (tableManager.hasNext()) {

                List<Table> configuredTables = tableManager.next();

                String currentProcessTable = tableManager.getCurrentProcessTable();
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

                for (int index = 0; index < configuredTables.size(); index++) {

                    Table table = configuredTables.get(index);

                    Package packageObj = packageManager.createPackageForTable(table.getLongName());

                    // Skip the table if it is NOT defined in the config
                    if (packageObj == null) {

                        System.out.println("WARNING: " + table.getLongName() + ": IS NOT CONFIGURED!");
                        continue;
                    }

                    // Add the jpubPackage which is the java package (namespace) to where the jpub files will eventually end up.
                    packageObj.setJpubPackage(jpubPackage);

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

                    // JPUB: Generate Java classes from created types...
                    JPubProcess jPubProcess = JPubProcess.getInstance();

                    TypMappingMetadata metadata = new TypMappingMetadata();
                    metadata.setTypePackageName(jpubPackage);
                    metadata.setTable(table);
                    metadata.setPackageName(packageObj.getName());
                    addTypMappingMetadata(table.getShortName(), metadata);

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

                    if (customTypesArray != null) {

                        for (CustomType customType : customTypesArray) {

                            Statement customTypeStatement = connection.createStatement();
                            customTypeStatement.setEscapeProcessing(false);
                            customTypeStatement.execute(customType.getCreateOrReplace());
                            customTypeStatement.close();
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

                                Function customFunction = FunctionUtil.CreateCustomWithFilterMethod(packageObj.getName(), customMethod);
                                customFunction.setClassName(customMethod.getClassName());
                                customFunction.setTable(table);
                                packageObj.addFunction(customFunction);
                            }
                        }
                    }

                    if (executeCustomSqlArray != null) {

                        for (CustomMethod customMethod : executeCustomSqlArray) {

                            if (customMethod.getClassName().equals(javaCode.getClassName())) {

                                Function customFunction = FunctionUtil.CreateCustomSQLMethod(packageObj.getName(), customMethod);
                                customFunction.setClassName(customMethod.getClassName());
                                customFunction.setTable(table);
                                packageObj.addFunction(customFunction);
                            }
                        }
                    }

                    String logicalClassName = PhysicalModelUtil.LogicalClass(abbreviationUtil, table.getLongName());

                    // TODO: Hacky... Need to fix
                    if (logicalClassName.equals("PatientDmeAndSupplies")) {
                        logicalClassName = "PatientDMEAndSupplies";
                    }

                    String restPathName = RestUtil.PathName(logicalClassName, currentProcessTable);

                    // INSERT
                    Function insertMethod = FunctionUtil.InsertMethod(connection, table, packageObj.getName());
                    insertMethod.setClassName(javaCode.getClassName());
                    insertMethod.setTable(table);
                    packageObj.addFunction(insertMethod);

                    // REST Endpoint
                    InsertEndpoint insertEndpoint = new InsertEndpoint();
                    insertEndpoint.setPathName(restPathName);
                    insertEndpoint.setModelClass(logicalClassName);
                    insertEndpoint.setOraclePackage(metadata.getPackageName());
                    insertEndpoint.setMethodName(insertMethod.getMethodName());
                    restServicesTemplate.addRestEndpoint(insertEndpoint);
                    //

                    // UPDATE
                    Function updateMethod = FunctionUtil.UpdateMethod(connection, table, packageObj.getName());
                    updateMethod.setClassName(javaCode.getClassName());
                    updateMethod.setTable(table);
                    packageObj.addFunction(updateMethod);

                    // REST Endpoint
                    UpdateEndpoint updateEndpoint = new UpdateEndpoint();
                    updateEndpoint.setPathName(restPathName);
                    updateEndpoint.setModelClass(logicalClassName);
                    updateEndpoint.setOraclePackage(metadata.getPackageName());
                    updateEndpoint.setMethodName(updateMethod.getMethodName());
                    restServicesTemplate.addRestEndpoint(updateEndpoint);
                    //

                    // DELETE
                    Function deleteMethod = FunctionUtil.DeleteMethod(connection, table, packageObj.getName());
                    deleteMethod.setClassName(javaCode.getClassName());
                    deleteMethod.setTable(table);
                    packageObj.addFunction(deleteMethod);

                    // REST Endpoint
                    DeleteEndpoint deleteEndpoint = new DeleteEndpoint();
                    deleteEndpoint.setPathName(restPathName);
                    deleteEndpoint.setModelClass(logicalClassName);
                    deleteEndpoint.setOraclePackage(metadata.getPackageName());
                    deleteEndpoint.setMethodName(deleteMethod.getMethodName());
                    restServicesTemplate.addRestEndpoint(deleteEndpoint);
                    //

                    // GET for SK
                    Function getMethodForSK = FunctionUtil.GetMethodForPK(packageObj.getName(), table.getShortName());
                    getMethodForSK.setClassName(javaCode.getClassName());
                    getMethodForSK.setTable(table);
                    packageObj.addFunction(getMethodForSK);

                    // REST Endpoint
                    GetSkEndpoint getSkEndpoint = new GetSkEndpoint();
                    getSkEndpoint.setPathName(restPathName);
                    getSkEndpoint.setModelClass(logicalClassName);
                    getSkEndpoint.setOraclePackage(metadata.getPackageName());
                    getSkEndpoint.setMethodName(getMethodForSK.getMethodName());
                    restServicesTemplate.addRestEndpoint(getSkEndpoint);
                    //

                    // GET for PK
                    Function getMethodForPK = FunctionUtil.GetMethodForPrimaryKeys(packageObj.getName(), table.getShortName());
                    getMethodForPK.setClassName(javaCode.getClassName());
                    getMethodForPK.setTable(table);
                    packageObj.addFunction(getMethodForPK);


                    List<String> primaryKeys = TableUtil.GetPrimaryKeys(connection, table, currentProcessTable);

                    // REST Endpoint
                    GetPkEndpoint getPkEndpoint = new GetPkEndpoint();
                    getPkEndpoint.setPathName(restPathName);
                    getPkEndpoint.setModelClass(logicalClassName);
                    getPkEndpoint.setOraclePackage(metadata.getPackageName());
                    getPkEndpoint.setMethodName(getMethodForPK.getMethodName());
                    getPkEndpoint.setMatrixParams(primaryKeys);
                    restServicesTemplate.addRestEndpoint(getPkEndpoint);
                    //

                    metadata.setModelClass(logicalClassName);
                    metadata.setPrimaryKeys(primaryKeys);

                    // JPUB: Create Batch File / Since executable is only on Windows
                    jPubProcess.createTyp(metadata.getTypName(), metadata.getTypeClassName(), "com.sandata.lab.data.model.jpub.model");
                    restServicesCreator.AddExecProcess(jPubProcess.toString());

                    //dmr--jPubProcess.execute();
                }

                packageManager.execute();

                printStdOut(String.format("**** END: Processing: [%s]", tableManager.getCurrentProcessTable()));

                restServicesCreator
                        .RestServiceTemplate(restServicesTemplate)
                        .Create();

            } // while(hasNext)

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
    protected void initProperties() throws Exception {

        this.packageManager.setMaxTableLength(Integer.valueOf(tableProperties.getProperty("table.max.length")));
    }

    @Override
    protected JavaCode createJavaClass(Table table) throws Exception {

        TypMappingMetadata metadata = getTypMappingMetadataMap().get(table.getShortName());

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

        if (executeCustomSqlArray != null) {

            for (CustomMethod customMethod : executeCustomSqlArray) {

                if (customMethod.getClassName().equals(className)) {
                    source.append(JavaUtil.CustomSQLMethod(customMethod));
                }
            }
        }

        if (executeWithFilterSqlArray != null) {

            for (CustomMethod customMethod : executeWithFilterSqlArray) {

                if (customMethod.getClassName().equals(className)) {
                    source.append(JavaUtil.CreateCustomExecuteWithFilter(customMethod));

                    // Setup metadata
                    StoredProcedure storedProcedure = new StoredProcedure();
                    storedProcedure.setExecuteType(StoredProcedure.FIND_TYPE);
                    Function function = new Function();
                    function.setMethodName(customMethod.getMethodName());
                    function.setClassName(customMethod.getClassName());
                    Parameter parameter = new Parameter();
                    parameter.setName(customMethod.getCustomType().getName());
                    parameter.setType(Parameter.STRUCT);
                    function.addParameter(parameter);
                    storedProcedure.setFunction(function);
                    metadata.addStoredProcedure(storedProcedure);
                    //
                }
            }
        }

        String[] primaryKeyColumns = getPrimaryKeyColumnsProps(table.getLongName());
        if (primaryKeyColumns != null) {
            source.append(JavaUtil.GetMethodForPrimaryKeys(primaryKeyColumns, className, table));

            // Setup metadata
            StoredProcedure storedProcedure = new StoredProcedure();
            storedProcedure.setExecuteType(StoredProcedure.GET_TYPE);
            Function function = new Function();
            function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
            function.setClassName(className);
            Parameter parameter = new Parameter();
            parameter.setName("params");
            parameter.setType(Parameter.NUMBER_ARRAY);
            function.addParameter(parameter);
            storedProcedure.setFunction(function);
            metadata.addStoredProcedure(storedProcedure);
            //
        }

        source.append(JavaUtil.GetMethodForPK(className, table));

        // Setup metadata
        {
            StoredProcedure spGetMethodForPK = new StoredProcedure();
            spGetMethodForPK.setExecuteType(StoredProcedure.GET_TYPE);
            Function function = new Function();
            function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
            function.setClassName(className);
            Parameter parameter = new Parameter();
            parameter.setName("primaryKey");
            parameter.setType(Parameter.NUMBER);
            function.addParameter(parameter);
            spGetMethodForPK.setFunction(function);
            metadata.addStoredProcedure(spGetMethodForPK);
        }
        //

        source.append(JavaUtil.InsertMethod(className, table));

        // Setup metadata
        {
            StoredProcedure spInsertMethod = new StoredProcedure();
            spInsertMethod.setExecuteType(StoredProcedure.INSERT_TYPE);
            Function function = new Function();
            function.setMethodName(MethodUtil.MethodNameFromTable(table, "insert"));
            function.setClassName(className);
            Parameter parameter = new Parameter();
            parameter.setName("struct");
            parameter.setType(Parameter.STRUCT);
            function.addParameter(parameter);
            spInsertMethod.setFunction(function);
            metadata.addStoredProcedure(spInsertMethod);
        }
        //

        source.append(JavaUtil.UpdateMethod(className, table));

        // Setup metadata
        {
            StoredProcedure spUpdateMethod = new StoredProcedure();
            spUpdateMethod.setExecuteType(StoredProcedure.UPDATE_TYPE);
            Function function = new Function();
            function.setMethodName(MethodUtil.MethodNameFromTable(table, "update"));
            function.setClassName(className);
            Parameter parameter = new Parameter();
            parameter.setName("struct");
            parameter.setType(Parameter.STRUCT);
            function.addParameter(parameter);
            spUpdateMethod.setFunction(function);
            metadata.addStoredProcedure(spUpdateMethod);
        }
        //

        source.append(JavaUtil.DeleteMethod(className, table));

        // Setup metadata
        {
            StoredProcedure spDeleteMethod = new StoredProcedure();
            spDeleteMethod.setExecuteType(StoredProcedure.DELETE_TYPE);
            Function function = new Function();
            function.setMethodName(MethodUtil.MethodNameFromTable(table, "delete"));
            function.setClassName(className);
            Parameter parameter = new Parameter();
            parameter.setName("primaryKey");
            parameter.setType(Parameter.NUMBER);
            function.addParameter(parameter);
            spDeleteMethod.setFunction(function);
            metadata.addStoredProcedure(spDeleteMethod);
        }
        //

        if (
                table.getLongName().equals("ALLERGY_LKUP")
                || table.getLongName().equals("DME_AND_SUPPLY_LKUP")
                || table.getLongName().equals("NUTR_RQMT_LKUP")) {
            source.append(JavaUtil.GetLookupWithBsnEnt(className, table));
        }

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
