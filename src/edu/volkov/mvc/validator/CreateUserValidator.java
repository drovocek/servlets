package edu.volkov.mvc.validator;

import edu.volkov.mvc.dao.UserDao;
import edu.volkov.mvc.entity.Gender;
import edu.volkov.mvc.entity.Role;
import edu.volkov.mvc.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import edu.volkov.mvc.dto.CreateUserDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private static final UserDao userDao = UserDao.getInstance();

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();
        if (object.getName() == null || object.getName().isEmpty()) {
            validationResult.addError(Error.of("invalid.name", "Name is invalid"));
        }
        if (Gender.find(object.getGender()).isEmpty()) {
            validationResult.addError(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (Role.find(object.getRole()).isEmpty()) {
            validationResult.addError(Error.of("invalid.role", "Role is invalid"));
        }
        if (object.getEmail() == null || object.getEmail().isEmpty() || !object.getEmail().contains("@")) {
            validationResult.addError(Error.of("invalid.email", "Email is invalid"));
        } else if (userDao.findByEmail(object.getEmail()).isPresent()) {
            validationResult.addError(Error.of("invalid.email.duplicate", "Email duplicate"));
        }
        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.addError(Error.of("invalid.date", "Date is invalid"));
        }
        if (object.getPassword() == null || object.getPassword().isEmpty()) {
            validationResult.addError(Error.of("invalid.password", "Password is invalid"));
        }
        return validationResult;
    }
}
