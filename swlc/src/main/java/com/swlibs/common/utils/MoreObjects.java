package com.swlibs.common.utils;


import com.swlibs.common.annatation.NonNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * Дополнительные операции над объектами, которых не хватает в Objects.
 */
public class MoreObjects {

    /**
     * Вернет не нулевое значение.
     */
    @NonNull
    public static <T> T firstNonNull(T first, T second)
    {
        if(first != null) {
            return first;
        } else if(second != null) {
            return second;
        } else {
            throw new NullPointerException();
        }
    }

}
