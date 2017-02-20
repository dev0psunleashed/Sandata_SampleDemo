package com.sandata.lab.eligibility.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandata.lab.common.utils.data.adapter.CustomJavaDateAdapter;
import com.sandata.lab.common.utils.data.adapter.CustomJodaDateTimeAdapter;
import com.sandata.lab.common.utils.data.adapter.CustomLocalDateTimeAdapter;
import com.sandata.lab.common.utils.data.adapter.JodaLocalDateJsonAdapter;
import com.sandata.lab.common.utils.data.adapter.JodaLocalTimeJsonAdapter;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * Use GSON to deserialize JSON to object
 */
public class GsonProvider {
    private static final String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    protected static final String UTF_8 = "UTF-8";

    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * Initialize an instance of {@link GsonProvider} with default date time
     * format
     */
    public GsonProvider() {
        dateTimeFormat = DATE_TIME_FORMAT_DEFAULT;
    }

    /**
     * Initialize an instance of {@link GsonProvider} with specified date time
     * format
     * 
     * @param format
     *            the date time format used in JSON to be deserialized
     */
    public GsonProvider(String format) {
        dateTimeFormat = format;
    }

    /**
     * Deserializes the specified Json into an object of the specified class
     * 
     * @param <T>
     *            the type of the desired object
     * @param json
     *            the string from which the object is to be deserialized
     * 
     * @param clazz
     *            the class of T
     * @return an object of type T from the string
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return getFromGson().fromJson(json, clazz);
    }

    private Gson getFromGson() {
        return new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new CustomJodaDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new JodaLocalDateJsonAdapter())
                .registerTypeAdapter(LocalTime.class, new JodaLocalTimeJsonAdapter())
                .registerTypeAdapter(LocalDateTime.class, new CustomLocalDateTimeAdapter())
                .registerTypeAdapter(Date.class, new CustomJavaDateAdapter(dateTimeFormat))
                .create();
    }
}