package com.sandata.lab.db.oracle.common.util;

import com.sandata.lab.db.oracle.common.model.FromSelect;
import com.sandata.lab.db.oracle.common.model.LeftJoinSelect;
import com.sandata.lab.db.oracle.common.model.Select;

/**
 * Date: 8/26/15
 * Time: 3:37 PM
 */

public class ColumnUtil {

    public static boolean Exists(final Select select, final String columnName) {

        for (String column : select.getColumns()) {

            if(column.equals(columnName)) {
                return true;
            }
        }

        return false;
    }

    public static String SelectColumns(final FromSelect fromSelect, final LeftJoinSelect leftJoinSelect) {

        StringBuilder builder = new StringBuilder("SELECT ");

        for (String column : fromSelect.getColumns()) {
            builder.append(String.format("%s.%s,", fromSelect.getName(), column));
        }

        int columnsCount = leftJoinSelect.getColumns().length;
        String[] columns = leftJoinSelect.getColumns();
        for (int index = 0; index < columnsCount - 1; index++) {
            String column = columns[index];
            builder.append(String.format("%s.%s,", leftJoinSelect.getName(), column));
        }

        // last column does have a comma
        String column = columns[columnsCount - 1];
        builder.append(String.format("%s.%s", leftJoinSelect.getName(), column));

        return builder.toString();
    }

    public static String CommaDelimitedString(final String[] columns) {

        // Generate a comma separated list of column names
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < columns.length - 1; index++) {

            String column = columns[index];
            builder.append(column + ",");
        }

        String column = columns[columns.length - 1];
        builder.append(column);

        return builder.toString();
    }
}
