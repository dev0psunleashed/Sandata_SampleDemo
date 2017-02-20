package com.sandata.lab.db.oracle.common.model;

/**
 * Date: 8/26/15
 * Time: 3:19 PM
 */

public class FromSelect extends Select {

    public FromSelect(final String name, final String tableName, final String[] columns) {

        super("FROM (SELECT %s FROM %s) %s", name, tableName, columns);
    }
}
