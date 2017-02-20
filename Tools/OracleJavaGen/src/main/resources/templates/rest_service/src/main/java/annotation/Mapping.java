package __GROUP_ID__.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 8/31/15
 * Time: 8:49 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapping {

    String setter() default "";
    String getter() default "";
    String type() default "";
    int index() default -1;
}