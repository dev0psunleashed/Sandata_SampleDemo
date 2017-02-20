package com.sandata.lab.common.utils.java;

import com.google.common.base.CaseFormat;

/**
 * Helper methods used in generating/manipulating java files.
 * <p/>
 *
 * @author David Rutgos
 */
public class JavaUtil {

    public static String UnderscoresToCamelUppercase(String target) {

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, target);
    }

    public static String LowerToCamelUppercase(String target) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, target);
    }
}
