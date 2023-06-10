package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql"})
public class GenreDbStorageTest {
    private final GenreStorage genreStorage;

    @Test
    public void getAllGenresTest() {
        List<Genre> allGenres = genreStorage.getAllGenres();
        assertThat(allGenres).hasSize(6);
    }

    @Test
    public void getGenreByIdTest() {
        Genre genre = genreStorage.getGenreById(2);
        assertThat(genre).hasFieldOrPropertyWithValue("id", 2);
        assertThat(genre).hasFieldOrPropertyWithValue("name", "Драма");
    }
}
