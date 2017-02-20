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

package com.sandata.lab.common.utils.data.adapter;

import com.google.gson.*;
import com.sandata.lab.common.utils.date.DateUtil;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Type;

/**
 * Convert LocalDateTime to/from JSON.
 * <p/>
 * Date: 7/2/2014
 * Time: 6:48 PM
 *
 * @author David Rutgos
 * @since 1.0
 */
public class CustomLocalDateTimeAdapter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(json.getAsString(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));

            return localDateTime;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(DateUtil.format(localDateTime));
    }
}
