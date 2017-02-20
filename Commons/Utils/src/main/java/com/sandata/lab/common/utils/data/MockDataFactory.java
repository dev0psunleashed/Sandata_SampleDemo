package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.data.model.dl.annotation.Mapping;

import java.lang.reflect.Field;

/**
 * Date: 5/30/16
 * Time: 5:36 PM
 */

public class MockDataFactory {

    public static Object Mock(String clazz, String id, Table table) throws Exception {

        Object instance = Class.forName(clazz).getConstructor().newInstance();

        for (Field field : instance.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (SkipField(field)) {
                continue;
            }

            Object data = new DataFactory().createObject(field, instance);

            Mapping mapping = DataMapper.getMapping(field);

            if ((mapping.index() + 1) == table.tableIdColumnIndex()) {
                field.set(instance, id);
            } else {
                field.set(instance, data);
            }
        }

        return instance;
    }

    public static Object Mock(String clazz) throws Exception {

        Object instance = Class.forName(clazz).getConstructor().newInstance();

        for (Field field : instance.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (SkipField(field)) {
                continue;
            }

            Object data = new DataFactory().createObject(field, instance);

            field.set(instance, data);
        }

        return instance;
    }

    private static boolean SkipField(Field field) {

        // Skip
        if (field.getName().equals("serialVersionUID")) {
            return true;
        }

        // Skip
        if (field.getType().getName().endsWith("List")) {
            return true;
        }

        // Skip
        if (field.getType().getName().contains("[B")) {
            // BLOB
            return true;
        }

        return false;
    }
}
