package edu.volkov.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import edu.volkov.mvc.validator.Error;

import java.util.List;

@RequiredArgsConstructor
public class ValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;
}
