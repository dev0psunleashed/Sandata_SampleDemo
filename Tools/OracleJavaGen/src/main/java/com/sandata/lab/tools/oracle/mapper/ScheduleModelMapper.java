package com.sandata.lab.tools.oracle.mapper;

/**
 * Date: 9/2/15
 * Time: 5:42 PM
 */

public class ScheduleModelMapper extends ModelMapper {

    private ScheduleModelMapper() throws Exception {
        super();
        setVerbosity(false);
    }

    @Override
    protected String modelFileName() {
        return "ScheduleEventTyp.java";
    }

    @Override
    protected String normalizedFileName() {
        return "ScheduleEvent.java";
    }

    public static ScheduleModelMapper getInstance() throws Exception {

        return new ScheduleModelMapper();
    }
}
