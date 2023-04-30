package ru.yandex.practicum.filmorate.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exceptions.ValidationException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final List<Film> films = new ArrayList<>();
    private int filmId = 1;

    @GetMapping
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        Film newFilm = film.toBuilder().id(filmId++).build();
        films.add(newFilm);
        return newFilm;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) throws ValidationException {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId() == film.getId()) {
                films.set(i, film);
                return film;
            }
        }
        throw new ValidationException("Фильм с id=" + film.getId() + " не найден");
    }
}
