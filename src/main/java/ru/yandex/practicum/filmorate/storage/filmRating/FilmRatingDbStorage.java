package ru.yandex.practicum.filmorate.storage.filmRating;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.FilmRowMapper;

import java.util.List;

@Repository
public class FilmRatingDbStorage implements FilmRatingStorage {
    private final JdbcTemplate jdbcTemplate;

    public FilmRatingDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(long filmId, long userId) {
        String sql = "INSERT INTO likes (user_id, film_id) "
                + "VALUES (?, ?) "
                + "ON CONFLICT DO NOTHING;";
        checkUserExists(userId);
        checkFilmExists(filmId);
        jdbcTemplate.update(sql, userId, filmId);
    }

    @Override
    public void deleteLike(long filmId, long userId) {
        String sql = "DELETE FROM likes WHERE film_id = ? AND user_id = ?";
        checkUserExists(userId);
        checkFilmExists(filmId);
        jdbcTemplate.update(sql, filmId, userId);
    }

    @Override
    public List<Film> getMostPopularFilms(long count) {
        String sql = "SELECT film.*, "
                + "COUNT(DISTINCT likes.user_id) AS rate,"
                + "mpa.name AS mpa_name, "
                + "GROUP_CONCAT(genre.id) AS genre_id, "
                + "GROUP_CONCAT(genre.name) AS genre_name "
                + "FROM film "
                + "JOIN mpa ON film.mpa_id = mpa.id "
                + "LEFT JOIN likes ON (film.id = likes.film_id) "
                + "LEFT JOIN film_genre ON (film.id = film_genre.film_id) "
                + "LEFT JOIN genre ON (film_genre.genre_id = genre.id) "
                + "GROUP BY film.id "
                + "ORDER BY rate DESC "
                + "LIMIT ?";
        return jdbcTemplate.query(sql, new FilmRowMapper(), count);
    }

    private void checkUserExists(long userId) {
        try {
            jdbcTemplate.queryForObject("SELECT id FROM app_user WHERE id=?", Long.class, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Пользователь с id=" + userId + " не зарегистрирован");
        }
    }

    private void checkFilmExists(long filmId) {
        try {
            jdbcTemplate.queryForObject("SELECT id FROM film WHERE id=?", Long.class, filmId);
        } catch (EmptyResultDataAccessException e) {
            throw new FilmNotFoundException("Фильм с id=" + filmId + " отсутствует");
        }
    }
}
