package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.java.JavaUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class AbbreviationsTests {

    @Test
    public void should_convert_physical_pt_id_xwalk_to_logical_name() throws Exception {

        AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);

        String ptIdXwalk = abbreviationUtil.toLogical("PT_ID_XWALK");

        Assert.assertTrue(ptIdXwalk.equals("Patient_I_D_Crosswalk"));
        Assert.assertTrue(JavaUtil.UnderscoresToCamelUppercase(ptIdXwalk).equals("PatientIDCrosswalk"));
    }

    @Test
    public void should_convert_physical_be_id_xwalk_to_logical_name() throws Exception {

        AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);

        String beIdXwalk = abbreviationUtil.toLogical("BE_ID_XWALK");

        Assert.assertTrue(beIdXwalk.equals("Business_Entity_I_D_Crosswalk"));
        Assert.assertTrue(JavaUtil.UnderscoresToCamelUppercase(beIdXwalk).equals("BusinessEntityIDCrosswalk"));
    }

    @Test
    public void should_convert_physical_db_name_to_logical_entity_name() throws Exception {

        AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);

        String elig = abbreviationUtil.toLogical("ELIG");
        Assert.assertTrue(elig.equals("Eligibility"));

        String patient = abbreviationUtil.toLogical("PT");
        Assert.assertTrue(patient.equals("Patient"));

        String schedEvent = abbreviationUtil.toLogical("SCHED_EVNT");
        Assert.assertTrue(JavaUtil.UnderscoresToCamelUppercase(schedEvent).equals("ScheduleEvent"));
    }

    @Test
    public void should_convert_logical_entity_name_to_physical_db_name() throws Exception {

        AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);

        String elig = abbreviationUtil.toPhysical("Eligibility");
        Assert.assertTrue(elig.equals("ELIG"));

        String patient = abbreviationUtil.toPhysical("Patient");
        Assert.assertTrue(patient.equals("PT"));

        String schedEvent = abbreviationUtil.toPhysical(StringUtil.SplitByCapital("ScheduleEvent", "_"));
        Assert.assertTrue(schedEvent.equals("SCHED_EVNT"));
    }
}
