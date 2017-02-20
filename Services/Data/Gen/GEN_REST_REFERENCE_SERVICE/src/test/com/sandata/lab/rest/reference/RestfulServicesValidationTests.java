package com.sandata.lab.rest.reference;

import com.sandata.lab.common.utils.tools.AbbreviationUtil;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Validate that all restful endpoints are correct.
 *
 * Are they referencing an existing table?
 * Are there missing endpoints?
 * <p/>
 *
 * @author David Rutgos
 */
public class RestfulServicesValidationTests extends BaseTestSupport {

    private final String MODEL_PACKAGE = "com.sandata.lab.data.model.dl.model";

    private AbbreviationUtil abbreviationUtil;

    private RestfulServices services;

    @Test
    public void should_validate_restful_service_methods() throws Exception {

        for (Method method : services.getClass().getMethods()) {

            if (method.getName().startsWith("PKG_")) {

                System.out.println(String.format("Validate: [%s]", method.getName()));

                String[] methodParts = method.getName().split("_");

                String packageName = methodParts[0] + "_" + methodParts[1];
                String methodName = methodParts[2];
                String entityName = methodParts[3];

                boolean hasOracleMetadataAnnotation = false;

                String clazz = String.format("%s.%s", MODEL_PACKAGE, entityName);
                Object instance = Class.forName(clazz).getConstructor().newInstance();
                for (Annotation annotation : instance.getClass().getDeclaredAnnotations()) {

                    if (annotation instanceof OracleMetadata) {
                        hasOracleMetadataAnnotation = true;

                        System.out.println(String.format("\t[OracleMetadata] ... OK", packageName));

                        OracleMetadata oracleMetadata = (OracleMetadata) annotation;
                        if (oracleMetadata.packageName().equals(packageName)) {
                            System.out.println(String.format("\t[%s] ... OK", packageName));
                        } else {
                            System.out.println(String.format("\t[%s][%s] ... ERROR", packageName, oracleMetadata.packageName()));
                        }

                        if (methodName.startsWith("insert")) {

                            if (methodName.equals(oracleMetadata.insertMethod())) {
                                System.out.println(String.format("\t[%s] ... OK", methodName));

                                if (method.getParameterTypes() != null && method.getParameterTypes().length == 1) {
                                    Class<?> param = method.getParameterTypes()[0];
                                    if(param.getName().equals(clazz)) {
                                        System.out.println(String.format("\tParam: [%s] ... OK", param.getName()));
                                    } else {
                                        System.out.println(String.format("\tParam: [%s][%s] ... ERROR", clazz, param.getName()));
                                    }
                                } else {
                                    System.out.println(String.format("\tParam: [MISSING]... ERROR"));
                                }

                            } else {
                                System.out.println(String.format("\t[%s][%s] ... ERROR", methodName, oracleMetadata.insertMethod()));
                            }
                        }
                        else if (methodName.startsWith("update")) {
                            if (methodName.equals(oracleMetadata.updateMethod())) {
                                System.out.println(String.format("\t[%s] ... OK", methodName));

                                if (method.getParameterTypes() != null && method.getParameterTypes().length == 1) {
                                    Class<?> param = method.getParameterTypes()[0];
                                    if(param.getName().equals(clazz)) {
                                        System.out.println(String.format("\tParam: [%s] ... OK", param.getName()));
                                    } else {
                                        System.out.println(String.format("\tParam: [%s][%s] ... ERROR", clazz, param.getName()));
                                    }
                                } else {
                                    System.out.println(String.format("\tParam: [MISSING]... ERROR"));
                                }

                            } else {
                                System.out.println(String.format("\t[%s][%s] ... ERROR", methodName, oracleMetadata.updateMethod()));
                            }
                        }
                        else if (methodName.startsWith("delete")) {
                            if (methodName.equals(oracleMetadata.deleteMethod())) {
                                System.out.println(String.format("\t[%s] ... OK", methodName));
                            } else {
                                System.out.println(String.format("\t[%s][%s] ... ERROR", methodName, oracleMetadata.deleteMethod()));
                            }
                        }
                        else if (methodName.startsWith("get")) {
                            if (methodName.equals(oracleMetadata.getMethod())) {
                                System.out.println(String.format("\t[%s] ... OK", methodName));
                            } else {
                                System.out.println(String.format("\t[%s][%s] ... ERROR", methodName, oracleMetadata.getMethod()));
                            }
                        }
                        else {
                            System.out.println(String.format("\t[%s] ... UNKNOWN", methodName));
                        }
                    }
                }

                if (!hasOracleMetadataAnnotation) {
                    System.out.println(String.format("\t[OracleMetadata] ... ERROR", packageName));
                }

                String tableName = AbbreviationUtil.ToPhysical(entityName).toUpperCase();

                /*for (Class<?> param : method.getParameterTypes()) {

                    System.out.println("\t" + param.getName());
                }*/
            }
        }
    }

    @Override
    protected void onSetup() throws Exception {
        abbreviationUtil = new AbbreviationUtil();
        AbbreviationUtil.Init(abbreviationUtil);
        services = new RestfulServices();
    }
}
