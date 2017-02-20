package com.sandata.lab.rules.vv.imports.data.transformers;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.*;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import org.joda.time.DateTime;

/**
 * 
 * @author thanhxle
 *
 */
public class CustomJodaDateTimeAdapter implements JsonDeserializer<DateTime>, JsonSerializer<DateTime> {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

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

        try {
           // Convert jsonElement string to JODA DateTime (if in the given format by Javascript new Date().toJSON() method.
            /*if (!jsonElement.isJsonNull()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonElement.getAsString());
                // Conversion successful, return a Joda DateTime object
                return new DateTime(date.getTime());
            }*/
        	
        	final String dateAsString = jsonElement.getAsString();
            if (jsonElement.isJsonNull() && dateAsString.length() == 0) {
               return null;
            } else {
            	 Date date = SIMPLE_DATE_FORMAT.parse(jsonElement.getAsString());
            	 return new DateTime(date.getTime());
            }
        	
        	
        }
        catch (Exception e) {
            // If the jsonElement is the time in milliseconds from Jan 1 1970 try to convert...
            //return new DateTime(jsonElement.getAsLong());
            throw new SandataRuntimeException("Unexpected exception while convert string to JODA Date time");
        }
    }

    @Override
    public JsonElement serialize(DateTime dateTime, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(SIMPLE_DATE_FORMAT.format(dateTime.toDate()));
    }
}
