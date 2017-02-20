package com.sandata.lab.tools.oracle.manager;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.tools.oracle.factory.StoredProcedureFactory;
import com.sandata.lab.tools.oracle.main.OracleTools;
import com.sandata.lab.tools.oracle.manager.model.OraclePackage;
import com.sandata.lab.tools.oracle.model.*;
import com.sandata.lab.tools.oracle.model.Package;
import com.sandata.lab.tools.oracle.util.FileUtil;
import com.sandata.lab.tools.oracle.util.JavaUtil;
import com.sandata.lab.tools.oracle.util.TableUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Date: 8/19/15
 * Time: 10:25 PM
 */

public class PackageManager extends OracleTools {

    protected HashMap<String, Package> packageHashMap;

    protected HashMap<String, OraclePackage> tablePackageHash;

    protected HashMap<String, String> tableNameMapper = new HashMap<>();

    protected Connection connection;

    protected int maxTableLength = 0;

    protected String typePath;

    protected String javaPath;

    protected String packagePath;

    protected GeneralTableManager generalTableManager = new GeneralTableManager();

    public String getShortestTableName(final String tableName) {

        if (tableName.length() > maxTableLength) {
            return tableNameMapper.get(tableName);
        }

        return tableName;
    }

    protected PackageManager() throws Exception {
        super();

        tablePackageHash = new HashMap<>();

        this.typePath = properties.getProperty("type.path");
        this.javaPath = properties.getProperty("java.path");
        this.packagePath = properties.getProperty("package.path");

        /**
         * Read the packages and table values from the config file.
         * Set the tablePackageHash to store the table as the key and the package as the value.
         */
        String packagesProperty = properties.getProperty("process.packages");
        if (packagesProperty == null || packagesProperty.length() == 0) {
            throw new SandataRuntimeException(String.format("PackageManager: process.packages for config [%s] " +
                    "is not defined!", propertyResourceName()));
        }

        String[] packages = packagesProperty.split(",");
        for (String packageVal : packages) {

            // Get the tables that are configured for this package...
            String tablesForPackage = properties.getProperty(packageVal);
            String[] tablesProperty = tablesForPackage.split(",");
            Connection connection = getConnection();
            connection.setAutoCommit(true);
            for (String table : tablesProperty) {
                try {
                    OraclePackage oraclePackage = new OraclePackage(packageVal, connection, properties, schemaOwner());
                    oraclePackage.initTable(table);
                    tablePackageHash.put(table, oraclePackage);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int abbreviationType() {
        return AbbreviationUtil.COREDATA_TYPE;
    }

    public static PackageManager getInstance() throws Exception {

        return new PackageManager();
    }

    public void execute() throws SQLException {

        if (packageHashMap == null) {
            System.out.println("WARNING: PackageManager: packageHashMap == null!");
            return;
        }

        Iterator itr = packageHashMap.entrySet().iterator();
        while (itr.hasNext()) {

            Map.Entry pair = (Map.Entry)itr.next();
            Package packageObj = (Package)pair.getValue();

            String toPackage = packageObj.toPackage();
            Statement toPackageStatement = connection.createStatement();
            toPackageStatement.setEscapeProcessing(false);
            toPackageStatement.execute(toPackage);
            toPackageStatement.close();

            String toPackageBody = packageObj.toPackageBody();
            Statement toPackageBodyStatement = connection.createStatement();
            toPackageBodyStatement.setEscapeProcessing(false);
            toPackageBodyStatement.execute(toPackageBody);
            toPackageBodyStatement.close();

            System.out.println("---- RUN TESTS ----");

            for (Function function : packageObj.getFunctions()) {

                if (function.getTestSql() == null) {
                    System.out.println(String.format("[%s]: ---- SKIP TESTS ----",
                            function.getMethodName()));

                    continue;
                }

                Statement statement = connection.createStatement();
                statement.setEscapeProcessing(false);

                System.out.println(String.format("TEST: [%s]: \n\nSQL: %s",
                        function.getMethodName(), function.getTestSql().getSource()));

                PLSQLScript plsqlScript = function.getTestSql();

                System.out.print(String.format("*** EXECUTING: START: [%s.%s] ***\n\n",
                        plsqlScript.getPackageName(), plsqlScript.getMethodName()));

                try {
                    ResultSet resultSet = statement.executeQuery(plsqlScript.getSource());

                    System.out.print(String.format("*** EXECUTING: FINISHED: [%s.%s] ***\n\n",
                            plsqlScript.getPackageName(), plsqlScript.getMethodName()));

                    Object storedProcDataObj = resultSet.getObject(1);

                    ResultSet storedProcResultSet = (ResultSet) storedProcDataObj;

                    while (storedProcResultSet.next()) {

                        for (Column column : function.getTable().getColumns()) {

                            System.out.println(String.format("Column: %s: Value: %s",
                                    column.getName(),
                                    storedProcResultSet.getObject(column.getName())));
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println(String.format("*** TEST FAILED ***: %s: %s",
                            e.getClass().getName(), e.getMessage()));
                }

                statement.close();
            }

            try {

                TableUtil.CreateOrReplacePackage(packageObj, toPackage, toPackageBody, getPackagePath());

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("---- TESTS: COMPLETE! ----");
        }

        // Reset State
        packageHashMap.clear();
    }

    public Package createPackage(final String packageName) {

        if (packageHashMap == null) {
            packageHashMap = new HashMap<>();
        }

        Package packageObj = packageHashMap.get(packageName);

        if (packageObj == null) {

            packageObj = new Package(packageName);
            packageHashMap.put(packageName, packageObj);
        }

        return packageObj;
    }

    public Package createPackageForTable(final String tableName) {

        String packageName = packageForTable(tableName);

        // Get the packageName using the "originalTableName" as the key since this is the "original" value set to
        // the package name. The "shortName" could have changed "getShortestTableName" to make sure the
        // table name length is small enough otherwise OracleMetadata would complain.
        if (packageName == null) {
            return null;
        }

        return createPackage(packageName);
    }

    public int getMaxTableLength() {
        return maxTableLength;
    }

    public void setMaxTableLength(int maxTableLength) throws SandataRuntimeException {
        this.maxTableLength = maxTableLength;

        Iterator itr = tablePackageHash.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry)itr.next();
            String table = (String)pair.getKey();

            if (table.length() > maxTableLength) {

                String tableShortName = properties.getProperty(table);

                if (tableShortName == null) {
                    throw new SandataRuntimeException(String.format("TABLE: %s: Length: %d: MUST HAVE A SHORT VERSION!!!",
                            table, table.length()));
                }

                tableNameMapper.put(table, tableShortName);
            }
        }
    }

    private String packageForTable(String tableName) {

        String packageName = null;
        Iterator itr = tablePackageHash.entrySet().iterator();
        while (itr.hasNext()) {

            Map.Entry pair = (Map.Entry)itr.next();

            OraclePackage oraclePackage = (OraclePackage)pair.getValue();
            packageName = oraclePackage.packageNameForTable(tableName);
            if (packageName != null) {
                return packageName;
            }
        }

        return null;
    }

    public List<OraclePackage> getOraclePackages() {

        return new ArrayList<>(tablePackageHash.values());
    }

    @Override
    public void generateCode() throws Exception {
    }

    public JavaCode createOracleJavaSrc(Table table, TypMappingMetadata metadata) throws Exception {

        JavaCode javaCode = createJavaCode(table, metadata);

        String executeJava = javaCode.oracleCreate();

        String javaPath = getJavaPath();

        if (javaPath != null) {

            String fileName = String.format("%s.java.sql", javaCode.getClassName());
            String filePath = String.format("%s/%s", javaPath, fileName);
            FileUtil.DeleteFile(filePath);
            FileUtil.Write(executeJava + ";\n/\n", javaPath, fileName);
        }

        return javaCode;
    }

    private JavaCode createJavaCode(Table table, TypMappingMetadata metadata) throws Exception {

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

        String javaSource;

        if (table.hasBsnEntColumn() && !table.isHistorical()) {
            StoredProcedure getWithBsnEnt = StoredProcedureFactory.GetWithBsnEnt(table, className);
            if (getWithBsnEnt != null) {
                metadata.addStoredProcedure(getWithBsnEnt);
                source.append(getWithBsnEnt.getSource());
            }
        }

        // If a table is a LKUP
        StoredProcedure getMethodForLkup = StoredProcedureFactory.GetMethodForLKUP(table, className);
        if (getMethodForLkup != null) {
            metadata.addStoredProcedure(getMethodForLkup);
            source.append(getMethodForLkup.getSource());
        }

        // If a table has primary keys defined in general.tables.cfg
        String[] primaryKeyColumns = generalTableManager.getPrimaryKeyColumnsProps(table.getLongName());

        StoredProcedure getMethodForPrimaryKeys = StoredProcedureFactory.GetMethodForPrimaryKeys(primaryKeyColumns, table, className);
        if (getMethodForPrimaryKeys != null) {
            metadata.addStoredProcedure(getMethodForPrimaryKeys);
            javaSource = getMethodForPrimaryKeys.getSource();

            if (javaSource != null) {
                source.append(javaSource);
            }
        }

        StoredProcedure getMethodForPK = StoredProcedureFactory.GetMethodForPK(table, className);
        if (getMethodForPK != null) {
            metadata.addStoredProcedure(getMethodForPK);
            javaSource = getMethodForPK.getSource();
            source.append(javaSource);
        }

        StoredProcedure spInsertMethod = StoredProcedureFactory.InsertMethod(table, className);
        if (spInsertMethod != null) {
            metadata.addStoredProcedure(spInsertMethod);
            javaSource = spInsertMethod.getSource();
            source.append(javaSource);
        }

        StoredProcedure spUpdateMethod = StoredProcedureFactory.UpdateMethod(table, className);
        if (spUpdateMethod != null) {
            metadata.addStoredProcedure(spUpdateMethod);
            javaSource = spUpdateMethod.getSource();
            source.append(javaSource);
        }

        StoredProcedure spDeleteMethod = StoredProcedureFactory.DeleteMethod(table, className);
        if (spDeleteMethod != null) {
            metadata.addStoredProcedure(spDeleteMethod);
            javaSource = spDeleteMethod.getSource();
            source.append(javaSource);
        }

        // End the class
        source.append("\n\n}\n");

        javaCode.setSource(source.toString());
        javaCode.setClassName(className);

        return javaCode;
    }

    @Override
    protected JavaCode createJavaClass(Table table) throws Exception {
        return null;
    }

    @Override
    protected void setup() throws Exception {
    }

    @Override
    protected void initProperties() throws Exception {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected String propertyResourceName() {
        return "packages.cfg";
    }

    @Override
    protected String tablePropertyResourceName() {
        return "oracle.cfg";
    }

    public String getTypePath() {
        return typePath;
    }

    public String getJavaPath() {
        return javaPath;
    }

    public String getPackagePath() {
        return packagePath;
    }
}

