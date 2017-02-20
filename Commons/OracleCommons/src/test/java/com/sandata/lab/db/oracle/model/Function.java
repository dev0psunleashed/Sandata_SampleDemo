package com.sandata.lab.db.oracle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/19/15
 * Time: 4:17 PM
 */

public class Function {

    public static String FUNCTION = "FUNCTION";

    private String className;
    private Table table;

    private String methodName;
    private String returnType;
    private List<Parameter> parameters;

    private PLSQLScript testSql;

    public void addParameter(final Parameter parameter) {
        if (parameters == null) {
            parameters = new ArrayList<Parameter>();
        }

        parameters.add(parameter);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public PLSQLScript getTestSql() {
        return testSql;
    }

    public void setTestSql(PLSQLScript testSql) {
        this.testSql = testSql;
    }

    // TODO: Refactor to decorator pattern
    public String toPackage() {

        StringBuilder packageFunction = new StringBuilder();
        List<Parameter> parameters = getParameters();

        if (parameters != null) {

            packageFunction.append(Function.FUNCTION + " " + getMethodName() + "(");

            for (int index = 0; index < parameters.size() - 1; index++) {
                Parameter parameter = getParameters().get(index);
                packageFunction.append(String.format("%s %s,", parameter.getName(), parameter.getType()));
            }

            Parameter parameter = parameters.get(parameters.size() - 1);
            packageFunction.append(String.format("%s %s", parameter.getName(), parameter.getType()));
            packageFunction.append(String.format(") RETURN %s", getReturnType()));
        }
        else {

            // When there are no parameters the format is a bit different
            packageFunction.append(Function.FUNCTION + " " + getMethodName());
            packageFunction.append(String.format(" RETURN %s", getReturnType()));
        }

        return packageFunction.toString();
    }

    public String getParameterTypes() {

        if (parameters == null) {
            return "";
        }

        StringBuilder parameterTypes = new StringBuilder();

        for (int index = 0; index < parameters.size() - 1; index++) {
            parameterTypes.append(Parameter.toJavaType(parameters.get(index).getType()) + ", ");
        }

        parameterTypes.append(Parameter.toJavaType(parameters.get(parameters.size() - 1).getType()));

        return parameterTypes.toString();
    }
}
