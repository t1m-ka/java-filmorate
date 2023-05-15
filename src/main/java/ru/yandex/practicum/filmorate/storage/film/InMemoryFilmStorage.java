package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final List<Film> films = new ArrayList<>();
    private long filmId = 1;

    @Override
    public List<Film> getFilms() {
        return films;
    }

    @Override
    public Film getFilmById(long filmId) {
        Optional<Film> result = films.stream()
                .filter(x -> x.getId() == filmId)
                .findFirst();
        if (result.isPresent()) return result.get();
        return null;
    }

    @Override
    public Film createFilm(Film film) {
        Film newFilm = film.toBuilder().id(filmId++).build();
        films.add(newFilm);
        log.info("Фильм успешно создан, id = " + newFilm.getId() + ". Всего фильмов " + films.size());
        return newFilm;
    }

    @Override
    public Film updateFilm(Film film) {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId() == film.getId()) {
                films.set(i, film);
                log.info("Фильм с id = " + film.getId() + " успешно обновлен.");
                return films.get(i);
            }
        }
        return null;
    }
}
