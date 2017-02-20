package com.sandata.lab.common.utils.data;

import java.lang.reflect.Field;

/**
 * Date: 9/1/15
 * Time: 1:17 PM
 */
@SuppressWarnings("unchecked")
public class DataFactory<T> extends Factory<T> {

    public Object createOnlyOne(final Class<?> type, final String referralId) throws Exception {

        Object instance = type.getConstructor().newInstance();

        for (Field field : instance.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            // Skip
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            // Skip
            if (field.getType().getName().endsWith("List")) {
                continue;
            }

            // Skip
            if (field.getType().getName().contains("[B")) {
                // BLOB
                continue;
            }

            Object data = createObject(field, instance);

            field.set(instance, data);
        }

        Field staffIdField = instance.getClass().getDeclaredField("referralID");
        staffIdField.setAccessible(true);
        staffIdField.set(instance, referralId);

        return instance;
    }

	public Object createOnlyOne(final Class<?> type, final String staffId, final String businessEntityId) throws Exception {

        Object instance = type.getConstructor().newInstance();

        for (Field field : instance.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            // Skip
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            // Skip
            if (field.getType().getName().endsWith("List")) {
                continue;
            }

            // Skip
            if (field.getType().getName().contains("[B")) {
                // BLOB
                continue;
            }

            Object data = createObject(field, instance);

            field.set(instance, data);
        }

        Field staffIdField = instance.getClass().getDeclaredField("staffID");
        staffIdField.setAccessible(true);
        staffIdField.set(instance, staffId);

        Field businessEntityIdField = instance.getClass().getDeclaredField("businessEntityID");
        businessEntityIdField.setAccessible(true);
        businessEntityIdField.set(instance, businessEntityId);

        return instance;
    }
}
