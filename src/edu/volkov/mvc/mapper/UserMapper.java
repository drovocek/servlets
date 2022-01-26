package edu.volkov.mvc.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import edu.volkov.mvc.dto.UserDto;
import edu.volkov.mvc.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private final static UserMapper INSTANCE = new UserMapper();

    public static UserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .name(object.getName())
                .imageUrl(object.getImageUrl())
                .email(object.getEmail())
                .role(object.getRole())
                .gender(object.getGender())
                .birthday(object.getBirthday())
                .build();
    }
}
