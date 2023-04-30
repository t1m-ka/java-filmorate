package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Film filmSample;

    @BeforeEach
    void createFilmSample() {
        filmSample = new Film(1,"name", "description",
                LocalDate.parse("2000-01-01"), 120);
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
        Film film = filmSample.toBuilder().description("very very looooooooooooooooooooooooooooooooooooooooooo"
                + "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
                + "oooooooooooooooooooooooooooooooooooooooong description").build();

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
}