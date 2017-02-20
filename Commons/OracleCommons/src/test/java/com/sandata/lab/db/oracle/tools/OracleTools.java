package com.sandata.lab.db.oracle.tools;

import com.sandata.lab.db.oracle.model.*;
import com.sandata.lab.db.oracle.model.Package;
import com.sandata.lab.db.oracle.util.FunctionUtil;
import com.sandata.lab.db.oracle.util.JavaUtil;
import com.sandata.lab.db.oracle.util.TableUtil;
import oracle.ucp.UniversalConnectionPoolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Date: 8/19/15
 * Time: 5:47 AM
 */

public abstract class OracleTools extends Tools {

    protected Properties tableProperties;

    protected SandataOracleConnection sandataOracleConnection;

    protected HashMap<String, String> tablePackageHash = new HashMap<String, String>();

    protected HashMap<String, String> tableNameMapper = new HashMap<String, String>();

    protected List<String> tables = new ArrayList<String>();

    protected int maxTableLength;

    public abstract void generateCode() throws Exception;

    protected abstract JavaCode createJavaClass(Table table) throws Exception;

    protected Connection getConnection() throws SQLException {

        return this.sandataOracleConnection.getConnection();
    }

    protected abstract void setup() throws Exception;

    protected void initProperties(final Properties properties) throws Exception {

        /**
         * Read the tables that we should process
         */
        String processTables = properties.getProperty("tables");

        if (processTables != null && processTables.length() > 0) {
            String[] tablesArray = processTables.split(",");
            for (String table : tablesArray) {

                System.out.println(String.format("INFO: TABLE: [%s]: Configured!", table));

                tables.add(table);
            }
        }

        /**
         * Read the packages and table values from the config file.
         * Set the tablePackageHash to store the table as the key and the package as the value.
         */
        String packagesProperty = properties.getProperty("packages");

        if (packagesProperty != null && packagesProperty.length() > 0) {

            maxTableLength = Integer.valueOf(properties.getProperty("table.max.length"));

            String[] packages = packagesProperty.split(",");
            for (String packageVal : packages) {

                String tablesForPackage = properties.getProperty(packageVal);

                String[] tablesProperty = tablesForPackage.split(",");
                for (String table : tablesProperty) {
                    tablePackageHash.put(table, packageVal);

                    if (table.length() > maxTableLength) {

                        String tableShortName = properties.getProperty(table);

                        if (tableShortName == null) {
                            throw new Exception(String.format("TABLE: %s: Length: %d: MUST HAVE A SHORT VERSION!!!",
                                    table, table.length()));
                        }

                        tableNameMapper.put(table, tableShortName);
                    }
                }
            }
        }
    }

    public OracleTools() throws Exception {
        super();

        this.sandataOracleConnection =
                new SandataOracleConnection()
                        .DatabaseName(properties.getProperty("sid"))
                        .ServerName(properties.getProperty("server"))
                        .PortNumber(Integer.valueOf(properties.getProperty("port")))
                        .User(properties.getProperty("user"))
                        .Password(properties.getProperty("password"))
                        .Open();

        this.sandataOracleConnection.startPool();

        // load the child table properties
        tableProperties = loadProperties(tablePropertyResourceName());

        initProperties(tableProperties);

        setup();
    }

    protected String getShortestTableName(final String tableName) {

        if (tableName.length() > maxTableLength) {
            return tableNameMapper.get(tableName);
        }

        return tableName;
    }

    public void close() throws UniversalConnectionPoolException {

        this.sandataOracleConnection.stopPool();
    }

    /**
     * Global settings that affect all classes.
     * @return the config file name that will be loaded.
     */
    @Override
    protected String propertyResourceName() {
        return "oracle.cfg";
    }

    /**
     * Specific settings for the class that inherits OracleTools.
     * @return the config file name that will be loaded.
     */
    protected abstract String tablePropertyResourceName();

    private void createJavaFile(Table table) throws FileNotFoundException, UnsupportedEncodingException {

        final String path = "OracleTools/";

        File target = new File(path);

        if (!target.exists()) {
            target.mkdirs();
        }

        String className = JavaUtil.CamelCase(table.getShortName());
        String javaFile = className + ".java";
        PrintWriter writer = new PrintWriter(path + "/" + javaFile, "UTF-8");

        // Imports
        for (String importVal : JavaUtil.Imports()) {
            writer.write(importVal + "\n");
        }

        writer.write("\n\n");

        writer.write("public class " + className + " {\n\n");

        // Statics
        for (String staticVal : JavaUtil.Static(table)) {
            writer.write(staticVal + "\n\n");
        }

        writer.write(JavaUtil.GetMethodForPK(javaFile, table));

        // End the class
        writer.write("\n\n}\n");

        writer.close();
    }
}
