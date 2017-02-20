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

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Serialize/Deserialize java.util.Date to/from JSON.
 * <p/>
 * Date: 9/29/2014
 * Time: 4:45 PM
 *
 * @author David M. Rutgos
 * @since 1.0
 */
public class CustomJavaDateAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

    private final String dateTimeFormat;

    public CustomJavaDateAdapter(final String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new SimpleDateFormat(dateTimeFormat).parse(jsonElement.getAsString());

        } catch (ParseException e) {

            throw new JsonParseException("Please use the format '" +
                    DateUtil.SANDATA_UTC_DATE_TIME_FORMAT + "', for example 2016-10-04T14:30:00Z \n" + e);
        }
    }

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(new SimpleDateFormat(dateTimeFormat).format(date));
    }
}
