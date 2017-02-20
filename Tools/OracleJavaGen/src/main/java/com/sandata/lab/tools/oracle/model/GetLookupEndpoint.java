package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/9/15
 * Time: 5:55 AM
 */

public class GetLookupEndpoint extends RestEndpoint {

    public GetLookupEndpoint() {
        super(Type.GET_LOOKUP);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/get.lookup.template";
    }
}
