package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.tools.oracle.BaseTestSupport;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Unit tests for the physical model utility class.
 * <p/>
 *
 * @author David Rutgos
 */
public class PhysicalModelUtilTests extends BaseTestSupport {

    private AbbreviationUtil abbreviationUtil;

    @Test
    public void should_convert_physical_model_to_logical_model_name_poc_task_lst() throws Exception {

        String logicalClassName = PhysicalModelUtil.LogicalClass(abbreviationUtil, "POC_TASK_LST");

        Assert.notNull(logicalClassName);
        Assert.isTrue(logicalClassName.equals("PlanOfCareTaskList"));
    }

    @Test
    public void should_convert_physical_model_to_logical_model_name_be_allergy_lst() throws Exception {

        String logicalClassName = PhysicalModelUtil.LogicalClass(abbreviationUtil, "BE_ALLERGY_LST");

        Assert.notNull(logicalClassName);
        Assert.isTrue(logicalClassName.equals("BusinessEntityAllergyList"));
    }

    @Override
    protected void onSetup() throws Exception {
        abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);
    }
}
