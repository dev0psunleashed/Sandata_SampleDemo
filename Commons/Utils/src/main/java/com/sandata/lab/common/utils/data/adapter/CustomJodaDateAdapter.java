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
import org.joda.time.LocalDate;

import java.lang.reflect.Type;

/**
 * Date: 9/5/13
 * Time: 5:19 PM
 */

public class CustomJodaDateAdapter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        return new LocalDate(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {

        return new JsonPrimitive(DateUtil.format(localDate));
    }
}
