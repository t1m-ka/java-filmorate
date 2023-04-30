package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exceptions.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final List<Film> films = new ArrayList<>();
    private int filmId = 1;

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен запрос к эндпоинту: /GET /films");
        return films;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /POST /films");
        if (film == null) {
            throw new ValidationException("Тело запроса не содержит объекта");
        }
        Film newFilm = film.toBuilder().id(filmId++).build();
        films.add(newFilm);
        log.info("Фильм успешно создан, id = " + newFilm.getId() + ". Всего фильмов " + films.size());
        return newFilm;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: /PUT /films");
        if (film == null) {
            throw new ValidationException("Тело запроса не содержит объекта");
        }
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId() == film.getId()) {
                films.set(i, film);
                log.info("Фильм с id = " + film.getId() + " успешно обновлен.");
                return film;
            }
        }
        throw new ValidationException("Фильм с id=" + film.getId() + " не найден");
    }
}
