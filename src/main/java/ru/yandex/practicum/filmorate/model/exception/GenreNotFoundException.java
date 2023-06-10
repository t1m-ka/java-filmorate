package ru.yandex.practicum.filmorate.model.exception;

public class GenreNotFoundException extends NullPointerException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
