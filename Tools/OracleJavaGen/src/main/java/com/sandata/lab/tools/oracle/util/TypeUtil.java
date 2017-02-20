package com.sandata.lab.tools.oracle.util;

import com.google.common.base.CaseFormat;

/**
 * Date: 9/5/15
 * Time: 2:58 PM
 */

public class TypeUtil {

    public static String ToTypeClassName(String physicalModelName) {

        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, physicalModelName) + "Typ";
    }

    public static String ToType(String physicalModelName) {

        return (physicalModelName + "_T").toUpperCase();
    }
}
