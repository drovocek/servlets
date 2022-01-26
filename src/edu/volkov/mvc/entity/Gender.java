package edu.volkov.mvc.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE, FEMALE;

    public static Optional<Gender> find(String name) {
        return Arrays.stream(Gender.values())
                .filter(value -> value.name().equals(name))
                .findFirst();
    }
}
