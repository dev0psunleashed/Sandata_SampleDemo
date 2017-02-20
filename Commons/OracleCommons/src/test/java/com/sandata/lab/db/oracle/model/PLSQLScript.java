package com.sandata.lab.db.oracle.model;

import com.sandata.lab.db.oracle.util.PLSQLTestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/20/15
 * Time: 10:34 AM
 */

public class PLSQLScript {

    private String source;
    private String packageName;
    private String methodName;
    private List<Parameter> parameters;
    private Parameter returnType;

    private Integer sequenceKey;

    private Table table;

    public void addParameter(final Parameter parameter) {

        if (parameters == null) {
            parameters = new ArrayList<Parameter>();
        }

        this.parameters.add(parameter);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Integer getSequenceKey() {
        return sequenceKey;
    }

    public void setSequenceKey(Integer sequenceKey) {
        this.sequenceKey = sequenceKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Parameter getReturnType() {
        return returnType;
    }

    public void setReturnType(Parameter returnType) {
        this.returnType = returnType;
    }

    public PLSQLScript buildScript() {

        StringBuilder testString = new StringBuilder();

        testString.append("SET SERVEROUTPUT ON;\n");
        testString.append("DBMS_OUTPUT.ENABLE;\n");
        testString.append("DECLARE\n");

        // Declare Types
        for (Parameter parameter : getParameters()) {
            testString.append(parameter.getName() + " " + parameter.getType() + ";\n");
        }

        testString.append(getReturnType().getName() + " " + getReturnType().getType() + ";\n");
        testString.append("BEGIN\n\n");

        String methodSignature = "%s := %s.%s(";
        String params = "";

        // FILL PARAMS
        for (int i = 0; i < getParameters().size(); i++) {

            Parameter parameter = getParameters().get(i);

            testString.append(String.format(parameter.getName() + " := %s(\n", parameter.getType()));

            // LOOP Through Columns to help with setting test data...
            for (int index = 0; index < getTable().getColumns().size() - 1; index++) {

                Column column = getTable().getColumns().get(index);

                // Sequence Number
                if (column.getName().contains("_SK")) {

                    testString.append(String.format("%d,\n", getSequenceKey()));

                } else {

                    testString.append(PLSQLTestUtil.GetValue(column.getType(), column.getNullable()) + ",\n");
                }
            }

            // GET LAST COLUMN
            Column column = getTable().getColumns().get(getTable().getColumns().size() - 1);

            testString.append(PLSQLTestUtil.GetValue(column.getType(), column.getNullable()) + "\n);\n");

            // If there are more parameters, then add a comma
            if ((i + 1) < getParameters().size()) {
                params += parameter.getName() + ",";
            }
            else {
                // Last parameter do not add a comma
                params += parameter.getName();
            }
        }

        // GET LAST PARAM

        String executable = String.format(methodSignature, getReturnType().getName(),
                                getPackageName(), getMethodName());

        executable += params + ");\n";

        testString.append(executable);

        testString.append(String.format("DBMS_OUTPUT.PUT_LINE(%s);\n\n", getReturnType().getName()));
        testString.append("END;\n");

        setSource(testString.toString());

        return this;

    }
}
