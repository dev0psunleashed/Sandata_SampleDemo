package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;

import java.beans.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Date: 9/1/15
 * Time: 5:46 PM
 */
@SuppressWarnings("unchecked")
public class DataMapper {

    public Object map(final ResultSet resultSet, final String className) throws SandataRuntimeException {

        try {
            List list = new ArrayList<>();

            while (resultSet.next()) {

                Object instance = Class.forName(className).getConstructor().newInstance();

                for (Field instanceField : instance.getClass().getDeclaredFields()) {

                    instanceField.setAccessible(true);

                    if (instanceField.isAnnotationPresent(Mapping.class)) {

                        Mapping mapping = getMapping(instanceField);

                        int columnIndex = mapping.index() + 1;
                        Object result = resultSet.getObject(columnIndex);

                        Object enumObj = null;
                        if (result instanceof BigDecimal
                                && !instanceField.getType().equals(BigDecimal.class)) {

                            if (instanceField.getType().equals(BigInteger.class)) {
                                result = BigInteger.valueOf(((BigDecimal) result).longValue());
                            }
                            //dmr Bug Fix: GEORG-672
                            if (instanceField.getType().toString().equals("boolean")) {
                                result = ((BigDecimal) result).intValue() == 0 ? false : true;
                            }
                        } else if (instanceField.getType().isEnum()) {

                            try {
                                if (result != null) {
                                    String enumValue = (String) result;

                                    Method valueOfMethod = instanceField.getType().getMethod("fromValue", String.class);
                                    enumObj = valueOfMethod.invoke(Enum.class, enumValue.toUpperCase());
                                }

                            } catch (Exception ex) {

                                ex.printStackTrace();

                                System.out.println(String.format("DataMapper: Set ENUM Value to NULL since the string " +
                                        "[%s] did not match the expected ENUM value!", result.toString()));
                                result = null;
                            }
                        }

                        try {
                            if (enumObj != null) {
                                instanceField.set(instance, enumObj);
                            }
                            else {
                                instanceField.set(instance, result);
                            }
                        }
                        catch (IllegalArgumentException ille) {

                            // Hack-a-rooh :)

                            //java.lang.IllegalArgumentException: Can not set java.math.BigDecimal field com.sandata.lab.rest.oracle.model.PatientContactDetail.patientPostalCode to java.math.BigInteger
                            if (ille.getMessage().contains("Can not set java.math.BigInteger")) {
                                instanceField.set(instance, BigInteger.valueOf(((BigDecimal) result).longValue()));
                            }
                            else if (ille.getMessage().contains("Can not set java.math.BigDecimal")) {
                                instanceField.set(instance, BigDecimal.valueOf(((BigInteger) result).longValue()));
                            }
                            else if(ille.getMessage().contains("Can not set java.lang.Boolean") && ille.getMessage().contains("java.math.BigInteger")) {
                                instanceField.set(instance, Boolean.valueOf(((BigInteger) result).longValue() == 1));
                            }
                            else if(ille.getMessage().contains("Can not set java.lang.Boolean") && ille.getMessage().contains("java.math.BigDecimal")) {
                                instanceField.set(instance, Boolean.valueOf(((BigDecimal) result).longValue() == 1));
                            }
                        }
                    }
                }

                list.add(instance);
            }

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: map: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

    public Object map(final Object data) throws SandataRuntimeException {

        try {
            OracleMetadata oracleMetadata = getOracleMetadata(data);

            String jpubClass = oracleMetadata.jpubClass();

            Object jpubInstance = Class.forName(jpubClass).getConstructor().newInstance();

            for (Field dataField : data.getClass().getDeclaredFields()) {

                dataField.setAccessible(true);

                // Skip
                if (dataField.getName().equals("serialVersionUID")) {
                    continue;
                }

                if (Factory.IsSimpleType(dataField)) {

                    Mapping mapping = getMapping(dataField);

                    Object value = dataField.get(data);

                    // HACK..
                    if (value instanceof BigInteger) {

                        // JPub makes every int/long/etc...to BigDecimal... I think...
                        BigDecimal bigDecimal = BigDecimal.valueOf(((BigInteger)value).longValue());
                        value = bigDecimal;
                    }
                    if (value instanceof Boolean) {

                        BigDecimal bigDecimal = BigDecimal.valueOf(((Boolean)value) ? 1 : 0);
                        value = bigDecimal;
                    }
                    else if (value instanceof Date) {

                        Timestamp timestamp = new Timestamp(((Date)value).getTime());
                        value = timestamp;
                    }
                    else if (value instanceof Enum) {

                        //For example EmploymentStatusTypeName.NOT_OFFERED_EMPLOYMENT("NOT OFFERED EMPLOYMENT");

                        //the expected value is "NOT OFFERED EMPLOYMENT"
                        Object customEnumValue = null;
                        try {
                            if (value != null) {
                                Method valueOfMethod = value.getClass().getMethod("value");
                                customEnumValue = valueOfMethod.invoke(value);

                                value = customEnumValue;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println(String.format("DataMapper: No value() method defined for the ENUM [%s] ", value.getClass().getSimpleName()));
                        }

                        //In worse case, if NOT OFFERED EMPLOYMENT is not defined, returns NOT_OFFERED_EMPLOYMENT
                        if(customEnumValue == null){
                            value = ((Enum)value).name();
                        }
                    }

                    Statement statement = new Statement(jpubInstance, mapping.setter(), new Object[]{value});
                    statement.execute();
                }
            }

            return jpubInstance;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: map: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

    public static Mapping getMapping(final Field field) throws SandataRuntimeException {

        if (!field.isAnnotationPresent(Mapping.class)) {

            throw new SandataRuntimeException(
                    String.format("DataMapper: getMapping: %s: Expecting @Mapping annotation!",
                            field.getName()));
        }

        return field.getAnnotation(Mapping.class);

    }

    public static OracleMetadata getOracleMetadata(final Object target) throws SandataRuntimeException {

        if (!target.getClass().isAnnotationPresent(OracleMetadata.class)) {

            throw new SandataRuntimeException(
                    String.format("DataMapper: getOracleMetadata: %s: Expecting @OracleMetadata annotation!",
                            target.getClass().getName()));
        }

        return target.getClass().getAnnotation(OracleMetadata.class);
    }

	public Object mapWithOffset(final ResultSet resultSet, final String className, final int offset) throws SandataRuntimeException {

        try {
            List list = new ArrayList<>();

            while (resultSet.next()) {

                Object instance = mapResultSet(resultSet, className, offset);
                list.add(instance);
            }

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: map: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

    /**
     * Use this method if the resultSet.next() method is called at least once before mapWithOffsetNext is invoked.
     *
     * @param resultSet The REF CURSOR returned from JDBC call.
     * @param className The fully qualified name of the entity to be instantiated.
     * @param offset The number of columns to skip from the resultSet before attempting to map the entity to the columns.
     * @return List of the entities of @className
     * @throws SandataRuntimeException
     */
    public Object mapWithOffsetNext(final ResultSet resultSet, final String className, final int offset) throws SandataRuntimeException {

        try {
            List list = new ArrayList<>();

            //dmr--resultSet.next() is called one time before mapWithOffsetNext is called
            do {

                Object instance = mapResultSet(resultSet, className, offset);
                list.add(instance);

            } while (resultSet.next());

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: map: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

    public Object mapWithOffsetEntityNext(final ResultSet resultSet, final String className, final int offset) throws SandataRuntimeException {

        try {
            return mapResultSet(resultSet, className, offset);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: map: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

	public Object mapResultSet(final ResultSet resultSet, final String className, final int offset)
                                    throws SandataRuntimeException {

        try {
                Object instance = Class.forName(className).getConstructor().newInstance();

                for (Field instanceField : instance.getClass().getDeclaredFields()) {

                    instanceField.setAccessible(true);

                    if (instanceField.isAnnotationPresent(Mapping.class)) {

                        Mapping mapping = getMapping(instanceField);

                        int columnIndex = mapping.index() + 1 + offset;
                        Object result = resultSet.getObject(columnIndex);

                        Object enumObj = null;

                        if (result instanceof BigDecimal
                                && !instanceField.getType().equals(BigDecimal.class)) {

                            if (instanceField.getType().equals(BigInteger.class)) {
                                result = BigInteger.valueOf(((BigDecimal) result).longValue());
                            }
                            //dmr Bug Fix: GEORG-672
                            if (instanceField.getType().toString().equals("boolean")) {
                                result = ((BigDecimal) result).intValue() == 0 ? false : true;
                            }
                        } else if (instanceField.getType().isEnum()) {

                            try {
                                if (result != null) {
                                    String enumValue = (String) result;

                                    Method valueOfMethod = instanceField.getType().getMethod("fromValue", String.class);
                                    enumObj = valueOfMethod.invoke(Enum.class, enumValue.toUpperCase());
                                }

                            } catch (Exception ex) {

                                ex.printStackTrace();

                                System.out.println(String.format("DataMapper: Set ENUM Value to NULL since the string " +
                                        "[%s] did not match the expected ENUM value!", result.toString()));
                                result = null;
                            }
                        }

                        try {
                            if (enumObj != null) {
                                instanceField.set(instance, enumObj);
                            }
                            else {
                                instanceField.set(instance, result);
                            }
                        }
                        catch (IllegalArgumentException ille) {

                            // Hack-a-rooh :)

                            //java.lang.IllegalArgumentException: Can not set java.math.BigDecimal field com.sandata.lab.rest.oracle.model.PatientContactDetail.patientPostalCode to java.math.BigInteger
                            if (ille.getMessage().contains("Can not set java.math.BigInteger")) {
                                instanceField.set(instance, BigInteger.valueOf(((BigDecimal) result).longValue()));
                            }
                            else if (ille.getMessage().contains("Can not set java.math.BigDecimal")) {
                                instanceField.set(instance, BigDecimal.valueOf(((BigInteger) result).longValue()));
                            }
                            else if(ille.getMessage().contains("Can not set java.lang.Boolean") && ille.getMessage().contains("java.math.BigInteger")) {
                                instanceField.set(instance, Boolean.valueOf(((BigInteger) result).longValue() == 1));
                            }
                            else if(ille.getMessage().contains("Can not set java.lang.Boolean") && ille.getMessage().contains("java.math.BigDecimal")) {
                                instanceField.set(instance, Boolean.valueOf(((BigDecimal) result).longValue() == 1));
                            }
                        }
                    }
                }

            return instance;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(
                    String.format("DataMapper: mapResultSet: %s: %s",
                            e.getClass().getName(), e.getMessage()));
        }
    }

    public static void Copy(Object source, Object target) throws SandataRuntimeException {

        try {

            for (Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    Field copyField = target.getClass().getDeclaredField(field.getName());
                    copyField.setAccessible(true);
                    if (copyField.getName().equals("serialVersionUID")) {
                        continue;
                    }

                    copyField.set(target, field.get(source));
                } catch (NoSuchFieldException nsfe) {
                    // Not a problem, the entities are not completely similar
                }
            }

        } catch (Exception e) {
            throw new SandataRuntimeException(format("%s: CopySimilar: ERROR: %s",
                    e.getClass().getName(), e.getMessage()));
        }
    }

    public static BigInteger GetSK(Object data) {

        if (data == null) {
            return null;
        }

        try {

            String type = data.getClass().getSimpleName();
            String skValueParamName = StringUtil.FirstCharToLowercase(type) + "SK";
            Field skValueField = data.getClass().getDeclaredField(skValueParamName);
            skValueField.setAccessible(true);
            return (BigInteger)skValueField.get(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String GetBusinessEntityID(Object data) {

        if (data == null) {
            return null;
        }

        try {

            Field field = data.getClass().getDeclaredField("businessEntityID");
            field.setAccessible(true);
            return (String)field.get(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
