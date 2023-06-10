package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql"})
public class MpaDbStorageTest {
    private final MpaStorage mpaStorage;

    @Test
    public void getAllMpaTest() {
        List<Mpa> allMpa = mpaStorage.getAllMpa();
        assertThat(allMpa).hasSize(5);
    }

    @Test
    public void getMpaByIdTest() {
        Mpa mpa = mpaStorage.getMpaById(1);
        assertThat(mpa).hasFieldOrPropertyWithValue("id", 1);
        assertThat(mpa).hasFieldOrPropertyWithValue("name", "G");
    }
}
