package com.sandata.lab.db.oracle.tests;

import com.sandata.lab.db.oracle.model.Column;
import com.sandata.lab.db.oracle.model.Table;
import com.sandata.lab.db.oracle.util.JavaUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 8/19/15
 * Time: 8:59 AM
 */

public class JavaUtilTests {

    @Test
    public void should_generate_get_method_for_primary_keys() throws Exception {

        Table table = new Table();
        table.setShortName("PATIENT");

        Column col1 = new Column();
        col1.setName("Test1");

        Column col2 = new Column();
        col2.setName("Test2");

        table.addColumn(col1);
        table.addColumn(col2);

        String javaCode = JavaUtil.GetMethodForPrimaryKeys(new String[] {"PATIENT_ID", "AGENCY_ID", "BLAH_ID"}, "Patient", table);

        Assert.assertNotNull(javaCode);

        System.out.println(javaCode);
    }

    @Test
    public void should_generate_sequence_column_name_from_compound_table_name_staff_working_prefs() throws Exception {

        String skCol = JavaUtil.SK("STAFF_WORKING_PREF", null);

        Assert.assertNotNull(skCol);

        Assert.assertTrue(skCol.equals("STAFF_WORKING_SK_PREF"));

        System.out.println(skCol);
    }

    @Test
    public void should_generate_sequence_column_name_from_compound_table_name_staff_avail() throws Exception {

        String skCol = JavaUtil.SK("STAFF_AVAIL", null);

        Assert.assertNotNull(skCol);

        Assert.assertTrue(skCol.equals("STAFF_SK_AVAIL"));

        System.out.println(skCol);
    }

    @Test
    public void should_generate_sequence_column_name_from_simple_table_name_schedule() throws Exception {

        String skCol = JavaUtil.SK("SCHEDULE", null);

        Assert.assertNotNull(skCol);

        Assert.assertTrue(skCol.equals("SCHEDULE_SK"));

        System.out.println(skCol);
    }

    @Test
    public void should_generate_sequence_column_name_from_simple_table_name_staff() throws Exception {

        String skCol = JavaUtil.SK("STAFF", null);

        Assert.assertNotNull(skCol);

        Assert.assertTrue(skCol.equals("STAFF_SK"));

        System.out.println(skCol);
    }
}
