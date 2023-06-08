package ru.yandex.practicum.filmorate.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.mapper.MpaRowMapper;

import java.util.List;

@Service
public class MpaService {
    private final JdbcTemplate jdbcTemplate;

    public MpaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Mpa> getAllMpa() {
        String sql = "SELECT * FROM mpa ORDER BY id";
        List<Mpa> allMpa = jdbcTemplate.query(sql, new MpaRowMapper());
        return allMpa;
    }

    public Mpa getMpaById(int id) {
        //SqlRowSet mpaRows = jdbcTemplate.queryForRowSet("SELECT id, name FROM mpa WHERE id = ?", id);
        //if(mpaRows.next())




        //String sql= "SELECT id, name FROM mpa WHERE id=" + id;
        //List<Mpa> mpa = jdbcTemplate.query(sql, new MpaRowMapper());


        return (Mpa) jdbcTemplate.queryForObject("SELECT * FROM mpa WHERE id = ?", new Object[]{id}, new MpaRowMapper());
        //return mpa.get(0);
    }
}
