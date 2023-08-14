package com.swlibs.common.system;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    public static <T> boolean isEmpty(List<T> value) {
        if(value == null) {
            return true;
        } else {
            return value.size() == 0;
        }
    }

    public static <T> boolean isNotEmpty(List<T> value) {
        if(value == null) {
            return false;
        } else {
            return value.size() > 0;
        }
    }

    public static <T> String join(List<T> list) {
        StringBuilder result = new StringBuilder();
        for (T item : list) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(item.toString());
        }
        return result.toString();
    }

    /**
     * Переворачивает список
     */
    public static <T> List<T> reverse(List<T> list) {
        List<T> result = new ArrayList<>();
        for(int i = (list.size() -1); i >= 0 ; i--) {
            result.add(list.get(i));
        }
        return result;
    }

}
