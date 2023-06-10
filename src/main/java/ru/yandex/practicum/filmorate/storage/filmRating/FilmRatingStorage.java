package ru.yandex.practicum.filmorate.storage.filmRating;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmRatingStorage {
    void addLike(long filmId, long userId);

    void deleteLike(long filmId, long userId);

    List<Film> getMostPopularFilms(long count);

}
