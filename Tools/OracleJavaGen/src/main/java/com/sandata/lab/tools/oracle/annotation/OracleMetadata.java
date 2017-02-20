package com.sandata.lab.tools.oracle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dmrutgos on 8/31/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OracleMetadata {

    String packageName() default "";
    String insertMethod() default "";
    String updateMethod() default "";
    String deleteMethod() default "";
    String getMethod() default "";
    String findMethod() default "";
    String jpubClass() default "";
}
