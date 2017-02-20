package com.sandata.lab.tools.oracle.util;

import com.sandata.lab.common.utils.data.AbbreviationUtil;

/**
 * Date: 9/5/15
 * Time: 5:33 PM
 */

public class PhysicalModelUtil {

    public static String LogicalClass(AbbreviationUtil abbreviationUtil, String physicalModel) {

        String logicalModel = abbreviationUtil.toLogical(physicalModel);
        return com.sandata.lab.common.utils.java.JavaUtil.UnderscoresToCamelUppercase(logicalModel);
    }
}
