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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 8/28/13
 * Time: 12:04 PM
 */

public class CustomJodaDateTimeAdapter implements JsonDeserializer<DateTime> {

    /**
     * UI will send the Date in Milliseconds from Jan 1 1970 via javascript new Date().getTime()
     *
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return
     * @throws com.google.gson.JsonParseException
     */

    @Override
    public DateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        try
        {
           // Convert jsonElement string to JODA DateTime (if in the given format by Javascript new Date().toJSON() method.
            Date date = new SimpleDateFormat("yyyy-MM-dd h:mm aa").parse(jsonElement.getAsString());

           // Conversion successful, return a Joda DateTime object
           return new DateTime(date.getTime());
        }
        catch (Exception e)
        {
            // If the jsonElement is the time in milliseconds from Jan 1 1970 try to convert...
            return new DateTime(jsonElement.getAsLong());
        }
    }
}
