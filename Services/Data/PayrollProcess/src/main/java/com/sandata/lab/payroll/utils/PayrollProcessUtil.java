package com.sandata.lab.payroll.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public static Date PayrollWeekEndDate(String payrollWeekEndDay) throws SandataRuntimeException {

        return PayrollWeekEndDate(payrollWeekEndDay, new Date());
    }

    public static Date PayrollWeekEndDate(String payrollWeekEndDay, Date fromDate) throws SandataRuntimeException {

        Calendar calendar = new GregorianCalendar();

        calendar.setTime(fromDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int targetDayOfWeek = DayOfWeek(payrollWeekEndDay);

        // Will generate the payrollWeekEndDate based on the Day that is passed in from the current Date/Time
        if (targetDayOfWeek < currentDayOfWeek) {
            calendar.add(Calendar.DAY_OF_WEEK, (targetDayOfWeek - currentDayOfWeek));
        } else {
            calendar.add(Calendar.DAY_OF_WEEK, (targetDayOfWeek - currentDayOfWeek) - 7);
        }

        return calendar.getTime();
    }

    public static int DayOfWeek(String payrollWeekEndDay) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(payrollWeekEndDay)) {
            throw new SandataRuntimeException("PayrollProcessUtil: DayOfWeek: ERROR: payrollWeekEndDay == null");
        }

        switch (payrollWeekEndDay.toUpperCase()) {
            case "SUNDAY": return Calendar.SUNDAY;
            case "MONDAY": return Calendar.MONDAY;
            case "TUESDAY": return Calendar.TUESDAY;
            case "WEDNESDAY": return Calendar.WEDNESDAY;
            case "THURSDAY": return Calendar.THURSDAY;
            case "FRIDAY": return Calendar.FRIDAY;
            case "SATURDAY": return Calendar.SATURDAY;
            default:
                throw new SandataRuntimeException(String.format("PayrollProcessUtil: DayOfWeek: ERROR: UNKNOWN: " +
                        "payrollWeekEndDay = [%s]", payrollWeekEndDay));
        }
    }
}
