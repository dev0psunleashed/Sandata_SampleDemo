package com.sandata.lab.tools.oracle.factory;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.model.Function;
import com.sandata.lab.tools.oracle.model.Parameter;
import com.sandata.lab.tools.oracle.util.JavaUtil;

/**
 * Date: 5/6/16
 * Time: 1:45 AM
 */

public class PackageFunctionFactory {

    public static Function CreateGet(Table table, String className) {

        Function function = new Function();

        String methodName = "get" + JavaUtil.CamelCase(table.getShortName());

        if (table.hasBsnEntColumn()) {
            Parameter parameter = new Parameter();
            parameter.setName("BE_ID");
            parameter.setType(Parameter.VARCHAR2);
            function.addParameter(parameter);
        }

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);
        function.setClassName(className);
        function.setTable(table);

        return function;
    }

    public static Function CreateGetForPK(Table table, String className) {

        Function function = new Function();
        String methodName = String.format("get%s", JavaUtil.CamelCase(table.getShortName()));

        Parameter parameter = new Parameter();
        parameter.setName(className.toUpperCase() + "_KEY");
        parameter.setType(Parameter.NUMBER);
        function.addParameter(parameter);

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);
        function.setClassName(className);
        function.setTable(table);

        return function;
    }

    public static Function CreateGetForPKArray(Table table, String className) {

        Function function = new Function();
        String methodName = String.format("get%s", JavaUtil.CamelCase(table.getShortName()));

        Parameter parameter = new Parameter();
        parameter.setName(className.toUpperCase() + "_ARRAY");
        parameter.setType(Parameter.STRING_ARRAY);
        function.addParameter(parameter);

        function.setMethodName(methodName);
        function.setReturnType(Parameter.CURSOR);
        function.setClassName(className);
        function.setTable(table);

        return function;
    }

    public static Function CreateInsert(Table table, String className) {
        return CreateFunctionForType("insert", table, className);
    }

    public static Function CreateUpdate(Table table, String className) {
        return CreateFunctionForType("update", table, className);
    }

    public static Function CreateDelete(Table table, String className) {

        Parameter primaryKey = new Parameter();
        primaryKey.setName(table.getShortName() + "_KEY");
        primaryKey.setType(Parameter.NUMBER);

        return CreateFunction("delete", table, className, primaryKey);
    }

    private static Function CreateFunctionForType(String functionType, Table table, String className) {

        Function function = new Function();

        String structTyp = table.getShortName() + "_T";

        String methodName = String.format("%s%s", functionType, JavaUtil.CamelCase(table.getShortName()));

        Parameter primaryKey = new Parameter();
        primaryKey.setName(table.getShortName() + "_VAR");
        primaryKey.setType(structTyp);
        function.addParameter(primaryKey);

        function.setMethodName(methodName);
        function.setReturnType(Parameter.LONG_NUMBER);
        function.setClassName(className);
        function.setTable(table);

        return function;
    }

    private static Function CreateFunction(String functionType, Table table, String className, Parameter... parameters) {

        Function function = new Function();

        String methodName = String.format("%s%s", functionType, JavaUtil.CamelCase(table.getShortName()));

        for (Parameter parameter : parameters) {
            function.addParameter(parameter);
        }

        function.setMethodName(methodName);
        function.setReturnType(Parameter.NUMBER);
        function.setClassName(className);
        function.setTable(table);

        return function;
    }
}
