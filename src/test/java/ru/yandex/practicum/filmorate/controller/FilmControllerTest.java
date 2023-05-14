package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exception.ValidationException;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Film filmSample;
    private final FilmController controller = new FilmController(new InMemoryFilmStorage());

    @BeforeEach
    void createFilmSample() {
        filmSample = new Film(0,"name", "description",
                LocalDate.parse("2000-01-01"), 120);
        controller.createFilm(filmSample);
    }

    @Test
    void createFilmWithoutName() {
        Film film = filmSample.toBuilder().name("").build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWithoutDescription() {
        Film film = filmSample.toBuilder().description(" ").build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWithWrongDescription() {
        Film film = filmSample.toBuilder().description("1".repeat(201)).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWithWrongReleaseDate() {
        Film film = filmSample.toBuilder().releaseDate(LocalDate.parse("1895-12-28")).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWithWrongFutureReleaseDate() {
        Film film = filmSample.toBuilder().releaseDate(LocalDate.parse("2025-05-15")).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWithoutReleaseDate() {
        Film film = filmSample.toBuilder().releaseDate(null).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        //срабатывают 2 аннотации при валидации: @NotNull и @CorrectFilmDate
        assertEquals(2, violationSet.size());
    }

    @Test
    void createFilmWithNegativeDuration() {
        Film film = filmSample.toBuilder().duration(-200).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createFilmWith0Duration() {
        Film film = filmSample.toBuilder().duration(0).build();

        Set<ConstraintViolation<Film>> violationSet = validator.validate(film);
        assertEquals(1, violationSet.size());
    }

    @Test
    void createValidFilm() {
        Set<ConstraintViolation<Film>> violationSet = validator.validate(filmSample);
        assertEquals(0, violationSet.size());
    }

    @Test
    void getFilms() {
        List<Film> films = controller.getFilms();
        Film createdFilm = filmSample.toBuilder().id(1).build();
        assertEquals(createdFilm, films.get(0));
    }

    @Test
    void updateFilm() throws ValidationException {
        Film updatedFilm = filmSample.toBuilder().id(1).name("updated name").build();
        controller.updateFilm(updatedFilm);
        assertEquals(updatedFilm, controller.getFilms().get(0));
    }
}