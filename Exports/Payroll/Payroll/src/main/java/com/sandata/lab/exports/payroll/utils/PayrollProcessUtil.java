package com.sandata.lab.exports.payroll.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * General helper methods for various Payroll Process workflows.
 * <p/>
 *
 * @author David Rutgos
 */
public class PayrollProcessUtil {

    /**
     * Given the BE.BE_PR_WEEK_END_DAY, determine the Date the week ended from the current date.
     *
     * The Business Entity defined last day of the week for payroll.
     * Identifies the week end day for the Business Entity (common week end days: Friday, Saturday, Sunday).
     *
     * https://mnt-ers-ts01.sandata.com/object/view.spg?key=331211
     *
     * @param payrollWeekEndDay Is the weekday common name, Friday, Saturday, Sunday.
     * @return Date of the actual Payroll week ended from the current date.
     */
    public static Date PayrollWeekEndDate(String payrollWeekEndDay, String timeZone) throws SandataRuntimeException {

        return PayrollWeekEndDate(payrollWeekEndDay, new Date(), timeZone);
    }

    public static Date PayrollWeekEndDate(String payrollWeekEndDay, Date fromDate, String timeZone) throws SandataRuntimeException {

        Calendar calendar = new GregorianCalendar();

        calendar.setTime(fromDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int targetDayOfWeek = GetIntDayOfWeek(payrollWeekEndDay);

        // Will generate the payrollWeekEndDate based on the Day that is passed in from the current Date/Time
        if (targetDayOfWeek < currentDayOfWeek) {
            calendar.add(Calendar.DAY_OF_WEEK, (targetDayOfWeek - currentDayOfWeek));
        } else {
            calendar.add(Calendar.DAY_OF_WEEK, (targetDayOfWeek - currentDayOfWeek) - 7);
        }

        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));

        return calendar.getTime();
    }

    /**
     * Get integer value of day of week
     *
     * @param dayOfWeek
     * @return
     */
    public static int GetIntDayOfWeek(String dayOfWeek) {
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("EEE");
            date = (Date) formatter.parse(dayOfWeek);
            GregorianCalendar g = new GregorianCalendar();
            g.setTime(date);
            return g.get(Calendar.DAY_OF_WEEK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
