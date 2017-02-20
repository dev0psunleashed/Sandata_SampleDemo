package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date: 9/1/15
 * Time: 1:52 PM
 */

public abstract class Factory<T> {

    protected static String modelPackage = "com.sandata.lab.rest.oracle.model";

    public Object createList(Field field, Object parent) throws Exception {

        List listData = new ArrayList();

        String fieldName = field.getName();

        String modelClass = modelPackage + "." + fieldName.substring(0,1).toUpperCase() +
                fieldName.substring(1, fieldName.length());

        System.out.println("*** createList for: [" + modelClass + "] ***");

        // TODO: The following classes do not have the required OracleMetadata... Fix this!
        boolean skipClass = false;
        if (modelClass.contains("ScheduleEvent")
                || modelClass.contains("Authorization")
                || modelClass.contains("ExcludedPatients")
                || modelClass.contains("StaffPatientHistory")
                || modelClass.contains("Eligibility")
                || modelClass.contains("Reference")
                || modelClass.contains("Visit")
                || modelClass.contains("PlanOfCare")
                || modelClass.contains("BillingRate")

                // PatientIntake
                || modelClass.contains("AdmissionPeriod")
                || modelClass.contains("AdmissionPeriodDiagnosis")
                ) {
            skipClass = true;
        }

        if (!parent.getClass().isAnnotationPresent(OracleMetadata.class) && !skipClass) {
            throw new SandataRuntimeException(
                    String.format("DataFactory: createList: %s: Expecting @OracleMetadata annotation!",
                            parent.getClass().getName()));
        }

        // TODO: Temporary skipClass logic...
        if (!skipClass) {
            OracleMetadata oracleMetadata = parent.getClass().getAnnotation(OracleMetadata.class);

            if (oracleMetadata.primaryKeys().length == 0) {
                throw new SandataRuntimeException(
                        String.format("DataFactory: createList: %s: @OracleMetadata primaryKeys is required!",
                                parent.getClass().getName()));
            }

            Map primaryKeysMap = new HashMap<>();
            for (String primaryKey : oracleMetadata.primaryKeys()) {

                Field primaryKeyField = parent.getClass().getDeclaredField(primaryKey);
                primaryKeyField.setAccessible(true);
                primaryKeysMap.put(primaryKey, primaryKeyField.get(parent));
            }

            Object instance = Class.forName(modelClass).getConstructor().newInstance();

            for (Field instanceField : instance.getClass().getDeclaredFields()) {

                instanceField.setAccessible(true);

                // Skip
                if (instanceField.getName().equals("serialVersionUID")) {
                    continue;
                }

                // check if this is a primary field
                Object value = primaryKeysMap.get(instanceField.getName());
                if (value != null) {

                    instanceField.set(instance, value);
                } else {

                    // BLOB field
                    if (instanceField.getType().getName().equals("[B")) {
                        System.out.println("BLOB Field! *** Set to NULL ***");

                        instanceField.set(instance, null);

                    } else {

                        Object data = createObject(instanceField, instance);

                        System.out.println(String.format("**** %s [Set: %s] ****",
                                instanceField.getType().getName(), data.toString()));

                        instanceField.set(instance, data);
                    }
                }

            }

            listData.add(instance);
        }

        return listData;
    }

    public Object createObject(final Field instanceField, final Object parent) throws Exception {

        return createObject(instanceField, parent, true);
    }

    public Object createObject(final Field instanceField, final Object parent, boolean initLists) throws Exception {

        instanceField.setAccessible(true);
        Class<?> clazz = instanceField.getType();

        if (clazz.getName().equals("java.lang.String")) {

            return "1";
        }

        if (clazz.getName().equals("java.math.BigInteger")) {

            return BigInteger.valueOf(0);
        }

        if (clazz.getName().equals("java.math.BigDecimal")) {

            return BigDecimal.valueOf(0);
        }

        if (clazz.getName().equals("java.lang.Boolean")
                || clazz.getName().equals("boolean")) {

            return false;
        }

        if (clazz.getName().equals("java.util.Date")) {

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");

            return date;
        }

        if (clazz.getName().equals("java.util.List")) {

            if (initLists) {
                // WARNING: Recursive!!
                Object objectlist = createList(instanceField, parent);

                return objectlist;
            }
        }

        return new Object();
    }

    public static boolean IsSimpleType(final Field instanceField) {

        instanceField.setAccessible(true);

        Class<?> clazz = instanceField.getType();

        if (clazz.getName().equals("java.lang.String")) {
            return true;
        }

        if (clazz.getName().equals("java.math.BigInteger")) {
            return true;
        }

        if (clazz.getName().equals("java.math.BigDecimal")) {
            return true;
        }

        if (clazz.getName().equals("java.lang.Boolean")
                || clazz.getName().equals("boolean")) {
            return true;
        }

        if (clazz.getName().equals("java.util.Date")) {
            return true;
        }

        if (instanceField.getType().isEnum()) {
            return true;
        }

        if (!clazz.getName().equals("java.util.List")) {
            System.out.println(String.format("Factory: IsSimpleType: >>>>>> Not a Simple Type: [%s] <<<<<<",
                    clazz.getName()));
        }

        return false;
    }

}
