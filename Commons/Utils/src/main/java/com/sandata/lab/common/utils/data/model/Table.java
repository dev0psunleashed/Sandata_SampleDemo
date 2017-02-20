package com.sandata.lab.common.utils.data.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 8/19/15
 * Time: 6:59 AM
 */
public class Table extends BaseObject {

    private static final long serialVersionUID = 1L;

    private static Map<String, String> historicalColumnMap;
    static {
        historicalColumnMap = new HashMap<>();
        historicalColumnMap.put("CURR_REC_IND", null);
        historicalColumnMap.put("REC_TERM_TMSTP", null);
    }

    private String longName;
    private String shortName;
    private String sequenceKeyColumn;
    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public int size() {

        return (columns == null) ? 0 : columns.size();
    }

    public Column getColumn(final int index) {
        if (columns == null || columns.size() == 0) {
            return null;
        }

        return columns.get(index);
    }

    public void addColumn(final Column column) {

        if (columns == null) {
            columns = new ArrayList<>();
        }

        columns.add(column);
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSequenceKeyColumn() {

        sequenceKeyColumn = "UNKNOWN_SK"; // Signifies that the expected sequence column name is not set for this table

        // All tables sequence column should be named TABLE_NAME_SK
        String seqColumnName = getLongName() + "_SK";
        for (Column column : columns) {
            if (column.getName().equals(seqColumnName)) {
                sequenceKeyColumn = seqColumnName;
                break;
            }
        }

        return sequenceKeyColumn;
    }

    //TODO: 5/5/16: No longer needed: Refactor me!
    public void setSequenceKeyColumn(String sequenceKeyColumn) {
        this.sequenceKeyColumn = sequenceKeyColumn;
    }

    public int primaryKeyIndex() {

        int count = 1;
        for (Column column : columns) {

            String columnName = column.getName();
            if (columnName.equals(getSequenceKeyColumn())) {
                return count;
            }

            count++;
        }

        return -1;

    }

    public int recordTermTimestampIndex() {

        int count = 1;
        for (Column column : columns) {

            if (column.getName().equals("REC_TERM_TMSTP")) {
                return count;
            }

            count++;
        }

        return -1;
    }

    public int currentRecordIndIndex() {

        int count = 1;
        for (Column column : columns) {

            if (column.getName().equals("CURR_REC_IND")) {
                return count;
            }

            count++;
        }

        return -1;
    }

    public int changeVersionIdIndex() {

        int count = 1;
        for (Column column : columns) {

            if (column.getName().equals("CHANGE_VERSION_ID")) {
                return count;
            }

            count++;
        }

        return -1;
    }

    //dmr--GEOR-2766: We will be auto generating the table id if it isn't provided on insert.
    public int tableIdColumnIndex() {

        String tableColumnName = this.longName + "_ID";
        int count = 1;
        for (Column column : columns) {

            if (column.getName().equals(tableColumnName)) {
                return count;
            }

            count++;
        }

        return -1;
    }

    public String insertStatement() {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + getLongName() + "(");
        for (int index = 0; index < columns.size() - 1; index++) {

            // append column names to SQL string
            sql.append(columns.get(index).getName());
            sql.append(",");
        }

        sql.append(columns.get(columns.size() - 1).getName());
        sql.append(") VALUES (");

        for (int index = 0; index < columns.size() - 1; index++) {
            sql.append("?,");
        }

        sql.append("?)");

        return sql.toString();
    }

    public boolean hasBsnEntColumn() {

        if (columns == null) {
            return false;
        }

        for (Column column : columns) {
            if (column.getName().equals("BE_ID")) {
                return true;
            }
        }

        return false;
    }

    public boolean isHistorical() {

        int historicalColumnCount = 0;  // Increment when a historical column is found

        for (Column column : columns) {
            if (historicalColumnCount == 2)
                break;

            if (historicalColumnMap.containsKey(column.getName())) {
                historicalColumnCount++;
            }
        }

        return (historicalColumnCount == 2);
    }
}
