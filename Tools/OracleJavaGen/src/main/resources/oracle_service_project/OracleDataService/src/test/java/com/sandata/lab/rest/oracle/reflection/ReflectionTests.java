package com.sandata.lab.rest.oracle.reflection;

import com.sandata.lab.rest.oracle.BaseTestSupport;
import com.sandata.lab.rest.oracle.annotation.Mapping;
import com.sandata.lab.rest.oracle.annotation.OracleMetadata;
import com.sandata.lab.rest.oracle.jpub.model.PatientTyp;
import com.sandata.lab.rest.oracle.model.Patient;
import com.sandata.lab.rest.oracle.model.PatientService;
import org.junit.Assert;
import org.junit.Test;

import java.beans.Statement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Date: 8/31/15
 * Time: 8:57 PM
 */

public class ReflectionTests extends BaseTestSupport {

    //@Mapping(getter = "getPatientId", setter = "setPatientId")

    @Test
    public void should_use_reflection_to_analyze_object() throws Exception {

        Patient patient = new Patient();
        patient.setPatientID("555");

        PatientService patientService = new PatientService();
        patientService.setPatientID("494949");
        patient.getPatientService().add(patientService);

        OracleMetadata oracleMetadata = patient.getClass().getAnnotation(OracleMetadata.class);

        Object type = Class.forName(oracleMetadata.jpubClass())
                .getConstructor()
                .newInstance();

        for (Field field : patient.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            Object data = field.get(patient);
            if (data != null && data instanceof List) {

                List list = (List)data;
                for (Object obj : list) {
                    System.out.println("**** Data is of type: " + obj.getClass().getName() + " ****");
                }
            }

            if (field.isAnnotationPresent(Mapping.class)) {

                Mapping mapping = field.getAnnotation(Mapping.class);

                PatientTyp patientTyp = (PatientTyp)type;

                Assert.assertTrue(patientTyp.getPatientId() == null);

                Object value = field.get(patient);
                Statement statement = new Statement(type, mapping.setter(), new Object[] {value});
                statement.execute();

                Assert.assertTrue(patientTyp.getPatientId().equals("555"));
            }


        }

    }

    @Override
    protected void onSetup() throws Exception {

    }
}
