/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.db.oracle.common.handler;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Date: 8/15/15
 * Time: 8:02 PM
 */

public class OracleQueryHandler {

    private static DateFormat tzSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    private long getNext(Connection connection, String tableName, String type) throws SQLException {

        // Get the next sequence or version for this table *** tableName_type.NEXTVAL ***
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + tableName + "_" + type + ".NEXTVAL FROM DUAL");
        if (!resultSet.next()) {
            throw new SQLException("OracleQueryHandler.java: getNext: resultSet.next() == false: " +
                    "Can not get next " + tableName + "_" + type + ".NEXTVAL");
        }

        // nextval is the next available index value
        Long val = resultSet.getLong("NEXTVAL");

        // close statement
        statement.close();

        return val;
    }

    private long getNextSequence(Connection connection, String tableName) throws SQLException {

        return getNext(connection, tableName, "SEQ");
    }

    private long getNextVersion(Connection connection, String tableName) throws SQLException {

        return getNext(connection, tableName, "VER");
    }

    public long executeInsert(String tableName,
                              String insertStatement,
                              final int primaryKeyColumnIndex,
                              final int recordTermTimestampIndex,
                              final int currentRecordIndIndex,
                              final int tableIdColumnIndex,
                              final int changeVersionIdIndex,
                              STRUCT data) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeInsert: ");

        if (tableIdColumnIndex == -1) {
            errLog.append("WARN: tableIdColumnIndex == -1: ");
        }

        PreparedStatement pstmt = null;
        Connection connection = null;
        try {
            connection = createOracleConnection(false);
            connection.setAutoCommit(false);

            long nextSequence = getNextSequence(connection, tableName);

            long nextVersion = -1;

            if (changeVersionIdIndex != -1) {
                nextVersion = getNextVersion(connection, tableName);
            }

            pstmt = insertStatement(connection, data, insertStatement, primaryKeyColumnIndex,
                    recordTermTimestampIndex, currentRecordIndIndex,
                    tableIdColumnIndex, changeVersionIdIndex, nextSequence, nextVersion, errLog);

            /**
             * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
             *         or (2) 0 for SQL statements that return nothing
             */
            int resultValue = pstmt.executeUpdate();
            if (resultValue != 1) {
                errLog.append(String.format("[Result: %d]: Expected value should be the number of rows inserted!",
                        resultValue));
                throw new SQLException(errLog.toString());
            }

            connection.commit();

            // Return the SK that was used for the insert!
            return nextSequence;
        } catch (Exception e) {

            if (connection != null) {
                connection.rollback();
            }

            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public long executeUpdate(String tableName,
                              String primaryKeyColumn,
                              final int changeVersionIdIndex,
                              STRUCT data) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeUpdate: ");

        PreparedStatement pstmt = null;
        Connection connection = null;
        try {

            connection = createOracleConnection(false);
            connection.setAutoCommit(false);

            long nextVersion = 0;

            //dmr--Some tables do not have the CHANGE_VERSION_ID column
            if (changeVersionIdIndex >= 0) {
                nextVersion = getNextVersion(connection, tableName);
            }

            //errLog.append("Before dynamicUpdateStatement");

            pstmt = dynamicUpdateStatement(connection, data, tableName, primaryKeyColumn, changeVersionIdIndex, nextVersion, errLog);

            /**
             * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
             *         or (2) 0 for SQL statements that return nothing
             */

            //errLog.append("Before executeUpdate");

            int resultValue = pstmt.executeUpdate();

            //errLog.append("After executeUpdate");

            if (resultValue != 1) {
                errLog.append(String.format("[Result: %d]: Expected value should be the number of rows updated!",
                        resultValue));
                throw new SQLException(errLog.toString());
            }

            // commit the transaction
            connection.commit();

            return resultValue;

        } catch (Exception e) {

            e.printStackTrace();

            if (connection != null) {
                connection.rollback();
            }

            errLog.append("[Exception[1]: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");
            throw new SQLException(errLog.toString());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public long executeDelete(final String tableName, final String primaryKeyColumn, final long primaryKey, final boolean isHistoricalTable) throws SQLException {

        if (isHistoricalTable) {
            return executeDelete(tableName, primaryKeyColumn, primaryKey);
        }

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeDelete: *** DELETE ***");

        PreparedStatement pstmt = null;
        Connection connection = null;
        try {
            connection = createOracleConnection(false);

            // Lock the record before delete
            String selectForUpdate = String.format("SELECT %s FROM %s WHERE %s=%d FOR UPDATE NOWAIT",
                    primaryKeyColumn, tableName, primaryKeyColumn, primaryKey);

            Statement statement = connection.createStatement();
            statement.executeQuery(selectForUpdate);
            statement.close();

            pstmt = connection.prepareStatement(String.format("DELETE FROM %s WHERE %s=?", tableName, primaryKeyColumn));

            pstmt.setLong(1, primaryKey);

            /**
             * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
             *         or (2) 0 for SQL statements that return nothing
             */

            int resultValue = pstmt.executeUpdate();
            if (resultValue != 1) {
                errLog.append(String.format("[Result: %d]: Expected value should be the number of rows deleted!",
                        resultValue));
                throw new SQLException(errLog.toString());
            }

            // commit the transaction
            connection.commit();

            return primaryKey;
        } catch (Exception e) {

            if (connection != null) {
                connection.rollback();
            }

            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public long executeDelete(final String tableName, final String primaryKeyColumn, final long primaryKey) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeDelete: ");

        PreparedStatement pstmt = null;
        Connection connection = null;
        try {
            connection = createOracleConnection(false);

            // Lock the record before delete
            String selectForUpdate = "SELECT REC_TERM_TMSTP, CURR_REC_IND FROM " + tableName + " WHERE " +
                    primaryKeyColumn + "=" + primaryKey + " FOR UPDATE NOWAIT";

            Statement statement = connection.createStatement();
            statement.executeQuery(selectForUpdate);
            statement.close();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String now = dateFormat.format(new java.util.Date());

            pstmt = connection.prepareStatement("UPDATE " + tableName + " SET REC_TERM_TMSTP=TO_DATE('" + now
                    + "', 'YYYY-MM-DD'), CURR_REC_IND = 0 WHERE " + primaryKeyColumn + "=?");

            pstmt.setLong(1, primaryKey);

            /**
             * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
             *         or (2) 0 for SQL statements that return nothing
             */

            int resultValue = pstmt.executeUpdate();
            if (resultValue != 1) {
                errLog.append(String.format("[Result: %d]: Expected value should be the number of rows inserted!",
                        resultValue));
                throw new SQLException(errLog.toString());
            }

            // commit the transaction
            connection.commit();

            return resultValue;
        } catch (Exception e) {

            if (connection != null) {
                connection.rollback();
            }

            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");
            throw new SQLException(errLog.toString());

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResultSet execute(final String selectPattern, final String whereClause, final ARRAY paramsArray) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: execute(selectPattern,whereClause,params): ");

        try {
            Object[] params = (Object[]) paramsArray.getArray();

            if (whereClause == null || whereClause.length() == 0) {
                throw new SQLException("whereClause is NULL or EMPTY!");
            }

            // http://stackoverflow.com/questions/275944/how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
            // Count the number of times '?' appears in the whereClause which MUST match the params passed in.
            int countParams = whereClause.length() - whereClause.replace("?", "").length();
            if (countParams != params.length) {
                errLog.append(String.format("[whereClause: %s][params: %d]", whereClause, params.length));
                throw new SQLException("whereClause does not match params!");
            }

            String sql = String.format(selectPattern, whereClause);
            PreparedStatement pstmt = createOracleConnection(true).prepareStatement(sql);

            int index = 1;
            for (Object object : params) {

                pstmt.setObject(index++, object);
            }

            return pstmt.executeQuery();
        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");

            System.err.println(errLog.toString());

            throw new SQLException(errLog.toString());
        }
    }

    public ResultSet execute(final String sql, final Object[] params) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: execute(sql,params): ");

        //errLog.append("[SQL: " + sql + "]: ");

        try {
            PreparedStatement pstmt = createOracleConnection(true).prepareStatement(sql);

            int index = 1;
            for (Object object : params) {

                pstmt.setObject(index++, object);
            }

            return pstmt.executeQuery();
        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");

            System.err.println(errLog.toString());

            throw new SQLException(errLog.toString());
        }
    }

    /**
     * This method will process a complex select/join statement that uses a filter STRUCT that should dynamically
     * build a WHERE clause for the select and return only records that satisfy the filter.
     *
     * @param filter        The options that will create the WHERE clause. At least one filter item is required.
     * @param selectPattern Complete SQL statement WITHOUT the WHERE clause which will be injected.
     * @param whereColumns  The columns that we will be filtering on. filter[0] must equal whereColumns[0].
     * @return a ResultSet of all the columns that were selected.
     * @throws SQLException EXAMPLE:
     *                      <p>
     *                      SELECT t1.PATIENT_SK,t2.PATIENT_CONTACT_SK_DETL,t1.PATIENT_ID,t1.PATIENT_ID_INS_NUM,t1.PATIENT_FIRST_NAME,
     *                      t1.PATIENT_LAST_NAME,t1.PATIENT_MIDDLE_NAME,t2.PATIENT_HOME_PHONE,t1.PATIENT_DOB,t2.PATIENT_ADDR1,t2.PATIENT_ADDR2,
     *                      t2.PATIENT_APT_NUM,t2.PATIENT_CITY,t2.PATIENT_STATE,t2.PATIENT_ZIP4,t1.REC_TERM_TMSTP,t1.CURR_REC_IND,t2.REC_TERM_TMSTP,t2.CURR_REC_IND
     *                      FROM (SELECT PATIENT_SK,PATIENT_ID,PATIENT_FIRST_NAME,PATIENT_LAST_NAME,PATIENT_MIDDLE_NAME,PATIENT_DOB,PATIENT_ID_INS_NUM,CURR_REC_IND,REC_TERM_TMSTP FROM PATIENT) t1
     *                      LEFT JOIN (SELECT PATIENT_CONTACT_SK_DETL,PATIENT_ID,PATIENT_HOME_PHONE,PATIENT_ADDR1,PATIENT_ADDR2,
     *                      PATIENT_APT_NUM,PATIENT_CITY,PATIENT_STATE,PATIENT_ZIP4,CURR_REC_IND,REC_TERM_TMSTP FROM PATIENT_CONTACT_DETL) t2
     *                      ON t1.PATIENT_ID = t2.PATIENT_ID
     *                      WHERE t1.PATIENT_FIRST_NAME like 'Test%'
     *                      AND t1.PATIENT_ID = t2.PATIENT_ID
     *                      -- Filter out deleted records
     *                      AND (TO_CHAR(t1.REC_TERM_TMSTP, 'DD-MON-YY') = '31-DEC-99' AND t1.CURR_REC_IND = 1)
     *                      AND (TO_CHAR(t2.REC_TERM_TMSTP, 'DD-MON-YY') = '31-DEC-99' AND t2.CURR_REC_IND = 1);
     */
    public ResultSet executeWithFilter(final STRUCT filter,
                                       final String selectPattern,
                                       final String[] whereColumns) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeWithFilter: ");

        try {

            String whereClause = " WHERE ";

            Object[] attribs = filter.getAttributes();

            for (int index = 0; index < attribs.length; index++) {

                String filterItem = (String) attribs[index];
                if (filterItem != null) {
                    whereClause += whereColumns[index] + " LIKE ? AND ";
                }
            }

            String sql = String.format(selectPattern, whereClause);

            //int findWhereClause = sql.indexOf("WHERE");
            //errLog.append(String.format("[SQL: --> %s]", sql.substring(findWhereClause, sql.length() - 1)));

            PreparedStatement pstmt = createOracleConnection(true).prepareStatement(sql);

            int paramIndex = 1;
            for (Object filterItemObj : filter.getAttributes()) {

                if (filterItemObj != null) {

                    //errLog.append(String.format("[%s][paramIndex: %d]", filterItemObj, paramIndex));
                    pstmt.setObject(paramIndex++, filterItemObj + "%"); // add a % after the LIKE
                }
            }

            return pstmt.executeQuery();
        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");

            System.err.println(errLog.toString());

            throw new SQLException(errLog.toString());
        }

    }

    public ResultSet executeWithFilter(final STRUCT filter,
                                       final String selectPattern,
                                       final String[] whereColumns,
                                       final String orderByColumn,
                                       final int page,
                                       final int pageSize,
                                       final String orderByDirection) throws SQLException {

        StringBuilder errLog = new StringBuilder("OracleQueryHandler.java: executeWithFilter: ");

        int toRow = page * pageSize;
        int fromRow = toRow - (pageSize - 1);

        try {

            String sql = selectPattern.replace("__ORDER_BY__", orderByColumn);
            sql = sql.replace("__ORDER_BY_DIRECTION__", orderByDirection);
            sql = sql.replace("__ROW_NUM_FROM__", String.valueOf(fromRow));
            sql = sql.replace("__ROW_NUM_TO__", String.valueOf(toRow));

            Object[] attribs = filter.getAttributes();

            StringBuilder filterItems = new StringBuilder();

            for (int index = 0; index < attribs.length; index++) {

                Object filterItem = attribs[index];
                if (filterItem != null) {

                    filterItems.append(String.format("UPPER(%s) LIKE ? AND ", whereColumns[index]));

                    errLog.append(String.format("FilterItem: >> %s <<", filterItems.toString()));
                }
            }

            sql = sql.replace("__FILTER_ITEMS__", filterItems.toString());

            PreparedStatement pstmt = createOracleConnection(true).prepareStatement(sql);

            int paramIndex = 1;
            for (Object filterItemObj : filter.getAttributes()) {

                if (filterItemObj != null) {

                    errLog.append(String.format("[%s][paramIndex: %d]", filterItemObj, paramIndex));

                    pstmt.setObject(paramIndex++, filterItemObj.toString().toUpperCase() + "%"); // add a % after the LIKE
                }
            }

            return pstmt.executeQuery();
        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");

            System.err.println(errLog.toString());

            throw new SQLException(errLog.toString());
        }

    }

    public ResultSet execute(final String sql) throws SQLException {

        StringBuilder errLog = new StringBuilder(String.format("OracleQueryHandler.java: execute(%s): ", sql));

        try {
            return createOracleConnection(true).createStatement().executeQuery(sql);
        } catch (Exception e) {
            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");

            System.err.println(errLog.toString());

            throw new SQLException(errLog.toString());
        }
    }

    private static PreparedStatement insertStatement(
            final Connection connection,
            final STRUCT data,
            final String insertStatement,
            final int primaryKeyColumnIndex,
            final int recordTermTimestampIndex,
            final int currentRecordIndIndex,
            final int tableIdColumnIndex,
            final int changeVersionIdIndex,
            final long nextSequence,
            final long nextVersion,
            final StringBuilder errLog)
            throws SQLException {

        try {

            PreparedStatement pstmt = connection.prepareStatement(insertStatement);

            Object[] attribs = data.getAttributes();

            for (int i = 1; i <= attribs.length; i++) {

                //errLog.append(i + ",");

                if (primaryKeyColumnIndex == i) {
                    pstmt.setLong(i, nextSequence);
                } else if (changeVersionIdIndex == i) {
                    pstmt.setLong(i, nextVersion); // Initial Version
                } else if (recordTermTimestampIndex == i) {

                    // DEFAULT: RECORD_END_TMSTP: 31-12-9999
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
                    Date openEndDate = dateFormat.parse("31-12-9999 00:00:00");
                    pstmt.setObject(i, new Timestamp(openEndDate.getTime()));
                } else if (currentRecordIndIndex == i) {

                    // DEFAULT: CURRENT_RECORD_IND: 1
                    pstmt.setObject(i, 1);
                } else if (tableIdColumnIndex == i) {
                    //dmr--GEOR-2766: We will be auto generating the table id if it isn't provided on insert.
                    //dmr--NOTE: Staff/Patient ID's are set the user and should be validated on the F/E.
                    if (attribs[i - 1] == null) {
                        pstmt.setObject(i, UUID.randomUUID().toString());
                    } else {
                        pstmt.setObject(i, attribs[i - 1]);
                    }
                } else {

                    if (attribs[i - 1] instanceof Date) {

                        Date date = (Date) attribs[i - 1];

                        // Convert to UTC before insert
                        TimeZone utcTZ = TimeZone.getTimeZone("UTC");
                        tzSimpleDateFormat.setTimeZone(utcTZ);

                        String utcDateString = tzSimpleDateFormat.format(date);
                        Calendar calendar = Calendar.getInstance(utcTZ);
                        pstmt.setObject(i, new Timestamp(tzSimpleDateFormat.parse(utcDateString).getTime()));
                    } else {
                        pstmt.setObject(i, attribs[i - 1]);
                    }
                }
            }

            return pstmt;

        } catch (SQLException sqle) {
            throw new SQLException("OracleQueryHandler.java: insertStatement: SQLException: " + sqle.getMessage());
        } catch (Exception e) {
            throw new SQLException("OracleQueryHandler.java: insertStatement: Exception! ");
        }
    }

    private static PreparedStatement dynamicUpdateStatement(
            final Connection connection,
            final STRUCT data,
            String tableName,
            String primaryKeyColumn,
            final int changeVersionIdIndex,
            final long nextVersion,
            final StringBuilder errLog) throws SQLException {

        //errLog.append("dynamicUpdateStatement: ");
        //errLog.append(tableName + ": " + primaryKeyColumn + ": ");

        StringBuilder sql = new StringBuilder();
        Statement statement;
        try {
            statement = connection.createStatement();

            //errLog.append("After createStatement");

            ResultSet resultSet =
                    statement.executeQuery(String.format("SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE " +
                            "TABLE_NAME = '%s' ORDER BY COLUMN_ID ASC", tableName));

            //errLog.append("After executeQuery");

            sql.append(String.format("UPDATE %s SET ", tableName));

            // load all the columns into a list so we know the amount of columns that will be processed
            List<String> columns = new ArrayList<String>();

            int skIndexColumn = -1;
            int columnIndex = 0;
            while (resultSet.next()) {

                columnIndex++;

                String columnName = resultSet.getObject(1).toString();

                // since the position of the PK column is not guaranteed (ie first position) we need to set the index
                // so we know when to set the PK value

                // SKIP PK column and do not add it to the columns list
                if (columnName.equals(primaryKeyColumn)) {
                    skIndexColumn = columnIndex;
                }

                columns.add(columnName);
            }

            if (columns.size() == 0) {
                throw new SQLException("OracleQueryHandler.java: dynamicUpdateStatement: columns.size == 0: " +
                        "Could not get column names from all_tab_columns table!");
            }

            // close statement
            statement.close();

            // Select the row before attempting to update to make sure it is locked by this process
            String selectForUpdate = "SELECT ";

            for (int index = 0; index < columns.size() - 1; index++) {

                String columnName = columns.get(index);

                // Create list of columns that may be updated...
                selectForUpdate += columnName + ", ";

                // append column names to SQL string
                sql.append(columnName);
                sql.append("=?, ");
            }

            String columnName = columns.get(columns.size() - 1);

            sql.append(columnName);
            sql.append(String.format("=? WHERE %s=?", primaryKeyColumn));

            Object[] attribs = data.getAttributes();

            Object primaryKey = attribs[skIndexColumn - 1];

            selectForUpdate += columnName + " FROM " + tableName + " WHERE " +
                    primaryKeyColumn + "=" + primaryKey.toString() + " FOR UPDATE NOWAIT";
            // Run "SELECT FOR UPDATE" to ** LOCK ** row
            statement = connection.createStatement();

            //errLog.append("Before executeQuery2 col: [" + columns.size() + "] ");

            statement.executeQuery(selectForUpdate);

            //errLog.append("After executeQuery2 idx: [" + columnIndex + "]");

            statement.close();
            //

            //errLog.append("[SQL: " + sql.toString() + "]: ");

            PreparedStatement pstmt = connection.prepareStatement(sql.toString());

            int currentIndex = 0;
            for (; currentIndex < attribs.length; ) {

                currentIndex++;

                //errLog.append("currentIndex: [" + currentIndex + "]: ");

                //dmr--06-03-2016: Update should increment the version
                if (currentIndex == changeVersionIdIndex) {
                    pstmt.setLong(currentIndex, nextVersion);
                } else {
                    pstmt.setObject(currentIndex, attribs[currentIndex - 1]);
                }
            }

            currentIndex++;

            //errLog.append("currentIndex: [" + currentIndex + "]: [PK: " + primaryKey + "]");

            // since we are skipping the setting of the primary key, we need to increment the index by 1
            // when we are finally ready to set the primary key.
            pstmt.setObject(currentIndex, primaryKey);

            return pstmt;

        } catch (Exception e) {

            e.printStackTrace();

            errLog.append("[Exception: " + e.getClass().getName() + ": [Message: " +
                    e.getMessage() + "]");
            throw new SQLException(errLog.toString());
        }
    }

    private Connection createOracleConnection(final boolean asRefCursor) throws SQLException {

        try {
            Connection connection = DriverManager.getConnection("jdbc:default:connection:");
            ((OracleConnection) connection).setCreateStatementAsRefCursor(asRefCursor);
            connection.setAutoCommit(true);
            return connection;
        } catch (Exception e) {
            String errMsg = "OracleQueryHandler.java: createOracleConnection: " + e.getClass().getName() + ": " + e.getMessage();
            System.err.println(errMsg);
            throw new SQLException(errMsg);
        }
    }
}
