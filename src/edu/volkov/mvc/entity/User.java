package edu.volkov.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String imageUrl;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
}
