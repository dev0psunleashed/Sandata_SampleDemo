package com.sandata.lab.db.oracle.model;

/**
 * Date: 8/19/15
 * Time: 4:19 PM
 */

public class Parameter {

    public static String VARCHAR2 = "VARCHAR2";
    public static String NUMBER = "NUMBER";
    public static String CURSOR = "REF_CURSOR";
    public static String DATE = "DATE";
    public static String CHAR = "CHAR";
    public static String STRING_ARRAY = "STRING_ARRAY";

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String toJavaType(final String type) {

        // TODO: May need to extend this

        if (type.equals(Parameter.NUMBER)) {
            return "int";
        }

        if (type.equals(Parameter.VARCHAR2)) {
            return "java.lang.String";
        }

        if (type.equals(Parameter.CURSOR)) {
            return "java.sql.ResultSet";
        }

        if (type.equals(Parameter.STRING_ARRAY)) {
            return "oracle.sql.ARRAY";
        }

        if (type.endsWith("TYP")) {

            return "oracle.sql.STRUCT";
        }

        return "UNKNOWN";
    }
}
