package com.sandata.lab.db.oracle.util;

import com.sandata.lab.db.oracle.model.Column;

import java.util.List;

/**
 * Date: 8/19/15
 * Time: 6:54 AM
 */

public class ColumnUtil {

    public static String Select(final List<Column> columns) {

        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < columns.size() - 1; index++) {

            builder.append(columns.get(index).getName() + ",");
        }

        // Append last column without a comma
        builder.append(columns.get(columns.size() - 1).getName());

        return builder.toString();
    }

    public static String TypFormat(final Column column) {

        if (column.getType().equals("BLOB")) {
            return column.getName() + " " + column.getType();
        }

        if (column.getType().equals("DATE")) {
            return column.getName() + " " + column.getType();
        }

        // NOTE: TIMESTAMP is defined in the DATA_TYPE column as TIMESTAMP (6)
        if (column.getType().contains("TIMESTAMP")) {
            return column.getName() + " " + column.getType();
        }

        if (column.getType().equals("NUMBER")) {

            int scale = Integer.valueOf(column.getScale());

            if (scale != 0) {
                return column.getName() + "\t" + column.getType() +
                        "(" + validatePrecision(column.getPrecision()) + ", " + column.getScale() + ")";
            }
            else {
                return column.getName() + "\t" + column.getType() +
                        "(" + validatePrecision(column.getPrecision()) + ")";
            }
        }

        // VARCHAR2
        return column.getName() +  "\t" + column.getType() + "(" + column.getLength() + " BYTE)";
    }

    public static String TabFormat(final Column column) {

        if (column.getType().equals("DATE")) {
            return column.getName() + "\t" + column.getType();
        }

        if (column.getType().equals("NUMBER")) {

            int scale = Integer.valueOf(column.getScale());

            if (scale != 0) {
                return column.getName() + "\t" + column.getType() +
                        "(" + validatePrecision(column.getPrecision()) + ", " + column.getScale() + ")";
            }
            else {
                return column.getName() + "\t" + column.getType() +
                        "(" + validatePrecision(column.getPrecision()) + ")";
            }
        }

        // VARCHAR2
        return column.getName() + "\t" + column.getType() + "(" + column.getLength() + ")";
    }

    // TODO: For some reason the DATA_PRECISION is null for a NUMBER... ex. PATIENT.PATIENT_DISASTER_LVL
    private static int validatePrecision(final String precision) {

        if (precision == null) {
            return 38;
        }

        try {
            return Integer.valueOf(precision);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return 38;
    }
}
