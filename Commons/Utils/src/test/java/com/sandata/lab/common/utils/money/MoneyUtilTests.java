package com.sandata.lab.common.utils.money;

import com.sandata.lab.common.utils.data.money.MoneyUtil;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(JMockit.class)
public class MoneyUtilTests {

    @Test
    public void should_convert_money_string_to_big_decimal() throws Exception {

        Assert.assertTrue(MoneyUtil.toBigDecimal(null).equals(BigDecimal.ZERO));
        Assert.assertTrue(MoneyUtil.toBigDecimal("0").equals(BigDecimal.ZERO));
        Assert.assertTrue(MoneyUtil.toBigDecimal("0.0").equals(BigDecimal.valueOf(0.0f)));
        Assert.assertTrue(MoneyUtil.toBigDecimal("0.0").equals(BigDecimal.valueOf(0.00f)));
        Assert.assertTrue(MoneyUtil.toBigDecimal("3,274.00").equals(new BigDecimal("3274.00")));
        Assert.assertTrue(MoneyUtil.toBigDecimal("52,923,274.71").equals(new BigDecimal("52923274.71")));
    }

    @Test
    public void should_convert_big_decimal_to_us_currency_format() throws Exception {

        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.ZERO).toString().equals("0.00"));
        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.valueOf(99.9)).toString().equals("99.90"));
        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.valueOf(1)).toString().equals("1.00"));
        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.valueOf(1.2)).toString().equals("1.20"));

        //Choose java.math.RoundingMode.CEILING rule
        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.valueOf(1.234)).toString().equals("1.24"));
        Assert.assertTrue(MoneyUtil.formatCurrency(BigDecimal.valueOf(1.146)).toString().equals("1.15"));

    }
}
