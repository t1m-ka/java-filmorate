package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.film.FilmDbStorage;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql"})
public class FilmDbStorageTest {
    private final FilmDbStorage filmDbStorage;

    @Test
    public void getFilmsTest() {
        List<Film> allFilms = filmDbStorage.getFilms();
        assertThat(allFilms).hasSize(3);
    }

    @Test
    public void getFilmByIdTest() {
        Film film = filmDbStorage.getFilmById(2);
        assertThat(film).hasFieldOrPropertyWithValue("id", 2L);
    }

    @Test
    public void createFilmTest() {
        Film newFilm = filmDbStorage.createFilm(Film.builder()
                .name("test")
                .description("test")
                .releaseDate(LocalDate.now())
                .duration(20)
                .mpa(new Mpa(1, "Комедия"))
                .build());
        assertThat(newFilm).hasFieldOrPropertyWithValue("id", 4L);
    }

    @Test
    public void updateFilmTest() {
        Film updateFilm = filmDbStorage.updateFilm(Film.builder()
                .id(3L)
                .name("updated")
                .description("test")
                .releaseDate(LocalDate.now())
                .duration(20)
                .mpa(new Mpa(1, "Комедия"))
                .build());
        assertThat(updateFilm).hasFieldOrPropertyWithValue("id", 3L);
        assertThat(updateFilm).hasFieldOrPropertyWithValue("name", "updated");
    }
}
