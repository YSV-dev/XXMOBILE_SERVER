package com.uralkali.api.model;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данные исключения.
 * Замена "ExceptionMessage".
 *
 * @author Selivanovskikh MG
 * @version 1 (2023-05-24)
 */
@Data
@AllArgsConstructor
public class ExceptionData {
    private final String exception;
    private final @NonNull String message;
    private final ExceptionData cause;

    public ExceptionData(Throwable ex) {
        this.exception = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        Throwable parentCause = ex.getCause();
        if(parentCause != null) {
            this.cause = new ExceptionData(parentCause);
        } else {
            this.cause = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ExceptionData e = this;
        do {
            result.append(String.format("[%s] %s\r\n", e.exception, e.message));
        } while((e = e.cause) != null);
        return result.toString();
    }
}
