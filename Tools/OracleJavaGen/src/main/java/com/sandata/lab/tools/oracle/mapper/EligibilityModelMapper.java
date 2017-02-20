package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/2/15
 * Time: 12:31 PM
 */

public class EligibilityModelMapper extends ModelMapper {

    private EligibilityModelMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "EligibilityTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Eligibility.java";
    }

    public static EligibilityModelMapper getInstance() throws Exception {

        return new EligibilityModelMapper();
    }
}
