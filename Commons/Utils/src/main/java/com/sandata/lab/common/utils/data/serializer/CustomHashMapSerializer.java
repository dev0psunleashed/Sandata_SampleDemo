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

package com.sandata.lab.common.utils.data.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

/**
 * Date: 8/21/13
 * Time: 3:49 PM
 */

public class CustomHashMapSerializer implements JsonSerializer<Map> {

    /**
     * Transform Map key values to lower case.
     *
     * @param map
     * @param type
     * @param context
     * @return
     */
    @Override
    public JsonElement serialize(Map map, Type type, JsonSerializationContext context) {

        JsonObject result = new JsonObject();

        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object obj = entry.getValue();

            JsonElement valueElement = context.serialize(obj, obj.getClass());
            result.add(entry.getKey().toString().toLowerCase(), valueElement);
        }

        return result;
    }
}
