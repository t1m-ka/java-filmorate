package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage{
    private final List<Film> films = new ArrayList<>();
    private int filmId = 1;

    @Override
    public List<Film> getFilms() {
        return films;
    }

    @Override
    public Film createFilm(Film film) {
        Film newFilm = film.toBuilder().id(filmId++).build();
        films.add(newFilm);
        log.info("Фильм успешно создан, id = " + newFilm.getId() + ". Всего фильмов " + films.size());
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId() == film.getId()) {
                films.set(i, film);
                log.info("Фильм с id = " + film.getId() + " успешно обновлен.");
                return film;
            }
        }
        return null;
    }
}
