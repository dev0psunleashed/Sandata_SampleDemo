package com.sandata.lab.db.oracle.common.util;

import com.sandata.lab.db.oracle.common.model.FromSelect;
import com.sandata.lab.db.oracle.common.model.LeftJoinSelect;
import oracle.sql.STRUCT;

import java.sql.SQLException;

/**
 * Date: 8/26/15
 * Time: 5:39 PM
 */

public class SQLUtil {

    public static void ValidateExecuteWithFilter(final STRUCT filter,
                                                 final FromSelect fromSelect,
                                                 final LeftJoinSelect leftJoinSelect,
                                                 final String[] whereColumns) throws SQLException {

        if (filter == null) {
            throw new SQLException("SQLUtil.java: executeWithFilter: filter == null");
        }

        if (fromSelect == null) {
            throw new SQLException("OracleQueryHandler.java: executeWithFilter: fromSelect == null");
        }

        if (leftJoinSelect == null) {
            throw new SQLException("OracleQueryHandler.java: executeWithFilter: leftJoinSelect == null");
        }

        // selectJoinStatement is the initial part of the query, the "WHERE" clause is built from the filter that is passed in
        Object[] attribs = filter.getAttributes();

        if (attribs == null || attribs.length == 0) {
            throw new SQLException("OracleQueryHandler.java: executeWithFilter: At least one filter item must be provided!");
        }

        if (whereColumns == null || whereColumns.length == 0) {
            throw new SQLException("OracleQueryHandler.java: executeWithFilter: At least one whereColumn item must be provided!");
        }

        if (whereColumns.length != attribs.length) {
            throw new SQLException(String.format("OracleQueryHandler.java: executeWithFilter: The filter and " +
                            "whereColumn's must match!: [Filter: %d] != [Where Columns: %d]",
                    attribs.length, whereColumns.length));
        }

    }

    public static String[] CompleteColumnNameArray(final FromSelect fromSelect,
                                                   final LeftJoinSelect leftJoinSelect,
                                                   final String[] whereColumns) throws SQLException {

        final String[] completeColumnNameArray = new String[whereColumns.length];
        int index = 0;
        // Verify that the whereColumns exist in either FromSelect or LeftJoinSelect
        for (String column : whereColumns) {

            boolean verified = false;
            // while validating the whereColumns, add the Select name to the column

            if (ColumnUtil.Exists(fromSelect, column)) {

                verified = true;
                completeColumnNameArray[index] = String.format("%s.%s", fromSelect.getName(), column);
            }
            else if (ColumnUtil.Exists(leftJoinSelect, column)) {

                verified = true;
                completeColumnNameArray[index] = String.format("%s.%s", leftJoinSelect.getName(), column);
            }

            if (!verified) {
                throw new SQLException(String.format("OracleQueryHandler.java: executeWithFilter: column: [%s]: " +
                        "Not found in FromSelect/LeftJoinSelect!", column));
            }
        }

        return completeColumnNameArray;
    }
}
