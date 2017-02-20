package com.sandata.lab.tools.oracle.model;

/**
 * Date: 8/26/15
 * Time: 8:58 PM
 */

public class CustomType {

    private String name;
    private String createOrReplace;

    public String getTypeName() {
        return String.format("%s_TYP", getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateOrReplace() {
        return createOrReplace;
    }

    public void setCreateOrReplace(String createOrReplace) {
        this.createOrReplace = createOrReplace;
    }
}
