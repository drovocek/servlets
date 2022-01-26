package edu.volkov.mvc.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER, ADMIN;

    public static Optional<Role> find(String name) {
        return Arrays.stream(Role.values())
                .filter(value -> value.name().equals(name))
                .findFirst();
    }
}
