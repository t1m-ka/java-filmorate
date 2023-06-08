package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.exception.MpaNotFoundException;
import ru.yandex.practicum.filmorate.service.mapper.MpaRowMapper;

import java.util.List;

@Repository
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mpa> getAllMpa() {
        String sql = "SELECT * FROM mpa ORDER BY id";
        List<Mpa> allMpa = jdbcTemplate.query(sql, new MpaRowMapper());
        return allMpa;
    }

    @Override
    public Mpa getMpaById(int mpaId) {
        String sql = "SELECT * FROM mpa WHERE id=" + mpaId;
        try {
            Mpa mpa = (Mpa) jdbcTemplate.queryForObject(sql, new MpaRowMapper());
            return mpa;
        } catch (EmptyResultDataAccessException e) {
            throw new MpaNotFoundException("Рейтинг с id=" + mpaId + " отсутствует");
        }
    }
}
