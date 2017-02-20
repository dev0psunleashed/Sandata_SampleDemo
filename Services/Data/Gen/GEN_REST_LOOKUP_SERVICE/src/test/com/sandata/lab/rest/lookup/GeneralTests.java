package com.sandata.lab.rest.lookup;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * General unit tests;
 * <p/>
 *
 * @author David Rutgos
 */
public class GeneralTests extends BaseTestSupport {

    @Test
    public void should_validate_stringbuilder_init_empty_string() throws Exception {

        StringBuilder builder = new StringBuilder();
        Assert.isTrue(builder.toString().equals(""));
    }
    
    @Override
    protected void onSetup() throws Exception {
    }
}
