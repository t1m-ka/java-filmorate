package ru.yandex.practicum.filmorate.storage.friendship;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.model.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.UserRowMapper;

import java.util.List;

@Repository
public class FriendshipDbStorage implements FriendshipStorage {
    private final JdbcTemplate jdbcTemplate;

    public FriendshipDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFriend(long userId, long friendId) {
        String sql = "INSERT INTO friendship (follower_user_id, followed_user_id) "
                + "VALUES (?, ?) "
                + "ON CONFLICT DO NOTHING;";
        checkUserExists(userId);
        checkUserExists(friendId);
        jdbcTemplate.update(sql, userId, friendId);
    }

    @Override
    public List<User> getUserFriends(long userId) {
        String sql = "SELECT public.app_user.* "
                + "FROM public.app_user "
                + "WHERE app_user.id IN (SELECT followed_user_id "
                + "FROM public.friendship "
                + "WHERE follower_user_id = ?);";
        return jdbcTemplate.query(sql, new UserRowMapper(), userId);
    }

    @Override
    public List<User> getCommonUserFriends(long userId, long otherUserId) {
        String sql = "SELECT * "
                + "FROM app_user "
                + "WHERE id IN (SELECT followed_user_id "
                + "FROM friendship "
                + "WHERE follower_user_id = ? AND "
                + "followed_user_id IN (SELECT followed_user_id "
                + "FROM friendship "
                + "WHERE follower_user_id = ?));";
        return jdbcTemplate.query(sql, new UserRowMapper(), userId, otherUserId);
    }

    @Override
    public void deleteFriend(long userId, long friendId) {
        String sql = "DELETE FROM friendship WHERE follower_user_id = ? AND followed_user_id = ?";
        jdbcTemplate.update(sql, userId, friendId);
    }

    private void checkUserExists(long userId) {
        try {
            jdbcTemplate.queryForObject("SELECT id FROM app_user WHERE id=?", Long.class, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Пользователь с id=" + userId + " не зарегистрирован");
        }
    }
}
