package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/3/15
 * Time: 7:46 PM
 */

public class DeleteEndpoint extends RestEndpoint {

    public DeleteEndpoint() {
        super(Type.DELETE);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/delete.template";
    }
}
