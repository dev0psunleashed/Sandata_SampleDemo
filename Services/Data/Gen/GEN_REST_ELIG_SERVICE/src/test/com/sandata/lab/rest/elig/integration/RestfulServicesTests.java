package com.sandata.lab.rest.elig.integration;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.elig.RestfulServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/9/16
 * Time: 1:30 AM
 */

public abstract class RestfulServicesTests extends BaseIntegrationTest {

    private final int MAX_TABLE_NAME_SIZE = 27;

    private final AbbreviationUtil abbreviationUtil;

    private List<String> tablesThatAreTooLong = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

    protected final String MODEL_PACKAGE = "com.sandata.lab.data.model.dl.model";

    protected RestfulServices services;

    protected List<Table> tables() {
        return this.tables;
    }

    protected List<String> tablesThatAreTooLong() {
        return this.tablesThatAreTooLong;
    }

    protected AbbreviationUtil abbreviationUtil() {
        return this.abbreviationUtil;
    }

    protected String className(String entity) {
        return String.format("%s.%s", MODEL_PACKAGE, entity);
    }

    protected Object createEntity(String entity) throws Exception {
        return Class.forName(className(entity)).getConstructor().newInstance();
    }

    protected RestfulServicesTests(int dbType) throws SandataRuntimeException {
        super(dbType);

        if (schemaOwner() == null) {
            throw new SandataRuntimeException("ERROR: Table Owner can not be null!");
        }

        if (schemaOwner().toUpperCase().equals("COREDATA")) {
            abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);
        } else if (schemaOwner().toUpperCase().equals("METADATA")) {
            abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.METADATA_TYPE);
        } else {
            throw new SandataRuntimeException(String.format("ERROR: UNKNOWN Table Owner: %s", schemaOwner()));
        }
    }

    protected abstract String oraclePackage();

    protected abstract String schemaOwner();

    protected abstract String tableFilter();

    @Override
    protected void onSetup() throws Exception {
        services = new RestfulServices();
        initTables();
    }

    private void initTables() throws Exception {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = openConnection();
            connection.setAutoCommit(true);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(tableFilter());
            processResultSet(connection, resultSet);

            if (tablesThatAreTooLong.size() > 0) {

                System.out.println(String.format("***** WARNING: There are %d tables that are greater than the " +
                        "[MAX_TABLE_NAME_SIZE=%d] *****", tablesThatAreTooLong.size(), MAX_TABLE_NAME_SIZE));
                System.out.println("*** Tables Are Greater Than [" + MAX_TABLE_NAME_SIZE + "] ***\n");
                for (String tb : tablesThatAreTooLong) {
                    System.out.println("[" + tb.length() + "]" + tb + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();

                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        } finally {

            // Close the ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Statement
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

            // Close the Connection
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    private void checkTableSize(String tableName) {
        if (tableName.length() > MAX_TABLE_NAME_SIZE) {
            tablesThatAreTooLong.add(tableName);
        }
    }

    private void processResultSet(Connection connection, ResultSet resultSet) throws Exception {

        int count = 0;
        StringBuilder builder = new StringBuilder();

        while (resultSet.next()) {

            String tableName = resultSet.getString("TABLE_NAME");
            Table table = new Table();
            table.setLongName(tableName);

            columnsForTable(connection, table);
            tables.add(table);
            checkTableSize(tableName);
        }

        for (int index = 0; index < tables.size() - 1; index++) {

            count++;
            Table table = tables.get(index);
            builder.append(table.getLongName() + ",");
        }

        count++;

        Table table = tables.get(tables.size() - 1);
        builder.append(table.getLongName());

        System.out.println("[" + count + "]" + builder.toString() + "\n\n");
    }

    private void columnsForTable(Connection connection, Table table) throws Exception {

        Statement colStatement = connection.createStatement();

        final String colSql = String.format("SELECT * FROM ALL_TAB_COLUMNS WHERE OWNER='%s' AND " +
                "TABLE_NAME='" + table.getLongName() + "' ORDER BY COLUMN_ID", schemaOwner());

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
    }
}
