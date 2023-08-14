/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uralkali.server.utils;


import com.swlibs.common.database.TableInfo;
import com.swlibs.common.system.Numbers;
import com.swlibs.common.system.Streams;
import com.uralkali.server.Log;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import jakarta.xml.bind.DatatypeConverter;

/**
 *
 * @author brzsmg
 */
public class Pojo {
    
    /**
     * Возвращает сформированный объект
     * Содержит ошибку? 
     * @param <T> Тип self
     * @param c Курсор
     * @param self Класс
     * @return Сформированный объект
     */
    public static <T extends Serializable> T cursorToInstance(ResultSet c, Class<T> self) {
        if (c == null) {
            throw new RuntimeException("ResultSet is null");
        }
        TableInfo tableInfo = TableInfo.getTableInfo(self);
        T instance = null;
        try{
            ResultSetMetaData rsmd = c.getMetaData();
            Collection<Field> fields = tableInfo.getFields();
            //if (c.next()) {//TODO: NEED REMOVE!!
                Object obj;
                try{
                    obj = self.newInstance();
                } catch(IllegalAccessException | InstantiationException ex) {
                    throw new RuntimeException("NewInstance failure.", ex);
                }
                instance = (T)obj;//@SuppressWarnings("unchecked")
                for (Field classField : fields) {
                    for(int i = 1; i <= rsmd.getColumnCount(); i++){
                        if(tableInfo.getColumnName(classField).equals(rsmd.getColumnName(i).toLowerCase())){
                            //classField.set(instance, getCursorValue(c, rsmd, i));
                            Object value = getCursorValue(c, rsmd, i, classField);
                            fieldSetValue(instance, classField, value);
                        }
                    }
                }
            //}
            c.close();
        } catch(SQLException ex) {
            throw new RuntimeException("SQL query failure.", ex);
        }
        return instance;
    }
    
