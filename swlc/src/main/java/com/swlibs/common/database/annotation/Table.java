package com.swlibs.common.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Заменено на JPA.
 */
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    //String DEFAULT_ID_NAME = "null";

    String name();

    String id() default "null";

    String serverId() default "null";
}