package com.swlibs.common.logging;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;


/**
 * Дает возможность указывать Supplier аргументы в log4j.
 * До Java 8, можно использовать интерфейс org.apache.logging.log4j.util.Supplier.
 * Пример:
 * logger.warn("{}", LogArg.create(()->"error=" + getMessage()))
 */
//@RequiredArgsConstructor(staticName = "create")
//public class LogArg {
//    @NonNull private final Supplier<String> supplier;
//
//    @Override
//    public String toString() {
//        return supplier.get();
//    }
//}