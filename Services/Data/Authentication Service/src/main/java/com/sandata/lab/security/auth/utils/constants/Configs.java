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

package com.sandata.lab.security.auth.utils.constants;

import org.apache.camel.PropertyInject;

/**
 * Enumerates the constants for app specific endpoints.
 * <p/>
 *
 * @author Ralph Sylvain
 */
public class Configs {

    public static String get_AdminGroupsKey() {
        return _AdminGroupsKey;
    }

    @PropertyInject("{{admin.groups}}")
    private static final String _AdminGroupsKey = "MW_ADMIN_GROUPS";
}
