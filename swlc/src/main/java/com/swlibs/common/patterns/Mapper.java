package com.swlibs.common.patterns;

/**
 * Реализует паттерн Mapper.
 * @param <A> DTO
 * @param <B> Entity
 */
public interface Mapper<A,B> {
    public B convertToEntity(A dto);
    public A convertToDto(B entity);
}
