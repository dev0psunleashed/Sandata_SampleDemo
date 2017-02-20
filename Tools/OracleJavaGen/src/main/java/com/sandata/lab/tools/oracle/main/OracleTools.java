package com.sandata.lab.tools.oracle.main;

import com.sandata.lab.common.oracle.db.connection.SandataOracleConnection;
import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.manager.PackageManager;
import com.sandata.lab.tools.oracle.model.JavaCode;
import com.sandata.lab.tools.oracle.model.TypMappingMetadata;
import com.sandata.lab.tools.oracle.util.JavaUtil;
import com.sandata.lab.util.tools.Tools;
import oracle.ucp.UniversalConnectionPoolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Date: 8/19/15
 * Time: 5:47 AM
 */

public abstract class OracleTools extends Tools {

    protected final AbbreviationUtil abbreviationUtil;

    private Map<String, TypMappingMetadata> typMappingMetadataMap;
    private Map<String, TypMappingMetadata> metadataMap;

    protected Properties tableProperties;

    protected SandataOracleConnection sandataOracleConnection;

    protected PackageManager packageManager;

    public String schemaOwner() {
        return tableProperties.getProperty("user").toUpperCase();
    }

    public Map<String, TypMappingMetadata> getMetadataMap() {

        if (getTypMappingMetadataMap() == null) {
            return null;
        }

        if (metadataMap != null) {
            return metadataMap;
        }

        metadataMap = new HashMap<>();

        for (Object entrySet : getTypMappingMetadataMap().entrySet()) {
            Map.Entry pair = (Map.Entry) entrySet;
            TypMappingMetadata metadata = (TypMappingMetadata) pair.getValue();
            metadataMap.put(metadata.getModelClass(), metadata);
        }

        return metadataMap;
    }

    public Map<String, TypMappingMetadata> getTypMappingMetadataMap() {
        return typMappingMetadataMap;
    }

    public void addTypMappingMetadata(final String key, final TypMappingMetadata typMappingMetadata) {
        if (this.typMappingMetadataMap == null) {
            this.typMappingMetadataMap = new HashMap<>();
        }
        this.typMappingMetadataMap.put(key, typMappingMetadata);
    }

    public abstract void generateCode() throws Exception;

    protected abstract JavaCode createJavaClass(Table table) throws Exception;

    protected Connection getConnection() throws SQLException {

        return this.sandataOracleConnection.getConnection();
    }

    protected abstract void setup() throws Exception;

    protected abstract void initProperties() throws Exception;

    protected OracleTools() throws Exception {
        super();

        this.abbreviationUtil = new AbbreviationUtil(abbreviationType());

        initTableProperties();
        initSandataOracleConnection();
    }

    public OracleTools(PackageManager packageManager) throws Exception {
        super();

        this.abbreviationUtil = new AbbreviationUtil(abbreviationType());

        this.packageManager = packageManager;

        initTableProperties();
        initSandataOracleConnection();
    }

    protected abstract int abbreviationType();

    private void initTableProperties() throws Exception {
        // load the child table properties
        String tablePropertyConfigFile = tablePropertyResourceName();
        if (tablePropertyConfigFile != null) {
            tableProperties = loadProperties(tablePropertyConfigFile);
            initProperties();
            setup();
        }
    }

    protected void initSandataOracleConnection() throws Exception {
        this.sandataOracleConnection =
                new SandataOracleConnection()
                        .DatabaseName(tableProperties.getProperty("sid"))
                        .ServerName(tableProperties.getProperty("server"))
                        .PortNumber(Integer.valueOf(tableProperties.getProperty("port")))
                        .User(tableProperties.getProperty("user"))
                        .Password(tableProperties.getProperty("password"))
                        .Open();

        this.sandataOracleConnection.startPool();
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

    public void executeSql(String sql, Connection connection) throws Exception {

        Statement typStatement = connection.createStatement();
        typStatement.setEscapeProcessing(false);
        typStatement.execute(sql);
        typStatement.close();
    }
}
