/*
 * Copyright (c) 2015. Sandata Technologies, LLC
 * 26 Harbor Park Drive, Port Washington, NY 11050, 800-544-7263
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sandata Technologies, LLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered into with
 * Sandata.
 */

package com.sandata.lab.common.utils.data.strategy;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

/**
 * User: David M Rutgos.
 * Date: 8/21/13
 * Time: 2:45 PM
 */

public class CustomFieldNamingStrategy implements FieldNamingStrategy {

    /**
     * Splits camel case string with underscore '_'
     *
     * @param inString
     * @return The result string with underscores
     */
    public static String splitCamelCase(String inString) {

        if(inString == null) return null;

        return inString.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                "_"
        );
    }

    @Override
    public String translateName(Field field) {

        return splitCamelCase(field.getName()).toLowerCase();
    }
}
