package com.sandata.lab.db.oracle.common.model;

/**
 * Date: 8/26/15
 * Time: 3:20 PM
 */

public class LeftJoinSelect extends Select {

    public LeftJoinSelect(final String name, final String tableName, final String[] columns) {

        super("LEFT JOIN (SELECT %s FROM %s) %s", name, tableName, columns);
    }
}
