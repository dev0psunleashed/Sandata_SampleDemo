package com.sandata.lab.db.oracle.model;

/**
 * Date: 8/19/15
 * Time: 6:39 AM
 */

public class Column {

    public static String NAME = "COLUMN_NAME";
    public static String TYPE = "DATA_TYPE";
    public static String LENGTH = "DATA_LENGTH";
    public static String NULLABLE = "NULLABLE";
    public static String PRECISION = "DATA_PRECISION";
    public static String SCALE = "DATA_SCALE";

    public static String ID = "COLUMN_ID";


    private String table;
    private String name;
    private String type;
    private String length;
    private String nullable;
    private String precision;
    private String scale;
    private String id;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
