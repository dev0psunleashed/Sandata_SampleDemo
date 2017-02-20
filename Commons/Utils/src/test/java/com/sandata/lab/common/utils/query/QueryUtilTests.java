package com.sandata.lab.common.utils.query;

import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JMockit.class)
public class QueryUtilTests {

    @Test
    public void should_test_delim_string_to_param() throws Exception {

        List<Object> params = QueryUtil.DelimStringToParams(null, ",");
        Assert.assertNotNull(params);
        Assert.assertTrue(params.size() == 0);

        params = QueryUtil.DelimStringToParams("NY,fl,Ca", ",");
        Assert.assertTrue(params.size() == 3);
        Assert.assertTrue(params.get(0).equals("NY"));
        Assert.assertTrue(params.get(1).equals("FL"));
        Assert.assertTrue(params.get(2).equals("CA"));

    }

    @Test
    public void should_test_param_list() throws Exception {

        Assert.assertTrue(QueryUtil.ParamList(-100) == null);
        Assert.assertTrue(QueryUtil.ParamList(0) == null);
        Assert.assertTrue(QueryUtil.ParamList(1).equals("(?)"));
        Assert.assertTrue(QueryUtil.ParamList(5).equals("(?,?,?,?,?)"));
    }
}
