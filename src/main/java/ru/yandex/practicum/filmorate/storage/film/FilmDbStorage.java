package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.FilmRowMapper;

import java.util.List;

@Repository
@Primary
public class FilmDbStorage implements FilmStorage {
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> getFilms() {
        String sql = "SELECT film.*, "
                + "(SELECT COUNT(film_id) "
                + "FROM likes "
                + "WHERE film_id=film.id) AS rate, "
                + "mpa.name AS mpa_name, "
                + "GROUP_CONCAT(genre.id) AS genre_id, "
                + "GROUP_CONCAT(genre.name) AS genre_name "
                + "FROM film "
                + "JOIN mpa ON film.mpa_id = mpa.id "
                + "JOIN film_genre ON (film.id = film_genre.film_id) "
                + "LEFT JOIN genre ON (film_genre.genre_id = genre.id) "
                + "GROUP BY film.id";
        return jdbcTemplate.query(sql, new FilmRowMapper());
    }

    @Override
    public Film getFilmById(long filmId) {
        String sql = "SELECT film.*, "
                + "(SELECT COUNT(film_id) "
                + "FROM likes "
                + "WHERE film_id=film.id) AS rate, "
                + "mpa.name AS mpa_name, "
                + "GROUP_CONCAT(genre.id) AS genre_id, "
                + "GROUP_CONCAT(genre.name) AS genre_name "
                + "FROM film "
                + "JOIN mpa ON film.mpa_id = mpa.id "
                + "LEFT JOIN film_genre ON (film.id = film_genre.film_id) "
                + "LEFT JOIN genre ON (film_genre.genre_id = genre.id) "
                + "WHERE film.id=? "
                + "GROUP BY film.id";
        try {
            return (Film) jdbcTemplate.queryForObject(sql, new FilmRowMapper(), filmId);
        } catch (EmptyResultDataAccessException e) {
            throw new FilmNotFoundException("Фильм с id=" + filmId + " отсутствует");
        }
    }

    @Override
    public Film createFilm(Film film) {
        String sql = "INSERT INTO film (name, description, release_date, duration, mpa_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpa().getId());
        sql = "SELECT film.*, "
                + "(SELECT COUNT(film_id) "
                + "FROM likes "
                + "WHERE film_id=film.id) AS rate, "
                + "mpa.name AS mpa_name, "
                + "GROUP_CONCAT(genre.id) AS genre_id, "
                + "GROUP_CONCAT(genre.name) AS genre_name "
                + "FROM film "
                + "JOIN mpa ON film.mpa_id = mpa.id "
                + "LEFT JOIN film_genre ON (film.id = film_genre.film_id) "
                + "LEFT JOIN genre ON (film_genre.genre_id = genre.id) "
                + "WHERE film.id IN (SELECT MAX(id) "
                + "FROM film "
                + "GROUP BY film.id";
        return (Film) jdbcTemplate.queryForObject(sql, new FilmRowMapper());
    }

    @Override
    public Film updateFilm(Film film) {
        return null;
    }
}
