package com.sandata.lab.tools.oracle.factory;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.tools.oracle.model.Function;
import com.sandata.lab.tools.oracle.model.Parameter;
import com.sandata.lab.tools.oracle.model.StoredProcedure;
import com.sandata.lab.tools.oracle.util.JavaUtil;
import com.sandata.lab.tools.oracle.util.MethodUtil;

/**
 * Date: 5/5/16
 * Time: 11:58 PM
 */

public class StoredProcedureFactory {

    public static StoredProcedure GetWithBsnEnt(Table table, String className) {
        StoredProcedure getWithBsnEnt = new StoredProcedure(JavaUtil.GetLookupWithBsnEnt(className, table));
        getWithBsnEnt.setExecuteType(StoredProcedure.GET_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
        function.setClassName(className);
        getWithBsnEnt.setFunction(function);
        return getWithBsnEnt;
    }

    public static StoredProcedure GetMethodForLKUP(Table table, String className) {

        if (!table.getLongName().endsWith("_LKUP")) {
            return null;
        }

        StoredProcedure getMethodForLkup = new StoredProcedure(JavaUtil.GetMethod(className, table));
        getMethodForLkup.setExecuteType(StoredProcedure.GET_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
        function.setClassName(className);
        getMethodForLkup.setFunction(function);
        return getMethodForLkup;
    }

    public static StoredProcedure GetMethodForPrimaryKeys(String[] primaryKeyColumns, Table table, String className) {

        if (primaryKeyColumns == null) {

            if (table.hasBsnEntColumn()) {
                primaryKeyColumns = new String[1];
                primaryKeyColumns[0] = "BE_ID";
            }
            else
                return null;
        }

        StoredProcedure getMethodForPrimaryKeys = new StoredProcedure(JavaUtil.GetMethodForPrimaryKeys(primaryKeyColumns, className, table));
        getMethodForPrimaryKeys.setExecuteType(StoredProcedure.GET_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
        function.setClassName(className);
        Parameter parameter = new Parameter();
        parameter.setName("params");
        parameter.setType(Parameter.NUMBER_ARRAY);
        function.addParameter(parameter);
        getMethodForPrimaryKeys.setFunction(function);
        return getMethodForPrimaryKeys;
    }

    public static StoredProcedure GetMethodForPK(Table table, String className) {

        StoredProcedure getMethodForPK = new StoredProcedure(JavaUtil.GetMethodForPK(className, table));
        getMethodForPK.setExecuteType(StoredProcedure.GET_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "get"));
        function.setClassName(className);
        Parameter parameter = new Parameter();
        parameter.setName("primaryKey");
        parameter.setType(Parameter.NUMBER);
        function.addParameter(parameter);
        getMethodForPK.setFunction(function);
        return getMethodForPK;
    }

    public static StoredProcedure InsertMethod(Table table, String className) {

        StoredProcedure spInsertMethod = new StoredProcedure(JavaUtil.InsertMethod(className, table));
        spInsertMethod.setExecuteType(StoredProcedure.INSERT_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "insert"));
        function.setClassName(className);
        Parameter parameter = new Parameter();
        parameter.setName("struct");
        parameter.setType(Parameter.STRUCT);
        function.addParameter(parameter);
        spInsertMethod.setFunction(function);
        return spInsertMethod;
    }

    public static StoredProcedure UpdateMethod(Table table, String className) {

        StoredProcedure spUpdateMethod = new StoredProcedure(JavaUtil.UpdateMethod(className, table));
        spUpdateMethod.setExecuteType(StoredProcedure.UPDATE_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "update"));
        function.setClassName(className);
        Parameter parameter = new Parameter();
        parameter.setName("struct");
        parameter.setType(Parameter.STRUCT);
        function.addParameter(parameter);
        spUpdateMethod.setFunction(function);
        return spUpdateMethod;

    }

    public static StoredProcedure DeleteMethod(Table table, String className) {

        StoredProcedure spDeleteMethod = new StoredProcedure(JavaUtil.DeleteMethod(className, table));
        spDeleteMethod.setExecuteType(StoredProcedure.DELETE_TYPE);
        Function function = new Function();
        function.setMethodName(MethodUtil.MethodNameFromTable(table, "delete"));
        function.setClassName(className);
        Parameter parameter = new Parameter();
        parameter.setName("primaryKey");
        parameter.setType(Parameter.NUMBER);
        function.addParameter(parameter);
        spDeleteMethod.setFunction(function);
        return spDeleteMethod;
    }
}
