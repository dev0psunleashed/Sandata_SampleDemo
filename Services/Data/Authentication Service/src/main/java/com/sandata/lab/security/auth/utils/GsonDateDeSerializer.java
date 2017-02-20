package com.sandata.lab.security.auth.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonDateDeSerializer implements JsonDeserializer<Date> {


    private SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
    private SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    private SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");



    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String j = json.getAsJsonPrimitive().getAsString();
            return parseDate(j);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        if (dateString != null && dateString.trim().length() > 0) {
            try {
                return format3.parse(dateString.replaceAll("Z$", "+0000"));
            } catch (ParseException pe) {

                try {

                    return format2.parse(dateString);
                }catch (ParseException e){

                    return format1.parse(dateString);
                }
            }
        } else {
            return null;
        }
    }

}
