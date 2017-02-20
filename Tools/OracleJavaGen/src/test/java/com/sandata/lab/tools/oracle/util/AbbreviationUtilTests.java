package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.tools.oracle.BaseTestSupport;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

/**
 * Tests the abbreviation util that converts between logical and physical names.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class AbbreviationUtilTests extends BaseTestSupport {

    private AbbreviationUtil abbreviationUtil;

    @Test
    public void should_convert_physical_pt_id_xwalk_to_logical_name() throws Exception {

        String ptIdXwalk = abbreviationUtil.toLogical("PT_ID_XWALK");

        System.out.println(ptIdXwalk);
        System.out.println(com.sandata.lab.common.utils.java.JavaUtil.UnderscoresToCamelUppercase(ptIdXwalk));
    }

    @Test
    public void should_convert_physical_be_id_xwalk_to_logical_name() throws Exception {

        String beIdXwalk = abbreviationUtil.toLogical("BE_ID_XWALK");

        System.out.println(beIdXwalk);
        System.out.println(com.sandata.lab.common.utils.java.JavaUtil.UnderscoresToCamelUppercase(beIdXwalk));
    }

    @Test
    public void should_convert_physical_db_name_to_logical_entity_name() throws Exception {

        String elig = abbreviationUtil.toLogical("ELIG");
        Assert.isTrue(elig.equals("Eligibility"));

        String patient = abbreviationUtil.toLogical("PT");
        Assert.isTrue(patient.equals("Patient"));

        String schedEvent = abbreviationUtil.toLogical("SCHED_EVNT");
        Assert.isTrue(com.sandata.lab.common.utils.java.JavaUtil.UnderscoresToCamelUppercase(schedEvent).equals("ScheduleEvent"));
    }

    @Test
    public void should_convert_logical_entity_name_to_physical_db_name() throws Exception {

        String elig = abbreviationUtil.toPhysical("Eligibility");
        Assert.isTrue(elig.equals("ELIG"));

        String patient = abbreviationUtil.toPhysical("Patient");
        Assert.isTrue(patient.equals("PT"));

        String schedEvent = abbreviationUtil.toPhysical(StringUtil.SplitByCapital("ScheduleEvent", "_"));
        Assert.isTrue(schedEvent.equals("SCHED_EVNT"));
    }
    
    @Override
    protected void onSetup() throws Exception {
        abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);
    }
}
