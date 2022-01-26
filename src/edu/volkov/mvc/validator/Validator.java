package edu.volkov.mvc.validator;

public interface Validator<T>{

    ValidationResult isValid(T object);
}
