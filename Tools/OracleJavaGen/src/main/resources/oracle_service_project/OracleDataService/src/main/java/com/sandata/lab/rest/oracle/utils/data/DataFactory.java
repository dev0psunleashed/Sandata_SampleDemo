package com.sandata.lab.rest.oracle.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.rest.oracle.model.Patient;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Date: 9/1/15
 * Time: 1:17 PM
 */
@SuppressWarnings("unchecked")
public class DataFactory<T> extends Factory<T> {

    // TODO: Rename this... this is specifically for patient related items that have patientId and businessEntityId as PK fields
    public Object createOnlyOne(final Class<?> type, final String patientId, final String businessEntityId) throws Exception {

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

        Field patientIdField = instance.getClass().getDeclaredField("patientID");
        patientIdField.setAccessible(true);
        patientIdField.set(instance, patientId);

        Field businessEntityIdField = instance.getClass().getDeclaredField("businessEntityID");
        businessEntityIdField.setAccessible(true);
        businessEntityIdField.set(instance, businessEntityId);

        return instance;
    }

    public Patient createOnlyPatient(final String patientId, final String businessEntityId) throws Exception {

        Patient patient = new Patient();

        for (Field field : patient.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            // Skip
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            // Skip
            if (field.getType().getName().endsWith("List")) {
                continue;
            }

            Object data = createObject(field, patient);

            field.set(patient, data);
        }

        patient.setPatientID(patientId);
        patient.setBusinessEntityID(businessEntityId);

        return patient;
    }

    public Patient createPatient() throws Exception {

        Patient patient = new Patient();
        patient.setPatientID("1");
        patient.setBusinessEntityID("1");

        for (Field field : patient.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            // Skip
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            if (field.getType().getName().endsWith("List")) {

                Object listData = createList(field, patient);

                field.set(patient, listData);
            }
            else {

                Object data = createObject(field, patient);

                field.set(patient, data);
            }

            //System.out.println("Field: [" + field.getName() + "][Type: " + field.getType().getName() + "]");
        }

        return patient;
    }

    // TODO: Below code is not working as expected!!!

    // Returns the actual type for 'T'
    private Type getType() {

        Type superType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)superType).getActualTypeArguments()[0];

        return type;
    }

    public T createWithData() throws SandataRuntimeException {

        try {
            String className = getType().toString();
            return (T)(Class.forName(className)).getConstructor().newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("DataFactory: %s: %s", getClass().getName(), e.getMessage()));
        }
    }
}
