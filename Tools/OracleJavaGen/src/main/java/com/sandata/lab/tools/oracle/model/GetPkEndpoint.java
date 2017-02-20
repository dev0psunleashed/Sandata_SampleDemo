package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/3/15
 * Time: 7:46 PM
 */

public class GetPkEndpoint extends RestEndpoint {

    public GetPkEndpoint() {
        super(Type.GET_PK);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/get.by.pk.template";
    }
}
