package edu.volkov.mvc.dto;

import edu.volkov.mvc.entity.Gender;
import edu.volkov.mvc.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    Integer id;
    String name;
    String imageUrl;
    String email;
    LocalDate birthday;
    Role role;
    Gender gender;
}
