package com.sandata.lab.tools.oracle.util;

import com.google.common.base.CaseFormat;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.model.CustomMethod;

import java.sql.Connection;
import java.sql.Statement;

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
            "\tprivate static String SEQUENCE_KEY_COLUMN_NAME = \"" + table.getSequenceKeyColumn() + "\";",
            "\tprivate static int PRIMARY_KEY_INDEX = " + table.primaryKeyIndex() + ";",
            "\tprivate static int REC_TERM_TMSTP_INDEX = " + table.recordTermTimestampIndex() + ";",
            "\tprivate static int CURR_REC_IND_INDEX = " + table.currentRecordIndIndex() + ";",
            "\tprivate static int TABLE_ID_COLUMN_INDEX = " + table.tableIdColumnIndex() + ";",
            "\tprivate static int CHANGE_VERSION_ID_INDEX = " + table.changeVersionIdIndex() + ";",
            "\tprivate static String INSERT_STATEMENT = \"" + table.insertStatement() + "\";"
        };
    }

    public static String CreateCustomExecuteWithFilter(final CustomMethod method) {

        StringBuilder builder = new StringBuilder();

        builder.append("\tpublic static ResultSet ");

        builder.append(method.getMethodName() + "(STRUCT filter, String orderByColumn, int page, int pageSize, String orderByDirection) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n",
                method.getClassName(), method.getMethodName()));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\treturn new OracleQueryHandler().executeWithFilter(filter, \"%s\", new String[]{%s}, orderByColumn, page, pageSize, orderByDirection);\n\n",
                method.getSelectPattern(),
                method.whereColumnsToString()));

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String CustomSQLMethod(CustomMethod customMethod) {

        StringBuilder builder = new StringBuilder();

        builder.append("\tpublic static ResultSet ");

        builder.append(customMethod.getMethodName() + "(ARRAY params) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n",
                customMethod.getClassName(), customMethod.getMethodName()));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\tString selectPattern = \"%s\";\n\n", customMethod.getSelectPattern()));

        builder.append("\t\t\t\t\tString whereClause = \"");
        for (int index = 0; index < customMethod.getWhereColumns().size() - 1; index++) {
            builder.append(customMethod.getWhereColumns().get(index) + "=? AND ");
        }

        builder.append(customMethod.getWhereColumns().get(customMethod.getWhereColumns().size() - 1));
        builder.append("=?\";\n\n");

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(selectPattern, whereClause, params);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String GetMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("get");

        builder.append("\tpublic static ResultSet ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("() throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        if (isHistoricalTable(table)) {

            builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s WHERE (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')\", TABLE_NAME);\n\n",
                    ColumnUtil.Select(table.getColumns())));
        }
        else {
            builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s\", TABLE_NAME);\n\n",
                    ColumnUtil.Select(table.getColumns())));
        }

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(sql);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

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

        // Do not return deleted records!
        if (isHistoricalTable(table)) {
            for (int index = 0; index < primaryKeyColumns.length; index++) {
                builder.append(primaryKeyColumns[index] + "=? AND ");
            }

            builder.append("(TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')\";\n\n");
        }
        else {

            for (int index = 0; index < primaryKeyColumns.length - 1; index++) {
                builder.append(primaryKeyColumns[index] + "=? AND ");
            }

            builder.append(primaryKeyColumns[primaryKeyColumns.length - 1] + "=?\";\n\n");
        }

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(selectPattern, whereClause, params);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

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
        builder.append("(long primaryKey) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        if (isHistoricalTable(table)) {

            builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s WHERE %%s=? AND (TO_CHAR(REC_TERM_TMSTP, 'YYYY-MM-DD') = '9999-12-31' AND CURR_REC_IND = '1')\", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);\n\n",
                    ColumnUtil.Select(table.getColumns())));
        }
        else {
            builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s WHERE %%s=?\", TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME);\n\n",
                    ColumnUtil.Select(table.getColumns())));
        }


        builder.append("\t\t\t\t\treturn new OracleQueryHandler().execute(sql, new Object[]{primaryKey});\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String InsertMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("insert");

        builder.append("\tpublic static long ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(STRUCT struct) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().executeInsert(TABLE_NAME, INSERT_STATEMENT, PRIMARY_KEY_INDEX, REC_TERM_TMSTP_INDEX, CURR_REC_IND_INDEX, TABLE_ID_COLUMN_INDEX, CHANGE_VERSION_ID_INDEX, struct);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String UpdateMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("update");

        builder.append("\tpublic static long ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(STRUCT struct) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append("\t\t\t\t\treturn new OracleQueryHandler().executeUpdate(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, CHANGE_VERSION_ID_INDEX, struct);\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String DeleteMethod(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("delete");

        builder.append("\tpublic static long ");
        for (String part : parts) {
            methodName.append(FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(long primaryKey) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        if (isHistoricalTable(table)) {
            builder.append("\t\t\t\t\treturn  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, true);\n\n");
        }
        else {
            builder.append("\t\t\t\t\treturn  new OracleQueryHandler().executeDelete(TABLE_NAME, SEQUENCE_KEY_COLUMN_NAME, primaryKey, false);\n\n");
        }

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

        return builder.toString();
    }

    public static String GetLookupWithBsnEnt(final String className, final Table table) {

        String[] parts = table.getShortName().split("_");
        StringBuilder builder = new StringBuilder();

        StringBuilder methodName = new StringBuilder("get");

        builder.append("\tpublic static ResultSet ");
        for (String part : parts) {
            methodName.append(JavaUtil.FirstCharUpper(part.toLowerCase()));
        }

        builder.append(methodName);
        builder.append("(String bsnEntId) throws SQLException {\n\n");

        builder.append(String.format("\t\t\tStringBuilder errLog = new StringBuilder(\"%s: %s: \");\n\n", className, methodName));
        builder.append("\t\t\ttry {\n\n");

        builder.append(String.format("\t\t\t\t\tString sql = String.format(\"SELECT %s FROM %%s WHERE BE_ID = ?\", TABLE_NAME);\n\n",
                ColumnUtil.Select(table.getColumns())));

        builder.append("\t\t\t\t\treturn  new OracleQueryHandler().execute(sql, new Object[]{bsnEntId});\n\n");

        builder.append("\t\t\t} catch (Exception e) {\n");

        builder.append("\t\t\t\t\terrLog.append(\"[Exception: \" + e.getClass().getName() + \": [Message: \" + e.getMessage() + \"]\");\n");
        builder.append("\t\t\t\t\tthrow new SQLException(errLog.toString());\n\n");

        builder.append("\t\t\t}\n");

        // End Method
        builder.append("\t}\n\n");

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

    public static String CamelCaseSmallFirstLetter(String input) {

        String camelCase = CamelCase(input);

        return camelCase.substring(0, 1).toLowerCase() + camelCase.substring(1, camelCase.length());
    }

    public static String ProperCase(String input) {
        return input.substring(0, 1).toUpperCase() +
                input.substring(1).toLowerCase();
    }

    public static String UnderscoresToCamelUppercase(String target) {

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, target);
    }

    private static boolean isHistoricalTable(final Table table) {
        return table.isHistorical();
    }

    public static void DropJava(Connection connection, String className) throws Exception {

        Statement dropObjectStatement = connection.createStatement();
        dropObjectStatement.setEscapeProcessing(false);
        dropObjectStatement.execute(String.format("DROP JAVA CLASS \"%s\"", className));
        dropObjectStatement.close();
    }
}
