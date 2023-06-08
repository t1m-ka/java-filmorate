package ru.yandex.practicum.filmorate.storage.genre;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.exception.GenreNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.GenreRowMapper;

import java.util.List;

@Component
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> getAllGenres() {
        String sql = "SELECT * FROM genre ORDER BY id";
        List<Genre> genres = jdbcTemplate.query(sql, new GenreRowMapper());
        return genres;
    }

    @Override
    public Genre getGenreById(int genreId) {
        String sql = "SELECT * FROM genre WHERE id=" + genreId;
        try {
            Genre genre = (Genre) jdbcTemplate.queryForObject(sql, new GenreRowMapper());
            return genre;
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException("Жанр с id=" + genreId + " отсутствует");
        }
    }
}
