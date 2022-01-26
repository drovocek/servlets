package edu.volkov.mvc.service;

import edu.volkov.mvc.dao.UserDao;
import edu.volkov.mvc.dto.CreateUserDto;
import edu.volkov.mvc.dto.UserDto;
import edu.volkov.mvc.entity.User;
import edu.volkov.mvc.exception.ValidationException;
import edu.volkov.mvc.mapper.CreateUserMapper;
import edu.volkov.mvc.mapper.UserMapper;
import edu.volkov.mvc.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private static final CreateUserValidator validator = CreateUserValidator.getInstance();
    private static final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private static final UserMapper userMapper = UserMapper.getInstance();

    private static final UserDao userDao = UserDao.getInstance();
    private static final ImageService imageService = ImageService.getInstance();

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = validator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        User persist = createUserMapper.mapFrom(userDto);
        imageService.upload(persist.getImageUrl(), userDto.getImage().getInputStream());
        return userDao.save(persist).getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
