package com.swlibs.common.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Альтернатива @FunctionalInterface когда методов несколько.
 */
@Target(ElementType.TYPE)
public @interface LambdaInterface {}
