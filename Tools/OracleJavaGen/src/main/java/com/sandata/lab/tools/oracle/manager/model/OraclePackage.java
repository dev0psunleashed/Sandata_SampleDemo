package com.sandata.lab.tools.oracle.manager.model;

import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Date: 5/1/16
 * Time: 9:41 PM
 */

public class OraclePackage {

    private final String TABLE_TYPE_LST = "%_LST";
    private final String TABLE_TYPE_LKUP = "%_LKUP";
    private final String TABLE_TYPE_BE_LKUP = "BE_%_LKUP";

    private final int MAX_TABLE_NAME_LENGTH = 27;
    private final String packageName;
    private List<Table> tables = new ArrayList<>();
    private final Connection connection;

    private String groupId;
    private String artifactId;
    private String jpubPackage;

    private final String schemaOwner;

    private Map<String, String> tableHash = new HashMap<>();

    public OraclePackage(String packageName, Connection connection, Properties properties, String schemaOwner) throws Exception {
        this.connection = connection;
        this.packageName = setPackageName(packageName, properties);
        this.schemaOwner = schemaOwner;

        this.connection.setAutoCommit(true);
    }

    public List<Table> getTables() {
        return tables;
    }

    private boolean isAgencyManagementTables(String tableFilter) {

        switch (tableFilter) {
            case TABLE_TYPE_LST:
            case TABLE_TYPE_BE_LKUP:
                return true;
        }

        return false;
    }

    private boolean requiresAdditionalFiltering(String tableFilter) {

        switch (tableFilter) {
            case "VISIT_TASK_LST":
            case "POC_TASK_LST":
            case "SCHED_TASK_LST":
                return false;
        }

        return true;
    }

    public void initTable(String tableFilter) throws SandataRuntimeException {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //TODO: Need a better solution (no time to design now)
            final String excludeBETables = " AND TABLE_NAME NOT LIKE 'BE_%'";

            final String excludeLookupTables =
                    String.format(" AND (TABLE_NAME NOT LIKE '%s' AND TABLE_NAME NOT LIKE '%s'",
                                        TABLE_TYPE_LKUP, TABLE_TYPE_LST);

            final String excludeDocTables = " AND TABLE_NAME NOT LIKE '%_DOC_XWALK')";
            final String excludeProviderTables = " AND TABLE_NAME <> 'PROVIDER'";

            StringBuilder query = new StringBuilder(String.format("SELECT * FROM ALL_TABLES WHERE OWNER='%s' " +
                    "AND TABLE_NAME LIKE '%s'", getSchemaOwner(), tableFilter));

            if (tableFilter.equals(TABLE_TYPE_LKUP)) {
                query.append(excludeBETables);
            }

            // For _LST tables, we need to exclude the following because they belong in the PKG_LOOKUP package
            else if (tableFilter.equals(TABLE_TYPE_LST)) {
                query.append(" AND TABLE_NAME <> 'VISIT_TASK_LST'");
                query.append(" AND TABLE_NAME <> 'POC_TASK_LST'");
                query.append(" AND TABLE_NAME <> 'SCHED_TASK_LST'");
            }
            else {

                // Exclude Lookup Tables
                if (!isAgencyManagementTables(tableFilter)
                        && requiresAdditionalFiltering(tableFilter)) {

                    query.append(excludeLookupTables);

                    // if NOT a DOC filter then exclude DOC tables
                    if(!tableFilter.equals("%_DOC_XWALK")) {

                        query.append(excludeDocTables);
                    }
                    else {
                        query.append(")");
                    }
                }

                // PR% is for Payroll only, exclude Provider
                if (tableFilter.equals("PR%")) {
                    query.append(excludeProviderTables);
                }
            }

            System.out.println(String.format("OraclePackage: EXECUTE SQL: [%s]", query));

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");

                final int tableNameLength = tableName.length();
                if (tableNameLength > MAX_TABLE_NAME_LENGTH) {
                    System.out.println(String.format("*** ERROR *** [%d] %s", tableName.length(), tableName));
                    continue;
                }
                else {
                    //dmr--System.out.println(String.format("[%d] %s", tableName.length(), tableName));
                    System.out.println(String.format("%s", tableName));
                }

                final Table table = new Table();
                table.setLongName(tableName);
                table.setShortName(tableName);

                Statement colStatement = connection.createStatement();

                // REMINDER: "originalTableName" is the "actual" table name and should be used here.
                final String colSql = String.format("SELECT * FROM ALL_TAB_COLUMNS WHERE OWNER='%s' AND " +
                        "TABLE_NAME='" + table.getLongName() + "' ORDER BY COLUMN_ID", getSchemaOwner());


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
                colStatement.close();

                tables.add(table);

                tableHash.put(table.getLongName(), getPackageName()); // tableName (key), packageName (value)
            }
        }
        catch (Exception e) {
            e.printStackTrace();

            throw new SandataRuntimeException(e.getMessage());
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    // return the packageName if the tableName provided is part of the package
    public String packageNameForTable(String tableName) {
        if (tableHash.containsKey(tableName)) {
            return getPackageName();
        }

        return null;
    }

    public String getPackageName() {
        return packageName;
    }

    private String setPackageName(String packageName, Properties properties) {

        this.groupId = properties.getProperty(String.format("%s.groupId", packageName));
        this.artifactId = properties.getProperty(String.format("%s.artifactId", packageName));
        this.jpubPackage = properties.getProperty("jpub.package");

        return packageName;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getJpubPackage() {
        return jpubPackage;
    }

    public String getSchemaOwner() {
        return this.schemaOwner;
    }
}
