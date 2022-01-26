package edu.volkov.mvc.mapper;

import edu.volkov.mvc.entity.Gender;
import edu.volkov.mvc.entity.Role;
import edu.volkov.mvc.entity.User;
import edu.volkov.mvc.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import edu.volkov.mvc.dto.CreateUserDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final String USER_FOLDER = "users/";
    private final static CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .imageUrl(USER_FOLDER + object.getImage().getSubmittedFileName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .gender(Gender.valueOf(object.getGender()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
