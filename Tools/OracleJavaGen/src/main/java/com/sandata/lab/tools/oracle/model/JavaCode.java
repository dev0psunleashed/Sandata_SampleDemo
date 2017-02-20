package com.sandata.lab.tools.oracle.model;

/**
 * Date: 8/19/15
 * Time: 2:53 PM
 */

public class JavaCode {

    public String source;
    public String className;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String oracleCreate() {

        return String.format("CREATE OR REPLACE AND COMPILE JAVA SOURCE NAMED \"%s\" AS \n%s",
                getClassName(), getSource()
        );
    }
}
