package com.swlibs.common.system;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Стандартная рефлексия, не содержит некоторых очевидных решений, которые содержаться здесь.
 */
public class Reflection {

    /**
     * Возвращает поле из класса или из супер классов
     */
    public static Field getField(Class<?> type, String fieldName)
            throws NoSuchFieldException {
        try {
            return type.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = type.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    /**
     * Возвращает значение поля класса или одного из супер классов.
     * игнорирует исключения.
     *
     * @param object где искать поле
     * @param fieldName имя поля
     * @return значение
     */
    public static Object getFieldValue(Object object, String fieldName){
        try {
            Field field = getField(object.getClass(),fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return null;
        }
    }

    /**
     * Устанавливает значение поля класса или одного из супер классов.
     * игнорирует исключения.
     *
     * @param object где искать поле
     * @param fieldName имя поля
     * @param value значение
     */
    public static void setFieldValue(Object object, String fieldName, Object value){
        try {
            Field field = getField(object.getClass(),fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) { }
    }

    /**
     * Проверяет, наследует ли класс {@code superClass}.
     *
     * @param type где искать суперкласс
     * @param superClass суперкласс
     * @return true if is subclass
     */
    public static boolean isSubclassOf(Class<?> type, Class<?> superClass) {
        if (type.getSuperclass() != null) {
            if (type.getSuperclass().equals(superClass)) {
                return true;
            }
            return isSubclassOf(type.getSuperclass(), superClass);
        }

        return false;
    }

    /**
     * Возвращает список отсортированных полей класса (включая дочерние),
     * которые можно сериализовать {@code annotationTypes}.
     *
     * @param type с полями
     * @return список полей
     */
    public static Set<Field> getSerializableFields(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        Set<Field> declaredColumnFields = new LinkedHashSet<>();
        for (Field field : fields) {
            if(
                    !Modifier.isStatic(field.getModifiers())
                    &&
                    !Modifier.isTransient(field.getModifiers())
            ) {
                declaredColumnFields.add(field);
            }
        }
        Class<?> parentType = type.getSuperclass();
        if (parentType != null) {
            declaredColumnFields.addAll(getSerializableFields(parentType));
        }
        return declaredColumnFields;
    }

    /**
     * Возвращает список отсортированных полей класса (включая дочерние),
     * с перечисленными аннотациями {@code annotationTypes}.
     *
     * @param type с полями
     * @param annotationTypes список аннотаций
     * @return список полей
     */
    public static Set<Field> getAnnotationsFields(Class<?> type, List<Class<? extends Annotation>> annotationTypes) {
        Field[] fields = type.getDeclaredFields();
        //Ссортируем поля
        Arrays.sort(fields, (field1, field2) -> {
            return field2.getName().compareTo(field1.getName());
        });
        Set<Field> declaredColumnFields = new LinkedHashSet<>();
        for (Field field : fields) {
            for(Class<? extends Annotation> annotationType : annotationTypes) {
                if (field.isAnnotationPresent(annotationType)) {
                    declaredColumnFields.add(field);
                    break;
                }
            }
        }
        Class<?> parentType = type.getSuperclass();
        if (parentType != null) {
            declaredColumnFields.addAll(getAnnotationsFields(parentType, annotationTypes));
        }
        return declaredColumnFields;
    }

}
