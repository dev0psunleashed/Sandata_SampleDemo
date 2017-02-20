package com.sandata.lab.db.oracle.util;

import com.sandata.lab.db.oracle.model.Column;
import com.sandata.lab.db.oracle.model.Table;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Date: 8/19/15
 * Time: 7:02 AM
 */

public class TableUtil {

    public static void DropType(final Connection connection, final String type) throws Exception {

        Statement dropTabTypeStatement = null;
        try {
            dropTabTypeStatement = connection.createStatement();
            dropTabTypeStatement.execute(String.format("DROP TYPE %s", type));
        } catch (Exception e) {
            // This is OK as long as the TAB type didn't exist in the first place
            if (e.getMessage().contains("ORA-04043")) {
                System.out.println("INFO: " + type + ": Did not exist in the database!");
            } else {
                // oops what happend?
                throw new Exception("UNEXPECTED ERROR: " + e.getClass().getName() + ": " + e.getMessage());
            }
        } finally {
            if (dropTabTypeStatement != null) {
                dropTabTypeStatement.close();
            }
        }
    }

    public static String CreateOrReplaceTyp(final Table table) {

        System.out.println(String.format("**** Creating Table: [%s][%d] ****",
                table.getShortName(), table.getShortName().length()));

        String typ = String.format("CREATE OR REPLACE TYPE %s_TYP AS OBJECT (\n", table.getShortName());

        for (int i = 0; i < table.size() - 1; i++) {

            Column column = table.getColumn(i);

            typ += ColumnUtil.TypFormat(column) + ",\n";
        }

        Column lastCol = table.getColumn(table.size() - 1);
        typ += ColumnUtil.TypFormat(lastCol) + "\n)\n\n";

        return typ;
    }

    public static void PrintTyp(final Table table) {

        System.out.println(TableUtil.CreateOrReplaceTyp(table));
    }

    public static void Print(final Table table) {

        System.out.println(table.getShortName());

        for (int i = 0; i < table.size() - 1; i++) {

            Column column = table.getColumn(i);

            System.out.println("\t" + ColumnUtil.TabFormat(column) + ",");
        }

        Column lastCol = table.getColumn(table.size() - 1);
        System.out.println("\t" + ColumnUtil.TabFormat(lastCol));
    }
}
