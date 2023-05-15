package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.exception.IncorrectParameterException;
import ru.yandex.practicum.filmorate.model.exception.ValidationException;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен запрос к эндпоинту: /GET /films");
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable("id") long filmId) {
        log.info("Получен запрос к эндпоинту: /GET /films/" + filmId);
        Film result = filmService.getFilmById(filmId);
        if (result == null)
            throw new FilmNotFoundException("Фильм не найден");
        return result;
    }

    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(defaultValue = "10", required = false) long count) {
        log.info("Получен запрос к эндпоинту: /GET /films/popular");
        if (count <= 0) {
            throw new IncorrectParameterException("count");
        }
        return filmService.getMostPopularFilms(count);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Получен запрос к эндпоинту: /POST /films");
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /films");
        Film result = filmService.updateFilm(film);
        if (result == null)
            throw new FilmNotFoundException("Фильм с id=" + film.getId() + " не найден");
        return result;
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable("id") long filmId, @PathVariable long userId) {
        log.info("Получен запрос к эндпоинту: /PUT /films/{id}/like/{userId}");
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable("id") long filmId, @PathVariable long userId) {
        log.info("Получен запрос к эндпоинту: /DELETE /films/{id}/like/{userId}");
        filmService.deleteLike(filmId, userId);
    }
}
