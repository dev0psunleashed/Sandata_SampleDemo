package com.sandata.lab.db.oracle.util;

import com.sandata.lab.db.oracle.model.Function;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 8/19/15
 * Time: 4:58 PM
 */

public class FunctionTests {

    @Test
    public void should_create_default_get_function_for_primary_key_and_table() throws Exception {

        Function function = FunctionUtil.GetMethodForPK("PKG_TEST", "STAFF_AVAIL");

        Assert.assertNotNull(function);

        String packageString = function.toPackage();

        Assert.assertNotNull(packageString);

        Assert.assertTrue(packageString.equals("FUNCTION getStaffAvail(STAFF_AVAIL_KEY NUMBER) RETURN REF_CURSOR"));
    }
}
