package com.sandata.lab.db.oracle.util;

import com.sandata.lab.db.oracle.model.CustomMethod;
import com.sandata.lab.db.oracle.model.Table;

/**
 * Date: 8/19/15
 * Time: 8:24 AM
 */

public class JavaUtil {

    public static String[] Imports() {

      return new String[] {

              "import com.sandata.lab.db.oracle.common.handler.OracleQueryHandler;",
              "import oracle.sql.STRUCT;",
              "import oracle.sql.ARRAY;",
              "import java.sql.ResultSet;",
              "import java.sql.SQLException;"
      };
    }

    public static String[] Static(final Table table) {

        return new String[] {
            "\tprivate static String TABLE_NAME = \"" + table.getLongName() + "\";",
            "\tprivate static String PRIMARY_KEY_COLUMN_NAME = \"" + table.getSequenceKeyColumn() + "\";"
        };
    }

    public static String CreateCustomExecuteWithFilter(final CustomMethod method) {

        StringBuilder builder = new StringBuilder();

        builder.append("\tpublic static ResultSet ");

        builder.append(method.getMethodName() + "(STRUCT filter) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n",
                method.getClassName(), method.getMethodName()));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\treturn new OracleQueryHandler().executeWithFilter(filter, \"%s\", new String[]{%s});\n\n",
                method.getSelectPattern(),
                method.whereColumnsToString()));

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String GetMethodForPrimaryKeys(final String[] primaryKeyColumns, final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("get");

        builder.append("\tpublic static ResultSet ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(ARRAY params) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\tString selectPattern = \"SELECT %s FROM %s %%s\";\n\n",

                ColumnUtil.Select(table.getColumns()),
                table.getLongName()));

        builder.append("\t\t\t\t\tString whereClause = \"WHERE ");
        for (int index = 0; index < primaryKeyColumns.length - 1; index++) {
            builder.append(primaryKeyColumns[index] + "=? AND ");
        }

        builder.append(primaryKeyColumns[primaryKeyColumns.length - 1]);
        builder.append("=?\";\n\n");

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(selectPattern, whereClause, params);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String GetMethodForPK(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("get");

        builder.append("\tpublic static ResultSet ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(int primaryKey) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s WHERE %%s=?\", TABLE_NAME, PRIMARY_KEY_COLUMN_NAME);\n\n",
                                ColumnUtil.Select(table.getColumns())));

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(sql, new Object[]{primaryKey});\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String InsertMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("insert");

        builder.append("\tpublic static int ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(STRUCT struct) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append("\t\t\t\t\treturn  new OracleQueryHandler().executeInsert(TABLE_NAME, PRIMARY_KEY_COLUMN_NAME, struct);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String UpdateMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("update");

        builder.append("\tpublic static int ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(STRUCT struct) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append("\t\t\t\t\treturn  new OracleQueryHandler().executeUpdate(TABLE_NAME, PRIMARY_KEY_COLUMN_NAME, struct);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String DeleteMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("delete");

        builder.append("\tpublic static int ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(int primaryKey) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append("\t\t\t\t\treturn  new OracleQueryHandler().executeDelete(TABLE_NAME, PRIMARY_KEY_COLUMN_NAME, primaryKey);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n");

        return builder.toString();
    }

    public static String SK(final String tableName, final String skValue) {

        // This is a work around to some tables not following the standard format for SK columns.
        // The new value is mapped in the oracle.cfg (SK.Exceptions) and passed into this function.
        if (skValue != null) {
            return skValue;
        }

        String[] parts = tableName.split("_");
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < parts.length - 1; index++) {

            builder.append(parts[index]);
            builder.append("_");
        }

        if (parts.length == 1) {
            builder.append(parts[0]);
            builder.append("_SK");
        }
        else {
            builder.append("SK");
        }

        if (parts.length > 1) {
            builder.append("_" + parts[parts.length - 1]);
        }

        return builder.toString();
    }

    public static String FirstCharUpper(final String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String CamelCase(String input) {

        String[] parts = input.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + ProperCase(part);
        }

        return camelCaseString;
    }

    public static String ProperCase(String input) {
        return input.substring(0, 1).toUpperCase() +
                input.substring(1).toLowerCase();
    }
}
