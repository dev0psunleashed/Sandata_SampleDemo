package com.sandata.lab.db.oracle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/26/15
 * Time: 8:04 PM
 */

public class CustomMethod {

    private String className;
    private String methodName;
    private String selectPattern;
    private CustomType customType;

    private List<String> whereColumns;

    public String whereColumnsToString() {

        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < whereColumns.size() - 1; index++) {
            builder.append(String.format("\"%s\",", whereColumns.get(index)));
        }

        builder.append(String.format("\"%s\"", whereColumns.get(whereColumns.size() - 1)));

        return builder.toString();
    }

    public void addWhereColumn(String column) {
        if (whereColumns == null) {
            whereColumns = new ArrayList<String>();
        }

        whereColumns.add(column);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSelectPattern() {
        return selectPattern;
    }

    public void setSelectPattern(String selectPattern) {
        this.selectPattern = selectPattern;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }
}
