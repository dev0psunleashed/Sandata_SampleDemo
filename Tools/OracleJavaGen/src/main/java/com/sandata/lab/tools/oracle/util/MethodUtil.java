package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.model.Table;

/**
 * Created by dmrutgos on 8/31/2015.
 */
public class MethodUtil {

    public static String MethodNameFromTable(final Table table, final String prefix) {

        String[] parts = table.getShortName().split("_");
        StringBuilder methodName = new StringBuilder(prefix);
        for (String part : parts) {
            methodName.append(JavaUtil.FirstCharUpper(part.toLowerCase()));
        }

        return methodName.toString();
    }
}
