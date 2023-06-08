package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.mapper.FilmRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class FilmDbStorage implements FilmStorage {
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> getFilms() {
        String sql = "SELECT f.id, "
                + "f.name, "
                + "f.description, "
                + "f.release_date, "
                + "f.duration, "
                + "(SELECT COUNT(film_id) "
                + "FROM likes "
                + "WHERE film_id=f.id) AS rate, "
                + "mpa.id AS mpa_id, "
                + "mpa.name AS mpa_name "
                + "FROM film f "
                + "JOIN mpa ON f.mpa_id = mpa.id;";

        List<Film> allFilms = jdbcTemplate.query(sql, new FilmRowMapper());
        return allFilms;
    }

    @Override
    public Film getFilmById(long filmId) {
        return null;
    }

    @Override
    public Film createFilm(Film film) {
        return null;
    }

    @Override
    public Film updateFilm(Film film) {
        return null;
    }
}
