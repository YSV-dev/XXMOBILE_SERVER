package com.swlibs.common.annatation;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Отмечает паттерн Singleton.
 */
@Documented
@Target(value={TYPE})
public @interface Singleton {}
