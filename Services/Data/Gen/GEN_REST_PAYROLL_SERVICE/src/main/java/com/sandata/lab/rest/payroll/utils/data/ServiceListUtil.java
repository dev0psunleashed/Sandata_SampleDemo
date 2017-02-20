package com.sandata.lab.rest.payroll.utils.data;

import com.sandata.lab.common.utils.string.StringUtil;

/**
 * Helper utility for service list requests.
 * <p/>
 *
 * @author David Rutgos
 */
public class ServiceListUtil {

    public static String FormatToSqlList(String services) {

        if (StringUtil.IsNullOrEmpty(services)) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        String[] servicesArray = services.split(",");
        int i = 0;
        for (; i < servicesArray.length - 1; ) {
            result.append(String.format("'%s',", servicesArray[i++].toUpperCase()));
        }

        result.append(String.format("'%s'", servicesArray[i].toUpperCase()));

        return result.toString();
    }

    public static String[] FormatToSqlArray(String services) {

        if (StringUtil.IsNullOrEmpty(services)) {
            return null;
        }

        String result[] = services.split(",");

        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].toUpperCase();
        }

        return result;
    }
}
