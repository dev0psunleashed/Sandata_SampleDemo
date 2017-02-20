package com.sandata.lab.payroll;

import com.sandata.lab.payroll.utils.PayrollProcessUtil;
import org.junit.Test;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Tests the helper methods for PayrollProcessUtil class.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollProcessUtilTests extends BaseTestSupport {

    private DateFormat dateFormat;

    @Test
    public void should_determine_the_week_end_date_from_the_current_date() throws Exception {

        Date fromDate = dateFormat.parse("2016-05-14 00:00:00");
        Date date = PayrollProcessUtil.PayrollWeekEndDate("Friday", fromDate);
        String dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-05-13 23:59:59"));

        fromDate = dateFormat.parse("2016-06-23 00:00:00");
        date = PayrollProcessUtil.PayrollWeekEndDate("Wednesday", fromDate);
        dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-06-22 23:59:59"));

        fromDate = dateFormat.parse("2016-06-21 00:00:00");
        date = PayrollProcessUtil.PayrollWeekEndDate("Wednesday", fromDate);
        dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-06-15 23:59:59"));

        date = PayrollProcessUtil.PayrollWeekEndDate("FRIDAY", dateFormat.parse("2016-06-02 15:30:00"));
        dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-05-27 23:59:59"));

        date = PayrollProcessUtil.PayrollWeekEndDate("Friday", dateFormat.parse("2016-06-03 23:59:59"));
        dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-05-27 23:59:59"));

        date = PayrollProcessUtil.PayrollWeekEndDate("Friday", dateFormat.parse("2016-06-04 00:00:00"));
        dateString = dateFormat.format(date);

        Assert.isTrue(dateString.equals("2016-06-03 23:59:59"));
    }
    
    @Override
    protected void onSetup() throws Exception {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
