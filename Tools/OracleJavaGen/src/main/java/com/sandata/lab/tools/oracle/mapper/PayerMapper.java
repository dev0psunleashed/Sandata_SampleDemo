package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/4/15
 * Time: 10:21 AM
 */

public class PayerMapper extends ModelMapper {

    private PayerMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "PayerTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "Payer.java";
    }

    public static PayerMapper getInstance() throws Exception {
        return new PayerMapper();
    }
}
