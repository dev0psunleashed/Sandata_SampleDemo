package com.sandata.lab.tools.oracle.model;

import com.sandata.lab.tools.oracle.BaseTestSupport;
import com.sandata.lab.tools.oracle.mapper.EligibilityModelMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 9/2/15
 * Time: 1:15 PM
 */

public class NormalizedModelMapperTests extends BaseTestSupport {

    @Test
    public void should_process_setter_method() throws Exception {

        String setterMethod = "public void setClaimSk(java.math.BigDecimal claimSk) throws SQLException";
        String key = "public void set";
        if (setterMethod.startsWith(key)) {
            GetterSetter getterSetter = new GetterSetter();
            String property = setterMethod.substring(key.length(), setterMethod.indexOf("("));
            String type = setterMethod.substring(setterMethod.indexOf("(") + 1, setterMethod.indexOf(")") - (property.length() + 1));
            getterSetter.setType(type);
            getterSetter.setGetter("get" + property);
            getterSetter.setSetter("set" + property);
            System.out.println(getterSetter.toString());
        }
    }
    
    @Test
    public void should_create_in_memory_hashmap_of_model_files_between_normalized_and_jpub_models() throws Exception {

        EligibilityModelMapper eligibilityModelMapper = EligibilityModelMapper.getInstance();

        Assert.assertNotNull(eligibilityModelMapper);

        eligibilityModelMapper.process();
    }

    @Override
    protected void onSetup() throws Exception {

    }
}
