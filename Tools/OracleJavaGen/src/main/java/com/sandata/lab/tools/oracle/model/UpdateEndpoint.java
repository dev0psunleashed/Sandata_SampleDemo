package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/3/15
 * Time: 7:46 PM
 */

public class UpdateEndpoint extends RestEndpoint {

    public UpdateEndpoint() {
        super(Type.UPDATE);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/update.template";
    }
}
