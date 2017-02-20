package com.sandata.lab.db.oracle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/19/15
 * Time: 4:16 PM
 */

public class Package {

    public Package(final String name) {
        this.name = name;
    }

    private String name;
    private List<Function> functions;

    public void addFunction(final Function function) {
        if (functions == null) {
            functions = new ArrayList<Function>();
        }

        functions.add(function);
    }

    public String getName() {
        return name;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public String toPackage() {

        StringBuilder packageFunction = new StringBuilder();
        packageFunction.append(String.format("CREATE OR REPLACE PACKAGE %s IS\n\n", getName()));
        packageFunction.append(String.format("TYPE %s IS REF CURSOR;\n", Parameter.CURSOR));

        for (Function function : getFunctions()) {

            packageFunction.append(function.toPackage() + ";\n");
        }

        packageFunction.append(String.format("\nEND %s;\n", getName()));

        return packageFunction.toString();
    }

    // TODO: Refactor to decorator pattern
    public String toPackageBody() {

        StringBuilder packageFunction = new StringBuilder();
        packageFunction.append(String.format("CREATE OR REPLACE PACKAGE BODY %s IS\n\n", getName()));

        for (Function function : getFunctions()) {

            packageFunction.append(function.toPackage() + "\n");

            // parameters exist for the function
            packageFunction.append(String.format("AS LANGUAGE JAVA NAME '%s.%s(%s) return %s';\n",
                    function.getClassName(),
                    function.getMethodName(),
                    function.getParameterTypes(),   // returns a comma delimited list of params (int, int)
                    Parameter.toJavaType(function.getReturnType())));
        }

        packageFunction.append(String.format("\nEND %s;\n", getName()));

        return packageFunction.toString();
    }
}
