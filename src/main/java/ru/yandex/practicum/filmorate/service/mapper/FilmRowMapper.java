package ru.yandex.practicum.filmorate.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class FilmRowMapper implements RowMapper {
    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        Film film = new Film();
        film.setId(rs.getInt("id"));
        film.setName(rs.getString("name"));
        film.setDescription(rs.getString("description"));
        film.setReleaseDate(rs.getDate("release_date").toLocalDate());
        film.setDuration(rs.getInt("duration"));
        film.setRate(rs.getInt("rate"));
        film.setMpa(new Mpa(rs.getInt("mpa_id"), rs.getString("mpa_name")));

        LinkedHashSet<Genre> genres = new LinkedHashSet<>();
        if (rs.getString("genre_id") != null) {
            String[] idArr = rs.getString("genre_id").split(",");
            String[] nameArr = rs.getString("genre_name").split(",");

            for (int i = 0; i < idArr.length; i++) {
                genres.add(new Genre(Integer.parseInt(idArr[i]), nameArr[i]));
            }
        }
        film.setGenres(genres);
        return film;
    }
}
