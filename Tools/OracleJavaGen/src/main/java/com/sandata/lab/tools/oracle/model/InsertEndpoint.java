package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/3/15
 * Time: 7:43 PM
 */

public class InsertEndpoint extends RestEndpoint {

    public InsertEndpoint() {
        super(Type.INSERT);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/insert.template";
    }
}
