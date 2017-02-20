package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.string.StringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 9/5/15
 * Time: 2:23 PM
 */

public class StringUtilTests {

    @Test
    public void should_separate_a_given_string_by_capital_letters_ignoring_capitals_that_are_together() throws Exception {

        String result = StringUtil.SplitByCapital("PatientDMEAndSupplies", "_");

        Assert.assertNotNull(result);
        Assert.assertTrue(result.equals("Patient_DME_And_Supplies"));
    }
}
