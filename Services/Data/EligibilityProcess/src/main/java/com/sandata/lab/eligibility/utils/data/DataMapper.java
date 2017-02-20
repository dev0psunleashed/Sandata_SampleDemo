package com.sandata.lab.eligibility.utils.data;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.GenderTypeName;
import com.sandata.lab.eligibility.annotation.Mapping;
import com.sandata.lab.eligibility.model.inquiry.Gender;
import com.sandata.lab.eligibility.utils.log.LoggingUtils;

/**
 * A mapping class to map records getting from database to objects
 */
public class DataMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataMapper.class);

    /**
     * Maps record getting from database in {@code Map<String, Object>} to
     * object in specified {@code className}
     * 
     * @param record
     *            the record getting from database in
     *            {@code Map<String, Object>}
     * @param className
     *            the class name of object to which database record is mapped to
     * @return an instance of specified {@code className}
     * @throws SandataRuntimeException
     *             (runtime) if error happens when do mapping
     */
    public Object map(final Map<String, Object> record, final String className) {
        try {
            Object instance = Class.forName(className).getConstructor().newInstance();

            for (Field instanceField : instance.getClass().getDeclaredFields()) {
                instanceField.setAccessible(true);

                if (instanceField.isAnnotationPresent(Mapping.class)) {
                    doMap(record, instance, instanceField);
                }
            }

            return instance;

        } catch(Exception e) {
            String errorMessage = LoggingUtils.getErrorMessageInfo(this, "map",
                    new StringBuilder("An exception happens when mapping a record to class name: ").append(className)
                            .append(", error: ").append(e.getMessage()).toString());
            LOGGER.error(errorMessage, e);
            throw new SandataRuntimeException(errorMessage, e);
        }
    }

    private void doMap(final Map<String, Object> record, final Object instance, final Field instanceField) throws IllegalArgumentException, IllegalAccessException {
        Mapping mapping = instanceField.getAnnotation(Mapping.class);

        String columnName = mapping.columnName();
        Object result = record.get(columnName);

        // change null to empty string
        if (instanceField.getType().isAssignableFrom(String.class) && result == null) {
            result = "";
        }

        Object enumObj = null;
        if (instanceField.getType().isEnum()) {
            enumObj = getEnumObject(result, instanceField, mapping);
        }

        if (enumObj != null) {
            instanceField.set(instance, enumObj);
        } else if (!instanceField.getType().isEnum()) {
            instanceField.set(instance, result);
        }
    }

    private Object getEnumObject(Object result, Field instanceField, Mapping mapping) {
        Object enumObj = null;
        try {
            if (result != null && "com.sandata.lab.data.model.dl.model.GenderTypeName".equals(mapping.sourceEnum())
                    && instanceField.getType().equals(Gender.class)) {
                String enumValue = (String) result;
                enumObj = mapToGender(GenderTypeName.fromValue(enumValue));
            }

        } catch (Exception e) {
            LOGGER.warn(
                    LoggingUtils.getLogMessageInfo(this, "getEnumObject",
                            "Set ENUM Value to NULL since the string [{}] did not match the expected ENUM value for {}"),
                    result, mapping.sourceEnum(), e);
        }

        return enumObj;
    }

    private Gender mapToGender(GenderTypeName genderTypeName) {
        switch (genderTypeName) {
            case MALE:
                return Gender.Male;
            case FEMALE:
                return Gender.Female;
            case OTHER:
                return Gender.Unknown;
            default:
                return Gender.Unknown;
        }
    }
}
