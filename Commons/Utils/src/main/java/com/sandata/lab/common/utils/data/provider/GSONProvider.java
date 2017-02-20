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

package com.sandata.lab.common.utils.data.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandata.lab.common.utils.data.adapter.*;
import com.sandata.lab.common.utils.data.serializer.CustomHashMapSerializer;
import com.sandata.lab.common.utils.date.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;

/**
 * Date: 7/24/13
 * Time: 2:36 PM
 */
@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class GSONProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    private static String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ssX";

    protected static final String UTF_8 = "UTF-8";

    protected static Gson _toJson;
    protected static Gson _fromJson;

    public GSONProvider() {
        dateTimeFormat = DateUtil.SANDATA_UTC_DATE_TIME_FORMAT;
    }

    public GSONProvider(final String format) {
        dateTimeFormat = format;
    }

    public String toJson(final Object object) {
        return getToGson().toJson(object);
    }

    public Object fromJson(final String json, final Class clazz) {
        return getFromGson().fromJson(json, clazz);
    }

    protected void toJson(Object object, Type type, OutputStreamWriter writer) {
        getToGson().toJson(object, type, writer);
    }

    protected Object fromJson(InputStreamReader streamReader, Type type) {

        return getFromGson().fromJson(streamReader, type);
    }

    public static Gson getToGson() {
        if(_toJson == null)
            _toJson = new GsonBuilder()
                    //dmr--.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(HashMap.class, new CustomHashMapSerializer())
                    .registerTypeAdapter(LocalDate.class, new CustomJodaDateAdapter())
                    .registerTypeAdapter(LocalTime.class, new CustomJodaTimeAdapter())
                    .registerTypeAdapter(LocalDateTime.class, new CustomLocalDateTimeAdapter())
                    .registerTypeAdapter(Date.class, new CustomJavaDateAdapter(dateTimeFormat))
                    .setPrettyPrinting()
                    .enableComplexMapKeySerialization()
                    .serializeNulls()
                    .setVersion(1.0)
                    .create();

        return _toJson;
    }

    public static String ToJson(Object object) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(HashMap.class, new CustomHashMapSerializer())
                .registerTypeAdapter(LocalDate.class, new JodaLocalDateJsonAdapter())
                .registerTypeAdapter(LocalTime.class, new JodaLocalTimeJsonAdapter())
                .registerTypeAdapter(LocalDateTime.class, new CustomLocalDateTimeAdapter())
                .registerTypeAdapter(Date.class, new CustomJavaDateAdapter(dateTimeFormat))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();

        return gson.toJson(object);
    }

    public static Gson getFromGson() {
        if(_fromJson == null)
            _fromJson = new GsonBuilder()
                    .registerTypeAdapter(DateTime.class, new CustomJodaDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new JodaLocalDateJsonAdapter())
                    .registerTypeAdapter(LocalTime.class, new JodaLocalTimeJsonAdapter())
                    .registerTypeAdapter(LocalDateTime.class, new CustomLocalDateTimeAdapter())
                    .registerTypeAdapter(Date.class, new CustomJavaDateAdapter(dateTimeFormat))
                    .create();

        return _fromJson;
    }

    public static Object FromJson(String json, Class clazz) {
        Gson gson = getFromGson();
        return gson.fromJson(json, clazz);
    }

    public static Object FromJson(String json, Type type) {
        Gson gson = getFromGson();
        return gson.fromJson(json, type);
    }

    public static void SetDateTimeFormat(final String format) {
        dateTimeFormat = format;
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        InputStreamReader streamReader = new InputStreamReader(entityStream, UTF_8);
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            return fromJson(streamReader, jsonType);
        } finally {
            entityStream.close();
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {

        OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }

            toJson(object, jsonType, writer);

        } finally {
            writer.close();
        }
    }
}
