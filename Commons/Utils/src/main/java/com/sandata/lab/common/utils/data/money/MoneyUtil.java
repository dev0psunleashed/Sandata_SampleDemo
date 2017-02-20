package com.sandata.lab.common.utils.data.money;

import com.sandata.lab.common.utils.string.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

/**
 * Helper utilities for formatting, converting, etc. money (BigDecimal).
 * <p/>
 *
 * @author David Rutgos
 */
public class MoneyUtil {

    // http://stackoverflow.com/questions/18231802/how-can-i-parse-a-string-to-bigdecimal
    public static BigDecimal toBigDecimal(String money) throws ParseException {

        if (StringUtil.IsNullOrEmpty(money)) {
            return BigDecimal.ZERO;
        }

        if (!money.contains(",")) {
            return new BigDecimal(money);
        }

        // Create a DecimalFormat that fits your requirements
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        return  (BigDecimal) decimalFormat.parse(money);
    }

    /**
     * Format the input amount money in US currency: value of "1" or "1.0", should understand "1.00"
     * Default value BigDecimal.ZERO = 0.00
     * @param money
     * @return
     */
    public static BigDecimal formatCurrency(BigDecimal money) {

        return formatCurrency(money, BigDecimal.ZERO);

    }

    /**
     * Format the input amount money in US currency: value of "1" or "1.0", should understand "1.00".
     * User can pass the default value
     * @param money
     * @param defaultValue
     * @return
     */
    public static BigDecimal formatCurrency(BigDecimal money, BigDecimal defaultValue) {

        if (money == null) {
            money = (defaultValue != null) ? defaultValue : BigDecimal.ZERO;
        }

        return formatInternal(money);
    }

    private static BigDecimal formatInternal(BigDecimal input) {
        //Get the US as default
        Currency currency = NumberFormat.getCurrencyInstance(Locale.US).getCurrency();

        try {
            return input.setScale(currency.getDefaultFractionDigits());
        }
        catch (Exception ex){
            //Exception with RoundMode http://docs.oracle.com/javase/1.5.0/docs/api/java/math/RoundingMode.html
            //Choose ROUND_CEILING as the default cannot handle. Should discuss how to handle in the system
            return input.setScale(currency.getDefaultFractionDigits(), BigDecimal.ROUND_CEILING);
        }
    }

}
