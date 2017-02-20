package com.sandata.lab.db.oracle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/19/15
 * Time: 6:59 AM
 */

public class Table {

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
            columns = new ArrayList<Column>();
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
        return sequenceKeyColumn;
    }

    public void setSequenceKeyColumn(String sequenceKeyColumn) {
        this.sequenceKeyColumn = sequenceKeyColumn;
    }
}
