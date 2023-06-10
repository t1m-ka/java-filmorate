package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.filmRating.FilmRatingDbStorage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestPropertySource(locations = "classpath:application.properties")
@Sql({"/schema.sql", "/data.sql", "/data-test.sql"})
public class FilmRatingDbStorageTest {
    public final FilmRatingDbStorage filmRatingDbStorage;

    @Test
    public void addLikeTest() {
        filmRatingDbStorage.addLike(3, 3);
        List<Film> popularFilm = filmRatingDbStorage.getMostPopularFilms(1);
        assertThat(popularFilm).hasSize(1);
        assertThat(popularFilm).element(0).hasFieldOrPropertyWithValue("name", "Форрест Гамп");
    }

    @Test
    public void deleteLikeTest() {
        filmRatingDbStorage.deleteLike(3, 3);
        List<Film> popularFilm = filmRatingDbStorage.getMostPopularFilms(2);
        assertThat(popularFilm).hasSize(2);
    }

    @Test
    public void getMostPopularFilmsTest() {
        filmRatingDbStorage.addLike(3, 3);
        List<Film> popularFilm = filmRatingDbStorage.getMostPopularFilms(3);
        assertThat(popularFilm).hasSize(3);
        assertThat(popularFilm).element(0).hasFieldOrPropertyWithValue("name", "Форрест Гамп");
        assertThat(popularFilm).element(1).hasFieldOrPropertyWithValue("name", "Побег из Шоушенка");
        assertThat(popularFilm).element(2).hasFieldOrPropertyWithValue("name", "Зеленая миля");
    }

}
