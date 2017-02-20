package com.sandata.lab.tools.oracle.model;

/**
 * Date: 9/3/15
 * Time: 7:46 PM
 */

public class GetSkEndpoint extends RestEndpoint {

    public GetSkEndpoint() {
        super(Type.GET_SK);
    }

    @Override
    protected String templateFile() {
        return "src/main/resources/templates/get.by.sk.template";
    }
}
