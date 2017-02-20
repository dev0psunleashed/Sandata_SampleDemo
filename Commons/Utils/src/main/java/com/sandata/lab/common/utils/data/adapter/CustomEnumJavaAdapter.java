package com.sandata.lab.common.utils.data.adapter;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.DefaultLogger;
import com.sandata.lab.common.utils.log.SandataLogger;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by vuto on 8/25/2016.
 */
public class CustomEnumJavaAdapter implements JsonDeserializer<Enum> {

    SandataLogger logger = DefaultLogger.CreateLogger();

    @Override
    public Enum deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            //Accept the value of enum with case-insensitive by UPPER_CASE the input value
            if(type instanceof Class && ((Class<?>)type).isEnum()) {

                try {
                    Method valueOfMethod = ((Class) type).getMethod("fromValue", String.class);
                    return (Enum)valueOfMethod.invoke(Enum.class, jsonElement.getAsString().toUpperCase());
                }catch (Exception ex){
                    logger.warn(String.format("The enum % does not have the method fromValue()", ((Class<Enum>) type).getSimpleName()));
                }

                return Enum.valueOf((Class<Enum>) type, jsonElement.getAsString().toUpperCase());
            }
        } catch (Exception e) {
            Enum[] values = ((Class<Enum>) type).getEnumConstants();
            String message = String.format("Input parameter {%s : %s} which must be in the defined value list [%s] (Case-insensitive support)",
                                            ((Class<Enum>) type).getSimpleName(), jsonElement.getAsString(), getSerializedNameValues(values));
            logger.error(message);
            throw new SandataRuntimeException(message, e);
        }

        return null;
    }

    private String getSerializedNameValues(Enum[] enums) {

        StringBuilder sb = new StringBuilder();
        String separator = "" ;
        try {
            for (Enum item : enums) {
                SerializedName serializedName = item.getDeclaringClass().getField(item.name()).getAnnotation(SerializedName.class);

                sb.append(separator);
                separator = ", ";

                if (serializedName != null) {
                    sb.append("\"").append(serializedName.value()).append("\"");
                } else {
                    sb.append("\"").append(item.name()).append("\"");
                }
            }
        }catch (Exception ex){
            logger.warn(String.format("Exception while getting SerializedName of the enum %", enums[0].getDeclaringClass().getSimpleName()));
        }

        return sb.toString();
    }
}
