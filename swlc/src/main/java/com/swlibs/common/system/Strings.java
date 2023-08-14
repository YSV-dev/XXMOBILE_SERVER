package com.swlibs.common.system;

import java.util.Locale;

public class Strings {

    public static final Locale RUSSIAN = new Locale("ru", "RU");

    public static boolean isEmpty(String value) {
        if(value == null) {
            return true;
        } else {
            return value.equals("");
        }
    }

    public static boolean isNotEmpty(String value) {
        if(value == null) {
            return false;
        } else {
            return !value.equals("");
        }
    }

    public static String asNullable(String value) {
        if("".equals(value)) {
            return null;
        } else {
            return value;
        }
    }

    public static String fromValue(Long value){
        if(value != null) {
            return Long.toString(value);
        } else {
            return "";
        }
    }

    public static String fromValue(Integer value){
        if(value != null) {
            return Integer.toString(value);
        } else {
            return "";
        }
    }

    public static String fromValue(Object value){
        if(value != null) {
            return value.toString();
        } else {
            return "";
        }
    }

}
