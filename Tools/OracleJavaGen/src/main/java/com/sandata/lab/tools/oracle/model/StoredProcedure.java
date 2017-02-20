package com.sandata.lab.tools.oracle.model;

/**
 * Created by dmrutgos on 8/31/2015.
 */
public class StoredProcedure {

    public static String INSERT_TYPE = "INSERT";
    public static String UPDATE_TYPE = "UPDATE";
    public static String DELETE_TYPE = "DELETE";
    public static String GET_TYPE = "GET";
    public static String FIND_TYPE = "FIND";

    private String executeType;

    private Function function;

    private String source;

    public StoredProcedure() {
    }

    public StoredProcedure(String source) {
        this.source = source;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getSource() {
        return source;
    }
}
