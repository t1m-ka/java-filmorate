package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен запрос к эндпоинту: /GET /films");
        return filmStorage.getFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") long filmId) {
        log.info("Получен запрос к эндпоинту: /GET /films/" + filmId);
        return null;
    }

    @GetMapping("/popular?count={count}")
    public List<Film> getMostPopularFilms(@RequestParam(required = false) long count) {
        log.info("Получен запрос к эндпоинту: /GET /films/popular");
        return null;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Получен запрос к эндпоинту: /POST /films");
        return filmStorage.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /films");
        Film result = filmStorage.updateFilm(film);
        if (result == null)
            throw new ValidationException("Фильм с id=" + film.getId() + " не найден");
        return result;
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable("id") long userId, @PathVariable("userId") long otherUserId) {
        log.info("Получен запрос к эндпоинту: /PUT /films/{id}/like/{userId}");

    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable("id") long userId, @PathVariable("userId") long otherUserId) {
        log.info("Получен запрос к эндпоинту: /DELETE /films/{id}/like/{userId}");
    }
}
