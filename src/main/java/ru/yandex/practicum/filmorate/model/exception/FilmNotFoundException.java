package ru.yandex.practicum.filmorate.model.exception;

public class FilmNotFoundException extends NullPointerException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
