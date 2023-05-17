package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.*;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    private final UserService userService;
    private final HashMap<Long, HashSet<Long>> likes = new HashMap<>();

    public FilmService(FilmStorage filmStorage, UserService userService) {
        this.filmStorage = filmStorage;
        this.userService = userService;
    }

    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film getFilmById(long filmId) {
        checkFilmExist(filmId);
        return filmStorage.getFilmById(filmId);
    }

    private void checkFilmExist(long filmId) {
        if (filmStorage.getFilmById(filmId) == null)
            throw new FilmNotFoundException("Фильм с id=" + filmId + " не найден");
    }

    public Film createFilm(Film film) {
        Film result = filmStorage.createFilm(film);
        likes.put(result.getId(), new HashSet<>());
        return result;
    }

    public Film updateFilm(Film film) {
        checkFilmExist(film.getId());
        return filmStorage.updateFilm(film);
    }

    public void addLike(long filmId, long userId) {
        checkFilmExist(filmId);
        userService.checkUserExist(userId);
        if (likes.containsKey(filmId)) {
            likes.get(filmId).add(userId);
        } else {
            likes.put(filmId, new HashSet<>(Collections.singleton(userId)));
        }
    }

    public void deleteLike(long filmId, long userId) {
        checkFilmExist(filmId);
        userService.checkUserExist(userId);
        if (likes.containsKey(filmId)) {
            likes.get(filmId).remove(userId);
        }
    }

    public List<Film> getMostPopularFilms(long count) {
        List<Film> result = new ArrayList<>();
        Queue<Film> popularSortedFilms = new PriorityQueue<>((x1, x2) ->
                likes.get(x2.getId()).size() - likes.get(x1.getId()).size());
        popularSortedFilms.addAll(filmStorage.getFilms());
        long i = 1;
        for (Film film : popularSortedFilms) {
            result.add(film);
            if (i >= count)
                break;
            i++;
        }
        return result;
    }
}
