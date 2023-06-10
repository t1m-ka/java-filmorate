package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.filmRating.FilmRatingStorage;

import java.util.List;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final FilmRatingStorage filmRatingStorage;

    public FilmService(FilmStorage filmStorage, FilmRatingStorage filmRatingStorage) {
        this.filmStorage = filmStorage;
        this.filmRatingStorage = filmRatingStorage;
    }

    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film getFilmById(long filmId) {
        return filmStorage.getFilmById(filmId);
    }

    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public void addLike(long filmId, long userId) {
        filmRatingStorage.addLike(filmId, userId);
    }

    public void deleteLike(long filmId, long userId) {
        filmRatingStorage.deleteLike(filmId, userId);
    }

    public List<Film> getMostPopularFilms(long count) {
        return filmRatingStorage.getMostPopularFilms(count);
    }
}
