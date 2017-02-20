package com.sandata.lab.rules.vv.imports.data.transformers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandata.up.commons.service.data.transformer.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.util.HashMap;



/**
 * 
 * @author thanhxle
 *
 */

@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class CustomGSONProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object>
{
    private static Logger logger = LoggerFactory.getLogger(CustomGSONProvider.class);

    private static final String UTF_8 = "UTF-8";

    private static Gson _toJson;
    private static Gson _fromJson;

    public String toJson(Object object)
    {
        return getToGson().toJson(object);
    }

    private void toJson(Object object, Type type, OutputStreamWriter writer)
    {
        getToGson().toJson(object, type, writer);
    }

    private Object fromJson(InputStreamReader streamReader, Type type) {

        return getFromGson().fromJson(streamReader, type);
    }

    public static Gson getToGson()
    {
        if(_toJson == null)
            _toJson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(HashMap.class, new CustomHashMapSerializer())
                    .registerTypeAdapter(LocalDate.class, new CustomJodaDateAdapter())
                    .registerTypeAdapter(LocalTime.class, new CustomJodaTimeAdapter())
                    .registerTypeAdapter(DateTime.class, new CustomJodaDateTimeAdapter())
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
                .registerTypeAdapter(LocalDate.class, new CustomJodaDateAdapter())
                .registerTypeAdapter(LocalTime.class, new CustomJodaTimeAdapter())
                .registerTypeAdapter(DateTime.class, new CustomJodaDateTimeAdapter())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();

        return gson.toJson(object);
    }

    public static Gson getFromGson()
    {
        if(_fromJson == null)
            _fromJson = new GsonBuilder()
                    .registerTypeAdapter(DateTime.class, new CustomJodaDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new JodaLocalDateJsonAdapter())
                    .registerTypeAdapter(LocalTime.class, new JodaLocalTimeJsonAdapter())
                    .create();

        return _fromJson;
    }

    public static Object FromJson(String json, Class clazz) {
        Gson gson = getFromGson();
        return gson.fromJson(json, clazz);
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
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
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {

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
