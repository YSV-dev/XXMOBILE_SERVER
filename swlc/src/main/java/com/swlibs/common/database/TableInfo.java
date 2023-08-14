package com.swlibs.common.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.persistence.OneToMany;
import com.swlibs.common.database.annotation.LocalId;
import com.swlibs.common.system.Reflection;

/**
 * Собирает информацию о БД структуре объекта с помощью рефлексии.
 * Данные кешируются.
 */
public class TableInfo {
    private static final transient Map<Class, TableInfo> mTableInfo = new LinkedHashMap<>();

    private Class<? extends DatabaseEntity> mType;
    private String mTableName;
    private String mIdName = null;
    private String mLocalIdName = null;
    private String mColumnsString;
    private String mConditionsString;
    //private String mTranslateString;
    private Field mIdField;
    private Field mLocalIdField;
    private Constructor mConstructor;
    private Map<Field, String> mRelationNames = new LinkedHashMap<>();
    private Map<Field, String> mColumnNames = new LinkedHashMap<>();
    //private Map<Field, String> mColumnTranslates = new LinkedHashMap<Field, String>();

    /*public String getTranslate(Field field){
        return null;
    }*/

    public static TableInfo getTableInfo(Class cls){
        TableInfo tableInfo = mTableInfo.get(cls);
        if(tableInfo == null) {
            tableInfo = new TableInfo(cls);
            mTableInfo.put(cls,tableInfo);
        }
        return tableInfo;
    }

    public TableInfo(Class<? extends DatabaseEntity> type) {
        mType = type;
        mColumnsString = " ";
        mConditionsString = " ";
        mConstructor = null;

        for (Constructor constructor : type.getConstructors()) {
            Class<?>[] types = constructor.getParameterTypes();
            if (types.length == 0) {
                mConstructor = constructor;
            }
        }

        Table tableAnnotation = mType.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            mTableName = tableAnnotation.name();
            //mIdName = ((Table) tableAnnotation).id();
            /*if(mIdName.equals("null")) {
                mIdName = null;
            }*/
            //mServerIdName = ((Table) tableAnnotation).serverId();
        }
        if(mLocalIdName != null) {
            if(mLocalIdName.equals("null")) {
                mLocalIdName = mIdName;
            }
        }

        StringBuilder columnsString = new StringBuilder(64);
        StringBuilder conditionsString = new StringBuilder(64);
        //for (Field field : Reflection.getAnnotationsFields(mType, Arrays.asList(Column.class, OneToMany.class))) {

        for (Field field : Reflection.getSerializableFields(mType)) {
            field.setAccessible(true);
            String name = field.getName();
            boolean ignore = false;
            //boolean translate = false;
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Column) {
                    name = ((Column) annotation).name();
                    //translate = ((Column) annotation).translate();
                }
                if (annotation instanceof OneToMany) {
                    String mappedBy = ((OneToMany) annotation).mappedBy();
                    mRelationNames.put(field, mappedBy);
                    ignore = true;
                }
                if (annotation instanceof Id) {
                    mIdField = field;
                    mIdName = name;
                }
                if (annotation instanceof LocalId) {
                    mLocalIdField = field;
                    mLocalIdName = name;
                }
                if(annotation instanceof Transient) {
                    ignore = true;
                }
            }
            if(ignore){
                continue;
            }
            if (name.equals(mIdName)) {
                mIdField = field;
            }
            if (name.equals(mLocalIdName)) {
                mLocalIdField = field;
            }
            mColumnNames.put(field, name);

            if (columnsString.length() > 0) {
                columnsString.append(", ");
                conditionsString.append("and ");
            }
            String columnName = name;
            columnsString.append(columnName).append(" ");
            conditionsString.append(columnName).append(" = ? ");

                    /*if(translate){
                        mTranslateString = "left join translates t1 "
                                + "on t1.table_name = tablename "
                                + "on t1.column_name = columnName "
                                + "on t1.language = language "
                                + "t1.id = tableName.getId";
                        if (!mColumsString.equals("")) {
                            mColumsString += ", ";
                        }
                        mColumsString = "t1.translate as " + columnName + "_translate ";
                    }*/
        }

        mColumnsString = columnsString.toString();
        mConditionsString = conditionsString.toString();

        if(this.getLocalIdName() != null) {
            //по первичному ключу
            mConditionsString = this.getLocalIdName() + " = ? ";
        }
    }

    public String getTableName() {
        return this.mTableName;
    }

    public String getIdName() {
        return this.mIdName;
    }

    public String getLocalIdName() {
        if(mLocalIdName != null) {
            return this.mLocalIdName;
        } else {
            return this.mIdName;
        }
    }

    public Field getIdField() {
        return mIdField;
    }

    public Field getLocalIdField() {
        if(mLocalIdField != null) {
            return mLocalIdField;
        } else {
            return mIdField;
        }
    }

    public Collection<Field> getFields() {
        return this.mColumnNames.keySet();
    }

    public Collection<Field> getRelationFields() {
        return this.mRelationNames.keySet();
    }

    public String getColumnName(Field field) {
        return this.mColumnNames.get(field);
    }

    public String getColumnsString() {
        return mColumnsString;
    }

    public String getConditionsString() {
        return mConditionsString;
    }

    public Constructor getConstructor() {
        return mConstructor;
    }
}
