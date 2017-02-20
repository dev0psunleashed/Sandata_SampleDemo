package com.sandata.lab.tools.oracle.manager;

import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.util.tools.Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/4/15
 * Time: 2:37 PM
 */

public class TableManager extends Tools {

    private final Connection connection;

    private List<String> tables;
    private List<Table> configuredTables;

    private int currentIndex;

    private String currentProcessTable;

    private PackageManager packageManager;

    public boolean hasNext() {
        return currentIndex >=0 && currentIndex < tables.size();
    }

    public List<Table> next() throws SandataRuntimeException {

        ResultSet tabResultSet = null;
        Statement tabStatement = null;

        try {
            if (hasNext()) {

                currentProcessTable = tables.get(currentIndex++);

                configuredTables = new ArrayList<>();

                printStdOut(String.format("**** START: Processing: [%s]", currentProcessTable));

                tabStatement = this.connection.createStatement();

                final String tabSql = String.format("SELECT * FROM ALL_TABLES WHERE OWNER='COREDATA' AND " +
                        "TABLE_NAME LIKE '%s%%'", currentProcessTable);

                tabResultSet = tabStatement.executeQuery(tabSql);
                while (tabResultSet.next()) {

                    String configuredTable = tabResultSet.getString("TABLE_NAME");
                    String shortName = packageManager.getShortestTableName(configuredTable);

                    if (shortName == null) {
                        System.out.println("WARNING: **** [TABLE: NULL]: " + configuredTable + ": IS NOT CONFIGURED! ****");
                        continue;
                    }

                    Table table = new Table();
                    table.setLongName(configuredTable);
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

                        // TODO: *** HACK*** Determine if the column is the Sequence Key (SK)...
                        // Since the format per table varies, this may *** NOT *** work for some tables!!
                        //dmr--if (column.getName().endsWith("_SK") && column.getName().contains(table.getLongName())
                        if (column.getName().equals(table.getLongName() + "_SK")

                                || column.getName().equals("STAFF_IN_CASE_OF_EMERG_CONTACT")
                                // TODO: Another Hack
                                //dmr--|| column.getName().equals("PATIENT_DME_SUPPLY_SK") // Column should be name PATIENT_DME_AND_SUPPLY_SK

                                //dmr--Fixed: 11-17-2015: || (table.getLongName().equals("PATIENT_RATE") && column.getName().equals("PATIENT_SK"))

                                //dmr--Fixed: 12-12-2015: || (table.getLongName().equals("PR_OUTPUT") && column.getName().equals("PR_INPUT_SK"))

                                ) {
                            table.setSequenceKeyColumn(column.getName());
                        }
                    }

                    colResultSet.close();

                    configuredTables.add(table);
                }

                return configuredTables;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("%s: %s: %s",
                    getClass().getName(), e.getClass().getName(), e.getMessage()));
        }
        finally {
            try {
                if (tabResultSet != null) {
                    tabResultSet.close();
                }
            }
            catch (Exception e) {
            }

            try {
                if (tabStatement != null) {
                    tabStatement.close();
                }
            }
            catch (Exception e) {
            }
        }

        return null;
    }

    public String getCurrentProcessTable() {
        return currentProcessTable;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public List<String> getTables() {
        return tables;
    }

    private TableManager(Connection connection, PackageManager packageManager) {
        super();

        this.connection = connection;
        this.packageManager = packageManager;

        setVerbosity(true);

        currentIndex = 0;

        tables = new ArrayList<>();

        /**
         * Read the tables that we should process
         */
        String processTables = properties.getProperty("process.tables");

        if (processTables != null && processTables.length() > 0) {
            String[] tablesArray = processTables.split(",");
            for (String table : tablesArray) {

                printStdOut(String.format("INFO: TABLE: [%s]: Configured!", table));

                tables.add(table);
            }
        }
    }

    public static TableManager getInstance(Connection connection, PackageManager packageManager) {
        return new TableManager(connection, packageManager);
    }

    @Override
    protected String propertyResourceName() {
        return "tables.cfg";
    }
}
