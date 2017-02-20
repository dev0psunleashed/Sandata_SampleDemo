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
import org.joda.time.LocalTime;

import java.lang.reflect.Type;

/**
 * Date: 12/17/13
 * Time: 7:08 AM
 */
public class JodaLocalTimeJsonAdapter implements JsonDeserializer<LocalTime>, JsonSerializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        try {
            return new LocalTime(jsonElement.getAsString());

        } catch (IllegalArgumentException iae) {
            // Fall back to parse with "hh:mm aa" format
            return DateUtil.parseShortTime(jsonElement.getAsString());
        }
    }

    @Override
    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {

        return new JsonPrimitive(DateUtil.format(localTime, "hh:mm:ss"));
    }
}
