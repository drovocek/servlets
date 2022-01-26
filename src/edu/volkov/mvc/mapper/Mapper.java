package edu.volkov.mvc.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
