package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/4/15
 * Time: 10:21 AM
 */

public class BillingRateMapper extends ModelMapper {

    private BillingRateMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "BillingRateTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "BillingRate.java";
    }

    public static BillingRateMapper getInstance() throws Exception {
        return new BillingRateMapper();
    }
}
