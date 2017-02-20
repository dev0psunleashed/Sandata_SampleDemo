package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/3/15
 * Time: 2:30 PM
 */

public class ReferralMapper extends ModelMapper {

    private ReferralMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "RfrlTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Referral.java";
    }

    public static ReferralMapper getInstance() throws Exception {
        return new ReferralMapper();
    }
}