    /**
     * Возвращает список сформированных из курсора объектов 
     * @param <T> Тип self
     * @param c Курсор
     * @param self Класс
     * @return Список сформированных объектов
     */
    public static <T extends Serializable> List<T> cursorToInstances(ResultSet c, Class<T> self) throws SQLException {
        if (c == null) {
            throw new RuntimeException("ResultSet is null");
        }
        TableInfo tableInfo = TableInfo.getTableInfo(self);
        ResultSetMetaData rsmd = c.getMetaData();
        List<T> list = new ArrayList<>(rsmd.getColumnCount());
        Collection<Field> fields = tableInfo.getFields();
        while (c.next()) {
            Object obj;
            try{
                obj = self.newInstance();
            } catch(IllegalAccessException | InstantiationException ex) {
                throw new RuntimeException("NewInstance failure.");
            }
            T instance = (T) obj;
            for (Field classField : fields) {
                Class fieldType = classField.getType();
                //String fieldType = classField.getType().getSimpleName();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (tableInfo.getColumnName(classField).equals(rsmd.getColumnName(i).toLowerCase())) {
                        Object value = null;
                        try {
                            value = getCursorValue(c, rsmd, i, classField);
                            fieldSetValue(instance, classField, value);
                            break;
                        } catch (Exception e) {
                            Log.e(e.getClass().getSimpleName());
                            //System.out.println("[Message]"+e.getMessage());
                            e.printStackTrace();
                            System.out.print("Тип \"" + fieldType.getSimpleName() + "\"");
                            System.out.print(" столбца \"" + tableInfo.getColumnName(classField) + "\" не соответствует");
                            if(value != null) {
                                System.out.print(" \"" + value.getClass().getSimpleName() + "\"");
                            } else {
                                System.out.print(" \"null\"");
                            }
                            System.out.println(".");
                            System.out.println("Класс: "  + self.getClass().getSimpleName());
                            System.out.println("Значение: " + (value == null ? "null" : value.toString()));
                            //Log.e("cast", "Неподдерживаемый тип \"" + fieldType.getSimpleName() + "\" для столбца \"" + tableInfo.getColumnName(classField) + "\".");
                        }

                    }
                }
            }
            list.add(instance);
        }
        return list;
    }
    
    private static void fieldSetValue(Object instance, Field field, Object value) {
        try {
            Class fieldType = field.getType();
            if(value == null) {
                field.set(instance, null);
            } else if (fieldType.equals(Integer.class)) {
                if (value instanceof Integer) {
                    field.set(instance, value);
                } else if (value instanceof String) {
                    Integer v = Integer.parseInt((String)value);
                    field.set(instance, v);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            } else if (fieldType.equals(Long.class)) {
                if (value instanceof Integer) {
                    field.set(instance, (long)((Integer)value));
                } else if (value instanceof Long) {
                    field.set(instance, value);
                } else if (value instanceof String) {
                    Long v = Long.parseLong((String)value);
                    field.set(instance, v);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(Float.class)) {
                if (value instanceof Float) {
                    field.set(instance, value);
                } else if (value instanceof Integer) {
                    field.set(instance, (float)((Integer)value));
                } else if (value instanceof Long) {
                    field.set(instance, (float)((Long)value));
                } else if (value instanceof String) {
                    Float v = Float.parseFloat((String)value);
                    field.set(instance, v);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(String.class)) {
                if (value instanceof String) {
                    field.set(instance, value);
                } else if (value instanceof Blob) {
                    String v;
                    try {
                        byte[] array = Streams.readAllBytes(((Blob) value).getBinaryStream());
                        v = DatatypeConverter.printBase64Binary(array);
                    } catch (SQLException ex) {
                        v = null;
                    }
                    field.set(instance, v);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(Boolean.class)) {
                if (value instanceof Boolean) {
                    field.set(instance, value);
                } else if (value instanceof Integer) {
                    field.set(instance, ((Integer) value).equals(1) ? true : false);
                } else if (value instanceof String) {
                    field.set(instance, ((String) value).equals("Y") ? true : false);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(Date.class)) {
                if(value instanceof Date){
                    //DateTime v = new DateTime(((Date) value).getTime());
                    //v.
                    field.set(instance, value);
                /*} else if(value instanceof String){
                    DateTime v = new DateTime((String) value);
                    field.set(instance, v);*/
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(byte[].class)) {
                if(value instanceof byte[]){
                    field.set(instance, value);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            }else if (fieldType.equals(Byte[].class)) {
                if(value instanceof Byte[]){
                    field.set(instance, value);
                } else {
                    throw new RuntimeException("Не соответствие типов для поля \"" + field.getName() + "\".");
                }
            } else {
                throw new RuntimeException("Неподдерживаемый тип для поля \"" + field.getName() + "\".");
            }
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static Object getCursorValue(ResultSet c, ResultSetMetaData rsmd, int index, Field field) {
        try {
            Class fieldType = field.getType();
            int cType = rsmd.getColumnType(index);
            switch (cType) {
                case Types.NULL:
                    return null;
                case Types.VARCHAR:
                    return c.getString(index);
                case Types.CHAR:
                    return c.getString(index);
                case Types.INTEGER:
                    return (Integer)c.getObject(index);
                case Types.FLOAT:
                    return (Float)c.getObject(index);
                case Types.NUMERIC:
                    BigDecimal v = ((BigDecimal)c.getObject(index));
                    if(v == null){
                        return null;
                    } else {
                        if(fieldType.equals(Float.class)){
                            return v.floatValue();
                        } else {
                            return v.intValue();
                        }
                    }
                case Types.TIMESTAMP:
                    return c.getDate(index);
                case Types.CLOB:
                    Clob clob = c.getClob(index);
                    if(clob != null) {
                        int size = Numbers.safeLongToInt(clob.length());
                        return clob.getSubString(1, size);
                    } else {
                        return null;
                    }
                case Types.BLOB:
                    Blob blob = c.getBlob(index);
                    if(blob != null) {
                        int size = Numbers.safeLongToInt(blob.length());
                        return blob.getBytes(1, size);
                    } else {
                        return null;
                    }
                default:
                    throw new RuntimeException("Неизвестный тип столбца \"" + cType + "\".");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
