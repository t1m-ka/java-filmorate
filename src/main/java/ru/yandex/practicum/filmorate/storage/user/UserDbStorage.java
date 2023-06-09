package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.UserRowMapper;

import java.util.List;

@Repository
@Primary
public class UserDbStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * "
                + "FROM app_user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User getUserById(long userId) {
        String sql = "SELECT * "
                + "FROM app_user "
                + "WHERE id=?";
        try {
            return (User) jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Пользователь с id=" + userId + " не зарегистрирован");
        }
    }

    @Override
    public User createUser(User user) {
        String sqlCreateUser = "INSERT INTO app_user (email, login, name, birthday) "
                + "VALUES (?, ?, ?, ?)";
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        jdbcTemplate.update(sqlCreateUser,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday());
        long lastUserId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM app_user", Long.class);
        return getUserById(lastUserId);
    }

    @Override
    public User updateUser(User user) {
        String sqlUpdateUser = "UPDATE app_user SET "
                + "email = ?, login = ?, name = ?, birthday = ?"
                + "WHERE id = ?";
        try {
            jdbcTemplate.update(sqlUpdateUser,
                    user.getEmail(),
                    user.getLogin(),
                    user.getName(),
                    user.getBirthday(),
                    user.getId());
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Пользователь с id=" + user.getId() + " не зарегистрирован");
        }
        return getUserById(user.getId());
    }
}
