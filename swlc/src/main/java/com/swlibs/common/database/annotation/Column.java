package com.swlibs.common.database.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "";

    //ColumnType columnType() default ColumnType.SET_NULL;

    int length() default -1;

    boolean notNull() default false;

    boolean translate() default false;

    boolean localOnly() default false;

    boolean unique() default false;

    String[] uniqueGroups() default {};

    boolean index() default false;

    String[] indexGroups() default {};

    /*public static enum ColumnType {
        SET_NULL,
        DATE,
        NUMBER,
        TEXT;

        private ColumnType() {
        }
    }*/

    public static enum ForeignKeyAction {
        SET_NULL,
        SET_DEFAULT,
        CASCADE,
        RESTRICT,
        NO_ACTION;

        private ForeignKeyAction() {
        }
    }

    public static enum ConflictAction {
        ROLLBACK,
        ABORT,
        FAIL,
        IGNORE,
        REPLACE;

        private ConflictAction() {
        }
    }
}