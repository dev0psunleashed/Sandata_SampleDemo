package com.sandata.lab.tools.oracle.annotation;

import com.sandata.lab.tools.oracle.BaseTestSupport;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by dmrutgos on 8/31/2015.
 */
public class OracleAnnotationTests extends BaseTestSupport {

    @Test
    public void should_validate_that_the_annotation_properties_are_set_correctly() throws Exception {

        TestClass myTestClass = new TestClass();

        Assert.assertTrue(myTestClass.getClass().isAnnotationPresent(OracleMetadata.class));

        Annotation annotation = myTestClass.getClass().getAnnotation(OracleMetadata.class);

        Assert.assertNotNull(annotation);

        OracleMetadata oracleMetadata = (OracleMetadata) annotation;

        System.out.println(oracleMetadata.insertMethod());
        System.out.println(oracleMetadata.updateMethod());
        System.out.println(oracleMetadata.deleteMethod());
        System.out.println(oracleMetadata.getMethod());
        System.out.println(oracleMetadata.packageName());
        System.out.println(oracleMetadata.jpubClass());

        Object type = Class.forName(oracleMetadata.jpubClass())
                .getConstructor()
                .newInstance();

        Assert.assertNotNull(type);


        //Statement statement = new Statement(type, "setPatientId", new Object[] {"123"});
        //statement.execute();

       //PatientTyp patientTyp = (PatientTyp) type;

       //Assert.assertTrue(patientTyp.getPatientId().equals("123"));
    }

    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    @Override
    protected void onSetup() throws Exception {
    }
}
