package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/3/15
 * Time: 2:30 PM
 */

public class ReferenceMapper extends ModelMapper {

    private ReferenceMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "ReferenceTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Reference.java";
    }

    public static ReferenceMapper getInstance() throws Exception {
        return new ReferenceMapper();
    }
}
