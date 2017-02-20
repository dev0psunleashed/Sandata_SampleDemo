package com.sandata.lab.db.oracle.common.model;

import com.sandata.lab.db.oracle.common.util.ColumnUtil;

/**
 * Date: 8/26/15
 * Time: 3:41 PM
 */

public abstract class Select {

    private final String pattern;
    private final String name;
    private final String tableName;
    private final String sql;
    private final String[] columns;

    protected Select(final String pattern, final String name, final String tableName, final String[] columns) {

        this.pattern = pattern;
        this.name = name;
        this.tableName = tableName;
        this.columns = columns;

        this.sql = String.format(this.pattern,
                ColumnUtil.CommaDelimitedString(this.columns),
                this.tableName,
                this.name
        );
    }

    public String getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumns() {
        return columns;
    }

    public String getSql() {
        return sql;
    }
}
