package com.swlibs.common.system;

public class Arrays {

    public static <T> boolean contains(final T[] array, final T v) {
        for (final T e : array)
            if (e == v || v != null && v.equals(e))
                return true;

        return false;
    }

    public static <T> boolean containsNull(final T[] array) {
        for (final T e : array)
            if (e == null)
                return true;

        return false;
    }

}
