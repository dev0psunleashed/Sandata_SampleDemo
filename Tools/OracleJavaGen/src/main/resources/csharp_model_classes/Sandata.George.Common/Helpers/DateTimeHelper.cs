using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sandata.George.Common.Helpers
{
    public static class DateTimeHelper
    {
        public static String DateTimePattern
        {
            get { return (GlobalConstants.IS_AM_PM ? GlobalConstants.DATETIME_FORMAT_AMPM : GlobalConstants.DATETIME_FORMAT); }
        }

        public static String TimeSpanPattern
        {
            get { return (GlobalConstants.IS_AM_PM ? GlobalConstants.TIMESPAN_TIME_FORMAT_AMPM : GlobalConstants.TIMESPAN_TIME_FORMAT); }
        }

        public static string ToDateString(DateTime date)
        {
            return date.ToString(GlobalConstants.DATE_FORMAT, DateTimeFormatInfo.InvariantInfo);
        }

        public static string ToDateString(DateTime? date)
        {
            if (date.HasValue)
            {
                return date.Value.ToString(GlobalConstants.DATE_FORMAT, DateTimeFormatInfo.InvariantInfo);
            }

            return null;
        }

        public static string ToDateTimeString(DateTime? date)
        {
            if (date.HasValue)
            {
                return date.Value.ToString(DateTimePattern, DateTimeFormatInfo.InvariantInfo);
            }

            return null;
        }

        public static DateTime ToDateTime(string dateTime)
        {
            if (!String.IsNullOrEmpty(dateTime))
            {
                return Convert.ToDateTime(dateTime, DateTimeFormatInfo.InvariantInfo);
            }

            return DateTime.MinValue;
        }

        public static TimeSpan ToTime(string time)
        {
            if (!String.IsNullOrEmpty(time))
            {
                return TimeSpan.Parse(time, DateTimeFormatInfo.InvariantInfo);
            }

            return TimeSpan.MinValue;
        }

        public static string ToTimeString(TimeSpan time)
        {
            return time.ToString(TimeSpanPattern, DateTimeFormatInfo.InvariantInfo);
        }

        public static string ToTimeString(TimeSpan? time)
        {
            if (time.HasValue)
            {
                return time.Value.ToString(TimeSpanPattern, DateTimeFormatInfo.InvariantInfo);
            }

            return null;
        }
    }
}
