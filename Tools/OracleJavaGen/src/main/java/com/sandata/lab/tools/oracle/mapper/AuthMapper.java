package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/4/15
 * Time: 10:21 AM
 */

public class AuthMapper extends ModelMapper {

    private AuthMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "AuthTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Authorization.java";
    }

    public static AuthMapper getInstance() throws Exception {
        return new AuthMapper();
    }
}
