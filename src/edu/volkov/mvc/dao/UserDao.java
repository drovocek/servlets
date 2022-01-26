package edu.volkov.mvc.dao;

import edu.volkov.mvc.entity.Role;
import edu.volkov.mvc.entity.User;
import edu.volkov.mvc.util.DaoConnectionManager;
import lombok.SneakyThrows;
import edu.volkov.mvc.entity.Gender;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL =
            "INSERT INTO users (name, image_url, email, birthday, password, role, gender) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_EMAIL_SQL =
            "SELECT * FROM USERS WHERE email=?";

    private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM USERS WHERE email=? AND password=?";

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public void update(User persist) {

    }

    @SneakyThrows
    @Override
    public User save(User persist) {
        try (var connection = DaoConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, persist.getName());
            preparedStatement.setObject(2, persist.getImageUrl());
            preparedStatement.setObject(3, persist.getEmail());
            preparedStatement.setObject(4, persist.getBirthday());
            preparedStatement.setObject(5, persist.getPassword());
            preparedStatement.setObject(6, persist.getRole().name());
            preparedStatement.setObject(7, persist.getGender().name());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            persist.setId(generatedKeys.getObject("id", Integer.class));
            return persist;
        }
    }

    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        try (var connection = DaoConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
            preparedStatement.setObject(1, email);

            preparedStatement.execute();

            var resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = DaoConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            preparedStatement.execute();

            var resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("image_url", String.class),
                resultSet.getObject("birthday", Date.class).toLocalDate(),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class),
                Role.valueOf(resultSet.getObject("role", String.class)),
                Gender.valueOf(resultSet.getObject("gender", String.class))
        );
    }
}
