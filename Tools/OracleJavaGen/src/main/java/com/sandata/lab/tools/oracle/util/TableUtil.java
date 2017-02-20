package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.model.Column;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.tools.oracle.model.Package;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/19/15
 * Time: 7:02 AM
 */

public class TableUtil {

    public static List<String> GetPrimaryKeys(final Connection connection, final Table table, final String prefix) throws Exception {

        List<String> primaryKeys = new ArrayList<>();

        // Hack-a-rooh
        String tempKey = "fix_me";        // TODO: Some tables are not correct and don't have FK's defined. This should be fixed!

        Statement statement = null;
        try {

            String sql = "SELECT cols.table_name, cols.column_name, cols.position, cons.status, cons.owner\n" +
                "FROM all_constraints cons, all_cons_columns cols\n" +
                "WHERE cols.table_name = '%s'\n" +
                "AND cons.constraint_type = 'P'\n" +
                "AND cons.constraint_name = cols.constraint_name\n" +
                "AND cons.owner = cols.owner\n" +
                "ORDER BY cols.table_name, cols.position";

            sql = String.format(sql, table.getLongName());

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                // Strip the prefix from the key since it is redundant
                String primaryKey = StringUtil.RemovePrefix(resultSet.getString("COLUMN_NAME").toLowerCase(), prefix.toLowerCase());

                if (primaryKey.startsWith("_")) {
                    primaryKey = primaryKey.substring(1);
                }

                // if we are left with only an "id" then add the prefix back in...
                if (primaryKey.equals("id")) {
                    primaryKey = prefix.toLowerCase() + "_" + "id";
                }

                // TODO: Fix Me!!
                tempKey = primaryKey;

                // NOTE: Do not include the id for the table which may not be known by the caller
                String[] parts = primaryKey.split("_");
                // Make sure to include the pk that is the same as the prefix
                if (!parts[0].toLowerCase().equals(prefix.toLowerCase())) {

                    if (table.getLongName().toLowerCase().contains(parts[0])) {
                        continue; // DO NOT INCLUDE primaryKey
                    }
                }

                primaryKeys.add(primaryKey);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("TableUtil: GetPrimaryKeys: " + e.getMessage());
        }
        finally {
            if (statement != null) {
                statement.close();
            }
        }

        // TODO: Fix Me!! This should never be the case!!!
        if (primaryKeys.size() == 0) {
            primaryKeys.add(tempKey);
        }
        //

        return primaryKeys;
    }

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

    public static String CreateOrReplaceTyp(final Table table, String targetPath) throws Exception {

        String typeString = CreateOrReplaceTyp(table);

        if (targetPath != null) {

            String fileName = String.format("%s_T.sql", table.getShortName());
            String filePath = String.format("%s/%s", targetPath, fileName);
            FileUtil.DeleteFile(filePath);
            FileUtil.Write(typeString + ";\n/\n", targetPath, fileName);
        }

        return (typeString + "\n\n");
    }

    public static String CreateOrReplaceTyp(final Table table) throws Exception {

        System.out.println(String.format("**** Creating Table: [%s][%d] ****",
                table.getShortName(), table.getShortName().length()));

        String typ = String.format("CREATE OR REPLACE TYPE %s_T AS OBJECT (\n", table.getShortName());

        for (int i = 0; i < table.size() - 1; i++) {

            Column column = table.getColumn(i);

            typ += ColumnUtil.TypFormat(column) + ",\n";
        }

        Column lastCol = table.getColumn(table.size() - 1);
        typ += ColumnUtil.TypFormat(lastCol) + "\n)";

        return typ;
    }

    public static void CreateOrReplacePackage(Package pkg, String contract, String body, String targetPath) throws Exception {

        if (targetPath != null) {

            StringBuilder pkgData = new StringBuilder();
            pkgData.append(contract + "/\n\n");
            pkgData.append(body + "/\n\n");

            String fileName = String.format("%s.sql", pkg.getName());
            String filePath = String.format("%s/%s", targetPath, fileName);
            FileUtil.DeleteFile(filePath);
            FileUtil.Write(pkgData.toString(), targetPath, fileName);
        }
    }

    public static void PrintTyp(final Table table) throws Exception {

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
